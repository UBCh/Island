package graphicInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOfDeath {

    public PanelOfDeath() {
	final JFrame theFrame = new JFrame();
	theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	theFrame.setTitle("умерли все");
	theFrame.setSize(600, 150);
	theFrame.setLocation(550, 400);
	theFrame.setVisible(true);
	JButton jButton = new JButton("stop simulation");
	theFrame.add(jButton);
	jButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    System.exit(0);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    }
	});

    }

}



