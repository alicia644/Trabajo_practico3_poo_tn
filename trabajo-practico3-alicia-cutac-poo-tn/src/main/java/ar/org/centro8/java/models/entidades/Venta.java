package ar.org.centro8.java.models.entidades;

import java.time.LocalDate;

import ar.org.centro8.java.models.enums.EstadoVenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    private int idVenta;
    private int idCliente;
    private LocalDate fechaVenta;
    private double total;
    private EstadoVenta estadoVenta;
}
