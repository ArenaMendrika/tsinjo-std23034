package com.hei.school.service;

import com.hei.school.model.Payment;
import com.hei.school.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VolaService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${vola.api.url}")
    private String baseUrl;

    @Value("${vola.api.key}")
    private String apiKey;

    public void submitPayment(Payment payment, String email) {
        String url = baseUrl + "/payment?apiKey=" + apiKey +
                "&payerEmail=" + email +
                "&pspType=" + payment.getPspType() +
                "&pspPaymentId=" + payment.getPspPaymentId();

        restTemplate.postForObject(url, null, Object.class);

        payment.setPayerEmail(email);
        payment.setStatus("VERIFYING");
        payment.setCreationDate(LocalDateTime.now());
        paymentRepository.save(payment);
    }

    @Scheduled(fixedRate = 30000)
    public void pollPayments() {
        List<Payment> verifyingPayments = paymentRepository.findByStatus("VERIFYING");

        for (Payment payment : verifyingPayments) {
            try {
                String url = baseUrl + "/payment?apiKey=" + apiKey +
                        "&payerEmail=" + payment.getPayerEmail() +
                        "&pspType=" + payment.getPspType() +
                        "&pspPaymentId=" + payment.getPspPaymentId();

                Map<String, Object> response = restTemplate.getForObject(url, Map.class);
                String status = (String) response.get("verificationStatus");

                if ("SUCCEEDED".equals(status) || "FAILED".equals(status)) {
                    payment.setStatus(status);
                    paymentRepository.save(payment);
                    System.out.println("Paiement " + payment.getId() + " mis à jour → " + status);
                }

            } catch (Exception e) {
                System.err.println("Erreur de polling pour paiement " + payment.getId());
                e.printStackTrace();
            }
        }
    }
}
