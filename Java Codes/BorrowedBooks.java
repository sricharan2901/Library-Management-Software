
import javax.swing.*;

import models.Borrowing;
import models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
  


public class BorrowedBooks extends JFrame{
    private JFrame frame;
    JTable j;
    
    private SQLConnection connection = new SQLConnection();

    public BorrowedBooks (User user, JFrame parentFrame) {
        frame = new JFrame();
        frame.setTitle("Book History");
        frame.getContentPane().setBackground(new Color(198, 179, 142));
        frame.setLayout(new BorderLayout());

        // JLabel book_hisory = new JLabel("Book History");
        // book_hisory.setFont(new Font("Sans Serif", Font.BOLD, 60));
        // book_hisory.setBounds(460, 10, 1000, 80);

		ArrayList<Borrowing> borrowings = Borrowing.currentlyBorrowedBooks(user, connection.statement);
        String[][] data = Borrowing.getTableData(borrowings);
        String[] dropdownStrings = Borrowing.getDropDownData(borrowings);

        JButton returnbook = new JButton("Return book");
        //returnbook.setBounds(20, 100, 190, 30);
        returnbook.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        //returnbook.setMaximumSize(new Dimension(200, 80));

        
        JComboBox<String> cmbnamelist = new JComboBox<String>(dropdownStrings);
        cmbnamelist.setBounds(5, 40, 40, 10);
        cmbnamelist.setFont(new Font("Sans Serif", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        
        returnbook.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                int option = cmbnamelist.getSelectedIndex();
                borrowings.get(option).return_book(connection.statement);
                frame.dispose();
                parentFrame.dispose();
                new StudentPage(user, "");
            }
        });


       // Adding Data to be displayed 

	// Column Names
	String[] columnName = { "Book Name","Borrowing Date","Fine"};

        JPanel something = new JPanel();
        something.add(cmbnamelist);
        something.add(returnbook);

	// Initializing  JTable
	    j = new JTable(data, columnName);
	    //j.setBounds(40, 250, 1200, 3200);
        j.setRowHeight(100);
        j.setFont(new Font("Sans Serif", Font.LAYOUT_LEFT_TO_RIGHT, 20));
	
	    frame.add(j);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setSize(1000, 500);
        // frame.add(returnbook,BorderLayout.SOUTH);
        // frame.add(cmbnamelist,BorderLayout.SOUTH);
        frame.add(something,BorderLayout.SOUTH);



    }
}

// Example of JTable in Java
