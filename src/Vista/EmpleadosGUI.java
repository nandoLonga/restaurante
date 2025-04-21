package vista;

import conexion.ConexionDB;
import controlador.EmpleadosDAO;
import modelo.Empleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpleadosGUI {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    private final EmpleadosDAO empleadosDAO = new EmpleadosDAO();
    private final ConexionDB conexionDB = new ConexionDB();


    public EmpleadosGUI() {
        obtenerDatos();


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField2.getText();
                String cargo = textField3.getText();
                String salarioTexto = textField4.getText();

                if (!nombre.isEmpty() && !cargo.isEmpty() && !salarioTexto.isEmpty()) {
                    try {
                        int salario = Integer.parseInt(salarioTexto);
                        Empleados empleados = new Empleados(nombre, cargo, salario); // No se pasa el ID
                        empleadosDAO.agregar(empleados);
                        obtenerDatos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El salario debe ser un número válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
                }
            }
        });


        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = textField1.getText();
                String nombre = textField2.getText();
                String cargo = textField3.getText();
                String salarioTexto = textField4.getText();

                if (!idTexto.isEmpty() && !nombre.isEmpty() && !cargo.isEmpty() && !salarioTexto.isEmpty()) {
                    try {
                        int id_empleado = Integer.parseInt(idTexto);
                        int salario = Integer.parseInt(salarioTexto);
                        Empleados empleados = new Empleados(id_empleado, nombre, cargo, salario); // Se pasa el ID
                        empleadosDAO.actualizar(empleados);
                        obtenerDatos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID y Salario deben ser numéricos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
                }
            }
        });


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = textField1.getText();
                if (!idTexto.isEmpty()) {
                    try {
                        int id_empleado = Integer.parseInt(idTexto);
                        empleadosDAO.eliminar(id_empleado);
                        obtenerDatos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID inválido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debes ingresar el ID del empleado a eliminar.");
                }
            }
        });
    }


    public void obtenerDatos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Empleado");
        model.addColumn("Nombre");
        model.addColumn("Cargo");
        model.addColumn("Salario");

        table1.setModel(model);
        String[] dato = new String[4];

        try (Connection con = conexionDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM empleados")) {

            while (rs.next()) {
                dato[0] = rs.getString("id_empleado");
                dato[1] = rs.getString("nombre");
                dato[2] = rs.getString("cargo");
                dato[3] = rs.getString("salario");

                model.addRow(dato);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        EmpleadosGUI empleadosGUI = new EmpleadosGUI();
        JFrame frame = new JFrame("CRUD Empleados");

        frame.setContentPane(empleadosGUI.panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
