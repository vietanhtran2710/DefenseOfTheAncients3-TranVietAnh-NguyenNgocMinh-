package Entity;

import Utils.myText;

import java.awt.*;

public class Player {
    private int live;
    private int cash;

    private myText liveInfo;
    private myText cashInfo;

    public Player() {
        this.cash = 0;
        this.live = 0;
        this.liveInfo = new myText("Live" + "  " + this.live, 0.5);
        this.cashInfo = new myText("Cash" + " " + this.cash, 0.5);
    }

    public Player(int initCash) {
        this.cash = initCash;
        this.live = 1000;
        this.liveInfo = new myText("Live" + "  " + this.live, 0.5);
        this.cashInfo = new myText("Cash" + " " + this.cash, 0.5);
    }

    public Player(int initCash, int initLives) {
        this.cash = initCash;
        this.live = initLives;
        this.liveInfo = new myText("Live" + "  " + this.live, 0.5);
        this.cashInfo = new myText("Cash" + " " + this.cash, 0.5);
    }


    public void playerInfo(int x, int y) {
        this.liveInfo.render(x, y);
        this.cashInfo.render(x, y + 50);
    }

    public int getCash() {
        return cash;
    }

    public int getLive() {
        return live;
    }

    public void setCash(int cash) {
        this.cash = cash;
        this.cashInfo = new myText("Cash" + " " + this.cash, 0.5);
    }

    public void setLive(int live) {
        this.live = live;
        this.liveInfo = new myText("Live" + "  " + this.live, 0.5);
    }

    public void takeDamage(int damage) {
        if (this.live < damage)
            this.live = 0;
        else this.live -= damage;
        this.liveInfo = new myText("Live" + "  " + this.live, 0.5);
    }

    public void payMoney(int price) {
        this.cash -= price;
        this.cashInfo = new myText("Cash" + " " + this.cash, 0.5);
    }

    public void earnMoney(int bounty) {
        this.cash += bounty;
        this.cashInfo = new myText("Cash" + " " + this.cash, 0.5);
    }
}
