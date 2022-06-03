import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;

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

        System.setProperty("webdriver.chrome.driver", "C:\\123456\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-air=C:\\Users\\shani\\AppData\\Local\\Temp\\scoped_dir4008_2001821348\\Default");

//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\adarm\\Downloads\\chromedriver_win32 (8)\\chromedriver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("user-data-air=C:\\Users\\adarm\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1\n");

        WebWindow webWindow = new WebWindow(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(webWindow);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
