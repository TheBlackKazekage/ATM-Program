package bean;

import DBConnection.DBConnection;
import model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Auth implements AuthI{
    @Inject
    DBConnection db;

    @Override
    public boolean login(String phoneNo, String password) {
        boolean status = db.loginUser(phoneNo, password);

        return status;
    }

    @Override
    public boolean register(User user) {
        return false;
    }
}
