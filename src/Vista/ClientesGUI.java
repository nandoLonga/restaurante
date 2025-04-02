package Vista;

import Conexion.ConexionDB;
import Controlador.ClientesDAO;
import Modelo.Clientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientesGUI
{
    private JPanel main;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    ClientesDAO clientesDAO = new ClientesDAO();

    public ClientesGUI()
    {
        obtenerDatos();
        agregarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                agregar();
                obtenerDatos();
            }
        });
        editarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                editar();
                obtenerDatos();
            }
        });
        eliminarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                obtenerDatos();
            }
        });
    }

    ConexionDB conexionDB = new ConexionDB();

    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id Cliente");
        model.addColumn("Nombre");
        model.addColumn("Telefono");
        model.addColumn("correo");

        table1.setModel(model);

        String[] dato = new String[4];

        Connection con = conexionDB.getConnection();

        try
        {
            Statement stmt = con. createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM clientes");

            while (rs.next())
            {
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);

                model.addRow(dato);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void agregar()
    {
        String nombre = textField2.getText();
        String telefono = textField3.getText();
        String correo = textField4.getText();

        Clientes clientes = new Clientes(0,nombre,telefono,correo);

        if (clientesDAO.agregar(clientes))
        {

            JOptionPane.showMessageDialog(null, "Agregado con Exito");
        }

        else
        {
            JOptionPane.showMessageDialog(null,"No se agrego con Exito");
        }

    }

    public void editar()
    {
        int IdCliente = Integer.parseInt(textField1.getText());
        String nombre = textField2.getText();
        String telefono = textField3.getText();
        String correo = textField4.getText();

        Clientes clientes = new Clientes(IdCliente,nombre,telefono,correo);
        clientesDAO.actualizar(clientes);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("CRUD Clientes");
        frame.setContentPane(new ClientesGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setResizable(false);
    }
}
