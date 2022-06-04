import javafx.scene.control.cell.ChoiceBoxTreeCellBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class StatusMessage {

    private boolean isSend; // הודעה שנשלחה
    private boolean isAccepted; // הודעה שנשלחה והתקבלה
    private boolean isSeen; // וי כחול - הודעה שנקראה
    private String messageAccepted;


    public StatusMessage(ChromeDriver web) {
        status(web);

    }

    public void status(ChromeDriver web) {
        WebElement messageBox = null;
        try {
            while (true) {
                messageBox = web.findElement(By.id("main"));
                List<WebElement> tagMessage = messageBox.findElements(By.className("_1beEj"));
                System.out.println("tag" + tagMessage.size());
                List<WebElement> afterTag = tagMessage.get(tagMessage.size() - 1).findElements(By.tagName("span"));
                System.out.println("after" + afterTag.size());
                String status = afterTag.get(1).getAttribute("aria-label");
                System.out.println(status);
            }
        } catch (
                Exception e) {
            if (messageBox == null) {
                status(web);
            }
        }
    }
}
