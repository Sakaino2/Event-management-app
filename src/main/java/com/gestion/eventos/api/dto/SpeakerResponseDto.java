package com.gestion.eventos.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeakerResponseDto {
    private Long id;
    @NotBlank(message = "El nombre del ponente no puede estar vacío")
    @Size(max = 100, message = "El nombre del ponente no puede exceder los 100 caracteres")
    private String name;
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Se debe de ingresar un correo electrónico válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String email;
    @Size(max = 500, message = "La biografía del ponente no puede exceder los 500 caracteres")
    private String bio;
}
