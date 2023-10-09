package com.desafio.goldenraspberryawards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AwardsDTO implements Serializable {

  private static final long serialVersionUID = 8670620443291178353L;

  private String producer;

  private Long interval;

  private Long previousWin;

  private Long followingWin;
}
