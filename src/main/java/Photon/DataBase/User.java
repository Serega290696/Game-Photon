package Photon.DataBase;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
//import java.util.Date;

/**
 * Created by Serega on 02.04.2015.
 */
@Entity
@Table(name = "top_list")
@NamedQuery(name = "User.getAll", query = "SELECT c from User c")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", length = 6, nullable = false)
    private int id = 0;
    @Column(name = "name")
    private String name;
    @Column(name = "placeInTopList")
    private int placeInTopList = 0;
    @Column(name = "score")
    private int score;
    @Column(name = "bestTime")
    private Time bestTime;
    @Column(name = "gameDate")
    private Date gameDate;
    @Column(name = "isBestScore")
    private boolean isBestScore = true;

    public static String defaultName = "Player";

    public User() {

    }
    public User(String name, int score, Time time, Date date) {
        this.id = setId();
        System.out.println("ID: "+id);
        this.name = name;
        this.score = score;
        this.placeInTopList = setPlaceInTopList(this.score);
        this.bestTime = time;
        this.gameDate = date;
        setName();
//        this(0, name, setPlaceInTopList(this.score), score, time, date, isBestScore);
    }
    public User(int id, String name, int placeInTopList, int bestScore, Time bestTime, Date gameDate, int isBestScore) {
        this.id = id;
        this.name = name;
        this.placeInTopList = placeInTopList;
        this.score = bestScore;
        this.bestTime = bestTime;
        this.gameDate = gameDate;
        if (isBestScore == 1) {
            this.isBestScore = true;
        }
        else {
            this.isBestScore = false;
        }

        setName();
    }

    public boolean changeName(String newName) {
        name = newName;
        if(name == newName)
            return true;
        return false;
    }
    public void newScore(int score, Time time) {
        if(isBestScore(score)) {
            this.score = score;
            bestTime = time;
            gameDate = new Date(new java.util.Date().getTime());
            placeInTopList = setPlaceInTopList(score);
        }
    }
    public boolean isBestScore(int score) {
        return score > this.score;
    }

    public int setPlaceInTopList(int score) {
        int newPlace = 1;

        ArrayList<User> usersList = ListWorker.DBWorker.getAll();
        if(usersList.isEmpty())
            return 1;
        for (int i = 0; score <= usersList.get(i).getScore(); i++) {
            if(usersList.get(i).getName().equals(this.name))
                this.isBestScore = false;
            newPlace = i + 2;
            if(i >= usersList.size()-1) {
//                newPlace--;
                break;
            }
        }
//        newPlace++;
        placeInTopList = newPlace;
        ListWorker.DBWorker.updatePlaceInTopList(this.id, this.placeInTopList, this.isBestScoreBool());
//        DBWorker.update(this);
        for (int i = newPlace-1; i <= usersList.size() - 1; i++) {
            User curUser = usersList.get(i);
            if(this.getName().equals(curUser.getName()))
                curUser.setBestScoreBool(false);
            curUser.setPlaceInTopListByNumber(curUser.getPlaceInTopList() + 1);
//            DataBaseWorker.updatePlaceInTopList(curUser.getId(), curUser.getPlaceInTopList(), curUser.isBestScoreBool());
            ListWorker.DBWorker.update(curUser);
        }
        return newPlace;
    }
    public void setPlaceInTopListByNumber(int placeInTopList) {
        this.placeInTopList = placeInTopList;
    }

    public boolean isBestScoreBool() {
        return isBestScore;
    }

    public void setBestScoreBool(boolean isBestScore) {
        this.isBestScore = isBestScore;
    }


    @Override
    public String toString() {
        int best;
        if (isBestScore) {
            best = 1;
        }
        else {
            best = 0;
        }

        String str = "*" + name + "*" + score + "*" + bestTime + "*" + best + "*";
        return str;
    }



    public ArrayList<String> getData() {
        String prefics = "\t* ";
        ArrayList<String> dataList = new ArrayList<String>();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        dataList.add(prefics + "Id: " + Integer.toString(id));
        dataList.add(prefics + "Name: " + name);
        dataList.add(prefics + "Place: " + Integer.toString(placeInTopList));
        dataList.add(prefics + "Score: " + Integer.toString(score));
        dataList.add(prefics + "Time: " + bestTime.toString());
        dataList.add(prefics + "Date: " + gameDate.toString());
        if(isBestScore)
            dataList.add(prefics + "THE RECORD!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//        dataList.add(Integer.toString(id));
//        dataList.add(name);
//        dataList.add(Integer.toString(placeInTopList));
//        dataList.add(Integer.toString(score));
//        dataList.add(bestTime.toString());
//        dataList.add(gameDate.toString());
//        dataList.add(Boolean.toString(isBestScore));

        return dataList;
    }
    public static int setId() {
        int tmpID = 1;
        System.out.println("AAA");
        for(int i = 1; ListWorker.DBWorker.getUser(i) != null; i++) {
            tmpID = i+1;
            System.out.println(i);
        }
        System.out.println(ListWorker.DBWorker.getAll());
        System.out.println(ListWorker.DBWorker.getUser(2));
//        tmpID++;
        return tmpID;
    }
    public void setName() {
        if(name.equals(defaultName)) {
            name += id;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBestTime(Time bestTime) {
        this.bestTime = bestTime;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public void setIsBestScore(boolean isBestScore) {
        this.isBestScore = isBestScore;
    }

    public static void setDefaultName(String defaultName) {
        User.defaultName = defaultName;
    }

    public boolean isBestScore() {
        return isBestScore;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPlaceInTopList() {
        return placeInTopList;
    }
    public int getScore() {
        return score;
    }
    public Time getBestTime() {
        return bestTime;
    }
    public Date getGameDate() {
        return gameDate;
    }
    public int isBestScoreInt() {
        if(isBestScore)
            return 1;
        return 0;
    }

}
