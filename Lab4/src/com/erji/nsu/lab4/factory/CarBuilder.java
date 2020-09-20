package com.erji.nsu.lab4.factory;

import com.erji.nsu.lab4.carParts.Accessory;
import com.erji.nsu.lab4.carParts.Body;
import com.erji.nsu.lab4.carParts.Car;
import com.erji.nsu.lab4.carParts.Engine;
import com.erji.nsu.lab4.threadPool.ThreadPool;

import java.util.Arrays;

public class CarBuilder {

    private final Storage<Engine> enginesStorage;
    private final Storage<Body> bodiesStorage;
    private final Storage<Accessory> accessoriesStorage;
    private final Storage<Car> carStorage;

    private final ThreadPool threadPool;

    private Integer workTime;
    private final WorkerStatus[] statuses;

    public CarBuilder(Integer noOfWorkers, Storage<Engine> enginesStorage, Storage<Body> bodiesStorage, Storage<Accessory> accessoriesStorage, Storage<Car> carStorage) {
        this.enginesStorage = enginesStorage;
        this.bodiesStorage = bodiesStorage;
        this.accessoriesStorage = accessoriesStorage;
        this.carStorage = carStorage;

        workTime = 2000; // ms

        threadPool = new ThreadPool(noOfWorkers);

        statuses = new WorkerStatus[noOfWorkers];

        Arrays.fill(statuses, WorkerStatus.SLEEPING);
    }

    public void buildCar() {
        threadPool.addTask(new WorkerTask(this));
    }

    public Storage<Engine> getEnginesStorage() {
        return enginesStorage;
    }

    public Storage<Body> getBodiesStorage() {
        return bodiesStorage;
    }

    public Storage<Accessory> getAccessoriesStorage() {
        return accessoriesStorage;
    }

    public Storage<Car> getCarStorage() {
        return carStorage;
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer t) {
        workTime = t;
    }

    public WorkerStatus[] getStatuses() {
        return statuses;
    }

    public void setStatus(Integer i, WorkerStatus newStatus) {
        statuses[i] = newStatus;
    }
}
