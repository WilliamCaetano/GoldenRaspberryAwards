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
import java.util.List;
import java.util.Map;

@Service
public class AwardsService {

  private final MovieService movieService;

  public AwardsService(MovieService movieService) {
    this.movieService = movieService;
  }

  public AwardsSummaryDTO findProducerAwardsSummary() {
    val movies = movieService.findAll();
    Map<String, List<Long>> producerAwards = new HashMap<>();
    movies.stream().filter(Movie::getIsWinner).forEach(movie -> movie.getProducers().forEach(producer -> {
      producerAwards.computeIfAbsent(producer.getName(), k -> new ArrayList<>()).add(movie.getYear());
    }));
    return new AwardsSummaryDTO()
        .setMin(findIntervalBetweenTwoAwards(producerAwards, true))
        .setMax(findIntervalBetweenTwoAwards(producerAwards, false));
  }

  private List<AwardsDTO> findIntervalBetweenTwoAwards(Map<String, List<Long>> producerAwards,
                                                         Boolean isFasterInterval) {
    List<AwardsDTO> awards = new ArrayList<>();
    for (Map.Entry<String, List<Long>> entry : producerAwards.entrySet()) {
      List<Long> awardsYears = entry.getValue();
      Collections.sort(awardsYears);
      if (awardsYears.size() >= 2) {
        for (int i = 0; i < awardsYears.size() - 1; i++) {
          long firstYear = awardsYears.get(i);
          long secondYear = awardsYears.get(i + 1);
          long interval = secondYear - firstYear;
          AwardsDTO award = setAwardFields(entry.getKey(), firstYear, secondYear, interval);
          awards.add(award);
        }
      }
    }
    if (isFasterInterval) {
      awards.sort(Comparator.comparingLong(AwardsDTO::getInterval));
    } else {
      awards.sort(Comparator.comparingLong(AwardsDTO::getInterval).reversed());
    }
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
