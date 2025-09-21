import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.net.URL;

class PanelMeteorite extends JFrame {

    int amountMeteorite = 100;
    JLabel[] meteorite = new JLabel[amountMeteorite];
    meteoriteThread[] mtoT = new meteoriteThread[amountMeteorite];
    ImageIcon[] mtoIcon = new ImageIcon[amountMeteorite];
    Random rand = new Random();

    PanelMeteorite() {
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel BackG = new JPanel(null);
        BackG.setBackground(Color.BLACK);

        String[] imageFiles = {"meteorite2.png", "meteorite3.png", "meteorite4.png", "meteorite5.png", "meteorite6.png"};

        for (int i = 0; i < meteorite.length; i++) {
            String chosenFile = imageFiles[rand.nextInt(imageFiles.length)];

            // โหลดรูปตรง ๆ
//            ImageIcon rawIcon = new ImageIcon(chosenFile);
//            Image scaled = rawIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//            mtoIcon[i] = new ImageIcon(scaled);

            URL url = PanelMeteorite.class.getResource("/" + chosenFile); // ไฟล์อยู่ใน classpath root
            ImageIcon rawIcon = new ImageIcon(url);
            Image scaled = rawIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            mtoIcon[i] = new ImageIcon(scaled);


            meteorite[i] = new JLabel(mtoIcon[i]);
            meteorite[i].setSize(50, 50);

            // สุ่มตำแหน่ง
            int maxX = 600 - meteorite[i].getWidth();
            int maxY = 600 - meteorite[i].getHeight();
            meteorite[i].setLocation(rand.nextInt(Math.max(1, maxX)), rand.nextInt(Math.max(1, maxY)));

            BackG.add(meteorite[i]);

            // ความเร็วสุ่ม 1-4
            int dx = rand.nextInt(4) + 1;
            int dy = rand.nextInt(4) + 1;

            mtoT[i] = new meteoriteThread(meteorite[i], BackG, dx, dy);
            mtoT[i].start();
        }

        add(BackG);
        setVisible(true);
    }
}
