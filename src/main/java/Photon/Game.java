package Photon;

import Photon.DataBase.ListWorker;
import Photon.DataBase.ListWorker2;
import Photon.DataBase.User;
import Photon.Enums.DrawFigure;
import Photon.Enums.Music;
import Photon.Exceptions.PlayerDoNotExist;
import Photon.GameObjects.Bonus.GOPrism;
import Photon.GameObjects.Enemy.GOBlackHole;
import Photon.GameObjects.Enemy.GOObstacle;
import Photon.GameObjects.GOPhoton.GOPhotonFon;
import Photon.GameObjects.GOPhoton.GOPlayer;
import Photon.GameObjects.GOPhoton.GOPoint;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Serega on 03.03.2015.
 */
public class Game implements IGame{


    public static GameConfiguration gameConfiguration = new GameConfiguration();
    public static GameConfiguration gameConfigurationNew;

    public GOPlayer player;
    public static GOPlayer player2;
    public static GOBlackHole blackHole = new GOBlackHole();
    public static ArrayList<GOPlayer> players = new ArrayList<GOPlayer>();
    public static ArrayList<GOPhotonFon> fon = new ArrayList<GOPhotonFon>();
    public static ArrayList<GO> allObjects = new ArrayList<GO>();
    public static ArrayList<GOObstacle> obstacles = new ArrayList<GOObstacle>();
    public static ArrayList<GOPrism> bonuses = new ArrayList<GOPrism>();
//    public static GOPrism prism;
    public static int controlMode = 1;
    public static float freakChanger = Main.game.gameConfiguration.freakChanger;

    public static float distance = 0;

    public static StringBuffer playerName = new StringBuffer();
    public static int lenght = 0;
    public static int playerNamelenght=0;

    public static float time = 0;
    public static int integerTime= 0;
    public static int oldTime= 0;
    public static float timeCfCreationObstacle = 0;
    public static float timeCfCreationBonus = 0;

//    public static float moveOnStep = 20f/ Main.em;

    public static boolean second = false;


    public static float defMoveOnStep = gameConfiguration.defMoveOnStep;
    public static float moveOnStep;
    public static boolean somethingWasChanged = false;
    public static int level;
    public static boolean nowNewSecond = false;
   // public static boolean mute = gameConfiguration.mute;
    public static boolean mouseGrabbed = true;
    public static boolean pause = false;
    private static float timeToPrism;
    private static float timeToObst;
    private static boolean restartGame = false;
    private static long lastFrame = 0;

    private boolean isEnableCheats = true;
    private int toEnableCheats[] = new int[]{5, 5, 6, 7, 2, 5};
    private int toEnableCheatN = 0;

    public static int pg= 1;
    public static int maxPg;
    public static int maxElems;


    public static int gameStatus = -1;
    public static boolean changedGameStatus;

    public static int shadowing;
    public static int selected;
    public static long lastKeyDownTime;

    public static boolean changedSettings;
    public static boolean isBt;

    public static BufferedReader br;

    public Game() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gameConfiguration.isBot = Main.defBotAmount;
        gameConfiguration.playersAmount = Main.defPlayersAmount;
        gameConfiguration.mute = Main.defMute;
        System.out.println("DEFMUTE"+Main.defMute);
        if (gameConfiguration.mute) {
            Draw.musicStop(Music.FON1);
        }else{
            if (!Draw.musicIsPlaying(Music.FON1))
            Draw.musicPlay(Music.FON1);
        }

        maxPg = (ListWorker.getList(0,100).size())/10;

        if ((ListWorker.getList(0,100).size() - ((ListWorker.getList(0,100).size())/10) > 0))
            ++maxPg;

       // System.out.println(maxPg+"maxPg");
        maxElems = ListWorker.getList(0,100).size();
       // System.out.println(maxElems+"maxElems");
        clear();
        controlMode = 2;

        try {
//            GOPlayer massOfPlayers[] = new GOPlayer()[];
            GOPlayer massOfPlayers[] = new GOPlayer[gameConfiguration.playersAmount + gameConfiguration.isBot];
//            for(int i = 0; i < GameConfiguration.playersAmount; i++) {
//                massOfPlayers[i] = player = new GOPlayer(50 + 10*i, 50*Main.ratio, 2, DrawFigure.CIRCLE, User.defaultName, 2 + i, false);
//            }
            if((gameConfiguration.playersAmount + gameConfiguration.isBot )== 1) {
                massOfPlayers[0] =
                        (player = GOPlayer.newBuilder()
                                        .setXStart(GOPlayer.beginX)
                                        .setYStart(50 * Main.ratio)
                                        .setSize(2)
                                        .setFigure(DrawFigure.CIRCLE)
                                        .setName(User.defaultName)
                                        .setColor(2)
                                        .isBot(false)
                                        .build()
                        );
              //  System.out.println("A: "  + player.x);
            }
            if(((gameConfiguration.playersAmount) + (gameConfiguration.isBot)) == 2) {
               // System.out.println("TWO");
                massOfPlayers[0] =
                        (player = GOPlayer.newBuilder()
                                .setXStart(45)
                                .setYStart(40 * Main.ratio)
                                .setSize(2)
                                .setFigure(DrawFigure.CIRCLE)
                                .setName(User.defaultName)
                                .setColor(2)
                                .isBot(false)
                                .build()
                        );
                if (gameConfiguration.isBot == 1) {
                    //System.out.println("true");
                    isBt = true;
                }
                else{
                    //System.out.println("false");
                    isBt = false;
                }
                massOfPlayers[1] =
                        (player2 = GOPlayer.newBuilder()
                                .setXStart(GOPlayer.beginX)
                                .setYStart(60 * Main.ratio)
                                .setSize(2)
                                .setFigure(DrawFigure.CIRCLE)
                                .setName(User.defaultName + "_2")
                                .setColor(4)
                                .isBot(isBt)
                                .build()
                        );
            }

            if(gameConfiguration.playersAmount >= 3) {
                //System.out.println(">3");
                massOfPlayers[0] =
                        (player = GOPlayer.newBuilder()
                                .setXStart(GOPlayer.beginX)
                                .setYStart(35 * Main.ratio)
                                .setSize(2)
                                .setFigure(DrawFigure.CIRCLE)
                                .setName(User.defaultName)
                                .setColor(2)
                                .isBot(false)
                                .build()
                        );
                massOfPlayers[1] =
                        (player = GOPlayer.newBuilder()
                                .setXStart(GOPlayer.beginX)
                                .setYStart(50 * Main.ratio)
                                .setSize(2)
                                .setFigure(DrawFigure.CIRCLE)
                                .setName(User.defaultName + "_2")
                                .setColor(4)
                                .isBot(false)
                                .build()
                        );
                massOfPlayers[2] =
                        (player = GOPlayer.newBuilder()
                                .setXStart(GOPlayer.beginX)
                                .setYStart(65 * Main.ratio)
                                .setSize(2)
                                .setFigure(DrawFigure.CIRCLE)
                                .setName(User.defaultName + "_3")
                                .setColor(3)
                                .isBot(true)
                                .build()
                        );
            }
            repairMassOfPlayers(massOfPlayers);
            addAllPlayers(massOfPlayers);
        } catch(PlayerDoNotExist myE) {
            System.err.print(myE);
        }
        allObjects.add(blackHole);
        if(!Draw.musicIsPlaying(Music.FON1) && !gameConfiguration.mute) {
            Draw.musicPlay(Music.FON1);
        }
//        prism = new GOPrism();
    }

    public void restartGame() {

    }

    public void clear() {
//        Thread thread = new Thread(
//                new Runnable() {
//                    public void run() {
//                        while(true) {
//                            Draw.writeFramesPerSecond((int)(1000f/ (new Date().getTime()-lastFrame)));
//                            lastFrame = new Date().getTime();
////                            System.out.println("a");
//                        }
//                    }
//                }
//        );
//        thread.start();
        players.clear();
        allObjects.clear();
        obstacles.clear();
        bonuses.clear();
        fon.clear();
//        gameConfiguration.defaultGravitationPower = 0;
//        gameConfiguration.gravitationParameter = gameConfiguration.defaultGravitationPower;
       // System.out.println(Main.defPlayersAmount + Main.defBotAmount+"HEHERHERERE"+ (gameConfiguration.playersAmount + gameConfiguration.isBot));
            for(int i = 0; i < (gameConfiguration.playersAmount + gameConfiguration.isBot); i++) {
                gameConfiguration.gravitationParameter[i] = gameConfiguration.defaultGravitationPower;
            }
        level = 1;
        time = 0;
        integerTime = 0;
        timeCfCreationObstacle = 0;
        timeCfCreationBonus = 0;
        moveOnStep = defMoveOnStep;
        restartGame = false;
//        if(Draw.musicIsPlaying(Music.FON1))
//            Draw.musicStop(Music.FON1);

    }



    public void getInput() {
if (gameStatus != -1) {
    float b = (float) (15f / Main.fps);
    switch (controlMode) {
        case 3:
            if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !player.isBot) {
                if (player.y <= 100 * Main.ratio - player.amplitude * 2 - player.sy)
                    player.playerYShift += b;
            }
        case 1:
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !player.isBot) {
                if (player.freak >= player.minFreak)
                    player.freak -= freakChanger;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !player.isBot) {
                if (player.freak <= player.maxFreak)
                    player.freak += freakChanger;
            }
            if (player2 != null) {
                if (Keyboard.isKeyDown(Keyboard.KEY_A) && !player2.isBot) {
                    if (player2.freak >= player.minFreak)
                        player2.freak -= freakChanger;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_D) && !player2.isBot) {
                    if (player2.freak <= player.maxFreak)
                        player2.freak += freakChanger;
                }
            }
            break;
        case 2:


            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !player.isBot) {
                if (player.freak <= player.maxFreak)
                    player.freak += freakChanger;
            }


            if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
                ListWorker.getList();
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
                gameConfiguration.mute = true;
                if (Draw.musicIsPlaying(Music.FON1))
                    Draw.musicStop(Music.FON1);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
                gameConfiguration.mute = false;
                if (!Draw.musicIsPlaying(Music.FON1))
                    Draw.musicPlay(Music.FON1);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_0)) {
                mouseGrabbed = true;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_9)) {
                mouseGrabbed = false;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
                pause = true;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
                pause = false;
            }
            if (player2 != null) {
                if (Keyboard.isKeyDown(Keyboard.KEY_Q) && !player2.isBot) {
                    if (player2.freak <= player2.maxFreak)
                        player2.freak += freakChanger;
                }
            }
            break;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
        player.immortalityDie = 99999999;
        player.die = false;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
        player.immortalityDie = 0;
        player.die = false;
    }
    /*if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
        if (gameStatus == 9)
            restartGame = true;
        gameStatus = 3;
        changedGameStatus = true;
        return;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
        if (gameStatus == 9)
            restartGame = true;
        gameStatus = 1;
        changedGameStatus = true;
        return;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
        if (gameStatus == 9)
            restartGame = true;
        gameStatus = 2;
        changedGameStatus = true;

        return;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_0)) {
        if (gameStatus == 9)
            restartGame = true;
        gameStatus = 0;
        changedGameStatus = true;
        return;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
        if (gameStatus == 9)
            restartGame = true;
        gameStatus = 4;
        changedGameStatus = true;
        return;
    } */
}
            lastKeyDownTime++;

            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                if (lastKeyDownTime > 10) {
                    lastKeyDownTime = 0;
                    changedGameStatus = true;
                    if (gameStatus == 9) {
                        restartGame = true;
                        gameStatus = 0;
                        changedGameStatus = true;
                    } else {
                        if (gameStatus == 2) {
                            gameStatus = 1;
                        } else {
                            if (gameStatus == 1) {
                                gameStatus = 2;
                            } else {
                                if (gameStatus == 0) {
                                    changedGameStatus = false;
                                } else {
                                    gameStatus = 0;
                                }
                            }
                        }

                    }
                }
            }

            if (Mouse.isButtonDown(0)) {
                if (lastKeyDownTime > 10) {
                    lastKeyDownTime = 0;
                    switch (checkButtonIsOver()) {
                        case 1:
                            switch (gameStatus) {
                                case (-1):
                                    gameStatus = 0;

                                    break;

                                case 0:
                                    gameStatus = 2;
                                    changedGameStatus = true;
                                    Main.restartGame();

                                    break;
                                case 1:
                                    gameStatus = 2;
                                    changedGameStatus = true;
                                    break;
                                case 3:
                                    if (pg > 1) {
                                        pg--;
                                    }
                                    //  getPreviousTopList();
                                    break;
                                case 4:
                                    setMultiplayer();
                                    break;
                                case 9:
                                    saveScore();
                                    gameStatus = 10;
                                    changedGameStatus = true;
                                    break;

                                case 10:
                                    gameStatus = 2;

                                    Main.restartGame();
                                    break;
                            }

                            break;
                        case 2:
                            switch (gameStatus) {
                                case 0:
                                    gameStatus = 3; //TOP SCORES
                                    changedGameStatus = true;
                                    break;
                                case 1:
                                    gameStatus = 2;

                                    Main.restartGame();
                                    break;
                                case 3:
                                    if (pg < maxPg) {
                                        pg++;
                                    }
                                    //  getNextTopList();
                                    break;
                                case 4:

                                    setBot();
                                    break;
                                case 9:
                                    gameStatus = 10;

                                    break;

                                case 10:
                                    gameStatus = 0;

                                    changedGameStatus = true;
                                    break;
                            }
                            changedGameStatus = true;
                            break;
                        case 3:
                            switch (gameStatus) {
                                case 0:
                                    gameStatus = 4; //Settings
                                    createNewConfiguration();
                                    changedGameStatus = true;
                                    break;
                                case 1:
                                    gameStatus = 0;
                                    changedGameStatus = true;
                                    break;
                                case 3:
                                    gameStatus = 0;
                                    changedGameStatus = true;
                                    break;
                                case 4:

                                     setAudio();
                                    break;
                            }

                            break;
                        case 4:
                            switch (gameStatus) {
                                case 0:
                                    exit();
                                    break;
                                case 4:

                                    //   setPreviousMusicGenre();
                                    break;
                            }
                            break;
                        case 5:
                            if (gameStatus == 4)

                                //  setNextMusicGenre();
                                break;
                        case 6:
                            if (gameStatus == 4)
                                gameStatus = 0;
                            changedGameStatus = true;
                            if (changedSettings == true) {
                                changedSettings = false;
                                saveConfiguration();
                                gameConfigurationNew = null;
                            }
                            break;
                        case 7:
                            if (gameStatus == 4)
                                gameStatus = 0;
                            changedGameStatus = true;
                            if (changedSettings == true) {
                                changedSettings = false;
                                gameConfigurationNew = null;
                            }

                            break;


                        default:
/*
                            if (gameStatus == 9) {
                                restartGame = true;
                                gameStatus = 2;
                                //changedGameStatus = true;

                            }*/
                            break;
                    }
                }
            }


        if ((gameStatus == -1)||(gameStatus ==9)) {

            while (Keyboard.next()) {

                //System.out.println(playerName);



                    char x = Keyboard.getEventCharacter();

                    //if ((int)(x) == 42) {
                   //     continue;
                  //  }

                if((int)(x) == 0)
                    continue;

                if((int)(x)==32)
                    continue;

                    if ((int) (x) == 13) {
                        gameStatus = 0;
                        break;
                    }

                if ((int)(x) == 8) {
                    if (playerNamelenght>0) {
                        playerName.deleteCharAt(playerNamelenght - 1);
                        playerNamelenght--;
                    }
                    continue;
                }

                    //playerName = Keyboard.getKeyName(x);
                    //playerNameChars[playerNamelenght] = Keyboard.get
                if (playerNamelenght < 15) {
                    playerName.append(x);
                    playerNamelenght++;
                }

                    //System.out.println(playerName);




            }
        }

        }


    public void getInputCheats() {
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !player.isBot) {
            player.x += -1;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !player.isBot) {
            player.x += 1;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP) && !player.isBot) {
            player.collisionWithPrism();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !player.isBot) {
            player.collisionWithObstacle();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            level++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            level--;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
            player.immortal = true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
            player.immortal = false;
        }
    }





    public void update() {
        //System.out.println(gameConfiguration.mute);
        if (gameStatus == -1) {
 return;
        }
        if ((gameStatus == 1)||(gameStatus == 9)) {
            mouseGrabbed = false;
            Main.mouseGrabbed(mouseGrabbed);
            //gameConfiguration.update();
        }

        if (gameStatus == 2) {

            Main.mouseGrabbed(mouseGrabbed);
            if (pause) {
                mouseGrabbed = false;
                return;
            } else {
                mouseGrabbed = true;
            }
            Main.mouseGrabbed(mouseGrabbed);
            gameConfiguration.update();
            script();
//        prism.update();
            if (blackHole != null)
                blackHole.update();

            for (GO ob : allObjects) {
                ob.update();
            }
            for (GOPlayer obPlayer : players) {
                blackHole.setGravitationPower(obPlayer);
                obPlayer.update();
                if (restartGame)
                    break;
                else
                    for (GOPoint point : obPlayer.path) {
                        point.update();
                    }
//            obPlayer.danger = false;
            }
            if (restartGame) {
                Main.restartGame();
                return;
            }
            do {
                somethingWasChanged = false;
                for (GO p : obstacles) {
                    p.update();
                    if (somethingWasChanged) break;
                }
                for (GOPrism bonus : bonuses) {
                    bonus.update();
                    if (somethingWasChanged) break;
                }
            } while (somethingWasChanged);

        }
    }

    public void script() {
        time+=Main.delay/1000f;
        integerTime = (int)time;
        if(oldTime == integerTime) {
            nowNewSecond = false;
//            return;
        }
        else {
            nowNewSecond = true;
            oldTime = integerTime;
        }

        if(integerTime%6 == 0 && nowNewSecond) {
            level++;
        }
        moveOnStep = gameConfiguration.moveOnStep;
        freakChanger = gameConfiguration.freakChanger;
        timeToPrism = gameConfiguration.timeToPrism;
        timeToObst = gameConfiguration.timeToObst;
        if(time - timeCfCreationBonus >= timeToPrism && (Main.game.gameConfiguration.playersAmount + Main.game.gameConfiguration.isBot <= 1)) {
            if(!obstacles.isEmpty()) {
                bonuses.add(new GOPrism());
                while(Physics.checkCollisions(obstacles.get(obstacles.size() - 1), bonuses.get(bonuses.size() - 1))) {
                    bonuses.remove(bonuses.size() - 1);
                    bonuses.add(new GOPrism());
                }
            }
            else
                bonuses.add(new GOPrism());
            timeCfCreationBonus = time;
        }

        if(level > 7) {
            if (time - timeCfCreationObstacle >= timeToObst) {
                obstacles.add(new GOObstacle());
                timeCfCreationObstacle = time;
            }
        }
        else {
            if(time - timeCfCreationObstacle >= timeToObst && time - timeCfCreationBonus > 0.2f) {
                obstacles.add(new GOObstacle());
                timeCfCreationObstacle = time;
            }
        }
    }

    public void render() {
        switch (gameStatus) {
            case -1:


                break;
            case 1:
            case 2:
            case 9:
//        distance += 0.2;
        if(Main.game.player.funX >= GOPlayer.beginX && (gameConfiguration.playersAmount+ gameConfiguration.isBot  < 2)) {
            Draw.xshift = (float) Math.pow(Main.game.player.funX - GOPlayer.beginX, 1f);
        }
        else
            Draw.xshift = 0;
        if(distance*moveOnStep >= 20) distance = 0;
        Draw.draw(DrawFigure.FON2, 0, 20 * Main.ratio, 150, 60 * Main.ratio);

        for(GO ob : allObjects) {
            ob.render();
        }
        for(GOPlayer obPlayer : players) {
            for(GOPoint point : obPlayer.path) {
                point.render();
            }
            obPlayer.render();
        }
        for(GO ob : obstacles) {
            ob.render();
        }
        for(GOPrism bonus : bonuses) {
            bonus.render();
        }
        Draw.gameInterface();
                if ((gameStatus == 1)||(gameStatus == 9)) {
                    if (changedGameStatus)
                       Draw.wallpaper = Draw.shadowTexture;
                    changedGameStatus = false;
                } else {

                    return;
                }
                break;
            case 3:
                changedGameStatus = false;
                break;
            case 4:
                if (changedGameStatus)
                    Draw.wallpaper = loadTexture("fon/textureWithShadow");
                changedGameStatus = false;

                break;
            case 0:

                if (changedGameStatus) {
                Draw.wallpaper = Draw.wallTexture;
                changedGameStatus = false;

                }
                break;

            case 10:

                if (changedGameStatus) {
                    Draw.wallpaper = Draw.wallTexture;
                    changedGameStatus = false;

                }
                break;

        }
        Draw.drawWallpaper(Draw.wallpaper);
        selected = checkButtonIsOver();

        Draw.drawContextMenu(gameStatus,selected);

        if (gameStatus == 3)
            Draw.drawTopScoreList(pg,maxPg);

        //Draw.drawCoords();
        if (gameStatus == -1) {
            //Draw.drawGradient(Draw.gradient, shadowing);
            //System.out.println(shadowing);

        }
        else {
            Draw.drawFPS();
        }
    }
    public void delObj(GO removeOb) {
        obstacles.remove(removeOb);
    }







    public static void addAllPlayers(GOPlayer massOfPlayers[]) throws PlayerDoNotExist{

        for(int i = 0; i < massOfPlayers.length; i++) {
            try {
                players.add(massOfPlayers[i]);
                massOfPlayers[i].iAdded();
                // попытка обычным способом добавить игрока.
            } catch(NullPointerException e) {
                // В случае ошибки. Этот метод попытается воссоздать массив игроков.
                repairMassOfPlayers(massOfPlayers);
                try {
                    // метод справился со своей задачей.
                    players.add(massOfPlayers[i]);
                    massOfPlayers[i].iAdded();
                } catch (NullPointerException unrealToRepair) {
                    // если же метод все таки не смог понять какого именно игрока не хватает.
                    throw new PlayerDoNotExist("ERROR! Player do not exist! Player #" + (i + 1) + " = NULL. ");
                }
            }
        }
    }

    public static void repairMassOfPlayers(GOPlayer massOfPlayers[]) throws PlayerDoNotExist{
        if(massOfPlayers.length < gameConfiguration.playersAmount + gameConfiguration.isBot) {
            throw new PlayerDoNotExist("Some Players do not created!");
            // сработает в любом случае?
        }
        for(int i = 0; i < gameConfiguration.playersAmount + gameConfiguration.isBot; i++) {
            if(massOfPlayers[i] == null) {
                massOfPlayers[i] = addPlayer(massOfPlayers, i);
            }
        }
    }
    private static GOPlayer addPlayer(final GOPlayer massOfPlayers[], final int amountCorrectlyCreatedPlayers) {
        // Проверка. Какого вида игрока еще не создано (бот\не бот) и СОЗДАНИЕ НЕДОСТОЮЩЕГО ИГРОКА.
        // Для этого нужно перебрать массив massOfPlayers[] до игрока номер - amountCorrectlyCreatedPlayers
        if(false/*Условия из-за которых нельзя воссоздать игрока.*/) return null;
        return GOPlayer.newBuilder()
                .setXStart(GOPlayer.beginX)
                .setYStart(65 * Main.ratio)
                .setSize(2)
                .setFigure(DrawFigure.CIRCLE)
                .setName(User.defaultName + "_3")
                .setColor(3)
                .isBot(true)
                .build()
        ;
    }








    public static void gameOver() {

        gameStatus = 9;
        while (Keyboard.next()) {
        }

        changedGameStatus = true;



        }



    public static void exit() {

        Main.stopGame = true;
    }


    public int checkButtonIsOver()
    {
        int x = Mouse.getX();
        int y = Mouse.getY();

        switch(gameStatus)
        {

            case -1:
                if ((x>720)&&(x<880)&&(y<Main.dHeight - 480)&&(y>Main.dHeight-530))
                    return 1;

                break;
            case 0:
                if ((y<Main.dHeight - 185)&&(Main.dHeight - 235< y)) {
                    if ((x > 100) && (x < 300))
                        return 1;
                    if ((x>420)&&(x<620))
                        return 2;
                    if ((x>740)&&(x<940))
                        return 3;
                    if ((x>1060)&&(x<1250))
                        return 4;
                }
                break;
            case 1:
                if ((x>555)&&(x<856)) {
                    if ((y > Main.dHeight - 330)&&( y< Main.dHeight - 300))
                        return 1;
                    if ((y > Main.dHeight - 360)&&( y< Main.dHeight - 335))
                        return 2;
                    if ((y > Main.dHeight - 405)&&( y< Main.dHeight - 375))
                        return 3;

                }
                break;
            case 2:
                break;
            case 3:
                if ((y<Main.dHeight -285)&&(y> Main.dHeight - 525)) {
                    if (((x>80)&&(x<320))&&(y< Main.dHeight - 450 + 0.5*x)&&(y> -0.5*x - 350 + Main.dHeight))
                        return 1;
                    if ((x>990)&&(x<1230)&&(y<Main.dHeight - 0.5*x + 210)&&(y>Main.dHeight + 0.5*x - 1020))
                        return 2;
                }
                if ((x>190)&&(x<320)&&(y>Main.dHeight- 230)&&(y<Main.dHeight-180))
                    return 3;

                break;
            case 4:
                if ((x>300)&&(x<440)) {
                    if ((y > Main.dHeight - 250) && (y < Main.dHeight - 210))
                        return 6;
                    if ((y > Main.dHeight - 295) && (y < Main.dHeight - 255))
                        return 7;
                }

                if((x>460)&&(x<900)) {
                    if ((y > Main.dHeight - 322) && (y < Main.dHeight - 290))
                        return 1;
                    if ((y > Main.dHeight - 356) && (y < Main.dHeight - 323))
                        return 2;
                    if ((y>Main.dHeight - 421)&&(y<Main.dHeight - 388))
                        return 3;
                }



                break;
            case 9:

                if ((y>Main.dHeight - 550)&&(y<Main.dHeight-450)) {
                    if((x>500)&&(x<600))
                        return 1;
                    if((x>720)&&(x<800))
                        return 2;
                }
                break;
            case 10:
                if ((x>555)&&(x<856)) {

                    if ((y > Main.dHeight - 360)&&( y< Main.dHeight - 335))
                        return 1;
                    if ((y > Main.dHeight - 405)&&( y< Main.dHeight - 375))
                        return 2;

                }
                break;
        }
        return 0;
    }



    public static void setMultiplayer() {
        if (changedSettings == false) {
            changedSettings = true;
        }

        if (gameConfigurationNew.playersAmount == 1) {
            gameConfigurationNew.playersAmount = 2;
            if (gameConfigurationNew.playersAmount + gameConfigurationNew.isBot == 3)
                gameConfigurationNew.isBot = 0;
        } else
            gameConfigurationNew.playersAmount = 1;


    }

    public static Texture loadTexture(String name) {
        try {
            return TextureLoader.getTexture("png", new FileInputStream(new File("content/images/" + name + ".png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setBot() {
        if (changedSettings == false) {
            changedSettings = true;

        }
        if (gameConfigurationNew.isBot == 0) {
            gameConfigurationNew.isBot = 1;
            if (gameConfigurationNew.isBot + gameConfigurationNew.playersAmount == 3)
                gameConfigurationNew.playersAmount = 1;
        }
        else
            gameConfigurationNew.isBot= 0;

    }

    public static void createNewConfiguration() {
        gameConfigurationNew = new GameConfiguration();
        gameConfigurationNew.isBot = gameConfiguration.isBot;
        gameConfigurationNew.playersAmount = gameConfiguration.playersAmount;
        gameConfigurationNew.mute = gameConfiguration.mute;
    }

    public static void saveConfiguration() {
        gameConfiguration.playersAmount = gameConfigurationNew.playersAmount;
        gameConfiguration.isBot = gameConfigurationNew.isBot;
        gameConfiguration.mute = gameConfigurationNew.mute;
        Main.defBotAmount = gameConfiguration.isBot;
        Main.defPlayersAmount = gameConfiguration.playersAmount;
        Main.defMute = gameConfiguration.mute;

        //System.out.println("maindefbot="+Main.defBotAmount+"maindefplayers=="+Main.defPlayersAmount);
        Main.restartGame(true);

    }

    public static void setAudio() {
        if (changedSettings == false) {
            changedSettings = true;
        }

        if (gameConfigurationNew.mute == true) {
            gameConfigurationNew.mute = false;
        }
        else
        {
            gameConfigurationNew.mute = true;
        }

    }

    public static void saveScore() {
        if ((gameConfiguration.playersAmount + gameConfiguration.isBot) == 1) {
            for (GOPlayer player : players) {
                User user = new User(playerName.toString(), (int) player.score, new Time((integerTime / 3600), (integerTime / 60) % 60, integerTime % 60), (new java.sql.Date(new Date().getTime())));
//                ListWorker.DBWorker.insert(user);
                new ListWorker2().add(user);
                System.out.println("OK");
            }
        }

    }
}
