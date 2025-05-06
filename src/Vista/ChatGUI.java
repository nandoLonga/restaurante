package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatGUI
{

    private JTextArea textArea1;
    private JTextField textField1;
    private JButton enviarButton;
    private JPanel main;

    public ChatGUI()
    {

    }

    public static void main(String[] args)
    {


        try(ServerSocket serverSocket = new ServerSocket(12345))
        {
            JOptionPane.showMessageDialog(null, "servidor iniciado  conexion..");
            Socket clientesocket = serverSocket.accept();
            JOptionPane.showMessageDialog(null, "Cliente conectado");

            BufferedReader in  = new BufferedReader(new InputStreamReader(clientesocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientesocket.getOutputStream(), true);

            String receivedMessage, sendMessage;

            do
            {

                receivedMessage = in.readLine();
                if(receivedMessage == null || receivedMessage.equalsIgnoreCase("salir"))
                {
                    JOptionPane.showMessageDialog(null, "cliente ha salido del chat");
                    break;

                }
                JOptionPane.showMessageDialog(null, " cliente dice " + receivedMessage);

                sendMessage = JOptionPane.showInputDialog("Escribe tu mensaje ");
                if(sendMessage == null || sendMessage.equalsIgnoreCase("salir"))
                {
                    out.println("salir");
                    break;

                }

                out.println(sendMessage);
            }while (true);

            clientesocket.close();
            serverSocket.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "error al servidor " + e.getMessage());

        }
    }
    public void ejecutar1()
    {
        JFrame frame = new JFrame("Servidor de Chat");
        frame.setContentPane(this.main);
        frame.pack();
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
