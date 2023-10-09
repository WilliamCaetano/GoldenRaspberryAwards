package com.desafio.goldenraspberryawards.jpa;

import com.desafio.goldenraspberryawards.entiity.Movie;
import com.desafio.goldenraspberryawards.entiity.Producer;
import com.desafio.goldenraspberryawards.entiity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieJpa extends JpaRepository<Movie, Long> {

  @Query("SELECT m "
       + "FROM Movie m "
       + "WHERE m.title = :title "
       + "AND m.year = :year ")
  Movie findDuplicatedMovie(
      @Param("title") String title,
      @Param("year") Long year
  );
}
