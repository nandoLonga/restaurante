package Modelo;

public class Clientes
{
    int id_cliente;
    String nombre;
    String telefono;
    String correo;

    public Clientes(int id_cliente, String nombre, String telefono, String correo)
    {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId_cliente()
    {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente)
    {
        this.id_cliente = id_cliente;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

}


