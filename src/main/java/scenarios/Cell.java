package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.FabricAnimal;
import entities.entitiy.LifeSensor;
import entities.plants.Plant;
import lombok.Getter;
import streams.ThreadAnimalLife;
import streams.ThreadPlantGrow;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class Cell {

    public static CopyOnWriteArrayList<Plant> plants = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Animal> zoo = new CopyOnWriteArrayList<>();
    static ExecutorService serviceLife = Executors.newFixedThreadPool(100);


    private final String[] ark = new String[]{"Buffalo", "Caterpillar", "Deer", "Duck", "Goat", "Hog", "Horse", "Mouse", "Rabbit", "Sheep", "Bear", "BoaConstrictor", "Eagle", "Fox", "Wolf"};

    public Cell() throws Exception {
	populate();
    }

    public static void die(Animal animal) {
	animal.toDie();
	    }

    public static void eat(Animal animal)  {
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



    private void setZoo(CopyOnWriteArrayList<Animal> zoo) {
	this.zoo = zoo;
    }

    private void populate() throws Exception {
	setPlants();
	int quantity = 0;
	CopyOnWriteArrayList<Animal> result = new CopyOnWriteArrayList<>();
	for (String name : ark) {
	    Animal animal = FabricAnimal.getAnimal(name);
	    quantity = determineCount(animal);
	    for (int i = 1; i <= quantity; i++) {
		result.add(animal);
		// запускаем поток "жизнь животного"
		ThreadAnimalLife threadAnimalLife = new ThreadAnimalLife(animal);
		threadAnimalLife.start();
		serviceLife.submit(threadAnimalLife);
	    }
	}
	setZoo(result);

    }

    private int determineCount(Animal animal) throws Exception {
	int numberOfStart = (int) getParameter(animal, "numberOfStart");
	RandomNumbers randomNumbers = new RandomNumbers(numberOfStart + 1);
	return randomNumbers.call();
    }


    // запускаем поток "рост растений" в каждой клетке при создании
    private void setPlants() throws Exception {
	CopyOnWriteArrayList<Plant> result = new CopyOnWriteArrayList<>();
	int quantity = Plant.getNumberOfStart();
	quantity = new RandomNumbers(quantity + 1).call();
	for (int i = 1; i <= quantity; i++) {
	    result.add(new Plant());
	}
	this.plants = result;
	ThreadPlantGrow threadPlantGrow = new ThreadPlantGrow();
	threadPlantGrow.start();
	serviceLife.submit(threadPlantGrow);
    }

    public static void replicate(Animal animal) throws Exception {
	AnimalBreeding animalBreeding = new AnimalBreeding();
	cleanUp();
	animalBreeding.reproduction(animal);
    }

    // отдельный метод рост растений
    public static void plantGrow() {
	for (int i = 0; i < plants.size(); i++) {
	    if (plants.size() < 180) {
		plants.addAll(Plant.replica());
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
	for (String a : ark) {
	    result[i] = a + " - " + counter(a);
	    i++;
	}
	return result;
    }

    public int counter(String name) {
	CopyOnWriteArrayList<Animal> zooCopy = new CopyOnWriteArrayList<>();
	zooCopy.addAll(zoo);
	zooCopy = cleanUp(zooCopy);
	return zooCopy.stream().filter(x -> getParameter(x, "name").
			equals(name))
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






}






