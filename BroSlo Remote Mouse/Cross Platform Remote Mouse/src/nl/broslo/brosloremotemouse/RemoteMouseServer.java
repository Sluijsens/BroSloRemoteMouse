package nl.broslo.brosloremotemouse;

import nl.broslo.brosloremotemouse.socket.Test;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Bryan on 12-12-2014.
 */
public class RemoteMouseServer implements Runnable {

    private boolean running = true;
    public ServerSocket serverSocket;
    private int port;

    private Point mousePointerLocation;

    public RemoteMouseServer(int port) {

        this.port = port;
        mousePointerLocation = MouseInfo.getPointerInfo().getLocation();
    }

    public void terminate() {
        System.out.println("Terminating Thread");
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(running) {
            try {
                serverSocket = new ServerSocket(port);

                System.out.println("\nWaiting for connection on port '" + port + "'...");
                Socket socket = serverSocket.accept();

                if (!serverSocket.isClosed()) {
                    InputStream in = socket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(in);

                    System.out.println("Connected...");
                    boolean isStopped = false;
                    while(!isStopped) {

                        try {
                            Thread.sleep(100);
                            Test data = (Test) ois.readObject();

                            System.out.println("Test String: " + data.getTest());

                            System.out.println(data);

                        } catch (IOException e) {
                            System.out.println("Something went wrong when processing the data!");
                            isStopped = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            System.out.println("Could not read data!");

                        }
                    }

                    System.out.println("Closing socket!");
                    ois.close();
                    in.close();
                    socket.close();
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Server closed!");
                running = false;
            }
        }
    }
}
