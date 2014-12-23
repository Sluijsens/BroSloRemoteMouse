package nl.broslo.brosloremotemouse.utility;

import android.view.KeyEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bryan on 22-12-2014.
 */
public class KeyCodeAndroidToJava {

    public final Map<Integer, Integer> keyCodeMap = new HashMap<Integer, Integer>();

    public KeyCodeAndroidToJava() {

        /**
         * Add numeric characters
         */
        keyCodeMap.put(KeyEvent.KEYCODE_0, 48);
        keyCodeMap.put(KeyEvent.KEYCODE_1, 49);
        keyCodeMap.put(KeyEvent.KEYCODE_2, 50);
        keyCodeMap.put(KeyEvent.KEYCODE_3, 51);
        keyCodeMap.put(KeyEvent.KEYCODE_4, 52);
        keyCodeMap.put(KeyEvent.KEYCODE_5, 53);
        keyCodeMap.put(KeyEvent.KEYCODE_6, 54);
        keyCodeMap.put(KeyEvent.KEYCODE_7, 55);
        keyCodeMap.put(KeyEvent.KEYCODE_8, 56);
        keyCodeMap.put(KeyEvent.KEYCODE_9, 57);

        /**
         * Add alphabetic characters
         */
        keyCodeMap.put(KeyEvent.KEYCODE_A, 65);
        keyCodeMap.put(KeyEvent.KEYCODE_B, 66);
        keyCodeMap.put(KeyEvent.KEYCODE_C, 67);
        keyCodeMap.put(KeyEvent.KEYCODE_D, 68);
        keyCodeMap.put(KeyEvent.KEYCODE_E, 69);
        keyCodeMap.put(KeyEvent.KEYCODE_F, 70);
        keyCodeMap.put(KeyEvent.KEYCODE_G, 71);
        keyCodeMap.put(KeyEvent.KEYCODE_H, 72);
        keyCodeMap.put(KeyEvent.KEYCODE_I, 73);
        keyCodeMap.put(KeyEvent.KEYCODE_J, 74);
        keyCodeMap.put(KeyEvent.KEYCODE_K, 75);
        keyCodeMap.put(KeyEvent.KEYCODE_L, 76);
        keyCodeMap.put(KeyEvent.KEYCODE_M, 77);
        keyCodeMap.put(KeyEvent.KEYCODE_N, 78);
        keyCodeMap.put(KeyEvent.KEYCODE_O, 79);
        keyCodeMap.put(KeyEvent.KEYCODE_P, 80);
        keyCodeMap.put(KeyEvent.KEYCODE_Q, 81);
        keyCodeMap.put(KeyEvent.KEYCODE_R, 82);
        keyCodeMap.put(KeyEvent.KEYCODE_S, 83);
        keyCodeMap.put(KeyEvent.KEYCODE_T, 84);
        keyCodeMap.put(KeyEvent.KEYCODE_U, 85);
        keyCodeMap.put(KeyEvent.KEYCODE_V, 86);
        keyCodeMap.put(KeyEvent.KEYCODE_W, 87);
        keyCodeMap.put(KeyEvent.KEYCODE_X, 88);
        keyCodeMap.put(KeyEvent.KEYCODE_Y, 89);
        keyCodeMap.put(KeyEvent.KEYCODE_Z, 90);

        /**
         * Add special characters
         */
        keyCodeMap.put(KeyEvent.KEYCODE_SPACE, 32);
//        keyCodeMap.put(KeyEvent.KEYCODE_TAB, 9);
//        keyCodeMap.put(KeyEvent.KEYCODE_APOSTROPHE, 222);
//        keyCodeMap.put(KeyEvent.KEYCODE_AT, 512);
//        keyCodeMap.put(KeyEvent.KEYCODE_NUMPAD_ADD, 521);
//        keyCodeMap.put(KeyEvent.KEYCODE_BACKSLASH, 92);
//        keyCodeMap.put(KeyEvent.KEYCODE_LEFT_BRACKET, 91);
//        keyCodeMap.put(KeyEvent.KEYCODE_RIGHT_BRACKET, 93);
//        keyCodeMap.put(KeyEvent.KEYCODE_COMMA, 44);
//        keyCodeMap.put(KeyEvent.KEYCODE_SLASH, 47);
//        keyCodeMap.put(KeyEvent.KEYCODE_EQUALS, 61);
//        keyCodeMap.put(KeyEvent.KEYCODE_GRAVE, 128);
//        keyCodeMap.put(KeyEvent.KEYCODE_NUMPAD_MULTIPLY, 106);
//        keyCodeMap.put(KeyEvent.KEYCODE_NUMPAD_SUBTRACT, 109);
        keyCodeMap.put(KeyEvent.KEYCODE_PERIOD, 46);

        /**
         * Add "special" keyboard keys
         */
//        keyCodeMap.put(KeyEvent.KEYCODE_ALT_LEFT, );
//        keyCodeMap.put(KeyEvent.KEYCODE_ALT_RIGHT, );
//        keyCodeMap.put(KeyEvent.KEYCODE_CTRL_LEFT, );
//        keyCodeMap.put(KeyEvent.KEYCODE_CTRL_RIGHT, );
//        keyCodeMap.put(KeyEvent.KEYCODE_SHIFT_LEFT, );
//        keyCodeMap.put(KeyEvent.KEYCODE_SHIFT_RIGHT, );
        keyCodeMap.put(KeyEvent.KEYCODE_DEL, 8);
        keyCodeMap.put(KeyEvent.KEYCODE_ENTER, 10);
    }

}
