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
            messageBox = web.findElement(By.id("main"));
            List<WebElement> tag = messageBox.findElements(By.className("_3K4-L"));
            System.out.println("tag" + tag.size());
            List<WebElement> after = tag.get(0).findElements(By.className("_2wUmf message-out focusable-list-item"));
            System.out.println("after" + after.size());
//            List<WebElement> tagMessage = messageBox.findElements(By.className("_1beEj"));
//            System.out.println("tag" + tagMessage.size());
//            List<WebElement> afterTag = tagMessage.get(0).findElements(By.tagName("span"));
//            System.out.println("after" + afterTag.size());
//            System.out.println(afterTag.get(0).getText());
//            System.out.println(afterTag.get(0).getAttribute("span"));
        } catch (Exception e) {
            if (messageBox == null) {
                status(web);
            }
        }
    }

}
