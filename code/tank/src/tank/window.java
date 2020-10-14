package tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class window {
    public static void main(String[] args) throws InterruptedException {
        Frame frame = new windowFrame();
        while (true) {
            Thread.sleep(30);
            frame.repaint();
        }
    }
}
