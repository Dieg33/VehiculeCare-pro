package com.vehiculecarepro.controller;

import com.vehiculecarepro.service.MantenimientoService;
import com.vehiculecarepro.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
@Controller
@RequiredArgsConstructor
public class PanelController {

    private final VehiculoService vehiculoService;
    private final MantenimientoService mantenimientoService;

    @GetMapping("/panel")
    public String panel(Model model) {

        // menú activo + título
        model.addAttribute("activeMenu", "panel");
        model.addAttribute("pageTitle", "Panel de Control — VehicleCare Pro");

        // -------- STATS VEHÍCULOS -------
        model.addAttribute("totalVehiculos", vehiculoService.contarTodos());
        model.addAttribute("vehiculosActivos", vehiculoService.contarPorEstado("ACTIVO"));
        model.addAttribute("vehiculosTaller", vehiculoService.contarPorEstado("EN_TALLER"));
        model.addAttribute("vehiculosFueraServicio", vehiculoService.contarPorEstado("FUERA_DE_SERVICIO"));

        // -------- STATS MANTENIMIENTOS -------
        model.addAttribute("totalMantenimientos", mantenimientoService.contarTodos());
        model.addAttribute("mantenimientosHoy", mantenimientoService.contarPorFecha(LocalDate.now()));
        model.addAttribute("mantenimientosRecientes", mantenimientoService.ultimos(5));

        return "layout"; // SIEMPRE layout
    }
}
