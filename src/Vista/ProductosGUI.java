package Vista;

import Conexion.ConexionDB;
import Controlador.ProductosDAO;
import Modelo.Productos;
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

public class ProductosGUI
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
    private JComboBox comboBox1;

    ProductosDAO productosDAO = new ProductosDAO();

    public ProductosGUI()
    {
        obtenerDatos();
        agregarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField2.getText();
                String categoria = textField3.getText();
                int precio_u = Integer.parseInt(textField4.getText());
                String disponibilidad = comboBox1.getSelectedItem().toString();

                Productos productos = new Productos(1,nombre,categoria,precio_u, disponibilidad);
                productosDAO.agregar(productos);
                obtenerDatos();
            }
        });
        editarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField2.getText();
                String categoria = textField3.getText();
                int precio_u = Integer.parseInt(textField4.getText());
                String disponibilidad = comboBox1.getSelectedItem().toString();
                int id = Integer.parseInt(textField1.getText());

                Productos productos = new Productos(id, nombre, categoria, precio_u, disponibilidad);
                productosDAO.actualizar(productos);
                obtenerDatos();
            }
        });
        eliminarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id = Integer.parseInt(textField1.getText());
                productosDAO.eliminar(id);
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
                    comboBox1.addItem(table1.getValueAt(selectFile, 4));
                }
            }
        });
    }

    ConexionDB conexionDB = new ConexionDB();
    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id Producto");
        model.addColumn("Nombre");
        model.addColumn("Categoria");
        model.addColumn("Precio");
        model.addColumn("Disponibilidad");

        table1.setModel(model);

        String[] dato = new String[5];

        Connection con = conexionDB.getConnection();

        try
        {
            Statement stmt = con. createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos");

            while (rs.next())
            {
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);

                model.addRow(dato);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void ejecutarProducto()
    {
        JFrame frame = new JFrame("CRUD Productos");
        frame.setContentPane(this.main);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setResizable(false);
    }
}
