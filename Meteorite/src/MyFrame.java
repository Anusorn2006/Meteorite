import javax.swing.*;

class MyFrame extends JFrame {

    PanelMeteorite BackG = new PanelMeteorite();

    MyFrame(){

        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(BackG);

        meteoriteThread meteoriteThread = new meteoriteThread(BackG);
        meteoriteThread.start();

        setVisible(true);
    }
}