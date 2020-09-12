package com.andy;

/**
 * @author HP
 * @Description 子弹与坦克的碰撞检测器
 * @date 2020/9/12-8:35
 */
public class BulletTankCollider implements Collider {


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet)o1;
            Tank tank = (Tank)o2;
            if (bullet.group == tank.getGroup()) {
                return true;
            }
            if (bullet.rect.intersects(tank.rect)) {
                bullet.die();
                tank.die();
                int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                bullet.gm.gameObjectList.add(new Explode(eX, eY, bullet.gm));
                return false;
            }
        } else if(o2 instanceof Bullet && o1 instanceof Tank) {
            collide(o2,o1);
        }
        return true;
    }
}
