package tank;

import java.awt.*;

public class Bullet {
    private static final int Speed = 10, Width = 10, Height = 10;
    private int x, y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    public void paint(Graphics g) {
        move();
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, Width, Height);
        g.setColor(color);
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x-=Speed;
                break;
            case RIGHT:
                x+=Speed;
                break;
            case UP:
                y-=Speed;
                break;
            case DOWN:
                y+=Speed;
                break;
        }
    }
}
