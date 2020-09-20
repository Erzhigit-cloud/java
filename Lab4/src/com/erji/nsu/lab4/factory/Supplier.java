package com.erji.nsu.lab4.factory;

import com.erji.nsu.lab4.carParts.CarPart;

import java.lang.reflect.InvocationTargetException;

public class Supplier<T extends CarPart> implements Runnable {

    private final Storage<T> storage;
    private Integer period;

    private final Class<T> partType;

    public Supplier(Storage<T> s, Integer p, Class<T> type) {
        storage = s;
        period = p;
        partType = type;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                T detail = partType.getDeclaredConstructor().newInstance();
                storage.put(detail);
                Thread.sleep(period);
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
