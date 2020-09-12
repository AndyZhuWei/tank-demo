package com.andy.observer;

import com.andy.Tank;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/12-22:36
 */
public class TankFireHandler implements TankFireObserver{

    @Override
    public void actionOnFire(TankFireEvent e) {
        Tank tank = e.getSource();
        tank.fire();
    }
}
