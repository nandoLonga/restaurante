package Controlador;

import Conexion.ConexionDB;

import java.sql.Connection;

public class ClienteDAO
{
    ConexionDB conexionDB = new ConexionDB();
    //Agregar
    public void agregar()
    {
        Connection con = conexionDB.getConnection();

        String query = "";
    }
}
