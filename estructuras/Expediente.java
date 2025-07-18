package estructuras;
import estructuras.Dependencia;
import estructuras.ListaEnlazada;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expediente {
    private static int contador = 1;
    private String id;
    private String asunto;
    private String prioridad;
    private Interesado interesado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;

    private stack_LL<Movimiento> movimientos = new stack_LL<>();
    private ListaEnlazada<Dependencia> rutaDependencias = new ListaEnlazada<>(); // ← Nuevo

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Expediente(String asunto, String prioridad, Interesado interesado) {
        this.id = "EXP" + String.format("%03d", contador++);
        this.asunto = asunto;
        this.prioridad = prioridad;
        this.interesado = interesado;
        this.fechaInicio = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDateTime fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void agregarMovimiento(Movimiento mov) {
        movimientos.push(mov);
    }

    public Movimiento getUltimoMovimiento() {
        return movimientos.peek();
    }

    public Movimiento quitarUltimoMovimiento() {
        return movimientos.pop();
    }

    public stack_LL<Movimiento> getMovimientos() {
        return movimientos;
    }

    // ---------- NUEVOS MÉTODOS PARA DEPENDENCIAS ----------
    public void agregarDependencia(Dependencia dependencia) {
        rutaDependencias.insertarFinal(dependencia);
    }

    public ListaEnlazada<Dependencia> getRutaDependencias() {
        return rutaDependencias;
    }
    // -------------------------------------------------------

    @Override
    public String toString() {
        return "ID: " + id +
               " | DNI: " + interesado.getDni() +
               " | Asunto: " + asunto +
               " | Fecha Inicio: " + (fechaInicio != null ? fechaInicio.format(FORMATO) : "No registrada") +
               " | Fecha Fin: " + (fechaFinal != null ? fechaFinal.format(FORMATO) : "En proceso");
    }
}