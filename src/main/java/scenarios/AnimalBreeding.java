package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.Appetite;
import entities.entitiy.FabricAnimal;
import entities.entitiy.LifeSensor;
import lombok.NoArgsConstructor;

import java.util.concurrent.CopyOnWriteArrayList;

@NoArgsConstructor
public class AnimalBreeding {

    public void reproduction(Animal animal) throws Exception {
	if (animal.getAppetite() == Appetite.WELL_FED) {
	    if (findACouple(animal)) {
		int countAnimal = counter(animal);
		int numberOfCubs = animal.getNumberOfCubs();
		countAnimal = numberOfCubs + countAnimal;
		int max = (int) FabricAnimal.getConfigAnimal(animal, "numberOfAnimalsInCage");
		if (countAnimal <= max) {
		    Cell.zoo.addAll(animal.replicate());
		}
	    }
	} else if (animal.getAppetite() == Appetite.HUNGRY) {
	    Cell.eat(animal);
	}
    }

    public int counter(Animal a) {

	return Cell.zoo.stream().filter(x -> FabricAnimal.getConfigAnimal(x, "name").equals(FabricAnimal.getConfigAnimal(a, "name"))).toArray().length;
    }

    private synchronized boolean findACouple(Animal animal) {
	String name = (String) FabricAnimal.getConfigAnimal(animal, "name");
	CopyOnWriteArrayList<Animal> choice = new CopyOnWriteArrayList<>();
	choice.addAll(Cell.zoo);
	choice.remove(animal);
	for (Animal a : choice) {
	    if (a.getLifeSensor() == LifeSensor.DEAD) {
		break;
	    }
	    if (FabricAnimal.getConfigAnimal(a, "name").equals(name)) {
		a.setAppetite(Appetite.HUNGRY);
		return true;
	    }

	}
	return false;
    }

}
