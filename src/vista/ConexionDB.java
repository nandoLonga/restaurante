package vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private final String USUARIO = "root";
    private final String CONTRASENA = "tu_contraseña";

    public Connection getConnection() {
        Connection conexion = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("✅ Conexión establecida correctamente.");

        } catch (ClassNotFoundException e) {
            System.err.println(" Error: No se encontró el driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println(" Error al conectar a la base de datos.");
            e.printStackTrace();
        }

        return conexion;
    }
}
