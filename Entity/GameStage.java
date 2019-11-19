package Entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameStage {
    private int initMoney;
    private List<List<Integer>> mapArr;
    private int initDirection = 2;
    private String[] waves;
    private int wavesIndex;

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

    public GameStage(String mapPath, String wavePath) {
        String inputData = readLineByLine(mapPath);
        String waveData = readLineByLine(wavePath);
        waves = waveData.split("\n");
        wavesIndex = 0;
        String[] data = inputData.split("\n");
        this.initMoney = Integer.parseInt(data[0].trim());
        mapArr = new ArrayList<>();
        for (int i = 1; i < data.length; i++) {
            mapArr.add(new ArrayList<Integer>());
            String[] itemList = data[i].split(" ");
            for (int j = 0; j < itemList.length; j++)
                mapArr.get(i - 1).add(Integer.decode(itemList[j].trim()));
        }
    }

    public int getMoney() {
        return initMoney;
    }

    public List<List<Integer>> getMapArr() {
        return mapArr;
    }

    public String[] getWaves() {
        return waves;
    }

    public int getWavesIndex() {
        return wavesIndex;
    }

    public void setWavesIndex(int index) {
        this.wavesIndex = index;
    }

    public void increaseWavesIndex() {
        this.wavesIndex++;
    }

    public int getInitDirection() {
        return initDirection;
    }
}
