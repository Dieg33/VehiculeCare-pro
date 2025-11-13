package com.vehiculecarepro.controller;

import com.vehiculecarepro.model.Proveedor;
import com.vehiculecarepro.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    // Mostrar lista de proveedores
    @GetMapping
    public String listarProveedores(Model model) {
        model.addAttribute("proveedores", proveedorService.listar());
        return "lista"; // <-- DE VUELTA A "lista"
    }

    // Mostrar formulario para nuevo proveedor
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "form"; // <-- DE VUELTA A "form"
    }

    // Guardar proveedor (nuevo o editado)
    @PostMapping
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.guardar(proveedor);
        return "redirect:/proveedores"; // <-- DE VUELTA A "/proveedores"
    }

    // Editar proveedor existente
    @GetMapping("/editar/{id}")
    public String editarProveedor(@PathVariable Long id, Model model) {
        model.addAttribute("proveedor", proveedorService.buscarPorId(id));
        return "form"; // <-- DE VUELTA A "form"
    }

    // Eliminar proveedor
    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return "redirect:/proveedores"; // <-- DE VUELTA A "/proveedores"
    }
}