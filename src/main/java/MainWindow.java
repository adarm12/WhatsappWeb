import javax.swing.*;

public class MainWindow extends JFrame {

    public static final int WINDOW_X = 0, WINDOW_Y = 0, WINDOW_HEIGHT = 955, WINDOW_WIDTH = 1400;


    public MainWindow() {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("whatsApp Web by adar & shanit");

        ImageIcon whatsappIcon = new ImageIcon("whatsAppWeb.jpg");
        this.setIconImage(whatsappIcon.getImage());

        WebWindow webWindow = new WebWindow(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(webWindow);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
