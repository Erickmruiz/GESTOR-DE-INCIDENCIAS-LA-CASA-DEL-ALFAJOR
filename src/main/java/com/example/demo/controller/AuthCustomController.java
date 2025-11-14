package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.security.JwtUtilCustom;
import com.example.demo.service.UsuarioService;

@RestController
public class AuthCustomController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtUtilCustom jwtUtilCustom;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login-jwt")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String correo = loginData.get("correo");
        String clave = loginData.get("clave");
        Usuario usuario = usuarioService.obtenerPorCorreo(correo);
        if (usuario != null && passwordEncoder.matches(clave, usuario.getClave())) {
            String token = jwtUtilCustom.generateToken(usuario.getNick(), usuario.getRol());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("rol", usuario.getRol());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }
}