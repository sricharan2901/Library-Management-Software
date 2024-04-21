package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Slot {
    String user_id;
    User user;
    Date date;
    String time;

    Slot (ResultSet r) {
        try {
            user_id = r.getString("user_id");
            date = r.getDate("date");
            time = r.getString("time");

            user = new User(r);

        } catch (SQLException e) {}
    }

    public static Boolean checkAvailability (Date date, String time, Statement st) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
            ResultSet r = st.executeQuery(String.format("SELECT count(*) AS c FROM slots WHERE date = '%s' AND time = '%s'", df.format(date), time));
            r.next();
            if (r.getInt("c") == 0) return true;
            else return false;
        } catch (SQLException e) { return false; }
    }


    public static void bookSlot (Date date, String time, User user, Statement st) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
            st.executeUpdate(String.format("INSERT INTO slots VALUES('%s', '%s', '%s')", user.id, df.format(date), time));
        } catch (SQLException e) {}
    }

    public static String[][] getBookedSlots (Statement st) {
        try {
            ResultSet r = st.executeQuery(String.format("SELECT * FROM slots, users WHERE userid = user_id AND date >= CURRENT_DATE()"));
            ArrayList<String[]> l = new ArrayList<String[]>();

            while (r.next()) {
                Slot s = new Slot(r);
                String[] strings = {s.user.username, s.date.toString(), s.time};
                l.add(strings);
            }

            String[][] data = new String[l.size()][];
            l.toArray(data);
            return data;
        } catch (SQLException e) {
            return new String[0][];
        }
    }
}
