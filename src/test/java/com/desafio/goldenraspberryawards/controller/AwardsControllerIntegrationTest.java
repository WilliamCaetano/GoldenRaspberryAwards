package com.desafio.goldenraspberryawards.controller;

import com.desafio.goldenraspberryawards.dto.AwardsSummaryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AwardsControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testGetProducerAwardsSummary_shouldReturnCorrect() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/awards/producer-summary"))
        .andReturn();

    int status = result.getResponse().getStatus();
    assertEquals(200, status);

    AwardsSummaryDTO awardSummary = objectMapper.readValue(
        result.getResponse().getContentAsString(),
        AwardsSummaryDTO.class
    );

    assertNotNull(awardSummary);
    assertEquals(awardSummary.getMax().get(0).getInterval(), Long.valueOf(13));
    assertEquals(awardSummary.getMin().get(0).getInterval(), Long.valueOf(1));
  }

}
