package estructuras;

import java.util.HashMap;
import java.util.List;

public class GestorTramites {
    public static Queue_LL<Expediente> colaAlta = new Queue_LL<>();
    public static Queue_LL<Expediente> colaMedia = new Queue_LL<>();
    public static Queue_LL<Expediente> colaBaja = new Queue_LL<>();
    public static ListaEnlazada<Expediente> expedientesFinalizados = new ListaEnlazada<>();
    public static Expediente buscarExpedientePorId(String id) {
        for (Queue_LL<Expediente> cola : List.of(colaAlta, colaMedia, colaBaja)) {
            Queue_LL<Expediente>.Node actual = cola.getFront();
            while (actual != null) {
                if (actual.data.toString().contains(id)) {
                    return actual.data;
                }
                actual = actual.next;
            }
        }
        return null;
}
    public static Expediente getUltimoExpedienteRegistrado() {
        Queue_LL<Expediente>.Node ultimo = null;

        // Buscar en orden de prioridad (asumiendo que se agregan por prioridad)
        for (Queue_LL<Expediente> cola : List.of(colaAlta, colaMedia, colaBaja)) {
            Queue_LL<Expediente>.Node actual = cola.getFront();
            while (actual != null) {
                ultimo = actual; // guardamos el último que se recorra
                actual = actual.next;
            }
        }

        if (ultimo != null) {
            return ultimo.data;
        }

        // Si no hay en trámite, buscar en expedientes finalizados
        Nodo<Expediente> nodo = expedientesFinalizados.getHead();
        Expediente ultimoFinalizado = null;
        while (nodo != null) {
            ultimoFinalizado = nodo.getClave();
            nodo = nodo.getSiguiente();
        }

        return ultimoFinalizado;
    }
    public static HashMap<String, ListaEnlazada<Expediente>> rutasDependencias = new HashMap<>();

}