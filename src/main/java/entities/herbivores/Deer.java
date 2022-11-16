package entities.herbivores;

import entities.entitiy.*;
import entities.plants.Plant;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Deer extends Animal {

    public static String name="Deer";

    public  String getName() {
	return name;
    }

    Specifications specifications;
    Appetite appetite;
     int numberOfCubs=1;
    double mass=300;
    double howMuchFood=5;
    double foodMass=0;
    int numberOfAnimalsInCage=20;
    int speed=4;
    LifeSensor lifeSensor;
    int numberOfStart;

    TreeMap<Integer,String> probabilityOfEating ;

    private  TreeMap<Integer,String> setProbabilityOfEating(){
	TreeMap<Integer,String> result= new TreeMap<>();
	result.putIfAbsent(100,Plant.name);
	return result;
    }


    public Deer(int numberOfCubsIn,int numberOfStart) {
	this.specifications = Specifications.PEACEFUL;
	this.appetite = Appetite.HUNGRY;
	this.lifeSensor=LifeSensor.ALIVE;
	this.numberOfCubs=numberOfCubsIn;
	this.numberOfStart=numberOfStart;
	this.probabilityOfEating=setProbabilityOfEating();
    }

    @Override
    public void setAppetite(Appetite appetite) {
	this.appetite = appetite;
    }

    public  TreeMap<Integer,String> getProbabilityOfEating() {
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
	lifeSensor=LifeSensor.DEAD;
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

    }

    @Override
    public CopyOnWriteArrayList<Animal> replicate() {
	CopyOnWriteArrayList<Animal>  animals = new CopyOnWriteArrayList<Animal>();
	for (int i = 0; i <numberOfCubs ; i++) {
	    animals.add(new Deer(numberOfCubs,numberOfStart));
	}
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }


}
