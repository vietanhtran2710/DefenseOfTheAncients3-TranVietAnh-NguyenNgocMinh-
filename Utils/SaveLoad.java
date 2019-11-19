package Utils;

import Entity.Enemies.*;
import Entity.GameField;
import Entity.GameStage;
import Entity.Player;
import Entity.Towers.MachineGunTower;
import Entity.Towers.NormalTower;
import Entity.Towers.SniperTower;
import Entity.Towers.Tower;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveLoad {
    public SaveLoad() {

    }

    public void save(GameField field, Player player, GameStage stage, boolean gameStarted) throws IOException {
        FileWriter fw = new FileWriter("save.txt");

        //Save player info
        if (gameStarted) fw.write("true\n");
        else fw.write("false\n");
        fw.write(player.getCash() + " " + player.getLive() + "\n");

        //Save towers + bullets info
        List<Tower> towers = field.getTowers();
        fw.write("Tower\n");
        for (int i = 0; i < towers.size(); i++) {
            Tower currentTower = towers.get(i);
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
            fw.write(currentTower.getLevel() + "\n");
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
            else if (currentEnemy instanceof BossEnemy) fw.write("3\n");
            Point enemyPosition = currentEnemy.getCoordinate();
            fw.write(enemyPosition.getX() + " " + enemyPosition.getY() + "\n");
            fw.write(currentEnemy.getCurrentHealth() + " " + "\n");
            fw.write(currentEnemy.getDirection() + "\n");
        }
        fw.write("End Enemy\n");

        //Load waves info
        fw.write("Waves\n");
        fw.write(stage.getWavesIndex() + "\n");
        fw.write(field.getSpawner().getSpawnIndex() + "\n");
        fw.write(field.getSpawner().isSpawning() + "\n");
        fw.write("End wave\n");
        fw.close();
    }

    public String loadData() {
        try {
            String data = "";
            data = new String(Files.readAllBytes(Paths.get("save.txt")));
            return data;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Tower> loadTowers(List<String> data) {
        List<Tower> towers = new ArrayList<>();
        //Type
        //Position
        //Level - Range - Damage;
        int lineIndex = 0;
        while (lineIndex < data.size()) {
            Tower newTower = null;
            String type = data.get(lineIndex).trim(); lineIndex++;
            String[] coordinate = data.get(lineIndex).trim().split(" "); lineIndex++;
            switch (type) {
                case "0":
                    newTower = new NormalTower(Integer.parseInt(coordinate[0].trim()), Integer.parseInt(coordinate[1].trim()));
                    break;
                case "1":
                    newTower = new SniperTower(Integer.parseInt(coordinate[0].trim()), Integer.parseInt(coordinate[1].trim()));
                    break;
                case "2":
                    newTower = new MachineGunTower(Integer.parseInt(coordinate[0].trim()), Integer.parseInt(coordinate[1].trim()));
            }
            String level = data.get(lineIndex); lineIndex++;
            newTower.setLevel(Integer.parseInt(level.trim()));
            towers.add(newTower);
        }
        return towers;
    }

    public List<Enemy> loadEnemies(List<String> data) {
        List<Enemy> enemies = new ArrayList<>();
        //Type
        //Position
        //Current health
        int lineIndex = 0;
        while (lineIndex < data.size()) {
            Enemy newEnemy = null;
            String type = data.get(lineIndex).trim();
            lineIndex++;
            String[] coordinate = data.get(lineIndex).split(" ");
            lineIndex++;
            String currentHealth = data.get(lineIndex);
            lineIndex++;
            String initDirection = data.get(lineIndex).trim(); lineIndex++;
            int dir = Integer.parseInt(initDirection.trim());
            int x = Integer.parseInt(coordinate[0].trim());
            int y = Integer.parseInt(coordinate[1].trim());
            switch (type) {
                case "0":
                    newEnemy = new NormalEnemy(dir, x, y);
                    break;
                case "1":
                    newEnemy = new SmallerEnemy(dir, x, y);
                    break;
                case "2":
                    newEnemy = new TankerEnemy(dir, x, y);
                    break;
                case "3":
                    newEnemy = new BossEnemy(dir, x, y);
                    break;
            }
            newEnemy.setCurrentHealth(Integer.parseInt(currentHealth.trim()));
            enemies.add(newEnemy);
        }
        return enemies;
    }

    public Player loadPlayer(String data) {
        String[] playerInfo = data.split(" ");
        Player player = new Player(Integer.parseInt(playerInfo[0].trim()), Integer.parseInt(playerInfo[1].trim()));
        return player;
    }
}
