import com.hei.school.model.Donation;
import com.hei.school.model.Donor;
import com.hei.school.model.Payment;
import com.hei.school.repository.DonationRepository;
import com.hei.school.repository.DonorRepository;
import com.hei.school.service.VolaService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DonationController {

  private final DonationRepository donationRepository;
  private final DonorRepository donorRepository;
  private final VolaService volaService;

  @PostMapping("/donations")
  public String createDonationFromForm(
      @RequestParam String donorFullName,
      @RequestParam String donorEmail,
      @RequestParam String pspType,
      @RequestParam String pspPaymentId,
      @RequestParam int amount) {
    Donor donor = donorRepository.save(new Donor(null, donorEmail, donorFullName));

    Payment payment = new Payment();
    payment.setAmount(amount);
    payment.setPspType(pspType);
    payment.setPspPaymentId(pspPaymentId);
    payment.setStatus("VERIFYING");
    payment.setCreationDate(LocalDateTime.now());

    Donation donation = new Donation();
    donation.setDonor(donor);
    donation.setPayment(payment);

    volaService.submitPayment(payment, donorEmail);

    donationRepository.save(donation);

    return "redirect:/";
  }

  @GetMapping("/donations")
  public List<Donation> getDonations() {
    return donationRepository.findAllByOrderByIdDesc();
  }
}
