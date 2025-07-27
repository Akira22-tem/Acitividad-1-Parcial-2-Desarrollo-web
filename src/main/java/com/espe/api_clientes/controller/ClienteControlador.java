package com.espe.api_clientes.controller;

import com.espe.api_clientes.model.Cliente;
import com.espe.api_clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteControlador {
    
    private final ClienteService clienteService;
    
    public ClienteControlador(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    /**
     * Endpoint de prueba
     */
    @GetMapping("/test")
    public String test() {
        return "API de Clientes funcionando correctamente";
    }
    
    /**
     * Listar todos los clientes
     * GET /api/clientes
     */
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }
    
    /**
     * Obtener cliente por ID
     * GET /api/clientes/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        return clienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crear nuevo cliente
     * POST /api/clientes
     */
    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        // Verificar si ya existe un cliente con esa cédula
        if (clienteService.existePorCi(cliente.getCi())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }
        
        Cliente creado = clienteService.guardar(cliente);
        return ResponseEntity.created(URI.create("/api/clientes/" + creado.getId())).body(creado);
    }
    
    /**
     * Actualizar cliente existente
     * PUT /api/clientes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return clienteService.obtenerPorId(id)
                .map(c -> {
                    // Verificar si la cédula ya está en uso por otro cliente
                    if (!c.getCi().equals(cliente.getCi()) && 
                        clienteService.existePorCi(cliente.getCi())) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).<Cliente>build();
                    }
                    
                    c.setCi(cliente.getCi());
                    c.setNombres(cliente.getNombres());
                    c.setApellidos(cliente.getApellidos());
                    c.setContacto(cliente.getContacto());
                    return ResponseEntity.ok(clienteService.guardar(c));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Eliminar cliente
     * DELETE /api/clientes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (clienteService.obtenerPorId(id).isPresent()) {
            clienteService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Buscar cliente por cédula
     * GET /api/clientes/ci/{ci}
     */
    @GetMapping("/ci/{ci}")
    public ResponseEntity<Cliente> obtenerPorCi(@PathVariable String ci) {
        return clienteService.obtenerPorCi(ci)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Buscar clientes por nombres
     * GET /api/clientes/buscar/nombres?q={nombres}
     */
    @GetMapping("/buscar/nombres")
    public List<Cliente> buscarPorNombres(@RequestParam String q) {
        return clienteService.buscarPorNombres(q);
    }
    
    /**
     * Buscar clientes por apellidos
     * GET /api/clientes/buscar/apellidos?q={apellidos}
     */
    @GetMapping("/buscar/apellidos")
    public List<Cliente> buscarPorApellidos(@RequestParam String q) {
        return clienteService.buscarPorApellidos(q);
    }
}