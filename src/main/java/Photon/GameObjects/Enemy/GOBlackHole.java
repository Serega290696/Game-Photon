package Photon.GameObjects.Enemy;

import Photon.*;
import Photon.Enums.DrawFigure;
import Photon.GameObjects.GOPhoton.GOPlayer;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

/**
 * Created by Serega on 22.03.2015.
 */
public class GOBlackHole extends GO {

    public float specialGravityParameter = 0;
    public float gravitationPower = 0;
    public float maxSpeedGravitation = 25;
    public float gravitationParameter = 0;
    public float defaultGravitationPower = Game.gameConfiguration.defaultGravitationPower;


    public static double phase = 0;
    public static double phiZero = 0;
    public static double diff = 0;

    public GOBlackHole() {

        x = 0;
        y = 100/2*Main.ratio;
        sx = 30;
        x = sx/2;
        sy = 100*Main.ratio;
        figure = DrawFigure.RECT;
        color = 7;
        opacity = 1.0f;
        defaultGravitationPower = Game.gameConfiguration.defaultGravitationPower;
//        for(int i = 0; i < Game.gameConfiguration.playersAmount; i++) {
            gravitationParameter = defaultGravitationPower;
//        }
//        x = -100/2;
//        y = 100/2*Main.ratio;
//        sx = 100*1.2f;
//        sy = 100*3*Main.ratio;
//        figure = DrawFigure.RECT;
//        color = 7;
//        opacity = 1.0f;
    }

    @Override
    public void move() throws CloneNotSupportedException {

    }

    @Override
    public void update() {
        gravitationParameter = Game.gameConfiguration.gravitationParameter[0];
//        float growth = 0.1f;
//        if(gravitationPower < maxSpeedGravitation)
//            gravitationPower += growth / Main.fps;
        gravitationPower = 1;

        for(GO ob : Game.obstacles) {
            if(Physics.checkCollisions(this, ob)) {
                ob.collision();
//                break;
            }
        }
        for(GO bonus : Game.bonuses) {
            if(Physics.checkCollisions(this, bonus)) {
                bonus.collision();
//                break;
            }
        }
        for(GOPlayer obPlayer : Game.players) {
//            setGravitationPower(obPlayer);
//            if(Physics.checkCollisions(this, obPlayer)) {
//                Game.gameOver(obPlayer);
//            }
        }
//        sx = gravitationPower * 10;
    }

    public void setGravitationPower(GOPlayer curPlayer) {
        gravitationParameter = Game.gameConfiguration.gravitationParameter[Main.game.players.indexOf(curPlayer)];
        gravitationPower = defaultGravitationPower;
            specialGravityParameter = 1;
            gravitationPower = defaultGravitationPower;
        if(defaultGravitationPower-gravitationParameter < 0) {
                gravitationParameter -= gravitationParameter*0.1f/Main.fps;
            Game.gameConfiguration.gravitationParameter[Main.game.players.indexOf(curPlayer)] = gravitationParameter;
        }
        gravitationPower -= gravitationParameter;
    }

    @Override
    public void render() {
        phase=0;
        phiZero=0;

        glDisable(GL_TEXTURE_2D);

        for (float ro = 0; ro <= 1; ro += 0.02) {


            if (((ro < 0.14) && (ro >= 0.06)) || ((ro >= 0.36) && (ro < 0.42)) || ((ro >= 0.6) && (ro < 0.68))) {
                if ((ro < 0.14) && (ro >= 0.06)) {
                    Draw.drawArc(0.7f, 0.2f, 0.2f, 3*(diff+phase), phiZero, ro); //purple
                }
                else
                    Draw.drawArc(0.4f, 0.17f, 0.6f,(2- (float)( (int)(ro*100) /20) / 5 )*(diff+phase)*0.8, phiZero, ro); //purple

            }

            if ((Math.abs((ro-0.04))<0.01) ||(Math.abs((ro-0.14))<0.01) || (Math.abs(ro-0.3)<0.01) || (Math.abs(ro-0.42)<0.01) || (Math.abs(ro-0.54)<0.01) || (Math.abs(ro-0.68)<0.01) || (Math.abs(ro-0.8)<0.01) || (Math.abs(ro-0.94)<0.01)) {
                phase += (Math.PI/3);
                phiZero=0;


            }

            if ( ((ro >= 0.5) && (ro < 0.54)) || ((ro >= 0.74) && (ro < 0.8))) {
                Draw.drawArc(0.4f, 0.7f, 0.7f, phase-diff, phiZero, ro); //yellow
            }

            if (((ro < 0.3) && (ro >= 0.24))) {
                Draw.drawArc(0.4f, 0.8f, 0.7f, phase - diff, phiZero, ro); //yellow

            }
            if (((ro >= 0.86) && (ro < 0.94))) {
                Draw.drawArc(0.7f, 0.05f, 0.1f, phase - diff, phiZero, ro); //yellow
            }
            if (((ro >= 0.98) && (ro <= 1))) {
                Draw.drawArc(0.9f, 0.95f, 0.3f, phase - diff, phiZero, ro); //yellow
            }
            if (phase > 2*Math.PI)
                phase -= 2*Math.PI;
            phiZero += 18 * Math.PI / (720 *(0.003+ Math.sqrt(ro)));


        }
        diff += Math.PI/90;
        glEnable(GL_TEXTURE_2D);
    }

    @Override
    public void collision() {

    }
}
