package com.desafio.goldenraspberryawards.service;

import com.desafio.goldenraspberryawards.dto.MovieDTO;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class CsvReaderService {

  private MovieService movieService;

  public CsvReaderService(MovieService movieService) {
    this.movieService = movieService;
  }

  @Transactional
  public void processCsvFile(InputStream inputStream) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      val moviesDto = reader.lines()
          .skip(1)
          .map(this::mapCsvToMovieDto)
          .collect(Collectors.toList());
      for (MovieDTO dto : moviesDto) {
        movieService.saveMovie(dto);
      }
    }
  }

  private MovieDTO mapCsvToMovieDto(String line) {
    String[] fields = line.split(";");
    return new MovieDTO(
        fields[1],
        Long.parseLong(fields[0]),
        fields.length != 4,
        fields[3],
        fields[2]
    );
  }
}
