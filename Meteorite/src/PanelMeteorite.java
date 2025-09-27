import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;


class PanelMeteorite extends JPanel {

    int amountMeteorite = Constants.amount_meteorite;
    Image []imageMeteorite = new Image[amountMeteorite];
    double[] posX = new double[amountMeteorite];
    double[] posY = new double[amountMeteorite];
    double[] dx = new double[amountMeteorite];
    double[] dy = new double[amountMeteorite];
    int SizeMeteorite = 50;

    Random rand = new Random();


    PanelMeteorite() {
        setSize(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
        setBackground(Color.BLACK);


        for (int i = 0; i < amountMeteorite; i++) {
            String chosenFile = Constants.imageFiles[rand.nextInt(Constants.imageFiles.length)];
            ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+File.separator+"Meteorite"+File.separator+"src"+File.separator+"Image"+File.separator+chosenFile);
            Image iconImage = icon.getImage();
            imageMeteorite[i] = iconImage.getScaledInstance(SizeMeteorite, SizeMeteorite, Image.SCALE_SMOOTH);


            // สุ่มตำแหน่ง (กันขอบ 50px)
            int margin = 50;
            posX[i] = rand.nextInt(Constants.WINDOW_WIDTH - 50 - 2 * margin) + margin;
            posY[i] = rand.nextInt(Constants.WINDOW_HEIGHT - 50 - 2 * margin) + margin;


            // ความเร็วสุ่ม (คงเดิม)
             dx[i] = rand.nextDouble(-0.5, 0.5) ;
             dy[i] = rand.nextDouble(-0.5, 0.5) ;

            System.out.println(System.getProperty("user.dir")+ File.separator+"src"+File.separator+"Image"+File.separator+chosenFile);
        }



    }
    void update(){
        for (int i = 0; i < amountMeteorite; i++) {
            posX[i] += dx[i];
            posY[i] += dy[i];

            if (posX[i] <= 0)
            {
                dx[i] = -dx[i];
                dx[i] += 1;
            }
            if (posX[i] >= this.getWidth() - SizeMeteorite )
            {
                dx[i] = -dx[i];
                dx[i] -= 1;
            }
            if (posY[i] <= 0)
            {
                dy[i] = -dy[i];
                dy[i] += 1;
            }
            if (posY[i] >= this.getHeight() - SizeMeteorite)
            {
                dy[i] = -dy[i];
                dy[i] -= 1;
            }
        }

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < amountMeteorite; i++) {
            g.drawImage(imageMeteorite[i], (int) posX[i], (int)posY[i], SizeMeteorite, SizeMeteorite, this);
        }
    }

}



