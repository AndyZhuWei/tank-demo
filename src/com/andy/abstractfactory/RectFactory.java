package com.andy.abstractfactory;

import com.andy.Dir;
import com.andy.Explode;
import com.andy.Group;
import com.andy.TankFrame;

/**
 * @author HP
 * @Description 方形工厂
 * @date 2020/9/10-9:00
 */
public class RectFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectTank(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y,Dir dir, Group group, TankFrame tf) {
        return new RectBullet(x,y,dir,group,tf);
    }
}
