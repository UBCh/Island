package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.LifeSensor;

public class ThreadAnimalLife extends Thread{

    Animal animal;
    public ThreadAnimalLife(Animal animal) {
	this.animal = animal;
    }


    @Override
    public void run() {

	try {
	    while (animal.getLifeSensor() == LifeSensor.ALIVE) {
		Cell.eat(animal);
		Cell.replicate(animal);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Thread.interrupted();
    }


}
