/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;


import com.mysql.cj.util.StringUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author great_000
 */
public class Database {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
    private Connection connection;
    private PreparedStatement pstLogin;
    private PreparedStatement pstSignup;
    private PreparedStatement pstInsertBooks;
    private PreparedStatement searchBooks;
    private PreparedStatement borrowBooks;
    private PreparedStatement selectBorrowed;
    private PreparedStatement pstDeleteBooks;
    private PreparedStatement updateQuantityBooks; 
    private PreparedStatement pstRenewBooks;
    private PreparedStatement pstMyAccount;
    private PreparedStatement pstAccountSettings;
    private PreparedStatement tableBooks;
    private PreparedStatement searchBooksAdmin;
    private PreparedStatement addBooks;
    private PreparedStatement borrowBooksNew;

    public static int test =0;
    String[] bookName = new String[10];
    String[] bookNumber= new String[10];
    String book;
    String bookn;
    private String wrongInfoMessage = "<html> <h3> Wrong LRN or Password. </h3> <br>Contact the librarian if you <br> forgotten your account <br >information  </html> ";
    MouseHandler mHandler;

    
    
    //my account details
    public Connection openConnection(JLabel myLrn,JLabel myName,JLabel myGradeLevel,
            JLabel mySection,JTextArea myAddress,JLabel myGender,JLabel myMobileNumber,
            int currentLrn,  ImageIcon icon, JLabel imageContainer){
         
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "Select * from finalusers where lrn=?";
            try {
                
                Class.forName(driver);
                this.connection =DriverManager.getConnection(url 
                            + dbName, userName , password);
                pstMyAccount = connection.prepareStatement(sql);
                pstMyAccount.setInt(1, currentLrn);
                ResultSet rs =  pstMyAccount.executeQuery();
                
                if(rs.next()){
                    myLrn.setText("LRN: " +rs.getString("lrn"));  
                    myName.setText("Full Name: " +rs.getString("first name") + " " + 
                            rs.getString("middle initial") + ". " + 
                            rs.getString("last name"));
                    myGradeLevel.setText("Grade Level: "+rs.getString("grade level"));
                    mySection.setText("Section: "+rs.getString("section"));
                    myAddress.setText("Address: "+rs.getString("address"));
                    myGender.setText("Gender: "+rs.getString("gender"));
                    myMobileNumber.setText("Mobile Number: "+rs.getString("mobile number"));
                    icon = new ImageIcon("C:\\Users\\great_000\\Documents\\"
                        + "NetBeansProjects\\LibrarySystem\\src\\Images\\" +
                        rs.getString("student number") +".jpg");
                    Image edit = icon.getImage();
                    Image edit2 = edit.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(edit2);
                    imageContainer.setIcon(icon);
                }
                
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
                
            }finally{
                try {
                    connection.close();
                    pstMyAccount.close();
                } catch (SQLException e) {
                }
            }   
        return connection;
    }
    
    //my account settings
    public Connection openConnection(JButton changeGender, AtomicInteger lrn, int cases,
            JButton changeLRN, JPanel container, String allLrn[], JButton changeName,
            JButton changeGradeLevel, JButton changeSection, JButton changeNumber,
            JButton changeAdress, JLabel myGender, JLabel myLrn, JLabel myName,
            JLabel myGradeLevel,JLabel mySection,JTextArea myAddress,
            JLabel myMobileNumber,JButton changePass){
         
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sqlGender = "UPDATE `finalusers` SET `gender`=? WHERE lrn=?";
            String sqlLrn = "UPDATE `finalusers` SET `lrn` =? WHERE lrn=? ";
            String sqlLrnBorrow = "UPDATE `finalborrowedbook` SET `userslrn`=?  WHERE userslrn=? ";
            String sqlName = "UPDATE `finalusers` SET `first name`=? ,`last name`=?,`middle initial`=? WHERE lrn=? ";
            String sqlGradeLevel = "UPDATE `finalusers` SET `grade level`=? WHERE lrn=? ";
            String sqlSection = "UPDATE `finalusers` SET `section`=? WHERE lrn=? ";
            String sqlAddress = "UPDATE `finalusers` SET `address`=? WHERE lrn=? ";
            String sqlNumber = "UPDATE `finalusers` SET `mobile number`=? WHERE lrn=? ";
            String sqlPass =  "UPDATE `finalusers` SET `password`=?,`confirm password`=? WHERE lrn=? ";
            try {
                
                Class.forName(driver);
                this.connection =DriverManager.getConnection(url 
                            + dbName, userName , password);
                
                switch(cases){
                    case 1://gender 
                        try {
                            
                    //gender
                    try (PreparedStatement accountGender = 
                            connection.prepareStatement(sqlGender)) {
                        //gender
                        String[] gender ={"Male", "Female"};
                        JComboBox genderBox = new JComboBox(gender);
                        int choice = JOptionPane.showConfirmDialog(null, genderBox, "New Gender",
                                JOptionPane.OK_CANCEL_OPTION);
                        if(choice == 0){
                            accountGender.setString(1, genderBox.getSelectedItem().toString());
                        accountGender.setInt(2, lrn.intValue());
                        accountGender.executeUpdate();
                        JOptionPane.showMessageDialog(container, "Gender updated to `"+
                                genderBox.getSelectedItem().toString() + "`"
                                , "Update Succes", JOptionPane.PLAIN_MESSAGE);
                        myGender.setText("Gender: " + genderBox.getSelectedItem().toString());
                        }
                        
                        }
                        } catch (HeadlessException | SQLException e) {
                            System.out.println(e.getMessage());
                        
                        }
                        break;
                    case 2: //lrn
                        try(PreparedStatement accountLrn =
                                connection.prepareStatement(sqlLrn) ; PreparedStatement accountBorrowLrn =
                                    connection.prepareStatement(sqlLrnBorrow)) {           
                            String s = JOptionPane.showInputDialog(null, "New LRN", lrn);
                            if(!s.isEmpty() && s.length() == 5 && 
                                    StringUtils.isStrictlyNumeric(s)){
                                
                                if(!Arrays.asList(allLrn).contains(s)){
                                accountLrn.setString(1, s);
                                accountLrn.setInt(2, lrn.intValue());
                                accountBorrowLrn.setString(1, s);
                                accountBorrowLrn.setInt(2, lrn.intValue());
                                accountBorrowLrn.executeUpdate();
                                accountLrn.executeUpdate();
                                lrn.compareAndSet(lrn.intValue(), Integer.parseInt(s));
                                JOptionPane.showMessageDialog(container, "Your lrn "
                                        + "has been changed to `" + s + "`.", 
                                        "Update Succes", JOptionPane.PLAIN_MESSAGE);
                                myLrn.setText("LRN: " + s);
                                }else{
                                    JOptionPane.showMessageDialog(container,"`" + 
                                            s + "`. LRN is used by other user. "
                                            + "Contact the librarian for this "
                                                    + "problem.", "Invalid input",
                                        JOptionPane.ERROR_MESSAGE);
                                }
                                
                            }else {
                                JOptionPane.showMessageDialog(container, "LRN should be 5 numeric "
                                        + "numbers only", "Invalid input",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            
                        } catch (Exception e) {
                           
                        }
                        break;
                    //account name    
                    case 3:    
                        try (PreparedStatement accountName =
                                connection.prepareStatement(sqlName)){
                           String fName = JOptionPane.showInputDialog(container, "First Name", "First Name");
                           if(!fName.isEmpty()){
                           String lName = JOptionPane.showInputDialog(container, "Last Name", "Last Name");
                           if(!lName.isEmpty()){
                           String alphabet[] = {
                               "A","B", "C","D","E","F", "G","H","I","J", "K","L","M","N", 
                               "O","P","Q","R", "S","T","U","V", "W","X","Y", "Z", "Ñ"
                           };
                           JComboBox mBox = new JComboBox(alphabet);
                           JOptionPane.showMessageDialog(container, mBox, "Middle "
                                   + "Initial", JOptionPane.PLAIN_MESSAGE);
                           accountName.setString(1, fName);
                           accountName.setString(2, lName);
                           accountName.setString(3, mBox.getSelectedItem().toString());
                           accountName.setInt(4, lrn.intValue());
                           accountName.executeUpdate();
                           JOptionPane.showMessageDialog(container, "Name has been "
                                   + "changed to `" + fName +" " + mBox.getSelectedItem().toString()+". " +
                                   lName + "`", "Update Success", JOptionPane.INFORMATION_MESSAGE);
                           myName.setText("Full Name: " + fName + " " + mBox.getSelectedItem().toString()+". " +
                                   lName);
                            }
                           }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    //account grade level
                    case 4:
                        try (PreparedStatement accountGradeLevel=
                                connection.prepareStatement(sqlGradeLevel)){
                            String gradeLevel[] = {"Grade 11", "Grade 12"};
                            JComboBox gradeBox = new JComboBox(gradeLevel);
                            int choice =JOptionPane.showConfirmDialog(container, gradeBox,
                                    "Grade Level", JOptionPane.OK_CANCEL_OPTION);
                            if(choice == 0){
                                accountGradeLevel.setString(1, gradeBox.getSelectedItem().toString());
                                accountGradeLevel.setInt(2, lrn.intValue());
                                accountGradeLevel.executeUpdate();
                                JOptionPane.showMessageDialog(container, "Your new "
                                        + "grade level is `" + gradeBox.getSelectedItem().toString() 
                                        + "`.", "Update Success", JOptionPane.INFORMATION_MESSAGE);
                                myGradeLevel.setText("Grade Level: " + gradeBox.getSelectedItem().toString());
                            }
                            
                        } catch (Exception e) {
                        }
                        break;
                    //account section
                    case 5:
                        try (PreparedStatement accountSection =
                                connection.prepareStatement(sqlSection)){
                            String sections11[] = {"Kamyas", "Bayabas", "Atis"};
                            String sections12[] = {"Aso", "Pusa", "Daga"};
                            JComboBox sectionBox = new JComboBox();
                            if("Grade Level: Grade 12".equals(myGradeLevel.getText())){
                                sectionBox.setModel(new DefaultComboBoxModel(sections12));
                            }else if("Grade Level: Grade 11".equals(myGradeLevel.getText())){
                                sectionBox.setModel(new DefaultComboBoxModel(sections11));
                            }
                            int choice = JOptionPane.showConfirmDialog(container,
                                    sectionBox, "New Section", JOptionPane.OK_CANCEL_OPTION);
                            if(choice ==0){
                                accountSection.setString(1, sectionBox.getSelectedItem().toString());
                                accountSection.setInt(2, lrn.intValue());
                                accountSection.executeUpdate();
                                JOptionPane.showMessageDialog(container, 
                                        "Your new section is `"+ sectionBox.getSelectedItem().toString(),
                                        "Update Success", JOptionPane.INFORMATION_MESSAGE);
                                mySection.setText("Section: "+  sectionBox.getSelectedItem().toString());
                            }
                            
                        } catch (Exception e) {
                        }
                        break;
                    //account mobile number
                    case 6: 
                        try (PreparedStatement accountNumber =
                                connection.prepareStatement(sqlNumber)){
                            String newNumber = JOptionPane.showInputDialog(container,
                                    "Update Mobile Number", "09");
                            if(!newNumber.isEmpty() ){
                                if(StringUtils.isStrictlyNumeric(newNumber)){
                                    if(newNumber.length() == 11){
                                accountNumber.setString(1, newNumber);
                                accountNumber.setInt(2, lrn.intValue());
                                accountNumber.executeUpdate();
                                JOptionPane.showMessageDialog(container, "Your new "
                                        + "number is `" + newNumber+
                                        "`.", "Update Success", JOptionPane.INFORMATION_MESSAGE);
                                myMobileNumber.setText("Mobile Number: "+ newNumber);
                                    }else{
                                        JOptionPane.showMessageDialog(container, "Mobile Number "
                                            + "should be 11 digit.", "Update fail;",
                                            JOptionPane.WARNING_MESSAGE);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(container, "Mobile Number "
                                            + "should be NUMERIC characters.", "Update fail;",
                                            JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        } catch (Exception e) {
                        }
                        break;
                    //account pass
                    case 7:
                        try (PreparedStatement accPass = 
                                connection.prepareStatement(sqlPass)){
                           JPasswordField pass1 = new JPasswordField();
                           JPasswordField pass2 = new JPasswordField();
                           int choice;
                           choice =JOptionPane.showConfirmDialog(container, pass1,
                                   "New Password", JOptionPane.OK_CANCEL_OPTION);
                           if(choice == 0){
                               if(!pass1.getText().isEmpty()){
                                  
                                   int choice2 = JOptionPane.showConfirmDialog(container, pass2,
                                   "Confirm Password", JOptionPane.OK_CANCEL_OPTION);
                                if(choice2 == 0){
                                    if(pass2.getText().equals(pass1.getText())){
                                        
                                        accPass.setString(1, pass2.getText());
                                        accPass.setString(2, pass2.getText());
                                        accPass.setInt(3, lrn.intValue());
                                        accPass.executeUpdate();
                                        JOptionPane.showMessageDialog(container,
                                                "Password Update Success", "Update Succes"
                                                , JOptionPane.INFORMATION_MESSAGE);
                                    }else{
                                        JOptionPane.showMessageDialog(container, 
                                               "Password not match.", 
                                                "Update Fail", JOptionPane.WARNING_MESSAGE);
                                       
                                    }  
                                  }   
                                   
                               }else{
                                   JOptionPane.showMessageDialog(container, "Password "
                                           + "can not be empty.", "Update Fail", JOptionPane.WARNING_MESSAGE);
                               }
                           }
                            
                        } catch (Exception e) {
                        }
                        
                        break;
                    //account address
                    case 8:
                        try (PreparedStatement accountAddress =
                                connection.prepareStatement(sqlAddress)){
                            
                            JTextArea addressArea = new JTextArea(8, 15);
                            addressArea.setBackground(Color.LIGHT_GRAY);
                            addressArea.setLineWrap(true);
                            addressArea.setWrapStyleWord(true);
                            addressArea.setAutoscrolls(true);
                            int choice =JOptionPane.showConfirmDialog(container,
                                    addressArea, "Address", JOptionPane.PLAIN_MESSAGE);
                            if(choice == 0){
                                if(!addressArea.getText().isEmpty()){
                                    accountAddress.setString(1, addressArea.getText());
                                    accountAddress.setInt(2, lrn.intValue());
                                    accountAddress.executeUpdate();
                                    JOptionPane.showMessageDialog(container, "Your new "
                                            + "address is `" + addressArea.getText() +
                                            "`.", "Update Succes", JOptionPane.INFORMATION_MESSAGE);
                                    myAddress.setText("Address: "+ addressArea.getText());
                                }
                                
                            }
                            
                        } catch (Exception e) {
                        }
                        break;
                }
                
                
                System.out.println("Success Update Account");
                connection.close();
                
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
                
            }
            
            
        return connection;
    }
    
    //arraylist of users get specific data of user
     public static ArrayList<libraryUsers> getAllUsers(AtomicInteger lrn) throws ClassNotFoundException, SQLException {
     ArrayList<libraryUsers> bookList = new ArrayList<>();
     
    try {
    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/testdb","root" ,"");
    Statement stm;
    stm = conn.createStatement();
    String sql = "SELECT * FROM `finalusers` WHERE lrn=" + lrn;
    ResultSet rst;
    rst = stm.executeQuery(sql);
    while (rst.next()) {
        libraryUsers users = new libraryUsers(rst.getInt("lrn"), 
                rst.getString("student number"));
        bookList.add(users);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Database Error" +  e.getMessage());
        System.exit(0);
    }
    return bookList;
    
    }  
     
     
    
    //log in
    public Connection openConnection(JTextField lrn, JTextField pass, Boolean loggedin,
                                JPanel logIn, JPanel home, JPanel main, JFrame frame,
                                JLabel usersName, AtomicInteger currentUserslrn,
                                JLabel returnTopBanner, JPanel adminPanel){
        if(!loggedin){   
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "Select * from finalusers where lrn=? and password=?";
            try {
                
                Class.forName(driver);
                this.connection =DriverManager.getConnection(url 
                            + dbName, userName , password);
                pstLogin = connection.prepareStatement(sql);
                pstLogin.setString(1, lrn.getText());
                pstLogin.setString(2, pass.getText());
                ResultSet rs =  pstLogin.executeQuery();
                
                if(rs.next()){
                    if("Grade 11".equals(rs.getString("grade level")) || 
                            "Grade 12".equals(rs.getString("grade level"))){
                        JOptionPane.showMessageDialog(frame, "Welcome to the Library.",
                        "Log In Successful", 1);
                    System.out.println("logged in");
                    usersName.setText( "Welcome " + rs.getString("first name") +" "+ rs.getString("last name")+ "!" );
                    returnTopBanner.setText(rs.getString("first name") + " " + rs.getString("last name")+ "'s Borrowed Books");
                    logIn.setVisible(false);
                    home.setVisible(true);
                    main.setVisible(false);
                    frame.setResizable(true);
                    frame.setContentPane(home);
                    loggedin = true;
                    currentUserslrn.compareAndSet(0, rs.getInt("lrn"));
                        
                    }else {
                        System.out.println("logged in as ADMIN");
                        logIn.setVisible(false);
                        adminPanel.setVisible(true);
                        main.setVisible(false);
                        frame.setResizable(true);
                        frame.setContentPane(adminPanel);
                        loggedin = true;
                        currentUserslrn.compareAndSet(0, rs.getInt("lrn"));
                    }
                    
                    
                }else {
                    System.out.println("wrong username and pass");
                JOptionPane.showMessageDialog(frame, wrongInfoMessage,
                        "Log In failed.", 1);}
                
            } catch (ClassNotFoundException | SQLException e) {
                String exception = e.getMessage();
                JOptionPane.showMessageDialog(main, "<html>Data Base Error <br> </html>" + exception );
                System.out.println("Unuccessful Connection " + e);
            }finally{
                try {
                    connection.close();
                    pstLogin.close();
                } catch (Exception e) {
                }
            }
        }       
        return connection;
    }
    
    
    //create account
     public Connection openConnection(Boolean loggedin, JTextField lrnSignup,
             JComboBox gradeLevel,JComboBox section ,JTextField fName,JTextField lName, JComboBox mName,
             JComboBox gender, JTextArea address,JTextField mNumber, JTextField pass1,
             JTextField pass2, String studentNum){
        if(!loggedin){   
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "INSERT INTO `finalusers`(`lrn`, `grade level`, "
                    + "`section`, `first name`, `last name`, `middle initial`,"
                    + " `gender`, `address`, `mobile number`, `password`, "
                    + "`confirm password`, `student number`)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
                pstSignup = connection.prepareStatement(sql);
                pstSignup.setString(1, lrnSignup.getText());
                pstSignup.setString(2, gradeLevel.getSelectedItem().toString());
                pstSignup.setString(3, section.getSelectedItem().toString());
                pstSignup.setString(4, fName.getText());
                pstSignup.setString(5, lName.getText());
                pstSignup.setString(6, mName.getSelectedItem().toString());
                pstSignup.setString(7, gender.getSelectedItem().toString());
                pstSignup.setString(8, address.getText());
                pstSignup.setString(9, mNumber.getText());
                pstSignup.setString(10, pass1.getText());
                pstSignup.setString(11, pass2.getText());
                pstSignup.setString(12, studentNum);
                
                pstSignup.execute();
                
              System.out.println("Successful Connection ");   
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Unuccessful Connection " + e);
                
            }finally{
                try {
                    connection.close();
                    pstSignup.close();
                } catch (Exception e) {
                }
            }
        }       
        return connection;
    }
    
    //arraylist of users
     public static ArrayList<libraryUsers> getAllUsers() throws ClassNotFoundException, SQLException {
     ArrayList<libraryUsers> bookList = new ArrayList<>();
     
    try {
    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/testdb","root" ,"");
    Statement stm;
    stm = conn.createStatement();
    String sql = "SELECT * FROM `finalusers`";
    ResultSet rst;
    rst = stm.executeQuery(sql);
    while (rst.next()) {
        libraryUsers users = new libraryUsers(rst.getInt("lrn"), 
                rst.getString("student number"));
        bookList.add(users);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Database Error" +  e.getMessage());
        System.exit(0);
    }
    return bookList;
    
    } 
    
     
     
    //arraylist of books 
     public static ArrayList<bookName> getAllCustomer() throws ClassNotFoundException, SQLException {
     ArrayList<bookName> bookList = new ArrayList<>();
     
    try {
    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/testdb","root" ,"");
    Statement stm;
    stm = conn.createStatement();
    String sql = "SELECT * FROM `bookdata` ";
    ResultSet rst;
    rst = stm.executeQuery(sql);
    while (rst.next()) {
        bookName books = new bookName(rst.getString("Title"), rst.getString
        ("BookNumber"), rst.getInt("Quantity"), rst.getString("Description"));
        bookList.add(books);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Database Error" + e.getMessage());
    }
    return bookList;
    
    }
     
    //arraylist of books 
     public static ArrayList<bookName> getAllCustomer(int bookNumber) throws ClassNotFoundException, SQLException {
     ArrayList<bookName> bookList = new ArrayList<>();
     
    try {
    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/testdb","root" ,"");
    Statement stm;
    stm = conn.createStatement();
    String sql = "SELECT * FROM `bookdata` WHERE BookNumber = " + bookNumber;
    ResultSet rst;
    rst = stm.executeQuery(sql);
    while (rst.next()) {
        bookName books = new bookName(rst.getString("Title"), rst.getString
        ("BookNumber"), rst.getInt("Quantity"), rst.getString("Description"));
        bookList.add(books);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Database Error" + e.getMessage());
        System.exit(0);
    }
    return bookList;
    
    } 
     
     
    //arraylist of borrowed books
     
    public static ArrayList<borrowedBookName> getAllborrowedBooks(int lrn) throws ClassNotFoundException, SQLException {
     ArrayList<borrowedBookName> borrowedBookList = new ArrayList<>();
     
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/testdb","root" ,"");

                String sql = "SELECT `userslrn`, `booksnumber`, `bookstitle`, `startingdate`, `endingdate`, `borrowCode` FROM `finalborrowedbook`  WHERE userslrn = " + lrn     ;

                PreparedStatement stm;
                stm = conn.prepareStatement(sql);

                    ResultSet rs;

                    rs = stm.executeQuery(sql);
                    while (rs.next()) {
                        borrowedBookName borrowedBooks = new borrowedBookName(rs.getInt("userslrn"),
                        rs.getInt("booksnumber"), rs.getString("bookstitle"), rs.getDate("startingdate"),
                        rs.getDate("endingdate"), rs.getInt("borrowCode"));
                        borrowedBookList.add(borrowedBooks);


                        }
            } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Database Error");
                        System.out.println(e.getMessage());
                        System.exit(0);
            }
            return borrowedBookList;

        } 
    

    
     
    //delete database from borrowed books
     public Connection openConnection(int lrn, int borrowCode, int booksNumber,int currentQuantity,
             JScrollPane returnScroll, JLabel bookDetailQuantity[],JButton bottomButtons[], 
             JPanel returnContainer){
        
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "DELETE FROM `finalborrowedbook` WHERE `userslrn`=? AND `borrowCode`=? AND `booksnumber`=?";
            String sql1 = " UPDATE `bookdata` SET `Quantity`=? WHERE `BookNumber`=?";
            String sql2 = "SELECT * FROM `bookdata`";
            
            try {
                
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
                
                pstDeleteBooks = connection.prepareStatement(sql);
                pstInsertBooks = connection.prepareStatement(sql1);
                PreparedStatement testState = connection.prepareStatement(sql2);
               
                
                pstDeleteBooks.setInt(1, lrn);
                pstDeleteBooks.setInt(2, borrowCode);
                pstDeleteBooks.setInt(3, booksNumber);
                pstInsertBooks.setInt(1, currentQuantity + 1);
                pstInsertBooks.setInt(2, booksNumber);
                
                int confirm = JOptionPane.showConfirmDialog(returnScroll, "Are you sure you want return book?", 
                            "Confirm Changes", JOptionPane.YES_NO_OPTION);
                if(confirm == 0){
                    pstDeleteBooks.execute();
                    pstInsertBooks.execute();
                   
                    returnScroll.setViewportView(returnContainer);
                    bottomButtons[1].doClick();
                    
                    JOptionPane.showMessageDialog(returnScroll, "Book Returned", "Return Success", JOptionPane.INFORMATION_MESSAGE);
                }
              System.out.println("Successful Connection "); 
            
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(returnScroll, "Error. Please  log out your account and then"
                            + " try again. Error details: " + e, "Return Unsuccessful", JOptionPane.WARNING_MESSAGE);
                System.out.println("Unuccessful Connection " + e);
                
            }finally{
                try {
                    connection.close();
                    pstDeleteBooks.close();
                    pstInsertBooks.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
               
        return connection;
    }
     
     
    //database from borrowed books VERSION 2
     public Connection openConnection(JScrollPane returnScroll, JPanel books[],
             JPanel container, AtomicInteger currentLrn, JLabel bookTitle[],
             JLabel bookImage[], JLabel endDate[], ImageIcon bookIcon[],
             Image editImage[], Image editImage2[], JPanel returnBookDetailPanel[],
             JScrollPane retunScroll,JButton returnBack[],JButton returnReturnBook[],
             JButton returnRenewBook[],JLabel returnDetailBookTitle[],
             JLabel returnDetailStartDate[],JLabel returnDetailEndDate[],
             JLabel returnDetailImage[],GridBagLayout layout,
             GridBagConstraints gbc, JButton homeButton[], JLabel homeQuantityLabel[],
             int homeBookCount, Color background){
        
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "DELETE FROM `finalborrowedbook` WHERE `userslrn`=? AND `borrowCode`=? AND `booksnumber`=?";
            String sql1 = " UPDATE `bookdata` SET `Quantity`=? WHERE `BookNumber`=?";
            String sql2 = "SELECT * FROM `bookdata`";
            String sql3 = "SELECT * FROM `finalborrowedbook` WHERE userslrn=?" ;
           
            try {
                
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
                
               
                container.setBackground(background);
                container.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
                pstDeleteBooks = connection.prepareStatement(sql);
                pstInsertBooks = connection.prepareStatement(sql1);
                PreparedStatement pstDisplayBorrowed = connection.prepareStatement(sql3);
                pstDisplayBorrowed.setInt(1, currentLrn.intValue());
                ResultSet rs = pstDisplayBorrowed.executeQuery();
                int i = -1;
                
                while(rs.next()){
                    i +=1;
                    books[i] = new JPanel();
                    books[i].setPreferredSize(new Dimension(120, 160));
                    books[i].setBackground(background);
                    books[i].setLayout(new BorderLayout());
                    books[i].setBorder(new LineBorder(Color.BLACK));
                    bookTitle[i] = new JLabel();
                    bookTitle[i].setText(rs.getString("bookstitle"));
                    bookImage[i] = new JLabel();
                    bookImage[i].setPreferredSize(new Dimension(120, 120));
                    
                    bookIcon[i] = new ImageIcon("src\\images\\" + rs.getString("booksnumber") + ".jpg");
                    editImage[i] = bookIcon[i].getImage();
                    editImage2[i] = editImage[i].getScaledInstance(120, 140,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                    bookIcon[i] = new ImageIcon(editImage2[i]);
                    bookImage[i].setIcon(bookIcon[i]);
                    
                    endDate[i] = new JLabel();
                    endDate[i].setText(rs.getDate("endingdate").toString());
                    
                    books[i].add(endDate[i], BorderLayout.NORTH);
                    books[i].add(bookImage[i], BorderLayout.CENTER);
                    books[i].add(bookTitle[i], BorderLayout.SOUTH);
                    container.add(books[i]);
                    
                    returnBookDetailPanel[i] = new JPanel();
                    returnBookDetailPanel[i].setBackground(background);
                    
                    
                returnDetailBookTitle[i] = new JLabel();
                returnDetailBookTitle[i].setText("Book Title: " + rs.getString("bookstitle"));
                returnDetailBookTitle[i].setFont(new Font ("Arial", Font.PLAIN, 16));
                returnDetailBookTitle[i].setForeground(Color.BLACK);
                returnDetailBookTitle[i].setHorizontalAlignment(SwingConstants.LEFT);
                
                returnDetailEndDate[i] = new JLabel();
                returnDetailEndDate[i].setText("Expiry Date: "+ rs.getDate("endingdate").toString());
                returnDetailEndDate[i].setFont(new Font ("Arial", Font.PLAIN, 16));
                returnDetailEndDate[i].setForeground(Color.BLACK);
                returnDetailEndDate[i].setHorizontalAlignment(SwingConstants.LEFT);
                
                returnDetailStartDate[i] = new JLabel();
                returnDetailStartDate[i].setText("Borrow Date: " + rs.getDate("startingdate").toString());
                returnDetailStartDate[i].setFont(new Font ("Arial", Font.PLAIN, 16));
                returnDetailStartDate[i].setForeground(Color.BLACK);
                returnDetailStartDate[i].setHorizontalAlignment(SwingConstants.LEFT);
                
                returnDetailImage[i] = new JLabel();
                returnDetailImage[i].setIcon(bookIcon[i]);
                
                returnBack[i] = new JButton("Back");
                returnBack[i].setPreferredSize(new Dimension(130, 50));
                returnBack[i].setBorder(new LineBorder(Color.BLACK, 1));
                
                returnRenewBook[i] = new JButton("Renew Book");
                returnRenewBook[i].setPreferredSize(new Dimension(130, 50));
                returnRenewBook[i].setBorder(new LineBorder(Color.BLACK, 1));
                
                returnReturnBook[i] = new JButton("Return Book");
                returnReturnBook[i].setPreferredSize(new Dimension(120, 50));
                returnReturnBook[i].setBorder(new LineBorder(Color.BLACK, 1));
                
                returnBookDetailPanel[i].setLayout(layout);                                               //top -left- bottom - right  
                addobjects(returnDetailImage[i], returnBookDetailPanel[i], layout, gbc,        0, 0, 1, 3,    0, 0,  0, 0);
                addobjects(returnDetailBookTitle[i], returnBookDetailPanel[i], layout, gbc,    1, 1, 1, 1,    0, 0, 0, 0);
                addobjects(returnDetailStartDate[i], returnBookDetailPanel[i], layout, gbc,    1, 1, 1, 1,    40, 50, 0, 0);
                addobjects(returnDetailEndDate[i], returnBookDetailPanel[i], layout, gbc,      1, 1, 1, 1,    80, 45, 0, 0);
                addobjects(returnReturnBook[i], returnBookDetailPanel[i], layout, gbc,         0, 4, 1, 1,    30, 0, 1, 1);
                addobjects(returnRenewBook[i], returnBookDetailPanel[i], layout, gbc,          1, 4, 1, 1,    30, 1, 1, 1);
                addobjects(returnBack[i], returnBookDetailPanel[i],layout, gbc,                2, 4, 1, 1,    30, 1, 1, 1);
                
                }
              
                retunScroll.setViewportView(container);
                retunScroll.setBorder(null);
                container.setPreferredSize(new Dimension(0, i * 50));
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(returnScroll, "Error. Please  log out your account and then"
                            + " try again. Error details: " + e, "Return Unsuccessful", JOptionPane.WARNING_MESSAGE);
                System.out.println("Unuccessful Connection " + e);
                
            }finally{
                try {
                    connection.close();
                    pstDeleteBooks.close();
                    pstInsertBooks.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
               
        return connection;
    } 
     
     
    
     //RENEW books
     public Connection openConnection(int currentLrn, int borrowCode, 
             int bookNumber, JScrollPane returnScroll, Date currentEndDate,
             JPanel returnContainer, JButton bottomButtons[], String bookName){
             
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "UPDATE  `finalborrowedbook` SET `endingdate`= ?   WHERE `userslrn`=? AND `borrowCode`=? AND `booksnumber`=?";
           
            try {
                
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
                
                pstRenewBooks = connection.prepareStatement(sql);

                String[] options = new String[] {"1 Week", "2 Weeks", "3 Weeks","4 Weeks" ,"Cancel"};
                
                int weeks =JOptionPane.showOptionDialog(returnScroll, "Select the week to add "
                + "expiration date of your book.", "Renew Book",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[3]);
                long addWeek = 0;            
                
                switch(weeks){
                    case 0: addWeek = 604800000l;
                        break;
                    case 1: addWeek = 1209600000l;
                        break;
                    case 2: addWeek = 1814400000l;
                        break;
                    case 3: addWeek = 2419200000l;
                        break;
                    
                }
                
                System.out.println(weeks);    
                Timestamp timestamp = new Timestamp(currentEndDate.getTime() + addWeek);
                pstRenewBooks.setTimestamp(1, timestamp);
                pstRenewBooks.setInt(2, currentLrn);
                pstRenewBooks.setInt(3, borrowCode);
                pstRenewBooks.setInt(4, bookNumber);
                
                if(weeks < 4){
                    int confirm = JOptionPane.showConfirmDialog(returnScroll, "Are you sure you want renew book?", 
                            "Confirm Changes", JOptionPane.YES_NO_OPTION);
                    if(confirm == 0){
                    pstRenewBooks.execute();
                    returnScroll.setViewportView(returnContainer);
                    bottomButtons[1].doClick();   
                    JOptionPane.showMessageDialog(returnScroll,"Expiration date renewed of " + bookName + " to " + timestamp
                        , "Book Renewed"
                        , JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                
                System.out.println("Successful Connection "); 
            
            } catch (ClassNotFoundException | SQLException e) {
                   JOptionPane.showMessageDialog(returnScroll, "Error Please  log out your account and then"
                            + " try again. Error details: " + e, "Renew Unsuccessful", JOptionPane.WARNING_MESSAGE);
                System.out.println("Unuccessful Connection " + e);
                
            }finally{
                try {
                    connection.close();
                    pstRenewBooks.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
               
        return connection;
    }
     
    
    //search method 
    public Connection openConnection(JLabel title[], JTextField searchBar, 
            JPanel books[]){
        
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "SELECT Title FROM bookData WHERE Title LIKE '%" + searchBar.getText() +"%' " ;
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
                
                searchBooks = connection.prepareStatement(sql);
                ResultSet rs = searchBooks.executeQuery();
                
                while(rs.next()) {
                    String s = rs.getString("Title");
                    for(int i = 0; i < Database.getAllCustomer().size();i++){
                        if(title[i].getText().equals(s)){
                            books[i].setVisible(true);
                        }else  books[i].setVisible(false);
                        
                        if("".equals(searchBar.getText()) ){
                            books[i].setVisible(true);
                            
                        }
                    }
                }    
              System.out.println("Successful Connection ");   
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Unuccessful Connection " + e);
                
            }finally{
                try {
                    searchBooks.close();
                    connection.close();
                } catch (Exception e) {
                }
            }               
        return connection;
    }
    
     //adding book details to borrowed books database
     public Connection openConnection(AtomicInteger currentUsersLrn,int bookNumber, 
             String bookTitle, Date startDate, Date endDate, int currentQuantity,
             JScrollPane scroll, JPanel bookPanel, JComboBox bookDetailDate[], int bookCount){  
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "INSERT INTO `finalBorrowedBook`(`userslrn`, `booksnumber`, `bookstitle`, `startingdate`, `endingdate`, `borrowCode`) VALUES (?,?,?,?,?,?)";
            String sql1 = "UPDATE `bookdata` SET `Quantity`= ?   WHERE `BookNumber`=?";
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
               borrowBooks = connection.prepareStatement(sql);
               updateQuantityBooks = connection.prepareStatement(sql1);
               
               borrowBooks.setInt(1, currentUsersLrn.intValue());
               borrowBooks.setInt(2, bookNumber);
               borrowBooks.setString(3, bookTitle);
               borrowBooks.setDate(4, startDate);
               borrowBooks.setDate(5,  endDate);
               borrowBooks.setInt(6, Database.getAllborrowedBooks(currentUsersLrn.intValue()).size() + 1);
               updateQuantityBooks.setInt(1, currentQuantity - 1);
               updateQuantityBooks.setInt(2, bookNumber);
               
               updateQuantityBooks.execute();
               borrowBooks.execute();
               scroll.setViewportView(bookPanel);
               for(int i = 0; i < bookCount; i ++){
               bookDetailDate[i].setSelectedIndex(0);
               }
               JOptionPane.showMessageDialog(scroll, "<html> Book borrowed to your account."
                       + " Give your name and lrn to <br> librarian or representative and get the book .</html> "
                       , "Borrow Success",
                       JOptionPane.PLAIN_MESSAGE);
               
              System.out.println("Successful Connection ");   
            } catch (ClassNotFoundException | SQLException e) {
                 JOptionPane.showMessageDialog(scroll, " Please log out and log in"
                         + " again to fix the problem. Error Details: " + e, "Borrow Unsuccessful",
                       JOptionPane.ERROR_MESSAGE);
                System.out.println("Unuccessful Connection " + e);
                
            }finally{
                try {
                    connection.close();
                    borrowBooks.close();
                } catch (Exception e) {
                }
            }
             
        return connection;
    }
    
    
       //admin books table
     public Connection openConnection(DefaultTableModel tableModel, 
             JTable table){  
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "SELECT * FROM `bookdata`";
            
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
               tableBooks = connection.prepareStatement(sql);
               ResultSet rs = tableBooks.executeQuery();
               
               table.removeAll();
               tableModel.setRowCount(0);
               while(rs.next()){

                   String title = rs.getString("Title");
                   String bookN = rs.getString("BookNumber");
                   String quantity = rs.getString("Quantity");
                   String description = rs.getString("Description");
                   String updateBook = "Click to update "+ rs.getString("Title");
                   String deleteBook = "Click to delete " +rs.getString("Title");

                   tableModel.addRow(new Object[]{title, bookN, quantity,
                           description, updateBook, deleteBook});
               }
              
               
              System.out.println("Successful Connection ");   
            } catch (ClassNotFoundException | SQLException e) {
                 System.out.println(e.getMessage());
                
            }finally{
                try {
                    connection.close();
                    tableBooks.close();
                } catch (Exception e) {
                }
            }
             
        return connection;
    }
     
     //admin account table
     public Connection openConnection(DefaultTableModel accModel, 
             JTable accTable, JPasswordField passField){  
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "SELECT * FROM `finalusers`";
            
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
               tableBooks = connection.prepareStatement(sql);
               ResultSet rs = tableBooks.executeQuery();
               
               accTable.removeAll();
               accModel.setRowCount(0);
               while(rs.next()){
                   int lrn = rs.getInt("lrn");
                   String studentNum = rs.getString("student number");
                   String pass = rs.getString("password");
                   String Name = rs.getString("first name") + " "+rs.getString("middle initial")
                           + " . "+ rs.getString("last name");
                   String gender = rs.getString("gender");
                   String GradeSec = rs.getString("grade level") + " - "+
                           rs.getString("section");
                   String address = rs.getString("address");
                   String contactN = rs.getString("mobile number");
                   String updateAcc = "Click to update " + rs.getString("last name") +
                           "`s account.";
                   String deleteAcc = "Click to delete " + rs.getString("last name") +
                           "`s account.";
                   accModel.addRow(new Object[]{lrn, pass , studentNum,Name,
                        gender, GradeSec, address, contactN, updateAcc, deleteAcc});
               }
              
               
              System.out.println("Successful Connection ");   
            } catch (ClassNotFoundException | SQLException e) {
                 System.out.println(e.getMessage());
                
            }finally{
                try {
                    connection.close();
                    tableBooks.close();
                } catch (Exception e) {
                }
            }
             
        return connection;
    }
     
     
     
      //update admin books table
     public Connection openConnection(String bookNumber, int switchCase,
             JTextField updateTitle, JTextField updateQuantity, 
             JTextArea updateAddress){  
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "DELETE FROM `bookdata` WHERE `BookNumber` = ?";
            String sql1= "UPDATE `bookdata` SET `Title`=?,"
                    + "`Quantity`=?,"
                    + "`Description`=? WHERE `BookNumber`=?";
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
               switch(switchCase){
                   case 1:
                       PreparedStatement deleteBooks = connection.prepareStatement(sql);
                       deleteBooks.setString(1, bookNumber);
                       deleteBooks.execute();
                       break;
                   case 2:   
                       PreparedStatement updateBooks = connection.prepareStatement(sql1);
                       updateBooks.setString(1, updateTitle.getText());
                       updateBooks.setString(2, updateQuantity.getText());
                       updateBooks.setString(3, updateAddress.getText());
                       updateBooks.setString(4, bookNumber);
                       updateBooks.executeUpdate();
                       
                       
               } 
               
            } catch (ClassNotFoundException | SQLException e) {
                 System.out.println(e.getMessage());
                
            }
        return connection;
    }
     
     //update admin accounts table
     public Connection openConnection(int switchCase, JTextField myAccLrnField,
             JScrollPane manageAccScroll, JPasswordField myAccPassField,
             String lrn){  
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "DELETE FROM `finalusers` WHERE lrn =?";
            String sql1= "UPDATE `finalusers` SET `lrn`=? , " 
                    + "`password`=?,"
                    + "`confirm password`=? WHERE lrn=?";
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
               switch(switchCase){
                   case 1:
                       PreparedStatement deleteAcc = connection.prepareStatement(sql);
                           System.out.println("databe execution start"); 
                           deleteAcc.setString(1,lrn);
                           System.out.println("databe execution start"); 
                           System.out.println(myAccLrnField.getText());
                           deleteAcc.execute();
                           
                       
                       break;
                   case 2:   
                       PreparedStatement updateAcc = connection.prepareStatement(sql1);
                      
                       updateAcc.setString(1, myAccLrnField.getText());
                       updateAcc.setString(2, myAccPassField.getText());
                       updateAcc.setString(3, myAccPassField.getText());
                       updateAcc.setString(4, lrn);
                       updateAcc.executeUpdate();
                       System.out.println(myAccLrnField.getText());
               } 
               
            } catch (ClassNotFoundException | SQLException e) {
                 System.out.println(e.getMessage());
                
            }
        return connection;
    }
      
     
     
    //search method ADMIN
    public Connection openConnection(JTextField searchBar, DefaultTableModel tableModel, 
             JTable table){
        
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "SELECT `Title`, `BookNumber`, `Quantity`, `Description`"
                    + " FROM bookData WHERE Title LIKE '%" + searchBar.getText() +"%' " ;
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
                
                searchBooksAdmin = connection.prepareStatement(sql);
                ResultSet rs = searchBooksAdmin.executeQuery();
                System.out.println("Test");
                table.removeAll();
                tableModel.setRowCount(0);
                while(rs.next()) {
                    String title = rs.getString("Title");
                    String bookN = rs.getString("BookNumber");
                    String quantity = rs.getString("Quantity");
                    String description = rs.getString("Description");
                    String updateBook = "Click to update "+ rs.getString("Title");
                    String deleteBook = "Click to delete " +rs.getString("Title");

                   tableModel.addRow(new Object[]{title, bookN, quantity,
                           description, updateBook, deleteBook});
                    }
                
              System.out.println("Successful Connection ");   
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Unuccessful Connection " + e);
                
            }finally{
                try {
                    searchBooksAdmin.close();
                    connection.close();
                } catch (Exception e) {
                }
            }               
        return connection;
    } 
    
    //search method account ADMIN
    public Connection openConnection(JTextField searchBar, DefaultTableModel accModel, 
             JTable accTable, int test){
        
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "SELECT * FROM `finalusers` WHERE "
                    + "lrn LIKE '%" + searchBar.getText() +"%' " ;
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
                
                searchBooksAdmin = connection.prepareStatement(sql);
                ResultSet rs = searchBooksAdmin.executeQuery();
                System.out.println("Test");
                accTable.removeAll();
                accModel.setRowCount(0);
                while(rs.next()){
                   int lrn = rs.getInt("lrn");
                   String studentNum = rs.getString("student number");
                   String pass = rs.getString("password");
                   String Name = rs.getString("first name") + " "+rs.getString("middle initial")
                           + " . "+ rs.getString("last name");
                   String gender = rs.getString("gender");
                   String GradeSec = rs.getString("grade level") + " - "+
                           rs.getString("section");
                   String address = rs.getString("address");
                   String contactN = rs.getString("mobile number");
                   String updateAcc = "Click to update " + rs.getString("lrn") +
                           "`s account.";
                   String deleteAcc = "Click to delete " + rs.getString("lrn") +
                           "`s account.";
                   accModel.addRow(new Object[]{lrn, pass ,studentNum ,Name,
                        gender, GradeSec, address, contactN, updateAcc, deleteAcc});
               }
                
              System.out.println("Successful Connection ");   
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Unuccessful Connection " + e);
                
            }finally{
                try {
                    searchBooksAdmin.close();
                    connection.close();
                } catch (Exception e) {
                }
            }               
        return connection;
    } 
    
    
    
    
    //insert books
    public Connection openConnection(JTextField addBookTitle,
            JTextField addBookQuantity, JTextArea addBookDescription,
            int addBookNumber,JLabel previewImage ,JPanel addBooksPanel,
            JFileChooser fileChooser){
        
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "INSERT INTO `bookdata`(`Title`, `BookNumber`,"
                    + " `Quantity`, `Description`) VALUES (?,?,?,?)" ;
            try {
                Class.forName(driver);
                this.connection = DriverManager.getConnection(url 
                            + dbName, userName , password);
                
                addBooks = connection.prepareStatement(sql);
                addBooks.setString(1, addBookTitle.getText());
                addBooks.setInt(2, addBookNumber);
                addBooks.setInt(3, Integer.parseInt(addBookQuantity.getText()));
                addBooks.setString(4, addBookDescription.getText());
                
                addBooks.executeUpdate();
                
                
                
                addBookQuantity.setText("");
                addBookDescription.setText("");
                previewImage.setText("Choose an Image");
                previewImage.setIcon(null);
                fileChooser.setSelectedFile(null);
                JOptionPane.showMessageDialog(addBooksPanel, addBookTitle.getText()
                        + " successfully added to database",
                        "Update Success", JOptionPane.INFORMATION_MESSAGE);
                addBookTitle.setText("");
              System.out.println("Successful Connection ");   
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Unuccessful Connection " + e);
                
            }finally{
                try {
                    addBooks.close();
                    connection.close();
                } catch (Exception e) {
                }
            }               
        return connection;
    }
    
    
    //borrow book TEST 2
    public Connection openConnection(JPanel bookPanel[], JLabel bookTitle[], 
            JPanel container, JLabel imagePanel[], ImageIcon bookIcon[],
            Image editImage[], Image editImage2[], Color background,
            Font titleFont, JPanel bookDetail[], GridBagLayout bookDetailLayout,
            JButton backDetail[], JComboBox bookDetailBorrowDate[],
            String borrowDateChoices[], JButton bookDetailBorrow[],
            JLabel bookDetailQuantity[], JTextArea bookDetailDescription[],
            JLabel bookDetailName[], JLabel bookDetailImage[], 
            JScrollPane bookDetailDescriptionScroll[],
            GridBagConstraints bookDetialCons){
         
            String url = "jdbc:mysql://localhost/"; //url to database in xampp
            String dbName = "testdb"; //database name
            String driver = "com.mysql.jdbc.Driver"; //jdbc driver class
            String userName = "root"; //username
            String password = ""; //password
            String sql = "SELECT * FROM `bookdata`";
            try {
                
                Class.forName(driver);
                this.connection =DriverManager.getConnection(url 
                            + dbName, userName , password);
                borrowBooksNew = connection.prepareStatement(sql);
                ResultSet rs = borrowBooksNew.executeQuery();
                container.removeAll();
                int i= -1;
                while(rs.next()){
                    i = i + 1;
                    bookPanel[i] = new JPanel();
                    bookPanel[i].setPreferredSize(new Dimension(120, 160));
                    bookPanel[i].setBackground(background);
                    bookPanel[i].setLayout(new BorderLayout());
                    bookPanel[i].setBorder(new LineBorder(Color.BLACK, 1));
                    bookTitle[i] = new JLabel();
                    bookTitle[i].setText(rs.getString("Title"));
                    bookTitle[i].setHorizontalAlignment(SwingConstants.CENTER);
                    bookTitle[i].setFont(titleFont);
                    
                    imagePanel[i] = new JLabel();
                    imagePanel[i].setPreferredSize(new Dimension(120, 140));
                    
                    bookIcon[i] = new ImageIcon("src\\images\\" + rs.getString("BookNumber") + ".jpg");
                    editImage[i] = bookIcon[i].getImage();
                    editImage2[i] = editImage[i].getScaledInstance(120, 140,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                    bookIcon[i] = new ImageIcon(editImage2[i]);
                    imagePanel[i].setIcon(bookIcon[i]);
                    bookPanel[i].add(bookTitle[i], BorderLayout.SOUTH);
                    bookPanel[i].add(imagePanel[i], BorderLayout.CENTER);
                    container.add(bookPanel[i]);
                    
                    //BOOK DETAIL
                    bookDetail[i] = new JPanel();
                    bookDetail[i].setBackground(background);
                    bookDetail[i].setLayout(bookDetailLayout);
                    backDetail[i] = new JButton("Back");
                    arrayString(borrowDateChoices, "Borrow Duration","1 Week","2 Weeks","3 Weeks","4 Weeks");
                    bookDetailBorrowDate[i] = new JComboBox(borrowDateChoices);
                    bookDetailBorrow[i] = new JButton("Borrow Book");
                    bookDetailQuantity[i] = new JLabel("Quantity: " + String.valueOf(rs.getInt("Quantity")));
                    bookDetailDescription[i] = new JTextArea(rs.getString("Description"), 5,1);
                    bookDetailName[i] = new JLabel(rs.getString("Title"));
                    bookDetailImage[i] = new JLabel(bookIcon[i]);
                    bookDetailBorrow[i].setBorder(new LineBorder(Color.BLACK, 1));
                    bookDetailBorrow[i].setPreferredSize(new Dimension(120, 40));
                      bookDetailName[i].setFont(new Font("Arial", Font.PLAIN, 34));
            bookDetailName[i].setForeground(Color.BLACK);
             
            bookDetailDescriptionScroll[i] = new JScrollPane(bookDetailDescription[i]);
            bookDetailDescriptionScroll[i].setPreferredSize(new Dimension(400, 200));
            bookDetailDescriptionScroll[i].setBorder(null);
            
            bookDetailQuantity[i].setFont(new Font("Arial", Font.BOLD, 18));
          
            backDetail[i].setPreferredSize(new Dimension(120, 40));
            backDetail[i].setBorder(new LineBorder(Color.BLACK, 1));
            
            bookDetailDescription[i].setFont(new Font("Arial", Font.PLAIN, 14));
            bookDetailDescription[i].setForeground(Color.black);
            bookDetailDescription[i].setLineWrap(true);
            bookDetailDescription[i].setWrapStyleWord(true);
            bookDetailDescription[i].setAutoscrolls(true);  
            bookDetailDescription[i].setBackground(background);
            bookDetailDescription[i].setEditable(false);
            if(rs.getInt("Quantity") > 0){
                    bookDetailQuantity[i].setForeground(Color.BLUE);  
                }else   bookDetailQuantity[i].setForeground(Color.RED);  
                
                if(rs.getInt("Quantity") == 0){
                    bookDetailBorrowDate[i].setEnabled(false);
                    bookDetailBorrow[i].setEnabled(false);
                }else{
                    bookDetailBorrowDate[i].setEnabled(true);
                    bookDetailBorrow[i].setEnabled(true);
                }
            addobjects(bookDetailName[i], bookDetail[i], bookDetailLayout, bookDetialCons,                 0,0,2,1, 0 , 0 ,  30,    0);
            addobjects(bookDetailImage[i], bookDetail[i], bookDetailLayout, bookDetialCons,                0,1,1,1, 0 , 0 ,   0,   20);
            addobjects(bookDetailQuantity[i], bookDetail[i], bookDetailLayout, bookDetialCons,             0,2,1,1, 0 , 0 ,   0,   30);
            addobjects(bookDetailBorrowDate[i], bookDetail[i], bookDetailLayout, bookDetialCons,           0,3,1,1, 0 , 0 ,   0,   30);
            addobjects(bookDetailDescriptionScroll[i], bookDetail[i], bookDetailLayout, bookDetialCons,    1,1,1,3, 0 , 0 ,   0,    0);
            addobjects(bookDetailBorrow[i], bookDetail[i], bookDetailLayout, bookDetialCons,               0,4,1,1, 20, 0 ,   0, -200);
            addobjects(backDetail[i], bookDetail[i], bookDetailLayout, bookDetialCons,                     1,4,1,1, 20, 0 ,   0,   50);    
           
            }
                
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
                
            }finally{
                try {
                    connection.close();
                    borrowBooksNew.close();
                } catch (SQLException e) {
                }
            }   
        return connection;
    }
     //combobox string method
     private void arrayString(String[] S, String s0, String s1, String s2, String s3,
            String s4){
         S[0] = s0;
         S[1] = s1;
         S[2] = s2;
         S[3] = s3;
         S[4] = s4;
     }
     
     //grid Bag lay out properties
    private void addobjects(Component componente, Container yourcontainer, GridBagLayout layout, GridBagConstraints gbc, 
            int gridx, int gridy, int gridwidth, int gridheight, int top, int left, int bottom, int right){

        gbc.insets = new Insets(top, left, bottom, right);
        
        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
       
        layout.setConstraints(componente, gbc);
        yourcontainer.add(componente);
    }
}     
     
