package com.andy.abstractfactory;

import com.andy.*;

import java.awt.*;

/**
 * @author HP
 * @Description 方形爆炸
 * @date 2020/9/10-9:10
 */
public class RectExplode extends BaseExplode{
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    //private boolean living = true;

    private int step = 0;

    private TankFrame tf;

    public RectExplode(int x, int y,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }



    @Override
    public void paint(Graphics g) {

       // g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x,y,10*step,10*step);
        step++;

        if(step >= ResourceMgr.explodes.length)
            tf.explodes.remove(this);

        g.setColor(c);


    }
}
