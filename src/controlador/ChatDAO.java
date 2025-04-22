package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChatDAO {

    private Connection connection;

    public ChatDAO(Connection connection) {
        this.connection = connection;
    }

    public void guardarMensaje(String mensajeCliente, String respuestaRestaurante) {
        try (Statement stmt = connection.createStatement()) {
            String query = "INSERT INTO mensajes (mensaje_cliente, respuesta_restaurante) VALUES ('"
                    + mensajeCliente + "', '" + respuestaRestaurante + "')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
