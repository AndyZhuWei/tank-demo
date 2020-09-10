package com.andy.abstractfactory;

import com.andy.*;

/**
 * @author HP
 * @Description 默认实现的工厂
 * @date 2020/9/10-9:00
 */
public class DefaultFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y,Dir dir, Group group, TankFrame tf) {
        return new Bullet(x,y,dir,group,tf);
    }
}
