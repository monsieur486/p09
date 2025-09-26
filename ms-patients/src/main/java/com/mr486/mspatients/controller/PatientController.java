package com.mr486.mspatients.controller;

import com.mr486.mspatients.dto.PatientDto;
import com.mr486.mspatients.model.Patient;
import com.mr486.mspatients.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;

  @GetMapping(value = "/patients", produces = "application/json")
  public ResponseEntity<List<Patient>> getAllPatients() {
    List<Patient> patients = patientService.getAllPatients();
    return ResponseEntity.ok(patients);
  }

  @GetMapping(value = "/patients/{id}", produces = "application/json")
  public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
    return ResponseEntity.ok(patientService.getById(id));
  }

  @PostMapping(value = "/patients", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Patient> createPatient(@RequestBody @Valid PatientDto patientDto) {
    Patient createdPatient = patientService.save(patientDto);
    return ResponseEntity.status(201).body(createdPatient);
  }

  @PutMapping(value = "/patients/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody @Valid PatientDto patientDto) {
    Patient updatedPatient = patientService.update(id, patientDto);
    return ResponseEntity.ok(updatedPatient);
  }

  @DeleteMapping(value = "/patients/{id}")
  public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
    patientService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
