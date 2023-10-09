package com.desafio.goldenraspberryawards.controller;

import com.desafio.goldenraspberryawards.controller.interf.IStudioController;
import com.desafio.goldenraspberryawards.entiity.Studio;
import com.desafio.goldenraspberryawards.service.StudioService;
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
@RequestMapping("/studios")
public class StudioController implements IStudioController {

  private final StudioService studioService;

  public StudioController(StudioService studioService) {
    this.studioService = studioService;
  }

  @Override
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Studio> findById(@PathVariable Long id) {
    val studio = studioService.findById(id);
    return nonNull(studio) ? ResponseEntity.ok(studio) : ResponseEntity.notFound().build();
  }

  @Override
  @GetMapping
  @ResponseBody
  public ResponseEntity<List<Studio>> findAll() {
    return ResponseEntity.ok(studioService.findAll());
  }

  @Override
  @PatchMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Studio> patch(@PathVariable Long id, @RequestBody Studio patched) {
    val updatedStudio = studioService.patch(id, patched);
    return nonNull(updatedStudio) ? ResponseEntity.ok(updatedStudio) : ResponseEntity.notFound().build();
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return studioService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
