
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

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.background = new ImageIcon("background.png");
        addEnterButton();
    }

    public void addEnterButton() {
        this.enterButton = addButton("התחבר", (WINDOW_WIDTH / 2) - (100),
                (WINDOW_HEIGHT / 2) - (100), 200, 200);
        repaint();
        enter();
    }

    private JButton addButton(String buttonName, int x, int y, int width, int height) {
        JButton addButton = new JButton(buttonName);
        this.setVisible(true);
        this.add(addButton);
        addButton.setBounds(x, y, width, height);
        return addButton;
    }

    private void enter() {
        this.enterButton.addActionListener((event) -> {
            this.enterButton.setVisible(false);
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\adarm\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("user-data-dir=c:C:\\Users\\adarm\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1\n");
            ChromeDriver driver = new ChromeDriver();
            driver.get(WEB);
            driver.manage().window().maximize();
        });
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(this.background.getImage(), 0, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT, null);
    }

    public static void main(String[] args) {
        new WebWindow();
    }
}





