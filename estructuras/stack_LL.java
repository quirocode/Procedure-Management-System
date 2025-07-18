package estructuras;

public class stack_LL<T> {
    
    public class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node top;
    private int size;

    public stack_LL() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T val) {
        Node n = new Node(val);
        n.next = top;
        top = n;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("⚠ Stack Vacía");
            return null;
        }
        T result = top.data;
        top = top.next;
        size--;
        return result;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("⚠ Stack Vacía");
            return null;
        }
        return top.data;
    }

    public void display() {
        Node t = top;
        if (isEmpty()) {
            System.out.println("⚠ Stack Vacía");
            return;
        }
        while (t != null) {
            System.out.println(t.data); // El objeto T debe tener toString()
            t = t.next;
        }
    }

    public java.util.List<T> getTodosLosElementos() {
        java.util.List<T> elementos = new java.util.ArrayList<>();
        Node actual = top;
        while (actual != null) {
            elementos.add(actual.data);
            actual = actual.next;
        }
        return elementos;
    }

    public Node getTop() {
        return top;
    }
}