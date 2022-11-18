package simulation;

import scenarios.Cell;

public class TreadEat extends Thread{




    Cell cell;

    public TreadEat(Cell cell) {
	this.cell = cell;
    }

    @Override
    public void run( ) {
	cell.eat();
    }
}
