package nl.broslo.brosloremotemouse;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import nl.broslo.brosloremotemouse.customviews.GestureOverlayCustomView;
import nl.broslo.brosloremotemouse.socket.MouseSocket;
import nl.broslo.brosloremotemouse.socket.SocketAction;

/**
 * Created by Bryan on 12-12-2014.
 */
public class MouseActivity extends MainActivity {

    LinearLayout linearLayoutMouseActivity;
    GestureOverlayCustomView gestureOverlayCustomView;

    public MouseSocket mouseSocket;
    private Thread mouseThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouse_activity);

        linearLayoutMouseActivity = (LinearLayout) findViewById(R.id.LinearLayout_MouseActivity);

        ImageView imageButtonLeftClick = (ImageView) findViewById(R.id.ImageButton_LeftClick);
        imageButtonLeftClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                SocketAction socketAction;

                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        socketAction = new SocketAction(SocketAction.ACTION_MOUSE_DOWN, 16);
                        mouseSocket.addAction(socketAction);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d(MainActivity.DEBUG_TAG, "Action UP");
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        socketAction = new SocketAction(SocketAction.ACTION_MOUSE_UP, 16);
                        mouseSocket.addAction(socketAction);
                        return true;
                }
                return false;
            }
        });

        ImageView imageButtonKeyboard = (ImageView) findViewById(R.id.ImageButton_Keyboard);
        imageButtonKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSoftKeyboard();
            }
        });

        ImageView imageButtonSettings = (ImageView) findViewById(R.id.ImageButton_Settings);
        imageButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSettingsActivity();
            }
        });

        ImageView imageButtonRightClick = (ImageView) findViewById(R.id.ImageButton_RightClick);
        imageButtonRightClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                SocketAction socketAction;

                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        socketAction = new SocketAction(SocketAction.ACTION_MOUSE_DOWN, 4);
                        mouseSocket.addAction(socketAction);
                        return true;
                    case MotionEvent.ACTION_UP:
                        socketAction = new SocketAction(SocketAction.ACTION_MOUSE_UP, 4);
                        mouseSocket.addAction(socketAction);
                        return true;
                }
                return false;
            }
        });

        gestureOverlayCustomView = (GestureOverlayCustomView) findViewById(R.id.GestureOverlayView_MouseActivity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ipAddress = mSettings.getString(SETTINGS_IP_ADDRESS, "");
        port = mSettings.getInt(SETTINGS_PORT, port);

        mouseSocket = new MouseSocket(ipAddress, port);
        mouseThread = new Thread(mouseSocket);
        mouseThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mouseSocket.terminate();
        try {
            mouseThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showSoftKeyboard() {
        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(linearLayoutMouseActivity.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCodeAndroidToJava.keyCodeMap.containsKey(keyCode)) {
            mouseSocket.addAction(new SocketAction(SocketAction.ACTION_KEYBOARD_TYPE, keyCodeAndroidToJava.keyCodeMap.get(keyCode)));
        }

        return false;
    }
}
