package com.andy.observer;

import java.io.Serializable;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/12-22:36
 */
public interface TankFireObserver extends Serializable {
    void actionOnFire(TankFireEvent e);
}
