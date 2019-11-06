package Utils;

public class Point {
    private float x;
    private float y;
    private int intX;
    private int intY;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y) {
        this.intX = x;
        this.intY = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getIntX() {
        return intX;
    }

    public int getIntY() {
        return intY;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setIntX(int intX) {
        this.intX = intX;
    }

    public void setIntY(int intY) {
        this.intY = intY;
    }

    public void setCoordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setIntCoordinate(int x, int y) {
        this.intX = x;
        this.intY = y;
    }
}
