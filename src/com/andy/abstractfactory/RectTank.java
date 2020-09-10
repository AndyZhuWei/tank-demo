package com.andy.abstractfactory;

import com.andy.*;

import java.awt.*;
import java.util.Random;

/**
 * @author HP
 * @Description 方形坦克
 * @date 2020/9/6-20:29
 */
public class RectTank extends BaseTank {



    private Random random = new Random();



    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if(this.group == Group.GOOD) {
            String goodFS = (String)PropertyMgr.get("goodFS");
            try {
                fireStrategy = (FireStrategy) Class.forName(goodFS).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String badFS = (String)PropertyMgr.get("badFS");
            try {
                fireStrategy = (FireStrategy) Class.forName(badFS).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void paint(Graphics g) {
        if(!living) {
            tf.tanks.remove(this);
        }

        /*switch(dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }*/
        Color c = g.getColor();
        g.setColor(this.group == Group.GOOD? Color.blue : Color.yellow);
        g.fillRect(x,y,20,20);
        g.setColor(c);

        move();
    }

    private void move() {
        if(!living) return;

        if(!moving) return;

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

        if(this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();

        if(this.group == Group.BAD && random.nextInt(100) > 95)
             randomDir();

        boundsCheck();

        //update rect
        rect.x = this.x;
        rect.y = this.y;


    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH- RectTank.WIDTH -2) x = TankFrame.GAME_WIDTH - RectTank.WIDTH -2;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
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

    public void fire() {
        fireStrategy.fire(this);

    }

}
