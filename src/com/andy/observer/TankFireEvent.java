package com.andy.observer;

import com.andy.Tank;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/12-22:39
 */
public class TankFireEvent {
    private Tank source;

    public TankFireEvent(Tank tank) {
        this.source=tank;
    }

    public Tank getSource() {
        return source;
    }
}
