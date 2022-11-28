package entities.herbivores;

import entities.entitiy.*;
import entities.plants.Plant;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Sheep extends Animal {
  public static String name="Sheep";

    public  String getName() {
	return name;
    }
    @Override
    public void setAppetite(Appetite appetite) {
	this.appetite = appetite;
    }

    Specifications specifications;
    Appetite appetite;
   int numberOfCubs=3;
    double mass=70;
    double howMuchFood=5;
    double foodMass=0;
    int numberOfAnimalsInCage=140;
    int speed=3;
    LifeSensor lifeSensor;
    int numberOfStart;

    TreeMap <Integer,String> probabilityOfEating ;

    private TreeMap <Integer,String> setProbabilityOfEating(){
	TreeMap <Integer,String> result= new TreeMap<>();
	result.putIfAbsent(100, Plant.name);
	return result;
    }


    public Sheep(int numberOfCubsIn,int numberOfStart) {
	this.specifications = Specifications.PEACEFUL;
	this.appetite = Appetite.HUNGRY;
	this.lifeSensor=LifeSensor.ALIVE;
	this.numberOfCubs=numberOfCubsIn;
	this.numberOfStart=numberOfStart;
	this.probabilityOfEating=setProbabilityOfEating();
    }

    @Override
    public TreeMap <Integer,String> getProbabilityOfEating() {
	return probabilityOfEating;
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
    public void moveAround() {
	appetite=Appetite.HUNGRY;
    }

    @Override
    public CopyOnWriteArrayList<Animal> replicate() {
	appetite = Appetite.HUNGRY;
	CopyOnWriteArrayList<Animal>  animals = new CopyOnWriteArrayList<Animal>();
	for (int i = 0; i <numberOfCubs ; i++) {
	    animals.add(new Sheep(numberOfCubs, numberOfStart));
	}
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }


}
