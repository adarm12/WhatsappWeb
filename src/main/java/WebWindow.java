
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class WebWindow extends JPanel {

    public static final String WEB = "https://web.whatsapp.com/";
    public static final int ENTER_BUTTON_WIDTH = 250, ENTER_BUTTON_HEIGHT = 100;
    public static final int ENTER_LABEL_Y = 0, ENTER_LABEL_WIDTH = 200, ENTER_LABEL_HEIGHT = 150;


    private ImageIcon background;
    private JButton enterButton;
    private JLabel successfullyEnterLabel;

    public WebWindow(int x, int y, int width, int height) {
        System.setProperty("webdriver.chrome.driver", "C:\\1234\\driver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-air=C:\\Users\\shani\\AppData\\Local\\Temp\\scoped_dir4008_2001821348\\Default");

        this.setBounds(x, y, width, height);
        this.setLayout(null);

        this.enterButton = new MyJButton("התחבר", width / 2, height / 2, ENTER_BUTTON_WIDTH, ENTER_BUTTON_HEIGHT).getButton();
        this.add(this.enterButton);

        enter();

        this.background = new ImageIcon("background.png");
        this.setVisible(true);
    }

    private void enter() { //TODO action listener++++++++++++++++++++++++
        this.enterButton.addActionListener((event) -> {
            this.enterButton.setVisible(false);
            repaint();
            ChromeDriver web = new ChromeDriver();
            web.get(WEB);
            web.manage().window().maximize();
            List<WebElement> pic = web.findElements(By.className("web"));
            if (pic.size() > 0) {
                System.out.println("find");
                enterLabel();
            }
        });
    }

    public void enterLabel() {
        this.successfullyEnterLabel = new JLabel("ההתחברות בוצעה בהצלחה");
        this.setBounds(MainWindow.WINDOW_WIDTH - ENTER_LABEL_WIDTH, ENTER_LABEL_Y, ENTER_LABEL_WIDTH, ENTER_LABEL_HEIGHT);
        Font font = new Font("Gisha", Font.BOLD, 30);
        this.successfullyEnterLabel.setFont(font);
        this.add(this.successfullyEnterLabel);
    }


    public void paintComponent(Graphics graphics) {
        graphics.drawImage(this.background.getImage(), 0, 0,
                MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT, null);
    }
}





