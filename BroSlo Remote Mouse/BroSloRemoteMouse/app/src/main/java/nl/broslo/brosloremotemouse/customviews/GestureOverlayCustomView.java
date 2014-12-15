package nl.broslo.brosloremotemouse.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.gesture.GestureOverlayView;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import nl.broslo.brosloremotemouse.MainActivity;

/**
 * Created by Bryan on 14-12-2014.
 */
public class GestureOverlayCustomView extends GestureOverlayView {

    public GestureOverlayCustomView(Context context) {
        super(context);
    }

    public GestureOverlayCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureOverlayCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GestureOverlayCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch(action) {
            case MotionEvent.ACTION_MOVE:

                float hx = event.getHistoricalX(0);
                float hy = event.getHistoricalY(0);

                float x = event.getX();
                float y = event.getY();

                double moveX = x - hx;
                double moveY = y - hy;

//                ((MouseActivity)getContext()).mouseSocket.addAction(new SocketAction(SocketAction.ACTION_MOUSE_MOVE, new MouseMovement(moveX, moveY)));

                Log.d(MainActivity.DEBUG_TAG, "X (current, historical): " + x + ", " + hx +
                        "\nY (current, historical): " + y + ", " + hy);

                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
