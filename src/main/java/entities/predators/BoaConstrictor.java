package entities.predators;

import entities.entitiy.*;
import entities.herbivores.*;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class BoaConstrictor extends Animal {

   public static String name="BoaConstrictor";

    public  String getName() {
	return name;
    }


    Specifications specifications;
    Appetite appetite;
    int numberOfCubs=5;
    double mass=15;
    double howMuchFood=3;
    double foodMass=0;
    int numberOfAnimalsInCage=30;
    int speed=1;
    LifeSensor lifeSensor;
    int numberOfStart;

    TreeMap <Integer,String> probabilityOfEating ;

    private  TreeMap <Integer,String> setProbabilityOfEating(){
	TreeMap <Integer,String> result= new TreeMap<>();
	result.putIfAbsent(15, Fox.name);
	result.putIfAbsent(40, Mouse.name);
	result.putIfAbsent(20, Rabbit.name);
	result.putIfAbsent(10, Duck.name);
	return result;
    }



    public BoaConstrictor(int numberOfCubsIn,int numberOfStart) {
	this.specifications = Specifications.PEACEFUL;
	this.appetite = Appetite.HUNGRY;
	this.lifeSensor=LifeSensor.ALIVE;
	this.numberOfCubs=numberOfCubsIn;
	this.numberOfStart=numberOfStart;
	this.probabilityOfEating=setProbabilityOfEating();
    }


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
    public void setNumberOfCubs(int numberOfCubs) {
	this.numberOfCubs = numberOfCubs;
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
    public void setAppetite(Appetite appetite) {
	this.appetite = appetite;
    }
    @Override
    public void moveAround() {
	moveAround();
    }

    @Override
    public CopyOnWriteArrayList<Animal> replicate() {
	appetite = Appetite.HUNGRY;
	CopyOnWriteArrayList<Animal>  animals = new CopyOnWriteArrayList<Animal>();
	for (int i = 0; i <numberOfCubs ; i++) {
	    animals.add(new BoaConstrictor(numberOfCubs,numberOfStart));
	}
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }



}
