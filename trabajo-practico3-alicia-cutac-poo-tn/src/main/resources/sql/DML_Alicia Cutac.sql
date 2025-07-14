INSERT INTO clientes (nombre, apellido, correo, telefono) VALUES
('Luciana',   'Pérez', 'luci.perez@gmail.com', '011-33278349'),
('Juan',    'Gómez', 'jugomez@yahoo.com',  '011-23475890'),
('Mariana',  'López', 'mar.lopez@gmail..com', '011-34545562'),
('Carlos',  'Ramírez','c.ramirez@example.com','011-45670001'),
('Analía','Arias','anitaarias@fmail.com','011-46732952');

INSERT INTO productos (tipo_prenda, talle, color, precio, stock,precio_costo) VALUES
('Remera', 'M',  'Rojo', 25000,45,8000),
('Remera', 'L',  'Negro', 25000,60,8000),
('Jean', 'L',  'Azul', 45500,80,18400),
('Campera',  'XL', 'Negro', 75990,50,30000),
('Vestido',  'S',  'Verde', 62000,50,20600),
('Buzo',  'M',  'Gris', 40000,40,12000),
('Camisa', 'M', 'Blanca', 30700,80,7200),
('Pantalon', 'L', 'Beige', 50300,30,16500);


INSERT INTO ventas (id_cliente, fecha_venta, total, estado_venta) VALUES
(1, '2025-06-01', 75000,  'pagado'),
(2, '2025-06-03', 45500,  'pendiente'),
(3, '2025-06-05', 135990,  'pagado'),
(1, '2025-06-06', 62000,  'cancelado'),
(5, '2025-06-07', 90000, 'pagado');


INSERT INTO detalle_ventas (id_venta, id_producto, cantidad, subtotal) VALUES
(1, 1, 2, 50000),  -- 2 remeras rojas talle M
(1, 3, 1, 45500),  -- 1 jean
(2, 3, 1, 45500),  -- 1 jean
(3, 4, 1, 75990),  -- 1 campera
(3, 5, 1, 62000),  -- 1 vestido
(4, 6, 1, 40000), -- 1 buzo
(5, 7, 2, 61400), -- 2 camisas
(5, 8, 1, 50300); -- 1 pantalón
