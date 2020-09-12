package com.andy.decorator;

import com.andy.GameObject;

import java.awt.*;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/12-17:48
 */
public class TailDecorator extends GameObjectDecorator {


    public TailDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        gameObject.paint(g);
        this.x = this.gameObject.x;
        this.y = this.gameObject.y;

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(x,y,gameObject.getWidth()+x,gameObject.getHeight()+y);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.gameObject.getWidth();
    }

    @Override
    public int getHeight() {
        return super.gameObject.getHeight();
    }

}
