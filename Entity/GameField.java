package Entity;

import Entity.Tile.*;
import org.lwjgl.system.CallbackI;

import java.util.List;
import java.util.ArrayList;

public class GameField {
    private List<GameTile> tileList;

    public GameField(GameStage gameStage) {
        tileList = new ArrayList<>();
        int[][] direction = {
                {0, -1},
                {-1, 0},
                {0, 1},
                {1, 0}
        };
        List<List<Integer>> mapInfo = gameStage.getMapArr();
        for (int i = 0; i < mapInfo.size(); i++)
            for (int j = 0; j < mapInfo.get(i).size(); j++) {
                if (mapInfo.get(i).get(j) == 0) {
                    //tileList.add(new Mountain(j * 48, i * 48));
                }
                else
                    if (mapInfo.get(i).get(j) == 9) {
                        tileList.add(new Spawner(0, j * 48, i * 48));
                    }
                    else
                        if (mapInfo.get(i).get(j) == 8) {
                            tileList.add(new Target(11, j * 48, i * 48));
                        }
                    else {
                        int modeBitMask = 0;
                        for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
                            if ((i + direction[dirIndex][0] >= 0) && (i + direction[dirIndex][0] < mapInfo.size()))
                                if ((j + direction[dirIndex][1] >= 0) && (j + direction[dirIndex][1] < mapInfo.get(0).size()))
                                    if (mapInfo.get(i + direction[dirIndex][0]).get(j + direction[dirIndex][1]) != 0)
                                        modeBitMask += 1 << dirIndex;
                        }
                        tileList.add(new Road(modeBitMask ,j * 48, i * 48));
                    }
            }
    }

    public void render() {
        for (int i = 0 ; i < tileList.size(); i++)
            tileList.get(i).render();
    }
}
