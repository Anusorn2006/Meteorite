import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class PanelMeteorite extends JFrame {

    int amountMeteorite = Constants.amount_meteorite;
    JLabel[] meteorite = new JLabel[amountMeteorite];
    meteoriteThread[] mtoT = new meteoriteThread[amountMeteorite];
    ImageIcon[] mtoIcon = new ImageIcon[amountMeteorite];

    Random rand = new Random();

    //  เพิ่มฟิลด์ // พื้นหลัง
    private JPanel BackG; 

    PanelMeteorite() {
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackG = new JPanel(null);
        BackG.setBackground(Color.BLACK);

        for (int i = 0; i < meteorite.length; i++) {
            String chosenFile = Constants.imageFiles[rand.nextInt(Constants.imageFiles.length)];

            ImageIcon rawIcon = new ImageIcon("Meteorite/src/Image/" + chosenFile);
            Image scaled = rawIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            mtoIcon[i] = new ImageIcon(scaled);


            meteorite[i] = new JLabel(mtoIcon[i]);
            meteorite[i].setSize(50, 50);

            // สุ่มตำแหน่ง (กันขอบ 50px)
            int margin = 50;
            meteorite[i].setLocation(
                    rand.nextInt(getWidth() - meteorite[i].getWidth() - 2*margin) + margin,
                    rand.nextInt(getHeight() - meteorite[i].getHeight() - 2*margin) + margin
            );

            BackG.add(meteorite[i]);

            // ความเร็วสุ่ม (คงเดิม)
            double dx = (rand.nextBoolean() ? 1 : -1) * (rand.nextDouble(0.6) + 0.2); // 0.2..0.8
            double dy = (rand.nextBoolean() ? 1 : -1) * (rand.nextDouble(0.6) + 0.2);


            mtoT[i] = new meteoriteThread(meteorite[i], BackG, dx, dy);
            mtoT[i].start();
        }

        add(BackG);
        setVisible(true);

        // >>> เพิ่มตัวเช็คการชนแบบเบา ๆ ทุก 30ms <<<
        Timer checkTimer = new Timer(30, new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                check_Collisions();
            }
        });
        checkTimer.start();
    }

    // ตรวจชนแบบ bounding box ของ JLabel
    private void check_Collisions() {
        for (int i = 0; i < meteorite.length; i++) {
            if (!meteorite[i].isVisible()) continue;
            Rectangle r1 = meteorite[i].getBounds();

            for (int j = i + 1; j < meteorite.length; j++) {
                if (!meteorite[j].isVisible()) continue;
                Rectangle r2 = meteorite[j].getBounds();

                if (r1.intersects(r2)) {
                    meteorite[j].setVisible(false);
                    mtoT[j].interrupt();
                }
            }
        }
    }
}