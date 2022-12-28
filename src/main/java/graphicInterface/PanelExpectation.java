package graphicInterface;

import scenarios.PlayingField;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelExpectation {
    static int expected;
    PlayingField playingField=PlayingField.getInstance();

    public PanelExpectation() throws Exception {
        final JFrame theFrame = new JFrame();
        theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        theFrame.setTitle("время ожидания отчета - " + expected + " milliseconds" + "\n");
        theFrame.setSize(600, 150);
        theFrame.setLocation(550, 400);
        theFrame.setVisible(true);
        JButton jButton = new JButton("start \n" +
                "migration");
        theFrame.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    theFrame.setVisible(false);
                      report();
                      playingField.startMigration();
                      } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    private void report() throws Exception {
        playingField.stopMigration();
        playingField.report();
        PanelIslandState panelTwo = new PanelIslandState();
        panelTwo.start();
        PanelContinued panelFo = new PanelContinued();

    }
    public static void setExpected(int exp) {
        expected = exp;
    }

}




