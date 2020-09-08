package com.andy;

import jdk.nashorn.internal.runtime.regexp.joni.ast.StateNode;

import java.awt.*;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/6-20:52
 */
public class Bullet {

    int x ,y;
    Dir dir;
    private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("bulletSpeed"));;

    public static int WIDTH = ResourceMgr.bulletD.getWidth();

    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean living = true;

    private Group group = Group.BAD;

    Rectangle rect = new Rectangle();

    TankFrame tf = null;

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!living) {
            tf.bullets.remove(this);
        }

        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }

    public void collideWith(Tank t) {
        if(this.group == t.getGroup()) {
            return;
        }
        if(this.rect.intersects(t.rect)) {
            this.die();
            t.die();
            int eX = t.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = t.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX,eY,tf));
        }
    }

    public void die() {
        this.living = false;
    }
}
