package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.Appetite;
import entities.entitiy.LifeSensor;
import entities.entitiy.RandomNumbers;
import entities.herbivores.*;
import entities.plants.Plant;
import entities.predators.*;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {

    static Ark ark;

      public CopyOnWriteArrayList<Plant> plants = new CopyOnWriteArrayList<>();
      public CopyOnWriteArrayList<Animal> zoo = new CopyOnWriteArrayList<>();

    public Cell() throws Exception {
	populate();
    }

    public static void setArk(Ark a) {
	ark = a;
    }


    public void setPlants() throws Exception {
	CopyOnWriteArrayList<Plant> result = new CopyOnWriteArrayList<>();
	int quantity = ark.plant.getNumberOfStart();
	quantity = new RandomNumbers(quantity + 1).call();
	for (int i = 1; i <= quantity; i++) {
	    result.add(new Plant());
	}
	this.plants = result;
    }

    public void setZoo(CopyOnWriteArrayList<Animal> zoo) {
	this.zoo = zoo;
    }


    public void populate() throws Exception {
	CopyOnWriteArrayList<Animal> set = ark.getAnimals();
	int quantity=0;
	CopyOnWriteArrayList<Animal> result = new CopyOnWriteArrayList<>();
	for (Animal o : set) {
	    quantity = determineCount(o);
	    for (int i = 1; i <= quantity; i++) {
		result.add(getAnimal(o));
	    }
	}
	setZoo(result);
	setPlants();
    }


    private Animal getAnimal(Animal animal) throws InterruptedException {
	String name = animal.getName();
	switch (name) {
	    case "Buffalo":
		return new Buffalo(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Caterpillar":
		return new Caterpillar(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Deer":
		return new Deer(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Duck":
		return new Duck(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Goat":
		return new Goat(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Hog":
		return new Hog(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Horse":
		return new Horse(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Mouse":
		return new Mouse(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Rabbit":
		return new Rabbit(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Sheep":
		return new Sheep(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Bear":
		return new Bear(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "BoaConstrictor":
		return new BoaConstrictor(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Eagle":
		return new Eagle(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Fox":
		return new Fox(animal.getNumberOfCubs(), animal.getNumberOfStart());
	    case "Wolf":
		return new Wolf(animal.getNumberOfCubs(), animal.getNumberOfStart());
	}
	return null;
    }


    public CopyOnWriteArrayList<Plant> getPlants() {
	return plants;
    }

    public void eat(Animal animal) throws InterruptedException {
	System.out.println("хочу жрать");
	switch (animal.getAppetite()) {
	    case HUNGRY -> {
		while (animal.getAppetite() == Appetite.HUNGRY) {
		    double getFood = getFood(animal);
		    if (getFood == 0) {
			break;
		    }
		    animal.eatUp(getFood);
		}
	    }
	}
    }


    private int determineCount(Animal animal) throws Exception {
	RandomNumbers randomNumbers = new RandomNumbers(animal.getNumberOfStart() + 1);
	return randomNumbers.call();
    }

    private double getFood(Animal animal) throws InterruptedException {
	System.out.println("ищу жрать");
	//	находим количество вариантов еды для animal
	TreeMap<Integer, String> map = animal.getProbabilityOfEating();
	int sizeMap = map.size();
	int countKey = map.keySet().size(); // количество вариантов еды
	//	находим ключ мапы- соответсвующий самомому большому проценту вероятности быть съеденным из всех вариантов еды
	int key = map.lastKey();
	//	находим "имя"  еды по ключу
	String value = map.get(key);
	double food = 0;
	for (int i = 0; i < sizeMap; i++) {
	    if (checkAvailability(value)) {
		food = hunting(value);
		return food;
	    } else {
		if (countKey < 2) {
		    return 0;
		}
		key = animal.getProbabilityOfEating().floorEntry(key - 1).getKey();
		value = animal.getProbabilityOfEating().get(key);
		countKey = countKey - 1;
	    }
	}

	return 0;
    }

    private synchronized double hunting(String value) throws InterruptedException {
	System.out.println("поймал и жру");
	double result = 0;
	//	если такой вид еды есть среди живности в клетке
	if (value.equals(Plant.name)) {
	    //	если value-растение
	    Plant plant = plants.get(0);
	    result = plant.getMass();
	    plants.remove(plant);
	} else {
	    //	если value-животное
	    for (Animal a : zoo) {
		if (a.getName().equals(value)) {
		    result = a.getMass();
		    die(a);
		    break;
		}
	    }
	}
	return result;
    }


    private boolean checkAvailability(String name) {
	if (name.equals(Plant.name)) {
	    if (plants.size() != 0) {
		return true;
	    }
	} else {
	    for (Animal a : zoo) {
		if (a == null) {
		    break;
		} else if (name.equals(a.getName())) {
		    return true;
		}
	    }
	}
	return false;
    }


    public void replicate(Animal animal) throws Exception {
	if (animal.getAppetite() == Appetite.WELL_FED) {
	    System.out.println("хочу трахаться");
	    if (findACouple(animal)) {
		int countAnimal = counter(animal);
		countAnimal = countAnimal + animal.getNumberOfCubs();
		int w = animal.getNumberOfAnimalsInCage();
		if (countAnimal <= animal.getNumberOfAnimalsInCage()) {
		    System.out.println("пара есть!!!");
		    zoo.addAll(animal.replicate());
		}
	    }
	} else if (animal.getAppetite() == Appetite.HUNGRY) {
	    eat(animal);
	}
	;
    }


    // отдельный метод рост растений
    public void plantGrow() {
	  while (plants.size() < 200) {
			plants.add(new Plant());
		    }
		    System.out.println("растения растут");
			    }


    private synchronized boolean findACouple(Animal animal) {
	System.out.println("ищу пару");
	CopyOnWriteArrayList<Animal> choice = new CopyOnWriteArrayList<>();
	choice.addAll(zoo);
	choice.remove(animal);
	for (Animal a : choice) {
	    if (a.getName().equals(animal.getName())) {
		System.out.println("вот моя пара");
		zoo.remove(a);
		a.setAppetite(Appetite.HUNGRY);
		zoo.add(a);
		return true;
	    }

	}
	return false;
    }


    public String[] countLiving(int vertical, int horizontal) {
	String[] result = new String[17];
	cleanUp();
	result[0] = "cell with coordinates____________" + vertical + " =vertical  " + horizontal + " =horizontal";
	result[1] = Plant.name + " - " + plants.size();
	int i = 2;
	for (Animal a : ark.getAnimals()) {
	    result[i] = a.getName() + " - " + counter(a);
	    i++;
	}
	return result;
    }


    public int counter(Animal animal) {

	return zoo.stream()
		.filter(x -> x.getName()
			.equals(animal.getName()))
		.toArray()
		.length;
    }

    public void cleanUp() {
	zoo.stream().forEach(x -> {

	    if (x.getLifeSensor() == LifeSensor.DEAD) {
		zoo.remove(x);
	    }
	});
    }

    public void testTread(int vertical, int horizontal) {
	System.out.println("прибежали, координаты " + vertical + horizontal);
    }

    public void die(Animal animal) throws InterruptedException {
//	animal.setLifeSensor(LifeSensor.DEAD);
	animal.toDie();
    }
}





