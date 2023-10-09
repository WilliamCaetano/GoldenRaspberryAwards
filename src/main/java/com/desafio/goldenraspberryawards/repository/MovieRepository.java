package com.desafio.goldenraspberryawards.repository;

import com.desafio.goldenraspberryawards.entiity.Movie;
import com.desafio.goldenraspberryawards.jpa.MovieJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {

  private final MovieJpa jpa;

  public MovieRepository(MovieJpa jpa) {
    this.jpa = jpa;
  }

  public Optional<Movie> findById(Long id) {
    return jpa.findById(id);
  }

  public List<Movie> findAll() {
    return jpa.findAll();
  };

  public Movie save(Movie movie) {
    return jpa.save(movie);
  }

  public Movie findDuplicatedMovie(String title, Long year) {
    return jpa.findDuplicatedMovie(title, year);
  }

  public Boolean existsById(Long id) {
    return jpa.existsById(id);
  }

  public void deleteById(Long id) {
    jpa.deleteById(id);
  }

  public void deleteAll() {
    jpa.deleteAll();
  }
}
