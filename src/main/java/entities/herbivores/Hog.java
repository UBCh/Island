package entities.herbivores;

import entities.entitiy.*;
import entities.plants.Plant;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Hog extends Animal {


   public static String name="Hog";

     public String getName() {
	return name;
    }


    Specifications specifications;
    Appetite appetite;
   static int numberOfCubs=2;
    double mass=400;
    double howMuchFood=5;
   double foodMass=0;
    int numberOfAnimalsInCage=50;
    int speed=2;
    LifeSensor lifeSensor;
    int numberOfStart;

    TreeMap <Integer,String> probabilityOfEating ;

    private  TreeMap <Integer,String> setProbabilityOfEating(){
	TreeMap <Integer,String> result= new TreeMap<>();
	result.putIfAbsent(100,Plant.name);
	result.putIfAbsent(90,Caterpillar.name);
	result.putIfAbsent(50,Mouse.name);
	return result;
    }
    @Override
    public void setAppetite(Appetite appetite) {
	this.appetite = appetite;
    }
    public Hog(int numberOfCubsIn,int numberOfStart) {
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
	super.moveAround();
    }

    @Override
    public CopyOnWriteArrayList<Animal> replicate() {
	appetite = Appetite.HUNGRY;
	CopyOnWriteArrayList<Animal>  animals = new CopyOnWriteArrayList<Animal>();
	for (int i = 0; i <numberOfCubs ; i++) {
	    animals.add(new Hog(numberOfCubs,numberOfStart));
	}
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }

}
