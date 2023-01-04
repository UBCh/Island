package entities.herbivores;

import entities.entitiy.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import scenarios.RandomNumbers;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Data


public class Hog extends Animal {

    @ConfigurationAnimal(name = "Hog", specifications = Specifications.PEACEFUL, mass = 400D, numberOfAnimalsInCage = 50, speed = 2, numberOfStart = 15)
    private Appetite appetite = Appetite.HUNGRY;
    private LifeSensor lifeSensor = LifeSensor.ALIVE;
    private int numberOfCubs = 2;
    private double howMuchFood = 5;
    private double foodMass;
    private TreeMap<Integer, String> probabilityOfEating = setProbabilityOfEating();

    public Hog() {
	life();
    }

    private TreeMap<Integer, String> setProbabilityOfEating() {
	TreeMap<Integer, String> result = new TreeMap<>();
	result.putIfAbsent(100, "Plant");
	result.putIfAbsent(90, "Caterpillar");
	result.putIfAbsent(50, "Mouse");
	return result;
    }


    @Override
    public void toDie() {
	lifeSensor = LifeSensor.DEAD;
	    }


    @Override
    public void eatUp(double massOfTheVictim) {
	if (appetite == Appetite.HUNGRY) {
	    foodMass = foodMass + massOfTheVictim;
	}
	if (foodMass >= howMuchFood) {
	    appetite = Appetite.WELL_FED;
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
	    animals.add(new Hog());
	  	}
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }

    public void life() {
	Runnable task=()->{
	    try {
		Thread.sleep(60000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    toDie(); };
	Thread taskTread=new Thread(task);
	taskTread.start();
	if (lifeSensor==LifeSensor.DEAD){
	    taskTread.isInterrupted();}
    }
}
