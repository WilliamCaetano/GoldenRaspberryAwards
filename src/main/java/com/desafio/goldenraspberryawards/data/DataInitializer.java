package com.desafio.goldenraspberryawards.data;

import com.desafio.goldenraspberryawards.service.CsvReaderService;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataInitializer implements CommandLineRunner {

  private final CsvReaderService csvReaderService;

  public DataInitializer(CsvReaderService csvReaderService) {
    this.csvReaderService = csvReaderService;
  }

  @Override
  public void run(String... args) throws Exception {
    try {
      val inputStream = getClass().getResourceAsStream("/movielist.csv");
      csvReaderService.processCsvFile(inputStream);
      System.out.println("A APLICAÇÃO FOI INICIADA COM SUCESSO");
    } catch (IOException e) {
      System.out.println("OCORREU UMA EXCEÇÃO");
      e.printStackTrace();
    }
  }
}
