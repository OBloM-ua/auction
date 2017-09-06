package com.baidiuk.daos;

import com.baidiuk.entities.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    private UserDao dao = new UserDao();

    @Before
    public void setUp() throws Exception {
        dao.deleteAll();
    }

    @Test
    public void create() throws Exception {
        assertEquals(0, dao.getAll().size());
        User user = new User("bogdan", "lonko");
        dao.updateOrInsert(user);
        assertEquals(1, dao.getAll().size());
        assertNotEquals(0, user.getId());
    }

    @Test
    public void update() throws Exception {
        User user = new User("bogdan", "lonko");
        dao.updateOrInsert(user);

        // update
        user.setEmail("aaa");
        dao.updateOrInsert(user);

        //get from DB and assert
        user = dao.get(user.getId());
        assertEquals("aaa", user.getEmail());
    }

    @Test
    public void delete() throws Exception {
        assertEquals(0, dao.getAll().size());
        User user = new User("bogdan", "lonko");
        dao.updateOrInsert(user);
        assertEquals(1, dao.getAll().size());
        dao.delete(user);
        assertEquals(0, dao.getAll().size());
    }

    @Test
    public void get() throws Exception {
        User user = new User("bogdan", "lonko");
        dao.updateOrInsert(user);
        user = dao.get(user.getId());
        assertNotNull(user);
    }

}