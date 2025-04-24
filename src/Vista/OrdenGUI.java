package Vista;

import Conexion.ConexionDB;
import Controlador.DetalleordenDAO;
import Controlador.OrdenDAO;
import Modelo.Detalleorden;
import Modelo.Orden;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class OrdenGUI
{
    private final HashMap<String, Integer> clienteMap = new HashMap<>();
    private final HashMap<String, Integer> empleadoMap = new HashMap<>();
    private final HashMap<String, Integer> mesaMap = new HashMap<>();
    private final HashMap<String, Integer> productoMap = new HashMap<>();
    private JPanel main;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JTextField textField2;
    private JTextField textField3;
    private JButton agregarButton1;
    private JButton editarButton1;
    private JButton eliminarButton1;
    private JButton añadirButton;
    private JButton actualizarButton;
    private JButton borrarButton;
    private JTable table1;
    private JTable table2;
    private JTextField textField4;
    private JLabel valor;
    int idOrden = 0;

    OrdenDAO ordenDAO = new OrdenDAO();
    DetalleordenDAO detalleordenDAO = new DetalleordenDAO();
    MesasGUI mesasGUI = new MesasGUI();

    public OrdenGUI()
    {
        mostrarcliente();
        mostrarmesa();
        mostrarempleado();
        mostrarproductos();
        obtenerDatos();
        obtenerDatos2();
        cargarEstados();
        obtenerIdOrden();
        agregarButton1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombreCliente = (String) comboBox1.getSelectedItem();
                String nombreEmpleado = (String) comboBox3.getSelectedItem();
                String nombreMesa = (String) comboBox2.getSelectedItem();
                String estado_orden = (String) comboBox4.getSelectedItem();
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String fecha_orden = now.format(formatter);

                int id_cliente = clienteMap.getOrDefault(nombreCliente, -1);
                int id_empleado = empleadoMap.getOrDefault(nombreEmpleado, -1);
                int id_mesa = mesaMap.getOrDefault(nombreMesa, -1);

                Orden orden = new Orden(1,0,estado_orden,id_cliente,id_empleado,id_mesa, fecha_orden);
                ordenDAO.agregar(orden);
                obtenerDatos();
                cargarEstados();

            }
        });
        editarButton1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                String estado_orden = (String) comboBox4.getSelectedItem();
                String nombreCliente = (String) comboBox1.getSelectedItem();
                String nombreEmpleado = (String) comboBox3.getSelectedItem();
                String nombreMesa = (String) comboBox2.getSelectedItem();
                String fecha_orden = textField4.getText();
                int id_orden = Integer.parseInt(textField1.getText());


                int id_cliente = clienteMap.getOrDefault(nombreCliente, -1);
                int id_empleado = empleadoMap.getOrDefault(nombreEmpleado, -1);
                int id_mesa = mesaMap.getOrDefault(nombreMesa, -1);

                Orden orden = new Orden(id_orden, 0,estado_orden, id_cliente, id_empleado, id_mesa, fecha_orden);
                ordenDAO.actualizar(orden);
                obtenerDatos();
                cargarEstados();

            }
        });
        eliminarButton1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                int id = Integer.parseInt(textField1.getText());
                ordenDAO.eliminar(id);
                obtenerDatos();
                cargarEstados();
            }
        });

        table2.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);

                int selectFile = table2.getSelectedRow();

                if (selectFile >= 0)
                {
                    textField1.setText((String) table2.getValueAt(selectFile, 0));
                    comboBox1.addItem(table2.getValueAt(selectFile, 1));
                    comboBox2.addItem(table2.getValueAt(selectFile, 2));
                    comboBox3.addItem(table2.getValueAt(selectFile, 3));
                    comboBox4.addItem(table2.getValueAt(selectFile, 4));
                }
            }
        });
        //Tabla de detalle
        añadirButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String idOrden = (String) comboBox5.getSelectedItem();
                String producto = (String) comboBox6.getSelectedItem();
                int cantidad = Integer.parseInt(textField2.getText());

                int id_orden = clienteMap.getOrDefault(idOrden, -1);
                int id_producto = empleadoMap.getOrDefault(producto, -1);
                Detalleorden detalleorden = new Detalleorden(1, id_orden, id_producto, cantidad);
                detalleordenDAO.agregar(detalleorden);

                //double precio = precio_u * cantidad;
                //valor.setText(String.valueOf(precio));
                obtenerDatos2();
            }
        });
        actualizarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                obtenerDatos2();
            }
        });
        borrarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id = Integer.parseInt(textField3.getText());
                detalleordenDAO.eliminar(id);
                obtenerDatos2();
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
                    textField3.setText((String) table1.getValueAt(selectFile, 0));
                    comboBox5.addItem(table1.getValueAt(selectFile, 1));
                    comboBox6.addItem(table1.getValueAt(selectFile, 2));
                    textField2.setText((String) table1.getValueAt(selectFile, 3));
                }
            }
        });
    }

    ConexionDB conexionDB = new ConexionDB();
    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id Orden");
        model.addColumn("Total");
        model.addColumn("Estado");
        model.addColumn("Fecha");
        model.addColumn("Id Empleada");
        model.addColumn("Id Mesa");
        model.addColumn("Id Cliente");

        table2.setModel(model);

        String[] dato = new String[7];

        Connection con = conexionDB.getConnection();

        try
        {
            Statement stmt = con. createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orden");

            while (rs.next())
            {
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                dato[5] = rs.getString(6);
                dato[6] = rs.getString(7);


                model.addRow(dato);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void obtenerDatos2()
    {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id Detalle");
        model.addColumn("Id Orden");
        model.addColumn("Id Producto");
        model.addColumn("Id Cantidad");

        table1.setModel(model);

        String[] dato = new String[4];

        Connection con = conexionDB.getConnection();

        try
        {
            Statement stmt = con. createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM detalleorden");

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

    public void mostrarcliente()
    {
        comboBox1.removeAllItems();
        Connection con = conexionDB.getConnection();
        String query = "SELECT id_cliente, nombre FROM clientes";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                int id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                clienteMap.put(nombre, id);
                comboBox1.addItem(nombre);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "error no está el usuario");
        }
    }

    public void mostrarmesa()
    {
        comboBox2.removeAllItems();
        Connection con = conexionDB.getConnection();

        String query = "SELECT id_mesa, numero FROM mesas WHERE estado_mesa = 'disponible'" ;

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                int id = rs.getInt("id_mesa");
                String nummesa = rs.getString("numero");
                mesaMap.put(nummesa, id);
                comboBox2.addItem(nummesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error no está la mesa");
        }
    }

    public void mostrarempleado()
    {
        comboBox3.removeAllItems();
        Connection con = conexionDB.getConnection();
        String query = "SELECT id_empleado, nombre FROM empleados WHERE  cargo = 'Mesero'";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                int id = rs.getInt("id_empleado");
                String nombre = rs.getString("nombre");
                empleadoMap.put(nombre, id);
                comboBox3.addItem(nombre);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "error no está el empleado");
        }
    }

    public void mostrarproductos()
    {
        comboBox6.removeAllItems();
        Connection con = conexionDB.getConnection();
        String query = "SELECT nombre FROM productos WHERE disponibilidad = 'Si'";
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                String nombre = rs.getString("nombre");
                comboBox6.addItem(nombre);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "error no está el producto");

        }
    }
    private void cargarEstados()
    {
        comboBox4.removeAllItems();
        comboBox4.addItem("En preparación");
        comboBox4.addItem("Servida");
        comboBox4.addItem("Pagada");
    }
    public boolean isMesaDisponible(int idMesa)
    {
        Connection con = conexionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT estado_mesa FROM mesas WHERE id_mesa = " + idMesa);
            if (rs.next()) {
                String estado = rs.getString("estado");
                return estado.equalsIgnoreCase("disponible");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public int obtenerIdOrden()
    {

        Connection con = conexionDB.getConnection();
        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_orden) FROM orden");
            if (rs.next())
            {
                idOrden = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return idOrden;
    }

    public void ejecutarOrden()
    {
        JFrame frame = new JFrame("CRUD Orden");
        frame.setContentPane(this.main);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1200,600);
        frame.setResizable(false);
    }
}
