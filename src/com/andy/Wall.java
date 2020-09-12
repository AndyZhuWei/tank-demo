package com.andy;

import java.awt.*;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/12-10:22
 */
public class Wall extends GameObject {

    Rectangle rect = new Rectangle();
    int weight,height;


    public Wall(int x,int y,int weight,int height) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.height = height;

        rect.x=x;
        rect.y=y;
        rect.width=weight;
        rect.height=height;
    }



    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.darkGray);
        g.fillRect(x,y,weight,height);
        g.setColor(color);


    }
}
