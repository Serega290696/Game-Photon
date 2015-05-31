package Photon;

import Photon.DataBase.ListWorker;
import Photon.DataBase.User;
import Photon.Enums.DrawFigure;
import Photon.Enums.Music;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;



public class Draw {


    public static int ax = 200;
    public static int bx = 75;
    public static int y;
    public static int x;
    public static int tmp = 0;


    public static Texture okgo;
    public static Texture okgoover;
    public static Texture enterName;
    public static Texture gameOver;
    public static Texture shadow;
    public static Texture on;
    public static Texture off;
    public static Texture number1;
    public static Texture number2;
    public static Texture button0_1;
    public static Texture button0_2;
    public static Texture button0_3;
    public static Texture button0_4;
    public static Texture button1_1;
    public static Texture button1_2;
    public static Texture button1_3;
    public static Texture button3_1;
    public static Texture button3_2;
    public static Texture button3_3;
    public static Texture button4_1;
    public static Texture button4_2;
    public static Texture button4_3;
    public static Texture button4_4;
    public static Texture button4_5;
    public static Texture button4_6;
    public static Texture button4_7;
    public static Texture button0_1over;
    public static Texture button0_2over;
    public static Texture button0_3over;
    public static Texture button0_4over;
    public static Texture button1_1over;
    public static Texture button1_2over;
    public static Texture button1_3over;
    public static Texture button3_1over;
    public static Texture button3_2over;
    public static Texture button3_3over;
    public static Texture button4_1over;
    public static Texture button4_2over;
    public static Texture button4_3over;
    public static Texture button4_4over;
    public static Texture button4_5over;
    public static Texture button4_6over;
    public static Texture button4_7over;
    public static Texture pauseTexture;
    public static Texture shadowTexture;
    public static Texture wallTexture;
    public static Texture settingsTexture;
    public static Texture topscoresTexture;
    public static Texture topNext;
    public static Texture topPrev;
    public static Texture topNextOver;
    public static Texture topPrevOver;
    public static Texture back;
    public static Texture backOver;

    public static int fps,rate;
    public static long delta;
    public static long curTime = new Date().getTime();

    public static Texture wallpaper = null;
    public static Texture gradient = null;
    public static long lastTime= new Date().getTime();

    public static float xshift = 0;
    public static float yshift = 0;
    private static final float Pi = 180f;

    private static boolean antiAlias = true;
    static Font awtFont1 = new Font("Times New Roman", Font.BOLD, 24);
    static Font awtFont2 = new Font("Times New Roman", Font.BOLD, 30);
    static Font dsCrystal = new Font("DS Crystal", Font.BOLD, 76);
    static Font awtFont4 = new Font("Gungsuh",Font.BOLD,45);
    static Font awtFont5 = new Font("Gungsuh",Font.BOLD,30);
    private static TrueTypeFont font1_1 = new TrueTypeFont(awtFont1, antiAlias);
    private static TrueTypeFont font1_2 = new TrueTypeFont(awtFont2, antiAlias);
    private static TrueTypeFont font2 = new TrueTypeFont(awtFont2, antiAlias);
    private static TrueTypeFont font3 = new TrueTypeFont(dsCrystal, antiAlias);
    private static TrueTypeFont font4 = new TrueTypeFont(awtFont4, antiAlias);
    private static TrueTypeFont font5 = new TrueTypeFont(awtFont5, antiAlias);
    private static TrueTypeFont fontForTop;


    private static final String CONTENT_PATH = "content/";
    private static final String TEXTURE_PATH = CONTENT_PATH+"images/";
    private static final String SOUND_PATH = CONTENT_PATH+"music/";
    private static final String FONTS_PATH = CONTENT_PATH+"fonts/";


    public static Texture fon1;
    public static Texture fon3;
    private static Audio fonSound1;
//    public static long curTime;

    public static void draw(DrawFigure figure, float x, float y, float sx, float sy, float rotate, int color, float opacity) {

        glDisable(GL_TEXTURE_2D);
        if(!figure.name().equals("FON2") && !figure.name().equals("CIRCLE"))
            x -= xshift;
        else {
            if(!figure.name().equals("CIRCLE")) {
                if (x - xshift / 3 > -50) {
                    x -= xshift / 3;
                } else
                    x = -50;
            }
        }
        y -= yshift;
        x *= Main.em;
        y *= Main.em;

        sx *= Main.em;
        sy *= Main.em;
        switch(figure) {
            case RECT: rect(x, y, sx, sy, rotate, color, opacity);
                break;
            case TRIANGLE: triangle(x, y, sx, sy, rotate, color, opacity);
                break;
            case CIRCLE: circle(x, y, sx, sy, color, opacity);
                break;
            case FON2:
                bigFon(x, y, sx, sy);
                break;
            default:
                break;
        }
    }



    public static void draw(DrawFigure figure, float x, float y, float sx, float sy) {
        draw(figure, x, y, sx, sy, 0, 0, 1);
    }
    public static void rect(float x, float y, float sx, float sy, float rotate, float opacity) {
        rect(x, y, sx, sy, rotate, 0);
    }
    public static void rect(float x, float y, float sx, float sy, float rotate, int color, float opacity) {
        glPushMatrix();
        {
            chooseColor(color, opacity);
//            glColor3f(0.5f,0.5f,0.5f);
//            glColor4f(0.8f, 0.2f, 0.2f, opacity);
            glTranslatef(x, y, 0);
            glRotatef((float) (-rotate), 0, 0, 1);
            glEnable(GL_POLYGON_SMOOTH);
            glBegin(GL_QUADS);
            {
                glVertex2f(-sx / 2, -sy / 2);
                glVertex2f(-sx/2, sy/2);
                glVertex2f(sx / 2, sy / 2);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }
    public static void triangle(float x, float y, float sx, float sy, float rotate, int color, float opacity) {
        glPushMatrix();
        {

            float side = sx;
            float r1 = 1;
            float g1 = 1;
            float b1 = 1;
            chooseColor(color, opacity);
//            glColor3f(0.5f,0.5f,0.5f);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);
            glEnable(GL_POLYGON_SMOOTH);
            glBegin(GL_TRIANGLES);
            {
                glVertex2f(-sx / 2, -sy / 2);
                glVertex2f(-sx / 2, sy / 2);
                glVertex2f((float) ((Math.pow(3, 0.5f) - 1) / 2 * sx), 0);
            }
            glEnd();
        }
        glPopMatrix();
    }

    public static void peramida(float x, float y, float sx, float sy, float rotate, int color, float opacity) {
        glPushMatrix();
        {
            float side = sx;
            float r1 = 1;
            float g1 = 1;
            float b1 = 1;
            chooseColor(color, opacity);
//            glColor3f(0.5f,0.5f,0.5f);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 1, 1);
            glEnable(GL_POLYGON_SMOOTH);
            glBegin(GL_TRIANGLES);
            {
                glBegin(GL_TRIANGLES);

                glColor3d(r1, g1, b1);
                glVertex3d(side, -side, -side);
                glColor3d(r1, g1, b1);
                glVertex3d(side, -side, side);
                glColor3d(r1/2, g1/2, b1/2);
                glVertex3d(0.0, side, 0.0);
                glEnd();

                glBegin(GL_TRIANGLES);
                glColor3d(1.0, 0.84, 0.0);  // Сделали боковую сторону желтой

                glColor3d(r1, g1, b1);
                glVertex3d(side, -side, side);
                glColor3d(r1, 0, 0);
                glVertex3d(-side, -side,side);
                glVertex3d(0.0, side, 0.0);
                glEnd();

                glBegin(GL_TRIANGLES);
                glColor3d(0.94, 0.5, 0.5);// Сделали сторону  розовой

                glColor3d(r1, g1, b1);
                glVertex3d(-side, -side, side);
                glColor3d(0, 0, 1);
                glVertex3d(-side, -side, -side);
                glVertex3d(0.0, side, 0.0);
                glEnd();

                glBegin(GL_TRIANGLES);
                glColor3d(0.0, 1.0, 0.0);  // Сделали сторону  светло зеленой

                glColor3d(r1, g1, b1);
                glVertex3d(-side, -side, -side);
                glColor3d(0, g1, 1);
                glVertex3d(side, -side, -side);
                glVertex3d(0.0, side, 0.0);
                glEnd();

                glBegin(GL_QUADS);// основание пирамиды
                glColor3d(1.0, 0.51, 0.28); // сделали основание рыжим

                glColor3d(r1, g1, b1);
                glVertex3d(side, -side, side);
                glColor3d(r1, g1, 0);
                glVertex3d(-side, -side, side);
                glVertex3d(-side, -side, -side);
                glColor3d(r1, g1, b1);
                glVertex3d(side, -side, -side);
                glEnd();
//                glVertex2f(-sx / 2, -sy / 2);
//                glVertex2f(-sx/2, sy/2);
//                glVertex2f((float) ((Math.pow(3, 0.5f)-1)/2*sx), 0);
            }
            glEnd();
        }
        glPopMatrix();
    }
    public static void fon(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
//            glEnable(GL_TEXTURE_2D);
            glDisable(GL_TEXTURE_2D);
            glColor3f(0.215f, 0.155f, 0.430f);//55, 43, 109
            glTranslatef(x, y, 0);
            sx*=0.9;
            glRotatef(-rotate, 0, 0, 1);
            glDisable(GL_POLYGON_SMOOTH);
            glBegin(GL_QUADS);
            {
                glVertex2f(-sx / 2, -sy / 2);
                glVertex2f(-sx / 2, sy / 2);
                glVertex2f(sx / 2, sy / 2);
                glVertex2f(sx / 2, -sy / 2);
            }
            glEnd();
        }
        glPopMatrix();
    }
    public static void gameInterface() {
        glEnable(GL_TEXTURE_2D);
        font1_2.drawString((10) * Main.em, 95 * Main.em * Main.ratio,

                        "        Time: " + Game.integerTime );

        for(int i = 0; i < Game.players.size(); i++) {

            font1_1.drawString((10 + (100 / Game.players.size() * i))*Main.em, 10*Main.em*Main.ratio,
                    "Score: "
                            + String.valueOf(Math.round(Game.players.get(i).score)), org.newdawn.slick.Color.white);
            }
    }
    /*public static void fon2(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glColor3f(0.5f, 0.5f, 0.5f);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);
            fon1.bind();
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx / 2, -sy / 2);
                glTexCoord2f(0, fon1.getHeight());
                glVertex2f(-sx / 2, sy / 2);
                glTexCoord2f(fon1.getWidth(), fon1.getHeight());
                glVertex2f(sx / 2, sy / 2);
                glTexCoord2f(fon1.getWidth(), 0);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }*/
    public static void bigFon(float x, float y, float sx, float sy) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
//            glBindTexture(GL_TEXTURE_2D, 0);
            glColor3f(1.0f, 1.0f, 1.0f);
            glColor3f(0.4f, 0.4f, 0.8f);
            glTranslatef(x, y, 0);
//            sx*=0.9;
            glRotatef(-0, 0, 0, 1);
            wallTexture.bind();
            glDisable(GL_POLYGON_SMOOTH);
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(0, 0);
                glTexCoord2f(0, wallTexture.getHeight());
                glVertex2f(0, sy);
                glTexCoord2f(wallTexture.getWidth(), wallTexture.getHeight());
                glVertex2f(sx, sy);
                glTexCoord2f(wallTexture.getWidth(), 0);
                glVertex2f(sx,0);
            }
            glEnd();
        }
        glPopMatrix();
//        fon1.release();
    }


    public static void circle(float x, float y, float sx, float sy, int color, float opacity) {
        glPushMatrix();
        {
            chooseColor(color, opacity);
            glTranslatef(x, y, 0);
            if(sx < 200) {
                glEnable(GL_POINT_SMOOTH);
                glPointSize(sx);
                glBegin(GL_POINTS);
                    glVertex2d(0, 0); // первая точка
                glEnd();
            }
            else {
//            glHint( GL_LINE_SMOOTH_HINT, GL_NICEST );
//            glEnable( GL_LINE_SMOOTH );
//            glEnable( GL_BLEND );
//            glBlendFunc( GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA );
//            glEnable(GL_POLYGON_SMOOTH);
                glBegin(GL_POLYGON);
                {
                    int amountPoints = (int) (20 * sx);
                    for (int i = 0; i <= amountPoints; i++) {
                        glVertex2d(sx / 2 * Math.cos((float) i / amountPoints * 2 * Math.PI), sy / 2 * Math.sin((float) i / amountPoints * 2 * Math.PI));
                    }
                }
                glEnd();
            }
        }
        glPopMatrix();
    }

    public static void chooseColor(int color, float opacity) {
        switch(color) {
            case 0:
                glColor4f(0.8f, 0.8f, 0.8f, opacity);
                break;
            case 1:
                glColor4f(1.0f, 0.0f, 0.0f, opacity);
                glColor4f(0.733f, 0.223f, 0.168f, opacity);
                break;
            case 2:
                glColor4f(0.478f, 0.737f, 0.345f, opacity);
                break;
            case 3:
                glColor4f(0.247f, 0.494f, 1.0f, opacity);
                break;
            case 4:
//                glColor4f(1.0f, 0.0f, 0.0f, opacity);
                glColor4f(0.9f, 0.2f, 0.2f, opacity);
                break;
            case 5:
                glColor4f(0.0f, 1.0f, 0.0f, opacity);
                break;
            case 6:
                glColor4f(0f, 0.0f, 1.0f, opacity);
                break;
            case 7:
                glColor4f(0f, 0.0f, 0.0f, opacity);
                break;
        }
    }
    public static void writeFramesPerSecond(int framesPerSecond) {
        font1_2.drawString(2, 95*Main.em*Main.ratio,
                String.valueOf(framesPerSecond),
                org.newdawn.slick.Color.white);
        font1_2.drawString(0, 0,
                String.valueOf(framesPerSecond),
                org.newdawn.slick.Color.white);
        font1_2.drawString(55, 55,
                String.valueOf(framesPerSecond),
                org.newdawn.slick.Color.white);
        font1_2.drawString(22, 22,
                String.valueOf(framesPerSecond),
                org.newdawn.slick.Color.white);
    }
    public static void musicPlay(Music music) {
        switch(music) {
            case FON1:
                fonSound1.playAsSoundEffect(1.0f, 1.0f, false);
//                fonSound1.playAsSoundEffect();
                break;
        }
    }
    public static boolean musicIsPlaying(Music music) {
        switch(music) {
            case FON1:
                return fonSound1.isPlaying();
        }
        return false;
    }
    public static void musicStop(Music music) {
        switch(music) {
            case FON1:
                fonSound1.stop();

                break;
        }
    }

    public static void initMusic() {
        try {
            fonSound1 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(SOUND_PATH + "1.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawWallpaper(Texture wallpaper) {
        wallpaper.bind();
        glPushMatrix();
        {
            glTranslatef(0, 0, 0);

            glBegin(GL_QUADS);
            {
                glTexCoord2f(0,0);
                glVertex2f(0,0);
                glTexCoord2f(0,1);
                glVertex2f(0,Main.dHeight);
                glTexCoord2f(1,1);
                glVertex2f(Main.dWidth,Main.dHeight);
                glTexCoord2f(1,0);
                glVertex2f(Main.dWidth,0);
            }
            glEnd();
        }
        glPopMatrix();

    }


    public static void drawGradient(Texture wallpaper, int shadowing) {
        wallpaper.bind();
        glPushMatrix();
        {
            glTranslatef(0,0,0);
            glColor4f(1,1,1,0.5f - ((float)(shadowing))/200);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE);
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0,0);
                glVertex2f(0,0);
                glTexCoord2f(0,1);
                glVertex2f(0,Main.dHeight);
                glTexCoord2f(1,1);
                glVertex2f(Main.dWidth,Main.dHeight);
                glTexCoord2f(1,0);
                glVertex2f(Main.dWidth,0);
            }
            glEnd();
        }
        glPopMatrix();

    }

    public static void drawTexture(Texture image, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4)
    {
        glEnable(GL_TEXTURE_2D);
        image.bind();
        glPushMatrix();
        {
            glTranslatef(0, 0, 0);
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0,0);
                glVertex2f(x1,y1);
                glTexCoord2f(0,1);
                glVertex2f(x2,y2);
                glTexCoord2f(1,1);
                glVertex2f(x3,y3);
                glTexCoord2f(1,0);
                glVertex2f(x4,y4);
            }
            glEnd();
        }
        glPopMatrix();
    }


    public static void drawFPS() {
        ++rate;
        curTime = new Date().getTime();

        font1_1.drawString(30, 30, String.valueOf(fps));
        delta = curTime - lastTime;
        if ((delta)>1000) {
            lastTime = curTime;
            fps=rate;
            rate=0;
            delta = 0;
        }
    }

    public static void drawContextMenu(int gameStatus, int selected) {
        glDisable(GL_POLYGON_SMOOTH);
        switch(gameStatus) {

            case -1:
                switch (selected) {


                    case 1:
                        drawTexture(button0_1, 100, 200, 100, 250, 400, 250, 400, 200);
                        drawTexture(button0_2, 420, 200, 420, 250, 720, 250, 720, 200);
                        drawTexture(button0_3, 740, 200, 740, 250, 1040, 250, 1040, 200);
                        drawTexture(button0_4, 1060, 200, 1060, 250, 1350, 250, 1350, 200);
                        drawTexture(shadow, 0, 0, 0, Main.dHeight, Main.dWidth, Main.dHeight, Main.dWidth, 0);
                        drawTexture(enterName, 400, 250, 400, 550, 900, 550, 900, 250);
                        drawTexture(okgoover, 720, 480, 720, 530, 880, 530, 880, 480);
                        font4.drawString(500, 270, "Greetings!");
                        font5.drawString(535, 360, "Enter name:");
                        font5.drawString(630 - 9 * (Game.playerNamelenght), 400, Game.playerName.toString());
                        break;


                    default:
                        drawTexture(button0_1, 100, 200, 100, 250, 400, 250, 400, 200);
                        drawTexture(button0_2, 420, 200, 420, 250, 720, 250, 720, 200);
                        drawTexture(button0_3, 740, 200, 740, 250, 1040, 250, 1040, 200);
                        drawTexture(button0_4, 1060, 200, 1060, 250, 1350, 250, 1350, 200);
                        drawTexture(shadow, 0, 0, 0, Main.dHeight, Main.dWidth, Main.dHeight, Main.dWidth, 0);
                        drawTexture(enterName, 400, 250, 400, 550, 900, 550, 900, 250);
                        drawTexture(okgo, 720, 480, 720, 530, 880, 530, 880, 480);
                        font4.drawString(500, 270, "Greetings!");
                        font5.drawString(535, 360, "Enter name:");
                        font5.drawString(630 - 9 * (Game.playerNamelenght), 400, Game.playerName.toString());


                        break;

                }

                break;

            case 9:
                //drawTexture(shadowTexture,0,0,0,Main.dHeight,Main.dWidth,Main.dHeight,Main.dWidth,0);
                switch (selected) {

                    case 1:
                        drawTexture(gameOver, 400, 250, 400, 550, 900, 550, 900, 250);
                        font5.drawString(685 - 9 * (Game.playerNamelenght), 380, Game.playerName.toString());
                        font4.drawString(500,450,"YES",Color.cyan);
                        font4.drawString(720,450,"NO");

                        break;
                    case 2:
                        drawTexture(gameOver, 400, 250, 400, 550, 900, 550, 900, 250);
                        font5.drawString(685 - 9 * (Game.playerNamelenght), 380, Game.playerName.toString());
                        font4.drawString(500,450,"YES");
                        font4.drawString(720,450,"NO",Color.cyan);
                        break;

                    default:
                        drawTexture(gameOver, 400, 250, 400, 550, 900, 550, 900, 250);
                        font5.drawString(685 - 9 * (Game.playerNamelenght), 380, Game.playerName.toString());
                        font4.drawString(500, 450, "YES");
                        font4.drawString(720,450,"NO");
                        break;

                }

                break;

            case 0:
                switch (selected) {
                    case 1:
                        drawTexture(button0_1over, 100, 200, 100, 250, 400, 250, 400, 200);
                        drawTexture(button0_2, 420, 200, 420, 250, 720, 250, 720, 200);
                        drawTexture(button0_3, 740, 200, 740, 250, 1040, 250, 1040, 200);
                        drawTexture(button0_4, 1060, 200, 1060, 250, 1350, 250, 1350, 200);
                        break;
                    case 2:
                        drawTexture(button0_1, 100, 200, 100, 250, 400, 250, 400, 200);
                        drawTexture(button0_2over, 420, 200, 420, 250, 720, 250, 720, 200);
                        drawTexture(button0_3, 740, 200, 740, 250, 1040, 250, 1040, 200);
                        drawTexture(button0_4, 1060, 200, 1060, 250, 1350, 250, 1350, 200);
                        break;
                    case 3:
                        drawTexture(button0_1, 100, 200, 100, 250, 400, 250, 400, 200);
                        drawTexture(button0_2, 420, 200, 420, 250, 720, 250, 720, 200);
                        drawTexture(button0_3over, 740, 200, 740, 250, 1040, 250, 1040, 200);
                        drawTexture(button0_4, 1060, 200, 1060, 250, 1350, 250, 1350, 200);
                        break;
                    case 4:
                        drawTexture(button0_1, 100, 200, 100, 250, 400, 250, 400, 200);
                        drawTexture(button0_2, 420, 200, 420, 250, 720, 250, 720, 200);
                        drawTexture(button0_3, 740, 200, 740, 250, 1040, 250, 1040, 200);
                        drawTexture(button0_4over, 1060, 200, 1060, 250, 1350, 250, 1350, 200);
                        break;
                    default:
                        drawTexture(button0_1, 100, 200, 100, 250, 400, 250, 400, 200);
                        drawTexture(button0_2, 420, 200, 420, 250, 720, 250, 720, 200);
                        drawTexture(button0_3, 740, 200, 740, 250, 1040, 250, 1040, 200);
                        drawTexture(button0_4, 1060, 200, 1060, 250, 1350, 250, 1350, 200);
                        break;

                }
                break;
            case 1:
                drawTexture(pauseTexture, 450, 200, 450, 750, 1000, 750, 1000, 200);
                switch (selected) {
                    case 1:
                        drawTexture(button1_1over, 555, 300, 555, 350, 855, 350, 855, 300);
                        drawTexture(button1_2, 556, 335, 556, 385, 856, 385, 856, 335);
                        drawTexture(button1_3, 555, 375, 555, 405, 855, 405, 855, 375);
                        break;
                    case 2:
                        drawTexture(button1_1, 555, 300, 555, 350, 855, 350, 855, 300);
                        drawTexture(button1_2over, 556, 335, 556, 385, 856, 385, 856, 335);
                        drawTexture(button1_3, 555, 375, 555, 405, 855, 405, 855, 375);
                        break;
                    case 3:
                        drawTexture(button1_1, 555, 300, 555, 350, 855, 350, 855, 300);
                        drawTexture(button1_2, 556, 335, 556, 385, 856, 385, 856, 335);
                        drawTexture(button1_3over, 555, 375, 555, 405, 855, 405, 855, 375);
                        break;
                    default:
                        drawTexture(button1_1, 555, 300, 555, 350, 855, 350, 855, 300);
                        drawTexture(button1_2, 556, 335, 556, 385, 856, 385, 856, 335);
                        drawTexture(button1_3, 555, 375, 555, 405, 855, 405, 855, 375);
                        break;


                }
                break;
            case 10:

                switch (selected) {
                    case 1:
                        //drawTexture(button0_1, 100, 200, 100, 250, 400, 250, 400, 200);
                       // drawTexture(button0_2, 420, 200, 420, 250, 720, 250, 720, 200);
                        //drawTexture(button0_3, 740, 200, 740, 250, 1040, 250, 1040, 200);
                        //drawTexture(button0_4, 1060, 200, 1060, 250, 1350, 250, 1350, 200);
                        drawTexture(shadow, 0, 0, 0, Main.dHeight, Main.dWidth, Main.dHeight, Main.dWidth, 0);
                        drawTexture(enterName, 400, 250, 400, 550, 900, 550, 900, 250);
                        drawTexture(button1_2over, 556, 335, 556, 385, 856, 385, 856, 335);
                        drawTexture(button1_3, 555, 375, 555, 405, 855, 405, 855, 375);
                        break;
                    case 2:
                       // drawTexture(button0_1, 100, 200, 100, 250, 400, 250, 400, 200);
                       // drawTexture(button0_2, 420, 200, 420, 250, 720, 250, 720, 200);
                       // drawTexture(button0_3, 740, 200, 740, 250, 1040, 250, 1040, 200);
                       // drawTexture(button0_4, 1060, 200, 1060, 250, 1350, 250, 1350, 200);
                        drawTexture(shadow, 0, 0, 0, Main.dHeight, Main.dWidth, Main.dHeight, Main.dWidth, 0);
                        drawTexture(enterName, 400, 250, 400, 550, 900, 550, 900, 250);
                        drawTexture(button1_2, 556, 335, 556, 385, 856, 385, 856, 335);
                        drawTexture(button1_3over, 555, 375, 555, 405, 855, 405, 855, 375);
                        break;
                    default:
                      //  drawTexture(button0_1, 100, 200, 100, 250, 400, 250, 400, 200);
                      //  drawTexture(button0_2, 420, 200, 420, 250, 720, 250, 720, 200);
                      //  drawTexture(button0_3, 740, 200, 740, 250, 1040, 250, 1040, 200);
                        drawTexture(shadow, 0, 0, 0, Main.dHeight, Main.dWidth, Main.dHeight, Main.dWidth, 0);
                        drawTexture(enterName, 400, 250, 400, 550, 900, 550, 900, 250);
                        drawTexture(button1_2, 556, 335, 556, 385, 856, 385, 856, 335);
                        drawTexture(button1_3, 555, 375, 555, 405, 855, 405, 855, 375);
                        break;


                }


                break;
            case 3:

                drawTexture(topscoresTexture, 330, 170, 330, 640, 980, 640, 980, 170);
                switch (selected) {
                    case 1:
                        drawTexture(topPrevOver, 80, 285, 80, 525, 320, 525, 320, 285);
                        drawTexture(topNext, 990, 285, 990, 525, 1230, 525, 1230, 285);
                        drawTexture(back, 190, 180, 190, 230, 320, 230, 320, 180);
                        break;

                    case 2:
                        drawTexture(topPrev, 80, 285, 80, 525, 320, 525, 320, 285);
                        drawTexture(topNextOver, 990, 285, 990, 525, 1230, 525, 1230, 285);
                        drawTexture(back, 190, 180, 190, 230, 320, 230, 320, 180);
                        break;

                    case 3:
                        drawTexture(topPrev, 80, 285, 80, 525, 320, 525, 320, 285);
                        drawTexture(topNext, 990, 285, 990, 525, 1230, 525, 1230, 285);
                        drawTexture(backOver, 190, 180, 190, 230, 320, 230, 320, 180);
                        break;

                    default:
                        drawTexture(topPrev, 80, 285, 80, 525, 320, 525, 320, 285);
                        drawTexture(topNext, 990, 285, 990, 525, 1230, 525, 1230, 285);
                        drawTexture(back, 190, 180, 190, 230, 320, 230, 320, 180);
                        break;

                }


                break;


            case 4:
                drawTexture(settingsTexture, 450, 200, 450, 750, 1000, 750, 1000, 200);
                if (Game.gameConfigurationNew.playersAmount == 1)
                    drawTexture(number1, 710, 295, 710, 322, 750, 322, 750, 295);
                else drawTexture(number2, 710, 295, 710, 322, 750, 322, 750, 295);

                if (Game.gameConfigurationNew.isBot == 0)
                    drawTexture(off, 700, 323, 700, 356, 770, 356, 770, 323);
                else
                    drawTexture(on, 700, 323, 700, 356, 770, 356, 770, 323);

                if (Game.gameConfigurationNew.mute) {
                    drawTexture(off, 700, 388, 700, 421, 770, 421, 770, 388);
                } else{
                    drawTexture(on, 700, 388, 700, 421, 770, 421, 770, 388);
                }
                //drawTexture(on,700,387,700,422,770,422,770,387);


                switch (selected) {
                    case 1:
                        drawTexture(button4_6,300,210,300,250,440,250,440,210);
                        drawTexture(button4_7,300,255,300,295,440,295,440,255);
                        break;
                    case 2:
                        drawTexture(button4_6,300,210,300,250,440,250,440,210);
                        drawTexture(button4_7,300,255,300,295,440,295,440,255);
                        break;
                    case 3:
                        drawTexture(button4_6,300,210,300,250,440,250,440,210);
                        drawTexture(button4_7,300,255,300,295,440,295,440,255);
                        break;
                    case 4:
                        drawTexture(button4_6,300,210,300,250,440,250,440,210);
                        drawTexture(button4_7,300,255,300,295,440,295,440,255);
                        break;
                    case 5:
                        drawTexture(button4_6,300,210,300,250,440,250,440,210);
                        drawTexture(button4_7,300,255,300,295,440,295,440,255);
                        break;
                    case 6:
                        drawTexture(button4_6over,300,210,300,250,440,250,440,210);
                        drawTexture(button4_7,300,255,300,295,440,295,440,255);
                        break;
                    case 7:
                        drawTexture(button4_6,300,210,300,250,440,250,440,210);
                        drawTexture(button4_7over,300,255,300,295,440,295,440,255);
                        break;
                    default:
                        drawTexture(button4_6,300,210,300,250,440,250,440,210);
                        drawTexture(button4_7,300,255,300,295,440,295,440,255);
                        break;


                }
                break;

        }


    }

    public static void bigFon() { //(float x, float y, float sx, float sy) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
//            glBindTexture(GL_TEXTURE_2D, 0);
            glColor3f(1.0f, 1.0f, 1.0f);
            glColor3f(0.4f, 0.4f, 0.8f);
            // glTranslatef(x, y, 0);
//            sx*=0.9;
            // glRotatef(-0, 0, 0, 1);
            wallTexture.bind();
            glDisable(GL_POLYGON_SMOOTH);
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(0, 0);
                glTexCoord2f(0, 1);
                glVertex2f(0, Main.dHeight);
                glTexCoord2f(1,1); // wallTexture.getHeight());
                glVertex2f(Main.dWidth, Main.dHeight);
                glTexCoord2f(1,0);
                glVertex2f(Main.dWidth,0);
            }
            glEnd();
        }
        glPopMatrix();
//        fon1.release();
    }

    public static void init() {

        button0_1= Game.loadTexture("buttonsMainMenu/b1");
        button0_2= Game.loadTexture("buttonsMainMenu/b2");
        button0_3= Game.loadTexture("buttonsMainMenu/b3");
        button0_4= Game.loadTexture("buttonsMainMenu/b4");

        button0_1over= Game.loadTexture("buttonsMainMenu/h1");
        button0_2over= Game.loadTexture("buttonsMainMenu/h2");
        button0_3over= Game.loadTexture("buttonsMainMenu/h3");
        button0_4over= Game.loadTexture("buttonsMainMenu/h4");

        pauseTexture=Game.loadTexture("fon/paused");
        settingsTexture = Game.loadTexture("fon/images/settings/settings");
        shadowTexture = Game.loadTexture("fon/shadow-texture");

        topscoresTexture = Game.loadTexture("fon/images/topscores/1");
        topNext = Game.loadTexture("fon/images/topscores/top_next");
        topNextOver = Game.loadTexture("fon/images/topscores/top_next_over");
        topPrev = Game.loadTexture("fon/images/topscores/top_prev");
        topPrevOver = Game.loadTexture("fon/images/topscores/top_prev_over");
        back = Game.loadTexture("fon/images/topscores/back");
        backOver = Game.loadTexture("fon/images/topscores/back_over");

        button1_1 = Game.loadTexture("fon/images/paused/paused_b1");
        button1_2 = Game.loadTexture("fon/images/paused/paused_b2");
        button1_3 = Game.loadTexture("fon/images/paused/paused_b3");
        button1_1over = Game.loadTexture("fon/images/paused/paused_h1");
        button1_2over = Game.loadTexture("fon/images/paused/paused_h2");
        button1_3over = Game.loadTexture("fon/images/paused/paused_h3");
       /* Texture button3_1;
        Texture button3_2;
        Texture button3_3;
        Texture button4_1;
        Texture button4_2;
        Texture button4_3;
        Texture button4_4;
        Texture button4_5;*/
        button4_6over =Game.loadTexture("fon/images/settings/settings_h6");
        button4_7over =Game.loadTexture("fon/images/settings/settings_h7");
        button4_6= Game.loadTexture("fon/images/settings/settings_b6");
        button4_7 = Game.loadTexture("fon/images/settings/settings_b7");
        wallTexture = Game.loadTexture("fon/texture");
        wallpaper = wallTexture;
        gradient = Game.loadTexture("fon/gradient");
        on = Game.loadTexture("fon/images/settings/on");
        off = Game.loadTexture("fon/images/settings/off");
        number1 = Game.loadTexture("fon/images/settings/number1");
        number2 = Game.loadTexture("fon/images/settings/number2");
        shadow = Game.loadTexture("fon/images/entername/shadow");
        enterName = Game.loadTexture("fon/images/entername/enterName");
        okgo = Game.loadTexture("fon/images/entername/okgo");
        okgoover = Game.loadTexture("fon/images/entername/okgo_over");
        gameOver = Game.loadTexture("fon/images/gameover/gameover");

        initMusic();
    }

    public static void drawHole(float ro,double phi,float r,float g, float b,float opacity){


        glPushMatrix();
        glPointSize((int)(ax/50));
        glBegin(GL_POINTS);

        //glVertex2i(200,300);
        glColor4f(r, g, b, opacity);

        y = (Main.dHeight)/2 + (int) (ax*ro*Math.sin(phi));
        x = bx+ax+100 + (int) (bx*ro*Math.cos(phi));
        x -= xshift;

        glVertex2i(x, y);
        glEnd();
        //System.out.println(phi+" PHI");
        double k = Math.cos(phi);

        if ((k< -0.2)&&(k>=-1)) {

            if (tmp==10) {

                glPointSize(3);
                glColor4f(r, g, b, 0.4f);
                glBegin(GL_POINTS);

                x = bx + 100 + (int) (ax * ro) + 1 + (int) (bx * Math.pow(ro, 1.5) * ro * Math.cos(phi));
                x *= ro;
                y = (Main.dHeight) / 2 + (int) (ax * Math.pow(ro, 1.5) * ro * Math.sin(phi));
                x -= xshift;


                glVertex2i(x, y);
                glEnd();

                tmp = 0;

            }

            tmp++;

        }



        glPopMatrix();

    }


    public static void drawTopScoreList(int pg, int maxPg) {
        int j = (pg-1) * 10;

int x =0;
        font1_1.drawString(400,250,"No          Name           Score         Time");
        if (pg == maxPg) {
            for (int i=j; i<=Game.maxElems-1;i++) {
/*
                String str = String.valueOf(ListWorker.getList(0, 100).get(i));
                System.out.println(str +"str");
                String name = str.substring(1, str.indexOf('*', 2));
                int second = str.indexOf('*', 2);
                String score = str.substring(second + 1, str.indexOf('*', second + 1));
                int third = str.indexOf('*', second + 1);
                String time = str.substring(third + 1, str.indexOf('*', third + 1));
                int fourth = str.indexOf('*', third + 1);
                String best = str.substring(fourth + 1, str.lastIndexOf('*'));
                Integer isBest = Integer.valueOf(best);
                System.out.println(isBest + "___" + best);




                fontForTop = font1_1;
                if (isBest == 1) {
                    fontForTop.drawString(400, 280 + x, String.valueOf(i + 1), Color.cyan);
                    fontForTop.drawString(470, 280 + x, name,Color.cyan);
                    fontForTop.drawString(615, 280 + x, score,Color.cyan);
                    fontForTop.drawString(715, 280 + x, time, Color.cyan);

                    x += 30;
                }
                else {
                    fontForTop.drawString(400, 280 + x, String.valueOf(i + 1));
                    fontForTop.drawString(470, 280 + x, name);
                    fontForTop.drawString(615, 280 + x, score);
                    fontForTop.drawString(715, 280 + x, time);
                    x += 30;

                }
  */
            }

        }
        else {

            for (int i = j; i <= j + 9; i++) {

               /* String str = String.valueOf(ListWorker.getList(0, 100).get(i));
                String name = str.substring(1, str.indexOf('*', 2));
                int second = str.indexOf('*',2);
                String score = str.substring(second + 1, str.indexOf('*',second +1));
                int third = str.indexOf('*',second+1);
                String time = str.substring(third+1, str.indexOf('*',third+1));
                int fourth = str.indexOf('*',third+1);
                String best = str.substring(fourth+1, str.lastIndexOf('*'));
                Integer isBest = Integer.valueOf(best);




                    fontForTop = font1_1;
                if (isBest == 1) {
                    fontForTop.drawString(400, 280 + x, String.valueOf(i + 1), Color.cyan);
                    fontForTop.drawString(470, 280 + x, name,Color.cyan);
                    fontForTop.drawString(615, 280 + x, score,Color.cyan);
                    fontForTop.drawString(715, 280 + x, time, Color.cyan);

                    x += 30;
                }
                else {
                    fontForTop.drawString(400, 280 + x, String.valueOf(i + 1));
                    fontForTop.drawString(470, 280 + x, name);
                    fontForTop.drawString(615, 280 + x, score);
                    fontForTop.drawString(715, 280 + x, time);
                    x += 30;

                }
*/
            }
        }
    }


    public static void drawArc(float r,float g, float b, double phase, double phiZero, float ro){

        double limit = (1.5*720*(0.003 + Math.sqrt(ro)));
        //double opacity = (phiZero)*(720 *(0.003+ Math.sqrt(ro))) / (60*(Math.PI));


        for (int i = 1; i < (int)(limit); i++) {
            //System.out.println(phase+" Phase");

            Draw.drawHole(ro, phase + phiZero + i * Math.PI / (720 * (0.003 + Math.sqrt(ro))), r, g, b, 0.05f + 0.3f * ro * ro);

        }



    }

}
