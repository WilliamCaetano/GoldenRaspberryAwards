package com.desafio.goldenraspberryawards.controller.interf;

import com.desafio.goldenraspberryawards.entiity.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Producer Controller")
public interface IProducerController {

  @ApiOperation("Find a producer by the given id")
  ResponseEntity<Producer> findById(Long id);

  @ApiOperation("Find all producers")
  ResponseEntity<List<Producer>> findAll();

  @ApiOperation("Patch a producer by the given id")
  ResponseEntity<Producer> patch(Long id, Producer patched);

  @ApiOperation("Delete a producer by the given id")
  ResponseEntity<Void> delete(Long id);
}
