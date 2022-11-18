package entities.herbivores;

import entities.RandomNumbers;
import entities.entitiy.Animal;
import entities.entitiy.Appetite;
import entities.entitiy.LifeSensor;
import entities.entitiy.Specifications;
import entities.plants.Plant;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Buffalo extends Animal {

    public static String name = "Buffalo";

    public String getName() {
	return name;
    }


    public Specifications specifications;
    public Appetite appetite;
    public LifeSensor lifeSensor;
    int numberOfCubs = 1;
    double mass = 700;
//    double howMuchFood = 100;
double howMuchFood = 100;
    double foodMass = 0;
    int numberOfAnimalsInCage = 10;
    int speed = 3;
    int numberOfStart;
    TreeMap<Integer, String> probabilityOfEating;

    public Buffalo(int numberOfCubsIn, int numberOfStart) {
	this.specifications = Specifications.PEACEFUL;
	this.appetite = Appetite.HUNGRY;
	this.lifeSensor = LifeSensor.ALIVE;
	this.numberOfCubs = numberOfCubsIn;
	this.numberOfStart = numberOfStart;
	this.probabilityOfEating = setProbabilityOfEating();
	this.name = "Buffalo";
    }

    @Override
    public void setAppetite(Appetite appetite) {
	this.appetite = appetite;
    }

    private TreeMap<Integer, String> setProbabilityOfEating() {
	TreeMap<Integer, String> result = new TreeMap<>();
	result.putIfAbsent(100, Plant.name);
	return result;
    }

    @Override
    public void setLifeSensor(LifeSensor lifeSensor) {
	this.lifeSensor = lifeSensor;
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
    public void setNumberOfCubs(int numberCubs) {
	numberOfCubs = numberCubs;
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
	RandomNumbers randomNumbers = new RandomNumbers(numberOfCubs+1);
	numberOfCubs=randomNumbers.call();;
	for (int i = 0; i < numberOfCubs; i++) {
	    animals.add(new Buffalo(numberOfCubs, numberOfStart));
	}
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }

}
