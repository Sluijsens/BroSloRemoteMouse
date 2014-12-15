package nl.broslo.brosloremotemouse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Bryan on 12-12-2014.
 */
public class MainActivity extends Activity {

    public static final String DEBUG_TAG = "BSRM";

    private String SETTINGS = "Settings";
    public SharedPreferences mSettings;
    public static final String SETTINGS_IP_ADDRESS = "Settings Ip Address";
    public static final String SETTINGS_PORT = "Settings Port";

    protected String ipAddress;
    protected int port = 23713;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSettings = getSharedPreferences(SETTINGS, MODE_PRIVATE);
    }

    public void startMouseActivity() {

        startActivity(new Intent(getApplicationContext(), MouseActivity.class));
    }

    public void startSettingsActivity() {

        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }
}
