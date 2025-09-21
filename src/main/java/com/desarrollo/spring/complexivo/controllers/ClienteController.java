package com.desarrollo.spring.complexivo.controllers;

import com.desarrollo.spring.complexivo.models.Cliente;
import com.desarrollo.spring.complexivo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    // Mostrar lista de clientes
    @GetMapping("/lista")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "clientes/lista";
    }

    // Formulario nuevo
    @GetMapping("/nuevo")
    public String formNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/nuevo";
    }

    // Formulario editar
    @GetMapping("/editar/{id}")
    public String formEditarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.findById(id);
        model.addAttribute("cliente", cliente);
        return "clientes/nuevo";
    }

    // Guardar nuevo cliente
    @PostMapping("/nuevo")
    public RedirectView guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.save(cliente);
        return new RedirectView("/clientes/lista");
    }

    // Actualizar cliente existente
    @PostMapping("/editar/{id}")
    public RedirectView actualizarCliente(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        Cliente clienteExistente = clienteService.findById(id);
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefono(cliente.getTelefono());

        clienteService.save(clienteExistente);
        return new RedirectView("/clientes/lista");
    }


    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public RedirectView eliminarCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return new RedirectView("/clientes/lista");
    }
}
