
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.util.List;

public class SendMessage {

    private String messageToSend; // הודעה שנשלחת

    public SendMessage(String message, ChromeDriver web) {

        this.messageToSend = message;

        sendMessage(web);

    }

    public void sendMessage(ChromeDriver web) {
        List<WebElement> chatWindow = null;
        try {
            chatWindow = web.findElements(By.tagName("footer"));
//            System.out.println("footer" + chatWindow.size());
            WebElement messageField = chatWindow.get(0).findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]"));
            //System.out.println("clicl" + messageField.size());
            messageField.sendKeys(this.messageToSend);
            System.out.println("send" + this.messageToSend);
            List<WebElement> click = chatWindow.get(0).findElements(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div._3HQNh._1Ae7k > button"));
            System.out.println("click" + click.size());
            click.get(0).click();

//            clickSend(web);

        } catch (Exception e) {
            if (chatWindow.size() == 0)
                sendMessage(web);
        }
    }

    public void clickSend(ChromeDriver web) {
        List<WebElement> chatWindow = web.findElements(By.tagName("footer"));
        List<WebElement> send = chatWindow.get(0).findElements(By.className("_2lMWa"));
        System.out.println("send2" + send.size());
        List<WebElement> sendButton = send.get(0).findElements(By.tagName("button"));
        System.out.println(sendButton.size() + "chat");
        sendButton.get(0).click();
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

