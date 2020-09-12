package com.andy;

/**
 * @author HP
 * @Description 碰撞检测器
 * @date 2020/9/12-8:34
 */
public interface Collider {

    //返回值boolean可以实现是否继续后边的检测器的逻辑
    boolean collide(GameObject o1,GameObject o2);
}
