package com.example.demo.service;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.model.Equipo;
import com.example.demo.repository.EquipoRepository;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    public Equipo guardarEquipo(@NonNull Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo obtenerPorId(@NonNull Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    public void eliminarEquipo(@NonNull Long id) {
        equipoRepository.deleteById(id);
    }
}