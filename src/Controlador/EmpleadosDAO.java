package controlador;

import modelo.Empleados;
import conexion.ConexionDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosDAO {
    private final ConexionDB conexionDB = new ConexionDB();


    public void agregar(Empleados empleado) {
        String query = "INSERT INTO empleados (id_empleado, nombre, cargo, salario) VALUES (?, ?, ?, ?)";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, empleado.getId_empleado());
            pst.setString(2, empleado.getNombre());
            pst.setString(3, empleado.getCargo());
            pst.setInt(4, empleado.getSalario());

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Empleado agregado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar empleado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar empleado: " + e.getMessage());
        }
    }


    public void actualizar(Empleados empleado) {
        String query = "UPDATE empleados SET nombre = ?, cargo = ?, salario = ? WHERE id = ?";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, empleado.getId_empleado());
            pst.setString(2, empleado.getNombre());
            pst.setString(3, empleado.getCargo());
            pst.setInt(4, empleado.getSalario());


            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Empleado actualizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el empleado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar empleado: " + e.getMessage());
        }
    }


    public void eliminar(int id_empleado) {
        String query = "DELETE FROM empleados WHERE id_empleado = ?";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id_empleado);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el empleado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar empleado: " + e.getMessage());
        }
    }


    public List<Empleados> obtenerTodos() {
        List<Empleados> listaEmpleados = new ArrayList<>();
        String query = "SELECT * FROM empleados";

        try (Connection con = conexionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Empleados empleado = new Empleados(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("cargo"),
                        rs.getInt("salario")
                );
                listaEmpleados.add(empleado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener empleados: " + e.getMessage());
        }

        return listaEmpleados;
    }
}
