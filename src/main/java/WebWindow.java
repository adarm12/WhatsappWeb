
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;

public class WebWindow extends JFrame {

    public static final String WEB = "https://web.whatsapp.com/";

    public static final int WINDOW_X = 0, WINDOW_Y = 0, WINDOW_HEIGHT = 955, WINDOW_WIDTH = 1400;

    private ImageIcon background;
    private JButton enterButton;

    public WebWindow() {
        System.setProperty("webdriver.chrome.driver", "C:\\1234\\driver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-air=C:\\Users\\shani\\AppData\\Local\\Temp\\scoped_dir4008_2001821348\\Default");

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("whatsApp Web by adar & shanit");

        this.background = new ImageIcon("background.png");

        ImageIcon whatsappIcon = new ImageIcon("whatsAppWeb.jpg");
        this.setIconImage(whatsappIcon.getImage());


        addEnterButton();


//        ChromeDriver driver = new ChromeDriver();
//        driver.get(WEB);
//        driver.manage().window().maximize();



        this.setVisible(true);
    }


    public void addEnterButton() {
        this.enterButton = new MyJButton("התחבר", 200, 100, 500, 500).getButton();
        this.add(enterButton);

    }


    public void paint(Graphics graphics) {
        graphics.drawImage(this.background.getImage(), 0, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT, null);
    }

    public static void main(String[] args) {
        new WebWindow();
    }
}
