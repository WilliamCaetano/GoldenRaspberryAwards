package com.desafio.goldenraspberryawards.controller;

import com.desafio.goldenraspberryawards.controller.interf.IAwardsController;
import com.desafio.goldenraspberryawards.dto.AwardsSummaryDTO;
import com.desafio.goldenraspberryawards.service.AwardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/awards")
public class AwardsController implements IAwardsController {

  private final AwardsService awardsService;

  public AwardsController(AwardsService awardsService) {
    this.awardsService = awardsService;
  }

  @Override
  @GetMapping("/producer-summary")
  @ResponseBody
  public ResponseEntity<AwardsSummaryDTO> getProducerAwardsSummary() {
    return ResponseEntity.ok(awardsService.findProducerAwardsSummary());
  }

}
