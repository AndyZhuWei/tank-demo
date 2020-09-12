package com.andy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 * @Description 作为门面模式代表类，把Model相关的都加入进来，从而和显示组件TankFrame分离
 * @date 2020/9/11-22:10
 */
public class GameModel {

    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);

    List<GameObject> gameObjectList = new ArrayList<>();

    ColliderChain colliderChain = new ColliderChain();


    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            gameObjectList.add(new Tank(50 + 80 * i, 200, Dir.DOWN, Group.BAD, this));
        }

        colliderChain.add(new BulletTankCollider()).add(new TankTankCollider());
    }


    public void paint(Graphics g) {
        // System.out.println("paint");
       /* Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.drawString("敌人的数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(c);*/


        myTank.paint(g);


        for (int i = 0; i < gameObjectList.size(); i++) {
            GameObject go = gameObjectList.get(i);
            go.paint(g);
        }

        //碰撞检测
        for (int i = 0; i < gameObjectList.size(); i++) {
            for (int j = i + 1; j < gameObjectList.size(); j++) {
                GameObject go1 = gameObjectList.get(i);
                GameObject go2 = gameObjectList.get(j);
                colliderChain.collide(go1,go2);
            }
        }


        //collision detect
        /*for (int i = 0; i < bullets.size(); i++) {
            for (Tank t : tanks)
                bullets.get(i).collideWith(t);
        }*/
    }

    public Tank getMainTank() {
        return myTank;
    }
}
