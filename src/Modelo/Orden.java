package Modelo;

import java.sql.Timestamp;
import java.util.Date;

public class Orden
{
    int id_orden;
    int total_orden;
    String estado_orden;
    int id_cliente;
    int id_empleado;
    int id_mesa;
    int cantidad;
    Timestamp fecha_orden;

    public Orden(int id_orden, int total_orden, String estado_orden, int id_cliente, int id_empleado, int id_mesa, int cantidad, Timestamp fecha_orden)
    {
        this.id_orden = id_orden;
        this.total_orden = total_orden;
        this.estado_orden = estado_orden;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.id_mesa = id_mesa;
        this.fecha_orden = fecha_orden;
        this.cantidad = cantidad;
    }

    public int getId_orden()
    {
        return id_orden;
    }

    public void setId_orden(int id_orden)
    {
        this.id_orden = id_orden;
    }

    public int getTotal_orden()
    {
        return total_orden;
    }

    public void setTotal_orden(int total_orden)
    {
        this.total_orden = total_orden;
    }

    public String getEstado_orden()
    {
        return estado_orden;
    }

    public void setEstado_orden(String estado_orden)
    {
        this.estado_orden = estado_orden;
    }

    public int getId_cliente()
    {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente)
    {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado()
    {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado)
    {
        this.id_empleado = id_empleado;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa)
    {
        this.id_mesa = id_mesa;
    }

    public Timestamp getFecha_orden()
    {
        return fecha_orden;
    }

    public void setFecha_orden(Timestamp fecha_orden)
    {
        this.fecha_orden = fecha_orden;
    }

    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }

    public int getCantidad()
    {
        return cantidad;
    }
}
