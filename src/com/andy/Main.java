package com.andy;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        for(int i=0;i<initTankCount;i++) {
            tf.tanks.add(new Tank(50+80*i,200,Dir.DOWN,Group.BAD,tf));
        }

        while(true) {
            Thread.sleep(25);
            tf.repaint();
        }
    }
}
