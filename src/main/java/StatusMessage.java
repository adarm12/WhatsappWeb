import javafx.scene.control.cell.ChoiceBoxTreeCellBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class StatusMessage extends JPanel {

    private boolean isSend; // הודעה שנשלחה
    private boolean isAccepted; // הודעה שנשלחה והתקבלה
    private boolean isSeen; // וי כחול - הודעה שנקראה
    private String messageAccepted;

    private JLabel status;

    public StatusMessage(JLabel status, ChromeDriver web) {
        this.isSend = true;
        this.isAccepted = false;
        this.isSeen = false;
        status(web);
        this.status = status;
    }

    public void status(ChromeDriver web) {
        try {
            new Thread(() -> {
                while (!this.isSeen) {
                    WebElement messageBox = web.findElement(By.id("main"));
                    List<WebElement> tagMessage = messageBox.findElements(By.className("_1beEj"));
                    List<WebElement> afterTag = tagMessage.get(tagMessage.size() - 1).findElements(By.tagName("span"));
                    String statusWeb = afterTag.get(1).getAttribute("aria-label");
                    new Thread(() -> {
                        if (statusWeb.contains("נמסרה")) {
                            this.isAccepted = true;
                            this.status.setText("נמסרה");
                        } else if (statusWeb.contains("נקראה")) {
                            this.isSeen = true;
                            this.status.setText("נקראה");
                            incomingMessage(web);
                        }
                    }).start();
                }
            }).start();
            Thread.sleep(3000);
        } catch (Exception e) {
            status(web);
        }
    }

    public void incomingMessage(ChromeDriver web) {
        try {
            new Thread(() -> {
                List<WebElement> messagesList = web.findElements(By.className("_3K4-L"));
                System.out.println("list" + messagesList.size());
                List<WebElement> incomingMessages = messagesList.get(0).findElements(By.className("_22Msk"));
                System.out.println("in" + incomingMessages.size());
                List<WebElement> lastTag = incomingMessages.get(0).findElements(By.className("_1Gy50"));
                System.out.println("last" + lastTag.size());
                List<WebElement> tag = lastTag.get(0).findElements(By.cssSelector("#main > div._2gzeB > div > div._33LGR > div._3K4-L > div:nth-child(17) > div > div.Nm1g1._22AX6 > div._22Msk > div.copyable-text > div > span.i0jNr.selectable-text.copyable-text"));
                System.out.println("text: " + tag.get(0).getText());
//                String tagText = tag.get(tag.size() - 1).getAttribute("dir");
//                System.out.println(tagText);
            }).start();
        } catch (Exception e) {
            incomingMessage(web);
        }
    }


    public JLabel getStatus() {
        return status;
    }

    public void setStatus(JLabel status) {
        this.status = status;
    }
}


