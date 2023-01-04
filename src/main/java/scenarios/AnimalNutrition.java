package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.Appetite;
import entities.entitiy.FabricAnimal;
import entities.plants.Plant;
import lombok.NoArgsConstructor;

import java.util.TreeMap;

@NoArgsConstructor
public class AnimalNutrition {




    public void feed( Animal animal)  {
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


    private double getFood(Animal animal)  {
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

    private synchronized double hunting(String value)  {

	double result = 0;
	//	если такой вид еды есть среди живности в клетке
	if (value.equals("Plant")) {
	    //	если value-растение
	    Plant plant = Cell.plants.get(0);
	    result = plant.getMass();
	    Cell.plants.remove(plant);
	} else {
	    //	если value-животное
	    for (Animal a : Cell.zoo) {
		if (value.equals(FabricAnimal.getConfigAnimal(a, "name"))) {
		    result = (double) FabricAnimal.getConfigAnimal(a, "mass");
		    Cell.die(a);
		    break;
		}
	    }
	}
	return result;
    }


    private boolean checkAvailability(String name) {
	if (name.equals("Plant")) {
	    if (Cell.plants.size() != 0) {
		return true;
	    }
	} else {
	    for (Animal a : Cell.zoo) {
		if (a == null) {
		    break;
		} else if (name.equals(FabricAnimal.getConfigAnimal(a, "name"))) {
		    return true;
		}
	    }
	}
	return false;
    }

}
