import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simulation {

  PlayingField playingField= PlayingField.getInstance();
    int x= playingField.playField.length;
    int y=playingField.playField[0].length;

  public Simulation() throws Exception {

  }


    ExecutorService service = Executors.newFixedThreadPool(x*y);

  public void stepSimulation() throws Exception {
      treadEat();
      treadReplicate();
      treadEat();
//      Runnable taskEat = () -> {
//          try {
//              playingField.moveAround();
//          } catch (Exception e) {
//              e.printStackTrace();
//          }
//      };
//      Thread thread = new Thread(taskEat);
//      thread.start();
//      Future result = service.submit(thread);
//      service.shutdown();
      playingField.report();
  }


    private void treadEat() {

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                // отправлем d клетку  поток -питание
                int finalI = i;
                int finalJ = j;
//                Runnable taskEat = () -> playingField.playField[finalI][finalJ].eat();
//                Thread thread = new Thread(taskEat);
//                thread.start();
//               Future result = service.submit(thread);

                playingField.playField[finalI][finalJ].eat();
            }
    }}


    private void treadReplicate() throws Exception {

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                // отправлем клетку в поток пазмножение
                int finalI = i;
                int finalJ = j;
//                Runnable taskEat = () -> {
//                    try {
//                        playingField.playField[finalI][finalJ].replicate();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                };
//               Thread thread = new Thread(taskEat);
//               thread.start();
//               Future result = service.submit(thread);
                playingField.playField[finalI][finalJ].replicate();
            }
        }
    }


}
