package com.desafio.goldenraspberryawards.service;

import com.desafio.goldenraspberryawards.dto.AwardsDTO;
import com.desafio.goldenraspberryawards.dto.AwardsSummaryDTO;
import com.desafio.goldenraspberryawards.entiity.Movie;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class AwardsService {

  private final MovieService movieService;

  public AwardsService(MovieService movieService) {
    this.movieService = movieService;
  }

  public AwardsSummaryDTO findProducerAwardsSummary() {
    val movies = movieService.findAll();
    Map<String, List<Long>> producerAwards = new HashMap<>();
    if (nonNull(movies) && !movies.isEmpty()) {
      movies.stream().filter(Movie::getIsWinner).forEach(movie -> movie.getProducers().forEach(producer -> {
        producerAwards.computeIfAbsent(producer.getName(), k -> new ArrayList<>()).add(movie.getYear());
      }));
      val awardInterval = findIntervalBetweenTwoAwards(producerAwards);
      return new AwardsSummaryDTO()
          .setMin(Collections.singletonList(awardInterval.getFirst()))
          .setMax(Collections.singletonList(awardInterval.getLast()));
    }
    return new AwardsSummaryDTO();
  }

  private LinkedList<AwardsDTO> findIntervalBetweenTwoAwards(Map<String, List<Long>> producerAwards) {
    val awards = new LinkedList<AwardsDTO>();
    for (Map.Entry<String, List<Long>> entry : producerAwards.entrySet()) {
      val awardsYears = entry.getValue().stream().sorted().collect(Collectors.toList());
      if (awardsYears.size() >= 2) {
        for (int i = 0; i < awardsYears.size() - 1; i++) {
          val firstYear = awardsYears.get(i);
          val secondYear = awardsYears.get(i + 1);
          val interval = secondYear - firstYear;
          val award = setAwardFields(entry.getKey(), firstYear, secondYear, interval);
          awards.add(award);
        }
      }
    }
    awards.sort(Comparator.comparingLong(AwardsDTO::getInterval));
    return awards;
  }

  private AwardsDTO setAwardFields(String producerName, long firstYear, long secondYear, long interval) {
    return new AwardsDTO()
        .setProducer(producerName)
        .setInterval(interval)
        .setPreviousWin(firstYear)
        .setFollowingWin(secondYear);
  }
}
