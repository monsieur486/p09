package com.mr486.msrisque.service;

import com.mr486.msrisque.dto.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientService {

  private final RestTemplate restTemplate;
  private final String patientServiceUrl;

  public PatientService(RestTemplate restTemplate,
                        @Value("${services.patients-service.url}") String patientServiceUrl) {
    this.restTemplate = restTemplate;
    this.patientServiceUrl = patientServiceUrl;
  }

  public Patient getPatientById(Long patientId) {
    ResponseEntity<Patient> response = restTemplate.exchange(
            patientServiceUrl + "/patients/" + patientId,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Patient>() {
            }
    );
    Patient patient = response.getBody();
    if (patient == null) {
      throw new RuntimeException("Patient not found with ID: " + patientId);
    }
    return patient;
  }
}
