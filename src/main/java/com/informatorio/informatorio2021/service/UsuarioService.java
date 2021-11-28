package com.informatorio.informatorio2021.service;

import com.informatorio.informatorio2021.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO save(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> getAllUsuarios();

    void delete(Long id);

    UsuarioDTO getusuariobyid(Long id);


    UsuarioDTO updateusuariobyid(Long id, UsuarioDTO usuario2UpdateDTO);
}
