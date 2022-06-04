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
//            System.out.println("box" + messageBox.size());
//            System.out.println(messageBox.getText());
            List<WebElement> tagMessage = messageBox.findElements(By.className("_1beEj"));
            System.out.println("tag" + tagMessage.size());
        } catch (Exception e) {
            if (messageBox == null) {
                status(web);
            }
        }
    }

}
