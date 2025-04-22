package modelo;

public class Mesas {
    private int id_mesas;
    private String numero;
    private String capacidad;
    private String estado_mesa;

    public Mesas(int id_mesas, String numero, String capacidad, String estado_mesa) {
        this.id_mesas = id_mesas;
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado_mesa = estado_mesa;
    }

    public int getId_mesas() {
        return id_mesas;
    }

    public void setId_mesas(int id_mesas) {
        this.id_mesas = id_mesas;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado_mesa() {
        return estado_mesa;
    }

    public void setEstado_mesa(String estado_mesa) {
        this.estado_mesa = estado_mesa;
    }
}
