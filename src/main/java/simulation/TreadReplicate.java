package simulation;

import scenarios.Cell;

public class TreadReplicate extends Thread{


    Cell cell;

    public TreadReplicate(Cell cell) {
	this.cell = cell;
    }

    @Override
    public void run( ) {
	try {
	    cell.replicate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
