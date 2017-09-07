package com.baidiuk.daos;

import com.baidiuk.entities.HibernateUtil;
import com.baidiuk.entities.User;
import org.hibernate.Session;

import java.util.List;

public class UserDao {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public User get(int id) {
        return session.find(User.class, id);
    }

    public List getAll() {
        return session.createQuery("from User").list();
    }


    public void updateOrInsert(User user) {
        User dbUser = get(user.getId());

        session.beginTransaction();
        if (dbUser == null)
            session.persist(user);
        else
            session.merge(user);
        session.getTransaction().commit();
    }


    public void updateOrInsert(List<User> list) {
        for (User u : list)
            updateOrInsert(u);
    }

    public void delete(User user) {
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }

    public void delete(List<User> list) {
        for (User u : list) {
            delete(u);
        }
    }

    public void deleteAll() {
        delete(getAll());
    }


}
