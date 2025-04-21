package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatGUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    public ChatGUI(String clienteNombre) {
        frame = new JFrame("Chat con " + clienteNombre);
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
                    chatArea.append("Restaurante: " + mensaje + "\n");
                    inputField.setText("");


                    chatArea.append(clienteNombre + ": Gracias por su mensaje.\n");
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
