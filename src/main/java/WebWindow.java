
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WebWindow extends JPanel {

    public static final String WEB = "https://web.whatsapp.com/";
    public static final int ENTER_BUTTON_WIDTH = 250, ENTER_BUTTON_HEIGHT = 100;

    private ImageIcon background;
    private JButton enterButton;

    public WebWindow(int x, int y, int width, int height) {
        System.setProperty("webdriver.chrome.driver", "C:\\1234\\driver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-air=C:\\Users\\shani\\AppData\\Local\\Temp\\scoped_dir4008_2001821348\\Default");

        this.setBounds(x, y, width, height);
        this.setLayout(null);

        this.enterButton = new MyJButton("Enter", width / 2, height / 2, ENTER_BUTTON_WIDTH, ENTER_BUTTON_HEIGHT).getButton();
        this.add(this.enterButton);

        enter();

        this.background = new ImageIcon("background.png");
        this.setVisible(true);
    }

    private void enter() {
        this.enterButton.addActionListener((event) -> {
            this.enterButton.setVisible(false);
            repaint();
            ChromeDriver driver = new ChromeDriver();
            driver.get(WEB);
            driver.manage().window().maximize();
        });
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(this.background.getImage(), 0, 0,
                MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT, null);
    }


}





