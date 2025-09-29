package com.mr486.msrisque.service;

import com.mr486.msrisque.dto.Note;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotesService {

  private final RestTemplate restTemplate;
  private final String notesServiceUrl;

  public NotesService(RestTemplate restTemplate,
                        @Value("${services.notes-service.url}") String patientServiceUrl) {
    this.restTemplate = restTemplate;
    this.notesServiceUrl = patientServiceUrl;
  }

  public List<Note> getNotesByPatientId(Long patientId) {
    List<Note> notes;
    ResponseEntity<List<Note>> response = restTemplate.exchange(
            notesServiceUrl + "/notes/" + patientId,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Note>>() {
            }
    );
    notes = response.getBody();
    return notes;
  }

}
