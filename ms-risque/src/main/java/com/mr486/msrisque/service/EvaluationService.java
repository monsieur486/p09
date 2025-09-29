package com.mr486.msrisque.service;

import com.mr486.msrisque.dto.Evaluation;
import com.mr486.msrisque.dto.Patient;
import com.mr486.msrisque.dto.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

  private final PatientService patientService;
  private final NotesService notesService;

  public Evaluation evaluateRisk(Long patientId) {
    Patient patient = patientService.getPatientById(patientId);
    List<Note> notes = notesService.getNotesByPatientId(patientId);
    String genre = patient.getGender();
    int age = calculateAge(patient);
    int nbrNotes = notes.size();
    Evaluation evaluation = new Evaluation();
    if (age < 30) {
      if (nbrNotes < 3) {
        evaluation.setRiskLevel(0);
        evaluation.setDenomination("None");
      } else if (nbrNotes <= 4) {
        evaluation.setRiskLevel(1);
        evaluation.setDenomination("Borderline");
      } else if (nbrNotes == 5) {
        evaluation.setRiskLevel(2);
        evaluation.setDenomination("In Danger");
      } else {
        evaluation.setRiskLevel(3);
        evaluation.setDenomination("Early onset");
      }
    } else if (age < 65) {
      if (nbrNotes < 2) {
        evaluation.setRiskLevel(0);
        evaluation.setDenomination("None");
      } else if (nbrNotes <= 5) {
        evaluation.setRiskLevel(1);
        evaluation.setDenomination("Borderline");
      } else if (nbrNotes <= 7) {
        evaluation.setRiskLevel(2);
        evaluation.setDenomination("In Danger");
      } else {
        evaluation.setRiskLevel(3);
        evaluation.setDenomination("Early onset");
      }
    } else {
      if (nbrNotes < 5) {
        evaluation.setRiskLevel(0);
        evaluation.setDenomination("None");
      } else if (nbrNotes <= 7) {
        evaluation.setRiskLevel(1);
        evaluation.setDenomination("Borderline");
      } else if (nbrNotes <= 9) {
        evaluation.setRiskLevel(2);
        evaluation.setDenomination("In Danger");
      } else {
        evaluation.setRiskLevel(3);
        evaluation.setDenomination("Early onset");
      }
    }
    return  evaluation;
  }

  private int calculateAge(Patient patient) {
    LocalDate birthDate = patient.getBirthDate();
    LocalDate today = LocalDate.now();
    return java.time.Period.between(birthDate, today).getYears();
  }

}
