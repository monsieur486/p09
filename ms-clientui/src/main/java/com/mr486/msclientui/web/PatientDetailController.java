package com.mr486.msclientui.web;

import com.mr486.msclientui.dto.response.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app")
public class PatientDetailController {

  private final RestTemplate restTemplate;
  private final String gatewayBase;

  public PatientDetailController(RestTemplate restTemplate,
                                 @Value("${app.gateway.base-url}") String gatewayBase) {
    this.restTemplate = restTemplate;
    this.gatewayBase = gatewayBase;
  }

  @GetMapping("/dashboard/{id}")
  public String dashboard(@PathVariable Long id,  Model model) {
    ResponseEntity<Patient> response = restTemplate.exchange(
            gatewayBase + "/patients/" + id,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Patient>() {
            }
    );
    Patient patient = response.getBody();
    model.addAttribute("patient", patient);
    return "patient-detail";
  }
}
