package simulation;

import entities.entitiy.Animal;
import scenarios.PlayingField;

public class ThreadAnimalLife extends Thread{



    PlayingField playingField;
    int startY;
    int startX;
    Animal animal;

    public ThreadAnimalLife( Animal animal, int startY, int startX) throws Exception {
	this.playingField = PlayingField.getInstance();
	this.startY=startY;
	this.startX=startX;
	this.animal=animal;
    }

    @Override
    public void run() {
	try {
	    playingField.cellSet[startY][startX].eat(animal);
	    playingField.cellSet[startY][startX].replicate(animal);
	    playingField.cellSet[startY][startX].eat(animal);
	    playingField.moveAround(animal,startY,startX);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
