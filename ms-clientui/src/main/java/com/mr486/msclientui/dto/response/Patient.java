package com.mr486.msclientui.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
  private Long id;
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private String gender;
  private String postalAddress;
  private String phoneNumber;
}
