package service;

import model.User;
import exception.AuthenticationException;

import java.util.Map;
import java.util.UUID;


public class AuthenticationService {
    private Map<String, User> users;

    public AuthenticationService(Map<String, User> users) {
        this.users = users;
    }

    public User authenticate(String email, String password) throws AuthenticationException {

        if(users.containsKey(email)) {
            var user1 = users.get(email);
            if (user1.getPassword().equals(password)) {
                return user1;
            } else {
                throw new AuthenticationException("Invalid password");
            }
        }
        throw new AuthenticationException("This email is not registered with us");

//        for (User user : users.values()) {
//            if (user.getEmail().equals(email)) {
//                if (user.getPassword().equals(password)) {
//                    return user;
//                } else {
//                    throw new AuthenticationException("Invalid password");
//                }
//            }
//        }
//        throw new AuthenticationException("Invalid email or password");
    }
    public  Map<String,User> getUsers(){
        return users;
    }
}
