package Entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameStage {
    private int initMoney;
    private List<List<Integer>> mapArr;

    private static String readLineByLine(String fileName) {
        try {
            String data = "";
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            return data;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GameStage(String mapPath) {
        String inputData = readLineByLine(mapPath);
        String[] data = inputData.split("\n");
        this.initMoney = Integer.parseInt(data[0]);
        mapArr = new ArrayList<>();
        for (int i = 1; i < data.length; i++) {
            mapArr.add(new ArrayList<Integer>());
            String[] itemList = data[i].split(" ");
            for (int j = 0; j < itemList.length; j++)
                mapArr.get(i - 1).add(Integer.decode(itemList[j]));
        }
    }

    public int getMoney() {
        return initMoney;
    }

    public List<List<Integer>> getMapArr() {
        return mapArr;
    }
}
