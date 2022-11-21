package start;

import simulation.Simulation;
import userInterface.PanelMenu;

public class Controller {


    public static void main(String[] args) throws Exception {
        Config config=new Config();
    config.setConfigurations();
    Simulation simulation=new Simulation();
//        int countStep= simulation.getPlayingField().getDurationOfTheSimulationCycle();
//
//        for (int i=0; i<countStep; i++){
//
     simulation.stepSimulation();

//        PanelMenu.getApplication();

        }
    }















