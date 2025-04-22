package vista;

import controlador.MesasDAO;
import modelo.Mesas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MesasGUI {
    public JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    private final MesasDAO mesasDAO = new MesasDAO();

    public MesasGUI() {
        initializeUIComponents();
    }

    private void initializeUIComponents() {
        obtenerDatos();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numero = textField2.getText();
                String capacidad = textField3.getText();
                String estado = textField4.getText();

                if (!numero.isEmpty() && !capacidad.isEmpty() && !estado.isEmpty()) {
                    Mesas mesa = new Mesas(0, numero, capacidad, estado);
                    mesasDAO.agregar(mesa);
                    limpiarCampos();
                    obtenerDatos();
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = textField1.getText();
                String numero = textField2.getText();
                String capacidad = textField3.getText();
                String estado = textField4.getText();

                if (!idTexto.isEmpty() && !numero.isEmpty() && !capacidad.isEmpty() && !estado.isEmpty()) {
                    try {
                        int id_mesa = Integer.parseInt(idTexto);
                        Mesas mesa = new Mesas(id_mesa, numero, capacidad, estado);
                        mesasDAO.actualizar(mesa);
                        limpiarCampos();
                        obtenerDatos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un número.");
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
                        int id_mesa = Integer.parseInt(idTexto);
                        mesasDAO.eliminar(id_mesa);
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
                int selectedRow = table1.getSelectedRow();
                textField1.setText(table1.getValueAt(selectedRow, 0).toString());
                textField2.setText(table1.getValueAt(selectedRow, 1).toString());
                textField3.setText(table1.getValueAt(selectedRow, 2).toString());
                textField4.setText(table1.getValueAt(selectedRow, 3).toString());
            }
        });
    }

    private void obtenerDatos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Mesa");
        model.addColumn("Número");
        model.addColumn("Capacidad");
        model.addColumn("Estado");

        for (Mesas mesa : mesasDAO.obtenerTodos()) {
            Object[] fila = {
                    mesa.getId_mesas(),
                    mesa.getNumero(),
                    mesa.getCapacidad(),
                    mesa.getEstado_mesa()
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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CRUD Mesas");
        frame.setContentPane(new MesasGUI().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
