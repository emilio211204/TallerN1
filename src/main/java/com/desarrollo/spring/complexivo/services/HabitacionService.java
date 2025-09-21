package com.desarrollo.spring.complexivo.services;

import com.desarrollo.spring.complexivo.models.Habitacion;
import com.desarrollo.spring.complexivo.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    HabitacionRepository habitacionRepository;

    // Guardar habitación
    public Habitacion save(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    // Listar todas las habitaciones
    public List<Habitacion> findAll() {
        return habitacionRepository.findAll();
    }

    // Buscar por ID
    public Habitacion findById(Long id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    // Eliminar por ID
    public void deleteById(Long id) {
        habitacionRepository.deleteById(id);
    }
}
