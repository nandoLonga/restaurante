package controlador;

import conexion.ConexionDB;
import modelo.Empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpleadosDAO {

    ConexionDB conexion = new ConexionDB();


    public void agregar(Empleados empleado) {
        String sql = "INSERT INTO empleados (nombre, cargo, salario) VALUES (?, ?, ?)";

        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getCargo());
            ps.setInt(3, empleado.getSalario());
            ps.executeUpdate();
            System.out.println("Empleado agregado exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al agregar empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void actualizar(Empleados empleado) {
        String sql = "UPDATE empleados SET nombre = ?, cargo = ?, salario = ? WHERE id_empleado = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getCargo());
            ps.setInt(3, empleado.getSalario());
            ps.setInt(4, empleado.getId_empleado());
            ps.executeUpdate();
            System.out.println("Empleado actualizado exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void eliminar(int idEmpleado) {
        String sql = "DELETE FROM empleados WHERE id_empleado = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Empleado con ID " + idEmpleado + " eliminado exitosamente");
            } else {
                System.out.println("No se encontró el empleado con ID " + idEmpleado);
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
