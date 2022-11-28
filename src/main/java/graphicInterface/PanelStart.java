package graphicInterface;

import javax.swing.*;

public class PanelStart extends JFrame  {
    static boolean vision =true;
    public PanelStart()  {
        setTitle("island");
        setSize(600,150);
        setLocationByPlatform(true);
        PanelConfiguration panelOne=new PanelConfiguration();
        add(panelOne);
       if (vision ==false){visible();}

    }
    public void start(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void setVision(boolean vision) {
        PanelStart.vision = vision;
    }

    private  void visible(){
       setVisible(false);
    }


}
