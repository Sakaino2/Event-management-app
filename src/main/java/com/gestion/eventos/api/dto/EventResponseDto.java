package com.gestion.eventos.api.dto;

import com.gestion.eventos.api.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto {
    private Long id;
    private String name;
    private LocalDate date;
    private String location;

    private Long categoryId;
    private String categoryName;

    private Set<SpeakerResponseDto> speakers;
}
