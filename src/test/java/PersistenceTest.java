import Photon.DataBase.ListWorker2;
import Photon.DataBase.User;
import Photon.Main;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Serega on 31.05.2015.
 */
public class PersistenceTest {

    ListWorker2 listWorker = new ListWorker2();
    @BeforeClass
    public static void initDB() {
        Main.initDBWorker();
    }

    @Test
    public void testGetAll() {
        List<User> users = listWorker.getList();
        for(User user : users){
            System.out.println(user);
        }
    }
    @Test
    public void testAdd() {
        User user = new User("Jim", (int) 256000, new Time((500 / 3600), (500 / 60) % 60, 500 % 60), (new java.sql.Date(new Date().getTime())));

        User userShow = listWorker.add(user);

        System.out.println(userShow);
    }
    @Test
    public void testSelect() throws Exception {
        User user = new User("Jim", (int) 256000, new Time((500 / 3600), (500 / 60) % 60, 500 % 60), (new java.sql.Date(new Date().getTime())));


        User userT = listWorker.add(user);

        User userFromBD = listWorker.get(user.getId());
        System.out.println(userFromBD);
    }

}
