package tank;

import java.awt.*;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int Speed = 5;
    private boolean moving = false;
    private windowFrame windowFrame = null;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public Tank(int x, int y, Dir dir, tank.windowFrame windowFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = false;
        this.windowFrame = windowFrame;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void move(){
        if (isMoving()) {
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

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        move();
        g.fillRect(x, y, 30, 30);
        g.setColor(color);
    }

    public void fire() {
        windowFrame.bulletList.add(new Bullet(x, y, dir, this.windowFrame));
    }
}
