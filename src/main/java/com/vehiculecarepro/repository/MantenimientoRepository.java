package com.vehiculecarepro.repository;

import com.vehiculecarepro.model.Mantenimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {

    // Para contar mantenimientos por fecha de ingreso
    long countByFechaIngreso(LocalDate fechaIngreso);

    // Para obtener los últimos mantenimientos
    Page<Mantenimiento> findAllByOrderByFechaIngresoDesc(Pageable pageable);
}
