package entities.plants;



public class Plant {
    double mass=1;
    int numberOfAnimalsInCage=200;
    int numberOfStart;

    public static String name="Plant";

    public String getName() {
	return name;
    }

    public Plant( int numberOfPlantAtTheStart ) {
	this.numberOfStart=numberOfPlantAtTheStart;
    }

    public Plant growing() {
	return new Plant(numberOfStart);
    }

    public int getNumberOfAnimalsInCage() {
	return numberOfAnimalsInCage;
    }

    public double getMass() {
	return mass;
    }

    public int getNumberOfStart() {
	return numberOfStart;
    }
}
