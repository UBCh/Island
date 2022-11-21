package simulation;

import entities.entitiy.Animal;
import scenarios.PlayingField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {

    public PlayingField playingField = PlayingField.getInstance();
    int x = PlayingField.cellSet.length;
    int y = PlayingField.cellSet[0].length;
    boolean simulationLivesOn = true;
    ExecutorService service = Executors.newFixedThreadPool(10);



    public Simulation() throws Exception {

    }

    public void stepSimulation() throws Exception {
        while (simulationLivesOn) {
            threadPlantGrow();
            treadLifeSimulation();
            simulationCycle();
        }
        service.shutdown();
        playingField.report();
    }


    public void threadPlantGrow() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                // отправлем  клетку   в поток -рост растений
                int finalI = i;
                int finalJ = j;
                Thread thread = new ThreadPlantGrow(PlayingField.cellSet[finalI][finalJ]);
                thread.start();
                service.submit(thread);
            }
        }
    }


    public void treadLifeSimulation() throws Exception {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                for (Animal a : PlayingField.cellSet[i][j].zoo) {
                    Thread thread = new ThreadAnimalLife(a, i, j);
                    thread.start();
                    service.submit(thread);
                }
            }
        }
    }



    public void simulationCycle() throws InterruptedException {
        Thread.sleep(10000);
        simulationLivesOn = false;
    }

}
