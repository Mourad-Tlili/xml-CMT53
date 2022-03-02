package main.java.sur_mesure;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LogWriter {
    private String logFilePath;
    private Date date;
    private String logContent = "";

    public LogWriter(String logDirPath) {
        logContent = "";
        date = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String fileName = "log_" + localDateTime.format(dateTimeFormatter) + ".txt";
        this.logFilePath = Paths.get(logDirPath, fileName).toString();
    }

    public void writeLineLog(String line) throws Exception {
        String content = line + "\n";
        logContent = logContent.concat(content);
        InOutManager.write(content, logFilePath, InOutManager.WriteType.APPEND);
    }

    public void customWrite(String label, String content) throws Exception {
        writeLineLog("[" + label + "]....." + content + ".");
    }

    public void writeDate() throws Exception {
        customWrite("Date", date.toString());
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void writeDurationSinceCreation() throws Exception {
        customWrite("Duration", +(new Date().getTime() - date.getTime()) + "ms");
    }

    public void writeOK() throws Exception {
        writeLineLog("\n");
        for (int i = 0; i < 6; i++)
            customWrite("OK", "[OK].....[OK]");
    }

    public String logContent(){
        return logContent;
    }
}
