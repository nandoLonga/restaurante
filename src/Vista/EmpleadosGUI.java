package Vista;

import Conexion.ConexionDB;
import Controlador.EmpleadosDAO;
import Modelo.Empleados;
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

public class EmpleadosGUI
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

    EmpleadosDAO empleadosDAO = new EmpleadosDAO();

    public EmpleadosGUI()
    {
        obtenerDatos();
        agregarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField2.getText();
                String cargo = textField3.getText();
                int salario = Integer.parseInt(textField4.getText());

                Empleados empleados = new Empleados(1,nombre,cargo,salario);
                empleadosDAO.agregar(empleados);
                obtenerDatos();
            }
        });
        editarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField2.getText();
                String cargo = textField3.getText();
                int salario = Integer.parseInt(textField4.getText());
                int id = Integer.parseInt(textField1.getText());

                Empleados empleados = new Empleados(id, nombre, cargo, salario);
                empleadosDAO.actualizar(empleados);
                obtenerDatos();
            }
        });
        eliminarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id = Integer.parseInt(textField1.getText());
                empleadosDAO.eliminar(id);

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
                    textField4.setText((String) table1.getValueAt(selectFile, 3));
                }
            }
        });
    }

    ConexionDB conexionDB = new ConexionDB();

    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id Empleado");
        model.addColumn("Nombre");
        model.addColumn("Cargo");
        model.addColumn("Salario");

        table1.setModel(model);

        String[] dato = new String[4];

        Connection con = conexionDB.getConnection();

        try
        {
            Statement stmt = con. createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");

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

    public void ejecutarEmpleado ()
    {
        JFrame frame = new JFrame("CRUD Empleados");
        frame.setContentPane(this.main);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setResizable(false);
    }
}
