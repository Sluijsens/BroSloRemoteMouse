package nl.broslo.brosloremotemouse;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import nl.broslo.brosloremotemouse.customviews.GestureOverlayCustomView;
import nl.broslo.brosloremotemouse.socket.Test;

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
        imageButtonLeftClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mouseSocket.addAction(new Test("Test String"));

//                mouseSocket.addAction(new SocketAction(SocketAction.ACTION_MOUSE_CLICK, KeyEvent.KEYCODE_BUTTON_1));
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
        imageButtonRightClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SocketAction socketAction = new SocketAction(SocketAction.ACTION_MOUSE_CLICK, KeyEvent.KEYCODE_BUTTON_3);

//                mouseSocket.addAction(socketAction);
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

        //hideSoftKeyboard();

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

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

//        mouseSocket.addAction(new SocketAction(SocketAction.ACTION_KEYBOARD_TYPE, keyCode));

        return false;
    }
}
