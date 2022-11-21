package userInterface;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelOne {
    static JFrame jFrame = getJFrame( );
    static JPanel jPanel = new JPanel( );

    public static void getPanelOne() throws IOException {
        jFrame.add(jPanel);
        jPanel.add(new JLabel("введите ключ"));
        JTextField jTextField2 = new JTextField(10);
        // получаем текс из файла
        jPanel.add(jTextField2);
        JButton jButton = new JButton(" включить шифратор");
        jPanel.add(jButton);
        jButton.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Key.setKey(Integer.parseInt(jTextField2.getText( )));    // сетаем Key
//
//                JFrame jFrame = new JFrame( ) {
//                };
//                jFrame.setVisible(true);
//                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                jFrame.setTitle("ПОЛУЧИТЕ, РАСПИШИТЕСЬ");
//                Toolkit toolkit = Toolkit.getDefaultToolkit( );  // инструменты для окна
//                Dimension dimension = toolkit.getScreenSize( ); // получаем размер экрана
//                jFrame.setBounds(dimension.width / 2 - 500, dimension.height / 2 - 400, 1000, 800);
//                String itog = null;
//                try {
//                    itog=StrimDao.inputFiles( );
//                    itog = Analisator.analisatorText(itog);
//
//                } catch (IOException ex) {
//                    ex.printStackTrace( );
//                } catch (SymbolNotFoundException ex) {
//                    ex.printStackTrace( );
//                }
//                catch (TextNotFoundException exx){
//                    exx.printStackTrace( );
//                }
//               itog=itog.toString();
//                jFrame.add(new MyComponents(itog));
//
//                try {
//                    StrimDao.outputFiles(itog);
//                } catch (IOException ex) {
//                    ex.printStackTrace( );
//                }

            }


        });


    }

    static JFrame getJFrame() {

        JFrame jFrame = new JFrame( ) {
        };
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle(" ПОМЕСТИТЕ ТЕКСТ В text.txt и ЗАДАЙТЕ КЛЮЧ ");
        Toolkit toolkit = Toolkit.getDefaultToolkit( );  // инструменты для окна
        Dimension dimension = toolkit.getScreenSize( ); // получаем размер экрана
        jFrame.setBounds(dimension.width / 2 - 500, dimension.height / 2 - 400, 500, 300);
        return jFrame;
    }

}
