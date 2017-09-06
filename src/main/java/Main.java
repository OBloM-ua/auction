import com.baidiuk.entities.HibernateUtil;
import com.baidiuk.entities.User;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + Oracle");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        User user = new User("email", "dai");
        session.save(user);
        session.getTransaction().commit();
    }

}