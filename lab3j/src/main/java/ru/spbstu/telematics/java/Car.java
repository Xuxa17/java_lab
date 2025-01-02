package ru.spbstu.telematics.java;

public class Car implements Runnable{
    private final Direction to;
    private final Direction from;
    private final CrossRoad intersection;

    public Car(Direction from1, Direction to1, CrossRoad cross){
        from = from1;
        to = to1;
        intersection = cross;
    }

    @Override
    public void run(){
        try {
            System.out.println("Car from " + from + " to " + to + " is arriving");
            intersection.arrive(from, to);
            Thread.sleep(250);//время проезда
            intersection.leave(from, to);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
