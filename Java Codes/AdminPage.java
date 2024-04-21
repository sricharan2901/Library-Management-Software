import javax.swing.*;
import javax.swing.border.Border;

import com.mysql.cj.log.Log;

import models.Book;
import models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminPage extends JFrame{

    private JFrame frame;
    JButton viewhistory;
    
    private SQLConnection connection = new SQLConnection();


    public AdminPage(User user) {

        // Admin Page window

        Color BackgroundColor = new Color(198, 179, 142);

        frame = new JFrame();
        frame.setTitle("Admin Page");
        frame.getContentPane().setBackground(BackgroundColor);
        frame.setLayout(new BorderLayout(0, 100));

        JPanel titleBar = new JPanel();
        titleBar.setBackground(BackgroundColor);
        titleBar.setLayout(new BorderLayout());
        titleBar.setPreferredSize(new Dimension(0, 100));

        JLabel usernametitleLabel = new JLabel("Admin Library Page");
        usernametitleLabel.setFont(new Font("Sans Serif", Font.BOLD, 60));
        usernametitleLabel.setBounds(20, 10, 1000, 80); 


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(BackgroundColor);

        JButton viewhistory = new JButton("Upload a Book");
        viewhistory.setPreferredSize(new Dimension(200, 100));
        viewhistory.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 12));

        viewhistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UploadBook(user, frame);
            }
        });





        JButton study_room_booking = new JButton("View Slots Booked");
        study_room_booking.setPreferredSize(new Dimension(200, 100));
        study_room_booking.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 12));
        
        study_room_booking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewSlots();
            }
        });
        
        
        
        JButton view_due_books = new JButton("View Due Books");
        view_due_books.setPreferredSize(new Dimension(200, 100));
        view_due_books.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 12));
        
        view_due_books.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewDueBooks();
            }
        });



        buttonsPanel.add(viewhistory);
        buttonsPanel.add(study_room_booking);
        buttonsPanel.add(view_due_books);




        
        titleBar.add(usernametitleLabel, BorderLayout.WEST);
        titleBar.add(buttonsPanel, BorderLayout.EAST);



        
        //These are the panels for the books

        Color Border_color = new Color(61, 8, 20);

        Border blackline = BorderFactory.createLineBorder(Border_color, 5);

        Font head_font = new Font("Sans Serif", Font.BOLD, 20);
        Font deet_font = new Font("Verdana",2,17);
        
        

        ArrayList<Book> books = Book.getAllBooks(connection.statement);

        JPanel booksPanel = new JPanel();
        booksPanel.setBackground(BackgroundColor);
        booksPanel.setLayout(new GridLayout(3, 4, 20, 20));
        
        for (Book b : books) {
            
            JPanel panel_1 = new JPanel();
            panel_1.setBounds(new Rectangle(420, 100));
            panel_1.setLayout(new GridLayout(5, 1, 0, 0));
            panel_1.setBackground(new Color(168, 246, 255));
            JLabel p1_t1 = new JLabel(b.name, JLabel.CENTER);
            p1_t1.setFont(head_font);     
            JLabel p1_t2 = new JLabel(b.author, JLabel.CENTER);
            p1_t2.setFont(deet_font);
            JLabel p1_t3 = new JLabel(b.genre, JLabel.CENTER);
            p1_t3.setFont(deet_font);
            JLabel p1_t4 = new JLabel(String.valueOf(b.yop), JLabel.CENTER);
            p1_t4.setFont(deet_font);

            JButton borrow_button;
            if(b.link == null){
                borrow_button = new JButton("View Book History");
                borrow_button.addActionListener(new ActionListener() {
                    public void actionPerformed (ActionEvent e) {
                        new BookHistory(b);
                    }
                });
            }

            else{
                borrow_button = new JButton("Go to link");
                borrow_button.addActionListener( new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try {         
                              java.awt.Desktop.getDesktop().browse(java.net.URI.create(b.link));
                            }
                        catch (java.io.IOException e1) {
                                System.out.println(e1.getMessage());
                            }
                    }
                });
            }


            panel_1.add(p1_t1);
            panel_1.add(p1_t2);
            panel_1.add(p1_t3);
            panel_1.add(p1_t4);
            panel_1.add(borrow_button);
            panel_1.setBorder(blackline);
            booksPanel.add(panel_1);
        }
        
        
        frame.add(titleBar, BorderLayout.NORTH);
        frame.add(booksPanel);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);


        
    
    }  

}