package streams;

import entities.entitiy.Animal;
import scenarios.AnimalMigration;

public class ThreadMigration extends Thread {


    private int startY;
    private int startX;
    private Animal animal;
    AnimalMigration animalMigration=new AnimalMigration();


    public ThreadMigration(Animal animal, int startY, int startX) {
	this.startY = startY;
	this.startX = startX;
	this.animal = animal;

    }


    @Override
    public void run() {
	try {
	    animalMigration.moveAround(animal, startY, startX);
	    Thread.interrupted();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
