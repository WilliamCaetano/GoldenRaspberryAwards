package com.desafio.goldenraspberryawards.service;

import com.desafio.goldenraspberryawards.entiity.Producer;
import com.desafio.goldenraspberryawards.repository.ProducerRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ProducerService {

  private final ProducerRepository repository;

  public ProducerService(ProducerRepository repository) {
    this.repository = repository;
  }

  public List<Producer> findAll() {
    return repository.findAll();
  }

  public Producer findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public List<Producer> getOrCreateProducers(String producersStr) {
    return Arrays.stream(producersStr.split(",|\\ and "))
        .map(this::getOrCreateProducer)
        .collect(Collectors.toList());
  }

  public Producer getOrCreateProducer(String producerName) {
    val nameWithoutWhiteSpaces = producerName.trim();
    Producer producer = repository.findByName(nameWithoutWhiteSpaces);
    if (isNull(producer) && !nameWithoutWhiteSpaces.isBlank()) {
      producer = new Producer();
      producer.setName(nameWithoutWhiteSpaces);
      repository.save(producer);
    }
    return producer;
  }

  public Producer patch(Long id, Producer patched) {
    val existingProducer = repository.findById(id);
    if (existingProducer.isPresent()) {
      val producerToUpdate = existingProducer.get();
      if (nonNull(patched.getName())) {
        producerToUpdate.setName(patched.getName());
      }
      return repository.save(producerToUpdate);
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
