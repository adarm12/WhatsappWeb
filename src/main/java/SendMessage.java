
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
        List<WebElement> chatWindow = null;
        try {
            chatWindow = web.findElements(By.tagName("footer"));
            System.out.println("footer" + chatWindow.size());
            List<WebElement> messageField = chatWindow.get(0).findElements(By.className("_1UWac _1LbR4"));
            System.out.println("clicl" + messageField.size());
            messageField.get(0).sendKeys(this.messageToSend);
            System.out.println("send");

        } catch (Exception e) {
            if (chatWindow == null)
                sendMessage(web);
        }
    }

    public WebElement readyToSend(ChromeDriver web) {
        WebElement chatWindowFiled = null;
        List<WebElement> chatWindow = null;
        try {

            System.out.println("mess/" + chatWindow.size());
            chatWindowFiled = chatWindow.get(0);
        } catch (Exception e) {
            if (chatWindow.size() == 0)
                readyToSend(web);
        }
        return chatWindowFiled;
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

