package controlador;

import modelo.Orden;
import conexion.ConexionDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdenDAO {
    private ConexionDB conexionDB = new ConexionDB();

    public void agregar(Orden orden) {
        String query = "INSERT INTO orden (id_orden, id_clientes, id_mesas, id_empleados, fecha_hora, total, estado_orden) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, orden.getId_orden());
            pst.setInt(2, orden.getId_clientes());
            pst.setInt(3, orden.getId_mesas());
            pst.setInt(4, orden.getId_empleados());
            pst.setTimestamp(5, new java.sql.Timestamp(orden.getFecha_hora().getTime()));
            pst.setInt(6, orden.getTotal());
            pst.setString(7, orden.getEstado_orden());

            int resultado = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, resultado > 0 ?
                    "Registro agregado con éxito." : "Registro NO agregado.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar orden: " + e.getMessage());
        }
    }

    public void actualizar(Orden orden) {
        String query = "UPDATE orden SET id_clientes = ?, id_mesas = ?, id_empleados = ?, fecha_hora = ?, total = ?, estado_orden = ? WHERE id_orden = ?";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, orden.getId_clientes());
            pst.setInt(2, orden.getId_mesas());
            pst.setInt(3, orden.getId_empleados());
            pst.setTimestamp(4, new java.sql.Timestamp(orden.getFecha_hora().getTime()));
            pst.setInt(5, orden.getTotal());
            pst.setString(6, orden.getEstado_orden());
            pst.setInt(7, orden.getId_orden());

            int resultado = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, resultado > 0 ?
                    "Registro actualizado con éxito." : "Registro NO actualizado.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar orden: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM orden WHERE id_orden = ?";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);

            int resultado = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, resultado > 0 ?
                    "Registro eliminado con éxito." : "Registro NO eliminado.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar orden: " + e.getMessage());
        }
    }
}
