import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class  InitSQL{



    //Main function in the class
    public static void main(String[] args) {
       
        String url = "jdbc:mysql://localhost:3306/proj";
        String username = "root";
        String password = "chinnu";

        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            //Establishing connection
            Statement statement = connection.createStatement();

            //Creating Tables for functionality
             statement.executeUpdate("DROP TABLE IF EXISTS users");
             String query1 = "CREATE TABLE users"+"( userid CHAR(8) PRIMARY KEY, username VARCHAR(255), role int(1), password VARCHAR(255) )";
             statement.executeUpdate(query1);
             statement.executeUpdate("DROP TABLE IF EXISTS books");
             query1 = "CREATE TABLE books"+"(bookid INT(4) PRIMARY KEY AUTO_INCREMENT, bookname VARCHAR(255), author VARCHAR(255), genre VARCHAR(255), yop INT(4), available bool, link VARCHAR(255) )";
             statement.executeUpdate(query1);
             statement.executeUpdate("DROP TABLE IF EXISTS borrowing");
             query1 = "CREATE TABLE borrowing (user_id CHAR(8), book_id INT(4), borrowing_date DATE, return_date DATE)";
             statement.executeUpdate(query1);
             statement.executeUpdate("DROP TABLE IF EXISTS slots");
             query1 = "CREATE TABLE slots (user_id CHAR(8), date DATE, time VARCHAR(5))";
             statement.executeUpdate(query1);


            //Inserting Data into the table
             query1 = "INSERT INTO users " + "VALUES ('221IT066', 'Sricharan', 1, 'charan')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO users " + "VALUES ('221IT038', 'Kevin', 1, 'kevin')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO users " + "VALUES ('221IT031', 'Hariharan', 1, 'hari')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO users " + "VALUES ('201IT012', 'Kiran', 0 , 'kiran')";
             statement.executeUpdate(query1);
             
             query1 = "INSERT INTO books (bookname, author, genre, yop, available, link) " + "VALUES ('Dune', 'Frank Herbert', 'Fiction', 1965, true , NULL)";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO books (bookname, author, genre, yop, available, link) " + "VALUES ('Angels and Demons', 'Dan Brown', 'Thriller', 2000, true ,'https://ia902906.us.archive.org/12/items/angels-demons-dan-brown/Angels%20%26%20Demons%20-%20Dan%20Brown.pdf')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO books (bookname, author, genre, yop, available, link) " + "VALUES ('Death on the Nile', 'Agatha Christie', 'Mystery', 1937, true, NULL )";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO books (bookname, author, genre, yop, available, link) " + "VALUES ('The Alchemist', 'Paulo Coelho', 'Fiction', 1988, false, NULL )";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO books (bookname, author, genre, yop, available, link) " + "VALUES ('The Hound of the Baskervilles','Arthur Conan Doyle', 'Mystery', 1902, true,NULL)";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO books (bookname, author, genre, yop, available, link) " + "VALUES ('Effective Java', 'Joshua Bloch', 'JAVA coding', 2001, true ,'https://kea.nu/files/textbooks/new/Effective%20Java%20%282017%2C%20Addison-Wesley%29.pdf')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO books (bookname, author, genre, yop, available, link) " + "VALUES ('Engineering Chemistry', 'A.Nithyananda Shetty', 'Chemistry', 2010, false ,NULL)";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO books (bookname, author, genre, yop, available, link) " + "VALUES ('Thomas Calculus (14th edition)', 'George.B.Thomas', 'Mathematics', 2018, true,'http://dl.konkur.in/post/Book/Paye/Thomas-Calculus-14th-Edition-%5Bkonkur.in%5D.pdf' )";
             statement.executeUpdate(query1);


             query1 = "INSERT INTO borrowing VALUES('221IT066', 1, '2023-06-10', '2023-06-20')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO borrowing VALUES('221IT031', 2, '2023-06-10', '2023-06-19')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO borrowing VALUES('221IT031', 3, '2023-06-09', NULL)";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO borrowing VALUES('221IT038', 2, '2023-06-20', NULL)";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO borrowing VALUES('221IT066', 3, '2023-05-15', '2023-06-02')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO borrowing VALUES('221IT038', 4, '2023-06-05', '2023-06-08')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO borrowing VALUES('221IT031', 5, '2023-05-05', '2023-05-23')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO borrowing VALUES('221IT038', 6, '2023-06-09', '2023-06-19')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO borrowing VALUES('221IT066', 7, '2023-06-21', NULL)";
             statement.executeUpdate(query1);
             
             
             
             query1 = "INSERT INTO slots VALUES('221IT031', '2023-06-20', '9:00')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO slots VALUES('221IT066', '2023-06-18', '13:00')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO slots VALUES('221IT038', '2023-05-15', '17:00')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO slots VALUES('221IT066', '2023-06-28', '10:00')";
             statement.executeUpdate(query1);
             query1 = "INSERT INTO slots VALUES('221IT031', '2023-06-29', '15:00')";
             statement.executeUpdate(query1);
            
            
            // ResultSet resultSet = statement.executeQuery("SELECT * FROM emp");

            
            // while (resultSet.next()) {
            //     User u1 = new User(resultSet);
            //     System.out.println("ID: " + u1.id + ", Name: " + u1.username);
            // }

            
            //resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

