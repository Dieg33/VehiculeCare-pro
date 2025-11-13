package com.vehiculecarepro.service;

import com.vehiculecarepro.model.Mantenimiento;
import com.vehiculecarepro.repository.MantenimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;

    // Total de registros de mantenimiento
    public long contarTodos() {
        return mantenimientoRepository.count();
    }

    // Mantenimientos para una fecha específica (lo usamos para "hoy")
    public long contarPorFecha(LocalDate fecha) {
        return mantenimientoRepository.countByFechaIngreso(fecha);
    }

    // Últimos N mantenimientos ordenados por fecha de ingreso DESC
    public List<Mantenimiento> ultimos(int limite) {
        return mantenimientoRepository
                .findAllByOrderByFechaIngresoDesc(PageRequest.of(0, limite))
                .getContent();
    }
}
