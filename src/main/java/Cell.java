import entities.entitiy.Animal;
import entities.entitiy.Appetite;
import entities.entitiy.LifeSensor;
import entities.herbivores.*;
import entities.plants.Plant;
import entities.predators.*;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {

    static Ark ark;
    public static Cell instance;

      public CopyOnWriteArrayList<Plant> plants = new CopyOnWriteArrayList<>();
      public CopyOnWriteArrayList<Animal> zoo = new CopyOnWriteArrayList<>();

    private Cell() {
    populate();
    }

    public static Cell getInstance(Ark ark) {
	if (instance == null) {
	    setArk(ark);
	    instance = new Cell();
	    var d=instance;
	}
	return instance;
    }

    private static void setArk(Ark a) {
	ark = a;
    }



    public void setPlants() {
	CopyOnWriteArrayList<Plant> result=new CopyOnWriteArrayList<>();
	int quantity=ark.plant.getNumberOfStart();
	for (int i = 1; i <= quantity; i++){
	  result.add(new Plant(quantity));
	}
	this.plants = result;
    }

    public void setZoo(CopyOnWriteArrayList<Animal> zoo) {
	this.zoo = zoo;
    }


    public void populate() {
	CopyOnWriteArrayList<Animal> set = ark.getAnimals();
	int quantity=0;
	CopyOnWriteArrayList<Animal> result = new CopyOnWriteArrayList<>();
	for (Animal o : set) {
	     quantity = o.getNumberOfStart();
	    for (int i = 1; i <= quantity; i++) {
		result.add(getAnimal(o));
	    }

	}
	setZoo(result);
       setPlants();
    }


    private Animal getAnimal(Animal animal) {
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

    public CopyOnWriteArrayList<Animal> getZoo() {
	return zoo;
    }

    public CopyOnWriteArrayList<Plant> getPlants() {
	return plants;
    }

    //    покормить
    public void eat(){
	for (Animal animal: zoo){
	       switch (animal.getAppetite()){
		case HUNGRY -> {
		 while (animal.getAppetite()== Appetite.HUNGRY){
		    double getFood=getFood(animal);
		if (getFood==0) {animal.setLifeSensor(LifeSensor.DEAD); break;}
		    animal.eatUp(getFood);}
	    }
	}
    }}

//    private double getFood(Animal animal){
//	//	находим количество вариантов еды для animal
//	int sizeMap=animal.getProbabilityOfEating().size();
//	//	находим ключ мапы- соответсвующий самомому большому проценту вероятности быть съеденным из всех вариантов еды
//	int key=animal.getProbabilityOfEating().lastKey();
//	//	находим "имя"  еды по ключу
//	String value=animal.getProbabilityOfEating().get(key);
//	double food=0;
//
//	if (checkAvailability(value)){
//	    //	если такой вид еды есть среди живности в клетке
//	    if (value.equals(Plant.name)){
//		//	если value-растение
//		food= plants.get(0).getMass();
//		plants.remove(0);
//	    }
//	    else {
//		//	если value-животное
//		for (Animal a: zoo ) {
//		    if (a.getName().equals(value)){
//			food=a.getMass();
//			zoo.remove(a);
//			break;
//		    }
//		}
//
//	    }
//	}
//	else {
////	если самого очевидного вида еды нет среди живности в клетке
//	    var f=animal.appetite;
//	  while (animal.getAppetite()==Appetite.HUNGRY) {
//	      for (int i = 0; i < sizeMap - 1; i++) {
//		  key = animal.getProbabilityOfEating().floorEntry(key - 1).getKey();
//		  value = animal.getProbabilityOfEating().get(key);
//		  if (checkAvailability(value)) {
//		      for (Animal a : zoo) {
//			  if (a.getName().equals(value)) {
//			      food = a.getMass();
//			      zoo.remove(a);
//			      return food;
//			  }
//		      }
//		  } else if (!checkAvailability(value)) {
//		      break;
//		  }
//	      }
//	  }
//	}
//	return food;
//    }

    private double getFood(Animal animal){
	//	находим количество вариантов еды для animal
	TreeMap<Integer,String> map=new TreeMap<>();
	map=animal.getProbabilityOfEating();
	int sizeMap=map.size();
	int countKey=map.keySet().size();
	//	находим ключ мапы- соответсвующий самомому большому проценту вероятности быть съеденным из всех вариантов еды
	int key=map.lastKey();
	//	находим "имя"  еды по ключу
	String value=map.get(key);
	double food=0;
	for (int i = 0; i < sizeMap; i++) {

	    if (checkAvailability(value)){
		food=hunting(value);
		return food;
	    }
	    else {
		if(countKey<2){return 0;}
		key = animal.getProbabilityOfEating().floorEntry(key - 1).getKey();
		value = animal.getProbabilityOfEating().get(key);
		countKey=countKey-1;
//		if (map==null){return 0;}
//		key=map.lastKey();
//		value=map.get(key);
	    }
	}

	return 0;
    }

    private double hunting(String value){
	double result=0;
	//	если такой вид еды есть среди живности в клетке
	if (value.equals(Plant.name)){
	    //	если value-растение
	    result= plants.get(0).getMass();
	    plants.remove(0);
	}
	else {
	    //	если value-животное
	    for (Animal a: zoo ) {
		if (a.getName().equals(value)){
		    result=a.getMass();
		    zoo.remove(a);
		    break;
		}
	    }

	}
	return result;
    }






    private boolean checkAvailability(String name){
	if (name.equals(Plant.name)){
	    if (plants.size()!=0){ return true;}
	}
	else {
	    for (Animal a: zoo) {
		if (a==null){break;}
		else if (name.equals(a.getName())){
		    return true;
		}
	    }
	}
	return false;
    }

    public void replicate(){
	CopyOnWriteArrayList< Animal> choice=new CopyOnWriteArrayList<>();
	 choice.addAll(zoo);
	for (Animal animal: choice) {
	       if (animal.getAppetite()==Appetite.WELL_FED){
		   if (findACouple(animal)){
		   zoo.addAll(animal.replicate());} }
	}
	if (plants.size()!=0){
	    for (Plant plant: plants){
		plants.add(plant.growing());
	    }}
    }



    private boolean findACouple(Animal animal){
	CopyOnWriteArrayList< Animal> choice=new CopyOnWriteArrayList<>();
	choice.addAll(zoo);
	choice.remove(animal);
	for (Animal a:choice) {
	    if (a.getName().equals(animal.getName())){
	      zoo.remove(a);
	     a.setAppetite(Appetite.HUNGRY);
	      zoo.add(a);
	    return true;
	}

	}
	return false;
    }

   public String[] countLiving(){
    String [] result=new  String [16];
    int i=0;
       for (Animal a: zoo) {
	result [i] =a.getName()+ " - " +counter(a);
	 i++;
	       }
         result[result.length-1]=Plant.name+" - " +plants.size();
	return result;
    }

    private int counter(Animal animal){
	return zoo.stream().filter(x-> x.getName().equals(animal.getName())).toArray().length;

    }


}





