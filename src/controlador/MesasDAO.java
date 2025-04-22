package controlador;

import conexion.ConexionDB;
import modelo.Mesas;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MesasDAO {
    private final ConexionDB conexionDB = new ConexionDB();

    public void agregar(Mesas mesa) {
        String query = "INSERT INTO mesas (numero, capacidad, estado_mesa) VALUES (?, ?, ?)";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, mesa.getNumero());
            pst.setString(2, mesa.getCapacidad());
            pst.setString(3, mesa.getEstado_mesa());

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Mesa agregada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar la mesa.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar la mesa: " + e.getMessage());
        }
    }

    public void actualizar(Mesas mesa) {
        String query = "UPDATE mesas SET numero = ?, capacidad = ?, estado_mesa = ? WHERE id_mesa = ?";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, mesa.getNumero());
            pst.setString(2, mesa.getCapacidad());
            pst.setString(3, mesa.getEstado_mesa());
            pst.setInt(4, mesa.getId_mesas());

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Mesa actualizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la mesa.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar la mesa: " + e.getMessage());
        }
    }

    public void eliminar(int id_mesa) {
        String query = "DELETE FROM mesas WHERE id_mesa = ?";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id_mesa);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Mesa eliminada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la mesa.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar la mesa: " + e.getMessage());
        }
    }

    public List<Mesas> obtenerTodos() {
        List<Mesas> listaMesas = new ArrayList<>();
        String query = "SELECT * FROM mesas";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Mesas mesa = new Mesas(
                        rs.getInt("id_mesa"),
                        rs.getString("numero"),
                        rs.getString("capacidad"),
                        rs.getString("estado_mesa")
                );
                listaMesas.add(mesa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener las mesas: " + e.getMessage());
        }

        return listaMesas;
    }
}
