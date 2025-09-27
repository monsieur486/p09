package com.mr486.msclientui.web;

import com.mr486.msclientui.dto.response.Note;
import com.mr486.msclientui.dto.response.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app")
public class NotesController {

  private final RestTemplate restTemplate;
  private final String gatewayBase;

  public NotesController(RestTemplate restTemplate,
                         @Value("${app.gateway.base-url}") String gatewayBase) {
    this.restTemplate = restTemplate;
    this.gatewayBase = gatewayBase;
  }

  @GetMapping("/dashboard/{patientId}/notes")
  public String getNotes(Model model, @PathVariable Long patientId) {
    List<Note> notes;
    ResponseEntity<List<Note>> response = restTemplate.exchange(
            gatewayBase + "/notes/" + patientId,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Note>>() {
            }
    );
    notes = response.getBody();
    model.addAttribute("notes", notes);
    model.addAttribute("patientId", patientId);
    return "notes";
  }

  /*@GetMapping("/dashboard/ajout")
  public String showCreatePatientForm(Model model) {
    model.addAttribute("patient", new Patient());
    return "patient-ajout";
  }

  @PostMapping("/dashboard/ajout")
  public String ajoutPatientPost(Model model, Patient patient) {
    HttpEntity<Patient> requestEntity = new HttpEntity<>(patient);

    ResponseEntity<Patient> scoreResponse = restTemplate.exchange(
            gatewayBase + "/patients",
            HttpMethod.POST,
            requestEntity,
            new ParameterizedTypeReference<Patient>() {
            }
    );
    return "redirect:/app/dashboard";
  }*/
}
