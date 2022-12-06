package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.ConfigurationAnimal;
import entities.herbivores.*;
import entities.predators.*;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

@NoArgsConstructor
public class FabricAnimal {


    public static Animal getAnimal(Animal animal)  {
	String name = (String) getConfigAnimal(animal, "name");
	switch (name) {
	    case "Buffalo":
		return new Buffalo();
	    case "Caterpillar":
		return new Caterpillar();
	    case "Deer":
		return new Deer();
	    case "Duck":
		return new Duck();
	    case "Goat":
		return new Goat();
	    case "Hog":
		return new Hog();
	    case "Horse":
		return new Horse();
	    case "Mouse":
		return new Mouse();
	    case "Rabbit":
		return new Rabbit();
	    case "Sheep":
		return new Sheep();
	    case "Bear":
		return new Bear();
	    case "BoaConstrictor":
		return new BoaConstrictor();
	    case "Eagle":
		return new Eagle();
	    case "Fox":
		return new Fox();
	    case "Wolf":
		return new Wolf();
	}
	return null;
    }



    public static Object getConfigAnimal(Animal animal, String name) {
	Class<? extends Animal> aClass = animal.getClass();
	Field[] fields = aClass.getDeclaredFields();
	String result = "";
	for (Field field : fields) {
	    ConfigurationAnimal сcf = field.getAnnotation(ConfigurationAnimal.class);
	    if (!(сcf == null)) {
		switch (name) {
		    case "name":
			return сcf.name();
		    case "speed":
			return сcf.speed();
		    case "mass":
			return сcf.mass();
		    case "numberOfAnimalsInCage":
			return сcf.numberOfAnimalsInCage();
		    case "numberOfStart":
			return сcf.numberOfStart();
		    case "specification":
			return сcf.specifications();
		}
	    }
	}
	return result;
    }

}
