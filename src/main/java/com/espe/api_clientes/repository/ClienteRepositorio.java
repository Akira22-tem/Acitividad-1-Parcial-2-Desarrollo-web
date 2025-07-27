package com.espe.api_clientes.repository;

import com.espe.api_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    
    // Buscar cliente por cédula
    Optional<Cliente> findByCi(String ci);
    
    // Buscar clientes por nombres (ignorando mayúsculas/minúsculas)
    List<Cliente> findByNombresContainingIgnoreCase(String nombres);
    
    // Buscar clientes por apellidos (ignorando mayúsculas/minúsculas)
    List<Cliente> findByApellidosContainingIgnoreCase(String apellidos);
    
    // Buscar clientes por nombres y apellidos
    @Query("SELECT c FROM Cliente c WHERE c.nombres LIKE %:nombres% AND c.apellidos LIKE %:apellidos%")
    List<Cliente> findByNombresAndApellidos(@Param("nombres") String nombres, @Param("apellidos") String apellidos);
    
    // Verificar si existe un cliente con la cédula dada (útil para validaciones)
    boolean existsByCi(String ci);
}