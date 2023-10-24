package com.desafio.goldenraspberryawards.controller;

import com.desafio.goldenraspberryawards.entiity.Movie;
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
public class MovieControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testFindAllMovies_shouldReturnCorrect() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
        .andReturn();

    int status = result.getResponse().getStatus();
    assertEquals(200, status);

    List<Movie> movies = objectMapper.readValue(
        result.getResponse().getContentAsString(),
        objectMapper.getTypeFactory().constructCollectionType(List.class, Movie.class)
    );

    assertNotNull(movies);
    assertFalse(movies.isEmpty());
  }
}