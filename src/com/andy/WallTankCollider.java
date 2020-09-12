package com.andy;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/12-10:29
 */
public class WallTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Wall && o2 instanceof Tank) {
            Wall wall = (Wall)o1;
            Tank tank = (Tank)o2;
            if(wall.rect.intersects(tank.rect)) {
                tank.back();
            }
        } else if(o1 instanceof Tank && o2 instanceof Wall) {
            return collide(o2,o1);
        }
        return true;
    }
}
