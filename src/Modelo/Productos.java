package Modelo;

import java.util.Stack;

public class Productos
{
    int id_producto;
    String nombreP;
    String categoria;
    int precio_u;
    String disponibilidad;

    public int getId_producto()
    {
        return id_producto;
    }

    public Productos(int id_producto, String nombreP, String categoria, int precio_u, String disponibilidad)
    {
        this.id_producto = id_producto;
        this.nombreP = nombreP;
        this.categoria = categoria;
        this.precio_u = precio_u;
        this.disponibilidad = disponibilidad;
    }

    public void setId_producto(int id_producto)
    {
        this.id_producto = id_producto;
    }

    public String getNombreP()
    {
        return nombreP;
    }

    public void setNombreP(String nombreP)
    {
        this.nombreP = nombreP;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public int getPrecio_u()
    {
        return precio_u;
    }

    public void setPrecio_u(int precio_u)
    {
        this.precio_u = precio_u;
    }

    public String getDisponibilidad()
    {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad)
    {
        this.disponibilidad = disponibilidad;
    }
}
