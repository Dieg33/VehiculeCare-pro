package com.vehiculecarepro.service;

import com.vehiculecarepro.model.Vehiculo;
import com.vehiculecarepro.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    // ========= MÉTODOS PARA EL PANEL =========

    public long contarTodos() {
        return vehiculoRepository.count();
    }

    public long contarPorEstado(String estado) {
        return vehiculoRepository.countByEstado(estado);
    }

    // ========= LISTADO / BUSCADOR =========

    public List<Vehiculo> list(String q) {
        if (q == null || q.isBlank()) {
            return vehiculoRepository.findAll();
        }
        return vehiculoRepository
                .findByPlacaContainingIgnoreCaseOrMarcaContainingIgnoreCaseOrModeloContainingIgnoreCase(
                        q, q, q
                );
    }

    // ========= CRUD QUE USA EL CONTROLLER =========

    // get(id) -> usado en editar
    public Vehiculo get(Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    // save(v) -> usado en guardar
    public Vehiculo save(Vehiculo v) {
        return vehiculoRepository.save(v);
    }

    // delete(id) -> usado en eliminar
    public void delete(Long id) {
        vehiculoRepository.deleteById(id);
    }
}
