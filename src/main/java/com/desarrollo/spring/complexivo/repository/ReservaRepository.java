package com.desarrollo.spring.complexivo.repository;

import com.desarrollo.spring.complexivo.models.Cliente;
import com.desarrollo.spring.complexivo.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findByHabitacionId(Long habitacionId);
}
