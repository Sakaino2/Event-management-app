package com.gestion.eventos.api.service;

import com.gestion.eventos.api.domain.Speaker;
import com.gestion.eventos.api.dto.SpeakerRequestDto;
import com.gestion.eventos.api.exception.ResourceNotFoundException;
import com.gestion.eventos.api.mapper.ISpeakerMapper;
import com.gestion.eventos.api.repository.ISpeakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeakerServiceImpl implements ISpeakerService{

    private final ISpeakerRepository speakerRepository;
    private final ISpeakerMapper speakerMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    @Transactional
    public Speaker save(SpeakerRequestDto speakerRequestDto) {
        Speaker speaker = speakerMapper.toEntity(speakerRequestDto);
        return speakerRepository.save(speaker);
    }

    @Override
    @Transactional(readOnly = true)
    public Speaker findById(Long id) {
        return speakerRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Ponente no encontrado con ID " + id)
                );
    }

    @Override
    @Transactional
    public Speaker update(Long id, SpeakerRequestDto speakerRequestDto) {
        Speaker speaker = speakerRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Ponente no encontrado con ID " + id)
                );
        speakerMapper.updateSpeakerFromDto(speakerRequestDto, speaker);
        return speakerRepository.save(speaker);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (speakerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ponente no encontrado con ID " + id);
        }

        speakerRepository.deleteById(id);
    }
}
