package main.java.sur_mesure;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

public class InOutManager {

    public static char delimiter = '=';
    public static char tagsDelimiter = ',';

    public static ArrayList<String> allLinesFileReader(String path) throws Exception {
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = null;
        scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        scanner.close();
        return lines;
    }

    public static void fillLine(String line, Dictionary<String, String> toFill, ArrayList<String> tags) {
        String[] lineSplited = line.split(String.valueOf(delimiter));
        if (lineSplited.length < 2 || line.startsWith("#"))
            return;
        if (lineSplited[0].equals(Wrapper.tagsName)) {
            String[] splitedTags = lineSplited[1].split(String.valueOf(tagsDelimiter));
            for (String t : splitedTags) {
                tags.add(t);
            }
        } else
            toFill.put(lineSplited[0], lineSplited[1]);
    }

    public static Dictionary<String, String> load(String path, Dictionary<String, String> toFill, ArrayList<String> tags) throws Exception{
        ArrayList<String> lines = allLinesFileReader(path);
        lines.forEach(x -> fillLine(x, toFill, tags));
        return toFill;
    }

    public static String allByteFileReader(String path) throws Exception{
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (Exception e) {
            throw e;
        }
        String content = new String(bytes);
        return content;
    }

    public enum WriteType {
        WRITE,
        APPEND
    }

    public static void write(String content, String path, WriteType WT) throws Exception {
        File oFile = new File(path);
        try {
            if (oFile.createNewFile()) {
                System.out.println("File created: " + oFile.getName());
            } else {
                if (WT.equals(WriteType.WRITE)) {
                    throw new Exception("Output file already exist");
                }
            }
            FileWriter fw = new FileWriter(oFile, WT.equals(WriteType.APPEND));
            fw.write(content);
            fw.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static String move(String toMoveDir, String fileName, String outputDirPath, String OFileName) throws Exception {
        try {
            Path OutDirFullPath = Paths.get(outputDirPath, OFileName);
            Files.move(Paths.get(toMoveDir, fileName), OutDirFullPath);
            System.out.println("File moved: " + fileName + "to " + outputDirPath);
            return OutDirFullPath.toString();
        } catch (Exception e) {
            throw e;
        }
    }
}
