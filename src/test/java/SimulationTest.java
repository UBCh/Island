import entities.entitiy.Animal;
import entities.herbivores.Buffalo;
import org.junit.jupiter.api.Test;
import scenarios.Cell;
import scenarios.FabricAnimal;
import scenarios.PlayingField;
import simulation.Simulation;
import simulation.ThreadAnimalLife;


class SimulationTest {

    @Test
    void stepSimulation() throws Exception {


   PlayingField.setSize(2);
   PlayingField.setCycleTime(20);
   PlayingField playingField=PlayingField.getInstance();
    Animal animal=playingField.cellSet[0][0].zoo.get(0);
    playingField.moveAround(animal,0,0);
 }
}
