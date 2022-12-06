package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.Appetite;
import entities.entitiy.LifeSensor;
import entities.entitiy.RandomNumbers;
import entities.plants.Plant;
import lombok.Getter;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class Cell {

    public CopyOnWriteArrayList<Plant> plants = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Animal> zoo = new CopyOnWriteArrayList<>();
    private Ark ark;

    public Cell() throws Exception {
	this.ark = Ark.getInstance();
	populate();
    }

    private void populate() throws Exception {
	CopyOnWriteArrayList<Animal> set = ark.getAnimals();
	int quantity = 0;
	CopyOnWriteArrayList<Animal> result = new CopyOnWriteArrayList<>();
	for (Animal o : set) {
	    quantity = determineCount(o);
	    for (int i = 1; i <= quantity; i++) {
		result.add(FabricAnimal.getAnimal(o));
	    }
	}
	setZoo(result);
	setPlants();
    }

    private int determineCount(Animal animal) throws Exception {
	int numberOfStart = (int) getParameter(animal, "numberOfStart");
	RandomNumbers randomNumbers = new RandomNumbers(numberOfStart + 1);
	return randomNumbers.call();
    }

    private void setZoo(CopyOnWriteArrayList<Animal> zoo) {
	this.zoo = zoo;
    }

    private void setPlants() throws Exception {
	CopyOnWriteArrayList<Plant> result = new CopyOnWriteArrayList<>();
	int quantity = ark.getPlant().getNumberOfStart();
	quantity = new RandomNumbers(quantity + 1).call();
	for (int i = 1; i <= quantity; i++) {
	    result.add(new Plant());
	}
	this.plants = result;
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

	double result = 0;
	//	если такой вид еды есть среди живности в клетке
	if (value.equals("Plant")) {
	    //	если value-растение
	    Plant plant = plants.get(0);
	    result = plant.getMass();
	    plants.remove(plant);
	    System.out.println("поймал и жру");
	} else {
	    //	если value-животное
	    for (Animal a : zoo) {
		if (value.equals(getParameter(a, "name"))) {
		    result = (double) getParameter(a, "mass");
		    die(a);
		    System.out.println("поймал и жру");
		    break;
		}
	    }
	}
	return result;
    }


    private boolean checkAvailability(String name) {
	if (name.equals("Plant")) {
	    if (plants.size() != 0) {
		return true;
	    }
	} else {
	    for (Animal a : zoo) {
		if (a == null) {
		    break;
		} else if (name.equals(getParameter(a, "name"))) {
		    return true;
		}
	    }
	}
	return false;
    }


    public void replicate(Animal animal) throws Exception {
	cleanUp();
	if (animal.getAppetite() == Appetite.WELL_FED) {
	    System.out.println("хочу трахаться");
	    if (findACouple(animal)) {
		System.out.println("пара есть!!!");
		int countAnimal = counter(animal);
		int numberOfCubs = animal.getNumberOfCubs();
		countAnimal = numberOfCubs + countAnimal;
		int max = (int) getParameter(animal, "numberOfAnimalsInCage");
		if (countAnimal <= max) {
		    System.out.println("процесс пошел");
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
	for (int i = 0; i < plants.size(); i++) {
	    if (plants.size() < 200) {
		plants.addAll(plants.get(i).replica());
	    }
	}
	System.out.println("растения растут");
    }


    private synchronized boolean findACouple(Animal animal) {
	String name= (String) getParameter(animal,"name");
	System.out.println(name+"ищу пару");
	CopyOnWriteArrayList<Animal> choice = new CopyOnWriteArrayList<>();
	choice.addAll(zoo);
	choice.remove(animal);
	for (Animal a : choice) {
	    if (a.getLifeSensor()==LifeSensor.DEAD){break;}
	    if (getParameter(a, "name").equals(name)) {
		System.out.println("вот моя пара"+name);
		a.setAppetite(Appetite.HUNGRY);
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
	    result[i] = getParameter(a, "name") + " - " + counter(a);
	    i++;
	}
	return result;
    }


    public int counter(Animal animal) {
           cleanUp();
	return zoo.stream()
		.filter(x -> getParameter(x, "name")
			.equals(getParameter(animal, "name")))
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

    public void die(Animal animal) {

	animal.toDie();
    }

    private Object getParameter(Animal animal, String config) {

	return FabricAnimal.getConfigAnimal(animal, config);
    }
}





