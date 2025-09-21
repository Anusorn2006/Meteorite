import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Meteorite {
    public static void main(String[] args) {
        new PanelMeteorite();
    }
}

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
            Image img = loadImageBestEffort(chosenFile);
            Image scaled = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            mtoIcon[i] = new ImageIcon(scaled);

            meteorite[i] = new JLabel(mtoIcon[i]);
            meteorite[i].setSize(50, 50);

            // สุ่มให้อยู่ในกรอบ ไม่หลุดขอบ
            int maxX = Math.max(1, 600 - meteorite[i].getWidth());
            int maxY = Math.max(1, 600 - meteorite[i].getHeight());
            meteorite[i].setLocation(rand.nextInt(maxX), rand.nextInt(maxY));

            BackG.add(meteorite[i]);

            int dx = rand.nextInt(1, 5);
            int dy = rand.nextInt(1, 5);

            mtoT[i] = new meteoriteThread(meteorite[i], BackG, dx, dy);
            mtoT[i].start();
        }

        add(BackG);
        setVisible(true);
    }

    // ลองหลายพาธ: <user.dir>/src/<file>, <user.dir>/<file>, แล้วค่อยลอง classpath
    private Image loadImageBestEffort(String filename) {
        String base = System.getProperty("user.dir");
        String[] candidates = new String[]{
                base + File.separator + "src" + File.separator + filename,
                base + File.separator + filename
        };
        for (String p : candidates) {
            try {
                File f = new File(p);
                if (f.exists()) {
                    BufferedImage bi = ImageIO.read(f); // synchronous
                    if (bi != null) return bi;
                }
            } catch (Exception ignored) {}
        }
        try {
            URL url = getClass().getResource("/" + filename);
            if (url != null) {
                BufferedImage bi = ImageIO.read(url);
                if (bi != null) return bi;
            }
        } catch (Exception ignored) {}

        // ถ้าไม่เจอจริง ๆ โยน error พร้อมพัมพ์พาธให้ดีบักง่าย
        throw new RuntimeException("Image not found: " + filename
                + "\nTried: " + candidates[0] + " , " + candidates[1] + " , classpath:/" + filename);
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

            if (newX <= 0 || newX >= panel.getWidth() - label.getWidth()) {
                dx = -dx;
            }
            if (newY <= 0 || newY >= panel.getHeight() - label.getHeight()) {
                dy = -dy;
            }

            label.setLocation(label.getX() + dx, label.getY() + dy);

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
