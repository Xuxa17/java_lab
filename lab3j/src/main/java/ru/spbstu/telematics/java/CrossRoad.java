package ru.spbstu.telematics.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CrossRoad{
    private Direction greenLight;
    private Map<Direction, List<Direction>> queue = new HashMap<>();//очередь, составляем исходя из направления ОТКУДА едет
    ReentrantLock locker;
    Condition condition;

    public CrossRoad(){
        locker = new ReentrantLock();
        condition = locker.newCondition();
        greenLight = null;

        for (Direction dir : Direction.values()){
            queue.put(dir, new ArrayList<>());
        }
    }

    public void arrive(Direction from, Direction to){
        locker.lock();
        try{
            queue.get(from).add(to);
            System.out.println("Car from " + from + " to " + to + " is waiting");
            while (greenLight != from){
                condition.await();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            locker.unlock();
        }
    }

    public void leave(Direction from, Direction to){
        locker.lock();
        try {
            System.out.println("Car from " + from + " to " + to + " left");
            queue.get(from).remove(to);

            if (queue.get(from).isEmpty()){
                greenLight = null;
            }
        }finally {
            locker.unlock();
        }
    }

    public void switchTrafficLight(){
        locker.lock();
        try{
            for(Direction dir : Direction.values()){
                if (!queue.get(dir).isEmpty() && greenLight != dir){
                    System.out.println("Traffic light was changed to green for cars from " + dir);
                    greenLight = dir;
                    condition.signalAll();
                    Thread.sleep(1250);
                    break;
                }
            }
            //Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            locker.unlock();
        }
    }
}
