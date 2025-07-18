package estructuras;
public class Queue_LL<T> {

    Node getFront() {
        return front;
    }
    class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public Queue_LL() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (front == null && rear == null);
    }

    public int count() {
        return size;
    }

    public void enqueue(T val) {
        Node n = new Node(val);
        if (isEmpty()) {
            front = rear = n;
        } else {
            rear.next = n;
            rear = n;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) return null;

        Node del = front;
        if (front == rear) {
            front = rear = null;
        } else {
            front = front.next;
            del.next = null;
        }
        size--;
        return del.data;
    }

    public T peek() {
        return (isEmpty()) ? null : front.data;
    }

    public void viewQ() {
        Node t = front;
        while (t != null) {
            System.out.println(t.data);
            t = t.next;
        }
    }
}