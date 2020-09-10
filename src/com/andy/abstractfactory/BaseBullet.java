package com.andy.abstractfactory;

import java.awt.*;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/10-9:00
 */
public abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank t);
}
