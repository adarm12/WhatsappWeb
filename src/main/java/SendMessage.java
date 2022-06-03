import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;

public class SendMessage extends JPanel {


    public SendMessage(int x, int y, int width, int height, String message, ChromeDriver web) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);


        System.out.println("hi");


        this.setVisible(true);
    }

    public void sendMessage(ChromeDriver driver, String messageToSend){



    }
//
//    public ChromeDriver sendMessage(ChromeDriver driver, String text) {
//        WebElement footerTextBox = null;
//        try {
//            footerTextBox = driver.findElement(By.tagName("footer"));
//            WebElement textBox = footerTextBox.findElement(By.cssSelector("div[role='textbox']"));
//            textBox.sendKeys(text);
//            footerTextBox.findElement(By.cssSelector("button[class='tvf2evcx oq44ahr5 lb5m6g5c svlsagor p2rjqpw5 epia9gcq']")).click();
//            this.isSent=true;
//        } catch (Exception e) {
//            sendMessage(driver, text);
//        }
//        return driver;
//    }
}

