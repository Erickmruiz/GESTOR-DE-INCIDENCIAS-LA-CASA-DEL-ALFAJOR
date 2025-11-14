package com.example.demo.service;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tecnico;
import com.example.demo.repository.TecnicoRepository;

@Service
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public List<Tecnico> listarTecnicos() {
        return tecnicoRepository.findAll();
    }

    public Tecnico guardarTecnico(@NonNull Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico obtenerPorId(@NonNull Long id) {
        return tecnicoRepository.findById(id).orElse(null);
    }

    public void eliminarTecnico(@NonNull Long id) {
        tecnicoRepository.deleteById(id);
    }
}