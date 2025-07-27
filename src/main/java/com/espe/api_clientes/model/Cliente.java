package com.espe.api_clientes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "La cédula es obligatoria")
    @Column(nullable = false, unique = true)
    private String ci;
    
    @NotBlank(message = "Los nombres son obligatorios")
    @Column(nullable = false)
    private String nombres;
    
    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(nullable = false)
    private String apellidos;
    
    @NotBlank(message = "El contacto es obligatorio")
    @Column(nullable = false)
    private String contacto;
    
    // Constructor vacío
    public Cliente() {}
    
    // Constructor con parámetros
    public Cliente(String ci, String nombres, String apellidos, String contacto) {
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.contacto = contacto;
    }
    
    // Getters y setters
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public String getCi() { 
        return ci; 
    }
    
    public void setCi(String ci) { 
        this.ci = ci; 
    }
    
    public String getNombres() { 
        return nombres; 
    }
    
    public void setNombres(String nombres) { 
        this.nombres = nombres; 
    }
    
    public String getApellidos() { 
        return apellidos; 
    }
    
    public void setApellidos(String apellidos) { 
        this.apellidos = apellidos; 
    }
    
    public String getContacto() { 
        return contacto; 
    }
    
    public void setContacto(String contacto) { 
        this.contacto = contacto; 
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", ci='" + ci + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}