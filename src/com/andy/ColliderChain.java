package com.andy;

import java.util.LinkedList;
import java.util.List;

/**
 * @author HP
 * @Description 碰撞检测器链
 * @date 2020/9/12-8:47
 */
public class ColliderChain implements Collider{
    List<Collider> colliderList = new LinkedList<>();

    public ColliderChain() {

    }

    public ColliderChain add(Collider collider) {
        colliderList.add(collider);
        return this;
    }

    //返回false表示不需要进行后续的检测器
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for(Collider collider : colliderList) {
            if(!collider.collide(o1,o2)) {
               return false;
            }
        }
        return true;
    }
}
