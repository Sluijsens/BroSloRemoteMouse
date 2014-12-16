package nl.broslo.brosloremotemouse.socket;

import android.util.Log;
import android.view.InputEvent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import nl.broslo.brosloremotemouse.MainActivity;

/**
 * Created by Bryan on 13-12-2014.
 */
public class MouseSocket implements Runnable {

    private volatile boolean running = true;
    private String ipAddress;
    private int port;

    private List<SocketAction> actions = new ArrayList<>();

    public MouseSocket(String ipAddress, int port) {

        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void terminate() {
        running = false;
    }

    public void addAction(SocketAction action) {

        actions.add(action);
    }

    @Override
    public void run() {

        while(running) {
            try {
                final Socket socket = new Socket(ipAddress, port);

                if(socket.isConnected()) {
                    Log.d(MainActivity.DEBUG_TAG, "Connected!");
                    OutputStream out = socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(out);

                    while (running) {
                        try {
                            if(!actions.isEmpty()) {

                                SocketAction socketAction = actions.get(0);
                                oos.writeObject(socketAction);

                                oos.flush();
                                actions.remove(0);
                            }
                        } catch (IOException e) {
                            System.out.println("Connection closed by server!");
                            running = false;
                        }
                    }

                    oos.close();
                    out.close();
                }

                socket.close();
            } catch (IOException e) {
                running = false;
                Log.e(MainActivity.DEBUG_TAG, "Could not connect socket!" +
                        "\nIp Address: " + ipAddress +
                        "\nPort : " + port, e);
            }
        }
    }
}
