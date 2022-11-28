package simulation;

import entities.entitiy.Animal;
import scenarios.Cell;
import scenarios.PlayingField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadToDie extends Thread{

   private Animal animal;
   Cell cell;
   private int time= PlayingField.CycleTime * 500;


    public ThreadToDie(Animal animal, Cell cell) {
	this.animal = animal;
	this.cell = cell;
    }

    @Override
    public void run( ) {
	try {
	    Thread.sleep(time);
	   cell.die(animal);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	Thread.interrupted();
    }

}
