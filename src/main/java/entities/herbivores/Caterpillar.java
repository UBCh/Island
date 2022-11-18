package entities.herbivores;

import entities.entitiy.*;
import entities.plants.Plant;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Caterpillar  extends Animal {


  public   static String name="Caterpillar";

    public  String getName() {
	return name;
    }

    public Specifications specifications;
    public Appetite appetite;
    int numberOfCubs=2;
    double mass=0.01;
    double howMuchFood=1;
    double foodMass=0;
    int numberOfAnimalsInCage=200;
    int speed=0;
   public LifeSensor lifeSensor;
    int numberOfStart;
    TreeMap<Integer,String> probabilityOfEating ;

    TreeMap<Integer,String> setProbabilityOfEating(){
	TreeMap<Integer,String>result= new TreeMap<>();
	result.putIfAbsent(100,Plant.name);
	return result;
    }

    @Override
    public void setAppetite(Appetite appetite) {
	this.appetite = appetite;
    }

    public Caterpillar(int numberOfCubsIn, int numberOfStart) {
	this.specifications = Specifications.PEACEFUL;
	this.appetite = Appetite.HUNGRY;
	this.lifeSensor=LifeSensor.ALIVE;
	this.numberOfCubs=numberOfCubsIn;
	this.numberOfStart=numberOfStart;
	this.probabilityOfEating=setProbabilityOfEating();
    }
    @Override
    public void moveAround() {
	appetite=Appetite.HUNGRY;
    }

    @Override
    public  TreeMap<Integer,String> getProbabilityOfEating() {
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
    public CopyOnWriteArrayList<Animal> replicate() {
	appetite = Appetite.HUNGRY;
	CopyOnWriteArrayList<Animal>  animals = new CopyOnWriteArrayList<Animal>();
	for (int i = 0; i <numberOfCubs ; i++) {
	    animals.add(new Caterpillar(numberOfCubs,numberOfStart));
	}
	appetite = Appetite.HUNGRY;
	foodMass = 0;
	return animals;
    }


}
