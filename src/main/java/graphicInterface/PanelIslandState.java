package graphicInterface;

import javax.swing.*;

public class PanelIslandState extends JFrame {
    static String [] message;

    public static void setMessage(String[] mess) {
	message = mess;
    }

    public PanelIslandState()  {

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
	for(int i=0; i<message.length;i++)
	{
	    theText.append(message[i] + "\n");
	}
	JScrollPane scroll = new JScrollPane(theText);
	scroll.setBounds(10, 11, 455, 249);

	getContentPane().add(scroll);
	setLocationRelativeTo ( null );
    }

    }







