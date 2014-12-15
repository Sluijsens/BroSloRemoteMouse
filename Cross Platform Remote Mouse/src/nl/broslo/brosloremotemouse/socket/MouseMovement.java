package nl.broslo.brosloremotemouse.socket;

import java.io.Serializable;

/**
 * Created by Bryan on 15-12-2014.
 */
public class MouseMovement implements Serializable {

    private double movementX;
    private double movementY;

    public MouseMovement(double movementX, double movementY) {

        this.movementX = movementX;
        this.movementY = movementY;
    }

    public double getMovementX() {
        return this.movementX;
    }

    public double getMovementY() {
        return this.movementY;
    }

}
