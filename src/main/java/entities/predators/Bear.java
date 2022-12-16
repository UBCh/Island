package entities.predators;

import entities.entitiy.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Data


public class Bear extends Animal {


    @ConfigurationAnimal(name = "Bear", specifications = Specifications.AGGRESSIVE, mass = 500D, numberOfAnimalsInCage = 50, speed = 2, numberOfStart = 5)
    private Appetite appetite = Appetite.HUNGRY;
    private LifeSensor lifeSensor = LifeSensor.ALIVE;
    private int numberOfCubs = 1;
    private double howMuchFood = 10;
    private double foodMass;
    private TreeMap<Integer, String> probabilityOfEating = setProbabilityOfEating();

    public Bear() {
	life();
    }

    private TreeMap<Integer, String> setProbabilityOfEating() {
	TreeMap<Integer, String> result = new TreeMap<>();
	result.putIfAbsent(80, "BoaConstrictor");
	result.putIfAbsent(40, "Horse");
	result.putIfAbsent(80, "Deer");
	result.putIfAbsent(80, "Rabbit");
	result.putIfAbsent(90, "Mouse");
	result.putIfAbsent(70, "Goat");
	result.putIfAbsent(70, "Sheep");
	result.putIfAbsent(50, "Hog");
	result.putIfAbsent(20, "Buffalo");
	result.putIfAbsent(10, "Duck");
	return result;
    }

    @Override
    public void toDie() {
	lifeSensor = LifeSensor.DEAD;
	System.out.println("dead  Bear");
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
	    animals.add(new Bear());
	  	}
	System.out.println("Bear be fruitful and multiply"+"+"+numberOfCubs);
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
	    System.out.println("Bear died of old age");
	    Thread.interrupted();
	}
    }
}
