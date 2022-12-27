import graphicInterface.PanelContinued;
import graphicInterface.PanelIslandState;
import org.junit.jupiter.api.Test;
import scenarios.PlayingField;




class SimulationTest {
 // временный класс для проверки работы остановки  потоков миграции
    @Test
    void stepSimulation() throws Exception {

 PlayingField.setSize(1);
PlayingField.setCycleTime(20);
        PlayingField playingField=PlayingField.getInstance();
        barrier();
        playingField.startMigration();
        playingField.report();
        PanelIslandState panelTwo = new PanelIslandState();
        panelTwo.start();
        PanelContinued panelFo = new PanelContinued();
        panelFo.start();

 }


 private void barrier() throws InterruptedException {
     Thread.sleep(20);
     PlayingField.stopMigration();
 }

}
