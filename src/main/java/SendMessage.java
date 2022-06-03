import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;

public class SendMessage extends JPanel {


    public SendMessage(int x, int y, int width, int height, String message, ChromeDriver web) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        System.out.println("hi");


        this.setVisible(true);
    }
}

