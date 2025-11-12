package com.vehiculecarepro.controller;

import com.vehiculecarepro.model.Mantenimiento;
import com.vehiculecarepro.repository.MantenimientoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mantenimientos")

public class MantenimientoController {

    private final MantenimientoRepository mantenimientoRepository;

    public MantenimientoController(MantenimientoRepository mantenimientoRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
    }

    // Listar todos
    @GetMapping
    public String listarMantenimientos(Model model) {
        model.addAttribute("mantenimientos", mantenimientoRepository.findAll());
        return "mantenimientos"; // nombre del HTML
    }

    // Mostrar formulario de nuevo
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("mantenimiento", new Mantenimiento());
        return "form_mantenimiento";
    }

    // Guardar nuevo o editado
    @PostMapping("/guardar")
    public String guardarMantenimiento(@ModelAttribute Mantenimiento mantenimiento) {
        mantenimientoRepository.save(mantenimiento);
        return "redirect:/mantenimientos";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editarMantenimiento(@PathVariable Long id, Model model) {
        Mantenimiento mantenimiento = mantenimientoRepository.findById(id).orElseThrow();
        model.addAttribute("mantenimiento", mantenimiento);
        return "form_mantenimiento";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarMantenimiento(@PathVariable Long id) {
        mantenimientoRepository.deleteById(id);
        return "redirect:/mantenimientos";
    }

}
