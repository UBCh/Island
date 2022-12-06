package entities.herbivores;


import entities.entitiy.*;
import lombok.Data;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Data

public class Horse extends Animal {

    @ConfigurationAnimal(name = "Horse", specifications = Specifications.PEACEFUL, mass = 400D, numberOfAnimalsInCage = 20, speed = 4, numberOfStart = 3)
    private Appetite appetite = Appetite.HUNGRY;
    private LifeSensor lifeSensor = LifeSensor.ALIVE;
    private int numberOfCubs = 1;
    private double howMuchFood = 5;
    private double foodMass;
    private TreeMap<Integer, String> probabilityOfEating = setProbabilityOfEating();

    public Horse() {
	life();
    }


    private TreeMap<Integer, String> setProbabilityOfEating() {
	TreeMap<Integer, String> result = new TreeMap<>();
	result.putIfAbsent(100, "Plant");
	return result;
    }

    @Override
    public void toDie() {
	lifeSensor = LifeSensor.DEAD;
	System.out.println("dead  Horse");
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
	    animals.add(new Horse());
	 	}
	System.out.println("Horse be fruitful and multiply"+"+"+numberOfCubs);
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
	    System.out.println("Horse died of old age");
	    Thread.interrupted();
	}
    }
}
