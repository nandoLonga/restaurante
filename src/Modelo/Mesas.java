package Modelo;

public class Mesas
{
    int id_mesa;
    String numero;
    String capacidad;
    String estado_mesa;

    public Mesas(int id_mesa, String numero, String capacidad, String estado_mesa)
    {
        this.id_mesa = id_mesa;
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado_mesa = estado_mesa;
    }

    public int getId_mesa()
    {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa)
    {
        this.id_mesa = id_mesa;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getCapacidad()
    {
        return capacidad;
    }

    public void setCapacidad(String capacidad)
    {
        this.capacidad = capacidad;
    }

    public String getEstado_mesa()
    {
        return estado_mesa;
    }

    public void setEstado_mesa(String estado_mesa)
    {
        this.estado_mesa = estado_mesa;
    }
}
