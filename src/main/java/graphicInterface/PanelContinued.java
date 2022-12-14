package graphicInterface;

import lombok.NoArgsConstructor;
import scenarios.PlayingField;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public  class PanelContinued {
 PlayingField playingField=PlayingField.getInstance();
    public PanelContinued() throws Exception {
	start();
    }

    public  void start(){
    final JFrame theFrame = new JFrame();
	theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	theFrame.setTitle("хотите продолжить наблюдения?");
	theFrame.setSize(600, 150);
	theFrame.setLocation(550, 400);
	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new FlowLayout());
	theFrame.setVisible(true);
	theFrame.setContentPane(mainPanel);
	JButton jButton = new JButton("start ");
	JButton jButton2 = new JButton("interrupt ");
	mainPanel.add(jButton);
	mainPanel.add(jButton2);
	jButton.addActionListener(new ActionListener( ) {
	    @Override
	    public void actionPerformed(ActionEvent e) {

	try {

	 if (PlayingField.everyBodyDied()){
	     PanelOfDeath panelSix = new PanelOfDeath();
	     Thread.sleep(5000);
	     System.exit(0);
	 }
	  int expected=PlayingField.cycleTime*1000;
	 report();
	  playingField.startMigration();
			} catch (Exception ex) {
		    ex.printStackTrace();
		}



	}});

	jButton2.addActionListener(new ActionListener( ) {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    PlayingField.stopMigration();
		} catch (InterruptedException ex) {
		    ex.printStackTrace();
		}
		System.exit(0);
					    }
	});}


    private void report() throws Exception {
	playingField.stopMigration();
	playingField.report();
	PanelIslandState panelTwo = new PanelIslandState();
	panelTwo.start();
	PanelContinued panelFo = new PanelContinued();

    }

}
