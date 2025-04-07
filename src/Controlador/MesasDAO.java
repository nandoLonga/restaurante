package controlador;

import conexion.ConexionDB;
import modelo.Mesas;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesasDAO {
    private final ConexionDB conexionDB = new ConexionDB();


    public void agregar(Mesas mesas) {
        String query = "INSERT INTO mesas (id_mesa, numero, capacidad, estado_mesa) VALUES (?, ?, ?, ?)";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, mesas.getId_mesas());
            pst.setString(2, mesas.getNumero());
            pst.setString(3, mesas.getCapacidad());
            pst.setString(4, mesas.getEstado_mesa());

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Mesa agregada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar mesa.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar mesa: " + e.getMessage());
        }
    }

    public void actualizar(Mesas mesas) {
        String query = "UPDATE mesas SET numero = ?, capacidad = ?, estado_mesa = ? WHERE id_mesa = ?";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, mesas.getId_mesas());
            pst.setString(2, mesas.getNumero());
            pst.setString(3, mesas.getCapacidad());
            pst.setString(4, mesas.getEstado_mesa());


            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Mesa actualizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la mesa.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar mesa: " + e.getMessage());
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
