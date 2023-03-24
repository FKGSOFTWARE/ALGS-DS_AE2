import java.util.NoSuchElementException;

public class Queue {
    Node head;
    Node tail;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(int data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
            tail = head;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public int dequeue() {
        if (head == null) {
            throw new NoSuchElementException("no element in queue");
        }
        int data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

class Main {
    public static void main(String[] args) {
        Queue queue = new Queue();

        // Test enqueue operation
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Queue after enqueue: " + queueToString(queue));

        // Test dequeue operation
        int dequeuedElement = queue.dequeue();
        System.out.println("Dequeued element: " + dequeuedElement);
        System.out.println("Queue after dequeue: " + queueToString(queue));

        // Test dequeue operation on empty queue
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Dequeue on empty queue throws: " + e);
        }

        // Test isEmpty operation
        System.out.println("Is queue empty? " + queue.isEmpty());
        queue.dequeue();
        System.out.println("Is queue empty? " + queue.isEmpty());
    }

    private static String queueToString(Queue queue) {
        StringBuilder sb = new StringBuilder();
        Queue.Node curr = queue.head;
        while (curr != null) {
            sb.append(curr.data);
            sb.append(" ");
            curr = curr.next;
        }
        return sb.toString();
    }
}