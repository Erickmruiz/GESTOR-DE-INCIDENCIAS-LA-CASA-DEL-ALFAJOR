package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Equipo;
import com.example.demo.service.EquipoService;

@Controller
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @GetMapping("/equipos")
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", equipoService.listarEquipos());
        return "equipos";
    }

    @GetMapping("/equipos/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "equipo_form";
    }

    @PostMapping("/equipos/guardar")
    public String guardarEquipo(@NonNull @ModelAttribute Equipo equipo) {
        equipoService.guardarEquipo(equipo);
        return "redirect:/equipos";
    }

    @GetMapping("/equipos/editar/{id}")
    public String mostrarFormularioEditar(@NonNull @PathVariable Long id, Model model) {
        Equipo equipo = equipoService.obtenerPorId(id);
        model.addAttribute("equipo", equipo);
        return "equipo_form";
    }

    @GetMapping("/equipos/eliminar/{id}")
    public String eliminarEquipo(@NonNull @PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return "redirect:/equipos";
    }
}