package com.desafio.goldenraspberryawards.service;

import com.desafio.goldenraspberryawards.dto.MovieDTO;
import com.desafio.goldenraspberryawards.entiity.Movie;
import com.desafio.goldenraspberryawards.repository.MovieRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class MovieService {

  private final MovieRepository repository;

  private final ProducerService producerService;

  private final StudioService studioService;

  public MovieService(MovieRepository repository, ProducerService producerService, StudioService studioService) {
    this.repository = repository;
    this.producerService = producerService;
    this.studioService = studioService;
  }

  public Movie findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public Movie saveMovie(MovieDTO dto) {
    if (!isMovieDuplicate(dto)) return repository.save(mapDtoToMovie(dto));
    return null;
  }

  public Movie mapDtoToMovie(MovieDTO dto) {
    val movie = new Movie();
    movie.setTitle(dto.getTitle());
    movie.setYear(dto.getYear());
    movie.setIsWinner(dto.getIsWinner());
    movie.setProducers(producerService.getOrCreateProducers(dto.getProducers()));
    movie.setStudios(studioService.getOrCreateStudios(dto.getStudios()));
    return movie;
  }

  private boolean isMovieDuplicate(MovieDTO dto) {
    return nonNull(repository.findDuplicatedMovie(dto.getTitle(), dto.getYear()));
  }

  public List<Movie> findAll() {
    return repository.findAll();
  }

  public Movie patch(Long id, Movie patched) {
    val existingMovie = repository.findById(id);
    if (existingMovie.isPresent()) {
      val movieToUpdate = existingMovie.get();
      if (nonNull(patched.getTitle())) {
        movieToUpdate.setTitle(patched.getTitle());
      }
      if (nonNull(patched.getYear())) {
        movieToUpdate.setYear(patched.getYear());
      }
      if (nonNull(patched.getTitle())) {
        movieToUpdate.setIsWinner(patched.getIsWinner());
      }
      return repository.save(movieToUpdate);
    }
    return null;
  }

  public Boolean delete(Long id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }
}
