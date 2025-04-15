package Controlador;

import Conexion.ConexionDB;
import Modelo.Productos;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductosDAO
{
    ConexionDB conexionDB = new ConexionDB();
    //Agregar
    public void agregar (Productos productos)
    {
        Connection con = conexionDB.getConnection();

        String query = "INSERT INTO productos (nombreP, categoria, precio_u, disponibilidad) VALUES (?,?,?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, productos.getNombreP());
            pst.setString(2, productos.getCategoria());
            pst.setInt(3, productos.getPrecio_u());
            pst.setString(2, productos.getDisponibilidad());


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
    public void actualizar (Productos productos)
    {
        Connection con = conexionDB.getConnection();

        String query = "UPDATE productos SET nombreP = ?, categoria = ?, precio_u = ?, disponibilidad = ? WHERE id_producto = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, productos.getNombreP());
            pst.setString(2, productos.getCategoria());
            pst.setInt(3, productos.getPrecio_u());
            pst.setString(2, productos.getDisponibilidad());
            pst.setInt(4, productos.getId_producto());

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

        String query = "DELETE FROM productos WHERE id_producto = ?";

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
