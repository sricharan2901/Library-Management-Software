package models;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public String id;
    public String username;
    public Role role;
    public Boolean found;

    User (Boolean found) {
        this.found = false;
    }

    User (ResultSet r) {
        try {
            id = r.getString("userid");
            username = r.getString("username");
            found = true;
            
            int rolename = r.getInt("role");
            if (rolename == 1)
                role = Role.STUDENT;
            else if (rolename == 0)
                role = Role.ADMIN;

            } catch (SQLException e) {
            found = false;
        }
    }

    public static User fromId (String id, Statement st) {
        try {
            ResultSet r = st.executeQuery("SELECT * FROM users");
            
            if (!r.next()) 
                return new User(false);

            return new User(r);

        } catch (SQLException e) {
            return new User(false);
        }
    }

    public static User fromUsernamePassword (String username, String password, Statement st) {
        try {
            ResultSet r = st.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");

            if (!r.next())
                return new User(false);

            return new User(r);
        } catch (SQLException e) {
            return new User(false);
        }
    }
}
