package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI
{
    private JPanel main;
    private JButton añadirEmpleadoButton;
    private JButton tomarOrdenButton;
    private JButton añadirMesaButton;
    private JButton añadirProductoButton;
    private JButton añadirClienteButton;
    private JButton chatButton;
    private JButton reportesButton;

    public MenuGUI() {
        tomarOrdenButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //OrdenGUI ordenGUI = new OrdenGUI();
                //ordenGUI.ejecutar();
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("CRUD Menu");
        frame.setContentPane(new MenuGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setResizable(false);
    }


}
