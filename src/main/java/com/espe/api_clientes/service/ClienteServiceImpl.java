package com.espe.api_clientes.service;

import com.espe.api_clientes.model.Cliente;
import com.espe.api_clientes.repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @Override
    public List<Cliente> listarTodos() {
        return clienteRepositorio.findAll();
    }
    
    @Override
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepositorio.findById(id);
    }
    
    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }
    
    @Override
    public void eliminar(Long id) {
        clienteRepositorio.deleteById(id);
    }
    
    @Override
    public Optional<Cliente> obtenerPorCi(String ci) {
        return clienteRepositorio.findByCi(ci);
    }
    
    @Override
    public List<Cliente> buscarPorNombres(String nombres) {
        return clienteRepositorio.findByNombresContainingIgnoreCase(nombres);
    }
    
    @Override
    public List<Cliente> buscarPorApellidos(String apellidos) {
        return clienteRepositorio.findByApellidosContainingIgnoreCase(apellidos);
    }
    
    @Override
    public boolean existePorCi(String ci) {
        return clienteRepositorio.existsByCi(ci);
    }
}