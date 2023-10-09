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
public class MovieDTO implements Serializable {

  private static final long serialVersionUID = 3616967099882367690L;

  private String title;

  private Long year;

  private Boolean isWinner;

  private String producers;

  private String studios;
}
