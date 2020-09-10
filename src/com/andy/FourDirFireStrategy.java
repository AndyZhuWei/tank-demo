package com.andy;

import com.andy.abstractfactory.BaseBullet;
import com.andy.abstractfactory.BaseTank;

/**
 * @author HP
 * @Description 四个方向开火的策略
 * @date 2020/9/9-11:55
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(BaseTank tank) {
        TankFrame tf = tank.getTf();
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for(Dir dir : dirs) {
            //Bullet bullet = new Bullet(bX,bY,dir,tank.getGroup(),tf);
            BaseBullet bullet = tf.gf.createBullet(bX,bY,dir,tank.getGroup(),tf);
            tf.bullets.add(bullet);
        }
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
