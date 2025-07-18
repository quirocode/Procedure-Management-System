package estructuras;

public class Dependencia {
    private String nombre;

    public Dependencia(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "📌 Dependencia: " + nombre;
    }
}