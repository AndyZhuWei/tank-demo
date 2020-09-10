package com.andy.abstractfactory;

import com.andy.Dir;
import com.andy.Group;
import com.andy.TankFrame;

/**
 * @author HP
 * @Description 运用抽象工厂模式
 * @date 2020/9/10-8:56
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y,TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y,Dir dir, Group group,TankFrame tf);


}
