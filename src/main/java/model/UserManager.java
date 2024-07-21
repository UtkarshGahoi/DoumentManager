package model;

import exception.AuthenticationException;
import exception.EmailAuthentication;
import service.AuthenticationService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {
    private Map<String, User> users;
    private AuthenticationService authService;

    public UserManager() {
        this.users = new HashMap<>();
        this.authService = new AuthenticationService(users);
    }

    public User createUser(String name, String email, String password) throws EmailAuthentication {
        User user = new User(name, email, password);
        if(users.containsKey(email)){
            throw new EmailAuthentication("Email is already registered with us");
        }
        users.put(email, user);
        return user;
    }

    public User loginUser(String email, String password) throws AuthenticationException {
        return authService.authenticate(email, password);
    }
    public  Map<String,User> getUsers(){
        return authService.getUsers();
    }
}
