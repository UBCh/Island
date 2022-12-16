package scenarios;

import entities.entitiy.Animal;
import entities.entitiy.Direction;
import entities.entitiy.FabricAnimal;
import entities.entitiy.RandomNumbers;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AnimalMigration {

    PlayingField playingField;

    {
	try {
	    playingField = PlayingField.getInstance();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void moveAround(Animal animal, int startY, int startX) throws Exception {
	Cell current = playingField.cellSet[startY][startX];
	int distance = determineDistance(animal);
	current.zoo.remove(animal);
	int iNew = startY;
	int jNew = startX;
	switch (determineDirection()) {
	    case up -> {
		iNew = up(startY, distance);
	    }
	    case down -> { iNew = down(startY,distance);}
	    case left -> {jNew =left(startX,distance);}
	    case right -> {jNew = right(startX,distance);}
	}
	 moveToAnotherCell(iNew, jNew, animal);
    }

    private int up(int startY, int distance){
	if (startY - distance > 0) {
	    return startY - distance;
	} else {
	    return  0;
	}
    }

    private int down(int startY, int distance){
	if (startY + distance <= playingField.sizeOfTheIslandIsVertical - 1) {
	    return startY + distance;
	} else {
	    return playingField.sizeOfTheIslandIsVertical - 1;
	}
    }

    private int left(int startX, int distance){
	if (startX - distance > 0) {
	    return startX - distance;
	} else {
	    return  0;
	}
    }

    private int right(int startX, int distance){
	if (startX + distance <= playingField.sizeOfTheIslandIsHorizontal - 1) {
	    return startX + distance;
	} else {
	    return playingField.sizeOfTheIslandIsHorizontal - 1;
	}
    }


    private void moveToAnotherCell(int vertical, int horizontal, Animal animal) {
	playingField.cellSet[vertical][horizontal].zoo.add(animal);
	    }

    private int determineDistance(Animal animal) throws Exception {
	int speed= (int) FabricAnimal.getConfigAnimal(animal, "speed");
	RandomNumbers randomNumbers = new RandomNumbers(speed + 1);
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





}
