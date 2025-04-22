package modelo;


public class Productos {
    private int id_producto;
    private String nombre;
    private String categoria;
    private int precio_u;
    private String disponibilidad;


    public Productos(int id_producto, String nombre, String categoria, int precio_u, String disponibilidad) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio_u = precio_u;
        this.disponibilidad = disponibilidad;
    }


    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio_u() {
        return precio_u;
    }

    public void setPrecio_u(int precio_u) {
        this.precio_u = precio_u;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
