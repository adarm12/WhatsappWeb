import java.io.FileWriter;
import java.io.IOException;

public class CreateReport {

    public final static String PATH = "C:\\Users\\WhatsappReport\\report";

    private String phoneNumber;
    private String messageSend;
    private String messageAccepted;
    private int reportNumber;
    private String path;

    public CreateReport(String phoneNumber, String messageSend, String messageAccepted) {
        this.phoneNumber = phoneNumber;
        this.messageSend = messageSend;
        this.messageAccepted = messageAccepted;
        this.reportNumber = addReportNumber();
        String allDetails = "The is: " + this.phoneNumber + "\n" +
                "Outgoing message: " + this.messageSend + "\n" +
                "Incoming message: " + this.messageAccepted + "\n";


        this.path = PATH + this.reportNumber + ".txt";
        writeToFile(allDetails, this.path);
    }

    private int addReportNumber() {
        if (this.reportNumber == 0)
            this.reportNumber = 1;
        else
            this.reportNumber++;
        return this.reportNumber;
    }

    public static void writeToFile(String text, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(text);
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
