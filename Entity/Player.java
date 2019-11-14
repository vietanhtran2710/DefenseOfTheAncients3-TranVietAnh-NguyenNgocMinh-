package Entity;

import java.awt.*;

public class Player {
    private int live;
    private int cash;

    public Player(int initCash) {
        this.cash = initCash;
        this.live = 1000;
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
