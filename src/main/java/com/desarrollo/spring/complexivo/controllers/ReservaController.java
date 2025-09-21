package com.desarrollo.spring.complexivo.controllers;

import com.desarrollo.spring.complexivo.models.Reserva;
import com.desarrollo.spring.complexivo.services.ClienteService;
import com.desarrollo.spring.complexivo.services.HabitacionService;
import com.desarrollo.spring.complexivo.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaService service;

    @Autowired
    HabitacionService habitacionService;

    @Autowired
    ClienteService clienteService;

    // Mostrar formulario de nueva reserva
    @GetMapping("/nueva")
    public String getFormReserva(@ModelAttribute Reserva reserva, Model model) {
        model.addAttribute("reserva", reserva);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("habitaciones", habitacionService.findAll());
        return "reservas/nueva"; // <-- usamos el mismo archivo
    }

    // Guardar nueva reserva
    @PostMapping("/nueva")
    public RedirectView saveReserva(@ModelAttribute Reserva reserva) {
        service.save(reserva);
        return new RedirectView("/reservas/lista");
    }

    // Listar todas las reservas
    @GetMapping("/lista")
    public String listarReservas(Model model) {
        model.addAttribute("reservas", service.findAll());
        return "reservas/lista";
    }

    // Formulario para editar reserva
    @GetMapping("/editar/{id}")
    public String editarReserva(@PathVariable Long id, Model model) {
        Reserva reserva = service.findById(id);
        model.addAttribute("reserva", reserva);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("habitaciones", habitacionService.findAll());
        return "reservas/nueva"; // <-- mismo archivo "nueva.html"
    }

    // Guardar cambios de edición
    @PostMapping("/editar/{id}")
    public RedirectView actualizarReserva(@PathVariable Long id, @ModelAttribute Reserva reserva) {
        reserva.setId(id); // asegurar que actualice el mismo registro
        service.save(reserva);
        return new RedirectView("/reservas/lista");
    }

    // Eliminar reserva
    @GetMapping("/eliminar/{id}")
    public RedirectView eliminarReserva(@PathVariable Long id) {
        service.deleteById(id);
        return new RedirectView("/reservas/lista");
    }
}

