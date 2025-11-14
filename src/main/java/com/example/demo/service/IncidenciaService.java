package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.model.Incidencia;
import com.example.demo.repository.IncidenciaRepository;

@Service
public class IncidenciaService {
    private final IncidenciaRepository incidenciaRepository;

    public IncidenciaService(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    public List<Incidencia> listarIncidencias() {
        return incidenciaRepository.findAll();
    }

    public Incidencia guardarIncidencia(@NonNull Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    public Incidencia obtenerPorId(@NonNull Long id) {
        return incidenciaRepository.findById(id).orElse(null);
    }

    public void eliminarIncidencia(@NonNull Long id) {
        incidenciaRepository.deleteById(id);
    }

    public List<Incidencia> buscarPorRangoFechas(@NonNull LocalDate fechaInicio, @NonNull LocalDate fechaFin) {
        return incidenciaRepository.findByFechaAperturaBetween(fechaInicio, fechaFin);
    }
}