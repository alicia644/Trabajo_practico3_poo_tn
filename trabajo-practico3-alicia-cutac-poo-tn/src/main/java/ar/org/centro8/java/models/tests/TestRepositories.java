package ar.org.centro8.java.models.tests;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ar.org.centro8.java.models.entidades.Cliente;
import ar.org.centro8.java.models.entidades.DetalleVenta;
import ar.org.centro8.java.models.entidades.Producto;
import ar.org.centro8.java.models.entidades.Venta;
import ar.org.centro8.java.models.enums.EstadoVenta;
import ar.org.centro8.java.models.enums.Talle;
import ar.org.centro8.java.models.repositories.ClienteDAO;
import ar.org.centro8.java.models.repositories.DetalleVentaDAO;
import ar.org.centro8.java.models.repositories.ProductoDAO;
import ar.org.centro8.java.models.repositories.VentaDAO;
import ar.org.centro8.java.models.repositories.interfaces.I_ClienteDAO;
import ar.org.centro8.java.models.repositories.interfaces.I_DetalleVentaDAO;
import ar.org.centro8.java.models.repositories.interfaces.I_ProductoDAO;
import ar.org.centro8.java.models.repositories.interfaces.I_VentaDAO;
@SpringBootApplication(scanBasePackages = "ar.org.centro8.java"
)
public class TestRepositories {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(TestRepositories.class,args);) {
            I_ProductoDAO productoDAO = context.getBean(ProductoDAO.class);
            I_ClienteDAO clienteDAO = context.getBean(ClienteDAO.class);
            I_DetalleVentaDAO detalleVentaDAO = context.getBean(DetalleVentaDAO.class);
            I_VentaDAO ventaDAO = context.getBean(VentaDAO.class);

            //Pruebas de productos

            // Test 1: Crear un producto
            System.out.println("\n>>> Test 1: creando un producto");
            Producto  nuevoProducto = new Producto(0,"pantalon", Talle.L, "azul", 43200, 20,12000);
            productoDAO.create(nuevoProducto);
            if(nuevoProducto.getIdProducto()>0){
                System.out.println(" Producto creado correctamente con el id " + nuevoProducto.getIdProducto());
                System.out.println(nuevoProducto);
                System.out.println();
            }else {
                System.err.println(" ¡¡ ERROR - no se pudo crear al producto !!");
            }

            //Test 2: Mostrar un producto por ID(existente)
            System.out.println("\n>>> test 2: Mostrando producto por ID " + nuevoProducto.getIdProducto() + "...");
            Producto productoEncontrado = productoDAO.findById(nuevoProducto.getIdProducto());
            if (productoEncontrado!=null) {
                System.out.println("Producto encontrado: " + productoEncontrado);
                System.out.println();
            } else {
                System.err.println("ERROR! No se encontró el producto con el ID " + nuevoProducto.getIdProducto());
                System.out.println();
            }


            //Prueba 3:Buscando un producto con ID(inexistente)
            System.out.println("\n>>> Buscando el producto con ID 43");
            Producto productoNoEncontrado = productoDAO.findById(43);
            if (productoNoEncontrado!=null) {
                System.out.println("Producto  encontrado: " + productoNoEncontrado);
                System.out.println();
            } else {
                System.err.println("ERROR! No se encontró el producto con el ID 43");
                System.out.println();
            }   
            //Test 4: Actualizar un producto
            System.out.println("\n>>> Test 4: Actualizando Producto " + nuevoProducto.getIdProducto() + "...");
            nuevoProducto.setTipoPrenda("Musculosa");
            int filasAfectadas = productoDAO.update(nuevoProducto);
            if(filasAfectadas==1){
                System.out.println("Producto " + nuevoProducto.getIdProducto() + " actualizado correctamente");
                System.out.println("Verificando actualización: " + productoDAO.findById(nuevoProducto.getIdProducto()));
            } else {
                System.err.println(" ERROR! No fue posible actualizar el producto !");
            }
            //Test 5: Listar todos los productos
            System.out.println("\n>>> Test 5: Listando todos los productos...");
            List<Producto> productosTodos = productoDAO.findAll();
            if (!productosTodos.isEmpty()) {
                System.out.println(" Productos encontrados: " + productosTodos.size());
                productosTodos.forEach(System.out::println);
            } else {
                System.err.println(" ERROR! No se encontraron productos");
            }
            // //Test 6: Eliminar un producto
            // System.out.println("\n>>> Test 6: Eliminando Producto " + nuevoProducto.getIdProducto() + "...");
            // int filasAfectadasDelete = productoDAO.delete(nuevoProducto.getIdProducto());
            // if (filasAfectadasDelete==1) {
            //     System.out.println(" Producto " + nuevoProducto.getIdProducto() + " eliminado correctamente");
            //     System.out.println("verificando eliminación: " + productoDAO.findById(nuevoProducto.getIdProducto()));
            // } else {
            //   System.err.println(" ERROR! No se pudo eliminar el producto!");
            // }   

            //Test 7: Buscar productos por tipo de prenda (remera)
            String prenda = "remera";
            System.out.println("\n>>> Test 7: buscando por prenda ("+ prenda +") ");
            List<Producto> productoPorPrenda = productoDAO.findByTipoDePrenda(prenda);
            if(!productoPorPrenda.isEmpty()){
                if (productoPorPrenda.size()>1) {
                prenda = "remeras";   
                }
                System.out.println("   Productos encontrados: "+ productoPorPrenda.size() +" "+ prenda );
                productoPorPrenda.forEach(System.out::println);
                System.out.println();
            } else{
                System.out.println(" No se encontraron productos!");
                System.out.println();
            }

            
            // Pruebas de cliente
            System.out.println("\n=== TEST CLIENTE ===");

            // Test 1: Crear cliente
            System.out.println("\n>>> Test Cliente 1: creando cliente");
            Cliente nuevoCliente = new Cliente(0, "Juan", "Pérez", "juanperez@gmail.com", "123456789");
            clienteDAO.create(nuevoCliente);
            if(nuevoCliente.getIdCliente() > 0){
                System.out.println("Cliente creado con ID: " + nuevoCliente.getIdCliente());
                System.out.println(nuevoCliente);
            } else {
                System.err.println("ERROR: No se pudo crear el cliente");
            }

            // Test 2: Buscar por ID (existente)
            System.out.println("\n>>> Test Cliente 2: buscar por ID");
            Cliente clienteEncontrado = clienteDAO.findById(nuevoCliente.getIdCliente());
            if(clienteEncontrado != null){
                System.out.println("Cliente encontrado: " + clienteEncontrado);
            } else {
                System.err.println("ERROR: Cliente no encontrado con ID " + nuevoCliente.getIdCliente());
            }

            // Test 3: Buscar todos
            System.out.println("\n>>> Test Cliente 3: listar todos los clientes");
            List<Cliente> clientes = clienteDAO.findAll();
            if(!clientes.isEmpty()){
                System.out.println("Clientes encontrados: " + clientes.size());
                clientes.forEach(System.out::println);
            } else {
                System.out.println("No se encontraron clientes");
            }

            // Test 4: Actualizar cliente
            System.out.println("\n>>> Test Cliente 4: actualizando cliente");
            nuevoCliente.setNombre("Carlos");
            nuevoCliente.setCorreo("carlos@gmail.com");
            int filasActualizadas = clienteDAO.update(nuevoCliente);
            if(filasActualizadas == 1){
                System.out.println("Cliente actualizado correctamente");
                System.out.println("Verificación: " + clienteDAO.findById(nuevoCliente.getIdCliente()));
            } else {
                System.err.println("ERROR: No se pudo actualizar el cliente");
            }

            // Test 5: Eliminar cliente
            System.out.println("\n>>> Test Cliente 5: eliminando cliente");
            int filasEliminadas = clienteDAO.delete(nuevoCliente.getIdCliente());
            if(filasEliminadas == 1){
                System.out.println("Cliente eliminado correctamente");
            } else {
                System.err.println("ERROR: No se pudo eliminar el cliente");
            }


            // Pruebas de ventas
            System.out.println("\n=== TEST VENTAS ===");

            // Test 1: Crear una venta
            System.out.println("\n>>> Test Venta 1: creando venta");
            Venta nuevaVenta = new Venta(0, 1, LocalDate.now(), 50000, EstadoVenta.PENDIENTE);
            ventaDAO.create(nuevaVenta);
            if(nuevaVenta.getIdVenta() > 0){
                System.out.println("Venta creada con ID: " + nuevaVenta.getIdVenta());
                System.out.println(nuevaVenta);
            } else {
                System.err.println("ERROR: No se pudo crear la venta");
            }

            // Test 2: Buscar venta por ID
            System.out.println("\n>>> Test Venta 2: buscar por ID");
            Venta ventaEncontrada = ventaDAO.findById(nuevaVenta.getIdVenta());
            if(ventaEncontrada != null){
                System.out.println("Venta encontrada: " + ventaEncontrada);
            } else {
                System.err.println("ERROR: Venta no encontrada con ID " + nuevaVenta.getIdVenta());
            }

            // Test 3: Buscar todas las ventas
            System.out.println("\n>>> Test Venta 3: listar todas las ventas");
            List<Venta> ventas = ventaDAO.findAll();
            if(!ventas.isEmpty()){
                System.out.println("Ventas encontradas: " + ventas.size());
                ventas.forEach(System.out::println);
            } else {
                System.out.println("No se encontraron ventas");
            }

            // Test 4: Actualizar venta
            System.out.println("\n>>> Test Venta 4: actualizando venta");
            nuevaVenta.setTotal(55000);
            nuevaVenta.setEstadoVenta(EstadoVenta.PAGADO);
            int filasActualizadasVenta = ventaDAO.update(nuevaVenta);
            if(filasActualizadasVenta == 1){
                System.out.println("Venta actualizada correctamente");
                System.out.println("Verificación: " + ventaDAO.findById(nuevaVenta.getIdVenta()));
            } else {
                System.err.println("ERROR: No se pudo actualizar la venta");
            }

            // Test 5: Buscar ventas por estado
            System.out.println("\n>>> Test Venta 5: buscar ventas por estado");
            List<Venta> ventasPendientes = ventaDAO.findByEstado(EstadoVenta.PENDIENTE);
            if(!ventasPendientes.isEmpty()){
                System.out.println("Ventas pendientes encontradas: " + ventasPendientes.size());
                ventasPendientes.forEach(System.out::println);
            } else {
                System.out.println("No se encontraron ventas pendientes");
            }

            // Test 6: Eliminar venta
            System.out.println("\n>>> Test Venta 6: eliminando venta");
            int filasEliminadasVenta = ventaDAO.delete(nuevaVenta.getIdVenta());
            if(filasEliminadasVenta == 1){
                System.out.println("Venta eliminada correctamente");
            } else {
                System.err.println("ERROR: No se pudo eliminar la venta");
            }

            // Pruebas de detalle ventas
            System.out.println("\n=== TEST DETALLE VENTAS ===");

            // Test 1: Crear detalle venta
            System.out.println("\n>>> Test DetalleVenta 1: creando detalle venta");
            DetalleVenta nuevoDetalle = new DetalleVenta(nuevaVenta.getIdVenta(), 1, 2, 10000);
            detalleVentaDAO.create(nuevoDetalle);
            System.out.println("Detalle de venta creado");

            // Test 2: Buscar detalle por ID
            System.out.println("\n>>> Test DetalleVenta 2: buscar por ID");
            DetalleVenta detalleEncontrado = detalleVentaDAO.findById(nuevoDetalle.getIdVenta());
            if(detalleEncontrado != null){
                System.out.println("Detalle encontrado: " + detalleEncontrado);
            } else {
                System.err.println("ERROR: Detalle no encontrado");
            }

            // Test 3: Buscar todos los detalles
            System.out.println("\n>>> Test DetalleVenta 3: listar todos los detalles");
            List<DetalleVenta> detalles = detalleVentaDAO.findAll();
            if(!detalles.isEmpty()){
                System.out.println("Detalles encontrados: " + detalles.size());
                detalles.forEach(System.out::println);
            } else {
                System.out.println("No se encontraron detalles");
            }

            // Test 4: Actualizar detalle
            System.out.println("\n>>> Test DetalleVenta 4: actualizando detalle");
            nuevoDetalle.setCantidad(3);
            nuevoDetalle.setSubtotal(15000);
            int filasActualizadasDetalle = detalleVentaDAO.update(nuevoDetalle);
            if(filasActualizadasDetalle == 1){
                System.out.println("Detalle actualizado correctamente");
                System.out.println("Verificación: " + detalleVentaDAO.findById(nuevoDetalle.getIdVenta()));
            } else {
                System.err.println("ERROR: No se pudo actualizar el detalle");
            }

            // Test 5: Eliminar detalle
            System.out.println("\n>>> Test DetalleVenta 5: eliminando detalle");
            int filasEliminadasDetalle = detalleVentaDAO.delete(nuevoDetalle.getIdVenta());
            if(filasEliminadasDetalle == 1){
                System.out.println("Detalle eliminado correctamente");
            } else {
                System.err.println("ERROR: No se pudo eliminar el detalle");
            }
        




           
        } catch (Exception e) {
            System.err.println("¡¡ ERROR EN LA BASE DE DATOS !!");
            e.printStackTrace();
         } finally {
            System.out.println("<<< Pruebas finalizadas >>>");
            System.out.println("<<< Contexto de Spring cerrado >>>");
         }

    }
}
    


