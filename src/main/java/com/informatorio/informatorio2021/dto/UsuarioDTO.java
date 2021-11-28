package com.informatorio.informatorio2021.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.informatorio.informatorio2021.enums.TipoUsuario;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Setter
@Getter
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private LocalDate fechaCreacion;
    private String ciudad;
    private String provincia;
    private String pais;
    private TipoUsuario tipo;
}
