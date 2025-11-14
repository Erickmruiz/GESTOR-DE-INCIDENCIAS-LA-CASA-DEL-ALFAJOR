package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Lista de usuarios (vista)
    @GetMapping("/usuario")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuario", usuarioService.listarUsuarios());
        return "usuario";
    }

    // Formulario para nuevo usuario
    @GetMapping("/usuario/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario_form";
    }

    // Guardar usuario (nuevo o editado)
    @PostMapping("/usuario/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        if (usuario.getPersonas_id() == null) {
            usuario.setPersonas_id(1L); 
        }
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuario";
    }

    // Formulario para editar usuario
    @GetMapping("/usuario/editar/{id}")
    public String mostrarFormularioEditar(@NonNull @PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuario_form";
    }

    // Guardar usuario editado (CORREGIDO)
    @PostMapping("/usuario/editar/{id}")
    public String editarUsuario(@NonNull @PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuario.setId(id);

        // Si la clave está vacía, conserva la anterior
        if (usuario.getClave() == null || usuario.getClave().isEmpty()) {
            Usuario usuarioActual = usuarioService.obtenerPorId(id);
            usuario.setClave(usuarioActual.getClave());
        } else {
            usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        }

        if (usuario.getPersonas_id() == null) {
            usuario.setPersonas_id(1L); 
        }
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuario";
    }

    // Eliminar usuario
    @GetMapping("/usuario/eliminar/{id}")
    public String eliminarUsuario(@NonNull @PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuario";
    }

    // Ruta temporal para crear un usuario general de prueba
    @GetMapping("/crear-usuario-general")
    public String crearUsuarioGeneral() {
        if (usuarioService.obtenerPorCorreo("admin@admin.com") == null) {
            Usuario usuario = new Usuario();
            usuario.setNick("admin");
            usuario.setCorreo("admin@admin.com");
            usuario.setClave(passwordEncoder.encode("admin123"));
            usuario.setRol("ADMIN");
            usuario.setActivo(1);
            usuario.setPersonas_id(1L); 
            usuarioService.guardarUsuario(usuario);
        }
        return "redirect:/login";
    }
}