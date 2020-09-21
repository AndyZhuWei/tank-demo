package com.andy;

import com.andy.net.BulletNewMsg;
import com.andy.net.Client;
import com.andy.net.TankJoinMsg;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/6-20:29
 */
public class Tank {

    private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("tankSpeed"));
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();

    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private int x;
    private int y;
    Dir dir;
    TankFrame tf;
    private Group group;

    private UUID id = UUID.randomUUID();

    Rectangle rect = new Rectangle();

    private Random random = new Random();

    private boolean living = true;

    public boolean isLiving() {
        return living;
    }

    private boolean moving = true;

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public Tank(TankJoinMsg msg) {
        this.x = msg.x;
        this.y = msg.y;
        this.dir = msg.dir;
        this.moving = msg.moving;
        this.group = msg.group;
        this.id = msg.id;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        //uuid on head
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString(id.toString(), this.x, this.y - 20);
        g.drawString("live=" + living, x, y-10);
        g.setColor(c);

        //draw a rect if dead!
        if(!living) {
            moving = false;
            Color cc = g.getColor();
            g.setColor(Color.WHITE);
            g.drawRect(x, y, WIDTH, HEIGHT);
            g.setColor(cc);
            return;
        }


        switch(dir) {
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
        }

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
        if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
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
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        Bullet b = new Bullet(this.id, bX, bY, this.dir, this.group, this.tf);

        tf.bullets.add(b);

        Client.INSTANCE.send(new BulletNewMsg(b));

        if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();

    }

    public void die() {
        this.living = false;
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


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
