package controlador;

import conexion.ConexionDB;
import modelo.Clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientesDAO {

    ConexionDB conexion = new ConexionDB();

    public void agregar(Clientes cliente) {
        String sql = "INSERT INTO clientes (nombre, telefono, correo) VALUES (?, ?, ?)";
        try (Connection con = conexion.getConnection()) {
            if (con != null && conexion.isConnectionActive(con)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, cliente.getNombre());
                    ps.setString(2, cliente.getTelefono());
                    ps.setString(3, cliente.getCorreo());
                    ps.executeUpdate();
                    System.out.println("Cliente agregado exitosamente.");
                }
            } else {
                System.err.println("La conexión no está activa.");
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
        }
    }

    public void actualizar(Clientes cliente) {
        String sql = "UPDATE clientes SET nombre = ?, telefono = ?, correo = ? WHERE id_cliente = ?";
        try (Connection con = conexion.getConnection()) {
            if (con != null && conexion.isConnectionActive(con)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, cliente.getNombre());
                    ps.setString(2, cliente.getTelefono());
                    ps.setString(3, cliente.getCorreo());
                    ps.setInt(4, cliente.getId());
                    ps.executeUpdate();
                    System.out.println("Cliente actualizado exitosamente.");
                }
            } else {
                System.err.println("La conexión no está activa.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminar(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection con = conexion.getConnection()) {
            if (con != null && conexion.isConnectionActive(con)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idCliente);
                    ps.executeUpdate();
                    System.out.println("Cliente eliminado exitosamente.");
                }
            } else {
                System.err.println("La conexión no está activa.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
