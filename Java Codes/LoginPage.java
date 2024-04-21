
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

import models.Role;
import models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginPage extends JFrame{

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame frame;

    private SQLConnection connection = new SQLConnection();


    public LoginPage () {

        // Login window

        frame = new JFrame();
        frame.setTitle("Login");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setLayout(null);

        // Create username label and text field
        JLabel usernametitleLabel = new JLabel("Library Application Software");
        usernametitleLabel.setFont(new Font("Sans Serif", Font.BOLD, 60));
        usernametitleLabel.setBounds(250, 10, 2000, 70); 

        JLabel loginpage = new JLabel("Login to the page");
        loginpage.setFont(new Font("Sans Serif", Font.BOLD, 35));
        loginpage.setBounds(500, 220, 2000, 50);


        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Sans Serif", Font.BOLD, 25));
        usernameLabel.setBounds(450, 300, 2000, 50);


        //usernameLabel.setBounds();
        usernameField = new JTextField(20);
        usernameField.setBounds(600,300,200,40);
        usernameField.setFont(new Font("Sans Serif", Font.LAYOUT_LEFT_TO_RIGHT, 20));

        // Create password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(450,350,200,40);
        passwordLabel.setFont(new Font("Sans Serif", Font.BOLD, 25));

        passwordField = new JPasswordField(20);
        passwordField.setBounds(600,350,200,40);
        passwordField.setFont(new Font("Sans Serif", Font.LAYOUT_LEFT_TO_RIGHT, 20));


        // Create submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(575, 450, 150, 40);
        submitButton.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 25));

        // Add components to the window
        frame.add(usernametitleLabel);
        frame.add(loginpage);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(submitButton);

        // Add action listener to submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                User user = User.fromUsernamePassword(username, password, connection.statement);

                if (user.found) {

                    connection.close();
                    frame.dispose();

                    if(user.role==Role.ADMIN){
                        new AdminPage(user);
                    }
                    else{
                        new StudentPage(user, "");
                    }
                }

                else 
                {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }

                submitButton.addActionListener(this);
            }
        });

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main (String[] args) {
        new LoginPage();
    }
}

