package Entity;

import Utils.myText;

import java.awt.*;

public class Player {
    private int live;
    private int cash;

    private myText liveInfo;
    private myText cashInfo;

    public Player(int initCash) {
        this.cash = initCash;
        this.live = 1000;
    }

    public void playerInfo(int x, int y) {
        this.liveInfo = new myText("Live" + "  " + this.live, 0.5);
        this.liveInfo.render(x, y);

        this.cashInfo = new myText("Cash" + " " + this.cash, 0.5);
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
    }

    public void earnMoney(int bounty) {
        this.cash += bounty;
    }
}
