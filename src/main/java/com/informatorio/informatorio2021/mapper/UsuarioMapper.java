package com.informatorio.informatorio2021.mapper;

import com.informatorio.informatorio2021.dto.UsuarioDTO;
import com.informatorio.informatorio2021.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioEntity usuarioDTO2Entity(UsuarioDTO dto){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setCiudad(dto.getCiudad());
        entity.setProvincia(dto.getProvincia());
        entity.setPais(dto.getPais());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setTipo(dto.getTipo());
        entity.setFechaCreacion(dto.getFechaCreacion());
        return entity;
    }



    public UsuarioDTO usuarioEntity2DTO(UsuarioEntity entity){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setCiudad(entity.getCiudad());
        dto.setProvincia(entity.getProvincia());
        dto.setPais(entity.getPais());
        dto.setEmail(entity.getEmail());
        dto.setPassword(passwordEncoder.encode(entity.getPassword()));
        dto.setTipo(entity.getTipo());
        dto.setFechaCreacion(entity.getFechaCreacion());
        return dto;

    }

    public List<UsuarioDTO> listaEntity2DTO(List<UsuarioEntity> entities) {
        List<UsuarioDTO> dtos = new ArrayList<>();
        for(UsuarioEntity u : entities){
            dtos.add(usuarioEntity2DTO(u));
        }
        return dtos;
    }


    public void usuarioEntityRefreshValues(UsuarioEntity usuario, UsuarioDTO usuario2UpdateDTO) {
        usuario.setNombre(usuario2UpdateDTO.getNombre());
        usuario.setApellido(usuario2UpdateDTO.getApellido());
        //TODO:encriptar la clave
        usuario.setPassword(usuario2UpdateDTO.getPassword());
        usuario.setPais(usuario2UpdateDTO.getPais());
        usuario.setProvincia(usuario2UpdateDTO.getProvincia());
        usuario.setCiudad(usuario2UpdateDTO.getCiudad());
        usuario.setTipo(usuario2UpdateDTO.getTipo());
    }
}
