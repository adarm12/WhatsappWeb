
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.util.List;

public class SendMessage {

    private String messageToSend; // הודעה שנשלחת
    private boolean isSend;

    public SendMessage(String message, ChromeDriver web) {
        this.messageToSend = message;
        this.isSend = false;
        sendMessage(web);
    }

    public void sendMessage(ChromeDriver web) {
        List<WebElement> chatWindow = null;
        try {
            chatWindow = web.findElements(By.tagName("footer"));
            WebElement messageField = chatWindow.get(0).findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]"));
            messageField.sendKeys(this.messageToSend);

            List<WebElement> click = chatWindow.get(0).findElements(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div._3HQNh._1Ae7k > button"));

            click.get(0).click();
            this.isSend = true;

        } catch (Exception e) {
            if (chatWindow.size() == 0)
                sendMessage(web);
        }
    }

    public String getMessageToSend() {
        return messageToSend;
    }

    public void setMessageToSend(String messageToSend) {
        this.messageToSend = messageToSend;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }
}