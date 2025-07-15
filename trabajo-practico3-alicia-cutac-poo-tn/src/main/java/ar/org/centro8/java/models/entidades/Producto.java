package ar.org.centro8.java.models.entidades;

import ar.org.centro8.java.models.enums.Talle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private int idProducto;
    private String tipoPrenda;
    private Talle talle;
    private String color;
    private double precio;
    private int stock;
    private double precioCosto;
}
