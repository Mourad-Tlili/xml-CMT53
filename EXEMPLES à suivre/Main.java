package main.java.sur_mesure;

public class Main {
    public static void main(String args[]) {
        if (args.length < 1) Wrapper.Replace("parameter.txt");
        else Wrapper.Replace(args[0]);
    }
}
