package vista;

import controlador.OrdenDAO;
import modelo.Orden;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    private OrdenDAO ordenDAO = new OrdenDAO();

    public OrdenGUI() {

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());


        setContentPane(panel1);
        setTitle("Gestión de Órdenes");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        table1 = new JTable();
        panel1.add(new JScrollPane(table1));


        textField1 = new JTextField(20);
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
        textField5 = new JTextField(20);
        textField6 = new JTextField(20);
        textField7 = new JTextField(20);
        agregarButton = new JButton("Agregar");
        editarButton = new JButton("Editar");
        eliminarButton = new JButton("Eliminar");


        panel1.add(textField1);
        panel1.add(textField2);
        panel1.add(textField3);
        panel1.add(textField4);
        panel1.add(textField5);
        panel1.add(textField6);
        panel1.add(textField7);
        panel1.add(agregarButton);
        panel1.add(editarButton);
        panel1.add(eliminarButton);


        SwingUtilities.invokeLater(() -> obtenerDatos());


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idClientes = Integer.parseInt(textField1.getText());
                    int idMesas = Integer.parseInt(textField2.getText());
                    int idEmpleados = Integer.parseInt(textField3.getText());


                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date fechaHora = formato.parse(textField4.getText());

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
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date fechaHora = formato.parse(textField4.getText());
                    int total = Integer.parseInt(textField5.getText());
                    String estado = textField6.getText();

                    Orden orden = new Orden(idOrden, idClientes, idMesas, idEmpleados, fechaHora, total, estado);
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


        table1.setModel(modelo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrdenGUI().setVisible(true));
    }
}
