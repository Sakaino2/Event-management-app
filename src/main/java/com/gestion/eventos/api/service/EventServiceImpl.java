package com.gestion.eventos.api.service;

import com.gestion.eventos.api.domain.Category;
import com.gestion.eventos.api.domain.Event;
import com.gestion.eventos.api.domain.Speaker;
import com.gestion.eventos.api.dto.EventRequestDto;
import com.gestion.eventos.api.dto.EventResponseDto;
import com.gestion.eventos.api.exception.ResourceNotFoundException;
import com.gestion.eventos.api.mapper.IEventMapper;
import com.gestion.eventos.api.repository.IEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {

    private final IEventRepository eventRepository;
    private final IEventMapper eventMapper;
    private final ICategoryService categoryService;
    private final ISpeakerService speakerService;

    @Override
    @Transactional(readOnly = true)
    public Page<EventResponseDto> findAll(String name, Pageable pageable) {
        Page<Event> eventsPage;

        if (name != null && !name.trim().isEmpty()) {
            eventsPage = eventRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            eventsPage = eventRepository.findAll(pageable);
        }

        List<EventResponseDto> dtos = eventsPage.getContent().stream()
                .map(eventMapper::toResponseDto)
                .toList();

        return new PageImpl<>(dtos, pageable, eventsPage.getTotalElements());
    }

    @Override
    @Transactional
    public Event save(EventRequestDto eventRequestDto) {
        Event event = eventMapper.toEntity(eventRequestDto);

        Category category = categoryService.findById(eventRequestDto.getCategoryId());
        event.setCategory(category);

        if (eventRequestDto.getSpeakersIds() != null && !eventRequestDto.getSpeakersIds().isEmpty()) {
            Set<Speaker> speakers = eventRequestDto.getSpeakersIds().stream()
                    .map(speakerService::findById)
                    .collect(Collectors.toSet());
            speakers.forEach(event::addSpeaker);
        }

        return eventRepository.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Evento no encontrado con ID: " + id)
        );
    }

    @Override
    @Transactional
    public Event update(Long id, EventRequestDto eventRequestDto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Evento no encontrado con ID: " + id)
        );
        eventMapper.updateEventFromDto(eventRequestDto, event);

        if(!event.getCategory().getId().equals(eventRequestDto.getCategoryId())) {
            Category category = categoryService.findById(eventRequestDto.getCategoryId());
            event.setCategory(category);
        }

        Set<Speaker> updatedSpeakers;
        if (eventRequestDto.getSpeakersIds() != null && !eventRequestDto.getSpeakersIds().isEmpty()) {
            updatedSpeakers = eventRequestDto.getSpeakersIds().stream()
                    .map(speakerService::findById)
                    .collect(Collectors.toSet());
        } else {
            updatedSpeakers = new HashSet<>();
        }
        new HashSet<>(event.getSpeakers())
                .forEach(speaker -> {
                    if(!updatedSpeakers.contains(speaker)) {
                        event.removeSpeaker(speaker);
                    }
                });

        updatedSpeakers.forEach(speaker -> {
           if(!event.getSpeakers().contains(speaker)) {
               event.addSpeaker(speaker);
           }
        });

        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Event eventToDelete = findById(id);
        eventRepository.delete(eventToDelete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getAllEventsAndDetails() {
        return eventRepository.findAllWithCategoryAndSpeakers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getAllWithDetails() {
        return eventRepository.findAllWithAllDetails();
    }

}
