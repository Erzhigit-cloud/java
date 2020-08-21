package nsu.antonsokovnin.lab4.factory;

import nsu.antonsokovnin.lab4.carParts.Accessory;
import nsu.antonsokovnin.lab4.carParts.Body;
import nsu.antonsokovnin.lab4.carParts.Car;
import nsu.antonsokovnin.lab4.carParts.Engine;
import nsu.antonsokovnin.lab4.threadPool.Task;

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
        Integer workerNo = Integer.parseInt(Thread.currentThread().getName());
        carFactory.setStatus(workerNo, WorkerStatus.WAITING);

        Body b = carFactory.getBodiesStorage().get();
        Accessory a = carFactory.getAccessoriesStorage().get();
        Engine e = carFactory.getEnginesStorage().get();

        carFactory.setStatus(workerNo, WorkerStatus.WORKING);

        car = new Car(b, a, e);
        Thread.sleep(carFactory.getWorkTime());

        carFactory.getCarStorage().put(car);
        carFactory.setStatus(workerNo, WorkerStatus.SLEEPING);
    }
}
