package com.erji.nsu.lab4.factory;

import com.erji.nsu.lab4.carParts.Accessory;
import com.erji.nsu.lab4.carParts.Body;
import com.erji.nsu.lab4.carParts.Car;
import com.erji.nsu.lab4.carParts.Engine;
import com.erji.nsu.lab4.threadPool.Task;

public class WorkerTask implements Task {

    private final CarBuilder carFactory;
    private Car car;

    public WorkerTask(CarBuilder f) {
        carFactory = f;
    }

    @Override
    public String getName() {
        return car.getCarID().toString();
    }

    @Override
    public void performWork() throws InterruptedException {

        Integer workerNo = Integer.valueOf(Thread.currentThread().getName());

        carFactory.setStatus(workerNo, WorkerStatus.WAITING);

        Body body = carFactory.getBodiesStorage().get();
        Accessory accessory = carFactory.getAccessoriesStorage().get();
        Engine engine = carFactory.getEnginesStorage().get();

        carFactory.setStatus(workerNo, WorkerStatus.WORKING);

        car = new Car(body, accessory, engine);
        Thread.sleep(carFactory.getWorkTime());

        carFactory.getCarStorage().put(car);

        carFactory.setStatus(workerNo, WorkerStatus.SLEEPING);
    }
}
