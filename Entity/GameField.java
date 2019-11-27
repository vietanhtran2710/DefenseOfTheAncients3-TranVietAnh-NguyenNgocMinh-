package Entity;

import Entity.Enemies.Enemy;
import Entity.Tile.*;
import Entity.Towers.Tower;
import Utils.Point;
import Utils.myTexture;

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
                if (mapInfo.get(i).get(j) == 9) {
                    spawner =  new Spawner(j * 48, i * 48);
                }
                else
                    if (mapInfo.get(i).get(j) == 8) {
                        target = new  Target(j * 48, i * 48);
                    }
                    else {
                        int modeBitMask = 0;
                        for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
                            if ((i + direction[dirIndex][0] >= 0) && (i + direction[dirIndex][0] < mapInfo.size()))
                                if ((j + direction[dirIndex][1] >= 0) &&
                                        (j + direction[dirIndex][1] < mapInfo.get(0).size()))
                                    if (mapInfo.get(i + direction[dirIndex][0]).get(j + direction[dirIndex][1]) != 0)
                                        modeBitMask += 1 << dirIndex;
                        }
                        switch (modeBitMask) {
                            case 3:
                                List<Integer> newItem = new ArrayList<>();
                                newItem.add(j * 48); newItem.add(i * 48); newItem.add(3);
                                path.addItem(newItem);
                                break;
                            case 9:
                                newItem = new ArrayList<>();
                                newItem.add(j * 48); newItem.add(i * 48); newItem.add(9);
                                path.addItem(newItem);
                                break;
                            case 6:
                                newItem = new ArrayList<>();
                                newItem.add(j * 48); newItem.add(i * 48); newItem.add(6);
                                path.addItem(newItem);
                                break;
                            case 12:
                                newItem = new ArrayList<>();
                                newItem.add(j * 48); newItem.add(i * 48); newItem.add(12);
                                path.addItem(newItem);
                                break;
                        }
                        tileList.add(new Road(modeBitMask ,j * 48, i * 48));
                    }
            }
    }

    public void checkEnemyDirection() {
        List<List<Integer>> paths = path.getPath();
        for (Enemy enemy : enemies)
            for (List<Integer> integers : paths) {
                myTexture enemyTexture = enemy.getTexture();
                if (enemyTexture.getTopLeft().getX() == integers.get(0))
                    if (enemyTexture.getTopLeft().getY() == integers.get(1)) {
                        switch (integers.get(2)) {
                            case 3:
                                if (enemy.getDirection() == 2) enemy.setDirection(1);
                                else enemy.setDirection(0);
                                break;
                            case 9:
                                if (enemy.getDirection() == 2) enemy.setDirection(3);
                                else enemy.setDirection(0);
                                break;
                            case 6:
                                if (enemy.getDirection() == 3) enemy.setDirection(2);
                                else enemy.setDirection(1);
                                break;
                            case 12:
                                if (enemy.getDirection() == 1) enemy.setDirection(2);
                                else enemy.setDirection(3);
                                break;
                        }
                    }
            }
    }

    public void upgradeTower(Menu menu, Player player, int selectionX, int selectionY) {
        int towerX = selectionX + 40;
        int towerY = selectionY + 50;
        for (Tower tower : towers) {
            Point towerTopLeft = tower.getTexture().getTopLeft();
            if ((towerX == towerTopLeft.getX()) && (towerY == towerTopLeft.getY())) {
                int upgradePrice = menu.getUpgradePrice(tower);
                if ((tower.getLevel() < 5) && (player.getCash() >= upgradePrice)) {
                    tower.setLevel();
                    player.payMoney(upgradePrice);
                    return;
                }
            }
        }
    }

    public void sellTower(Menu menu, Player player, int selectionX, int selectionY) {
        int towerX = selectionX + 40;
        int towerY = selectionY + 50;
        for (int i = 0; i < towers.size(); i++) {
            Point towerTopLeft = towers.get(i).getTexture().getTopLeft();
            if ((towerX == towerTopLeft.getX()) && (towerY == towerTopLeft.getY())) {
                player.earnMoney(menu.getSellPrice(towers.get(i)));
                towers.remove(i);
                return ;
            }
        }
    }

    public void render() {
        for (GameTile gameTile : tileList) gameTile.render();
        for (Tower tower : towers) tower.render();
        spawner.render();
        target.render();
        for (Enemy enemy : enemies) enemy.render();
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

    public Target getTarget() {
        return target;
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
}
