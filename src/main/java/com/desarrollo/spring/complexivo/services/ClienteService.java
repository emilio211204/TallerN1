package com.desarrollo.spring.complexivo.services;

import com.desarrollo.spring.complexivo.models.Cliente;

import com.desarrollo.spring.complexivo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    // Guardar cliente
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Listar todos los clientes
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    // Buscar por ID
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // Eliminar por ID
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
