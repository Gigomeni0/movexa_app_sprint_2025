package com.movexa.api.service;

import com.movexa.api.model.Usuario;
import com.movexa.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cria um novo usuário
    public Usuario criarUsuario(Usuario usuario) {
        // Você pode adicionar validações ou lógica adicional aqui
        return usuarioRepository.save(usuario);
    }

    // Busca um usuário por ID
    public Usuario obterUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        return usuarioOpt.orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    // Método para autenticação (login)
    public Usuario autenticar(String email, String senhaHash) {
        return usuarioRepository.findByEmailAndSenhaHash(email, senhaHash)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }
}