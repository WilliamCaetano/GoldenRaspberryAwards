package com.desafio.goldenraspberryawards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AwardsSummaryDTO implements Serializable {

  private static final long serialVersionUID = 3513811052089589699L;

  private List<AwardsDTO> min;

  private List<AwardsDTO> max;
}
