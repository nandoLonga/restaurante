package modelo;

import java.time.LocalDateTime;

public class Orden {
    private int id_orden;
    private int id_clientes;
    private int id_mesas;
    private int id_empleados;
    private LocalDateTime fecha_hora;
    private int total;
    private String estado_orden;


    public Orden(int id_orden, int id_clientes, int id_mesas, int id_empleados, LocalDateTime fecha_hora, int total, String estado_orden) {
        this.id_orden = id_orden;
        this.id_clientes = id_clientes;
        this.id_mesas = id_mesas;
        this.id_empleados = id_empleados;
        this.fecha_hora = fecha_hora;
        this.total = total;
        this.estado_orden = estado_orden;
    }


    public Orden(int id_clientes, int id_mesas, int id_empleados, LocalDateTime fecha_hora, int total, String estado_orden) {
        this.id_clientes = id_clientes;
        this.id_mesas = id_mesas;
        this.id_empleados = id_empleados;
        this.fecha_hora = fecha_hora;
        this.total = total;
        this.estado_orden = estado_orden;
    }


    public int getId_orden() { return id_orden; }
    public void setId_orden(int id_orden) { this.id_orden = id_orden; }

    public int getId_clientes() { return id_clientes; }
    public void setId_clientes(int id_clientes) { this.id_clientes = id_clientes; }

    public int getId_mesas() { return id_mesas; }
    public void setId_mesas(int id_mesas) { this.id_mesas = id_mesas; }

    public int getId_empleados() { return id_empleados; }
    public void setId_empleados(int id_empleados) { this.id_empleados = id_empleados; }

    public LocalDateTime getFecha_hora() { return fecha_hora; }
    public void setFecha_hora(LocalDateTime fecha_hora) { this.fecha_hora = fecha_hora; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public String getEstado_orden() { return estado_orden; }
    public void setEstado_orden(String estado_orden) { this.estado_orden = estado_orden; }
}
