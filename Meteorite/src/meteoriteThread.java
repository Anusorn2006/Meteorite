import javax.swing.*;

class meteoriteThread extends Thread {
    JLabel label;
    JPanel panel;
    double dx, dy;
    double posX, posY;

    meteoriteThread(JLabel label, JPanel panel, double dx, double dy) {
        this.label = label;
        this.panel = panel;
        this.dx = dx;
        this.dy = dy;
        this.posX = label.getX();
        this.posY = label.getY();
    }

    @Override
    public void run() {
        while (true) {
            posX += dx;
            posY += dy;

            if (posX <= 0) { dx = -dx; dx += 1; }
            if (posX >= panel.getWidth() - label.getWidth()) { dx = -dx; dx -= 1; }
            if (posY <= 0) { dy = -dy; dy += 1; }
            if (posY >= panel.getHeight() - label.getHeight()) { dy = -dy; dy -= 1; }

            label.setLocation((int) posX, (int) posY);
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                //หยุดเธรดทันทีเมื่อถูก interrupt จากตอนชน
                return;
            }
        }
    }
}