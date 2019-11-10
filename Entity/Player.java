package Entity;

public class Player {
    private int live;
    private int cash;

    public Player(int initCash) {
        this.cash = initCash;
        this.live = 30;
    }

    public int getCash() {
        return cash;
    }

    public int getLive() {
        return live;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setLive(int live) {
        this.live = live;
    }
}
