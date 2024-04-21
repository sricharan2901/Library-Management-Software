
import javax.swing.*;

import models.Borrowing;
import models.Slot;
import models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
  


public class ViewSlots extends JFrame{
    private JFrame frame;
    JTable j;
    
    private SQLConnection connection = new SQLConnection();

    public ViewSlots () {
        frame = new JFrame();
        frame.setTitle("Booked Slots");
        frame.getContentPane().setBackground(new Color(198, 179, 142));
        frame.setLayout(new BorderLayout());

		
        String[][] data = Slot.getBookedSlots(connection.statement);


	// Column Names
	String[] columnName = { "Book Name","Borrowing Date","Fine"};


	// Initializing  JTable
	    j = new JTable(data, columnName);
	    //j.setBounds(40, 250, 1200, 3200);
        j.setRowHeight(100);
        j.setFont(new Font("Sans Serif", Font.LAYOUT_LEFT_TO_RIGHT, 20));
	
	    frame.add(j);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setSize(1000, 500);



    }
}