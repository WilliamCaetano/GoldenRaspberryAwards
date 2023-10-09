package com.desafio.goldenraspberryawards.controller;

import com.desafio.goldenraspberryawards.controller.interf.IProducerController;
import com.desafio.goldenraspberryawards.entiity.Producer;
import com.desafio.goldenraspberryawards.service.ProducerService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/producers")
public class ProducerController implements IProducerController {

  private final ProducerService producerService;

  public ProducerController(ProducerService producerService) {
    this.producerService = producerService;
  }

  @Override
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Producer> findById(@PathVariable Long id) {
    val producer = producerService.findById(id);
    return nonNull(producer) ? ResponseEntity.ok(producer) : ResponseEntity.notFound().build();
  }

  @Override
  @GetMapping
  @ResponseBody
  public ResponseEntity<List<Producer>> findAll() {
    return ResponseEntity.ok(producerService.findAll());
  }

  @Override
  @PatchMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Producer> patch(@PathVariable Long id, @RequestBody Producer patched) {
    val updatedProducer = producerService.patch(id, patched);
    return nonNull(updatedProducer) ? ResponseEntity.ok(updatedProducer) : ResponseEntity.notFound().build();
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return producerService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
