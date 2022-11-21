import entities.herbivores.Buffalo;
import org.junit.jupiter.api.Test;
import simulation.Simulation;
import start.Config;
import userInterface.PanelMenu;

class SimulationTest {

    @Test
    void stepSimulation() throws Exception {

        Config config=new Config();
        config.setConfigurations();
      Simulation simulation=new Simulation();
      simulation.playingField.moveAround(new Buffalo(1,1),1,1);
//     simulation.threadPlantGrow();
//      simulation.treadLifeSimulation();
//      simulation.playingField.report();
//        var s=simulation.getPlayingField().cellSet[0][0];
//        simulation.playingField.moveAround();
//        var d=simulation.getPlayingField().cellSet[0][0];



    }
}