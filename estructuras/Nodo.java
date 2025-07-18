package estructuras;
public class Nodo<T> {
    T clave;
        Nodo<T> siguiente;

        public Nodo(T clave) {
            this.clave = clave;
            this.siguiente = null;
        }

        public T getClave() {
            return clave;
        }

        public void setClave(T clave) {
            this.clave = clave;
        }

        public Nodo<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<T> siguiente) {
            this.siguiente = siguiente;
        }
}