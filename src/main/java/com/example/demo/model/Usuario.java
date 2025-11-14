package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long persona_id;
    private String nick;
    private String clave;
    private String rol;
    private Integer activo;
    private String correo;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPersonas_id() { return persona_id; }
    public void setPersonas_id(Long personas_id) { this.persona_id = personas_id; }

    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Integer getActivo() { return activo; }
    public void setActivo(Integer activo) { this.activo = activo; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}