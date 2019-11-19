package Entity;

import Entity.Towers.NormalTower;
import Entity.Towers.SniperTower;
import Entity.Towers.Tower;
import Utils.Vertex;
import Utils.myTexture;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class Menu {
    private List<myTexture> buttonList;
    private List<Integer> priceList;
    private final int playX = 1193;
    private int playY = 657;
    private boolean isMuted = false;
    private myTexture soundButton;

    public Menu() {
        buttonList = new ArrayList<>();
        priceList = new ArrayList<>();
        soundButton = new myTexture("src/res/GFX/GUI/Button/Sound_on.png", GL_QUADS, 1193 - 20 - 48 * 2, 657);
        soundButton.setDisplayWidth(48 * 2);
        soundButton.setDisplayHeight(48 * 2);
        myTexture normalTowerTexture = new myTexture(
                "src/res/GFX/Game/Tower/Normal Tower/NormalTower.png",
                GL_QUADS,
                40,
                657);
        normalTowerTexture.setDisplayWidth(48 * 2); normalTowerTexture.setDisplayHeight(48 * 2);
        buttonList.add(normalTowerTexture);

        myTexture sniperTowerTexture = new myTexture(
                "src/res/GFX/Game/Tower/Sniper Tower/SniperTower.png",
                GL_QUADS,
                40 + 48 * 2 + 20,
                657);
        sniperTowerTexture.setDisplayWidth(48 * 2); sniperTowerTexture.setDisplayHeight(48 * 2);
        buttonList.add(sniperTowerTexture);

        myTexture machineGunTower = new myTexture(
                "src/res/GFX/Game/Tower/Machine Gun Tower/MachineGunTower.png",
                GL_QUADS,
                40 + 48 * 2 + 20 + 48 * 2 + 20,
                657);
        machineGunTower.setDisplayWidth(48 * 2); machineGunTower.setDisplayHeight(48 * 2);
        buttonList.add(machineGunTower);

        priceList.add(10);
        priceList.add(20);
        priceList.add(30);
    }

    public void render() {
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).bind();
            buttonList.get(i).displayByPartitionVertex(
                    new Vertex(0, 0),
                    new Vertex(0.2f, 0),
                    new Vertex(0.2f, 1),
                    new Vertex(0, 1)
            );
        }
        soundButton.bind();
        soundButton.display();
    }

    public int getUpgradePrice(Tower tower) {
        if (tower instanceof NormalTower) {
            return (int) (priceList.get(0) * 1.5);
        }
        else if (tower instanceof SniperTower) {
            return (int) (priceList.get(1) * 1.5);
        }
        else return (int) (priceList.get(2) * 1.5);
    }

    public int getSellPrice(Tower tower) {
        if (tower instanceof NormalTower) {
            return (int) ((priceList.get(0) * (0.5 * tower.getLevel())));
        }
        else if (tower instanceof SniperTower) {
            return (int) ((priceList.get(1) * (0.5 * tower.getLevel())));
        }
        else return (int) ((priceList.get(2) * (0.5 * tower.getLevel())));
    }

    public List<myTexture> getButtonList() {
        return buttonList;
    }

    public List<Integer> getPriceList() {
        return priceList;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public myTexture getSoundButton() {
        return soundButton;
    }
}
