package net.uvavru.amazon;

/**
 * Created by stepan on 22.8.2014.
 */
public class Node<T> {
    private Node next;
    private T data;

    public Node(T data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
