package com.andy;

import java.awt.*;

/**
 * @author HP
 * @Description 抽象出一个游戏对象
 * @date 2020/9/12-8:27
 */
public abstract class GameObject {

    int x, y;

    public abstract void paint(Graphics g);


}
