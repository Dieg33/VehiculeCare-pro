package com.vehiculecarepro.controller;

import com.vehiculecarepro.model.Vehiculo;
import com.vehiculecarepro.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService service;

    @GetMapping("/nuevo")
    public String nuevo(Model m, Principal p) {
        m.addAttribute("username", p != null ? p.getName() : "usuario");
        m.addAttribute("vehiculo", new Vehiculo());
        return "vehiculo_form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model m, Principal p) {
        m.addAttribute("username", p != null ? p.getName() : "usuario");
        m.addAttribute("vehiculo", service.get(id));
        return "vehiculo_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Vehiculo v, RedirectAttributes ra) {
        try {
            service.save(v);
            ra.addFlashAttribute("ok", "Vehículo guardado correctamente.");
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/vehiculos" + (v.getId() == null ? "/nuevo" : "/editar/" + v.getId());
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("ok", "Vehículo eliminado.");
        return "redirect:/dashboard";
    }
}
