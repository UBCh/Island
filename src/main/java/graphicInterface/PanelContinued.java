package graphicInterface;

import scenarios.PlayingField;
import simulation.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class PanelContinued {

    public PanelContinued()
    {}
	public  void start(){
    final JFrame theFrame = new JFrame();
	theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	theFrame.setTitle("хотите продолжить симуляцию?");
	theFrame.setSize(600, 150);
	theFrame.setLocation(550, 400);
	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new FlowLayout());
	theFrame.setVisible(true);
	theFrame.setContentPane(mainPanel);
	JButton jButton = new JButton("start simulation");
	JButton jButton2 = new JButton("interrupt simulation");
	mainPanel.add(jButton);
	mainPanel.add(jButton2);
	jButton.addActionListener(new ActionListener( ) {
	    @Override
	    public void actionPerformed(ActionEvent e) {

	try {

	 if (PlayingField.everybodyDied()){
	     PanelOfDeath panelSix = new PanelOfDeath();
	     panelSix.start();

	 }
	    theFrame.setVisible(false);
	 Simulation.stepSimulation();
	    PanelIslandState panelTwo = new PanelIslandState();
	    panelTwo.start();
	    PanelContinued panelFo = new PanelContinued();
	    panelFo.start();
		} catch (Exception ex) {
		    ex.printStackTrace();
		}



	}});

	jButton2.addActionListener(new ActionListener( ) {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
					    }
	});}




}
