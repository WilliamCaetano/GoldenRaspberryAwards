package com.desafio.goldenraspberryawards.controller;

import com.desafio.goldenraspberryawards.entiity.Producer;
import com.desafio.goldenraspberryawards.entiity.Studio;
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
public class StudioControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testFindAllStudios_shouldReturnCorrect() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/studios"))
        .andReturn();

    int status = result.getResponse().getStatus();
    assertEquals(200, status);

    List<Studio> studios = objectMapper.readValue(
        result.getResponse().getContentAsString(),
        objectMapper.getTypeFactory().constructCollectionType(List.class, Studio.class)
    );

    assertNotNull(studios);
    assertFalse(studios.isEmpty());
  }

  @Test
  public void testFindStudioById_shouldReturnCorrect() throws Exception {
    Long studioId = 1L;
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/studios/{id}", studioId))
        .andReturn();

    assertEquals(200, result.getResponse().getStatus());

    Studio studio = objectMapper.readValue(
        result.getResponse().getContentAsString(),
        Studio.class
    );

    assertEquals("Associated Film Distribution", studio.getName());
  }

  @Test
  public void testFindStudioById_shouldReturnNotFound() throws Exception {
    Long studioId = 9999L;
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/studios/{id}", studioId))
        .andReturn();

    int status = result.getResponse().getStatus();
    assertEquals(404, status);
  }
}
