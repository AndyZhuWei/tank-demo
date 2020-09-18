package com.andy;

import java.io.Serializable;

/**
 * @author HP
 * @Description 坦克的开火策略
 * @date 2020/9/9-11:55
 */
public interface FireStrategy extends Serializable {

    void fire(Tank tank);
}
