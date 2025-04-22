package vista;

import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JFrame {

    private JPanel panelMenu;
    private JButton mesasButton;
    private JButton salirButton;
    private JButton chatButton;
    private JButton productosButton;
    private JButton ordenesButton;
    private JButton clientesButton;
    private JButton empleadosButton;

    public MenuGUI() {
        setTitle("Menú Principal - Restaurante");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(8, 1, 10, 10));

        JLabel titulo = new JLabel("Sistema de Gestión de Restaurante", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelMenu.add(titulo);

        mesasButton = new JButton("Gestión de Mesas");
        productosButton = new JButton("Gestión de Productos");
        ordenesButton = new JButton("Gestión de Órdenes");
        chatButton = new JButton("Chat del Restaurante");
        salirButton = new JButton("Salir");
        clientesButton = new JButton("Gestión de Clientes");
        empleadosButton = new JButton("Gestión de Empleados");

        panelMenu.add(mesasButton);
        panelMenu.add(productosButton);
        panelMenu.add(ordenesButton);
        panelMenu.add(chatButton);
        panelMenu.add(clientesButton);
        panelMenu.add(empleadosButton);
        panelMenu.add(salirButton);

        setContentPane(panelMenu);

        mesasButton.addActionListener(e -> {
            JFrame frame = new JFrame("CRUD Mesas");
            frame.setContentPane(new MesasGUI().panel1);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        productosButton.addActionListener(e -> {
            JFrame frame = new JFrame("CRUD Productos");
            frame.setContentPane(new ProductosGUI().panel1);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        ordenesButton.addActionListener(e -> {
            new OrdenGUI().setVisible(true);
        });

        chatButton.addActionListener(e -> {
            String nombreCliente = JOptionPane.showInputDialog(this, "Por favor, ingresa tu nombre:", "Nombre del Cliente", JOptionPane.PLAIN_MESSAGE);
            if (nombreCliente != null && !nombreCliente.trim().isEmpty()) {
                new ChatGUI(nombreCliente.trim());
            } else {
                JOptionPane.showMessageDialog(this, "Debes ingresar un nombre para iniciar el chat.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        clientesButton.addActionListener(e -> {
            JFrame frame = new JFrame("CRUD Clientes");
            frame.setContentPane(new ClientesGUI().main);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        empleadosButton.addActionListener(e -> {
            JFrame frame = new JFrame("CRUD Empleados");
            frame.setContentPane(new EmpleadosGUI().panel1);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        salirButton.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas salir de la aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuGUI().setVisible(true));
    }
}
