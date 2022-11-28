package simulation;

import entities.entitiy.Animal;
import scenarios.Cell;
import scenarios.PlayingField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadAnimalLife extends Thread {


    private PlayingField playingField;
    private int startY;
    private int startX;
    private Animal animal;
   private static ExecutorService serviceDie;

    public ThreadAnimalLife(Animal animal, int startY, int startX) throws Exception {
	this.playingField = PlayingField.getInstance();
	this.startY = startY;
	this.startX = startX;
	this.animal = animal;
	serviceDie=Executors.newFixedThreadPool(1000);
    }


    @Override
    public void run() {
	try {
	     Cell oldCell=playingField.cellSet[startY][startX];
	    Thread threadToDie=new ThreadToDie(animal,oldCell);
	    threadToDie.start();
	    serviceDie.submit(threadToDie);
	    playingField.cellSet[startY][startX].eat(animal);
	    playingField.cellSet[startY][startX].replicate(animal);
	    Cell cell=playingField.moveAround(animal, startY, startX);
	    cell.eat(animal);
	    cell.replicate(animal);
            Thread.interrupted();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void stopServiceDie(){
	serviceDie.shutdownNow();
    }


}
