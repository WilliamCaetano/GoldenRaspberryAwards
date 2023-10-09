package com.desafio.goldenraspberryawards.controller.interf;

import com.desafio.goldenraspberryawards.entiity.Studio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Studio Controller")
public interface IStudioController {

  @ApiOperation("Find a studio by the given id")
  ResponseEntity<Studio> findById(Long id);

  @ApiOperation("Find all studios")
  ResponseEntity<List<Studio>> findAll();

  @ApiOperation("Patch a studio by the given id")
  ResponseEntity<Studio> patch(Long id, Studio patched);

  @ApiOperation("Delete a studio by the given id")
  ResponseEntity<Void> delete(Long id);
}
