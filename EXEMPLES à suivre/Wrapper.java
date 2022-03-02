package main.java.sur_mesure;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Wrapper {
    public static final String inputFileName = "inputFileName";
    public static final String inputDirectoryPath = "inputDirectoryPath";
    public static final String outputFileName = "outputFileName";
    public static final String outputDirectoryPath = "outputDirectoryPath";
    public static final String logDirPath = "logDirPath";
    public static final String wordToReplaceWith = "wordToReplaceWith";
    public static final String emailServer = "emailServer";
    public static final String sendingAddress = "sendingAddress";
    public static final String receivingAddress = "receivingAddress";
    public static final String tagsName = "tagsName";
    public static final String archiveInputDirectoryPath = "archiveInputDirectoryPath";
    public static final String archiveOutputDirectoryPath = "archiveOutputDirectoryPath";

    public static LogWriter lg = null;
    public static EmailSender es = null;
    public static String fileNAME = null;

    public static void Replace(String configPath) {
        try {
            if (configPath == null)
                throw new Exception("Missing path to config file");
            ArrayList<String> tags = new ArrayList<>();
            Dictionary<String, String> dico = InOutManager.load(configPath, MakeDico(), tags);

            String replaceWith = dico.get(wordToReplaceWith);

            String inputDirPath = dico.get(inputDirectoryPath);
            String IFilePattern = dico.get(inputFileName);

            String outputDirPath = dico.get(outputDirectoryPath);
            String OFileName = dico.get(outputFileName);

            String archiveInDirPath = dico.get(archiveInputDirectoryPath);
            String archiveOutDirPath = dico.get(archiveOutputDirectoryPath);

            String LogDirectoryPath = dico.get(logDirPath);

            String EmailServer = dico.get(emailServer);
            String SendingAddress = dico.get(sendingAddress);
            String ReceivingAddress = dico.get(receivingAddress);

            es = new EmailSender(SendingAddress, ReceivingAddress, EmailServer);
            lg = new LogWriter(LogDirectoryPath);
            lg.writeDate();
            lg.writeLineLog("");
            // writeLoadedArg(dico);
            // lg.writeLineLog("");
            // lg.writeLineLog("");
            File inputDir = new File(inputDirPath);
            ArrayList<String> subFiles = new ArrayList<>(Arrays.asList(inputDir.list()));
            String fileContent = fileContentFromParentDir(inputDir, IFilePattern);
            lg.customWrite("Input File Name Found", fileNAME);
            lg.writeLineLog("\n");

            String preTraited = Replacer.replaceBetweenClosingAndOpening(fileContent, "Stmt", "");

            Replacer replacer = new Replacer(tags, replaceWith, preTraited);
            String outPutContent = replacer.replace();
            if (replacer.getReplaced() == 0)
                throw new Exception("No replacement done, probably no occurrence of the tag");
            lg.writeLineLog("Replacement(s) done successfully.");
            lg.customWrite("Number of replacement(s)", String.valueOf(replacer.getReplaced()));
            lg.customWrite("Old values replaced",  ":");
            for (int i = 0; i < replacer.getReplacedList().size(); i++) {
                lg.customWrite(String.valueOf(i), replacer.getReplacedList().get(i));
            }
            lg.writeLineLog("");
            String archiveOutPath = archiveName(Paths.get(archiveOutDirPath, OFileName).toString());
            String outputFileCompleteName = new File(outputDirPath, OFileName).getPath();

            InOutManager.write(outPutContent, outputFileCompleteName, InOutManager.WriteType.WRITE);
            lg.writeLineLog("Out file successfully generated: " + outputFileCompleteName);

            InOutManager.write(outPutContent, archiveOutPath,
                    InOutManager.WriteType.WRITE);
            lg.customWrite("Out File Archived", "to: " + archiveOutPath);

            String archName = InOutManager.move(inputDirPath, fileNAME, archiveInDirPath, archiveName(fileNAME));
            lg.customWrite("In File Archived", "to: " + archName);

            lg.customWrite("Output File name", OFileName);
            lg.writeDurationSinceCreation();
            es.sendMail("Sur-mesure \"SANTEN CAMT053\" : Traitement OK", lg.logContent());
            lg.writeOK();
        } catch (Exception exception) {
            try {
                if (lg == null)
                    lg = new LogWriter("");
                for (int i = 0; i < 6; i++)
                    lg.customWrite("ERROR", "[ERROR].....[ERROR]");
                lg.writeLineLog("");
                lg.writeLineLog(exception.getMessage());
                String stackTrace = "";
                for (int i = 0; i < exception.getStackTrace().length; i++)
                    stackTrace = stackTrace.concat("\n" + exception.getStackTrace()[i].toString());
                lg.writeLineLog(stackTrace);
                if (es != null)
                    es.sendMail("Sur-mesure \"SANTEN CAMT053\" : Traitement KO",
                            lg.logContent());
            } catch (Exception exception2) {
                exception2.printStackTrace();
            }
        }
    }

    private static String fileContentFromParentDir(File parent, String name) throws Exception {
        File[] files = parent.listFiles();
        String childPath = null;
        for (File f : files) {
            String s = f.getName();
            if (s.matches(name)) {
                if (childPath != null) {
                    throw new Exception("Multiple input files");
                }
                fileNAME = s;
                childPath = f.getPath();
            }
        }
        if (childPath == null)
            throw new Exception("Input File not found");
        return InOutManager.allByteFileReader(childPath);
    }

    public static Dictionary<String, String> MakeDico() {
        Dictionary<String, String> dico = new Hashtable<>();
        return dico;
    }

    public static String archiveName(String fileName) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String coreName = fileName.replaceFirst("\\.[^\\.]*", "");
        String extension = fileName.replaceFirst("[\\s\\S]*(?=\\.)", "");
        return coreName + localDateTime.format(dateTimeFormatter) + extension;
    }

    private static void writeLoadedArg(Dictionary<String, String> dico) throws Exception {
        lg.writeLineLog("[Loaded values]");
        Enumeration<String> keys = dico.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = dico.get(key);
            lg.writeLineLog(key + " : " + value);
        }
    }
}
