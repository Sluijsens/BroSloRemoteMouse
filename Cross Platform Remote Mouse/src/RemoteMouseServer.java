import javafx.scene.input.KeyCode;
import nl.broslo.brosloremotemouse.socket.MouseMovement;
import nl.broslo.brosloremotemouse.socket.SocketAction;

import java.awt.*;
import java.awt.event.MouseEvent;
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
    private Robot robot;

    public RemoteMouseServer(int port) {

        this.port = port;
        mousePointerLocation = MouseInfo.getPointerInfo().getLocation();
        try {
            robot = new Robot();
//            robot.setAutoDelay(40);
            robot.setAutoWaitForIdle(true);
        } catch (AWTException e) {
            e.printStackTrace();
        }
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
                    while(!isStopped && running) {

                        try {
                            //Thread.sleep(100);

                            SocketAction socketAction = (SocketAction) ois.readObject();

                            if(socketAction.getKey().equals(SocketAction.ACTION_MOUSE_MOVE) && socketAction.getMouseMovement() != null) {

                                MouseMovement mouseMovement = socketAction.getMouseMovement();
                                mousePointerLocation = MouseInfo.getPointerInfo().getLocation();

                                double movementMultiplier = 1.5;
                                double newX = mousePointerLocation.getX() + (mouseMovement.getMovementX() * movementMultiplier);
                                double newY = mousePointerLocation.getY() + (mouseMovement.getMovementY() * movementMultiplier);

                                System.out.println("\nX: " + newX + " -> " + mousePointerLocation.getX() + " + " + mouseMovement.getMovementX() +
                                        "\nY: " + newY + " -> "  + mousePointerLocation.getX() + " + " + mouseMovement.getMovementX());

                                  robot.mouseMove((int) newX, (int) newY);

                            } else if(socketAction.getKey().equals(SocketAction.ACTION_MOUSE_DOWN)) {

                                robot.mousePress(socketAction.getValue());

                            } else if(socketAction.getKey().equals(SocketAction.ACTION_MOUSE_UP)) {

                                robot.delay(100);
                                robot.mouseRelease(socketAction.getValue());

                            } else if (socketAction.getKey().equals(SocketAction.ACTION_KEYBOARD_TYPE)) {

                                robot.keyPress(socketAction.getValue());
                                robot.delay(200);
                                robot.keyRelease(socketAction.getValue());

                            }

                        } catch (IOException e) {
                            System.out.println("Something went wrong when processing the data!");
                            isStopped = true;
                        } catch (ClassNotFoundException e) {
                            System.out.println("Could not read data!");
                            e.printStackTrace();

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
