package graphicInterface;

import simulation.Simulation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelExpectation {
    static int expected;


    public PanelExpectation() {
        final JFrame theFrame = new JFrame();
        theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        theFrame.setTitle("время ожидания окончания симуляции - " + expected + " milliseconds" + "\n");
        theFrame.setSize(600, 150);
        theFrame.setLocation(550, 400);
        theFrame.setVisible(true);
        JButton jButton = new JButton("start simulation");
        theFrame.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    theFrame.setVisible(false);
                    Simulation.stepSimulation();
                    PanelIslandState panelTwo = new PanelIslandState();
                    panelTwo.start();
                    PanelContinued panelFo = new PanelContinued();
                    panelFo.start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

    }


    public static void setExpected(int exp) {
        expected = exp;
    }

}




