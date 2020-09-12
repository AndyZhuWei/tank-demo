package com.andy;

/**
 * @author HP
 * @Description 坦克与坦克碰撞器检测
 * @date 2020/9/12-8:43
 */
public class TankTankCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank)o1;
            Tank t2 = (Tank)o2;
            if(t1.rect.intersects(t2.rect)) {
                t1.x = t1.prevX;
                t1.y = t1.prevY;

                t2.x = t2.prevX;
                t2.y = t2.prevY;
            }
        }
        return true;
    }
}
