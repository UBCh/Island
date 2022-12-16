package entities.herbivores;

import entities.entitiy.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Data


public class Mouse extends Animal {

    @ConfigurationAnimal(name = "Mouse", specifications = Specifications.PEACEFUL, mass = 0.05D, numberOfAnimalsInCage = 500, speed = 1, numberOfStart = 100)
    private Appetite appetite = Appetite.HUNGRY;
    private LifeSensor lifeSensor = LifeSensor.ALIVE;
    private int numberOfCubs = 10;
    private double howMuchFood = 0.01;
    private double foodMass;
    private TreeMap<Integer, String> probabilityOfEating = setProbabilityOfEating();


    public Mouse() {
	life();
    }


    private TreeMap<Integer, String> setProbabilityOfEating() {
	TreeMap<Integer, String> result = new TreeMap<>();
	result.putIfAbsent(100, "Plan");
	result.putIfAbsent(90, "Caterpillar");
	return result;
    }

    @Override
    public void toDie() {
	lifeSensor = LifeSensor.DEAD;
	System.out.println("dead  Mouse");
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
	    animals.add(new Mouse());
	  	}
	System.out.println("Mouse be fruitful and multiply"+"+"+numberOfCubs);

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
	    System.out.println("Mouse died of old age");
	    Thread.interrupted();
	}
    }
}
