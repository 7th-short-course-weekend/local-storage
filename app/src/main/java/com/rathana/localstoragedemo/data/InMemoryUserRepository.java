package com.rathana.localstoragedemo.data;

import com.rathana.localstoragedemo.model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository {

    List<User> users;

    public InMemoryUserRepository() {
        users=new ArrayList<>();
        users.add(new User("admin","admin"));
    }

    public void create(User user){
        if(user!=null){
            users.add(user);
        }
    }

    public User authenticate(User user){
        for(User u: users){
            if(u.getName().equals(user.getName())
            && u.getPassword().equals(user.getPassword())){
                return u;
            }
        }

        return null;
    }
}
