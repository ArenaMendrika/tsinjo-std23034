package com.hei.school.endpoint.rest.controller;

import com.hei.school.repository.DonationRepository;
import com.hei.school.repository.HelpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final DonationRepository donationRepository;
  private final HelpRepository helpRepository;

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("donations", donationRepository.findAllByOrderByIdDesc());
    model.addAttribute("helps", helpRepository.findAllByOrderByIdDesc());
    return "index";
  }
}
