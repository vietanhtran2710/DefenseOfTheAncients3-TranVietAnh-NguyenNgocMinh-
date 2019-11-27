# Defense Of The Ancients 3 

## Contributor
Tran Viet Anh
Nguyen Ngoc Minh

## Description
OOP Game Project

## Project structure
```bash
.
├── Entity
│   ├── Enemies
│   │   ├── BossEnemy.java
│   │   ├── Enemy.java
│   │   ├── HealthBar.java
│   │   ├── NormalEnemy.java
│   │   ├── SmallerEnemy.java
│   │   └── TankerEnemy.java
│   ├── GameEntity.java
│   ├── GameField.java
│   ├── GameStage.java
│   ├── Menu.java
│   ├── Path.java
│   ├── Player.java
│   ├── Tile
│   │   ├── GameTile.java
│   │   ├── Mountain.java
│   │   ├── Road.java
│   │   ├── Spawner.java
│   │   └── Target.java
│   └── Towers
│       ├── Bullet.java
│       ├── MachineGunTower.java
│       ├── NormalTower.java
│       ├── SniperTower.java
│       └── Tower.java
├── MainWindow.java
├── mapInfo.txt
├── README.md
├── res
│   ├── GFX
│   │   ├── Font
│   │   │   ├── 0.png
│   │   │   ├── 1.png
│   │   │   ├── 2.png
│   │   │   ├── 3.png
│   │   │   ├── 4.png
│   │   │   ├── 5.png
│   │   │   ├── 6.png
│   │   │   ├── 7.png
│   │   │   ├── 8.png
│   │   │   └── 9.png
│   │   ├── Game
│   │   │   ├── Enemy
│   │   │   │   ├── Boss Enemy
│   │   │   │   │   ├── BossEnemy_Walk.png
│   │   │   │   │   └── BossEnemy_Walk.psd
│   │   │   │   ├── HealthBar
│   │   │   │   │   ├── HealthBar_empty.png
│   │   │   │   │   ├── HealthBar_green.png
│   │   │   │   │   └── HealthBar_red.png
│   │   │   │   ├── Normal Enemy
│   │   │   │   │   ├── NormalEnemy_Walk.png
│   │   │   │   │   └── NormalEnemy_Walk.psd
│   │   │   │   ├── Smaller Enemy
│   │   │   │   │   ├── SmallerEnemy_Walk.png
│   │   │   │   │   └── SmallerEnemy_Walk.psd
│   │   │   │   └── Tanker Enemy
│   │   │   │       ├── TankerEnemy_Walk.png
│   │   │   │       └── TankerEnemy_Walk.psd
│   │   │   ├── Tilemap
│   │   │   │   ├── Ground
│   │   │   │   │   ├── Background.png
│   │   │   │   │   ├── Background.psd
│   │   │   │   │   ├── lose.png
│   │   │   │   │   └── win.png
│   │   │   │   ├── Road
│   │   │   │   │   ├── mountain.png
│   │   │   │   │   ├── Road_doc.png
│   │   │   │   │   ├── Road_ngang.png
│   │   │   │   │   ├── Road.psd
│   │   │   │   │   ├── Road_turn12.png
│   │   │   │   │   ├── Road_turn14.png
│   │   │   │   │   ├── Road_turn23.png
│   │   │   │   │   └── Road_turn34.png
│   │   │   │   ├── Spawner
│   │   │   │   │   ├── EnemyHouse.png
│   │   │   │   │   └── EnemyHouse.psd
│   │   │   │   └── Target
│   │   │   │       ├── Target.png
│   │   │   │       └── Target.psd
│   │   │   └── Tower
│   │   │       ├── BuyNUpgrade.png
│   │   │       ├── Machine Gun Tower
│   │   │       │   ├── MachineGunBullet.png
│   │   │       │   ├── MachineGunTower.png
│   │   │       │   ├── MachineGunTower.psd
│   │   │       │   └── MachineGunTower_transparent.png
│   │   │       ├── Normal Tower
│   │   │       │   ├── NormalBullet.png
│   │   │       │   ├── NormalTower.png
│   │   │       │   ├── Normal Tower.psd
│   │   │       │   └── NormalTower_transparent.png
│   │   │       └── Sniper Tower
│   │   │           ├── SniperBullet.png
│   │   │           ├── SniperTower.png
│   │   │           ├── SniperTower.psd
│   │   │           └── SniperTower_transparent.png
│   │   └── GUI
│   │       ├── Background
│   │       │   └── Background_main_screen.jpg
│   │       └── Button
│   │           ├── button.png
│   │           ├── button-pressed.png
│   │           ├── button-selected.png
│   │           ├── LoadButton.png
│   │           ├── LoadButton_selected.png
│   │           ├── SaveButton.png
│   │           ├── SaveButton_selected.png
│   │           ├── Sound_off.png
│   │           └── Sound_on.png
│   └── SFX
│       ├── Death.ogg
│       ├── Underground_Battle.ogg
│       ├── Underground_Prep.ogg
│       └── Victory_Theme.ogg
├── Screen
│   ├── GameScreen.java
│   ├── GUI.java
│   ├── Screen.java
│   └── ShowScreen.java
├── Utils
│   ├── CharacterWidth.java
│   ├── Music.java
│   ├── myCharacter.java
│   ├── myText.java
│   ├── myTexture.java
│   ├── Point.java
│   ├── SaveLoad.java
│   ├── Sound.java
│   ├── Timer.java
│   └── Vertex.java
└── waveInfo.txt

29 directories, 104 files
```
