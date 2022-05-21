
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class WebWindow extends JPanel {

    Font font = new Font("Gisha", Font.BOLD, 30);

    public static final String WEB = "https://web.whatsapp.com/";

    public static final int ENTER_BUTTON_X = 100, ENTER_BUTTON_Y = 700, ENTER_BUTTON_WIDTH = 250, ENTER_BUTTON_HEIGHT = 100;
    public static final int ENTER_LABEL_Y = 0, ENTER_LABEL_WIDTH = 200, ENTER_LABEL_HEIGHT = 150;
    public static final int LENGTH_PHONE_NUMBER = 10;
    public static final String PHONE_START = "05", ISRAELI_AREA_CODE = "972";

    private ImageIcon background;
    private JButton enterButton;
    private JLabel successfullyEnterLabel;
    private JLabel phoneNumTitle;
    private JTextField phoneNumberTextField;

    public WebWindow(int x, int y, int width, int height) {
        System.setProperty("webdriver.chrome.driver", "C:\\1234\\driver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-air=C:\\Users\\shani\\AppData\\Local\\Temp\\scoped_dir4008_2001821348\\Default");
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\adarm\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("user-data-dir=c:C:\\Users\\adarm\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1\n");

        this.setBounds(x, y, width, height);
        this.setLayout(null);

        this.enterButton = new MyJButton("התחבר", ENTER_BUTTON_X, ENTER_BUTTON_Y, ENTER_BUTTON_WIDTH, ENTER_BUTTON_HEIGHT).getButton();
        this.add(this.enterButton);
        enter();

        this.phoneNumTitle = newLabel("הכנס מספר טלפון:", 800, 250, 250, 180);
        this.add(this.phoneNumTitle);
//        this.phoneNumberTextField = newTextField(this.phoneNumTitle.getX(),
//                this.phoneNumTitle.getY() + this.phoneNumTitle.getHeight(), 500, 500);
//        this.add(this.phoneNumberTextField);

        //      this.background = new ImageIcon("background.png");
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
                this.successfullyEnterLabel = newLabel("התתחברות בוצעה בהצלחה", MainWindow.WINDOW_WIDTH - ENTER_LABEL_WIDTH,
                        ENTER_LABEL_Y, ENTER_LABEL_WIDTH, ENTER_LABEL_HEIGHT);

            }
        });
    }

    public JLabel newLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        this.setBounds(x, y, width, height);
        label.setFont(font);
        return label;
    }

    public JTextField newTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        return textField;
    }

    public boolean phoneNumber1(String phone) {
        boolean validPhoneNumber = false;
        try {
            if (phone.length() != 0) {
                if (phone.length() < 10 || phone.length() > 12) {
                    System.out.println("Invalid value");
                } else {
                    if (phone.substring(0, 2).equals("05") && phone.length() == 10) {
                        validPhoneNumber = true;
                    } else if (phone.substring(0, 3).equals("972") && phone.length() == 12)
                        validPhoneNumber = true;
                }
            } else
                System.out.println("יש להכניס מספר טלפון - לא תיבה ריקה");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return validPhoneNumber;
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
//        graphics.drawImage(this.background.getImage(), 0, 0,
//                MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT, null);
    }
}





