package com.desafio.goldenraspberryawards.controller;

import com.desafio.goldenraspberryawards.entiity.Producer;
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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProducerControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testFindAllProducers_shouldReturnCorrect() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/producers"))
        .andReturn();

    int status = result.getResponse().getStatus();
    assertEquals(200, status);

    List<Producer> producers = objectMapper.readValue(
        result.getResponse().getContentAsString(),
        objectMapper.getTypeFactory().constructCollectionType(List.class, Producer.class)
    );

    assertNotNull(producers);
    assertFalse(producers.isEmpty());
  }

  @Test
  public void testFindProducerById_shouldReturnCorrect() throws Exception {
    Long producerId = 1L;
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/producers/{id}", producerId))
        .andReturn();

    assertEquals(200, result.getResponse().getStatus());

    Producer producer = objectMapper.readValue(
        result.getResponse().getContentAsString(),
        Producer.class
    );

    assertEquals("Allan Carr", producer.getName());
  }

  @Test
  public void testFindProducerById_shouldReturnNotFound() throws Exception {
    Long producerId = 9999L;
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/producers/{id}", producerId))
        .andReturn();

    int status = result.getResponse().getStatus();
    assertEquals(404, status);
  }

}
