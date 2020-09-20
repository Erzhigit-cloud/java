package com.erji.nsu.lab4.factory;

import com.erji.nsu.lab4.FactoryConfig;
import com.erji.nsu.lab4.carParts.Accessory;
import com.erji.nsu.lab4.carParts.Body;
import com.erji.nsu.lab4.carParts.Car;
import com.erji.nsu.lab4.carParts.Engine;

import java.util.ArrayList;

public class Factory {

    private final Integer __DEFAULT_SPEED = 2000;

    //Склады
    private final Storage<Accessory> accessoriesStorage;
    private final Storage<Engine> enginesStorage;
    private final Storage<Body> bodiesStorage;
    private final Storage<Car> carsStorage;

    //Поставщики
    private final ArrayList<Supplier<Accessory>> accessoriesSuppliers;
    private final Supplier<Engine> enginesSupplier;
    private final Supplier<Body> bodiesSupplier;

    private final ArrayList<Dealer> dealers;

    private final CarBuilder carBuilder;
    private final CarBuilderController controller;

    private final ArrayList<Thread> threads;

    public Factory(FactoryConfig factoryConfig) { //создаем всех и создаем потоки
        accessoriesStorage = new Storage<>(factoryConfig.getAccessoriesStorageSize());
        enginesStorage = new Storage<>(factoryConfig.getEnginesStorageSize());
        bodiesStorage = new Storage<>(factoryConfig.getBodiesStorageSize());
        carsStorage = new Storage<>(factoryConfig.getCarsStorageSize());

        accessoriesSuppliers = new ArrayList<>();
        for (int i = 0; i < factoryConfig.getAccessorySuppliers(); i++) {
            accessoriesSuppliers.add(new Supplier<>(accessoriesStorage, __DEFAULT_SPEED, Accessory.class));
        }

        enginesSupplier = new Supplier<>(enginesStorage, __DEFAULT_SPEED, Engine.class);
        bodiesSupplier = new Supplier<>(bodiesStorage, __DEFAULT_SPEED, Body.class);

        carBuilder = new CarBuilder(factoryConfig.getWorkersCount(), enginesStorage, bodiesStorage, accessoriesStorage, carsStorage);
        controller = new CarBuilderController(carBuilder);

        dealers = new ArrayList<>();
        for (int i = 0; i < factoryConfig.getDealers(); i++) {
            dealers.add(new Dealer(carsStorage, __DEFAULT_SPEED, factoryConfig.doLogSales()));
        }

        threads = new ArrayList<>();
        threads.add(new Thread(enginesSupplier));
        threads.add(new Thread(bodiesSupplier));

        for (var supplier : accessoriesSuppliers) {
            threads.add(new Thread(supplier));
        }

        int count = 0;
        for (var dealer : dealers) {
            threads.add(new Thread(dealer, String.valueOf(count++)));
        }

        threads.add(new Thread(controller));
    }

    public void start() {
        for (var thread : threads) {
            thread.start();
        }
    }

    public void stop() {
        for (var thread : threads) {
            thread.interrupt();
        }
    }

    public Storage<Accessory> getAccessoriesStorage() {
        return accessoriesStorage;
    }

    public Storage<Engine> getEnginesStorage() {
        return enginesStorage;
    }

    public Storage<Body> getBodiesStorage() {
        return bodiesStorage;
    }

    public Storage<Car> getCarsStorage() {
        return carsStorage;
    }

    public CarBuilder getCarBuilder() {
        return carBuilder;
    }

    public ArrayList<Supplier<Accessory>> getAccessoriesSuppliers() {
        return accessoriesSuppliers;
    }

    public Supplier<Engine> getEnginesSupplier() {
        return enginesSupplier;
    }

    public Supplier<Body> getBodiesSupplier() {
        return bodiesSupplier;
    }

    public ArrayList<Dealer> getDealers() {
        return dealers;
    }
}
