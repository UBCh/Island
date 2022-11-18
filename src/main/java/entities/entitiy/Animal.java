package entities.entitiy;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Animal {
    public static String name;

    public void setAppetite(Appetite appetite) {
	this.appetite = appetite;
    }

    public Specifications specifications;
    public Appetite appetite;

    public void setLifeSensor(LifeSensor lifeSensor) {
	this.lifeSensor = lifeSensor;
    }

    public LifeSensor lifeSensor;
    int numberOfStart;
    double foodMass;
    int numberOfCubs;
    TreeMap<Integer, String> probabilityOfEating;

    public String getName() {
	return name;
    }



    public TreeMap<Integer, String> getProbabilityOfEating() {
	return probabilityOfEating;
    }

    public Specifications getSpecifications() {
	return specifications;
    }

    public Appetite getAppetite() {
	return appetite;

    }

    public int getNumberOfCubs() {
	return numberOfCubs;
    }

    public void setNumberOfCubs(int numberCubs) {
    }

    public double getFoodMass() {
	return foodMass;
    }

    public int getNumberOfStart() {
	return numberOfStart;
    }

    public LifeSensor getLifeSensor() {
	return lifeSensor;
    }

    public int getNumberOfAnimalsInCage() {
	return 0;
    }

    public int getSpeed() {
	return 0;
    }

    public Double getMass() {
	return null;
    }

    public Double getHowMuchFood() {
	return null;
    }

    public void eatUp(double mass) {
    }

    public void moveAround() {
    }

    public CopyOnWriteArrayList<Animal> replicate() throws Exception {
	return null;
    }

    public void toDie() {
    }

}
