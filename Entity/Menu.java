package Entity;

import Utils.Vertex;
import Utils.myTexture;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class Menu {
    private List<myTexture> buttonList;

    public Menu() {
        buttonList = new ArrayList<>();
        myTexture normalTowerTexture = new myTexture(
                "src/res/GFX/Game/Tower/Normal Tower/NormalTower.png",
                GL_QUADS,
                20,
                624);
        normalTowerTexture.setDisplayWidth(48 * 2); normalTowerTexture.setDisplayHeight(48 * 2);
        buttonList.add(normalTowerTexture);

        myTexture sniperTowerTexture = new myTexture(
                "src/res/GFX/Game/Tower/Sniper Tower/SniperTower.png",
                GL_QUADS,
                20 + 48 * 2 + 20,
                624);
        sniperTowerTexture.setDisplayWidth(48 * 2); sniperTowerTexture.setDisplayHeight(48 * 2);
        buttonList.add(sniperTowerTexture);

        myTexture machineGunTower = new myTexture(
                "src/res/GFX/Game/Tower/Machine Gun Tower/MachineGunTower.png",
                GL_QUADS,
                20 + 48 * 2 + 20 + 48 * 2 + 20,
                624);
        machineGunTower.setDisplayWidth(48 * 2); machineGunTower.setDisplayHeight(48 * 2);
        buttonList.add(machineGunTower);
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
    }
}
