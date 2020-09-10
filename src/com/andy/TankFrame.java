package com.andy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/6-15:43
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
    List<Tank> tanks = new ArrayList<>();
    List<Bullet> bullets = new ArrayList();
    List<Explode> explodes = new ArrayList();

    static final int GAME_WIDTH = Integer.parseInt((String)PropertyMgr.get("gameWidth")),
            GAME_HEIGHT=Integer.parseInt((String)PropertyMgr.get("gameHeight"));

    public TankFrame() {
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);



        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

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
        for(int i=0;i<bullets.size();i++) {
            bullets.get(i).paint(g);
        }

        for(int i=0;i<tanks.size();i++) {
            Tank tank = tanks.get(i);
            tank.paint(g);
        }

        for(int i=0;i<explodes.size();i++) {
            Explode explode = explodes.get(i);
            explode.paint(g);
        }


        //collision detect
        for(int i=0; i<bullets.size(); i++) {
            for(Tank t : tanks )
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
            switch(keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    //myTank.fire(new DefaultFireStrategy());
                    //myTank.fire(new FourDirFireStrategy());
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch(keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;

            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bU) myTank.setDir(Dir.UP);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bD) myTank.setDir(Dir.DOWN);
            }

        }
    }
}
