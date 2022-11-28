package entities.predators;

import entities.entitiy.*;
import entities.herbivores.*;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Wolf extends Animal {

  public   static String name="Wolf";

    public String getName() {
        return name;
    }

    Specifications specifications;
    Appetite appetite;
    int numberOfCubs=3;
    double mass=50;
    double howMuchFood=8;
    double foodMass=0;
    int numberOfAnimalsInCage=30;
    int speed=3;
    LifeSensor lifeSensor;
    int numberOfStart;


    TreeMap <Integer,String> probabilityOfEating ;

    private  TreeMap <Integer,String> setProbabilityOfEating(){
        TreeMap <Integer,String> result= new TreeMap<>();
        result.putIfAbsent(10, Horse.name);
        result.putIfAbsent(15, Deer.name);
        result.putIfAbsent(60, Rabbit.name);
        result.putIfAbsent(80, Mouse.name);
        result.putIfAbsent(60, Goat.name);
        result.putIfAbsent(70, Sheep.name);
        result.putIfAbsent(15, Hog.name);
        result.putIfAbsent(10, Buffalo.name);
        result.putIfAbsent(40, Duck.name);
        return result;
    }

    @Override
    public void setAppetite(Appetite appetite) {
        this.appetite = appetite;
    }

    public Wolf(int numberOfCubsIn,int numberOfStart) {
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
    public void moveAround() {
        appetite=Appetite.HUNGRY;
    }

    @Override
    public CopyOnWriteArrayList<Animal> replicate() {
        appetite = Appetite.HUNGRY;
        CopyOnWriteArrayList<Animal>  animals = new CopyOnWriteArrayList<Animal>();
        for (int i = 0; i <numberOfCubs ; i++) {
            animals.add(new Wolf(numberOfCubs,numberOfStart));
        }
        appetite = Appetite.HUNGRY;
        foodMass = 0;
        return animals;
    }



}
