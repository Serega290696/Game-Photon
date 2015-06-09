import Photon.Draw;
import Photon.Game;
import Photon.GameObjects.GOPhoton.GOPlayer;
import Photon.Main;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Serega on 31.05.2015.
 */
public class TestPlayersList {

//    public final GOPlayer playerType1 = GOPlayer.newBuilder()
//                    .setXStart(111)
//                    .setYStart(50 * 0.7f)
//                    .setSize(2)
//                    .setFigure(DrawFigure.CIRCLE)
//                    .setName("aaa")
//                    .setColor(2)
//                    .isBot(false)
//                    .build()
//            ;
//    public final GOPlayer playerType2 = GOPlayer.newBuilder()
//            .setXStart(GOPlayer.beginX)
//            .setYStart(60 * Main.ratio)
//            .setSize(2)
//            .setFigure(DrawFigure.CIRCLE)
//            .setName(User.defaultName + "_2")
//            .setColor(4)
//            .isBot(false)
//            .build();
//    public final GOPlayer playerType3 = GOPlayer.newBuilder()
//            .setXStart(GOPlayer.beginX)
//            .setYStart(65 * Main.ratio)
//            .setSize(2)
//            .setFigure(DrawFigure.CIRCLE)
//            .setName(User.defaultName + "_3")
//            .setColor(3)
//            .isBot(true)
//            .build()
//    ;
    public GOPlayer specialMass[] = {
            null,
            null,
            null
    };
    private int testPlayersAmount = 2;
    private int testIsBot = 1;

    @Before
    public void beforeTest() {
        System.out.println("BEGIN TEST :)");

        Main.initDisplay();
        Main.initGL();
        Main.initDBWorker();
        Draw.init();
        Main.initGame();

        System.out.println("\n\n~INITIALISATION FINISH!\n");
        int i=0;
        if(specialMass[0] != null)
            for(i = 0; i < specialMass.length; i++) {
                if(specialMass[i] == null) break;
            }
//        else i = -1;
        System.out.println("\n\n\n***"
                + "\nMass size: \t\t" + specialMass.length
                        + "\nNot null elements: \t"+ (i)
                        + "\nNull elements: \t"+ (specialMass.length-i)
                + "\n***\n<<<"
        );
        Game.players.clear();
        Game.gameConfiguration.playersAmount = testPlayersAmount;
        Game.gameConfiguration.isBot = testIsBot;
    }
    @After
    public void finishMessage() {
        System.out.println("\n>>>\n");
        int i=0;
        if(specialMass[0] != null)
            for(i = 0; i < specialMass.length; i++) {
                if(specialMass[i] == null) break;
            }
//        else i = -1;
        System.out.println("***"
                + "\nMass size: \t\t" + specialMass.length
                + "\nNot null elements: \t"+ (i)
                + "\nNull elements: \t"+ (specialMass.length-i)
                + "\n***\n"
        );
    }

    @Test
    public void specialMassOfPlayers() {
        Main.game.addAllPlayers(specialMass);
        Assert.assertSame("Not all players was added!\n"
                +"Need add: " + specialMass.length
                +".\nWas added: " + Main.game.players.size()+"\n",
                specialMass.length, Main.game.players.size());
        for(int i = 0; i < Main.game.players.size(); i++) {
            System.out.println((i+1) + ". " + Main.game.players.get(i));
            Assert.assertNotNull(Main.game.players.get(i));
        }
    }

}
