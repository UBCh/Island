package scenarios;

import entities.entitiy.Animal;
import entities.herbivores.*;
import entities.plants.Plant;
import entities.predators.*;

import java.util.concurrent.CopyOnWriteArrayList;

public class Ark {

    Buffalo buffalo;
    Caterpillar caterpillar;
    Deer deer;
    Duck duck;
    Goat goat;
    Hog hog;
    Horse horse;
    Mouse mouse;
    Rabbit rabbit;
    Sheep sheep;
    Plant plant;
    Bear bear;
    BoaConstrictor boaConstrictor;
    Eagle eagle;
    Fox fox;
    Wolf wolf;

 public CopyOnWriteArrayList<Animal> animals=new CopyOnWriteArrayList<>();

    public Ark() {

    }

    public CopyOnWriteArrayList<Animal> getAnimals() {

	return animals;
    }

       public void setBuffalo( int numberOfCubsIn,int numberOfStart) {
	this.buffalo = new Buffalo(numberOfCubsIn,numberOfStart);
	animals.add(buffalo);
    }

       public void setCaterpillar(int numberOfCubsIn,int numberOfStart) {
	this.caterpillar = new Caterpillar(numberOfCubsIn,numberOfStart);
	animals.add(caterpillar);
    }

    public void setDeer(int numberOfCubsIn,int numberOfStart) {
		this.deer = new Deer(numberOfCubsIn,numberOfStart);
	animals.add(deer);
    }


    public void setDuck(int numberOfCubsIn,int numberOfStart) {
	this.duck = new Duck(numberOfCubsIn,numberOfStart);
	animals.add(duck);
    }

       public void setGoat(int numberOfCubsIn,int numberOfStart) {
	this.goat = new Goat(numberOfCubsIn,numberOfStart);
	   animals.add(goat);
    }

    public void setHog(int numberOfCubsIn,int numberOfStart) {
	this.hog = new Hog(numberOfCubsIn,numberOfStart);
	animals.add(hog);
    }

       public void setHorse(int numberOfCubsIn,int numberOfStart) {
	this.horse = new Horse(numberOfCubsIn,numberOfCubsIn);
	   animals.add(horse);
    }
    public void setMouse(int numberOfCubsIn,int numberOfStart) {

	this.mouse = new Mouse(numberOfCubsIn,numberOfStart);
	animals.add(mouse);
    }

    public void setRabbit(int numberOfCubsIn,int numberOfStart) {
	this.rabbit = new Rabbit(numberOfCubsIn,numberOfStart);
	animals.add(rabbit);
    }


    public void setSheep(int numberOfCubsIn,int numberOfStart) {
	this.sheep = new Sheep(numberOfCubsIn,numberOfStart);
	animals.add(sheep);
    }

       public void setPlant(int numberOfStart) {
	this.plant = new Plant(numberOfStart );
    }

      public void setBear(int numberOfCubsIn,int numberOfStart) {
	this.bear = new Bear(numberOfCubsIn, numberOfStart);
	  animals.add(bear);
    }

       public void setBoaConstrictor(int numberOfCubsIn,int numberOfStart) {
	this.boaConstrictor = new BoaConstrictor(numberOfCubsIn, numberOfStart);
	   animals.add(boaConstrictor);
    }

    public void setEagle(int numberOfCubsIn,int numberOfStart) {

	this.eagle = new Eagle(numberOfCubsIn,numberOfStart);
	animals.add(eagle);
    }
    public void setFox(int numberOfCubsIn,int numberOfStart) {

	this.fox = new Fox(numberOfCubsIn,numberOfStart);
	animals.add(fox);
    }


    public void setWolf(int numberOfCubsIn,int numberOfStart) {

	this.wolf = new Wolf(numberOfCubsIn,numberOfStart);
	animals.add(wolf);
    }
}
