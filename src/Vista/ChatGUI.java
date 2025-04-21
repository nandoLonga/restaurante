package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChatGUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Connection connection;

    public ChatGUI(Connection connection) {
        this.connection = connection;

        frame = new JFrame("Chat con el Restaurante");
        chatArea = new JTextArea(20, 40);
        inputField = new JTextField(30);
        sendButton = new JButton("Enviar");

        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(sendButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = inputField.getText().trim();
                if (!mensaje.isEmpty()) {
                    chatArea.append("Cliente: " + mensaje + "\n");


                    String respuesta = procesarMensaje(mensaje);
                    chatArea.append("Restaurante: " + respuesta + "\n");


                    inputField.setText("");


                    guardarMensajeEnBaseDeDatos(mensaje, respuesta);
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private String procesarMensaje(String mensaje) {

        if (mensaje.contains("pedido")) {
            return "¿Qué producto deseas pedir?";
        } else if (mensaje.contains("precio")) {
            return "Por favor, elige un producto y te diré el precio.";
        } else if (mensaje.contains("gracias")) {
            return "Gracias por comunicarte, tu pedido será procesado.";
        }
        return "¿En qué puedo ayudarte?";
    }

    private void guardarMensajeEnBaseDeDatos(String mensajeCliente, String respuestaRestaurante) {
        try (Statement stmt = connection.createStatement()) {
            String query = "INSERT INTO mensajes (mensaje_cliente, respuesta_restaurante) VALUES ('" + mensajeCliente + "', '" + respuestaRestaurante + "')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
