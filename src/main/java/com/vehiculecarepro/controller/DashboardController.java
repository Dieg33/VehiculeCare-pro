package com.vehiculecarepro.controller;

import com.vehiculecarepro.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final VehiculoService vehiculoService;

    @GetMapping("/dashboard")
    public String showDashboardPage(@RequestParam(required = false) String q,
                                    Model model,
                                    Principal principal) {

        String username = principal != null ? principal.getName() : "usuario";

        model.addAttribute("vehiculos", vehiculoService.list(q)); // o search(q)
        model.addAttribute("q", q);

        model.addAttribute("username", username);
        model.addAttribute("activeMenu", "vehiculos");
        model.addAttribute("pageTitle", "Flota de Vehículos - VehicleCare Pro");

        // YA NO PONEMOS model.addAttribute("content", ...);

        return "layout"; // templates/layout.html
    }
}
