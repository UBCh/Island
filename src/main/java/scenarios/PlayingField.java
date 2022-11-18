package scenarios;

import entities.entitiy.RandomNumbers;
import entities.entitiy.Animal;
import entities.entitiy.Direction;

public class PlayingField {


    public static PlayingField instance;
    static public int sizeOfTheIslandIsHorizontal = 0;
    static public int sizeOfTheIslandIsVertical = 0;
    static public String conditionForStoppingTheSimulation = "";
    static public int durationOfTheSimulationCycle = 0;

//    static public scenarios.Cell cell;
    public static Cell[][] playField;
    ResultReport resultReport = new ResultReport();
    private String[] resultSimulation = new String[0];

    private PlayingField() throws Exception {
	createPlayField();
    }

    public static int getDurationOfTheSimulationCycle() {
	return durationOfTheSimulationCycle;
    }

    public static void setDurationOfTheSimulationCycle(int duration) {
	durationOfTheSimulationCycle = duration;
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

//    public static void setCell(scenarios.Cell c) {
//	cell = c;
//    }

    private static void createPlayField() throws Exception {
	playField = new Cell[sizeOfTheIslandIsVertical][sizeOfTheIslandIsHorizontal];

	for (int i = 0; i < sizeOfTheIslandIsVertical; i++) {
	    for (int j = 0; j < sizeOfTheIslandIsHorizontal; j++) {
		playField[i][j] = new Cell();
	    }
	}
    }

    public void setResultSimulation(String[] resultSimulation) {
	this.resultSimulation = resultSimulation;
    }

    public void moveAround() throws Exception {
	for (int i = 0; i < sizeOfTheIslandIsVertical; i++) {
	    for (int j = 0; j < sizeOfTheIslandIsHorizontal; j++) {
		Cell current = playField[i][j];
		var set = current.zoo;
		for (Animal animal : set) {
		    int distance = determineDistance(animal);
		    playField[i][j].zoo.remove(animal);
		    int iNew = i;
		    int jNew = j;
		    switch (determineDirection()) {
			case up -> {
			    if (i - distance > 0) {
				iNew = i - distance;
			    } else {
				iNew = 0;
			    }
			}
			case down -> {
			    if (i + distance <= sizeOfTheIslandIsVertical - 1) {
				iNew = i + distance;
			    } else {
				iNew = sizeOfTheIslandIsVertical - 1;
			    }
			}
			case left -> {
			    if (j - distance > 0) {
				jNew = j - distance;
			    } else {
				jNew = 0;
			    }
			}
			case right -> {
			    if (jNew + distance <= sizeOfTheIslandIsHorizontal - 1) {
				jNew = j + distance;
			    } else {
				jNew = sizeOfTheIslandIsHorizontal - 1;
			    }
			}
		    }
		    moveToAnotherCell(iNew, jNew, animal);
		}
	    }
	}

    }


    private void moveToAnotherCell(int vertical, int horizontal, Animal animal) {
	playField[vertical][horizontal].zoo.add(animal);
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

	String[] result = new String[17 * sizeOfTheIslandIsVertical * sizeOfTheIslandIsHorizontal];
	String[] tmp = new String[17];
	for (int i = 0; i < sizeOfTheIslandIsVertical; i++) {
	    for (int j = 0; j < sizeOfTheIslandIsHorizontal; j++) {
		tmp = playField[i][j].countLiving(i, j);
		getResult(tmp);
	    }
	}
	resultReport.writingToFile(resultSimulation);
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


//    public void report() {
//	scenarios.ResultReport resultReport = new scenarios.ResultReport();
//	for (int i = 0; i < sizeOfTheIslandIsVertical; i++) {
//	    for (int j = 0; j < sizeOfTheIslandIsHorizontal; j++) {
//		String[] toStringForWrite = playField[i][j].countLiving(i,j);
//		resultReport.writingToFile(toStringForWrite);
//	    }
//	}
//    }

}
