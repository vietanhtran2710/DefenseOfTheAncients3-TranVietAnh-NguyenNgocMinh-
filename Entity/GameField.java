package Entity;

import Entity.Enemies.Enemy;
import Entity.Tile.*;
import Entity.Towers.Tower;
import Utils.Point;
import Utils.myTexture;
import org.lwjgl.system.CallbackI;

import java.util.List;
import java.util.ArrayList;

public class GameField {
    private List<GameTile> tileList;
    private List<Tower> towers;
    private List<Enemy> enemies;
    private Spawner spawner;
    private Target target;
    private Path path;

    public GameField(GameStage gameStage) {
        tileList = new ArrayList<>();
        towers = new ArrayList<>();
        enemies = new ArrayList<>();
        spawner = null; target = null;
        path = new Path();

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
                        spawner =  new Spawner(j * 48, i * 48);
                        spawner.setWave("3 3 3 3 3 3 3 3 3");
                    }
                    else
                        if (mapInfo.get(i).get(j) == 8) {
                            target = new  Target(j * 48, i * 48);
                        }
                    else {
                        int modeBitMask = 0;
                        for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
                            if ((i + direction[dirIndex][0] >= 0) && (i + direction[dirIndex][0] < mapInfo.size()))
                                if ((j + direction[dirIndex][1] >= 0) && (j + direction[dirIndex][1] < mapInfo.get(0).size()))
                                    if (mapInfo.get(i + direction[dirIndex][0]).get(j + direction[dirIndex][1]) != 0)
                                        modeBitMask += 1 << dirIndex;
                        }
                        switch (modeBitMask) {
                            case 3:
                                List<Integer> newItem = new ArrayList<Integer>();
                                newItem.add(j * 48); newItem.add(i * 48); newItem.add(3);
                                path.addItem(newItem);
                                break;
                            case 9:
                                newItem = new ArrayList<Integer>();
                                newItem.add(j * 48); newItem.add(i * 48); newItem.add(9);
                                path.addItem(newItem);
                                break;
                            case 6:
                                newItem = new ArrayList<Integer>();
                                newItem.add(j * 48); newItem.add(i * 48); newItem.add(6);
                                path.addItem(newItem);
                                break;
                            case 12:
                                newItem = new ArrayList<Integer>();
                                newItem.add(j * 48); newItem.add(i * 48); newItem.add(12);
                                path.addItem(newItem);
                                break;
                        }
                        tileList.add(new Road(modeBitMask ,j * 48, i * 48));
                    }
            }
        for (int i = 0; i < path.getPath().size(); i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(path.getPath().get(i).get(j) + " ");
            System.out.println("");
        }
    }

    public void checkEnemyDirection() {
        List<List<Integer>> paths = path.getPath();
        for (int i = 0; i < enemies.size(); i++)
            for (int j = 0; j < paths.size(); j++) {
                myTexture enemyTexture = enemies.get(i).getTexture();
                if (enemyTexture.getTopLeft().getX() == paths.get(j).get(0))
                    if (enemyTexture.getTopLeft().getY() == paths.get(j).get(1)) {
                        switch (paths.get(j).get(2)) {
                            case 3:
                                if (enemies.get(i).getDirection() == 2) enemies.get(i).setDirection(1);
                                else enemies.get(i).setDirection(0);
                                break;
                            case 9:
                                if (enemies.get(i).getDirection() == 2) enemies.get(i).setDirection(3);
                                else enemies.get(i).setDirection(0);
                                break;
                            case 6:
                                if (enemies.get(i).getDirection() == 3) enemies.get(i).setDirection(2);
                                else enemies.get(i).setDirection(1);
                                break;
                            case 12:
                                if (enemies.get(i).getDirection() == 1) enemies.get(i).setDirection(2);
                                else enemies.get(i).setDirection(3);
                                break;
                        }
                    }
            }
    }

    public void upgradeTower(Player player, int selectionX, int selectionY) {
        int towerX = selectionX + 40;
        int towerY = selectionY + 50;
        for (int i = 0; i < towers.size(); i++) {
            Point towerTopLeft = towers.get(i).getTexture().getTopLeft();
            if ((towerX == towerTopLeft.getX()) && (towerY == towerTopLeft.getY())) {
                if (towers.get(i).getLevel() < 4) {
                    towers.get(i).setLevel();
                    return ;
                }
            }
        }
    }

    public void sellTower(Player player, int selectionX, int selectionY) {
        int towerX = selectionX + 40;
        int towerY = selectionY + 50;
        for (int i = 0; i < towers.size(); i++) {
            Point towerTopLeft = towers.get(i).getTexture().getTopLeft();
            if ((towerX == towerTopLeft.getX()) && (towerY == towerTopLeft.getY())) {
                towers.remove(i);
                return ;
            }
        }
    }

    public void render() {
        for (int i = 0 ; i < tileList.size(); i++)
            tileList.get(i).render();
        for (int i = 0 ; i < towers.size(); i++)
            towers.get(i).render();
        spawner.render();
        target.render();
        for (int i = 0; i < enemies.size(); i++)
            enemies.get(i).render();
    }

    public List<GameTile> getTileList() {
        return tileList;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Spawner getSpawner() {
        return spawner;
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
}
