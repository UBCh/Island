package entities.herbivores;

import entities.entitiy.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Data


public class Sheep extends Animal {

    @ConfigurationAnimal(name = "Sheep", specifications = Specifications.PEACEFUL, mass = 70D, numberOfAnimalsInCage = 140, speed = 3, numberOfStart = 29)
    private Appetite appetite = Appetite.HUNGRY;
    private LifeSensor lifeSensor = LifeSensor.ALIVE;
    private int numberOfCubs = 3;
    private double howMuchFood = 50;
    private double foodMass;
    private TreeMap<Integer, String> probabilityOfEating = setProbabilityOfEating();


    public Sheep() {
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
	System.out.println("dead  Sheep");
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
	    animals.add(new Sheep());
	 	}
	System.out.println("Sheep be fruitful and multiply"+"+"+numberOfCubs);
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
	    System.out.println("Sheep died of old age");
	    Thread.interrupted();
	}
    }
}
