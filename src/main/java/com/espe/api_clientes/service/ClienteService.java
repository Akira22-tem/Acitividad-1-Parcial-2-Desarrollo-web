package com.espe.api_clientes.service;

import com.espe.api_clientes.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    
    /**
     * Obtener todos los clientes
     * @return Lista de todos los clientes
     */
    List<Cliente> listarTodos();
    
    /**
     * Obtener cliente por ID
     * @param id ID del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> obtenerPorId(Long id);
    
    /**
     * Guardar un nuevo cliente o actualizar uno existente
     * @param cliente Cliente a guardar
     * @return Cliente guardado
     */
    Cliente guardar(Cliente cliente);
    
    /**
     * Eliminar cliente por ID
     * @param id ID del cliente a eliminar
     */
    void eliminar(Long id);
    
    /**
     * Buscar cliente por cédula
     * @param ci Cédula del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> obtenerPorCi(String ci);
    
    /**
     * Buscar clientes por nombres
     * @param nombres Nombres a buscar
     * @return Lista de clientes que coincidan
     */
    List<Cliente> buscarPorNombres(String nombres);
    
    /**
     * Buscar clientes por apellidos
     * @param apellidos Apellidos a buscar
     * @return Lista de clientes que coincidan
     */
    List<Cliente> buscarPorApellidos(String apellidos);
    
    /**
     * Verificar si existe un cliente con la cédula dada
     * @param ci Cédula a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existePorCi(String ci);
}