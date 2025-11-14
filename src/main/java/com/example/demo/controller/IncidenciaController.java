package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Incidencia;
import com.example.demo.service.IncidenciaService;

@Controller
@RequestMapping("/incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    // Listar incidencias
    @GetMapping
    public String listarIncidencias(Model model) {
        model.addAttribute("incidencias", incidenciaService.listarIncidencias());
        return "incidencias";
    }

    // Mostrar formulario para nueva incidencia
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("incidencia", new Incidencia());
        return "incidencia_form";
    }

    // Guardar incidencia nueva
    @PostMapping("/guardar")
    public String guardarIncidencia(@NonNull @ModelAttribute Incidencia incidencia) {
        incidenciaService.guardarIncidencia(incidencia);
        return "redirect:/incidencias";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@NonNull @PathVariable Long id, Model model) {
        Incidencia incidencia = incidenciaService.obtenerPorId(id);
        model.addAttribute("incidencia", incidencia);
        return "incidencia_form";
    }

    // Guardar incidencia editada (SOLUCIÓN AL ERROR 405)
    @PostMapping("/editar/{id}")
    public String editarIncidencia(@NonNull @PathVariable Long id, @NonNull @ModelAttribute Incidencia incidencia) {
        incidencia.setId(id);
        incidenciaService.guardarIncidencia(incidencia);
        return "redirect:/incidencias";
    }

    // Eliminar incidencia
    @GetMapping("/eliminar/{id}")
    public String eliminarIncidencia(@NonNull @PathVariable Long id) {
        incidenciaService.eliminarIncidencia(id);
        return "redirect:/incidencias";
    }
}