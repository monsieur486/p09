package com.mr486.msclientui.dto.response;


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
