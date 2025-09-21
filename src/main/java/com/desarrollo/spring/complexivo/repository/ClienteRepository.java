package com.desarrollo.spring.complexivo.repository;

import com.desarrollo.spring.complexivo.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
