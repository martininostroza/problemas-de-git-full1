package com.microservicio.usuario.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String nombreRol;

}
    