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

//        System.out.println("0: " + KeyEvent.VK_0);
//        System.out.println("1: " + KeyEvent.VK_1);
//        System.out.println("2: " + KeyEvent.VK_2);
//        System.out.println("3: " + KeyEvent.VK_3);
//        System.out.println("4: " + KeyEvent.VK_4);
//        System.out.println("5: " + KeyEvent.VK_5);
//        System.out.println("6: " + KeyEvent.VK_6);
//        System.out.println("7: " + KeyEvent.VK_7);
//        System.out.println("8: " + KeyEvent.VK_8);
//        System.out.println("9: " + KeyEvent.VK_9);
//        System.out.println();
//        System.out.println("A: " + KeyEvent.VK_A);
//        System.out.println("B: " + KeyEvent.VK_B);
//        System.out.println("C: " + KeyEvent.VK_C);
//        System.out.println("D: " + KeyEvent.VK_D);
//        System.out.println("E: " + KeyEvent.VK_E);
//        System.out.println("F: " + KeyEvent.VK_F);
//        System.out.println("G: " + KeyEvent.VK_G);
//        System.out.println("H: " + KeyEvent.VK_H);
//        System.out.println("I: " + KeyEvent.VK_I);
//        System.out.println("J: " + KeyEvent.VK_J);
//        System.out.println("K: " + KeyEvent.VK_K);
//        System.out.println("L: " + KeyEvent.VK_L);
//        System.out.println("M: " + KeyEvent.VK_M);
//        System.out.println("N: " + KeyEvent.VK_N);
//        System.out.println("O: " + KeyEvent.VK_O);
//        System.out.println("P: " + KeyEvent.VK_P);
//        System.out.println("Q: " + KeyEvent.VK_Q);
//        System.out.println("R: " + KeyEvent.VK_R);
//        System.out.println("S: " + KeyEvent.VK_S);
//        System.out.println("T: " + KeyEvent.VK_T);
//        System.out.println("T: " + KeyEvent.VK_U);
//        System.out.println("T: " + KeyEvent.VK_V);
//        System.out.println("T: " + KeyEvent.VK_W);
//        System.out.println("T: " + KeyEvent.VK_X);
//        System.out.println("T: " + KeyEvent.VK_Y);
//        System.out.println("T: " + KeyEvent.VK_Z);
//        System.out.println();
//        System.out.println("SPACE: " + KeyEvent.VK_SPACE);
//        System.out.println("TABE: " + KeyEvent.VK_TAB);
//        System.out.println("QUOTE: " + KeyEvent.VK_QUOTE);
//        System.out.println("AT: " + KeyEvent.VK_AT);
//        System.out.println("PLUS: " + KeyEvent.VK_PLUS);
//        System.out.println("BACKSLASH: " + KeyEvent.VK_BACK_SLASH);
//        System.out.println("LEFT PARENTHESIS: " + KeyEvent.VK_OPEN_BRACKET);
//        System.out.println("RIGHT ,,: " + KeyEvent.VK_CLOSE_BRACKET);
//        System.out.println("COMMA: " + KeyEvent.VK_COMMA);
//        System.out.println("DOT?: " + KeyEvent.VK_PERIOD);
//        System.out.println("EQUALS: " + KeyEvent.VK_EQUALS);
//        System.out.println("DEAD GRAVE: " + KeyEvent.VK_DEAD_GRAVE);
//        System.out.println("MULTIPLY: " + KeyEvent.VK_MULTIPLY);
//        System.out.println("SUBSTRACT: " + KeyEvent.VK_SUBTRACT);
//        System.out.println("ENTER: " + KeyEvent.VK_ENTER);
//        System.out.println("SLASH: " + KeyEvent.VK_SLASH);
//        System.out.println("DELETE: " + KeyEvent.VK_DELETE);
//        System.out.println("Backspace: " + KeyEvent.VK_BACK_SPACE);
//
//        System.out.println("\nLeft Click: " + MouseEvent.BUTTON1_MASK +
//                "\nRight Click: " + MouseEvent.BUTTON3_MASK);

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
