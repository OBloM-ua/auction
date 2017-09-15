package com.baidiuk.services.impl;

import com.baidiuk.entities.User;
import com.baidiuk.repository.UserRepository;
import com.baidiuk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createOrUpdate(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User get(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getByEmail(String name) {
        return userRepository.getByEmail(name);
    }


    @Override
    public void delete(User user) {
        userRepository.delete(user);

    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAll() {
        for (User u : getAll())
            delete(u);
    }
}
