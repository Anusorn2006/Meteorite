class meteoriteThread extends Thread {
    private PanelMeteorite panel;

    meteoriteThread(PanelMeteorite panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            panel.update();
            panel.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                //หยุดเธรดทันทีเมื่อถูก interrupt จากตอนชน
                return;
            }
        }
    }
}