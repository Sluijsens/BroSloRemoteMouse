package nl.broslo.brosloremotemouse;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Bryan on 13-12-2014.
 */
public class SettingsActivity extends MainActivity {

    EditText editTextIpAddress;
    EditText editTextPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        ipAddress = mSettings.getString(SETTINGS_IP_ADDRESS, "");
        port = mSettings.getInt(SETTINGS_PORT, 23713);

        editTextIpAddress = (EditText) findViewById(R.id.EditText_IpAddress);
        editTextIpAddress.setText(ipAddress);

        editTextPort = (EditText) findViewById(R.id.EditText_Port);
        editTextPort.setText(String.valueOf(port));

        Button buttonConnect = (Button) findViewById(R.id.Button_Connect);
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveSettings();
                startMouseActivity();
            }
        });

//
//        System.out.println("SocketAction: " + new SocketAction("SocketAction"));
//        System.out.println("Not SocketAction: " + test);
//
//        NotificationSimple notTest = (NotificationSimple) test;
//        System.out.println("cast test: " + .getTitle());
    }

    /**
     * Save settings to SharedPreferences
     */
    private void saveSettings() {

        ipAddress = editTextIpAddress.getText().toString();
        port = Integer.parseInt(editTextPort.getText().toString());

        Editor editor = mSettings.edit();
        editor.putString(SETTINGS_IP_ADDRESS, ipAddress);
        editor.putInt(SETTINGS_PORT, port);

        editor.commit();
    }
}
