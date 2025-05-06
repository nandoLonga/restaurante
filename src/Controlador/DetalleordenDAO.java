package Controlador;

import Conexion.ConexionDB;
import Modelo.Clientes;
import Modelo.Detalleorden;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetalleordenDAO
{
    ConexionDB conexionDB = new ConexionDB();
    //Agregar
    public void agregar (Detalleorden detalleorden)
    {
        Connection con = conexionDB.getConnection();

        String query = "INSERT INTO detalleorden (id_orden, id_producto, cantidad) VALUES (?,?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            System.out.println("Datos: "+detalleorden.getId_orden()+" - "+detalleorden.getId_producto()+" - "+detalleorden.getCantidad());
            pst.setInt(1, detalleorden.getId_orden());
            pst.setInt(2, detalleorden.getId_producto());
            pst.setInt(3, detalleorden.getCantidad());


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
    public void actualizar (Detalleorden detalleorden)
    {
        Connection con = conexionDB.getConnection();

        String query = "UPDATE detalleorden SET id_orden = ?, id_producto = ?, cantidad = ? WHERE id_detalle = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, detalleorden.getId_orden());
            pst.setInt(2, detalleorden.getId_producto());
            pst.setInt(3, detalleorden.getCantidad());
            pst.setInt(4, detalleorden.getId_detalle());

            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Registro actualizado con exito.");
            } else
            {
                JOptionPane.showMessageDialog(null, "Registro NO actualizado con exito.");
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

        String query = "DELETE FROM detalleorden WHERE id_detalle = ?";

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
