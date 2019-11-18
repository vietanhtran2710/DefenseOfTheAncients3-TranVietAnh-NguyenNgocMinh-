package Utils;

import Entity.GameField;
import Entity.GameStage;
import Entity.Player;

import java.io.FileWriter;
import java.io.IOException;

public class SaveLoad {
    public SaveLoad() {

    }

    public void save(GameField field, Player player, GameStage stage) throws IOException {
        FileWriter fw = new FileWriter("save.txt");
        fw.write(player.getCash() + " " + player.getLive() + "\n");
        fw.close();
    }

    public void load() {

    }
}
