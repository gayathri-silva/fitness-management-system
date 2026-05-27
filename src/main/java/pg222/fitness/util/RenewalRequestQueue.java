package pg222.fitness.util;

import pg222.fitness.model.RenewalRequest;

public class RenewalRequestQueue {
    private int maxSize;
    private RenewalRequest[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    public RenewalRequestQueue(int size) {
        maxSize = size;
        queueArray = new RenewalRequest[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(RenewalRequest req) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (rear == maxSize - 1) {  // Deal with wraparound
                rear = -1;
            }
            queueArray[++rear] = req;
            nItems++;
        }
    }

    public RenewalRequest remove() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            RenewalRequest temp = queueArray[front++];
            if (front == maxSize) {  // Deal with wraparound
                front = 0;
            }
            nItems--;
            return temp;
        }
    }

    public RenewalRequest peekFront() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            return queueArray[front];
        }
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }

    // Added to help with displaying all items
    public RenewalRequest[] getAll() {
        RenewalRequest[] result = new RenewalRequest[nItems];
        int index = 0;
        int current = front;

        while (index < nItems) {
            result[index++] = queueArray[current];
            current = (current + 1) % maxSize;
        }

        return result;
    }
}
