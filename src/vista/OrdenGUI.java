package vista;

import controlador.OrdenDAO;
import modelo.Orden;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrdenGUI extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    private OrdenDAO ordenDAO = new OrdenDAO();

    public OrdenGUI() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(9, 2, 10, 10));

        setContentPane(panel1);
        setTitle("Gestión de Órdenes");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JLabel titulo = new JLabel("Gestión de Órdenes", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel1.add(titulo);
        panel1.add(new JLabel());


        textField1 = new JTextField(20);
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
        textField5 = new JTextField(20);
        textField6 = new JTextField(20);
        textField7 = new JTextField(20);

        panel1.add(new JLabel("ID Cliente:"));
        panel1.add(textField1);
        panel1.add(new JLabel("ID Mesa:"));
        panel1.add(textField2);
        panel1.add(new JLabel("ID Empleado:"));
        panel1.add(textField3);
        panel1.add(new JLabel("Fecha y Hora (yyyy-MM-dd HH:mm:ss):"));
        panel1.add(textField4);
        panel1.add(new JLabel("Total:"));
        panel1.add(textField5);
        panel1.add(new JLabel("Estado:"));
        panel1.add(textField6);
        panel1.add(new JLabel("ID Orden (para editar/eliminar):"));
        panel1.add(textField7);


        agregarButton = new JButton("Agregar");
        editarButton = new JButton("Editar");
        eliminarButton = new JButton("Eliminar");

        panel1.add(agregarButton);
        panel1.add(editarButton);
        panel1.add(eliminarButton);


        table1 = new JTable();
        JScrollPane scrollPane = new JScrollPane(table1);
        panel1.add(scrollPane);
        panel1.add(new JLabel());


        SwingUtilities.invokeLater(() -> obtenerDatos());


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idClientes = Integer.parseInt(textField1.getText());
                    int idMesas = Integer.parseInt(textField2.getText());
                    int idEmpleados = Integer.parseInt(textField3.getText());


                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime fechaHora = LocalDateTime.parse(textField4.getText(), formatter);

                    int total = Integer.parseInt(textField5.getText());
                    String estado = textField6.getText();

                    Orden orden = new Orden(idClientes, idMesas, idEmpleados, fechaHora, total, estado);
                    ordenDAO.agregar(orden);
                    obtenerDatos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar: " + ex.getMessage());
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idOrden = Integer.parseInt(textField7.getText());
                    int idClientes = Integer.parseInt(textField1.getText());
                    int idMesas = Integer.parseInt(textField2.getText());
                    int idEmpleados = Integer.parseInt(textField3.getText());


                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime fechaHora = LocalDateTime.parse(textField4.getText(), formatter);

                    int total = Integer.parseInt(textField5.getText());
                    String estado = textField6.getText();

                    Orden orden = new Orden(idOrden, idClientes, idMesas, fechaHora, total, estado);
                    ordenDAO.actualizar(orden);
                    obtenerDatos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al editar: " + ex.getMessage());
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textField7.getText());
                    ordenDAO.eliminar(id);
                    obtenerDatos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                }
            }
        });
    }

    private void obtenerDatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"ID", "Cliente", "Mesa", "Empleado", "Fecha", "Total", "Estado"});

        List<Orden> ordenes = ordenDAO.obtenerTodos();
        for (Orden orden : ordenes) {
            modelo.addRow(new Object[]{
                    orden.getId_orden(),
                    orden.getId_clientes(),
                    orden.getId_mesas(),
                    orden.getId_empleados(),
                    orden.getFecha_hora(),
                    orden.getTotal(),
                    orden.getEstado_orden()
            });
        }

        table1.setModel(modelo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrdenGUI().setVisible(true));
    }
}
