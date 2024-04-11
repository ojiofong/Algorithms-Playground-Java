package multithreading;

import java.util.LinkedList;
import java.util.Queue;


public class BlockingQueue<T> {
    private Queue<T> queue;
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(T element) throws InterruptedException {
        while (this.queue.size() == this.capacity) {
            wait(); // Wait until space becomes available
        }

        this.queue.add(element);
        notifyAll(); // Notify all waiting threads that an element has been added
    }

    public synchronized T dequeue() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait(); // Wait until an element becomes available
        }

        T item = this.queue.remove();
        notifyAll(); // Notify all waiting threads that an element has been removed
        return item;
    }

    public synchronized int size() {
        return this.queue.size();
    }
}



// More advanced version to improve throughput for consumers and producers
public class BlockingQueue<T> {
    private Queue<T> queue;
    private int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public void enqueue(T element) throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            while (this.queue.size() == this.capacity) {
                notFull.await(); // Wait until space becomes available or interrupted
            }

            this.queue.add(element);
            notEmpty.signal(); // Signal that an element has been added
        } finally {
            lock.unlock();
        }
    }

    public T dequeue() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            while (this.queue.isEmpty()) {
                notEmpty.await(); // Wait until an element becomes available or interrupted
            }

            T item = this.queue.remove();
            notFull.signal(); // Signal that an element has been removed
            return item;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return this.queue.size();
        } finally {
            lock.unlock();
        }
    }
}
