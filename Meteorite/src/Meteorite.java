import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;


public class Meteorite {
    public static void main(String[] args) {

    new PanelMeteorite();

    }
}


class PanelMeteorite extends JFrame{

    int amountMeteorite = 100;
    JLabel []meteorite = new JLabel[amountMeteorite];
    meteoriteThread []mtoT = new meteoriteThread[amountMeteorite];
    ImageIcon []mtoIcon = new ImageIcon[amountMeteorite];
    Random rand = new Random();




    PanelMeteorite(){

        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel BackG = new JPanel(null);
        BackG.setBackground(Color.BLACK);

        String []imageFiles = {"meteorite2.png", "meteorite3.png", "meteorite4.png", "meteorite5.png", "meteorite6.png"} ;





        for (int i = 0; i < meteorite.length; i++) {

            String chosenFile = imageFiles[rand.nextInt(imageFiles.length)];

           mtoIcon[i] = new ImageIcon(
                    new ImageIcon(System.getProperty("user.dir") + File.separator + "\\src\\" + chosenFile).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            meteorite[i] = new JLabel(mtoIcon[i]);
            meteorite[i].setSize(40,40);
            meteorite[i].setLocation(rand.nextInt(0, 500), rand.nextInt(0,500));
            BackG.add(meteorite[i]);

            int dx = rand.nextInt(1,5);
            int dy = rand.nextInt(1, 5);



            mtoT[i] = new meteoriteThread(meteorite[i], BackG, dx, dy);
            mtoT[i].start();
        }


        add(BackG);
        setVisible(true);


    }
}

class meteoriteThread extends Thread {
    JLabel label;
    JPanel panel;
    int dx, dy;

    meteoriteThread(JLabel label, JPanel panel, int dx, int dy) {
        this.label = label;
        this.panel = panel;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void run() {

        while (true) {
            int newX = label.getX() + dx;
            int newY = label.getY() + dy;

            if(newX <= 0 || newX >= panel.getWidth() - label.getWidth()){
                dx = -dx;
            }
            if(newY <= 0 || newY >= panel.getHeight() - label.getHeight()){
                dy = -dy;
            }

            label.setLocation(label.getX() + dx, label.getY() + dy);

            try {
                Thread.sleep(16);

            } catch (InterruptedException e) {
            }


        }
    }
}