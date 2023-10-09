package com.desafio.goldenraspberryawards.service;

import com.desafio.goldenraspberryawards.entiity.Studio;
import com.desafio.goldenraspberryawards.repository.StudioRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class StudioService {

  private final StudioRepository repository;

  public StudioService(StudioRepository repository) {
    this.repository = repository;
  }

  public List<Studio> findAll() {
    return repository.findAll();
  }

  public Studio findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public List<Studio> getOrCreateStudios(String studiosStr) {
    return Arrays.stream(studiosStr.split(",|\\ and "))
        .map(this::getOrCreateStudio)
        .collect(Collectors.toList());
  }

  public Studio getOrCreateStudio(String studioName) {
    val nameWithoutWhiteSpaces = studioName.trim();
    Studio studio = repository.findByName(nameWithoutWhiteSpaces);
    if (isNull(studio) && !nameWithoutWhiteSpaces.isBlank()) {
      studio = new Studio();
      studio.setName(studioName.trim());
      repository.save(studio);
    }
    return studio;
  }

  public Studio patch(Long id, Studio patched) {
    val existingStudio = repository.findById(id);
    if (existingStudio.isPresent()) {
      val studioToUpdate = existingStudio.get();
      if (nonNull(patched.getName())) {
        studioToUpdate.setName(patched.getName());
      }
      return repository.save(studioToUpdate);
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
