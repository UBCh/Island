package entities.predators;

import entities.entitiy.*;
import lombok.Data;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Data


public class Wolf extends Animal {

    @ConfigurationAnimal(name = "Wolf", specifications = Specifications.AGGRESSIVE, mass = 50D, numberOfAnimalsInCage = 30, speed = 3, numberOfStart = 3)
    private Appetite appetite = Appetite.HUNGRY;
    private LifeSensor lifeSensor = LifeSensor.ALIVE;
    private int numberOfCubs = 3;
    private double howMuchFood = 8;
    private double foodMass;
    private TreeMap<Integer, String> probabilityOfEating = setProbabilityOfEating();


    public Wolf() {
	life();
    }

    private TreeMap<Integer, String> setProbabilityOfEating() {
	TreeMap<Integer, String> result = new TreeMap<>();
	result.putIfAbsent(10, "Horse");
	result.putIfAbsent(15, "Deer");
	result.putIfAbsent(60, "Rabbit");
	result.putIfAbsent(80, "Mouse");
	result.putIfAbsent(60, "Goat");
	result.putIfAbsent(70, "Sheep");
	result.putIfAbsent(15, "Hog");
	result.putIfAbsent(10, "Buffalo");
	result.putIfAbsent(40, "Duck");
	return result;
    }


    @Override
    public void toDie() {
	lifeSensor = LifeSensor.DEAD;
	System.out.println("dead  Wolf");
    }


    @Override
    public void eatUp(double massOfTheVictim) {
	if (appetite == Appetite.HUNGRY) {
	    foodMass = foodMass + massOfTheVictim;
	}
	if (foodMass >= howMuchFood) {
	    appetite = Appetite.WELL_FED;
	    return;
	}
    }

    @Override
    public void moveAround() {
	appetite = Appetite.HUNGRY;
    }

    @Override
    public CopyOnWriteArrayList<Animal> replicate() throws Exception {
	CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<Animal>();
	RandomNumbers randomNumbers = new RandomNumbers(numberOfCubs + 1);
	numberOfCubs = randomNumbers.call();
	for (int i = 0; i < numberOfCubs; i++) {
	    animals.add(new Wolf());
	 	}
	System.out.println("Wolf be fruitful and multiply"+"+"+numberOfCubs);
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }

    public void life() {
	ThreadToDie threadToDie = new ThreadToDie();
	threadToDie.start();
    }


    private class ThreadToDie extends Thread {

	private ThreadToDie() {
	}

	@Override
	public void run() {
	    try {
		Thread.sleep(120000);
		appetite = Appetite.WELL_FED;
		lifeSensor = LifeSensor.DEAD;
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println("Wolf died of old age");
	    Thread.interrupted();
	}
    }

}
