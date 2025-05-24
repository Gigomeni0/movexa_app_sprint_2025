package com.movexa.api.controller;

import com.movexa.api.model.Usuario;
import com.movexa.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.movexa.api.dto.UsuarioDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.ok().body(novoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        return ResponseEntity.ok().body(usuario);
    }
}