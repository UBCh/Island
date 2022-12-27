package scenarios;

import entities.entitiy.Animal;
import entities.herbivores.*;
import entities.plants.Plant;
import entities.predators.*;
import lombok.Getter;

import java.util.concurrent.CopyOnWriteArrayList;

@Getter

public class Ark {

//создаем стадартный "набор" животных из которых будет сформирован уникальный для каждой клетки набор  "населения"
    public static Ark instance;
    private CopyOnWriteArrayList<Animal> animals;
    private Plant plant;

    public Ark() {
	this.animals = setAnimals();
	this.plant = new Plant();
    }

    public static Ark getInstance(){
	if (instance == null) {

	    instance = new Ark();
	}
	return instance;
    }
    public CopyOnWriteArrayList<Animal> setAnimals() {
	CopyOnWriteArrayList<Animal> tmp = new CopyOnWriteArrayList<>();
	tmp.add(new Buffalo());
	tmp.add(new Caterpillar());
	tmp.add(new Deer());
	tmp.add(new Duck());
	tmp.add(new Goat());
	tmp.add(new Hog());
	tmp.add(new Horse());
	tmp.add(new Mouse());
	tmp.add(new Rabbit());
	tmp.add(new Sheep());
	tmp.add(new Bear());
	tmp.add(new BoaConstrictor());
	tmp.add(new Eagle());
	tmp.add(new Fox());
	tmp.add(new Wolf());
	return tmp;
    }





}
