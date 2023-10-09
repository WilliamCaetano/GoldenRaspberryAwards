package com.desafio.goldenraspberryawards.repository;

import com.desafio.goldenraspberryawards.entiity.Producer;
import com.desafio.goldenraspberryawards.jpa.ProducerJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProducerRepository {

  private final ProducerJpa jpa;

  public ProducerRepository(ProducerJpa jpa) {
    this.jpa = jpa;
  }

  public List<Producer> findAll() {
    return jpa.findAll();
  }

  public Optional<Producer> findById(Long id) {
    return jpa.findById(id);
  }

  public Producer save(Producer producer) {
    return jpa.save(producer);
  }

  public Producer findByName(String name) {
    return jpa.findByName(name);
  }

  public Boolean existsById(Long id) {
    return jpa.existsById(id);
  }

  public void deleteById(Long id) {
    jpa.deleteById(id);
  }
}
