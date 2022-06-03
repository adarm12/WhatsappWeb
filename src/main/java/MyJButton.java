//import javax.swing.*;
//import java.awt.*;
//
//public class MyJButton extends JButton {
//    Font myFont = new Font("Gisha", Font.BOLD, 30);
//
//    public static final int BUTTON_WIDTH = 270, BUTTON_HEIGHT = 100;
//
//    private JButton button;
//
//    public MyJButton(String text, int x, int y) {
//        button = new JButton(text);
//        button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
//        button.setFont(myFont);
//        button.setFocusable(false);
//    }
//
//    public MyJButton(String text, int x, int y, int width, int height) {
//        button = new JButton(text);
//        button.setBounds(x, y, width, height);
//        button.setFont(myFont);
//        button.setFocusable(false);
//    }
//
//    public MyJButton(String text, int x, int y, int width, int height, int sizeFont) {
//        button = new JButton(text);
//        button.setBounds(x, y, width, height);
//        Font newFont = new Font("Gisha", Font.BOLD, sizeFont);
//        button.setFont(newFont);
//        button.setFocusable(false);
//    }
//
//    public MyJButton(String text, int x, int y, int width, int height, int sizeFont, Color color) {
//        button = new JButton(text);
//        button.setBounds(x, y, width, height);
//        Font newFont = new Font("Gisha", Font.BOLD, sizeFont);
//        button.setFont(newFont);
//        button.setForeground(color);
//        button.setFocusable(false);
//    }
//
//    public Font getMyFont() {
//        return myFont;
//    }
//
//    public JButton getButton() {
//        return button;
//    }
//
//
//}
