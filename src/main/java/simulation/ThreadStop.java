package simulation;

import scenarios.PlayingField;

public class ThreadStop extends Thread{


    int time= PlayingField.CycleTime * 1000-3000;



    @Override
    public void run( ) {
	try {
	    Thread.sleep(time);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	Simulation. simulationLivesOn = false;
	Thread.interrupted();
    }

}
