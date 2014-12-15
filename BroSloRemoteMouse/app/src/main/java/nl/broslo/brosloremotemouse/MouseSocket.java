package nl.broslo.brosloremotemouse;

import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import nl.broslo.brosloremotemouse.socket.Test;

/**
 * Created by Bryan on 13-12-2014.
 */
public class MouseSocket implements Runnable {

    private volatile boolean running = true;
    private String ipAddress;
    private int port;

    private List<Test> actions = new ArrayList<>();

    public MouseSocket(String ipAddress, int port) {

        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void terminate() {
        running = false;
    }

    public void addAction(Test action) {

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
                                Thread.sleep(100);
                                Test socketAction = actions.get(0);
                                oos.writeObject(socketAction);
                                System.out.println("Socket Action: " + socketAction.getTest());
                                oos.flush();
                                actions.remove(0);
                            }
                        } catch (IOException e) {
                            System.out.println("Connection closed by server!");
                            running = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
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
