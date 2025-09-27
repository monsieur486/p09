package com.mr486.msnotes.controller;

import com.mr486.msnotes.dto.NoteDto;
import com.mr486.msnotes.model.Note;
import com.mr486.msnotes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

  private final NoteService noteService;

  @GetMapping(value = "/{patientId}", produces = "application/json")
  public List<Note> getNotes(@PathVariable Long patientId) {
    return noteService.findByPatientId(patientId);
  }

  @PostMapping(value = "/{patientId}", consumes = "application/json")
  public void addNote(@PathVariable Long patientId, @RequestBody NoteDto noteDto) {
    noteService.save(patientId, noteDto);
  }

}
