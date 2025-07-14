DROP DATABASE IF EXISTS tiendaderopa;
CREATE DATABASE tiendaderopa;
USE tiendaderopa;



CREATE TABLE clientes (
  id_cliente INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NOT NULL,
  correo VARCHAR(45) NOT NULL,
  telefono VARCHAR(20) NOT NULL
  );

CREATE TABLE productos (
  id_producto INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  tipo_prenda VARCHAR(45) NOT NULL,
  talle ENUM('S', 'M', 'L', 'XL', 'XXL') NOT NULL,
  color VARCHAR(45) NOT NULL,
  precio DOUBLE NOT NULL,
  stock INT NOT NULL,
  precio_costo DOUBLE NOT NULL
  );

CREATE TABLE ventas (
  id_venta INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  id_cliente INT NOT NULL,
  fecha_venta DATE NOT NULL,
  total DOUBLE NOT NULL,
  estado_venta ENUM ('PENDIENTE', 'PAGADO', 'CANCELADO') NOT NULL
   );
CREATE TABLE detalle_ventas (
  id_venta INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  subtotal DOUBLE NOT NULL,
  PRIMARY KEY (id_venta, id_producto)
  );

ALTER TABLE ventas ADD CONSTRAINT fk_ventas_clientes
FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente);

ALTER TABLE detalle_ventas ADD CONSTRAINT fk_detalle_venta
FOREIGN KEY (id_venta) REFERENCES ventas(id_venta);

ALTER TABLE detalle_ventas
ADD CONSTRAINT fk_detalle_producto
FOREIGN KEY (id_producto)
REFERENCES productos(id_producto);