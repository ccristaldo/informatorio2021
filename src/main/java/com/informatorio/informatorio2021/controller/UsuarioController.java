//entra y sale solamente DTO
package com.informatorio.informatorio2021.controller;

import com.informatorio.informatorio2021.dto.UsuarioDTO;
import com.informatorio.informatorio2021.entity.UsuarioEntity;
import com.informatorio.informatorio2021.repository.UsuarioRepository;
import com.informatorio.informatorio2021.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping()
    public ResponseEntity <UsuarioDTO>  save(@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO usuarioGuardado = usuarioService.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@Valid @PathVariable Long id){
        UsuarioDTO usuarioBuscado = usuarioService.getusuariobyid(id);
        return ResponseEntity.ok(usuarioBuscado);
    }

    //SOFT DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
       UsuarioDTO usuarioUpadatedDTO = usuarioService.updateusuariobyid(id, usuarioDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioUpadatedDTO);


       /*
       public ResponseEntity<Void> updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioEntity entity){
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id).get();
        usuarioExistente.setNombre(entity.getNombre());
        usuarioRepository.save(usuarioExistente);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        */


    }



}
