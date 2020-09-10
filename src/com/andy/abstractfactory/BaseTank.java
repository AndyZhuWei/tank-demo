package com.andy.abstractfactory;

import com.andy.*;

import java.awt.*;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/10-9:00
 */
public abstract class BaseTank {

    public static final int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();

    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    public TankFrame tf;

    public FireStrategy fireStrategy;

    public Rectangle rect = new Rectangle();


    public boolean moving = true;

    public boolean living = true;



    public Dir dir;

    public Group group;

    public int x;
    public int y;

    public void fire() {
        fireStrategy.fire(this);
    }


    public abstract void paint(Graphics g);


    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public void die() {
        this.living = false;
    }

}
