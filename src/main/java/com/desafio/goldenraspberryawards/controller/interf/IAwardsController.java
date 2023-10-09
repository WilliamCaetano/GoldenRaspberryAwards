package com.desafio.goldenraspberryawards.controller.interf;

import com.desafio.goldenraspberryawards.dto.AwardsSummaryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(tags = "Awards Controller")
public interface IAwardsController {

  @ApiOperation("Get producers awards summary")
  ResponseEntity<AwardsSummaryDTO> getProducerAwardsSummary();
}
