package graphicInterface;

import javax.swing.*;

public class PanelOfDeath extends JFrame {


    public PanelOfDeath()  {

	setTitle("состояние острова");
	setSize(1000,1000);
	setLocationByPlatform(true);


    }
    public  void start(){
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setVisible(true);
	JTextArea textField = new JTextArea();
	textField.setEditable(false);
	JTextArea theText = new JTextArea(5,25);
	  theText.append("Все умерли, симуляция заврешена");
	    }




}
