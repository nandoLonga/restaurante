package Vista;

import Conexion.ConexionDB;
import Controlador.MesasDAO;
import Modelo.Clientes;
import Modelo.Mesas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MesasGUI
{
    private JPanel main;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JComboBox comboBox1;

    MesasDAO mesasDAO = new MesasDAO();

    public MesasGUI()
    {
        obtenerDatos();
        agregarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String numero = textField2.getText();
                String capacidad = textField3.getText();
                String estado_mesa = comboBox1.getSelectedItem().toString();

                Mesas mesas = new Mesas(1,numero,capacidad,estado_mesa);
                mesasDAO.agregar(mesas);
                obtenerDatos();
            }
        });
        editarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String numero = textField2.getText();
                String capacidad = textField3.getText();
                String estado_mesa = comboBox1.getSelectedItem().toString();
                int id = Integer.parseInt(textField1.getText());

                Mesas mesas = new Mesas(id, numero, capacidad, estado_mesa);
                mesasDAO.actualizar(mesas);
                obtenerDatos();
            }
        });
        eliminarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id = Integer.parseInt(textField1.getText());
                mesasDAO.eliminar(id);
                obtenerDatos();
            }
        });
        table1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);

                int selectFile = table1.getSelectedRow();

                if (selectFile >= 0)
                {
                    textField1.setText((String) table1.getValueAt(selectFile, 0));
                    textField2.setText((String) table1.getValueAt(selectFile, 1));
                    textField3.setText((String) table1.getValueAt(selectFile, 2));
                    comboBox1.addItem(table1.getValueAt(selectFile, 3));
                }
            }
        });
    }

    ConexionDB conexionDB = new ConexionDB();

    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id Mesas");
        model.addColumn("Numero de Masa");
        model.addColumn("Capacidad");
        model.addColumn("Estado de Mesa");

        table1.setModel(model);

        String[] dato = new String[4];

        Connection con = conexionDB.getConnection();

        try
        {
            Statement stmt = con. createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mesas");

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

    public void ejecutarMesas ()
    {
        JFrame frame = new JFrame("CRUD Mesas");
        frame.setContentPane(this.main);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setResizable(false);
    }
}
