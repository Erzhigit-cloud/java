package com.erji.nsu.lab4.factory;

import java.util.ArrayList;

public class Storage<T> {

    private final ArrayList<T> units;
    private final int capacity;

    public Storage(int capacity) {
        this.capacity = capacity;
        units = new ArrayList<>();
    }

    public synchronized void put(T detail) {
        while (units.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        units.add(detail);
        notifyAll();
    }

    public synchronized T get() {
        while (units.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        T detail = units.remove(units.size() - 1);
        notifyAll();
        return detail;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer currentCountElements() {
        return units.size();
    }
}
