package com.gestion.eventos.api.mapper;

import com.gestion.eventos.api.domain.Category;
import com.gestion.eventos.api.domain.Event;
import com.gestion.eventos.api.domain.Speaker;
import com.gestion.eventos.api.dto.EventRequestDto;
import com.gestion.eventos.api.dto.EventResponseDto;
import com.gestion.eventos.api.dto.EventSummaryDto;
import com.gestion.eventos.api.dto.SpeakerResponseDto;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-03T19:26:26-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class IEventMapperImpl implements IEventMapper {

    @Override
    public Event toEntity(EventRequestDto eventRequestDto) {
        if ( eventRequestDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( eventRequestDto.getName() );
        event.setDate( eventRequestDto.getDate() );
        event.setLocation( eventRequestDto.getLocation() );

        return event;
    }

    @Override
    public EventResponseDto toResponseDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventResponseDto eventResponseDto = new EventResponseDto();

        eventResponseDto.setCategoryId( eventCategoryId( event ) );
        eventResponseDto.setCategoryName( eventCategoryName( event ) );
        eventResponseDto.setSpeakers( speakerSetToSpeakerResponseDtoSet( event.getSpeakers() ) );
        eventResponseDto.setId( event.getId() );
        eventResponseDto.setName( event.getName() );
        eventResponseDto.setDate( event.getDate() );
        eventResponseDto.setLocation( event.getLocation() );

        return eventResponseDto;
    }

    @Override
    public List<EventResponseDto> toEventResponseDtoList(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventResponseDto> list = new ArrayList<EventResponseDto>( events.size() );
        for ( Event event : events ) {
            list.add( toResponseDto( event ) );
        }

        return list;
    }

    @Override
    public void updateEventFromDto(EventRequestDto dto, Event event) {
        if ( dto == null ) {
            return;
        }

        event.setName( dto.getName() );
        event.setDate( dto.getDate() );
        event.setLocation( dto.getLocation() );
    }

    @Override
    public EventSummaryDto toSummaryDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventSummaryDto eventSummaryDto = new EventSummaryDto();

        eventSummaryDto.setId( event.getId() );
        eventSummaryDto.setName( event.getName() );
        eventSummaryDto.setDate( event.getDate() );
        eventSummaryDto.setLocation( event.getLocation() );

        return eventSummaryDto;
    }

    @Override
    public List<EventSummaryDto> toSummaryDtoList(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventSummaryDto> list = new ArrayList<EventSummaryDto>( events.size() );
        for ( Event event : events ) {
            list.add( toSummaryDto( event ) );
        }

        return list;
    }

    private Long eventCategoryId(Event event) {
        Category category = event.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    private String eventCategoryName(Event event) {
        Category category = event.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }

    protected SpeakerResponseDto speakerToSpeakerResponseDto(Speaker speaker) {
        if ( speaker == null ) {
            return null;
        }

        SpeakerResponseDto speakerResponseDto = new SpeakerResponseDto();

        speakerResponseDto.setId( speaker.getId() );
        speakerResponseDto.setName( speaker.getName() );
        speakerResponseDto.setEmail( speaker.getEmail() );
        speakerResponseDto.setBio( speaker.getBio() );

        return speakerResponseDto;
    }

    protected Set<SpeakerResponseDto> speakerSetToSpeakerResponseDtoSet(Set<Speaker> set) {
        if ( set == null ) {
            return null;
        }

        Set<SpeakerResponseDto> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Speaker speaker : set ) {
            set1.add( speakerToSpeakerResponseDto( speaker ) );
        }

        return set1;
    }
}
