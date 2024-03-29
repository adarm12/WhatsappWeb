
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WebWindow extends JPanel {

    public static final String CONTACT = "https://api.whatsapp.com/send?phone=";

    public static final int TITLE_Y = 50, TITLE_WIDTH = 900, TITLE_HEIGHT = 100;
    public static final int TITLE_ICON_X = 1000, TITLE_ICON_Y = 50;

    public static final int ENTER_BUTTON_X = 100, ENTER_BUTTON_Y = 700, ENTER_BUTTON_WIDTH = 250, ENTER_BUTTON_HEIGHT = 100;
    public static final int GENERAL_WIDTH = 400, GENERAL_HEIGHT = 50;
    public static final int PHONE_NUM_TITLE_Y = 250, PHONE_NUM_TITLE_WIDTH = 275;
    public static final int MARGIN_BETWEEN = 100;
    public static final int MESSAGE_TITLE_MARGIN = 310, STATUS_MARGIN = 120;
    public static final int MESSAGE_TEXT_MARGIN_X = 210, MESSAGE_TEXT_WIDTH = 500, MESSAGE_TEXT_HEIGHT = 100;
    public static final int ENTER_LABEL_X = 100, ENTER_LABEL_Y = 700, ENTER_LABEL_WIDTH = 500, ENTER_LABEL_HEIGHT = 150;
    public static final int STATUS_LABEL_X = 300;

    private JLabel title;
    private ImageIcon titleIcon;
    private ImageIcon background;
    private JButton enterButton;
    private JLabel successMessageLabel;
    private JLabel phoneNumTitle;
    private JTextField phoneNumberTextField;
    private JLabel messageTitle;
    private JTextField messageTextField;
    private JLabel messageForUser;

    private JLabel statusTitle;
    private JLabel status;

    private ChromeDriver web;
    private WebElement connect;


    public WebWindow(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        this.title = CreateNew.newLabel("WhatsApp Web", width / 2 - TITLE_WIDTH / 2, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
        this.title.setFont(new Font("Gisha", Font.BOLD, 100));
        this.add(this.title);

        this.titleIcon = new ImageIcon("WhatsApp Icon.jpg");

        this.enterButton = CreateNew.newButton("התחבר", ENTER_BUTTON_X, ENTER_BUTTON_Y, ENTER_BUTTON_WIDTH, ENTER_BUTTON_HEIGHT);
        this.add(this.enterButton);
        enter();

        this.phoneNumTitle = CreateNew.newLabel("הכנס מספר פלאפון: ", width - GENERAL_WIDTH,
                PHONE_NUM_TITLE_Y, PHONE_NUM_TITLE_WIDTH, GENERAL_HEIGHT);
        this.add(this.phoneNumTitle);

        this.phoneNumberTextField = CreateNew.newTextField(this.phoneNumTitle.getX() + this.phoneNumTitle.getWidth() - GENERAL_WIDTH,
                this.phoneNumTitle.getY() + this.phoneNumTitle.getHeight(), GENERAL_WIDTH, GENERAL_HEIGHT);
        this.add(this.phoneNumberTextField);

        this.messageTitle = CreateNew.newLabel("הכנס הודעה: ", width - MESSAGE_TITLE_MARGIN,
                this.phoneNumberTextField.getY() + MARGIN_BETWEEN, GENERAL_WIDTH, GENERAL_HEIGHT);
        this.messageTitle.setForeground(Color.WHITE);
        this.add(this.messageTitle);

        this.messageTextField = CreateNew.newTextField(this.messageTitle.getX() + this.messageTitle.getWidth() - MESSAGE_TEXT_WIDTH - MESSAGE_TEXT_MARGIN_X,
                this.messageTitle.getY() + this.messageTitle.getHeight(), MESSAGE_TEXT_WIDTH, MESSAGE_TEXT_HEIGHT);
        this.add(this.messageTextField);

        this.messageForUser = CreateNew.newLabel("", width - GENERAL_WIDTH - GENERAL_WIDTH / 2,
                this.messageTextField.getY() + this.messageTextField.getHeight() + MARGIN_BETWEEN, MESSAGE_TEXT_WIDTH, GENERAL_HEIGHT);
        this.add(this.messageForUser);

        this.successMessageLabel = CreateNew.newLabel("", ENTER_LABEL_X, ENTER_LABEL_Y, ENTER_LABEL_WIDTH, ENTER_LABEL_HEIGHT);
        this.add(this.successMessageLabel);

        this.statusTitle = CreateNew.newLabel("סטטוס ההודעה:", STATUS_LABEL_X, PHONE_NUM_TITLE_Y, PHONE_NUM_TITLE_WIDTH, GENERAL_HEIGHT);
        this.add(this.statusTitle);
        this.status = CreateNew.newLabel("", STATUS_LABEL_X + STATUS_MARGIN, PHONE_NUM_TITLE_Y + GENERAL_HEIGHT, PHONE_NUM_TITLE_WIDTH, GENERAL_HEIGHT);

        this.connect = null;
        this.background = new ImageIcon("web.jpg");
        this.setVisible(true);
    }

    private void enter() {
        this.enterButton.addActionListener((event) -> {
            String phone = this.phoneNumberTextField.getText();
            if (this.messageTextField.getText().equals("") && this.phoneNumberTextField.getText().equals(""))
                this.messageForUser.setText("יש להכניס מס''ט והודעה לשליחה.");
            else if (this.phoneNumberTextField.getText().equals(""))
                this.messageForUser.setText("יש להכניס מספר טלפון.");
            else if (!PhoneNumber.isValidPhoneNumber(phone))
                this.messageForUser.setText("יש להכניס מספר תקין.");
            else if (this.messageTextField.getText().equals(""))
                this.messageForUser.setText("יש להכניס הודעה.");

            if (PhoneNumber.isValidPhoneNumber(phone) && (!this.messageTextField.getText().equals(""))) {
                hideD();
                this.web = new ChromeDriver();
                this.web.get(CONTACT + PhoneNumber.formatPhoneNumber(phone));
                web.manage().window().maximize();

                WebElement chet = this.web.findElement(By.id("action-button"));
                chet.click();

                WebElement linkId = this.web.findElement(By.id("fallback_block"));
                List<WebElement> linkElement = linkId.findElements(By.tagName("a"));
                String chatLink = linkElement.get(1).getAttribute("href");

                this.web.get(chatLink);
                connection();

            }
        });
    }

    public void hideD() {
        this.messageForUser.setVisible(false);
        this.phoneNumberTextField.setEnabled(false);
        this.messageTextField.setEnabled(false);
        this.enterButton.setVisible(false);
    }

    public ChromeDriver connection() {
        new Thread(() -> {
            try {
                this.successMessageLabel.setText("מתחבר.. ");
                this.connect = this.web.findElement(By.id("side"));
                if (this.connect != null) {
                    this.successMessageLabel.setText("ההתחברות בוצעה בהצלחה!");
                    new Thread(() -> {
                        SendMessage sendMessage = new SendMessage(this.messageTextField.getText(), this.web);
                        if (sendMessage.isSend()) {
                            this.successMessageLabel.setText("ההודעה נשלחה בהצלחה!");
                            StatusMessage statusMessage = new StatusMessage(this.status, web);
                            this.add(statusMessage);
                            new Thread(() -> {
                                this.add(this.statusTitle);
                                this.add(this.status);
                                this.status.setText(statusMessage.getStatus().getText());
                            }).start();
                        }
                    }).start();
                }
            } catch (Exception e) {
                connection();
            }
        }).start();
        return this.web;
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(this.background.getImage(), 0, 0,
                MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT, null);
        graphics.drawImage(this.titleIcon.getImage(), TITLE_ICON_X, TITLE_ICON_Y,
                TITLE_HEIGHT, TITLE_HEIGHT, null);
    }

    public JLabel getStatusTitle() {
        return status;
    }

    public void setStatusTitle(JLabel statusTitle) {
        this.status = statusTitle;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public ImageIcon getTitleIcon() {
        return titleIcon;
    }

    public void setTitleIcon(ImageIcon titleIcon) {
        this.titleIcon = titleIcon;
    }

    public void setBackground(ImageIcon background) {
        this.background = background;
    }

    public JButton getEnterButton() {
        return enterButton;
    }

    public void setEnterButton(JButton enterButton) {
        this.enterButton = enterButton;
    }

    public JLabel getSuccessMessageLabel() {
        return successMessageLabel;
    }

    public void setSuccessMessageLabel(JLabel successMessageLabel) {
        this.successMessageLabel = successMessageLabel;
    }

    public JLabel getPhoneNumTitle() {
        return phoneNumTitle;
    }

    public void setPhoneNumTitle(JLabel phoneNumTitle) {
        this.phoneNumTitle = phoneNumTitle;
    }

    public JTextField getPhoneNumberTextField() {
        return phoneNumberTextField;
    }

    public void setPhoneNumberTextField(JTextField phoneNumberTextField) {
        this.phoneNumberTextField = phoneNumberTextField;
    }

    public JLabel getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(JLabel messageTitle) {
        this.messageTitle = messageTitle;
    }

    public JTextField getMessageTextField() {
        return messageTextField;
    }

    public void setMessageTextField(JTextField messageTextField) {
        this.messageTextField = messageTextField;
    }

    public JLabel getMessageForUser() {
        return messageForUser;
    }

    public void setMessageForUser(JLabel messageForUser) {
        this.messageForUser = messageForUser;
    }

    public JLabel getStatus() {
        return status;
    }

    public void setStatus(JLabel status) {
        this.status = status;
    }

    public ChromeDriver getWeb() {
        return web;
    }

    public void setWeb(ChromeDriver web) {
        this.web = web;
    }

    public WebElement getConnect() {
        return connect;
    }

    public void setConnect(WebElement connect) {
        this.connect = connect;
    }
}

