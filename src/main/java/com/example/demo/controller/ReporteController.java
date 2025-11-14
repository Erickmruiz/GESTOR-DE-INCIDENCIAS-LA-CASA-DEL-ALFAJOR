package com.example.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Equipo;
import com.example.demo.model.Incidencia;
import com.example.demo.service.EquipoService;
import com.example.demo.service.IncidenciaService;

@Controller
public class ReporteController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/reportes")
    public String mostrarReporte(
            @RequestParam(required = false) Long equipoId,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String ubicacion,
            Model model) {

        List<Incidencia> incidencias = incidenciaService.listarIncidencias();
        if (incidencias == null) incidencias = Collections.emptyList();

        // Filtrar por equipo
        if (equipoId != null && equipoId > 0) {
            incidencias = incidencias.stream()
                    .filter(i -> i.getEquipo() != null && i.getEquipo().getId() != null && i.getEquipo().getId().equals(equipoId))
                    .collect(Collectors.toList());
        }

        // Filtrar por tipo
        if (tipo != null && !tipo.isEmpty()) {
            incidencias = incidencias.stream()
                    .filter(i -> i.getEquipo() != null && tipo.equals(i.getEquipo().getTipo()))
                    .collect(Collectors.toList());
        }

        // Filtrar por ubicación
        if (ubicacion != null && !ubicacion.isEmpty()) {
            incidencias = incidencias.stream()
                    .filter(i -> i.getEquipo() != null && ubicacion.equals(i.getEquipo().getUbicacion()))
                    .collect(Collectors.toList());
        }

        // Listas para los filtros
        List<Equipo> equipos = equipoService.listarEquipos();
        if (equipos == null) equipos = Collections.emptyList();

        List<String> tipos = equipos.stream()
                .map(Equipo::getTipo)
                .filter(t -> t != null && !t.isEmpty())
                .distinct()
                .collect(Collectors.toList());
        List<String> ubicaciones = equipos.stream()
                .map(Equipo::getUbicacion)
                .filter(u -> u != null && !u.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("incidencias", incidencias);
        model.addAttribute("equipos", equipos);
        model.addAttribute("tipos", tipos);
        model.addAttribute("ubicaciones", ubicaciones);

        return "reportes";
    }
}