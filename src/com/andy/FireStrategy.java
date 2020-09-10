package com.andy;

import com.andy.abstractfactory.BaseTank;

/**
 * @author HP
 * @Description 坦克的开火策略
 * @date 2020/9/9-11:55
 */
public interface FireStrategy {

    void fire(BaseTank tank);
}
