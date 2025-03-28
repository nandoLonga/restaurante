package Vista;

import javax.swing.*;

public class OrdenGUI
{
    private JPanel main;
    private JTable table1;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("CRUD Clientes");
        frame.setContentPane(new OrdenGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setResizable(false);
    }
}
