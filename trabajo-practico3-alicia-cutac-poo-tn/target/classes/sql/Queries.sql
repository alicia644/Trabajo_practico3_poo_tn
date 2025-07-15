-- Mostrar el producto más vendido por cantidad

SELECT p.tipo_prenda, p.talle, p.color, SUM(dv.cantidad) total_vendida
FROM detalle_ventas dv
JOIN productos p ON dv.id_producto = p.id_producto
GROUP BY p.id_producto
ORDER BY total_vendida DESC
LIMIT 1;

-- Total recaudado por cada cliente de ventas pagadas

SELECT c.nombre, c.apellido, SUM(v.total) total_comprado
FROM clientes c
JOIN ventas v ON v.id_cliente = c.id_cliente
WHERE v.estado_venta = 'pagado'
GROUP BY c.id_cliente
ORDER BY total_comprado DESC;

-- Mostrar las ventas canceladas con nombre del cliente

SELECT v.id_venta, v.fecha_venta, c.nombre, c.apellido, v.total
FROM ventas v
JOIN clientes c ON v.id_cliente = c.id_cliente
WHERE v.estado_venta = 'cancelado';

-- Detalle completo de la venta id 3

SELECT v.id_venta, p.tipo_prenda, p.talle, p.color, dv.cantidad, dv.subtotal
FROM ventas v
JOIN detalle_ventas dv ON v.id_venta = dv.id_venta
JOIN productos p ON dv.id_producto = p.id_producto
WHERE v.id_venta = 3;

-- Valor total del inventario actual (venta y costo)

SELECT SUM(stock * precio)  valor_venta_total,
       SUM(stock * precio_costo)  valor_costo_total
FROM productos;
