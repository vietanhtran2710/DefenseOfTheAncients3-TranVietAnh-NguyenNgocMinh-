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
        this.live = 1000;
        this.cashInfo = new myText("" + this.cash, 1);
    }

    public Player(int initCash) {
        this.cash = initCash;
        this.live = 1000;
        this.cashInfo = new myText("" + this.cash, 1);
    }

    public void renderMoney(int x, int y) {
        this.cashInfo.render(x, y);
    }

    public Player(int initCash, int initLives) {
        this.cash = initCash;
        this.live = initLives;
        this.cashInfo = new myText("" + this.cash, 1);
    }


    public int getCash() {
        return cash;
    }

    public int getLive() {
        return live;
    }

    public void setCash(int cash) {
        this.cash = cash;
        this.cashInfo = new myText("" + this.cash, 1);
    }

    public void setLive(int live) {
        this.live = live;
    }

    public void takeDamage(int damage) {
        if (this.live < damage)
            this.live = 0;
        else this.live -= damage;
    }

    public void payMoney(int price) {
        this.cash -= price;
        this.cashInfo = new myText("" + this.cash, 1);
    }

    public void earnMoney(int bounty) {
        this.cash += bounty;
        this.cashInfo = new myText("" + this.cash, 1);
    }
}
