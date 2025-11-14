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

import com.example.demo.model.Tecnico;
import com.example.demo.service.TecnicoService;

@Controller
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    // Mostrar lista de técnicos
    @GetMapping
    public String listarTecnicos(Model model) {
        model.addAttribute("tecnicos", tecnicoService.listarTecnicos());
        return "tecnicos";
    }

    // Mostrar formulario para nuevo técnico
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tecnico", new Tecnico());
        return "tecnico_form";
    }

    // Guardar técnico nuevo
    @PostMapping("/guardar")
    public String guardarTecnico(@NonNull @ModelAttribute("tecnico") Tecnico tecnico) {
        tecnicoService.guardarTecnico(tecnico);
        return "redirect:/tecnicos";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@NonNull @PathVariable Long id, Model model) {
        Tecnico tecnico = tecnicoService.obtenerPorId(id);
        if (tecnico == null) {
            return "redirect:/tecnicos";
        }
        model.addAttribute("tecnico", tecnico);
        return "tecnico_form";
    }

    // Guardar técnico editado
    @PostMapping("/editar/{id}")
    public String editarTecnico(@NonNull @PathVariable Long id, @NonNull @ModelAttribute("tecnico") Tecnico tecnico) {
        tecnico.setId(id);
        tecnicoService.guardarTecnico(tecnico);
        return "redirect:/tecnicos";
    }

    // Eliminar técnico
    @GetMapping("/eliminar/{id}")
    public String eliminarTecnico(@NonNull @PathVariable Long id) {
        tecnicoService.eliminarTecnico(id);
        return "redirect:/tecnicos";
    }
}