package controlador;

import conexion.ConexionDB;
import modelo.Productos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {
    private Connection con;

    public ProductosDAO() {
        ConexionDB conexionDB = new ConexionDB();
        con = conexionDB.getConnection();
    }

    public List<Productos> obtenerTodos() {
        List<Productos> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Productos producto = new Productos(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("precio_u"),
                        rs.getString("disponibilidad")
                );
                lista.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }

        return lista;
    }

    public void agregar(Productos producto) {
        String sql = "INSERT INTO productos (nombre, categoria, precio_u, disponibilidad) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCategoria());
            ps.setInt(3, producto.getPrecio_u());
            ps.setString(4, producto.getDisponibilidad());
            ps.executeUpdate();
            System.out.println("Producto agregado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar producto: " + e.getMessage());
        }
    }

    public void actualizar(Productos producto) {
        String sql = "UPDATE productos SET nombre = ?, categoria = ?, precio_u = ?, disponibilidad = ? WHERE id_producto = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCategoria());
            ps.setInt(3, producto.getPrecio_u());
            ps.setString(4, producto.getDisponibilidad());
            ps.setInt(5, producto.getId_producto());
            ps.executeUpdate();
            System.out.println("Producto actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    public void eliminar(int id_producto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_producto);
            ps.executeUpdate();
            System.out.println("Producto eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}
