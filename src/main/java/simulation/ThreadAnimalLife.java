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


    public ThreadAnimalLife(Animal animal, int startY, int startX) throws Exception {
	this.playingField = PlayingField.getInstance();
	this.startY = startY;
	this.startX = startX;
	this.animal = animal;

    }


    @Override
    public void run() {
	try {
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




}
