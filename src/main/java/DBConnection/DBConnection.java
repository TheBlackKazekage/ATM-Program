package DBConnection;

import model.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.sql.*;

@RequestScoped
public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/atm?useSSL=false";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "password";

    @Inject
    Connection conn;

    @Produces
    @RequestScoped
    public Connection getConnection(){
        try{
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
        }

        return null;
    }

    public boolean registerUser(User u){
        String query = "INSERT INTO users VALUES(?,?,?,?,?,?)";

        try{
            conn.setAutoCommit(false);
            PreparedStatement statement1 = conn.prepareStatement(query);


            statement1.setString(1, u.getFirstName());
            statement1.setString(2, u.getLastName());
            statement1.setString(3, u.getPassword());
            statement1.setString(4, u.getPhoneNo());
            statement1.setString(5, u.getEmail());
            statement1.setString(6, u.getAmt());

            int i = statement1.executeUpdate();

            if(i > 0){
                conn.commit();
                return true;
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return false;
    }

    public boolean loginUser(String phoneNo, String password){
        String query = "SELECT * from users WHERE phoneNo = ? and password = ?";

        try{

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, phoneNo);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getAmount(String phoneNo, String password){
        String query = "SELECT * from users WHERE phoneNo = ? and password = ?";

        try{

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, phoneNo);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("amt"));
                return rs.getString("amt");
            }

//            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateAmount(String amount, String phoneNo, String password){
        String query = "UPDATE users SET amt = ? WHERE phoneNo = ? AND password = ?";

        try{
            PreparedStatement stm = conn.prepareStatement(query);

            stm.setString(1, amount);
            stm.setString(2, phoneNo);
            stm.setString(3, password);

            boolean i = stm.execute();
            if(i){
                conn.commit();
                return true;
            }

//            conn.close();
        } catch (SQLException e) {
            e.getMessage();
        }

        return false;
    }

    public User getUser(String phoneNo, String password){
        String query = "SELECT * from users WHERE phoneNo = ? and password = ?";

        try{

            User user;

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, phoneNo);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String fname = rs.getString("firstName");
                String lname = rs.getString("lastName");
                String pword = rs.getString("password");
                String phone = rs.getString("phoneNo");
                String email = rs.getString("email");
                String amt = rs.getString("amt");

                user = new User(fname, lname, pword, phone, email, amt);

                return user;
            }

//            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
