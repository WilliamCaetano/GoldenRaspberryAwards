package com.desafio.goldenraspberryawards.controller.interf;

import com.desafio.goldenraspberryawards.entiity.Movie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Movie Controller")
public interface IMovieController {

  @ApiOperation("Find a movie by the given id")
  ResponseEntity<Movie> findById(Long id);

  @ApiOperation("Find all movies")
  ResponseEntity<List<Movie>> findAll();

  @ApiOperation("Patch a movie by the given id")
  ResponseEntity<Movie> patch(Long id, Movie patched);

  @ApiOperation("Delete a movie by the given id")
  ResponseEntity<Void> delete(Long id);
}
