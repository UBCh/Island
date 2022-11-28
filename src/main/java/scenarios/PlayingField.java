package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.Direction;
import entities.entitiy.RandomNumbers;
import graphicInterface.PanelIslandState;

public class PlayingField {


    public static PlayingField instance;
    static public int sizeOfTheIslandIsHorizontal = 0;
    static public int sizeOfTheIslandIsVertical = 0;
    static public String conditionForStoppingTheSimulation = "";
    static public int CycleTime = 0;


    public static Cell[][] cellSet;

    private String[] resultSimulation = new String[0];

    private PlayingField() throws Exception {
	createPlayField();
    }

    public static int getCycleTime() {
	return CycleTime;
    }

    public static void setCycleTime(int duration) {
	CycleTime = duration;
    }

    public static PlayingField getInstance() throws Exception {
	if (instance == null) {

	    instance = new PlayingField();
	}
	return instance;
    }

    public static void setSizeOfTheIslandIsHorizontal(int sizeHorizontal) {
	sizeOfTheIslandIsHorizontal = sizeHorizontal;
    }

    public static void setSizeOfTheIslandIsVertical(int sizeVertical) {
	sizeOfTheIslandIsVertical = sizeVertical;
    }

    public static void setConditionForStoppingTheSimulation(String forStoppingTheSimulation) {
	conditionForStoppingTheSimulation = forStoppingTheSimulation;
    }


    private static void createPlayField() throws Exception {
	cellSet = new Cell[sizeOfTheIslandIsVertical][sizeOfTheIslandIsHorizontal];

	for (int i = 0; i < sizeOfTheIslandIsVertical; i++) {
	    for (int j = 0; j < sizeOfTheIslandIsHorizontal; j++) {
		cellSet[i][j] = new Cell();
	    }
	}
    }

    public static boolean everybodyDied() {
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

    public void setResultSimulation(String[] resultSimulation) {
	this.resultSimulation = resultSimulation;
    }

    public Cell moveAround(Animal animal, int startY, int startX) throws Exception {
	System.out.println("пинок под жопу с координат " + startY + startX);
	Cell current = cellSet[startY][startX];
	int distance = determineDistance(animal);
	current.zoo.remove(animal);
	int iNew = startY;
	int jNew = startX;
	switch (determineDirection()) {
	    case up -> {
		if (startY - distance > 0) {
		    iNew = startY - distance;
		} else {
		    iNew = 0;
			    }
			}
			case down -> {
			    if (startY + distance <= sizeOfTheIslandIsVertical - 1) {
				iNew = startY + distance;
			    } else {
				iNew = sizeOfTheIslandIsVertical - 1;
			    }
			}
			case left -> {
			    if (startX - distance > 0) {
				jNew = startX - distance;
			    } else {
				jNew = 0;
			    }
			}
			case right -> {
			    if (jNew + distance <= sizeOfTheIslandIsHorizontal - 1) {
				jNew = startX + distance;
			    } else {
				jNew = sizeOfTheIslandIsHorizontal - 1;
			    }
			}
		    }
		  return moveToAnotherCell(iNew, jNew, animal);
		}


    private Cell moveToAnotherCell(int vertical, int horizontal, Animal animal) {
	System.out.println("выбрали направление");
	cellSet[vertical][horizontal].zoo.add(animal);
	cellSet[vertical][horizontal].testTread(vertical, horizontal);
	return cellSet[vertical][horizontal];
    }

    private int determineDistance(Animal animal) throws Exception {
	RandomNumbers randomNumbers = new RandomNumbers(animal.getSpeed() + 1);
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

    private void cleanReport() {
	resultSimulation = new String[0];
    }

}
