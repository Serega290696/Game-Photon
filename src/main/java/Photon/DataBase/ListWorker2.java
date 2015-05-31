package Photon.DataBase;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ListWorker2 {

    public static EntityManager em = Persistence.createEntityManagerFactory("PHOTONDB").createEntityManager();

    public static User add(User user) {
        em.getTransaction().begin();
        User userFromBD = em.merge(user);
        em.getTransaction().commit();
        return userFromBD;
    }

    public static void delete(long id){
        em.remove(get(id));
    }

    public static User get(long id){
        return em.find(User.class, id);
    }

    public static void update(User car){
        em.getTransaction().begin();
        em.merge(car);
        em.getTransaction().commit();
    }

    public static List<User> getList(){
        TypedQuery<User> namedQuery = em.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }
    public static List<User> getList(int begin, int range) {


        if(begin + range-1 > getList().size())
            range = getList().size()-begin;
        if(range < 0) return null;
        return getList().subList(begin, range);
    }

}
