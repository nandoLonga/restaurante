package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/restaurante";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

    // Método para obtener la conexión
    public Connection getConnection() {
        Connection con = null;
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Intentar establecer la conexión
            con = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
        return con;
    }

    // Método para cerrar la conexión
    public void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }

    // Método para verificar si la conexión está activa
    public boolean isConnectionActive(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la conexión.");
            e.printStackTrace();
        }
        return false;
    }
}
