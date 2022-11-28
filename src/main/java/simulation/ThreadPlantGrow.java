package simulation;

import scenarios.Cell;

public class ThreadPlantGrow extends Thread {


     private final Cell cell;

    public ThreadPlantGrow(Cell cell) {
	this.cell = cell;
    }


    @Override
    public void run() {
	try {
	cell.plantGrow();
	    Thread.interrupted();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
