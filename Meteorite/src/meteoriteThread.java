import javax.swing.*;

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
