package com.andy;

import java.awt.*;
import java.io.Serializable;

/**
 * @author HP
 * @Description 抽象出一个游戏对象
 * @date 2020/9/12-8:27
 */
public abstract class GameObject implements Serializable {

    public int x, y;

    public abstract void paint(Graphics g);

    public abstract int getWidth();
    public abstract int getHeight();



}
