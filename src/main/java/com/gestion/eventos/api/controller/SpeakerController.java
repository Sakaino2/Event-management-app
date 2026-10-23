package com.gestion.eventos.api.controller;

import com.gestion.eventos.api.domain.Speaker;
import com.gestion.eventos.api.dto.SpeakerRequestDto;
import com.gestion.eventos.api.dto.SpeakerResponseDto;
import com.gestion.eventos.api.mapper.ISpeakerMapper;
import com.gestion.eventos.api.service.ISpeakerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
@RequiredArgsConstructor
public class SpeakerController {

    private final ISpeakerService speakerService;
    private final ISpeakerMapper speakerMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SpeakerResponseDto> createSpeaker(@Valid @RequestBody SpeakerRequestDto speakerRequestDto) {
        Speaker speaker = speakerService.save(speakerRequestDto);
        return new ResponseEntity<>(speakerMapper.toDto(speaker), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<SpeakerResponseDto>> getAllSpeakers() {
        return ResponseEntity.ok(speakerMapper.toResponseDtoList(speakerService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<SpeakerResponseDto> getSpeakerById(@PathVariable Long id) {
        return  ResponseEntity.ok(speakerMapper.toDto(speakerService.findById(id)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SpeakerResponseDto> updateSpeaker(@PathVariable Long id, @RequestBody SpeakerRequestDto speakerRequestDto) {
        Speaker speaker = speakerService.save(speakerRequestDto);
        return ResponseEntity.ok(speakerMapper.toDto(speaker));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteSpeakerById(@PathVariable Long id){
        speakerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
