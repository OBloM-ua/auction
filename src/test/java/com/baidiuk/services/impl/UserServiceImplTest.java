package com.baidiuk.services.impl;

import com.baidiuk.config.TestDbConf;
import com.baidiuk.entities.User;
import com.baidiuk.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.*;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDbConf.class)
@WebAppConfiguration
public class UserServiceImplTest {
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private UserService userService;
    private User user;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        userService.deleteAll();
        user = new User("y.baidiuk", "dai");
    }

    @After
    public void tearDown() throws Exception {

    }
    /////-------------------------------------

    @Test
    public void create() throws Exception {
        assertEquals(0, userService.getAll().size());
        userService.createOrUpdate(user);
        assertEquals(1, userService.getAll().size());
    }

    @Test
    public void update() throws Exception {
        user = userService.createOrUpdate(user);
        user.setEmail("newEmail");
        user = userService.createOrUpdate(user);
        assertEquals("newEmail", user.getEmail());
    }

    @Test
    public void get() throws Exception {
        user = userService.createOrUpdate(user);
        User DbUser = userService.get(user.getId());
        assertEquals(user, DbUser);
    }

    @Test
    public void getByName() throws Exception {
        user = userService.createOrUpdate(user);
        User DbUser = userService.getByEmail(user.getEmail());
        assertEquals(user, DbUser);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(0, userService.getAll().size());
        user = userService.createOrUpdate(user);
        assertEquals(1, userService.getAll().size());
        userService.delete(user);
        assertEquals(0, userService.getAll().size());
    }

    @Test
    public void getAll() throws Exception {
        user = userService.createOrUpdate(user);
        User user1 = new User("asd", "sddaa");
        userService.createOrUpdate(user1);
        assertEquals(2, userService.getAll().size());
    }

}