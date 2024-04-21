import javax.swing.*;

import models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UploadBook extends JFrame{

    JFrame frame = new JFrame();

    SQLConnection connection = new SQLConnection();

    public UploadBook (User user, JFrame parentFrame){

        frame = new JFrame();
        frame.setTitle("Upload Book");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setLayout(null);

        // Create username label and text field
        JLabel uploadtitletitleLabel = new JLabel("Book Uploading Software");
        uploadtitletitleLabel.setFont(new Font("Sans Serif", Font.BOLD, 60));
        uploadtitletitleLabel.setBounds(250, 10, 2000, 70);

        JLabel bookname = new JLabel("Enter the name of the book:");
        bookname.setFont(new Font("Sans Serif", 0, 20));
        bookname.setBounds(260, 110, 300, 70);

        JTextField book_name = new JTextField();
        book_name.setFont(new Font("Sans Serif", 0, 15));
        book_name.setBounds(560, 120, 400, 50);

        JLabel authorname = new JLabel("Enter the name of the author:");
        authorname.setFont(new Font("Sans Serif", 0, 20));
        authorname.setBounds(260, 220, 300, 70);

        JTextField author_name = new JTextField();
        author_name.setFont(new Font("Sans Serif", 0, 15));
        author_name.setBounds(560, 230, 400, 50);

        JLabel genrename = new JLabel("Enter the name of the genre:");
        genrename.setFont(new Font("Sans Serif", 0, 20));
        genrename.setBounds(260, 330, 300, 70);

        JTextField genre_name = new JTextField();
        genre_name.setFont(new Font("Sans Serif", 0, 15));
        genre_name.setBounds(560, 340, 400, 50);

        JLabel yearname = new JLabel("Enter the year of release:");
        yearname.setFont(new Font("Sans Serif", 0, 20));
        yearname.setBounds(260, 440, 300, 70);

        JTextField year_name = new JTextField();
        year_name.setFont(new Font("Sans Serif", 0, 15));
        year_name.setBounds(560, 450, 400, 50);

        JButton submit = new JButton("submit");
        submit.setFont(new Font("Sans Serif", 0, 15));
        submit.setBounds(500, 550, 250, 50);


        submit.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                String name = book_name.getText();
                String author = author_name.getText();
                String genre = genre_name.getText();
                String yop = year_name.getText();

                try{
                    connection.statement.executeUpdate(String.format("INSERT INTO books (bookname, author, genre, yop, available, link) VALUES('%s', '%s', '%s', %s, true, NULL)", name, author, genre, yop));
                    frame.dispose();
                    parentFrame.dispose();

                    new AdminPage(user);
                } catch (SQLException f) {}
            }
        });


                


        frame.add(uploadtitletitleLabel);
        frame.add(bookname);
        frame.add(book_name);
        frame.add(authorname);
        frame.add(author_name);
        frame.add(genre_name);
        frame.add(genrename);
        frame.add(year_name);
        frame.add(yearname);
        frame.add(submit);


        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
