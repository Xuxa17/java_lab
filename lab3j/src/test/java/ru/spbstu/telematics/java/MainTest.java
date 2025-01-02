package ru.spbstu.telematics.java;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private CrossRoad intersection;

    Direction From [] = {Direction.S, Direction.W, Direction.N};
    Direction To [] = {Direction.W, Direction.E, Direction.S};

    @BeforeEach
    public void setUp() {
        intersection = new CrossRoad();
    }

    @Test
    public void testNoDeadlock() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            final Direction from = From[i % 3];
            final Direction to = To[i % 3];

            executor.submit(() -> {
                try {
                    Car car = new Car(from, to, intersection);
                    car.run();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(5, TimeUnit.SECONDS);
        executor.shutdownNow();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "Executor did not terminate in time");
    }


    @Test
    public void testNoRaceConditions() throws InterruptedException {
        // Создаем поток для светофора
        new Thread(() -> {
            while (true) {
                intersection.switchTrafficLight();
                // Можно добавить паузу между переключениями, если это необходимо
                try {
                    Thread.sleep(1000); // Задержка между переключениями
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        int numberOfCars = 10;
        CountDownLatch latch = new CountDownLatch(numberOfCars);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        int[] carsPassed = {0};

        for (int i = 0; i < numberOfCars; i++) {
//            final Direction from = Direction.values()[i % 4];
//            final Direction to = Direction.values()[(i + 1) % 4];
            final Direction from = From[i % 3];
            final Direction to = To[i % 3];

            executor.submit(() -> {
                try {
                    Car car = new Car(from, to, intersection);
                    car.run();
                    synchronized (carsPassed) {
                        carsPassed[0]++;
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(30, TimeUnit.SECONDS);
        executor.shutdownNow();
        assertEquals(numberOfCars, carsPassed[0], "Not all cars passed the intersection");
    }

    @Test
    public void testTrafficLightSwitching() throws InterruptedException {
        // Запускаем светофор в отдельном потоке
        Thread trafficLightThread = new Thread(() -> {
            while (true) {
                intersection.switchTrafficLight();
                try {
                    Thread.sleep(1000); // Задержка между переключениями
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        trafficLightThread.start();

        // Создаем и запускаем потоки для автомобилей
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
//            final Direction from = Direction.values()[i % 4];
//            final Direction to = Direction.values()[(i + 1) % 4];
            final Direction from = From[i % 3];
            final Direction to = To[i % 3];

            executor.submit(() -> {
                Car car = new Car(from, to, intersection);
                car.run();
            });
        }

        executor.shutdown();
        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS), "Executor did not terminate in time");

        // Останавливаем поток светофора
        trafficLightThread.interrupt();
    }
}
