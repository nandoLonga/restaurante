package vista;

import modelo.Clientes;
import controlador.ClientesDAO;
import conexion.ConexionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientesGUI {
    private JPanel main;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JButton chatButton;
    private JLabel Cliente;
    private JLabel nombre;

    private ClientesDAO clientesDAO = new ClientesDAO();
    private ConexionDB conexionDB = new ConexionDB();

    public ClientesGUI() {
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField2.getText();
                String telefono = textField3.getText();
                String correo = textField4.getText();

                if (!nombre.isEmpty() && !telefono.isEmpty() && !correo.isEmpty()) {
                    Clientes cliente = new Clientes(0, nombre, telefono, correo);
                    clientesDAO.agregar(cliente);
                    obtenerDatos();
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id_clientes = Integer.parseInt(textField1.getText());
                    String nombre = textField2.getText();
                    String telefono = textField3.getText();
                    String correo = textField4.getText();

                    if (!nombre.isEmpty() && !telefono.isEmpty() && !correo.isEmpty()) {
                        Clientes cliente = new Clientes(id_clientes, nombre, telefono, correo);
                        clientesDAO.actualizar(cliente);
                        obtenerDatos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido para editar.");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textField1.getText());
                    clientesDAO.eliminar(id);
                    obtenerDatos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido para eliminar.");
                }
            }
        });

        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChatGUI(conexionDB.getConnection());
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectFile = table1.getSelectedRow();
                if (selectFile >= 0) {
                    textField1.setText(table1.getValueAt(selectFile, 0).toString());
                    textField2.setText(table1.getValueAt(selectFile, 1).toString());
                    textField3.setText(table1.getValueAt(selectFile, 2).toString());
                    textField4.setText(table1.getValueAt(selectFile, 3).toString());
                }
            }
        });

        obtenerDatos();
    }

    public void obtenerDatos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Cliente");
        model.addColumn("Nombre");
        model.addColumn("Teléfono");
        model.addColumn("Correo");

        table1.setModel(model);

        Connection con = conexionDB.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.");
            return;
        }

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            while (rs.next()) {
                model.addRow(new String[] {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CRUD Clientes");
        frame.setContentPane(new ClientesGUI().main);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
    }

    public JLabel getClientes() {
        return Cliente;
    }

    public void setClientes(JLabel cliente) {
        Cliente = cliente;
    }

    public JLabel getCliente() {
        return Cliente;
    }

    public void setCliente(JLabel cliente) {
        Cliente = cliente;
    }
}
