package entities.predators;

import entities.entitiy.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Data


public class BoaConstrictor extends Animal {
    @ConfigurationAnimal(name = "BoaConstrictor", specifications = Specifications.AGGRESSIVE, mass = 15D, numberOfAnimalsInCage = 30, speed = 1, numberOfStart = 11)
    private Appetite appetite = Appetite.HUNGRY;
    private LifeSensor lifeSensor = LifeSensor.ALIVE;
    private int numberOfCubs = 1;
    private double howMuchFood = 3;
    private double foodMass;
    private TreeMap<Integer, String> probabilityOfEating = setProbabilityOfEating();


    public BoaConstrictor() {
	life();
    }

    private TreeMap<Integer, String> setProbabilityOfEating() {
	TreeMap<Integer, String> result = new TreeMap<>();
	result.putIfAbsent(15, "Fox");
	result.putIfAbsent(40, "Mouse");
	result.putIfAbsent(20, "Rabbit");
	result.putIfAbsent(10, "Duck");
	return result;
    }

    @Override
    public void toDie() {
	lifeSensor = LifeSensor.DEAD;
	System.out.println("dead  BoaConstrictor");
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
	    animals.add(new BoaConstrictor());
	  	}
	System.out.println("BoaConstrictor be fruitful and multiply"+"+"+numberOfCubs);
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
	    System.out.println("BoaConstrictor died of old age");
	    Thread.interrupted();
	}
    }
}
