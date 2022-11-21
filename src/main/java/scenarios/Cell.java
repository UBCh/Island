package scenarios;

import entities.entitiy.RandomNumbers;
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
//    public static scenarios.Cell instance;

      public CopyOnWriteArrayList<Plant> plants = new CopyOnWriteArrayList<>();
      public CopyOnWriteArrayList<Animal> zoo = new CopyOnWriteArrayList<>();

//    private scenarios.Cell() {
//    populate();
//    }
//
//    public static scenarios.Cell getInstance(scenarios.Ark ark) {
//	if (instance == null) {
//	    setArk(ark);
//	    instance = new scenarios.Cell();
//	    var d=instance;
//	}
//	return instance;
//    }


    public Cell() throws Exception {
	populate();
    }

    public static void setArk(Ark a) {
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

//    public CopyOnWriteArrayList<Animal> getZoo() {
//	return zoo;
//    }

    public CopyOnWriteArrayList<Plant> getPlants() {
	return plants;
    }

    //    покормить
//    public void eat(){
//	for (Animal animal: zoo){
//	       switch (animal.getAppetite()){
//		case HUNGRY -> {
//		 while (animal.getAppetite()== Appetite.HUNGRY){
//		    double getFood=getFood(animal);
//		if (getFood==0) {animal.setLifeSensor(LifeSensor.DEAD); break;}
//		    animal.eatUp(getFood);}
//	    }
//	}
//    }}

    public void eat(Animal animal){
	    switch (animal.getAppetite()){
		case HUNGRY -> {
		    while (animal.getAppetite()== Appetite.HUNGRY){
			double getFood=getFood(animal);
			if (getFood==0) {animal.setLifeSensor(LifeSensor.DEAD); break;}
			animal.eatUp(getFood);}
		}
	    }
    }


    private int determineCount(Animal animal) throws Exception {
	RandomNumbers randomNumbers = new RandomNumbers(animal.getNumberOfStart() + 1);
	return randomNumbers.call();
    }

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

//    public void replicate() throws Exception {
//	CopyOnWriteArrayList< Animal> choice=new CopyOnWriteArrayList<>();
//	 choice.addAll(zoo);
//	for (Animal animal: choice) {
//	       if (animal.getAppetite()==Appetite.WELL_FED){
//		   if (findACouple(animal)){
//		   zoo.addAll(animal.replicate());} }
//	}
//
//    }

    public void replicate(Animal animal) throws Exception {
	    if (animal.getAppetite()==Appetite.WELL_FED){
		if (findACouple(animal)){
		    zoo.addAll(animal.replicate());} }
	    }


// отдельный метод рост растений
public void plantGrow() throws Exception{
	if (plants.size()!=0){
    for (Plant plant: plants){
	plants.add(plant.growing());
    }}}


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

//   public String[] countLiving(int vertical, int horizontal){
//       String [] result=new  String [17];
//       CopyOnWriteArrayList<Animal> zooFake=new CopyOnWriteArrayList<>();
//      zooFake.addAll(zoo);
//      result[result.length-1]="cell with coordinates____________"+ vertical +" =vertical" +horizontal+" +horizontal";
//       int i=result.length-2;
//       for (Animal a: ark.getAnimals()) {
//	   result [i] =a.getName()+ " - " +counter(a);
//	   zooFake.stream().forEach(x->{
//	       if (x.getName().equals(a)){zooFake.remove(x);}
//	   });
//	   i--;
//
//       }
//       result[0]=Plant.name+" - " +plants.size();
//
//       return result;
//   }

    public String[] countLiving(int vertical, int horizontal){
	String[] result=new String[17];
//	CopyOnWriteArrayList<Animal> zooFake=new CopyOnWriteArrayList<>();
	cleanUp();
//	zooFake.addAll(zoo);
	result[0]="cell with coordinates____________"+ vertical +" =vertical  " +horizontal+" =horizontal";
	result[1]=Plant.name+" - " +plants.size();
	int i=2;
	for (Animal a: ark.getAnimals()) {
	    result[i]=a.getName()+ " - " +counter(a);
	    i++;
//	    zooFake.stream().forEach(x->{
//		if (x.getName().equals(a.getName())){zooFake.remove(x);}
//	    });
	 	}
		return result;
    }





    private int counter(Animal animal){

	return zoo.stream()
		.filter(x-> x.getName()
			.equals(animal.getName()))
		.toArray()
		.length;
    }

    public void cleanUp(){
	zoo.stream().forEach(x->{

	   if(x.getLifeSensor()==LifeSensor.DEAD){zoo.remove(x);}
	});
    }

}





