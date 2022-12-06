package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.Direction;
import entities.entitiy.RandomNumbers;
import graphicInterface.PanelIslandState;
import lombok.Data;



public class PlayingField {


    public static PlayingField instance;
     public static int sizeOfTheIslandIsHorizontal = 0;
     public static int sizeOfTheIslandIsVertical = 0;
     public static String conditionForStoppingTheSimulation = "";
     public static int CycleTime = 0;


    public static Cell[][] cellSet;

    private String[] resultSimulation = new String[0];

    private PlayingField() throws Exception {
	createPlayField();
    }


    public static PlayingField getInstance() throws Exception {
	if (instance == null) {

	    instance = new PlayingField();
	}
	return instance;
    }


    public static void setSize(int siz) {
	sizeOfTheIslandIsHorizontal = siz;
	sizeOfTheIslandIsVertical = siz;
    }


    public static void setCycleTime(int cycleTime) {
	CycleTime = cycleTime;
    }

    public static int getCycleTime() {
	return CycleTime;
    }

    private static void createPlayField() throws Exception {
	cellSet = new Cell[sizeOfTheIslandIsVertical][sizeOfTheIslandIsHorizontal];

	for (int i = 0; i < sizeOfTheIslandIsVertical; i++) {
	    for (int j = 0; j < sizeOfTheIslandIsHorizontal; j++) {
		cellSet[i][j] = new Cell();
	    }
	}
    }

    public static boolean everyBodyDied() {
	int countLiv = 0;
	Cell cell;
	for (int i = 0; i < sizeOfTheIslandIsVertical; i++) {
	    for (int j = 0; j < sizeOfTheIslandIsHorizontal; j++) {
		cell = cellSet[i][j];
		for (Animal a : cell.zoo) {
		    countLiv = cell.counter(a);
		    if (countLiv > 0) {
			return false;
		    }
		}
	    }
	}
	return true;
    }

       public Cell moveAround(Animal animal, int startY, int startX) throws Exception {
	System.out.println("пинок под жопу с координат " + startY + startX);
	Cell current = cellSet[startY][startX];
	int distance = determineDistance(animal);
	current.zoo.remove(animal);
	int iNew = startY;
	int jNew = startX;
	switch (determineDirection()) {
	    case up -> { iNew = up(startY,distance);}
	    case down -> { iNew = down(startY,distance);}
	    case left -> {jNew =left(startX,distance);}
	    case right -> {jNew = right(startX,distance);}
	}
	return moveToAnotherCell(iNew, jNew, animal);
    }

    private int up(int startY, int distance){
	if (startY - distance > 0) {
	   return startY - distance;
	} else {
	   return  0;
	}
	    }

    private int down(int startY, int distance){
	if (startY + distance <= sizeOfTheIslandIsVertical - 1) {
	   return startY + distance;
	} else {
	  return sizeOfTheIslandIsVertical - 1;
	}
    }

    private int left(int startX, int distance){
	if (startX - distance > 0) {
	  return startX - distance;
	} else {
	  return  0;
	}
    }

    private int right(int startX, int distance){
	if (startX + distance <= sizeOfTheIslandIsHorizontal - 1) {
	   return startX + distance;
	} else {
	    return sizeOfTheIslandIsHorizontal - 1;
	}
    }


    private Cell moveToAnotherCell(int vertical, int horizontal, Animal animal) {
	System.out.println("выбрали направление");
	cellSet[vertical][horizontal].zoo.add(animal);
	cellSet[vertical][horizontal].testTread(vertical, horizontal);
	return cellSet[vertical][horizontal];
    }

    private int determineDistance(Animal animal) throws Exception {
	int speed= (int) FabricAnimal.getConfigAnimal(animal, "speed");
	RandomNumbers randomNumbers = new RandomNumbers(speed + 1);
	return randomNumbers.call();
    }

    private Direction determineDirection() throws Exception {
	RandomNumbers randomNumbers = new RandomNumbers(5);
	int random = randomNumbers.call();
	switch (random) {
	    case 1:
		return Direction.up;
	    case 2:
		return Direction.down;
	    case 3:
		return Direction.left;
	    case 4:
		return Direction.right;
	}
	return null;
    }


    public void report() {
	String[] tmp;
	for (int i = 0; i < sizeOfTheIslandIsVertical; i++) {
	    for (int j = 0; j < sizeOfTheIslandIsHorizontal; j++) {
		tmp = cellSet[i][j].countLiving(i, j);
		getResult(tmp);
	    }
	}
	PanelIslandState.setMessage(resultSimulation);
	cleanReport();
    }

    private void getResult(String[] tmp) {
	String[] strings = new String[resultSimulation.length + tmp.length];
	for (int i = 0; i < resultSimulation.length; i++) {
	    strings[i] = resultSimulation[i];
	}
	int i = resultSimulation.length;
	for (int j = 0; j < tmp.length; j++) {
	    strings[i] = tmp[j];
	    i++;
	}
	setResultSimulation(strings);
    }

    public void setResultSimulation(String[] resultSimulation) {
	this.resultSimulation = resultSimulation;
    }

    private void cleanReport() {
	resultSimulation = new String[0];
    }

}
