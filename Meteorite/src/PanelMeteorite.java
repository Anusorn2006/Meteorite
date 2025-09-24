import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.net.URL;

        class PanelMeteorite extends JFrame {

            int amountMeteorite = 50;
            JLabel[] meteorite = new JLabel[amountMeteorite];
            meteoriteThread[] mtoT = new meteoriteThread[amountMeteorite];
            ImageIcon[] mtoIcon = new ImageIcon[amountMeteorite];
            Random rand = new Random();

            PanelMeteorite() {
                setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

                JPanel BackG = new JPanel(null);
                BackG.setBackground(Color.BLACK);

        for (int i = 0; i < meteorite.length; i++) {
            String chosenFile = Constants.imageFiles[rand.nextInt(Constants.imageFiles.length)]; 

            URL url = PanelMeteorite.class.getResource("/" + chosenFile); // ไฟล์อยู่ใน classpath root
            ImageIcon rawIcon = new ImageIcon(url);
            Image scaled = rawIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            mtoIcon[i] = new ImageIcon(scaled);


            meteorite[i] = new JLabel(mtoIcon[i]);
            meteorite[i].setSize(50, 50);

            // สุ่มตำแหน่ง
            int margin = 50;  // ห่างจากขอบ 50 พิกเซล

            meteorite[i].setLocation(
                    rand.nextInt(getWidth() - meteorite[i].getWidth() - 2*margin) + margin,
                    rand.nextInt(getHeight() - meteorite[i].getHeight() - 2*margin) + margin
            );

            BackG.add(meteorite[i]);

            // ความเร็วสุ่ม 1-4
            double dx = rand.nextDouble(0.1, 0.5) ;
            double dy = rand.nextDouble(0.1, 0.5) ;

            mtoT[i] = new meteoriteThread(meteorite[i], BackG, dx, dy);
            mtoT[i].start();
        }

        add(BackG);
        setVisible(true);
    }
}
