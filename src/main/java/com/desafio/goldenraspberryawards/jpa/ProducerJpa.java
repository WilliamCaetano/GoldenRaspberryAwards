package com.desafio.goldenraspberryawards.jpa;

import com.desafio.goldenraspberryawards.entiity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerJpa extends JpaRepository<Producer, Long> {

  Producer findByName(String name);

}
