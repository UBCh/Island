package entities.herbivores;

import entities.entitiy.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Data


public class Goat extends Animal {

    @ConfigurationAnimal(name = "Goat", specifications = Specifications.PEACEFUL, mass = 60D, numberOfAnimalsInCage = 140, speed = 3, numberOfStart = 30)
    private Appetite appetite = Appetite.HUNGRY;
    private LifeSensor lifeSensor = LifeSensor.ALIVE;
    private int numberOfCubs = 3;
    private double howMuchFood = 5;
    private double foodMass;
    private TreeMap<Integer, String> probabilityOfEating = setProbabilityOfEating();


    public Goat() {
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
	System.out.println("dead  Goat");
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
	    animals.add(new Goat());
	 	}
	System.out.println("Goat be fruitful and multiply"+"+"+numberOfCubs);
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }

    public void life() {
	ThreadToDie threadToDie = new ThreadToDie();
	threadToDie.start();
    }

    @NoArgsConstructor
    private class ThreadToDie extends Thread {

	@Override
	public void run() {
	    try {
		Thread.sleep(60000);
		appetite = Appetite.WELL_FED;
		lifeSensor = LifeSensor.DEAD;
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println("Goat died of old age");
	    Thread.interrupted();
	}
    }
}
