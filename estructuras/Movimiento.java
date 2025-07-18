package estructuras;

public class Movimiento {

    private String descripcion;
    private String dependencia;
    private String hora;

    public Movimiento(String descripcion, String dependencia) {
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.hora = java.time.LocalTime.now().toString();
    }

    @Override
    public String toString() {
        return "📄 Descripción: " + descripcion
                + "\n🏢 Dependencia: " + dependencia
                + "\n🕒 Hora: " + hora;
    }

}
