package com.baidiuk.daos;

import com.baidiuk.entities.Item;
import com.baidiuk.entities.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ItemDaoTest {
    private ItemDao itemDao = new ItemDao();
    private UserDao userDao = new UserDao();
    User oleh;
    private Item item;


    @Before
    public void setUp() throws Exception {
        itemDao.deleteAll();
        userDao.deleteAll();
        oleh = new User("oleh@gmail.com", "5423434");
        userDao.updateOrInsert(oleh);
        item = new Item("Ski", 55, oleh);
        itemDao.updateOrInsert(item);
    }

    @Test
    public void get() throws Exception {
        List<Item> list = itemDao.getAll();
        Item itemFromDB = list.get(0);
        assertEquals(item , itemFromDB);
    }

    @Test
    public void getAll() throws Exception {
        Item item2 = new Item("Ski22", 55, oleh);
        itemDao.updateOrInsert(item2);
        assertEquals(2, itemDao.getAll().size());
    }

    @Test
    public void updateOrInsert() throws Exception {
        assertEquals(1, itemDao.getAll().size());
    }

    @Test
    public void delete() throws Exception {
        itemDao.delete(item);
        assertEquals(0, itemDao.getAll().size());

    }

    @Test
    public void deleteAll() throws Exception {
        Item item2 = new Item("Ski22", 55, oleh);
        Item item3 = new Item("Ski22", 55, oleh);
        itemDao.updateOrInsert(item2);
        itemDao.updateOrInsert(item3);
        assertEquals(3, itemDao.getAll().size());

        itemDao.deleteAll();
        assertEquals(0, itemDao.getAll().size());


    }

}