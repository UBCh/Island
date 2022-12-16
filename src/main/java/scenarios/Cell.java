package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.FabricAnimal;
import entities.entitiy.LifeSensor;
import entities.entitiy.RandomNumbers;
import entities.plants.Plant;
import lombok.Getter;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class Cell {
    public static CopyOnWriteArrayList<Plant> plants = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Animal> zoo = new CopyOnWriteArrayList<>();
    static ExecutorService serviceLife = Executors.newFixedThreadPool(100);
    private Ark ark;

    public Cell() throws Exception {
	this.ark = Ark.getInstance();
	populate();
    }

    public static void die(Animal animal) {
	animal.toDie();
	Thread.interrupted();
    }

    public static void eat(Animal animal) throws InterruptedException {
	AnimalNutrition animalNutrition = new AnimalNutrition();
	cleanUp();
	animalNutrition.feed(animal);

    }

    public static void cleanUp() {
	zoo.stream().forEach(x -> {

	    if (x.getLifeSensor() == LifeSensor.DEAD) {
		zoo.remove(x);
	    }
	});
    }

    private int determineCount(Animal animal) throws Exception {
	int numberOfStart = (int) getParameter(animal, "numberOfStart");
	RandomNumbers randomNumbers = new RandomNumbers(numberOfStart + 1);
	return randomNumbers.call();
    }

    private void setZoo(CopyOnWriteArrayList<Animal> zoo) {
	this.zoo = zoo;
    }

    private void populate() throws Exception {
	setPlants();
	CopyOnWriteArrayList<Animal> set = ark.getAnimals();
	int quantity = 0;
	CopyOnWriteArrayList<Animal> result = new CopyOnWriteArrayList<>();
	for (Animal o : set) {
	    quantity = determineCount(o);
	    for (int i = 1; i <= quantity; i++) {
		Animal animal = FabricAnimal.getAnimal(o);
		result.add(animal);
		// запускаем поток "жизнь животного"
		ThreadAnimalLife threadAnimalLife = new ThreadAnimalLife(animal);
		threadAnimalLife.start();
		serviceLife.submit(threadAnimalLife);
	    }
	}
	setZoo(result);

    }
// запускаем поток "рост растений"
    private void setPlants() throws Exception {
	CopyOnWriteArrayList<Plant> result = new CopyOnWriteArrayList<>();
	int quantity = ark.getPlant().getNumberOfStart();
	quantity = new RandomNumbers(quantity + 1).call();
	for (int i = 1; i <= quantity; i++) {
	    result.add(new Plant());
	}
	this.plants = result;
	ThreadPlantGrow threadPlantGrow = new ThreadPlantGrow();
	threadPlantGrow.start();
	serviceLife.submit(threadPlantGrow);
    }

    public void replicate(Animal animal) throws Exception {
	AnimalBreeding animalBreeding = new AnimalBreeding();
	cleanUp();
	animalBreeding.reproduction(animal);
    }

    // отдельный метод рост растений
    public void plantGrow() {
	for (int i = 0; i < plants.size(); i++) {
	    if (plants.size() < 200) {
		plants.addAll(plants.get(i).replica());
	    }
	}

    }

    public String[] countLiving(int vertical, int horizontal) {
	String[] result = new String[17];
	CopyOnWriteArrayList<Plant> plantsCopy = new CopyOnWriteArrayList<>();
	plantsCopy.addAll(plants);
	cleanUp();
	result[0] = "cell with coordinates____________" + vertical + " =vertical  " + horizontal + " =horizontal";
	result[1] = Plant.name + " - " + plantsCopy.size();
	int i = 2;
	for (Animal a : ark.getAnimals()) {
	    result[i] = getParameter(a, "name") + " - " + counter(a);
	    i++;
	}
	return result;
    }

    public int counter(Animal a) {
	CopyOnWriteArrayList<Animal> zooCopy = new CopyOnWriteArrayList<>();
	zooCopy.addAll(zoo);
	zooCopy = cleanUp(zooCopy);
	return zooCopy.stream().filter(x -> getParameter(x, "name").
			equals(getParameter(a, "name")))
		.toArray()
		.length;
    }

    private CopyOnWriteArrayList<Animal> cleanUp(CopyOnWriteArrayList<Animal> zoo) {
	zoo.stream().forEach(x -> {

	    if (x.getLifeSensor() == LifeSensor.DEAD) {
		zoo.remove(x);
	    }
	});
	return zoo;
    }



    private Object getParameter(Animal animal, String config) {

	return FabricAnimal.getConfigAnimal(animal, config);
    }

    private class ThreadPlantGrow extends Thread {

	static boolean stop = true;

	@Override
	public void run() {
	    try {
		while (stop) {
		    plantGrow();
		  		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    Thread.interrupted();
	}
    }

    private class ThreadAnimalLife extends Thread {

	Animal animal;
	private ThreadAnimalLife(Animal animal) {
	    this.animal = animal;
	}


	@Override
	public void run() {

	    try {
		while (animal.getLifeSensor() == LifeSensor.ALIVE) {
		    System.out.println("жизнь идет" + FabricAnimal.getConfigAnimal(animal, "name"));
		    eat(animal);
		    replicate(animal);
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    Thread.interrupted();
	}
    }

}






