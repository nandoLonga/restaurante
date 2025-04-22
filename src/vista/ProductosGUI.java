package vista;

import controlador.ProductosDAO;
import modelo.Productos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductosGUI {
    public JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    private final ProductosDAO productosDAO = new ProductosDAO();

    public ProductosGUI() {
        initializeUIComponents();
    }

    private void initializeUIComponents() {
        obtenerDatos();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField2.getText();
                String categoria = textField3.getText();
                String precioStr = textField4.getText();
                String disponibilidad = textField5.getText();

                if (!nombre.isEmpty() && !categoria.isEmpty() && !precioStr.isEmpty() && !disponibilidad.isEmpty()) {
                    try {
                        int precio = Integer.parseInt(precioStr);
                        Productos producto = new Productos(0, nombre, categoria, precio, disponibilidad);
                        productosDAO.agregar(producto);
                        limpiarCampos();
                        obtenerDatos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El precio debe ser un número.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = textField1.getText();
                String nombre = textField2.getText();
                String categoria = textField3.getText();
                String precioStr = textField4.getText();
                String disponibilidad = textField5.getText();

                if (!idTexto.isEmpty() && !nombre.isEmpty() && !categoria.isEmpty() && !precioStr.isEmpty() && !disponibilidad.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idTexto);
                        int precio = Integer.parseInt(precioStr);
                        Productos producto = new Productos(id, nombre, categoria, precio, disponibilidad);
                        productosDAO.actualizar(producto);
                        limpiarCampos();
                        obtenerDatos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID y Precio deben ser números.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = textField1.getText();
                if (!idTexto.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idTexto);
                        productosDAO.eliminar(id);
                        limpiarCampos();
                        obtenerDatos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un número.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El campo de ID está vacío.");
                }
            }
        });

        table1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table1.getSelectedRow() != -1) {
                int fila = table1.getSelectedRow();
                textField1.setText(table1.getValueAt(fila, 0).toString());
                textField2.setText(table1.getValueAt(fila, 1).toString());
                textField3.setText(table1.getValueAt(fila, 2).toString());
                textField4.setText(table1.getValueAt(fila, 3).toString());
                textField5.setText(table1.getValueAt(fila, 4).toString());
            }
        });
    }

    private void obtenerDatos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Producto");
        model.addColumn("Nombre");
        model.addColumn("Categoría");
        model.addColumn("Precio");
        model.addColumn("Disponibilidad");

        for (Productos producto : productosDAO.obtenerTodos()) {
            Object[] fila = {
                    producto.getId_producto(),
                    producto.getNombre(),
                    producto.getCategoria(),
                    producto.getPrecio_u(),
                    producto.getDisponibilidad()
            };
            model.addRow(fila);
        }

        table1.setModel(model);
    }

    private void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CRUD Productos");
        frame.setContentPane(new ProductosGUI().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
