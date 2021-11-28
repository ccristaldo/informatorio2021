package com.informatorio.informatorio2021.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.informatorio.informatorio2021.enums.TipoUsuario;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Setter
@Getter
//@Table(name = "usuarios")
@SQLDelete(sql="UPDATE usuario_entity SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true, length = 32)
    @Email
    private String email;
    private String password;
    //@JsonFormat(pattern="yyyy-MM-dd")
    @CreationTimestamp
    private LocalDate fechaCreacion;
    private String ciudad;
    private String provincia;
    private String pais;
    @Enumerated(value = EnumType.STRING)
    private TipoUsuario tipo;
    private boolean deleted = Boolean.FALSE;



}

















