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
        isSend = true;
        status(web);
        this.status = status;
    }

    public void status(ChromeDriver web) {
        try {
            new Thread(() -> {
                while (!isSeen) {
                    WebElement messageBox = web.findElement(By.id("main"));
                    List<WebElement> tagMessage = messageBox.findElements(By.className("_1beEj"));

                    List<WebElement> afterTag = tagMessage.get(tagMessage.size() - 1).findElements(By.tagName("span"));

                    String status = afterTag.get(1).getAttribute("aria-label");

                    if (status.contains("נמסרה"))
                        isAccepted = true;
                    else if (status.contains("נקראה"))
                        isSeen = true;
                }
            }).start();
            Thread.sleep(5000);
        } catch (Exception e) {
            status(web);
        }
        applyStatus();
        System.out.println(this.status);
    }


    public void applyStatus() {
        if (this.isAccepted)
            this.status.setText("נמסרה");
        if (this.isSeen)
            this.status.setText("נקראה");

    }


}


