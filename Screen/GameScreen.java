package Screen;

import Entity.*;
import Entity.Enemies.BossEnemy;
import Entity.Enemies.Enemy;
<<<<<<< HEAD
=======
import Entity.Enemies.NormalEnemy;
>>>>>>> 11980fe4d18fd81c7a05c34d7a1696f0b5dc42cc
import Entity.Menu;
import Entity.Tile.GameTile;
import java.util.List;
import java.util.ArrayList;

import Entity.Tile.Spawner;
import Entity.Towers.MachineGunTower;
import Entity.Towers.NormalTower;
import Entity.Towers.SniperTower;
import Entity.Towers.Tower;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.openvr.Texture;
import org.lwjgl.system.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.*;
import Utils.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GameScreen extends Screen{
    private long window;
    private myTexture background;
    private myTexture upgradeAndSell;
    private GameStage gameStage;
    private GameField field;
    private Menu menu;
    private int isBuyingTower, isSelectingTower;
    private int selectionX, selectionY;
    private Player player;
    private boolean isMouseDown = false;
    private int tick, rate = 5;

    private final double FPS = 20.0;

    public void initLoop () {
        this.isBuyingTower = 0; this.isSelectingTower = 0;
        this.tick = 0;
        String backgroundImageSource = "src/res/GFX/Game/Tilemap/Ground/Background.png";
        this.background = new Utils.myTexture(backgroundImageSource, GL_QUADS);
        this.gameStage = new GameStage("src/mapInfo.txt");
        this.player = new Player(gameStage.getMoney());
        this.field = new GameField(gameStage);
        this.menu = new Menu();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        upgradeAndSell = new myTexture("src/res/GFX/Game/Tower/BuyNUpgrade.png", GL_QUADS);

        // Test Enemy
        this.field.addEnemy(new BossEnemy(48, 48));

    }

    public void loop(long window) {
        this.window = window;
        glClearColor( 0.0f, 0.0f, 0.0f, 0.0f);

        // Init attributes before loop
        initLoop();

        // Limit frame per second
        double frame_cap = 1.0 / FPS;
        double time = Timer.getTime();
        double unprocessed = 0;

        double frame_time = 0;
        double frames = 0;

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(this.window)) {
            // limit frame
            boolean can_render = false;
            double time_2 = Timer.getTime();
            double timePassed = time_2 - time;
            unprocessed += timePassed;
            frame_time += timePassed;
            time = time_2;

            while (unprocessed >= frame_cap) {
                unprocessed -= frame_cap;
                can_render = true;
                if(frame_time >= 1.0) {
                    frame_time = 0;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }

            if (can_render) {
                render();
                frames++;
            }
        }
    }

    public void placeTower(int tower, double x, double y) {
        boolean validPosition = true;
        List<GameTile> roads = field.getTileList();
        for (int i = 0; i < roads.size(); i++) {
            if (checkMouseHover(roads.get(i).getTexture(), this.window)) {
                validPosition = false; break;
            }
        }
        System.out.println(validPosition);
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
            isBuyingTower = 0;
        }
    }

    public void mouseClickHandle() {
        double cursorX = getCursorPosX(this.window);
        double cursorY = getCursorPosY(this.window);

        if ((1193 <= cursorX) && (cursorX <= 1193 + 48 * 2))
            if ((657 <= cursorY) && (cursorY <= 657 + 48 * 2)) {
                Spawner spawner = field.getSpawner();
                myTexture spawnerTexture = spawner.getTexture();
                spawner.spawnEnemy(field, new BossEnemy(
                        gameStage.getInitDirection(),
                        spawnerTexture.getTopLeft().getX(),
                        spawnerTexture.getTopLeft().getY())
                );
            }

        if (checkMouseHover(menu.getSoundButton(), this.window)) {
            menu.setMuted(!menu.isMuted());
            if (menu.isMuted())
                menu.getSoundButton().changeImage("src/res/GFX/GUI/Button/Sound_off.png");
            else
                menu.getSoundButton().changeImage("src/res/GFX/GUI/Button/Sound_on.png");
            return ;
        }

        System.out.println(cursorX + " " + cursorY);

        if ((0 <= cursorX) && (cursorX <= 348) && (624 <= cursorY) && (cursorY <= 768)) {
            List<myTexture> tiles = menu.getButtonList();
            for (int i = 0; i < tiles.size(); i++)
                if (checkMouseHover(tiles.get(i), this.window))
                    if (player.getCash() >= menu.getPriceList().get(i)) {
                        System.out.println("Clicked on item");
                        this.isBuyingTower = i + 1;
                        this.isSelectingTower = 0;
                        return ;
                    }
        }

        if (isBuyingTower != 0) {
            if ((0 <= cursorX) && (cursorX <= 1366))
                if ((0 <= cursorY) && (cursorY <= 624)) {
                    this.isSelectingTower = 0;
                    placeTower(isBuyingTower, cursorX, cursorY);
                    System.out.println("Clicked on map");
                }
        }
        else
            {
                List<Tower> towers = field.getTowers();
                boolean towerClicked = false;
                for (int i = 0; i < towers.size(); i++) {
                    if (checkMouseHover(towers.get(i).getTexture(), this.window)) {
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
        Spawner spawner = field.getSpawner();
        spawner.updateAnimation();

        List<Enemy> enemies = field.getEnemies();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).updateAnimation();
            enemies.get(i).move();
        }
        field.checkEnemyDirection();
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        tick++;
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

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }
}