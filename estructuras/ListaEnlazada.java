package estructuras;
public class ListaEnlazada<T> {
    private Nodo<T> head;
    private int size;

    public ListaEnlazada() {
        this.head = null;
        this.size = 0;
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int obtenerTamanio() {
        return size;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        Nodo<T> actual = head;
        while (actual != null) {
            System.out.println(actual.clave); // debe tener toString() en clase T
            actual = actual.siguiente;
        }
    }

    public void insertarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.siguiente = head;
        head = nuevo;
        size++;
    }

    public void insertarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            head = nuevo;
        } else {
            Nodo<T> actual = head;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        size++;
    }

    public void eliminarInicio() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        head = head.siguiente;
        size--;
    }

    public void eliminarFinal() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        if (head.siguiente == null) {
            head = null;
        } else {
            Nodo<T> actual = head;
            while (actual.siguiente.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = null;
        }
        size--;
    }

    public void revertir() {
        Nodo<T> anterior = null;
        Nodo<T> actual = head;
        Nodo<T> siguiente;
        while (actual != null) {
            siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }
        head = anterior;
    }
    public Nodo<T> getHead(){
        return head;
    }
}