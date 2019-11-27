package Screen;

import Entity.*;
import Entity.Enemies.Enemy;
import Entity.Enemies.HealthBar;
import Entity.Menu;
import Entity.Tile.GameTile;
import Entity.Tile.Spawner;
import Entity.Towers.*;

import Utils.Point;
import Utils.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class GameScreen extends Screen{
    private long window;
    private myTexture background;
    private myTexture upgradeAndSell;
    private myTexture SaveButton;
    private myTexture winPanel = new myTexture("src/res/GFX/Game/Tilemap/Ground/win.png", GL_QUADS, 269, 127);
    private myTexture losePanel = new myTexture("src/res/GFX/Game/Tilemap/Ground/lose.png", GL_QUADS, 269, 127);
    private GameStage gameStage;
    private GameField field;
    private Menu menu;
    private int isBuyingTower, isSelectingTower;
    private int selectionX, selectionY;
    private Player player;
    private boolean isMouseDown = false, gameStarted = false;
    private boolean onMouseSaveHover = false;
    private int finished = 0;
    private int tick;
    public boolean load;

    private HealthBar liveTarget;

    private Music backgroundMusic;
    private Music loseMusic;
    private Music winMusic;

    public GameScreen(boolean load) {
        this.load = load;
    }

    public void initLoop () {
        this.isBuyingTower = 0; this.isSelectingTower = 0;
        this.tick = 0;
        String backgroundImageSource = "src/res/GFX/Game/Tilemap/Ground/Background.png";
        this.background = new Utils.myTexture(backgroundImageSource, GL_QUADS);
        this.gameStage = new GameStage("src/mapInfo.txt", "src/waveInfo.txt");
        this.player = new Player(gameStage.getMoney());
        this.field = new GameField(gameStage);
        this.menu = new Menu();

        this.upgradeAndSell = new myTexture("src/res/GFX/Game/Tower/BuyNUpgrade.png", GL_QUADS);
        this.SaveButton = new myTexture(
                "src/res/GFX/GUI/Button/SaveButton.png", GL_QUADS, 1200, 15
        );
        SaveButton.setDisplayWidth(160);
        SaveButton.setDisplayHeight(55);

        this.backgroundMusic = new Music("src/res/SFX/Underground_Battle.ogg");
        this.loseMusic = new Music("src/res/SFX/Death.ogg");
        this.winMusic = new Music("src/res/SFX/Victory_Theme.ogg");

        this.player = new Player(30);
        this.liveTarget = new HealthBar("green");

        new CharacterWidth();

        if (this.load) loadGame();

        this.field.getSpawner().setWave(gameStage.getWaves()[gameStage.getWavesIndex()]);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void loadGame() {
        SaveLoad loadFunction = new SaveLoad();
        String data = loadFunction.loadData();
        String[] lines = data.split("\n");
        gameStarted = lines[0].trim().equals("true");
        this.player = loadFunction.loadPlayer(lines[1]);
        boolean updateTowerInfo = false, updateEnemyInfo = false, updateWaveInfo = false;
        List<String> towerInfo = new ArrayList<>();
        List<String> enemyInfo = new ArrayList<>();
        List<String> waveInfo = new ArrayList<>();
        for (String line : lines) {
            switch (line) {
                case "Tower":
                    updateTowerInfo = true;
                    break;
                case "End Tower":
                    updateTowerInfo = false;
                    break;
                case "Enemy":
                    updateEnemyInfo = true;
                    break;
                case "End Enemy":
                    updateEnemyInfo = false;
                    break;
                case "Waves":
                    updateWaveInfo = true;
                    break;
                case "End wave":
                    updateWaveInfo = false;
                    break;
                default:
                    if (updateEnemyInfo) enemyInfo.add(line);
                    else if (updateTowerInfo) towerInfo.add(line);
                    else if (updateWaveInfo) waveInfo.add(line);
                    break;
            }
        }
        field.setTowers(loadFunction.loadTowers(towerInfo));
        field.setEnemies(loadFunction.loadEnemies(enemyInfo));

        //Load wave info
        gameStage.setWavesIndex(Integer.parseInt(waveInfo.get(0).trim()));
        field.getSpawner().setSpawnIndex(Integer.parseInt(waveInfo.get(1).trim()));
        if (waveInfo.get(2).equals("true")) field.getSpawner().setSpawning(true);
        else field.getSpawner().setSpawning(false);
    }

    public void saveGame() throws IOException {
        SaveLoad saveFunction = new SaveLoad();
        saveFunction.save(field, player, gameStage, gameStarted);
    }

    public void loop(long window) throws Exception {
        this.window = window;
        glClearColor( 0.0f, 0.0f, 0.0f, 0.0f);

        initLoop();
        while ( !glfwWindowShouldClose(this.window)) {
            render();
        }
        this.backgroundMusic.delete();
        this.winMusic.delete();
        this.loseMusic.delete();
    }

    public void gameWon() {
        this.backgroundMusic.delete();
        this.finished = 1;
        this.isSelectingTower = 0;
        this.isBuyingTower = 0;
        this.winMusic.playFor(56, false);
    }

    public void gameLost() {
        this.backgroundMusic.delete();
        this.finished = 2;
        this.isSelectingTower = 0;
        this.isBuyingTower = 0;
        this.loseMusic.playFor(2, false);
    }

    public boolean placeTower(int tower, double x, double y) {
        boolean validPosition = true;
        List<GameTile> roads = field.getTileList();
        for (GameTile road : roads) {
            if (checkMouseHover(road.getTexture(), this.window)) {
                validPosition = false;
                break;
            }
        }
        List<Tower> towers = field.getTowers();
        for (Tower value : towers) {
            if (checkMouseHover(value.getTexture(), this.window)) {
                validPosition = false;
                break;
            }
        }
        if (validPosition) {
            int posX = (int) (Math.round(x) / 48 * 48);
            int posY = (int) (Math.round(y) / 48 * 48);
            Tower newTower = null;
            switch (tower) {
                case 1:
                    newTower = new NormalTower(posX, posY);
                    break;
                case 2:
                    newTower = new SniperTower(posX, posY);
                    break;
                case 3:
                    newTower = new MachineGunTower(posX, posY);
                    break;
            }
            field.addTower(newTower);
        }
        return validPosition;
    }

    public void mouseClickHandle() {
        double cursorX = getCursorPosX(this.window);
        double cursorY = getCursorPosY(this.window);

        //Upgrade tower
        if (isSelectingTower != 0) {
            if ((selectionX + 40 <= cursorX) && (cursorX <= selectionX + 80))
                if ((selectionY + 6 <= cursorY) && (cursorY <= selectionY + 40)) {
                    field.upgradeTower(menu, player, selectionX, selectionY);
                    isSelectingTower = 0;
                    return;
                }

            //Sell tower
            if ((selectionX + 50 <= cursorX) && (cursorX <= selectionX + 70))
                if ((selectionY + 100 <= cursorY) && (cursorY <= selectionY + 120)) {
                    field.sellTower(menu, player, selectionX, selectionY);
                    isSelectingTower = 0;
                    return;
                }
        }

        if ((1193 <= cursorX) && (cursorX <= 1193 + 48 * 2))
            if ((657 <= cursorY) && (cursorY <= 657 + 48 * 2)) {
                if (!this.gameStarted) {
                    Spawner spawner = field.getSpawner();
                    this.gameStarted = true;
                    spawner.setSpawning(true);
                }
            }

        if (checkMouseHover(menu.getSoundButton(), this.window)) {
            menu.setMuted(!menu.isMuted());
            if (menu.isMuted())
                menu.getSoundButton().changeImage("src/res/GFX/GUI/Button/Sound_off.png");
            else
                menu.getSoundButton().changeImage("src/res/GFX/GUI/Button/Sound_on.png");
            return ;
        }

        //1: Normal tower
        //2: Sniper tower
        //3: Machine gun tower
        if ((0 <= cursorX) && (cursorX <= 348) && (624 <= cursorY) && (cursorY <= 768)) {
            List<myTexture> tiles = menu.getButtonList();
            for (int i = 0; i < tiles.size(); i++)
                if (checkMouseHover(tiles.get(i), this.window))
                    if (player.getCash() >= menu.getPriceList().get(i)) {
                        this.isBuyingTower = i + 1;
                        this.isSelectingTower = 0;
                        return ;
                    }
        }

        if (isBuyingTower != 0) {
            if ((0 <= cursorX) && (cursorX <= 1366))
                if ((0 <= cursorY) && (cursorY <= 624)) {
                    this.isSelectingTower = 0;
                    boolean placeResult = placeTower(isBuyingTower, cursorX, cursorY);
                    if (placeResult) {
                        player.payMoney(menu.getPriceList().get(isBuyingTower - 1));
                        isBuyingTower = 0;
                    }
                }
        }
        else
            {
                List<Tower> towers = field.getTowers();
                boolean towerClicked = false;
                for (Tower tower : towers) {
                    if (checkMouseHover(tower.getTexture(), this.window)) {
                        towerClicked = true;
                        this.isSelectingTower = 1;
                        this.selectionX = (int) (Math.round(cursorX) / 48 * 48) - 40;
                        this.selectionY = (int) (Math.round(cursorY) / 48 * 48) - 50;
                        break;
                    }
                }
                if ((!towerClicked) && (isSelectingTower != 0))
                    isSelectingTower = 0;
            }
    }

    public void renderUpgradeAndSell() {
        upgradeAndSell.bind();
        upgradeAndSell.displayByOtherCoordinate(selectionX, selectionY);
    }

    public void updateDisplay() {
        //Spawner animation
        Spawner spawner = field.getSpawner();
        spawner.updateAnimation();

        if (spawner.isSpawning())
            if (spawner.getSpawnCooldown() == 0) {
                spawner.spawnEnemy(field);
            }
            else {
                spawner.setSpawnCooldown();
            }

        List<Tower> towers = field.getTowers();

        //Set up new wave
        List<Enemy> enemies = field.getEnemies();
        if ((enemies.size() == 0) && (!spawner.isSpawning()) && (gameStarted)) {
            gameStarted = false;
            gameStage.increaseWavesIndex();
            System.out.println("Index: " + gameStage.getWavesIndex());
            if ((gameStage.getWavesIndex() == gameStage.getWaves().length) && (player.getLive() > 0)) {
                gameWon(); return;
            }
            else spawner.setWave(gameStage.getWaves()[gameStage.getWavesIndex()]);
        }

        //Bullets hitting
        for (Tower currentTower : towers) {
            List<Bullet> bullets = currentTower.getBulletList();
            for (int j = 0; j < bullets.size(); j++) {
                Bullet currentBullet = bullets.get(j);
                if (currentBullet.isHit()) {
                    currentBullet.hit();
                    if (currentBullet.getTarget().getCurrentHealth() <= 0)
                        currentTower.setTarget(null);
                    bullets.remove(j);
                    j--;
                }
            }
        }

        //Remove bullets whose target is already killed
        for (Tower currentTower : towers) {
            List<Bullet> bullets = currentTower.getBulletList();
            for (int j = 0; j < bullets.size(); j++) {
                Bullet currentBullet = bullets.get(j);
                if (currentBullet.getTarget().getCurrentHealth() <= 0) {
                    bullets.remove(j);
                    j--;
                }
            }
        }

        //Move enemies and update new direction on the road
        for (Enemy value : enemies) {
            value.updateAnimation();
            value.move();
        }
        field.checkEnemyDirection();


        //Update target for towers
        for (Tower currentTower : towers) {
            if (currentTower.getShootCooldown() != 0)
                currentTower.decreaseCooldown();
            Point towerPosition = currentTower.getCoordinate();
            Enemy currentTarget = currentTower.getTarget();
            if (currentTower.getTarget() != null) {
                Point enemyPosition = currentTarget.getCoordinate();
                if (towerPosition.distanceTo(enemyPosition) > currentTower.getRange())
                    currentTower.setTarget(null);
            }
            if (currentTower.getTarget() == null) {
                List<Enemy> enemyList = field.getEnemies();
                for (Enemy enemy : enemyList) {
                    currentTarget = enemy;
                    Point enemyPosition = currentTarget.getCoordinate();
                    if (towerPosition.distanceTo(enemyPosition) <= currentTower.getRange())
                        currentTower.setTarget(currentTarget);
                }
            }
        }

        //Towers shooting
        for (Tower currentTower : towers) {
            //currentTower.setTarget(new BossEnemy(0, dummyX, dummyY));
            if ((currentTower.getShootCooldown() == 0) && (currentTower.getTarget() != null)) {
                currentTower.shoot();
                currentTower.setCooldown();
            }
        }

        //Bullets moving
        for (Tower currentTower : towers) {
            List<Bullet> bullets = currentTower.getBulletList();
            for (Bullet bullet : bullets) bullet.move();
        }

        //Killing enemies
        for (int i = 0; i < enemies.size(); i++)
            if (enemies.get(i).getCurrentHealth() <= 0) {
                player.earnMoney(enemies.get(i).getBounty());
                for (Tower tower : towers)
                    if (tower.getTarget() == enemies.get(i))
                        tower.setTarget(null);
                enemies.remove(i);
                i--;
            }

        //Enemies hit damage to target
        for (int i = 0; i < enemies.size(); i++)
            if (enemies.get(i).getCoordinate().equals(field.getTarget().getCoordinate())) {
                for (Tower tower : towers)
                    if (tower.getTarget() == enemies.get(i))
                        tower.setTarget(null);
                player.takeDamage(enemies.get(i).getDamage());
                enemies.remove(i); i--;
            }

        if (player.getLive() <= 0) gameLost();
    }

    public void render() throws IOException{

        backgroundMusic.playLoop(56, this.menu.isMuted());
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        tick++;
        int rate = 3;
        if (tick == rate) {
            updateDisplay();
            tick = 0;
        }

        background.bind();
        background.displayByVertex(
                new Vertex(-1.0f, 1.0f),
                new Vertex(1.0f, 1.0f),
                new Vertex(1.0f, -1.0f),
                new Vertex(-1.0f, -1.0f)
        );

        field.render();

        menu.render();

        if (isBuyingTower != 0) {
            myTexture towerIcon = null;
            switch (isBuyingTower) {
                case 1:
                    towerIcon = new myTexture(
                            "src/res/GFX/Game/Tower/Normal Tower/NormalTower_transparent.png",
                            GL_QUADS,
                            (int)(getCursorPosX(this.window) - 24),
                            (int)(getCursorPosY(this.window) - 24)
                    );
                    break;
                case 2:
                    towerIcon = new myTexture(
                            "src/res/GFX/Game/Tower/Sniper Tower/SniperTower_transparent.png",
                            GL_QUADS,
                            (int)(getCursorPosX(this.window) - 24),
                            (int)(getCursorPosY(this.window) - 24)
                    );
                    break;
                case 3:
                    towerIcon = new myTexture(
                            "src/res/GFX/Game/Tower/Machine Gun Tower/MachineGunTower_transparent.png",
                            GL_QUADS,
                            (int)(getCursorPosX(this.window) - 24),
                            (int)(getCursorPosY(this.window) - 24)
                    );
                    break;
            }
            assert towerIcon != null;
            towerIcon.display();
        }

        if (finished == 1) {
            winPanel.bind();
            winPanel.display();
        }
        else if (finished == 2) {
            losePanel.bind();
            losePanel.display();
        }

        if (isSelectingTower == 1)
            renderUpgradeAndSell();

        if (glfwGetMouseButton(this.window, GLFW_MOUSE_BUTTON_LEFT) == GLFW_TRUE) {
            if (!isMouseDown) {
                mouseClickHandle();
                isMouseDown = true;
            }
        }
        else {
            isMouseDown = false;
        }

        SaveButton.bind();
        SaveButton.display();

        if (glfwGetMouseButton(this.window, GLFW_MOUSE_BUTTON_LEFT) == GLFW_TRUE) {
            if (onMouseSaveHover) {
                saveGame();
            }
        }

        if (checkMouseHover(SaveButton, this.window)) {
            if (!onMouseSaveHover) {
                SaveButton.changeImage("src/res/GFX/GUI/Button/SaveButton_selected.png");
                onMouseSaveHover = true;
            }
        }
        else
        if (onMouseSaveHover) {
            SaveButton.changeImage("src/res/GFX/GUI/Button/SaveButton.png");
            onMouseSaveHover = false;
        }

        this.player.renderMoney(710, 675);

        this.liveTarget.render(1000, this.player.getLive(), 1296, 517);

        glfwSwapBuffers(window);
        glfwPollEvents();
    }
}
