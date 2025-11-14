package com.example.demo.service;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(@NonNull Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerPorId(@NonNull Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public void eliminarUsuario(@NonNull Long id) {
        usuarioRepository.deleteById(id);
    }
}