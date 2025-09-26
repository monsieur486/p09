package com.mr486.mspatients.service;

import com.mr486.mspatients.dto.PatientDto;
import com.mr486.mspatients.exeption.DuplicateException;
import com.mr486.mspatients.exeption.NotFoundException;
import com.mr486.mspatients.model.Patient;
import com.mr486.mspatients.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;

  public Patient getById(Long id) {
    return patientRepository.findById(id)
            .orElseThrow(() -> patientNotFoundException(id));
  }

  private void copyDtoToPatient(PatientDto dto, Patient patient) {
    patient.setFirstName(dto.getFirstName());
    patient.setLastName(dto.getLastName());
    patient.setBirthDate(dto.getBirthDate());
    patient.setGender(dto.getGender());
    patient.setPostalAddress(dto.getPostalAddress());
    patient.setPhoneNumber(dto.getPhoneNumber());
  }

  public Patient save(PatientDto patientDto) {
    if (isDuplicatePatient(patientDto)) {
      throw patientDuplicateException();
    }
    Patient patient = new Patient();
    copyDtoToPatient(patientDto, patient);
    return patientRepository.save(patient);
  }

  public Patient update(Long id, PatientDto patientDto) {
    Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> patientNotFoundException(id));
    copyDtoToPatient(patientDto, patient);
    return patientRepository.save(patient);
  }

  public void delete(Long id) {
    if (!patientRepository.existsById(id)) {
      throw patientNotFoundException(id);
    }
    patientRepository.deleteById(id);
  }

  public List<Patient> getAllPatients() {
    return patientRepository.findAll();
  }

  private NotFoundException patientNotFoundException(Long id) {
    return new NotFoundException("Patient non trouvé avec id: " + id);
  }

  private DuplicateException patientDuplicateException() {
    return new DuplicateException("Patient déjà existant avec le même nom, prénom, genre et date de naissance.");
  }

  private boolean isDuplicatePatient(PatientDto patientDto) {
    return patientRepository.findAll().stream().anyMatch(existingPatient ->
            existingPatient.getFirstName().equalsIgnoreCase(patientDto.getFirstName()) &&
                    existingPatient.getLastName().equalsIgnoreCase(patientDto.getLastName()) &&
                    existingPatient.getBirthDate().equals(patientDto.getBirthDate()) &&
                    existingPatient.getGender().equalsIgnoreCase(patientDto.getGender())
    );
  }

}
