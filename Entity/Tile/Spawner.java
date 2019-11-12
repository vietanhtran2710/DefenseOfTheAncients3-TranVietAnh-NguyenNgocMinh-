package Entity.Tile;

import Entity.Enemies.*;
import Entity.GameField;
import Utils.Vertex;
import Utils.myTexture;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class Spawner extends Road{
    private int animationPart;
    private boolean isSpawning;
    private int spawnIndex;
    private int spawnCooldown;
    private List<Enemy> wave;

    public Spawner(int x, int y) {
        super(x, y);
        this.wave = new ArrayList<>();
        this.spawnCooldown = 0; this.spawnIndex = 0;
        this.isSpawning = false;
        this.texture = new myTexture("src/res/GFX/Game/Tilemap/Spawner/EnemyHouse.png", GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
        this.animationPart = 1;
    }

    @Override
    public void render() {
        texture.bind();
        Vertex topLeft = new Vertex((this.animationPart - 1) * 0.25f, 0);
        Vertex topRight = new Vertex(this.animationPart * 0.25f, 0);
        Vertex bottomLeft = new Vertex(this.animationPart * 0.25f, 1);
        Vertex bottomRight = new Vertex((this.animationPart - 1) * 0.25f, 1);
        texture.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
    }

    public int getAnimationPart() {
        return animationPart;
    }

    public void setAnimationPart(int animationPart) {
        this.animationPart = animationPart;
    }

    public void spawnEnemy(GameField field) {
        field.getEnemies().add(this.wave.get(this.spawnIndex));
        this.spawnIndex++;
        this.spawnCooldown = 15;
        if (this.spawnIndex == this.wave.size())
            this.isSpawning = false;
    }

    public void updateAnimation() {
        this.animationPart++;
        if (this.animationPart > 4)
            this.animationPart = 1;
    }

    public void setWave(String waveInfo) {
        this.wave = new ArrayList<>();
        String[] enemyItem = waveInfo.split(" ");
        for (int i = 0; i < enemyItem.length; i++)
            switch (enemyItem[i]) {
                case "0":
                    wave.add(new NormalEnemy(2,
                            this.texture.getTopLeft().getX(),
                            this.texture.getTopLeft().getY())
                    );
                    break;
                case "1":
                    wave.add(new SmallerEnemy(2,
                            this.texture.getTopLeft().getX(),
                            this.texture.getTopLeft().getY())
                    );
                    break;
                case "2":
                    wave.add(new TankerEnemy(2,
                            this.texture.getTopLeft().getX(),
                            this.texture.getTopLeft().getY())
                    );
                    break;
                case "3":
                    wave.add(new BossEnemy(2,
                            this.texture.getTopLeft().getX(),
                            this.texture.getTopLeft().getY())
                    );
                    break;
            }
    }

    public boolean isSpawning() {
        return isSpawning;
    }

    public void setSpawning(boolean spawning) {
        isSpawning = spawning;
    }

    public int getSpawnCooldown() {
        return spawnCooldown;
    }

    public void setSpawnCooldown() {
        this.spawnCooldown--;
    }
}
