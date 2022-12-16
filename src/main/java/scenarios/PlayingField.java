package scenarios;

import entities.entitiy.Animal;
import graphicInterface.PanelIslandState;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PlayingField {


    public static PlayingField instance;
    public static int sizeOfTheIslandIsHorizontal = 0;
    public static int sizeOfTheIslandIsVertical = 0;
    public static int cycleTime = 0;
    static ExecutorService serviceLife ;
    private static boolean barrier = true;

    public static Cell[][] cellSet;

    private static String[] resultSimulation = new String[0];

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

    public static int getCycleTime() {
	return cycleTime;
    }

    public static void setCycleTime(int cycle) {
	cycleTime = cycle;
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

    public static void stopMigration()  {
		barrier = false;
	      }

    public static void startMigration() {
	barrier =true;
	serviceLife = Executors.newFixedThreadPool(100);
	while (barrier) {
	    for (int i = 0; i < sizeOfTheIslandIsVertical; i++) {
		if (!barrier) {
		    break;
		}
		for (int j = 0; j < sizeOfTheIslandIsHorizontal; j++) {
		    if (!barrier) {
			break;
		    }
		    Cell cell = cellSet[i][j];
		    for (Animal a : cell.zoo) {
			if (!barrier) {
			    break;
			}
			// запускаем поток "миграция животного"
			ThreadMigration threadMigration = new ThreadMigration(a, i, j);
			threadMigration.start();
			serviceLife.submit(threadMigration);
		    }
		}
	    }

	}

	serviceLife.shutdownNow();
    }




    public static void report() {
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

    private static void getResult(String[] tmp) {
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

    public static void setResultSimulation(String[] result) {
	resultSimulation = result;
    }


    private static void cleanReport() {
	resultSimulation = new String[0];
    }


    private static class ThreadMigration extends Thread {


	private int startY;
	private int startX;
	private Animal animal;
	AnimalMigration animalMigration=new AnimalMigration();


	private ThreadMigration(Animal animal, int startY, int startX) {
	    this.startY = startY;
	    this.startX = startX;
	    this.animal = animal;

	}


	@Override
	public void run() {
	    try {
		animalMigration.moveAround(animal, startY, startX);
		Thread.interrupted();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

    }

}
