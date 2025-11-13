package com.vehiculecarepro.service;

import com.vehiculecarepro.model.Proveedor;
import com.vehiculecarepro.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    public void guardar(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    public Proveedor buscarPorId(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
    }
}
