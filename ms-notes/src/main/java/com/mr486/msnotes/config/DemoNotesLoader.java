package com.mr486.msnotes.config;

import com.mr486.msnotes.model.Note;
import com.mr486.msnotes.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DemoNotesLoader {
    private final NoteRepository noteRepository;

    @PostConstruct
    public void loadDemoNotesIfEmpty() {
        if (noteRepository.count() == 0) {
            noteRepository.saveAll(Arrays.asList(
                Note.builder()
                    .id(UUID.randomUUID().toString())
                    .patientId(1L)
                    .content("Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé")
                    .createdDate(LocalDateTime.now())
                    .build(),
                Note.builder()
                    .id(UUID.randomUUID().toString())
                    .patientId(2L)
                    .content("Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement")
                    .createdDate(LocalDateTime.now())
                    .build(),
                Note.builder()
                    .id(UUID.randomUUID().toString())
                    .patientId(2L)
                    .content("Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale")
                    .createdDate(LocalDateTime.now())
                    .build(),
                Note.builder()
                    .id(UUID.randomUUID().toString())
                    .patientId(3L)
                    .content("Le patient déclare qu'il fume depuis peu")
                    .createdDate(LocalDateTime.now())
                    .build(),
                Note.builder()
                    .id(UUID.randomUUID().toString())
                    .patientId(3L)
                    .content("Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé")
                    .createdDate(LocalDateTime.now())
                    .build(),
                Note.builder()
                    .id(UUID.randomUUID().toString())
                    .patientId(4L)
                    .content("Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments")
                    .createdDate(LocalDateTime.now())
                    .build(),
                Note.builder()
                    .id(UUID.randomUUID().toString())
                    .patientId(4L)
                    .content("Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps")
                    .createdDate(LocalDateTime.now())
                    .build(),
                Note.builder()
                    .id(UUID.randomUUID().toString())
                    .patientId(4L)
                    .content("Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé")
                    .createdDate(LocalDateTime.now())
                    .build(),
                Note.builder()
                    .id(UUID.randomUUID().toString())
                    .patientId(4L)
                    .content("Taille, Poids, Cholestérol, Vertige et Réaction")
                    .createdDate(LocalDateTime.now())
                    .build()
            ));
        }
    }
}
