package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class Book {
    public int id;
    public String name;
    public String author;
    public String genre;
    public int yop;
    public Boolean available;
    public String link;

    Book (ResultSet r) {
        try {
            id = r.getInt("bookid");
            name = r.getString("bookname");
            author = r.getString("author");
            genre = r.getString("genre");
            yop = r.getInt("yop");
            available = r.getBoolean("available");
            link = r.getString("link");
        } catch (SQLException e) {}
    }

    public void borrow (User user, Statement st) {
        try {
            LocalDate now = LocalDate.now();
            st.executeUpdate(String.format("INSERT INTO borrowing VALUES ('%s', %d, '%s', NULL)", user.id, id, now.toString()));
            st.executeUpdate(String.format("UPDATE books SET available = false WHERE bookid = %d", id));
        } catch (SQLException e) {}
    }


    public static ArrayList<Book> search (String searchString, Statement st) {
        try {
            ResultSet r = st.executeQuery("SELECT * FROM books WHERE (genre LIKE '%" + searchString + "%' OR bookname LIKE '%" + searchString + "%') AND available = 1");
            ArrayList<Book> books = new ArrayList<Book>();

            while (r.next()) {
                books.add(new Book(r));
            }

            return books;
        } catch (SQLException e) {
            System.out.println(e);
            return new ArrayList<Book>();
        }
    }


    public static ArrayList<Book> getAllBooks (Statement st) {
    try {
        ResultSet r = st.executeQuery("SELECT * FROM books");
        ArrayList<Book> books = new ArrayList<Book>();

        while (r.next()) {
            books.add(new Book(r));
        }

        return books;
    } catch (SQLException e) {
        System.out.println(e);
        return new ArrayList<Book>();
    }
    }
}
