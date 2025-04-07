package controlador;

import conexion.ConexionDB;
import modelo.Productos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductosDAO {
    private ConexionDB conexionDB = new ConexionDB();


    public void agregar(Productos producto) {
        String query = "INSERT INTO productos (nombre, categoria, precio_u, disponibilidad) VALUES (?, ?, ?, ?)";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getCategoria());
            pst.setInt(3, producto.getPrecio_u());
            pst.setString(4, producto.getDisponibilidad());

            int resultado = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, resultado > 0 ?
                    "Registro agregado con éxito." : "Registro NO agregado.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage());
        }
    }

    public void actualizar(Productos producto) {

        String query = "UPDATE productos SET nombre = ?, categoria = ?, precio_u = ?, disponibilidad = ? WHERE id_producto = ?";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getCategoria());
            pst.setInt(3, producto.getPrecio_u());
            pst.setString(4, producto.getDisponibilidad());
            pst.setInt(5, producto.getId_producto()); // ID al final

            int resultado = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, resultado > 0 ?
                    "Registro actualizado con éxito." : "Registro NO actualizado.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar producto: " + e.getMessage());
        }
    }

    public void eliminar(int id_producto) {
        String query = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id_producto);

            int resultado = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, resultado > 0 ?
                    "Registro eliminado con éxito." : "Registro NO eliminado.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + e.getMessage());
        }
    }
}
