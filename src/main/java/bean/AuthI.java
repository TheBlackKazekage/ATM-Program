package bean;

import model.User;

public interface AuthI {
    boolean login(String phoneNo, String password);

    boolean register(User user);
}
