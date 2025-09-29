package com.mr486.msrisque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
  private int riskLevel;
  private String denomination;
}
