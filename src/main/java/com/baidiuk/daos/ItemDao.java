package com.baidiuk.daos;

import com.baidiuk.utils.HibernateUtil;
import com.baidiuk.entities.Item;
import org.hibernate.Session;

import java.util.List;

public class ItemDao {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public Item get(int id) {
        return session.find(Item.class, id);
    }

    public List<Item> getAll() {
        return session.createQuery("from Item").list();
    }

    public void updateOrInsert(Item item) {
        Item dbItem = get(item.getId());

        session.beginTransaction();
        if (dbItem == null)
            session.persist(item);
        else
            session.merge(item);
        session.getTransaction().commit();
    }

    public void updateOrInsert(List<Item> list) {
        for (Item i : list)
            updateOrInsert(i);
    }

    public void delete(Item item) {
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
    }

    public void delete(List<Item> list) {
        for (Item i : list) {
            delete(i);
        }
    }
    public void deleteAll() {
        delete(getAll());
    }
}
