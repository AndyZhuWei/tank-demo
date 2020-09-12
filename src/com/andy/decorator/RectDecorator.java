package com.andy.decorator;

import com.andy.GameObject;

import java.awt.*;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/12-17:48
 */
public class RectDecorator extends GameObjectDecorator {


    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        gameObject.paint(g);
        this.x = super.gameObject.x;
        this.y = super.gameObject.y;

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(x,y,gameObject.getWidth(),gameObject.getHeight());
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
