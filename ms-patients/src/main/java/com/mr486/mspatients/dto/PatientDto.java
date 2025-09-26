package com.mr486.mspatients.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto {

  @NotNull(message = "First name cannot be null")
  private String firstName;
  @NotNull(message = "Last name cannot be null")
  private String lastName;
  @NotNull(message = "Birth date cannot be null")
  private LocalDate birthDate;
  @NotNull(message = "Gender cannot be null")
  private String gender;
  private String postalAddress;
  private String phoneNumber;
}
