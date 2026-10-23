package com.gestion.eventos.api.mapper;

import com.gestion.eventos.api.domain.Event;
import com.gestion.eventos.api.domain.Role;
import com.gestion.eventos.api.domain.User;
import com.gestion.eventos.api.dto.EventSummaryDto;
import com.gestion.eventos.api.dto.RoleDto;
import com.gestion.eventos.api.dto.UserResponseDto;
import com.gestion.eventos.api.security.dto.RegisterDto;
import java.util.ArrayList;
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
public class UserMapperImpl extends UserMapper {

    @Override
    public User registerDtoToUser(RegisterDto registerDto) {
        if ( registerDto == null ) {
            return null;
        }

        User user = new User();

        user.setRoles( mapRoleStringsToRoles( registerDto.getRoles() ) );
        user.setUsername( registerDto.getUsername() );
        user.setName( registerDto.getName() );
        user.setEmail( registerDto.getEmail() );

        return user;
    }

    @Override
    public UserResponseDto toUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setName( user.getName() );
        userResponseDto.setUsername( user.getUsername() );
        userResponseDto.setEmail( user.getEmail() );
        userResponseDto.setRoles( roleSetToRoleDtoList( user.getRoles() ) );
        userResponseDto.setAttendedEvents( eventSetToEventSummaryDtoList( user.getAttendedEvents() ) );

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> toUserResponseDtoList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( users.size() );
        for ( User user : users ) {
            list.add( toUserResponseDto( user ) );
        }

        return list;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );

        return roleDto;
    }

    protected List<RoleDto> roleSetToRoleDtoList(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( set.size() );
        for ( Role role : set ) {
            list.add( roleToRoleDto( role ) );
        }

        return list;
    }

    protected EventSummaryDto eventToEventSummaryDto(Event event) {
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

    protected List<EventSummaryDto> eventSetToEventSummaryDtoList(Set<Event> set) {
        if ( set == null ) {
            return null;
        }

        List<EventSummaryDto> list = new ArrayList<EventSummaryDto>( set.size() );
        for ( Event event : set ) {
            list.add( eventToEventSummaryDto( event ) );
        }

        return list;
    }
}
