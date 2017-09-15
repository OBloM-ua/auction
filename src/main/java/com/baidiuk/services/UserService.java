package com.baidiuk.services;

import com.baidiuk.entities.User;

import java.util.List;

public interface UserService {

    User createOrUpdate(User user);
    User get(int id);
    User getByEmail(String name);
    void delete(User user);

    List<User> getAll();
    void deleteAll();

}
