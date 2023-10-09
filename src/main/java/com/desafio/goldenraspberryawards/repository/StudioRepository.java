package com.desafio.goldenraspberryawards.repository;

import com.desafio.goldenraspberryawards.entiity.Studio;
import com.desafio.goldenraspberryawards.jpa.StudioJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudioRepository {

  private final StudioJpa jpa;

  public StudioRepository(StudioJpa jpa) {
    this.jpa = jpa;
  }

  public List<Studio> findAll() {
    return jpa.findAll();
  }

  public Optional<Studio> findById(Long id) {
    return jpa.findById(id);
  }

  public Studio save(Studio studio) {
    return jpa.save(studio);
  }

  public Studio findByName(String name) {
    return jpa.findByName(name);
  }

  public Boolean existsById(Long id) {
    return jpa.existsById(id);
  }

  public void deleteById(Long id) {
    jpa.deleteById(id);
  }
}
