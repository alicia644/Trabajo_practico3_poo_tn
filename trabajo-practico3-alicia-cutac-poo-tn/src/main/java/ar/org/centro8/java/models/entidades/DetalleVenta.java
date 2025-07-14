package ar.org.centro8.java.models.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private double subtotal;
}
