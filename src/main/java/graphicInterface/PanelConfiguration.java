package graphicInterface;



import scenarios.PlayingField;
import simulation.Simulation;
import simulation.ThreadAnimalLife;
import simulation.ThreadPlantGrow;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelConfiguration extends JPanel{

    public PanelConfiguration() {
         start();
    }

    public void start(){
        add(new JLabel("установите рамер поля, установив длину стороны (количество клеток)"));
        JTextField jTextField1 = new JTextField(10);
        add(jTextField1);
        add(new JLabel("установите длительность цикла симуляции(в секундах)"));
        JTextField jTextField2 = new JTextField(10);
        add(jTextField2);
        JButton jButton = new JButton("начнем заселение");
        add(jButton);
        jButton.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
               PlayingField.setSize(Integer.parseInt(jTextField1.getText()));
              PlayingField.setCycleTime(Integer.parseInt(jTextField2.getText()));
               PanelStart.setVision(false);
                try {
                    Simulation simulation=new Simulation();
                    int expected=Simulation.playingField.getCycleTime()*1000;
                    Simulation.playingField.report();
                   PanelIslandState panelTwo=new PanelIslandState();
                    panelTwo.start();
                    PanelExpectation.setExpected(expected);
                    PanelExpectation panelExpectation=new PanelExpectation();
                                    } catch (Exception ex) {
                    ex.printStackTrace();
                }



                        }
        });
    }
}
