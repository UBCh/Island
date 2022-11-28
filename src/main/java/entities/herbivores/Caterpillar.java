package entities.herbivores;

import entities.entitiy.Animal;
import entities.entitiy.Appetite;
import entities.entitiy.LifeSensor;
import entities.entitiy.Specifications;
import entities.plants.Plant;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Caterpillar extends Animal {


    public static String name = "Caterpillar";
    public Specifications specifications;
    public Appetite appetite;
    public LifeSensor lifeSensor;
    private int numberOfCubs = 2;
    private final double mass = 0.01;
    private final double howMuchFood = 1;
    private double foodMass = 0;
    private final int numberOfAnimalsInCage = 200;
    private final int speed = 0;
    private final int numberOfStart;
    private final TreeMap<Integer, String> probabilityOfEating;


    public Caterpillar(int numberOfCubsIn, int numberOfStart) {
	this.specifications = Specifications.PEACEFUL;
	this.appetite = Appetite.HUNGRY;
	this.lifeSensor = LifeSensor.ALIVE;
	this.numberOfCubs = numberOfCubsIn;
	this.numberOfStart = numberOfStart;
	this.probabilityOfEating = setProbabilityOfEating();
    }

    private TreeMap<Integer, String> setProbabilityOfEating() {
	TreeMap<Integer, String> result = new TreeMap<>();
	result.putIfAbsent(100, Plant.name);
	return result;
    }

    public String getName() {
	return name;
    }

    @Override
    public void setAppetite(Appetite appetite) {
	this.appetite = appetite;
    }

    @Override
    public void moveAround() {
	appetite = Appetite.HUNGRY;
    }

    @Override
    public TreeMap<Integer, String> getProbabilityOfEating() {
	return probabilityOfEating;
    }

    @Override
        public int getNumberOfCubs() {
	return numberOfCubs;
    }
    @Override
    public double getFoodMass() {
	return foodMass;
    }
    @Override
    public int getNumberOfStart() {
	return numberOfStart;
    }

    @Override
    public Specifications getSpecifications() {
	return specifications;
    }

    @Override
    public Appetite getAppetite() {
	return appetite;
    }

    @Override
    public LifeSensor getLifeSensor() {
	return lifeSensor;
    }

    @Override
    public void toDie() {
	lifeSensor = LifeSensor.DEAD;
	System.out.println("сдох   "+name);
    }

    @Override
    public int getNumberOfAnimalsInCage() {
	return numberOfAnimalsInCage;
    }

    @Override
    public int getSpeed() {
	return speed;
    }

    @Override
    public Double getMass() {
	return mass;
    }

    @Override
    public Double getHowMuchFood() {
	return howMuchFood;
    }

    @Override
    public void setNumberOfCubs(int numberCubs) {
	numberOfCubs = numberCubs;
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
    public CopyOnWriteArrayList<Animal> replicate() throws InterruptedException {
	appetite = Appetite.HUNGRY;
	CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<Animal>();
	for (int i = 0; i < numberOfCubs; i++) {
	    animals.add(new Caterpillar(numberOfCubs, numberOfStart));
	    System.out.println("убусь до соплей");
	}
	lifeSensor = LifeSensor.DEAD;
	return animals;
    }


}
