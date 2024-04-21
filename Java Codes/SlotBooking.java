
import javax.swing.*;

import models.Slot;
import models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java. util.Date;
import java. text.SimpleDateFormat;  



public class SlotBooking extends JFrame{


    private JFrame frame;
    
    private SQLConnection connection = new SQLConnection();



    public SlotBooking(User user) {

        // Study Room Booking window

        frame = new JFrame();
        frame.setTitle("Study Room Booking Page");
        frame.getContentPane().setBackground(new Color(198, 179, 142));
        frame.setLayout(null);

        JLabel usernametitleLabel = new JLabel("Study Room Booking");
        usernametitleLabel.setFont(new Font("Sans Serif", Font.BOLD, 60));
        usernametitleLabel.setBounds(340, 10, 1000, 80); 

        // JButton bookissue = new JButton("Upload/Issue a book");
        // bookissue.setBounds(700, 25, 190, 50);
        // bookissue.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 12));

        JButton submit_booking = new JButton("Make Booking");
        submit_booking.setBounds(570,525,150,50);
        submit_booking.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 12));

        
        JLabel details = new JLabel("Enter the following details:");
        details.setFont(new Font("Sans Serif", Font.BOLD,30));
        details.setBounds(450, 180, 380, 40);
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        String str = formatter.format(date);
        
        int i=Integer.parseInt(str); 
        i+=1;
        String s=String.valueOf(i);  
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("/M/yyyy");
        String str1 = formatter1.format(date);
        
        
        JLabel next_date = new JLabel(s+str1, JLabel.CENTER);
        next_date.setFont(new Font("Sans Serif", Font.BOLD,25));
        next_date.setBounds(450, 290, 380, 40);
        
        
        
        JLabel hour1 = new JLabel("Time slot:  ");
        hour1.setFont(new Font("Sans Serif", Font.BOLD,25));
        hour1.setBounds(450, 390, 380, 40);
        
        String[] timeStrings = {"8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00",};
        JComboBox<String> cmbtimelist = new JComboBox<String>(timeStrings);
        cmbtimelist.setBounds(600, 390, 150, 40);
        cmbtimelist.setFont(new Font("Sans Serif", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        

        submit_booking.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                String time = timeStrings[cmbtimelist.getSelectedIndex()];

                if (!Slot.checkAvailability(date, time, connection.statement)) {
                    JOptionPane.showMessageDialog(null, "Slot already booked!");
                }
                else {
                    Slot.bookSlot(date, time, user, connection.statement);
                    frame.dispose();
                }
            }
        });


        frame.add(usernametitleLabel);
        frame.add(details);
        frame.add(submit_booking);
        frame.add(next_date);
        frame.add(hour1);

        frame.add(cmbtimelist);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);       
    
    }        

}