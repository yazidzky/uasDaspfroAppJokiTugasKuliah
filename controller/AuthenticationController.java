package controller;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationController {
    private Map<String, String> users;

    public AuthenticationController() {
        users = new HashMap<>();
        // Add default users
        users.put("pengguna1", "katasandi1");
        users.put("pengguna2", "katasandi2");
    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public void registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, password);
            System.out.println("User registration successful.");
        } else {
            System.out.println("Username already exists. Please try again.");
        }
        
    }

}
