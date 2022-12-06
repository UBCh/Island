package simulation;

import entities.entitiy.Animal;
import entities.entitiy.LifeSensor;
import graphicInterface.PanelOfDeath;
import lombok.Data;
import scenarios.PlayingField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Data

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
                if (PlayingField.everyBodyDied()) {
                    PanelOfDeath panelOfDeath = new PanelOfDeath();
                }
                for (Animal a : PlayingField.cellSet[i][j].zoo) {
                    if (a.getLifeSensor() == LifeSensor.DEAD) {
                        break;
                    }
                    threadAnimalLife = new ThreadAnimalLife(a, i, j);
                    threadAnimalLife.start();
                    serviceLife.submit(threadAnimalLife);
                    Thread threadPlantGrow= new ThreadPlantGrow(PlayingField.cellSet[i][j]);
                    threadPlantGrow.start();
                    servicePlants.submit(threadPlantGrow);
                    Thread.sleep(3000);
                    PlayingField.cellSet[i][j].cleanUp();
                }
            }
        }
    }


    private static void simulationCycle() {
        Thread threadStop = new ThreadStop();
        threadStop.start();
    }

    private static class ThreadStop extends Thread {


        int time = PlayingField.CycleTime * 1000 - 3000;

        @Override
        public void run() {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Simulation.simulationLivesOn = false;
            Thread.interrupted();
        }

    }

}
