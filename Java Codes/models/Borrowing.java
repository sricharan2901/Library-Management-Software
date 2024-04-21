package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Borrowing {
    public String user_id;
    public int book_id;
    public Book book;
    public User user;

    public Date borrow_date;
    public Date return_date;
    public Boolean returned;
    public int late_by;
    public int fine;

    Borrowing (ResultSet r) {
        try {
            user_id = r.getString("user_id");
            book_id = r.getInt("book_id");
            borrow_date = r.getDate("borrowing_date");
            return_date = r.getDate("return_date");

            user = new User(r);
            book = new Book(r);

            if (return_date == null) {
                returned = false;
                return_date = new Date();
            }
            else
                returned = true;

            late_by = (int) (return_date.getTime() - borrow_date.getTime()) / 86400000;

            if (late_by <= 14) late_by = 0;
            else late_by -= 14;

            fine = late_by * 10;
        } catch (SQLException e) {
        }

    }


    public void return_book (Statement st) {
        try {
            LocalDate now = LocalDate.now();

            String query1 = String.format("UPDATE borrowing SET return_date = '%s' WHERE user_id = '%s' AND book_id = %d AND borrowing_date = '%s'", now.toString(), user.id, book.id, borrow_date.toString());
            System.out.println(query1);

            st.executeUpdate(query1);
            st.executeUpdate(String.format("UPDATE books SET available = true WHERE bookid = %d", book.id));
        } catch (SQLException e) {}
    }



    public static ArrayList<Borrowing> currentlyBorrowedBooks (User user, Statement st) {
        try {
            ResultSet r;
            if (user == null) 
                r = st.executeQuery("SELECT * FROM users, borrowing, books WHERE user_id = userid AND bookid = book_id AND return_date IS NULL");
            else
                r = st.executeQuery("SELECT * FROM users, borrowing, books WHERE user_id = userid AND bookid = book_id AND return_date IS NULL AND userid = '" + user.id + "'");
 
            ArrayList<Borrowing> borrowings = new ArrayList<Borrowing>();

            while (r.next()) {
                borrowings.add(new Borrowing(r));
            }

            return borrowings;
        } catch (SQLException e) { 
            return new ArrayList<Borrowing>();
        }
    }



    public static String[][] getDueBooks (Statement st) {
        try {
            ResultSet r = st.executeQuery("SELECT * FROM users, borrowing, books WHERE user_id = userid AND bookid = book_id AND return_date IS NULL");
            ArrayList<String[]> l = new ArrayList<String[]>();

            while (r.next()) {
                Borrowing b = new Borrowing(r);
                Calendar cal = Calendar.getInstance();
                cal.setTime(b.borrow_date);
                cal.add(Calendar.DAY_OF_MONTH, 14);
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                String[] strings = {b.user.username, b.book.name, b.borrow_date.toString(), df.format(cal.getTime()), String.format("Rs. %s (late by %d days)", b.fine != 0 ? String.valueOf(b.fine) : "--", b.late_by)};
                l.add(strings);
            }

            String[][] data = new String[l.size()][];
            l.toArray(data);
            return data;            
        } catch (SQLException e) {
            return new String[0][];
        }
    }




    public static String[][] getHistory (int book_id, Statement st) {
        try {
            ResultSet r = st.executeQuery("SELECT * FROM users, borrowing, books WHERE user_id = userid AND bookid = book_id AND bookid = " + book_id);
            ArrayList<String[]> history = new ArrayList<String[]>();

            while (r.next()) {
                Borrowing b = new Borrowing(r);
                String[] strings = {b.user.username, b.borrow_date.toString(), b.returned ? b.return_date.toString() : "Due"};
                history.add(strings);
            }

            String[][] data = new String[history.size()][];
            history.toArray(data);
            return data;
        } catch (SQLException e) {
            return new String[0][];
        }

    }

    public static String[][] getTableData (ArrayList<Borrowing> borrowings) {
        ArrayList<String[]> l = new ArrayList<String[]>();

        for (Borrowing b : borrowings) {
            String[] strings = {b.book.name, b.borrow_date.toString(), String.format("Rs. %s (late by %d days)", b.fine != 0 ? String.valueOf(b.fine) : "--", b.late_by)};
            l.add(strings);
        }

        String[][] data = new String[l.size()][3];
        l.toArray(data);
        return data;
    }

    public static String[] getDropDownData (ArrayList<Borrowing> borrowings) {
        ArrayList<String> l = new ArrayList<String>();

        for (Borrowing b : borrowings) {
            l.add(b.book.name);
        }

        String[] data = new String[l.size()];
        l.toArray(data);
        return data;
    }
}
