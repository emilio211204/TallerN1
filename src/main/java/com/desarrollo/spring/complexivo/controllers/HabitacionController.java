package com.desarrollo.spring.complexivo.controllers;

import com.desarrollo.spring.complexivo.models.Habitacion;
import com.desarrollo.spring.complexivo.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    HabitacionService habitacionService;

    // Mostrar lista de habitaciones
    @GetMapping("/lista")
    public String listarHabitaciones(Model model) {
        model.addAttribute("habitaciones", habitacionService.findAll());
        return "habitaciones/lista";
    }

    // Formulario nuevo
    @GetMapping("/nuevo")
    public String formNuevaHabitacion(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        return "habitaciones/nuevo";
    }

    // Formulario editar
    @GetMapping("/editar/{id}")
    public String formEditarHabitacion(@PathVariable Long id, Model model) {
        Habitacion habitacion = habitacionService.findById(id);
        model.addAttribute("habitacion", habitacion);
        return "habitaciones/nuevo";
    }

    // Guardar nueva habitación
    @PostMapping("/nuevo")
    public RedirectView guardarHabitacion(@ModelAttribute Habitacion habitacion) {
        habitacionService.save(habitacion);
        return new RedirectView("/habitaciones/lista");
    }

    // Actualizar habitación existente
    @PostMapping("/editar/{id}")
    public RedirectView actualizarHabitacion(@PathVariable Long id, @ModelAttribute Habitacion habitacion) {
        habitacion.setId(id);
        habitacionService.save(habitacion);
        return new RedirectView("/habitaciones/lista");
    }

    // Eliminar habitación
    @GetMapping("/eliminar/{id}")
    public RedirectView eliminarHabitacion(@PathVariable Long id) {
        habitacionService.deleteById(id);
        return new RedirectView("/habitaciones/lista");
    }
}
