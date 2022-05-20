
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class WebWindow extends JPanel {

    public static final String WEB = "https://web.whatsapp.com/";
    public static final int ENTER_BUTTON_WIDTH = 250, ENTER_BUTTON_HEIGHT = 100,  ENTER_BUTTON_X= 100,  ENTER_BUTTON_Y= 700;
    public static final int ENTER_LABEL_Y = 0, ENTER_LABEL_WIDTH = 200, ENTER_LABEL_HEIGHT = 150;
    public static final int LENGTH_PHONE_NUMBER = 10;
    public static final String PHONE_START = "05";
    public static final String ISRAELI_AREA_CODE = "972";

    private ImageIcon background;
    private JButton enterButton;
    private JLabel successfullyEnterLabel;
    private JTextField phoneNumber;

    public WebWindow(int x, int y, int width, int height) {
//        System.setProperty("webdriver.chrome.driver", "C:\\1234\\driver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("user-data-air=C:\\Users\\shani\\AppData\\Local\\Temp\\scoped_dir4008_2001821348\\Default");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\adarm\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=c:C:\\Users\\adarm\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1\n");

        this.setBounds(x, y, width, height);
        this.setLayout(null);

        this.enterButton = new MyJButton("התחבר", ENTER_BUTTON_X, ENTER_LABEL_Y, ENTER_BUTTON_WIDTH, ENTER_BUTTON_HEIGHT).getButton();
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
//                enterLabel();
//                repaint();
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

    public JTextField newTextField(int x, int y,int width, int height){
        JTextField textField = new JTextField();
        textField.setBounds(x,y,width,height);
        return textField;
    }

    public boolean phoneNumber(String phoneNumber) {
        int counterValidNumber = 0;
        int counterAreaCodeNum = 0;
        boolean validLength = false;
        boolean validAreaCode = false;
        boolean validPhoneNumber = false;
        if (phoneNumber.length() != LENGTH_PHONE_NUMBER) {
            System.out.println("Invalid value");
        } else {
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (Character.isDigit(phoneNumber.charAt(i)))
                    counterValidNumber++;
            }
            if (counterValidNumber == LENGTH_PHONE_NUMBER)
                validLength = true;
            for (int i = 0; i < PHONE_START.length(); i++) {
                if (phoneNumber.charAt(i) == PHONE_START.charAt(i))
                    counterAreaCodeNum++;
            }
            if (counterAreaCodeNum == PHONE_START.length()) {
                validAreaCode = true;
            }
            if (validLength && validAreaCode)
                validPhoneNumber = true;
            else System.out.println("Invalid value");
        }
        return validPhoneNumber;
    }


    public void paintComponent(Graphics graphics) {
        graphics.drawImage(this.background.getImage(), 0, 0,
                MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT, null);
    }
}





