package simulation;

import scenarios.PlayingField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {

  PlayingField playingField= PlayingField.getInstance();
    int x= playingField.playField.length;
    int y=playingField.playField[0].length;

  public Simulation() throws Exception {

  }

    public PlayingField getPlayingField() {
        return playingField;
    }

    ExecutorService service = Executors.newFixedThreadPool(x*y);

  public void stepSimulation() throws Exception {
      treadEat();
      treadReplicate();
      treadEat();
      treadMigration();
      treadEat();
      playingField.report();
  }


    private void treadEat() {

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                // отправлем  клетку   в поток -питание
                int finalI = i;
                int finalJ = j;
                Thread thread = new TreadEat(playingField.playField[finalI][finalJ]);
               thread.start();
            }
    }}


    private void treadReplicate() throws Exception {

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                // отправлем клетку в поток пазмножение
                int finalI = i;
                int finalJ = j;
           Thread thread = new TreadReplicate( playingField.playField[finalI][finalJ]);
           thread.start();
            }
        }
    }

 private void treadMigration(){
      Thread thread=new ThreadMigration(playingField);
      thread.start();
 }
}
