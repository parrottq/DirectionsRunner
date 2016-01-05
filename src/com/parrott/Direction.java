package com.parrott;

/**
 * Created by quinn on 29/12/2015.
 */
public class Direction {

    private int rightSpeed;
    private int leftSpeed;
    private int delay;

    Direction(){
        rightSpeed = 128;
        leftSpeed = 128;
        delay = 0;
    }

    Direction(int rightSpeed, int leftSpeed, int delay){
        this.rightSpeed = rightSpeed;
        this.leftSpeed = leftSpeed;
        this.delay = delay;
    }

    //rightSpeed
    public int getRightSpeed() {
        return rightSpeed;
    }

    public void setRightSpeed(int rightSpeed) {
        this.rightSpeed = rightSpeed;
    }

    //leftSpeed
    public int getLeftSpeed() {
        return leftSpeed;
    }

    public void setLeftSpeed(int leftSpeed) {
        this.leftSpeed = leftSpeed;
    }

    //delay
    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getFormatedString(){
        return rightSpeed + " " + leftSpeed + " " + delay;
    }

}
