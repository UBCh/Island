package streams;

import scenarios.Cell;

public class ThreadPlantGrow extends Thread{


    static boolean stop = true;

    @Override
    public void run() {
	try {
	    while (stop) {
		Cell.plantGrow();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Thread.interrupted();
    }


}
