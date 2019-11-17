package Utils;

public class Timer {
    public static double getTime() {
        return (double)System.nanoTime() / 1000000000L;
    }
} // second
