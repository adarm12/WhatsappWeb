
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.util.List;

public class SendMessage {

    private String messageToSend; // הודעה שנשלחת
    private String messageAccepted; // הודעה שהתתקבלה

    public SendMessage(String message, ChromeDriver web) {

        this.messageToSend = message;

        System.out.println("hi");
        sendMessage(web);

    }

    public void sendMessage(ChromeDriver web) {

        List<WebElement> chatWindow = web.findElements(By.className("_6h3Ps"));
        System.out.println("mess/" + chatWindow.size());

        List<WebElement> messageField = chatWindow.get(0).findElements(By.className("_1UWac _1LbR4"));
        System.out.println("clicl" + messageField.size());

        messageField.get(0).click();
        messageField.get(0).sendKeys(this.messageToSend);
        System.out.println("send");


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

