package userInterface;

import simulation.Simulation;
import start.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMenu {
    static JFrame jFrame = getJFrame( );
    static JPanel jPanel1 = new JPanel( );
    static JPanel jPanel2 = new JPanel( );

    public static void getApplication() {
        jPanel1.add(new JLabel("укажите размер игрового поля, длинна стороны в квадратах 1-5"));
        JTextField jTextFieldSize = new JTextField(10);
       // получаем текс из файла
       jPanel1.add(jTextFieldSize);
       jFrame.getContentPane().add(BorderLayout.NORTH, jPanel1);
        jPanel2.add(new JLabel("укажите количество циклов симуляции1-5"));
        JTextField jTextFieldCicl = new JTextField(10);
        // получаем текс из файла
        jPanel2.add(jTextFieldCicl);
        jFrame.getContentPane().add(BorderLayout.CENTER, jPanel2);
        JButton jButton = new JButton("показать исходные данные");
       jButton.setSize(130,130);
        jFrame.getContentPane().add(BorderLayout.SOUTH, jButton);

        jButton.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config config=new Config();
                Config.sizeOfTheIslandIsHorizontal=(Integer.parseInt(jTextFieldSize.getText( )));    // сетаем размер
                Config.sizeOfTheIslandIsVertical=(Integer.parseInt(jTextFieldSize.getText( )));    // сетаем размер
                Config.durationOfTheSimulationCycle=(Integer.parseInt(jTextFieldCicl.getText( )));
                JFrame jFrame = new JFrame( ) {
                };
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setTitle("итак, вы заселили остров");
                Toolkit toolkit = Toolkit.getDefaultToolkit( );  // инструменты для окна
                Dimension dimension = toolkit.getScreenSize( ); // получаем размер экрана
                jFrame.setBounds(dimension.width / 2 - 800, dimension.height / 2 - 400, 800, 400);
                String itog = null;
                try {
                   config.setConfigurations();
                    Simulation simulation=new Simulation();
//                    simulation.getPlayingField().report();
                   simulation.simulationCycle();
                    simulation.stepSimulation();
               } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
    });}

    static JFrame getJFrame() {

        JFrame jFrame = new JFrame( ) {
        };
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("ДЛЯ ЗАПУСКА СИМУЛЯЦИИ УКАЖИТЕ ПАРАМЕТРЫ");
       jFrame.setSize(500,300);
        return jFrame;
    }


}
