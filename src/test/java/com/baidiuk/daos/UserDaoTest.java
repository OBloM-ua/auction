package com.baidiuk.daos;

import com.baidiuk.entities.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    private ItemDao itemDao = new ItemDao();
    private UserDao userDao = new UserDao();

    @Before
    public void setUp() throws Exception {
        itemDao.deleteAll();
        userDao.deleteAll();
    }

    @Test
    public void create() throws Exception {
        assertEquals(0, userDao.getAll().size());
        User user = new User("bogdan", "lonko");
        userDao.updateOrInsert(user);
        assertEquals(1, userDao.getAll().size());
        assertNotEquals(0, user.getId());
    }

    @Test
    public void update() throws Exception {
        User user = new User("bogdan", "lonko");
        userDao.updateOrInsert(user);

        // update
        user.setEmail("aaa");
        userDao.updateOrInsert(user);

        //get from DB and assert
        user = userDao.get(user.getId());
        assertEquals("aaa", user.getEmail());
    }

    @Test
    public void delete() throws Exception {
        assertEquals(0, userDao.getAll().size());
        User user = new User("bogdan", "lonko");
        userDao.updateOrInsert(user);
        assertEquals(1, userDao.getAll().size());
        userDao.delete(user);
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void get() throws Exception {
        User user = new User("bogdan", "lonko");
        userDao.updateOrInsert(user);
        user = userDao.get(user.getId());
        assertNotNull(user);
    }

}