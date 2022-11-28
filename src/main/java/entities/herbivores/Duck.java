package entities.herbivores;

import entities.entitiy.Animal;
import entities.entitiy.Appetite;
import entities.entitiy.LifeSensor;
import entities.entitiy.Specifications;
import entities.plants.Plant;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Duck extends Animal {

    public static String name = "Duck";
    private LifeSensor lifeSensor;
    private Specifications specifications;
    private Appetite appetite;
    private int numberOfCubs = 6;
    private double mass = 1;
    private double howMuchFood = 0.15;
    private double foodMass = 0;
    private int numberOfAnimalsInCage = 200;
    private int speed = 4;
    private int numberOfStart;
    private TreeMap<Integer, String> probabilityOfEating;

    public Duck(int numberOfCubsIn, int numberOfStart) {
	this.specifications = Specifications.PEACEFUL;
	this.appetite = Appetite.HUNGRY;
	this.lifeSensor = LifeSensor.ALIVE;
	this.numberOfCubs = numberOfCubsIn;
	this.numberOfStart = numberOfStart;
	this.probabilityOfEating = setProbabilityOfEating();
    }

    public String getName() {
	return name;
    }

    private TreeMap<Integer, String> setProbabilityOfEating() {
	TreeMap<Integer, String> result = new TreeMap<>();
	result.putIfAbsent(100, Plant.name);
	result.putIfAbsent(90, Caterpillar.name);
	return result;
    }

    @Override
    public void setAppetite(Appetite appetite) {
	this.appetite = appetite;
    }

    @Override
    public TreeMap<Integer, String> getProbabilityOfEating() {
	return probabilityOfEating;
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
    public int getNumberOfCubs() {
	return numberOfCubs;
    }

    public double getFoodMass() {
	return foodMass;
    }

    public int getNumberOfStart() {
	return numberOfStart;
    }
    @Override
    public void moveAround() {
	appetite=Appetite.HUNGRY;
    }

    @Override
    public CopyOnWriteArrayList<Animal> replicate() {
	appetite = Appetite.HUNGRY;
	CopyOnWriteArrayList<Animal>  animals = new CopyOnWriteArrayList<Animal>();
	for (int i = 0; i <numberOfCubs ; i++) {
	    animals.add(new Duck(numberOfCubs,numberOfStart));
	}
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }


}
