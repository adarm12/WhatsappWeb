import javafx.scene.control.cell.ChoiceBoxTreeCellBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
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
                            System.out.println("נמסרה");
                        } else if (statusWeb.contains("נקראה")) {
                            this.isSeen = true;
                            this.status.setText("נקראה");
                            System.out.println("נקראה");
                        }
                    }).start();
                }
            }).start();
            Thread.sleep(3000);
        } catch (Exception e) {
            status(web);
        }
    }

    public JLabel getStatus() {
        return status;
    }

    public void setStatus(JLabel status) {
        this.status = status;
    }
}


