package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioService.obtenerPorCorreo("admin@admin.com") == null) {
            Usuario usuario = new Usuario();
            usuario.setNick("admin");
            usuario.setCorreo("admin@admin.com");
            usuario.setClave(passwordEncoder.encode("admin123"));
            usuario.setRol("ADMIN");
            usuario.setActivo(1);
            usuario.setPersonas_id(1L); // Asigna un ID válido existente en tu tabla usuario/persona
            usuarioService.guardarUsuario(usuario);
            System.out.println("Usuario admin@admin.com creado con contraseña admin123");
        }
    }
}