package com.vehiculecarepro.repository;

import com.vehiculecarepro.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    boolean existsByPlaca(String placa);

    @Query("""
           SELECT v FROM Vehiculo v
           WHERE LOWER(v.placa)  LIKE LOWER(CONCAT('%', :q, '%'))
              OR LOWER(v.marca)  LIKE LOWER(CONCAT('%', :q, '%'))
              OR LOWER(v.modelo) LIKE LOWER(CONCAT('%', :q, '%'))
           """)
    List<Vehiculo> search(@Param("q") String q);

    // Para el panel de control
    long countByEstado(String estado);


    // para el buscador del dashboard
    List<Vehiculo> findByPlacaContainingIgnoreCaseOrMarcaContainingIgnoreCaseOrModeloContainingIgnoreCase(
            String placa,
            String marca,
            String modelo
    );
}
