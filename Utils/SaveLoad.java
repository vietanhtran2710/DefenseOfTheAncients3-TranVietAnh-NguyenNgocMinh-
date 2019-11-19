package Utils;

import Entity.Enemies.Enemy;
import Entity.Enemies.NormalEnemy;
import Entity.Enemies.SmallerEnemy;
import Entity.Enemies.TankerEnemy;
import Entity.GameField;
import Entity.GameStage;
import Entity.Player;
import Entity.Towers.NormalTower;
import Entity.Towers.SniperTower;
import Entity.Towers.Tower;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveLoad {
    public SaveLoad() {

    }

    public void save(GameField field, Player player, GameStage stage) throws IOException {
        FileWriter fw = new FileWriter("save.txt");

        //Save player info
        fw.write(player.getCash() + " " + player.getLive() + "\n");

        //Save towers + bullets info
        List<Tower> towers = field.getTowers();
        for (int i = 0; i < towers.size(); i++) {
            Tower currentTower = towers.get(i);
            fw.write("Tower\n");
            if (currentTower instanceof NormalTower) {
                fw.write("0\n");
            }
            else if (currentTower instanceof SniperTower) {
                fw.write("1\n");
            }
            else {
                fw.write("2\n");
            }
            Point towerPosition = currentTower.getCoordinate();
            fw.write(towerPosition.getX() + " " + towerPosition.getY() + "\n");
            fw.write(currentTower.getLevel() + " " + currentTower.getRange() + " " + currentTower.getDamage() + "\n");
        }
        fw.write("End Tower\n");

        //Save enemies info
        fw.write("Enemy\n");
        List<Enemy> enemies = field.getEnemies();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy currentEnemy = enemies.get(i);
            if (currentEnemy instanceof NormalEnemy) {
                fw.write("0\n");
            }
            else if (currentEnemy instanceof SmallerEnemy) {
                fw.write("1\n");
            }
            else if (currentEnemy instanceof TankerEnemy){
                fw.write("2\n");
            }
            else fw.write("3\n");
            Point enemyPosition = currentEnemy.getCoordinate();
            fw.write(enemyPosition.getX() + " " + enemyPosition.getY() + "\n");
            fw.write(currentEnemy.getCurrentHealth() + " " + "\n");
        }
        fw.write("End Enemy\n");

        //Load waves info
        fw.write("Waves\n");


        fw.write("End wave\n");

        fw.close();
    }

//    public List<Tower> loadTowers(List<String> data) {
//
//    }
//
//    public List<Enemy> loadEnemies(List<String> data) {
//
//    }
//
//    public Player loadPlayer(List<String> data) {
//
//    }
}
