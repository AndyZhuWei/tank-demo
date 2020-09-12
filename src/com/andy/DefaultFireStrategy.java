package com.andy;

import com.andy.decorator.RectDecorator;
import com.andy.decorator.TailDecorator;

/**
 * @author HP
 * @Description 默认开火策略
 * @date 2020/9/9-11:55
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        GameModel gm = tank.gm;
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        //Bullet bullet = new Bullet(bX,bY,tank.getDir(),tank.getGroup());
        //GameModel.getInstance().add(bullet);

        GameModel.getInstance().add(new RectDecorator(new TailDecorator(new Bullet(bX,bY,tank.getDir(),tank.getGroup()))));

        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
