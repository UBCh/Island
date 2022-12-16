package graphicInterface;



import scenarios.PlayingField;




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
        add(new JLabel("установите длительность периода отчета о состоянии острова(в секундах)"));
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

                    PlayingField playingField=PlayingField.getInstance();
                    playingField.report();
                    PanelIslandState panelTwo=new PanelIslandState();
                    panelTwo.start();
                   int expected=playingField.getCycleTime()*1000;
                   PanelExpectation.setExpected(expected);
                    PanelExpectation panelExpectation=new PanelExpectation();
                                    } catch (Exception ex) {
                    ex.printStackTrace();
                }



                        }
        });
    }
}
