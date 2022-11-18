package simulation;

import scenarios.PlayingField;

public class ThreadMigration extends Thread{



    PlayingField playingField;

    public ThreadMigration(PlayingField playingField) {
	this.playingField = playingField;
    }

    @Override
    public void run( ) {
	try {
	    playingField.moveAround();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
