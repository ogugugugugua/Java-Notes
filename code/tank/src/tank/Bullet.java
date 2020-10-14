package tank;

import java.awt.*;

public class Bullet {
    private static final int Speed = 10, Width = 10, Height = 10;
    private int x, y;
    private Dir dir;
    private boolean alive = true;
    private windowFrame windowFrame;

    public Bullet(int x, int y, Dir dir, windowFrame windowFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.windowFrame = windowFrame;
    }
    public void paint(Graphics g) {
        if (!alive) {
            windowFrame.bulletList.remove(this);
            return;
        }
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
        if (x < 0 || y < 0 || x > tank.windowFrame.GAME_WIDTH || y > tank.windowFrame.GAME_HEIGHT) {
            alive = false;
        }
    }
}
