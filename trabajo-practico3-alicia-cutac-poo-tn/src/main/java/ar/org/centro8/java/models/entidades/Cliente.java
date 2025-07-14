package ar.org.centro8.java.models.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
}

