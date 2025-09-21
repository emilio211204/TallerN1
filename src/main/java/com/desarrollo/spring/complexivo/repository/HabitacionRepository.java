package com.desarrollo.spring.complexivo.repository;

import com.desarrollo.spring.complexivo.models.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion,Long> {
}
