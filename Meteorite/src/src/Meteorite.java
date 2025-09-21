package src;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Meteorite {
    public static void main(String[] args) {


    new PanelMeteorite();


    }
}


class PanelMeteorite extends JFrame{
    PanelMeteorite(){

        setSize(600,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel BackG = new JPanel();

        BackG.setBackground(Color.BLACK);


        ImageIcon mtoIcon = new ImageIcon(
                new ImageIcon(System.getProperty("user.dir") + File.separator + "\\src\\" + "pop.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
        JLabel meteorite4 = new JLabel(mtoIcon, JLabel.CENTER);
        BackG.add(meteorite4);
        add(BackG);
        meteoriteThread mtoT = new meteoriteThread(meteorite4, BackG);
        mtoT.start();


        setVisible(true);


    }
}

class meteoriteThread extends Thread {
    JLabel label;
    JPanel panel;
    int dy = 5;
    int dx = 5;

    meteoriteThread(JLabel label, JPanel panel) {
        this.label = label;
        this.panel = panel;
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