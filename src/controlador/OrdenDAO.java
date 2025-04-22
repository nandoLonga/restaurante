package controlador;

import modelo.Orden;
import conexion.ConexionDB;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class OrdenDAO {
    private ConexionDB conexionDB = new ConexionDB();


    public void agregar(Orden orden) {
        String query = "INSERT INTO orden (id_clientes, id_mesas, id_empleados, fecha_hora, total, estado_orden) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, orden.getId_clientes());
            pst.setInt(2, orden.getId_mesas());
            pst.setInt(3, orden.getId_empleados());
            pst.setTimestamp(4, Timestamp.valueOf(orden.getFecha_hora()));
            pst.setInt(5, orden.getTotal());
            pst.setString(6, orden.getEstado_orden());

            int resultado = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, resultado > 0 ? "Orden agregada correctamente." : "No se pudo agregar la orden.");

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
            pst.setTimestamp(4, Timestamp.valueOf(orden.getFecha_hora()));
            pst.setInt(5, orden.getTotal());
            pst.setString(6, orden.getEstado_orden());
            pst.setInt(7, orden.getId_orden());

            int resultado = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, resultado > 0 ? "Orden actualizada correctamente." : "No se pudo actualizar la orden.");

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
            JOptionPane.showMessageDialog(null, resultado > 0 ? "Orden eliminada correctamente." : "No se pudo eliminar la orden.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar orden: " + e.getMessage());
        }
    }


    public List<Orden> obtenerTodos() {
        List<Orden> ordenes = new ArrayList<>();
        String query = "SELECT * FROM orden";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int idOrden = rs.getInt("id_orden");
                int idClientes = rs.getInt("id_clientes");
                int idMesas = rs.getInt("id_mesas");
                int idEmpleados = rs.getInt("id_empleados");
                Timestamp fechaHora = rs.getTimestamp("fecha_hora");
                int total = rs.getInt("total");
                String estadoOrden = rs.getString("estado_orden");


                LocalDateTime fechaHoraLocal = fechaHora.toLocalDateTime();

                Orden orden = new Orden(idOrden, idClientes, idMesas, fechaHoraLocal, total, estadoOrden);
                ordenes.add(orden);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener las órdenes: " + e.getMessage());
        }
        return ordenes;
    }
}
