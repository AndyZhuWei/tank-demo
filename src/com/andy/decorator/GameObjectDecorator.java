package com.andy.decorator;

import com.andy.GameObject;

import java.awt.*;

/**
 * @author HP
 * @Description 抽取出一个GameObjectDecorator装饰器积累
 * @date 2020/9/12-17:38
 */
public abstract class GameObjectDecorator extends GameObject {

    protected GameObject gameObject;

    public GameObjectDecorator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public abstract void  paint(Graphics g);

}
