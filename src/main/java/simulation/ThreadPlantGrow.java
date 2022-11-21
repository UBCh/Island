package simulation;

import scenarios.Cell;

public class ThreadPlantGrow extends Thread{




    Cell cell;

    public ThreadPlantGrow(Cell cell) {
	this.cell = cell;
    }

    @Override
    public void run( ) {
	try {
	    cell.plantGrow();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
