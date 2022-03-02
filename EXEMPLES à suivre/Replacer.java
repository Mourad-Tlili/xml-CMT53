package main.java.sur_mesure;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Replacer {
    private ArrayList<String> tags;
    private String replaceWith;
    private String text;
    private int replaced = 0;
    private ArrayList<String> replacedList = new ArrayList<>();

    public Replacer(ArrayList<String> tags, String replaceWith, String text) {
        this.tags = tags;
        this.replaceWith = replaceWith;
        this.text = text;
    }

    private String allBeforeTag(String str, String tag) {
        String regex = regexEverythingButIt(tag);
        Matcher m = Pattern.compile(regex).matcher(str);
        if (m.find()) {
            return m.group();
        }
        return "";
    }

    private static int openingTags = 0;

    private static int hasGoodNumberOfTags(String str, String tag) {
        openingTags = 0;
        String openingRegex = "<" + tag + ">";
        String closingRegex = "</" + tag + ">";
        Matcher m = Pattern.compile(openingRegex).matcher(str);
        int tags = 0;
        while (m.find()) {
            tags++;
            openingTags++;
        }
        m = Pattern.compile(closingRegex).matcher(str);
        while (m.find()) {
            tags--;
        }
        return tags;
    }

    private String largestTag(String str, String tag) throws Exception {
        String tail = "";
        int inside = 0;
        String regex = "</?" + tag + ">";
        do {
            Matcher m = Pattern.compile(regex).matcher(str);
            if (!m.find())
                throw new Exception("Ill formatted file, probably missing tag");
            String curTag = m.group();
            if (curTag.equals("<" + tag + ">")) inside++;
            else inside--;
            String beforeRegex = "[\\s\\S]*?" + regex;
            m = Pattern.compile(beforeRegex).matcher(str);
            if (!m.find())
                throw new Exception("Ill formatted file, probably missing tag");
            String beforeString = m.group();
            tail += beforeString;
            str = str.replaceFirst(beforeString, "");
        } while (inside != 0 && !str.isEmpty());
        return tail;
    }

    private ArrayList<String> divideString(String str, String tag) throws Exception {
        ArrayList<String> divided = new ArrayList<>();
        while (!str.isEmpty()) {
            if (startWithTag(str, tag)) {
                String part = largestTag(str, tag);
                divided.add(part);
                str = str.replaceFirst(part, "");
            } else {
                String regex = "[\\s\\S]*?(?=<" + tag + ">|\\z)";
                Matcher m = Pattern.compile(regex).matcher(str);
                if (!m.find())
                    System.out.println("wtf");
                String part = m.group();
                divided.add(part);
                str = str.replaceFirst(regex, "");
            }
        }
        return divided;
    }

    private String concat(ArrayList<String> all) {
        String res = "";
        for (String s : all) {
            res = res.concat(s);
        }
        return res;
    }

    private boolean startWithTag(String str, String tag) {
        String regex = "<" + tag + ">[\\s\\S]*";
        return str.matches(regex);
    }

    private String subReplace(String curText, ArrayList<String> curTags, String prevTag) throws Exception {
        if (curTags.isEmpty()) {
            String regex = "(?<=<" + prevTag + ">)[\\s\\S]*(?=</" + prevTag + ">)";
            if (curText.matches("[\\S\\s]*?" + regex + "[\\S\\s]*?")) {
                replaced++;
                replacedList.add(curText);
                curText = curText.replaceFirst(regex, replaceWith);
            }
            return curText;
        }
        ArrayList<String> subTexts = divideString(curText, curTags.get(0));
        ArrayList<String> replaceds = new ArrayList<>();
        for (int i = 0; i < subTexts.size(); i++) {
            String s = subTexts.get(i);
            if (startWithTag(s, curTags.get(0))) {
                String replaced = subReplace(s, new ArrayList<>(curTags.subList(1, curTags.size())), curTags.get(0));
                replaceds.add(replaced);
            } else replaceds.add(s);
        }
        return concat(replaceds);
    }

    public String replace() throws Exception {
        replaced = 0;
        if (tags.isEmpty())
            throw new Exception("No tag to replace");
        for (String t : tags) {
            goodNumberOfTagExceptionWrapper(text, t);
        }
        return subReplace(text, tags, tags.get(0));
    }

    public static int goodNumberOfTagExceptionWrapper(String text, String tag) throws Exception {
        int i = hasGoodNumberOfTags(text, tag);
        if (i < 0) {
            throw new Exception("Ill formatted file, to much closing tag: " + tag);
        }
        if (i > 0) {
            throw new Exception("Ill formatted file, missing closing tag: " + tag);
        }
        return i;
    }

    public int getReplaced() {
        return replaced;
    }

    public ArrayList<String> getReplacedList() {
        return replacedList;
    }


    public static String regexEverythingButIt(String word){
        return "[\\s\\S]*?" + word;
    }

    public static String replaceBetweenClosingAndOpening(String text, String tag, String replaceWith) throws Exception {
        goodNumberOfTagExceptionWrapper(text, tag);
        if (Wrapper.lg != null) Wrapper.lg.customWrite("<Stmt> tag(s) found", String.valueOf(openingTags));
        String leftTag = "</" + tag + ">";
        String rightTag = "<" + tag + ">";
        String regex = "(?<=" + leftTag +  ")[\\s\\S]*?.[\\s\\S]*?(?=[\\s]?" + rightTag + ")";
        Matcher  m = Pattern.compile(regex).matcher(text);
        if (m.find()){
            text = m.replaceAll("");
        }
        return text;
    }
}
