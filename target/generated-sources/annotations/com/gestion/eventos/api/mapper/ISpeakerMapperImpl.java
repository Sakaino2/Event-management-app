package com.gestion.eventos.api.mapper;

import com.gestion.eventos.api.domain.Speaker;
import com.gestion.eventos.api.dto.SpeakerRequestDto;
import com.gestion.eventos.api.dto.SpeakerResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-03T19:26:26-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class ISpeakerMapperImpl implements ISpeakerMapper {

    @Override
    public SpeakerResponseDto toDto(Speaker speaker) {
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

    @Override
    public Speaker toEntity(SpeakerRequestDto speakerRequestDto) {
        if ( speakerRequestDto == null ) {
            return null;
        }

        Speaker speaker = new Speaker();

        speaker.setName( speakerRequestDto.getName() );
        speaker.setEmail( speakerRequestDto.getEmail() );
        speaker.setBio( speakerRequestDto.getBio() );

        return speaker;
    }

    @Override
    public List<SpeakerResponseDto> toResponseDtoList(List<Speaker> speakers) {
        if ( speakers == null ) {
            return null;
        }

        List<SpeakerResponseDto> list = new ArrayList<SpeakerResponseDto>( speakers.size() );
        for ( Speaker speaker : speakers ) {
            list.add( toDto( speaker ) );
        }

        return list;
    }

    @Override
    public void updateSpeakerFromDto(SpeakerRequestDto speakerRequestDto, Speaker speaker) {
        if ( speakerRequestDto == null ) {
            return;
        }

        speaker.setName( speakerRequestDto.getName() );
        speaker.setEmail( speakerRequestDto.getEmail() );
        speaker.setBio( speakerRequestDto.getBio() );
    }
}
