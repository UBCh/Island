package simulation;

import entities.entitiy.Animal;
import graphicInterface.PanelOfDeath;
import scenarios.PlayingField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {

    public static PlayingField playingField;

    static {
        try {
            playingField = PlayingField.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int x = PlayingField.cellSet.length;
    static int y = PlayingField.cellSet[0].length;
    static boolean simulationLivesOn = true;

    static ExecutorService serviceLife;
    static ExecutorService servicePlants;

    public Simulation()  {

    }


    public static void stepSimulation() throws Exception {
        simulationLivesOn = true;
        serviceLife = Executors.newFixedThreadPool(100);
        servicePlants = Executors.newFixedThreadPool(10);
        simulationCycle();
        while (simulationLivesOn) {
         treadLifeSimulation();
               }
        servicePlants.shutdownNow();
        serviceLife.shutdownNow();
        playingField.report();
    }



    public static void treadLifeSimulation() throws Exception {
        Thread threadAnimalLife=null;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (PlayingField.everybodyDied()){
                    PanelOfDeath panelOfDeath=new PanelOfDeath();
                }
                    for (Animal a : PlayingField.cellSet[i][j].zoo) {
                        if (!simulationLivesOn){
                            ((ThreadAnimalLife) threadAnimalLife).stopServiceDie(); return;}
                    threadAnimalLife = new ThreadAnimalLife(a, i, j);
                    threadAnimalLife.start();
                    serviceLife.submit(threadAnimalLife);
                    Thread threadPlantGrow= new ThreadPlantGrow(PlayingField.cellSet[i][j]);
              threadPlantGrow.start();
              servicePlants.submit(threadPlantGrow);
               Thread.sleep(10000);
                   PlayingField.cellSet[i][j].cleanUp();
                }
                          }
        }
       ((ThreadAnimalLife) threadAnimalLife).stopServiceDie();
    }


    private static void simulationCycle()  {
       Thread threadStop=new ThreadStop();
       threadStop.start();
    }

}
