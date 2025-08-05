package com.hei.school.endpoint.rest.controller;

import com.hei.school.model.Help;
import com.hei.school.repository.HelpRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HelpController {

  private final HelpRepository helpRepository;

  @GetMapping("/helps")
  public List<Help> getHelps() {
    return helpRepository.findAllByOrderByIdDesc();
  }
}
