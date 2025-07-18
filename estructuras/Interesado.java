package estructuras;
public class Interesado {
    private String dni;
    private String nombre;
    private String telefono;
    private String email;
    private boolean esTrabajador;

    public Interesado(String dni, String nombre, String telefono, String email, boolean esTrabajador) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.esTrabajador = esTrabajador;
    }

    public String getDni() {
        return dni;
    }

    public boolean isTrabajador() {
        return esTrabajador;
    }

    // otros getters/setters si quieres
}