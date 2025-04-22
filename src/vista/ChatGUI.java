package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Map.*;

public class ChatGUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private Map<String, Integer> precios;
    private Random random = new Random();
    private Set<Integer> mesasOcupadas = new HashSet<>();
    private final int TOTAL_MESAS = 10;

    public ChatGUI(String clienteNombre) {
        frame = new JFrame("Chat con " + clienteNombre);
        chatArea = new JTextArea(20, 40);
        inputField = new JTextField(30);
        sendButton = new JButton("Enviar");

        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        precios = new HashMap<>();
        precios.put("hamburguesa", 10000);
        precios.put("perro caliente", 8000);
        precios.put("salchipapa", 12000);

        sendButton.addActionListener(e -> enviarMensaje(clienteNombre));
        inputField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enviarMensaje(clienteNombre);
                }
            }
        });
    }

    private void enviarMensaje(String clienteNombre) {
        String mensaje = inputField.getText().trim().toLowerCase();
        if (mensaje.isEmpty()) return;

        chatArea.append("Tú: " + mensaje + "\n");
        inputField.setText("");

        String respuesta = generarRespuesta(mensaje);
        chatArea.append("Restaurante: " + respuesta + "\n");
    }

    private String generarRespuesta(String mensaje) {
        if (mensaje.contains("hola") || mensaje.contains("buenos días") || mensaje.contains("buenas tardes") || mensaje.contains("buenas noches")) {
            return "¡Hola! Bienvenido a tu restaurante, ¿qué deseas?";
        }

        if (mensaje.contains("realizar un pedido") || mensaje.contains("quiero pedir") || mensaje.contains("hacer un pedido")) {
            return "¡Claro que sí! ¿Qué deseas pedir?";
        }

        if (mensaje.contains("deseo pedir") || mensaje.contains("quiero una") || mensaje.contains("quiero un")) {
            for (String producto : precios.keySet()) {
                if (mensaje.contains(producto)) {
                    int cantidad = extraerCantidad(mensaje);
                    int precioUnitario = precios.get(producto);
                    int total = cantidad * precioUnitario;
                    return "Perfecto, has pedido " + cantidad + " " + producto + "(s). Precio unitario: $" + precioUnitario + ", Total: $" + total +
                            ". ¿Deseas pagar en efectivo o por transferencia?";
                }
            }
            return "¿Podrías repetir el producto que deseas?";
        }

        if (mensaje.contains("precio")) {
            StringBuilder respuesta = new StringBuilder("Los precios son:\n");
            precios.forEach((producto, precio) -> respuesta.append("- ").append(producto).append(": $").append(precio).append("\n"));
            return respuesta.toString();
        }

        if (mensaje.contains("efectivo") || mensaje.contains("transferencia")) {
            return "Has escogido pagar por " + mensaje + ". ¿Deseas algo más?";
        }

        if (mensaje.contains("no deseo nada más") || mensaje.contains("eso es todo")) {
            return "¿Deseas enviar el pedido a domicilio o reservar una mesa?";
        }

        if (mensaje.contains("reservar") || mensaje.contains("comer aquí")) {
            return "¿Cuántas personas son para la mesa?";
        }

        if (mensaje.matches(".*(\\d+|uno|una|dos|tres|cuatro|cinco|seis|siete|ocho|nueve|diez).* personas.*")
                || mensaje.matches(".*somos (\\d+|uno|una|dos|tres|cuatro|cinco|seis|siete|ocho|nueve|diez).*")) {

            int personas = extraerCantidad(mensaje);
            int mesa = asignarMesa(personas);

            if (mesa == -1) {
                return "Lo sentimos, no hay mesas disponibles para " + personas + " personas.";
            }

            String fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

            return "✅ Reservación aceptada.\n" +
                    "Mesa número: " + mesa + "\n" +
                    "Cantidad de personas: " + personas + "\n" +
                    "Fecha y hora: " + fechaHora + "\n" +
                    "¿Deseas algo más?";
        }

        if (mensaje.contains("muchas gracias")) {
            return "Muchas gracias por su compra, ¡esperamos que disfrute su pedido! ¡Vuelva pronto!";
        }

        return "Lo siento, no entendí eso. ¿Podrías repetirlo o decirme si deseas pedir, reservar o consultar precios?";
    }

    private int extraerCantidad(String mensaje) {
        mensaje = mensaje.toLowerCase();
        Map<String, Integer> numeros = of(
                "uno", 1, "una", 1,
                "dos", 2,
                "tres", 3,
                "cuatro", 4,
                "cinco", 5,
                "seis", 6,
                "siete", 7,
                "ocho", 8,
                "nueve", 9,
                "diez", 10
        );

        for (String palabra : mensaje.split(" ")) {
            if (numeros.containsKey(palabra)) return numeros.get(palabra);
            try {
                return Integer.parseInt(palabra);
            } catch (NumberFormatException ignored) {}
        }

        return 1;
    }

    private Map<String, Integer> of(String uno, int i, String una, int i1, String dos, int i2, String tres, int i3, String cuatro, int i4, String cinco, int i5, String seis, int i6, String siete, int i7, String ocho, int i8, String nueve, int i9, String diez, int i10) {
        return Map.of();
    }

    private int asignarMesa(int personas) {
        for (int i = 1; i <= TOTAL_MESAS; i++) {
            if (!mesasOcupadas.contains(i)) {

                if (personas <= 4) {
                    mesasOcupadas.add(i);
                    return i;
                }
                if (personas <= 10) {
                    mesasOcupadas.add(i);
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new ChatGUI("Cliente");
    }
}
