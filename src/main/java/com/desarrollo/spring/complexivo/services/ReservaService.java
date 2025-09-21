package com.desarrollo.spring.complexivo.services;

import com.desarrollo.spring.complexivo.models.Reserva;
import com.desarrollo.spring.complexivo.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    // Método para guardar una reserva
    public Reserva save(Reserva reserva) {
        return this.reservaRepository.save(reserva);
    }

    // Método para listar todas las reservas
    public List<Reserva> findAll() {
        return this.reservaRepository.findAll();
    }
    // Método para buscar por ID
    public Reserva findById(Long id) {
        return this.reservaRepository.findById(id).orElse(null);
    }

    // Método para eliminar por ID
    public void deleteById(Long id) {
        this.reservaRepository.deleteById(id);
    }

}
