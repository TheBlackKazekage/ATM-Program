package bean;

import model.User;

public interface AtmI {
    boolean withdraw(User user, double amount);

    boolean deposit(User user, double amount);

    String checkBalance(User user);

}
