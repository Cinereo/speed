package pl.edu.agh.to2.speed.net.services.local.synchronisation;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConcurrentLinkedList<E> {
    private Lock lock = new ReentrantLock();
    private LinkedList<E> list;

    ConcurrentLinkedList() {
        list = new LinkedList<>();
    }

    void add(E e) {
        try {
            lock.lock();
            list.add(e);
        } finally {
            lock.unlock();
        }
    }

    LinkedList<E> getCopyAndClearList() {
        try {
            lock.lock();
            LinkedList<E> result = new LinkedList<>(this.list);
            this.list.clear();
            return result;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }
}
