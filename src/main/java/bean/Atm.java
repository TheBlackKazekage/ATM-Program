package bean;

import DBConnection.DBConnection;
import model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Atm implements AtmI{
    @Inject
    DBConnection db;

    @Override
    public boolean withdraw(User user, double amount) {
        String phoneNo = user.getPhoneNo();
        String password = user.getPassword();
        String amt = db.getAmount(phoneNo, password);
        double balance = Double.parseDouble(amt);
        if(amount > balance){
            return false;
        }

        double newBalance = balance - amount;
        boolean status = db.updateAmount(String.valueOf(newBalance), phoneNo, password);
        return status;
    }

    @Override
    public boolean deposit(User user, double amount) {
        String phoneNo = user.getPhoneNo();
        String password = user.getPassword();
        String amt = db.getAmount(phoneNo, password);
        double balance = Double.parseDouble(amt);

        double newBalance = balance - amount;
        boolean status = db.updateAmount(String.valueOf(newBalance), phoneNo, password);
        return status;
    }

    @Override
    public String checkBalance(User user) {
        db.getConnection();
        String phone = user.getPhoneNo();
        String password = user.getPassword();
        if(db.getAmount(phone, password) != null)return db.getAmount(phone, password);

        return null;
    }
}
