package com.desafio.goldenraspberryawards.jpa;

import com.desafio.goldenraspberryawards.entiity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioJpa extends JpaRepository<Studio, Long> {

  Studio findByName(String name);

}
