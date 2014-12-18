package nl.broslo.brosloremotemouse.socket;

import java.io.Serializable;

/**
 * Created by Bryan on 15-12-2014.
 */
public class SocketAction implements Serializable {

    // Constants
    public static final String ACTION_MOUSE_MOVE = "Move Mouse";
    public static final String ACTION_MOUSE_CLICK = "Click Mouse";
    public static final String ACTION_MOUSE_DOWN = "Press Mouse";
    public static final String ACTION_MOUSE_UP = "Release Mouse";
    public static final String ACTION_KEYBOARD_TYPE = "Type Keyboard";

    private String key;
    private int value;
    private MouseMovement mouseMovement;

    public SocketAction(String key, int value) {

        this.key = key;
        this.value = value;
    }

    public SocketAction(String key, MouseMovement mouseMovement) {

        this.key = key;
        this.mouseMovement = mouseMovement;
    }

    public String getKey() {
        return this.key;
    }

    public int getValue() {
        return this.value;
    }

    public MouseMovement getMouseMovement() {
        return this.mouseMovement;
    }
}
