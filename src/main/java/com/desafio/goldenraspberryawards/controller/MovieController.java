package com.desafio.goldenraspberryawards.controller;

import com.desafio.goldenraspberryawards.controller.interf.IMovieController;
import com.desafio.goldenraspberryawards.entiity.Movie;
import com.desafio.goldenraspberryawards.service.MovieService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/movies")
public class MovieController implements IMovieController {

  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @Override
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Movie> findById(@PathVariable Long id) {
    val movie = movieService.findById(id);
    return nonNull(movie) ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
  }

  @Override
  @GetMapping
  @ResponseBody
  public ResponseEntity<List<Movie>> findAll() {
    return ResponseEntity.ok(movieService.findAll());
  }

  @Override
  @PatchMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Movie> patch(@PathVariable Long id, @RequestBody Movie patched) {
    val updatedMovie = movieService.patch(id, patched);
    return nonNull(updatedMovie) ? ResponseEntity.ok(updatedMovie) : ResponseEntity.notFound().build();
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return movieService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
