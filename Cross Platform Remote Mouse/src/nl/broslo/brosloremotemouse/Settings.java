package nl.broslo.brosloremotemouse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Bryan on 8-12-2014.
 */
public class Settings {

    private JPanel mainPanel;
    private JTextField textFieldPortNumber;
    private JButton button1;
    private JTextField textFieldIpAddress;

    private int port = 23713;
    private RemoteMouseServer rms = new RemoteMouseServer(port);
    private volatile Thread serverThread;

    public Settings() {

        System.out.println("\nLeft Click: " + MouseEvent.BUTTON1_MASK +
                "\nRight Click: " + MouseEvent.BUTTON3_MASK);

        try {
            textFieldIpAddress.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        textFieldPortNumber.setText(String.valueOf(port));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rms.terminate();
                    serverThread.join();

                    port = Integer.parseInt(textFieldPortNumber.getText());
                    rms = new RemoteMouseServer(port);
                    serverThread = new Thread(rms);
                    serverThread.start();

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        System.out.println(textFieldIpAddress.getText());

        serverThread = new Thread(rms);
        serverThread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Settings");
        Settings settings = new Settings();
        JPanel mainPanel = settings.mainPanel;
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void test() {
        try {
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_A);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
