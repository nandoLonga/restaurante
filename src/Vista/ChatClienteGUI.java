package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClienteGUI
{
    private JTextArea textArea1;
    private JTextField textField1;
    private JButton enviarButton;
    private JPanel main;

    public ChatClienteGUI()
    {

    }

    public static void main(String[] args)
    {
        String serverAdress = JOptionPane.showInputDialog("Ingrese la IP del servidor (localhost si  es local)");
        if(serverAdress == null || serverAdress.isEmpty()) serverAdress = "localhost";

        try(Socket socket =new Socket(serverAdress, 12345)){
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out  = new PrintWriter(socket.getOutputStream(), true);
            String sendMessage, receivedMessage;


            do{
                sendMessage = JOptionPane.showInputDialog("escribe tu mensaje");
                if(sendMessage == null || sendMessage.equalsIgnoreCase("salir"))
                {
                    out.println("salir");
                    break;

                }

                out.println(sendMessage);

                receivedMessage = in.readLine();

                if(receivedMessage == null || receivedMessage.equalsIgnoreCase("salir"))
                {
                    JOptionPane.showMessageDialog(null, "el servidor ha cerrado la conexion");
                    break;

                }
                JOptionPane.showMessageDialog(null, " servidor dice"+ receivedMessage);

            }while (true);

        }catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error al cliente " + e.getMessage());

        }

    }
    public void ejecutar()
    {
        JFrame frame = new JFrame("Servidor de Chat");
        frame.setContentPane(this.main);
        frame.pack();
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

