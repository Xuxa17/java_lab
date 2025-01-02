package ru.spbstu.telematics.java;
import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String[] args) {
        CrossRoad intersection = new CrossRoad();

        // Создаем поток для светофора
        new Thread(() -> {
            while (true) {
                intersection.switchTrafficLight();
                // Можно добавить паузу между переключениями, если это необходимо
                try {
                    Thread.sleep(500); // Задержка между переключениями
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    // Создаем автомобили в разных направлениях
                    new Thread(new Car(Direction.N, Direction.S, intersection)).start();
                    new Thread(new Car(Direction.W, Direction.E, intersection)).start();
                    new Thread(new Car(Direction.S, Direction.W, intersection)).start();
                    new Thread(new Car(Direction.N, Direction.S, intersection)).start();

                    // Задержка перед созданием следующей группы автомобилей
                    TimeUnit.SECONDS.sleep(10); // Задержка в 5 секунд перед следующей группой
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
