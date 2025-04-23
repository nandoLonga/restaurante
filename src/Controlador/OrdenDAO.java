package Controlador;

import Conexion.ConexionDB;
import Modelo.Orden;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdenDAO
{
    ConexionDB conexionDB = new ConexionDB();
    //Agregar
    public void agregar (Orden orden)
    {
        Connection con = conexionDB.getConnection();

        String query = "INSERT INTO orden (total_orden, estado_orden, id_cliente, id_empleado, id_mesa, fecha_orden) VALUES (?,?,?,?,?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, orden.getTotal_orden());
            pst.setString(2, orden.getEstado_orden());
            pst.setInt(3, orden.getId_cliente());
            pst.setInt(4, orden.getId_empleado());
            pst.setInt(5, orden.getId_mesa());
            pst.setString(6, orden.getFecha_orden());


            int resultado = pst.executeUpdate();

            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Registro agregado con exito.");
            } else
            {
                JOptionPane.showMessageDialog(null, "Registro NO agreado con exito.");
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecucion");
        }
    }

    public void actualizar (Orden orden)
    {
        Connection con = conexionDB.getConnection();

        String query = "UPDATE orden SET total_orden = ?, estado_orden = ?, id_cliente = ?, id_empleado = ?, id_mesa = ?, fecha_orden = ? WHERE id_orden = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, orden.getTotal_orden());
            pst.setString(2, orden.getEstado_orden());
            pst.setInt(3, orden.getId_cliente());
            pst.setInt(4, orden.getId_empleado());
            pst.setInt(5, orden.getId_mesa());
            pst.setString(6, orden.getFecha_orden());
            pst.setInt(7, orden.getId_orden());

            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Registro actualizado con exito.");
            } else
            {
                JOptionPane.showMessageDialog(null, "Regostro NO actualizado con exito.");
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecucion");
        }
    }
    public void eliminar (int id)
    {
        Connection con = conexionDB.getConnection();

        String query = "DELETE FROM orden WHERE id_orden = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1,id);

            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Registro eliminado con exito.");
            } else
            {
                JOptionPane.showMessageDialog(null, "Regostro NO eliminado con exito.");
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecucion");
        }
    }
}

