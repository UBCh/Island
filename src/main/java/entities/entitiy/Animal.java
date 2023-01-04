package entities.entitiy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
@Data
public abstract class Animal {

    @ConfigurationAnimal( name = "", specifications = Specifications.AGGRESSIVE, mass = 0D, numberOfAnimalsInCage = 0, speed = 0, numberOfStart = 0)

    private Appetite appetite;
    private LifeSensor lifeSensor;
    private int numberOfCubs;
    private double howMuchFood;
    private double foodMass;
    private TreeMap<Integer, String> probabilityOfEating;




    public void eatUp(double mass) {
    }

    public void moveAround() {
    }

    public CopyOnWriteArrayList<Animal> replicate() throws Exception {
	return null;
    }

    public void toDie()  {
    }

    public void life() {
         }



}
