package com.informatorio.informatorio2021.service.implement;

import com.informatorio.informatorio2021.dto.UsuarioDTO;
import com.informatorio.informatorio2021.entity.UsuarioEntity;
import com.informatorio.informatorio2021.exception.ParamNotFound;
import com.informatorio.informatorio2021.mapper.UsuarioMapper;
import com.informatorio.informatorio2021.repository.UsuarioRepository;
import com.informatorio.informatorio2021.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO save( UsuarioDTO usuarioDTO) {
        UsuarioEntity entity = usuarioMapper.usuarioDTO2Entity(usuarioDTO);
        UsuarioEntity usuarioGuardado = usuarioRepository.save(entity);
        UsuarioDTO result = usuarioMapper.usuarioEntity2DTO(usuarioGuardado);
        System.out.println("USUARIO GUARDADO");
        return result;
    }

    @Override
    public UsuarioDTO updateusuariobyid(Long id, UsuarioDTO usuario2UpdateDTO) {
       Optional<UsuarioEntity> entity = usuarioRepository.findById(id);
       if (!entity.isPresent()){
           throw new ParamNotFound("Id Inexistente");
       }
       usuarioMapper.usuarioEntityRefreshValues(entity.get(), usuario2UpdateDTO);
       UsuarioEntity entityUpdated = usuarioRepository.save(entity.get());
       UsuarioDTO result = usuarioMapper.usuarioEntity2DTO(entityUpdated);
       return  result;
    }


    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        List<UsuarioEntity> entities = usuarioRepository.findAll();
        List<UsuarioDTO> result = usuarioMapper.listaEntity2DTO(entities);

        return result;
    }

    @Override
    public UsuarioDTO getusuariobyid(Long id) {
        Optional<UsuarioEntity> entity = usuarioRepository.findById(id);
        if (!entity.isPresent()){
           throw new ParamNotFound("id inexistente");
        }
        UsuarioDTO usuarioDTO = usuarioMapper.usuarioEntity2DTO(entity.get());
        return usuarioDTO;
    }




    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }



}

