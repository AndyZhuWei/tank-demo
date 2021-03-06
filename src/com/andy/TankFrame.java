package com.andy;

import com.andy.net.Client;
import com.andy.net.TankDirChangedMsg;
import com.andy.net.TankStartMovingMsg;
import com.andy.net.TankStopMsg;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.*;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/6-15:43
 */
public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();
    Random r = new Random();
    Tank myTank = new Tank(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), Dir.DOWN, Group.GOOD, this);
    //List<Tank> tanks = new ArrayList<>();
    List<Bullet> bullets = new ArrayList();
    List<Explode> explodes = new ArrayList();

    Map<UUID, Tank> tanks = new HashMap<>();

    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

    private TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        // setVisible(true);


        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public Tank getMyTank() {
        return myTank;
    }


    public Tank findTankByUUID(UUID id) {
        return tanks.get(id);
    }


    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //会自动调用，需要显示的时候会自动调用重新绘制
    @Override
    public void paint(Graphics g) {
        // System.out.println("paint");
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.drawString("敌人的数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(c);


        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }


        //java8 stream api
        tanks.values().stream().forEach((e) -> e.paint(g));

        for (int i = 0; i < explodes.size(); i++) {
            Explode explode = explodes.get(i);
            explode.paint(g);
        }


        //collision detect
        Collection<Tank> values = tanks.values();
        for (int i = 0; i < bullets.size(); i++) {
            for (Tank t : values)
                bullets.get(i).collideWith(t);
        }

    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    setMainTankDir();
                    break;
                default:
                    break;
            }

            new Thread(() -> new Audio("audio/tank_move.wav").play()).start();


        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;

            }


        }

        private void setMainTankDir() {
            //save the old dir
            Dir dir = myTank.getDir();

            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
                Client.INSTANCE.send(new TankStopMsg(getMyTank()));
            } else {

                if (bL)
                    myTank.setDir(Dir.LEFT);
                if (bU)
                    myTank.setDir(Dir.UP);
                if (bR)
                    myTank.setDir(Dir.RIGHT);
                if (bD)
                    myTank.setDir(Dir.DOWN);
                //发出坦克移动的消息
                if (!myTank.isMoving())
                    Client.INSTANCE.send(new TankStartMovingMsg(getMyTank()));

                myTank.setMoving(true);

                if (dir != myTank.getDir()) {
                    Client.INSTANCE.send(new TankDirChangedMsg(myTank));
                }
            }
        }

    }

    public Bullet findBulletByUUID(UUID id) {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getId().equals(id))
                return bullets.get(i);
        }

        return null;
    }

    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    public void addTank(Tank t) {
        tanks.put(t.getId(), t);
    }
}
