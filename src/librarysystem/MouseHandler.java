/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import com.mysql.cj.util.StringUtils;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import static librarysystem.TheFrame.currentUsersLrn;

/**
 *
 * @author great_000
 */
public class MouseHandler implements MouseListener{
    
    private JTextField lrnLogin;
    private JTextField passLogin;
    private JTextField lrn;
    private JTextField Fname;
    private JTextField Lname;
    private JTextArea address;
    private JTextField Mnumber;
    private JPasswordField pass1;
    private JPasswordField pass2;
    private JButton signIn;
    private JButton signUp;
    private JComboBox gradeLevel;
    private JComboBox section;
    private JComboBox middleName;
    private JComboBox gender;
    private JPanel signUpPanel; 
    private LineBorder errorBorder;
    private LineBorder normalBorder;
    
    private JLabel bookTitle[]; 
    private JPanel books[];
    private JLabel imageContainerBook[];
    private int bookCount;
    private JPanel bookDetail[];
    private JScrollPane scrollPane;
    private JButton backDetail[];
    private JPanel bookPanel;
    private JButton borrowButton[];
    private JButton searchButton;
    private JTextField searchBar;
    private int borrowedBookNumber;
    private String borrowedBookTitle;
    private Date startDate;
    private Date endDate;
    private JLabel bookDetailQuantity[];
    private  JComboBox bookDetailBorrowDate[];
    private int quantity;
    private long oneWeek = System.currentTimeMillis()+ 604800000l;
    private long twoWeek = System.currentTimeMillis() + 1209600000l;
    private long threeWeek = System.currentTimeMillis() + 1814400000l;
    private long fourWeek = System.currentTimeMillis() + 2419200000l;
    
    private JPanel returnBooks[];
    private JLabel returnBookTitle[];
    private JLabel returnBookImage[]; 
    private JLabel returnBookDate[];
    private int borrowedBookCount;
    private JPanel returnContainer;
    private JPanel returnBookDetailPanel[];
    private JScrollPane returnScroll;
    Database database = new Database();
    private JButton returnBack[];
    private JButton returnReturnBook[];
    private JButton returnRenewBook[];
    private int currentLrn;
    private JButton bottomButtons[];
    
    
    private JButton changeLrn;
    private JButton changeName;
    private JButton changeGradeLevel;
    private JButton changeSection;
    private JButton changeAddress;
    private JButton changeGender;
    private JButton changeNumber;
    private JButton changePass;
    private JPanel accountContainer;
    private JLabel myLrn;
    private JLabel myGender;
    private int accountCases;
    private JLabel myName;
    private JLabel myGradeLevel;
    private JLabel mySection;
    private JTextArea myAddress;
    private JLabel myMobileNumber;
    
    private JButton manageButton;
    private JButton addBookButton;
    private JButton manageAccountButton;
    private JPanel manageBooks;
    private JPanel addBooks;
    private JPanel manageAccount;
    private JPanel centerPanelAdmin;
    private DefaultTableModel tableModel;
    private JTable table;
    private LineBorder selectedBorder = new LineBorder(Color.RED, 1);
    private LineBorder notSelectedBorder = new LineBorder(new Color(18, 74, 61), 1);
    private JButton searchButtonAdmin;
    private JTextField searchBarAdmin;
    private JTextField addBookTitle;
    private JTextField addBookQuantity;
    private JTextArea addBookDescription;
    private JButton selectBookImage;
    private JFileChooser imageChooser;
    private ImageIcon bookIcon;
    private JLabel imagePreview;
    private Image beforeImage;
    private File sourceFile = null;
    private JButton addNewBook;
    
    private JTable accTable;
    private DefaultTableModel accModel;
    private JPasswordField manageAccPassField;
    private JButton accSearchButton;
    private JTextField accSearchField;
    private JLabel signUpImageContainer;
    private JButton changeImage;
    private JLabel myImageContainer;
    private JFileChooser myImageChooser;
    private ImageIcon myIcon;
    
    //admin
    public MouseHandler(JButton manageButton,JButton addBookButton,
            JButton manageAccountButton,JPanel manageBooks,
            JPanel addBooks,JPanel manageAccount, JPanel centerPanelAdmin,
            DefaultTableModel tableModel, JTable table, JButton searchButtonAdmin
            ,JTextField searchBarAdmin, JTextField addBookTitle,
            JTextField addBookQuantity,JTextArea addBookDescription,
            JButton selectBookImage,JFileChooser imageChooser, ImageIcon bookIcon,
            JLabel imagePreview, JButton addNewBook, JTable accTable,
            DefaultTableModel accModel,JPasswordField manageAccPassField,
            JButton accSearchButton, JTextField accSearchField){
        this.accSearchField = accSearchField;
        this.accSearchButton = accSearchButton;
        this.manageAccPassField = manageAccPassField;
        this.accModel = accModel;
        this.accTable = accTable;
        accTable.addMouseListener(this);
        this.addNewBook = addNewBook;
        this.imagePreview = imagePreview;
        this.bookIcon = bookIcon;
        this.imageChooser = imageChooser;
        this.addBookTitle = addBookTitle;
        this.addBookQuantity = addBookQuantity;
        this.addBookDescription = addBookDescription;
        this.selectBookImage = selectBookImage;
        this.searchBarAdmin = searchBarAdmin;
        this.searchButtonAdmin = searchButtonAdmin;
        this.table = table;
        this.tableModel = tableModel;
        this.centerPanelAdmin = centerPanelAdmin;
        this.manageButton = manageButton;
        this.addBookButton = addBookButton;
        this.manageAccountButton = manageAccountButton;
        this.manageAccount = manageAccount;
        this.addBooks = addBooks;
        this.manageBooks = manageBooks;
        accSearchButton.addMouseListener(this);
        addNewBook.addMouseListener(this);
        table.addMouseListener(this);
        selectBookImage.addMouseListener(this);
        searchBarAdmin.addMouseListener(this);
        searchButtonAdmin.addMouseListener(this);
        centerPanelAdmin.addMouseListener(this);
        manageAccountButton.addMouseListener(this);
        addBookButton.addMouseListener(this);
        manageButton.addMouseListener(this);
        manageAccount.addMouseListener(this);
        manageBooks.addMouseListener(this);
        addBooks.addMouseListener(this);
        
    }
    
    
    //account setting
    public MouseHandler( JButton changeLrn,JButton changeName,
            JButton changeGradeLevel,JButton changeSection,JButton changeAddress,
            JButton changeGender,JButton changeNumber,JPanel accountContainer,
            JLabel myLrn, JLabel myGender, JLabel myName,JLabel myGradeLevel,
            JLabel mySection,JTextArea myAddress,JLabel myMobileNumber,
            JButton changePass, JButton changeImage, JLabel myImageContainer,
            JFileChooser myImageChooser, ImageIcon myIcon){
        this.myIcon = myIcon;
        this.myImageChooser = myImageChooser;
        this.changeImage = changeImage;
        this.myImageContainer = myImageContainer;
        this.myGender = myGender;
        this.myLrn = myLrn;
        this.myName = myName;
        this.myGradeLevel = myGradeLevel;
        this.mySection = mySection;
        this.myAddress = myAddress;
        this.myMobileNumber = myMobileNumber;
        this.changeLrn = changeLrn;
        this.changeName = changeName;
        this.changeAddress = changeAddress;
        this.changeGender = changeGender;
        this.changeGradeLevel = changeGradeLevel;
        this.changeNumber = changeNumber;
        this.changeSection = changeSection;
        this.accountContainer = accountContainer;
        this.changePass = changePass;
        changeImage.addMouseListener(this);
        changePass.addMouseListener(this);
        changeAddress.addMouseListener(this);
        changeGender.addMouseListener(this);
        changeGradeLevel.addMouseListener(this);
        changeLrn.addMouseListener(this);
        changeName.addMouseListener(this);
        changeNumber.addMouseListener(this);
        changeSection.addMouseListener(this);
    }
    
    
    //return books
    public MouseHandler( JPanel returnBooks[], JLabel returnBookTitle[], 
             JLabel returnBookImage[], JLabel returnBookDate[],
             int borrowedBookCount, JPanel returnContainer,JPanel returnBookDetailPanel[],
             JScrollPane retunScroll,  JButton returnBack[],
             JButton returnReturnBook[],JButton returnRenewBook[], int currentLrn,
             JButton bottomButtons[], JLabel bookDetailQuantity[], int bookCount){
        this.returnBookDetailPanel = returnBookDetailPanel;
        this.borrowedBookCount = borrowedBookCount;
        this.returnBookDate = returnBookDate;
        this.returnBookImage = returnBookImage;
        this.returnBookTitle = returnBookTitle;
        this.returnBooks = returnBooks;
        this.returnContainer = returnContainer;
        this.returnScroll = retunScroll;
        this.returnBack = returnBack;
        this.returnRenewBook = returnRenewBook;
        this.returnReturnBook = returnReturnBook;
        this.currentLrn = currentLrn;
        this.bottomButtons = bottomButtons;
        this.bookDetailQuantity = bookDetailQuantity;
        
        for(int i = 0; i <4;i++){
            bottomButtons[i].addMouseListener(this);
        }
        for (int i = 0 ; i < bookCount; i++){
            bookDetailQuantity[i].addMouseListener(this);
        }
        
        try {
            for(int i = 0; i < 100;i++){
            returnBack[i].addMouseListener(this);
            returnRenewBook[i].addMouseListener(this);
            returnReturnBook[i].addMouseListener(this);
            returnBookImage[i].addMouseListener(this);
            returnBookTitle[i].addMouseListener(this);
            returnBooks[i].addMouseListener(this);
            returnBookDate[i].addMouseListener(this);
            returnBookDetailPanel[i].addMouseListener(this);
            
            }
            retunScroll.addMouseListener(this);
            returnContainer.addMouseListener(this);
        } catch (Exception e) {
        }
       
  
    }
    //borrow books
    public MouseHandler(JLabel[] bookTitle, JPanel[] books, 
            JLabel[] imageContainerBook, int bookCount, JScrollPane  scrollpane,
            JPanel bookDetail[], JButton backDetail[], JPanel bookPanel,
            JButton borrowButton[],JButton searchButton,JTextField searchBar,
            int currentUserlrn,int borrowedBookNumber,String borrowedBookTitle,
            Date startDate,Date endDate , JLabel bookDetailQuantity[], 
             JComboBox bookDetailBorrowDate[]) throws ClassNotFoundException, SQLException{
        
        
        this.borrowedBookNumber = borrowedBookNumber;
        this.borrowedBookTitle = borrowedBookTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scrollPane = scrollpane;
        this.bookDetail = bookDetail;
        this.bookTitle = bookTitle;
        this.books = books;
        this.imageContainerBook = imageContainerBook;
        this.bookCount = bookCount;
        this.backDetail = backDetail;
        this.bookPanel = bookPanel;
        this.borrowButton = borrowButton;
        this.searchBar = searchBar;
        this.searchButton = searchButton;
        this.bookDetailQuantity = bookDetailQuantity;
        this.bookDetailBorrowDate = bookDetailBorrowDate;
        for (int i = 0; i < bookCount; i++) {
            bookTitle[i].addMouseListener(this);
            books[i].addMouseListener(this);
            imageContainerBook[i].addMouseListener(this);
            bookDetail[i].addMouseListener(this);
            backDetail[i].addMouseListener(this);
            borrowButton[i].addMouseListener(this);
            bookDetailBorrowDate[i].addMouseListener(this);
        }
        
        
        
        searchButton.addMouseListener(this);
       
        scrollpane.addMouseListener(this);
        bookPanel.addMouseListener(this);
    }
    
    public MouseHandler(JPanel signUpPanel,JTextField lrn,JTextField Fname,JTextField Lname,JTextArea address,
                            JTextField Mnumber,JPasswordField pass1,JPasswordField pass2, JComboBox gradeLevel, JComboBox section,
                            JComboBox middleName, JComboBox gender, JButton signIn, JButton signUp,
                            JTextField lrnLogin, JTextField passLogin,
                            JLabel signUpImageContainer){
        errorBorder = new LineBorder(Color.RED, 1);
        normalBorder = new LineBorder(Color.BLACK,1);
        this.lrn = lrn;
        this.Fname = Fname;
        this.Lname = Lname;
        this.address = address;
        this.Mnumber = Mnumber;
        this.pass1 = pass1;
        this.pass2 = pass2;
        this.signUpPanel = signUpPanel;
        this.gradeLevel = gradeLevel;
        this.section = section;
        this.middleName = middleName;
        this.gender = gender;
        this.signIn = signIn;
        this.signUp = signUp;
        this.lrnLogin = lrnLogin;
        this.passLogin = passLogin;
        this.signUpImageContainer = signUpImageContainer;
        signUpImageContainer.addMouseListener(this);
        lrnLogin.addMouseListener(this);
        passLogin.addMouseListener(this);
        signUp.addMouseListener(this);
        signIn.addMouseListener(this);
        lrn.addMouseListener(this);
        Fname.addMouseListener(this);
        Lname.addMouseListener(this);
        address.addMouseListener(this);
        Mnumber.addMouseListener(this);
        pass1.addMouseListener(this);
        pass2.addMouseListener(this);
        signUpPanel.addMouseListener(this);
        gradeLevel.addMouseListener(this);
        section.addMouseListener(this);
        middleName.addMouseListener(this);
        gender.addMouseListener(this);
    }
    

    @Override
    public void mouseClicked(MouseEvent me) {    
        //admin panel
        if(me.getSource() == manageButton){
            System.out.println("manage books");
            manageBooks.setVisible(true);
            addBooks.setVisible(false);
            manageAccount.setVisible(false);
            manageButton.setBorder(selectedBorder);
            addBookButton.setBorder(notSelectedBorder);
            manageAccountButton.setBorder(notSelectedBorder);
            
            database.openConnection(tableModel, table);
        }
        if(me.getSource() == addBookButton){
            System.out.println("add books");
            addBooks.setVisible(true);
            manageAccount.setVisible(false);
            manageBooks.setVisible(false);
            manageButton.setBorder(notSelectedBorder);
            addBookButton.setBorder(selectedBorder);
            manageAccountButton.setBorder(notSelectedBorder);
        }
        if(me.getSource() == manageAccountButton){
             System.out.println("manage account");
            manageBooks.setVisible(false);
            addBooks.setVisible(false);
            manageAccount.setVisible(true);
            manageButton.setBorder(notSelectedBorder);
            addBookButton.setBorder(notSelectedBorder);
            manageAccountButton.setBorder(selectedBorder);
            database.openConnection(accModel, accTable, manageAccPassField);
        }
        //admin Panel search books
        if(me.getSource() == searchButtonAdmin){
            database.openConnection(searchBarAdmin, tableModel, table);
        }
        //admin Panel Search Account
        if(me.getSource() == accSearchButton){
            database.openConnection(accSearchField, accModel, accTable,1);
        }
        
        
        //admin panel addbook
        if(me.getSource() == selectBookImage){
            int choice =imageChooser.showOpenDialog(centerPanelAdmin);
            if(choice == JFileChooser.APPROVE_OPTION){
                //System.out.println(imageChooser.getSelectedFile());
                
                sourceFile = new File(imageChooser.getSelectedFile().toString());
                String mimetype= new MimetypesFileTypeMap().getContentType(sourceFile);
                String type = mimetype.split("/")[0];
                System.out.println(mimetype.split("/")[0]);
                if(type.equals("image")){
                try {
                    beforeImage = ImageIO.read(new File(imageChooser.getSelectedFile().toString()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                
                Image newImage =beforeImage.getScaledInstance(120, 180, Image.SCALE_DEFAULT);
                
                bookIcon = new ImageIcon(newImage);
                imagePreview.setIcon(bookIcon);
                imagePreview.setText("");
                }else{
                    JOptionPane.showMessageDialog(addBooks, "Please select an Image "
                            + "file format `.jpg` ", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if(me.getSource() == addNewBook){
          
            if(!StringUtils.isEmptyOrWhitespaceOnly(addBookTitle.getText())||
                   !StringUtils.isEmptyOrWhitespaceOnly(addBookQuantity.getText())||
                   !StringUtils.isEmptyOrWhitespaceOnly(addBookDescription.getText())){
                 if(imagePreview.getIcon() != null){
                     if(StringUtils.isStrictlyNumeric(addBookQuantity.getText())){
                        int bookNumber[] = null;
                        try{
                         bookNumber = new int[Database.getAllCustomer().size()];
                        for(int i=0; i< bookNumber.length; i++){
                                bookNumber[i] = Integer.parseInt(Database.getAllCustomer().get(i).getBookNumber());
                                System.out.println(bookNumber[i]);
                        }
                        Arrays.sort(bookNumber);
                        System.out.println("book number highest value: "+bookNumber[bookNumber.length -1]);
                        } catch (ClassNotFoundException | SQLException ex) {
                                Logger.getLogger(MouseHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String newImageFilename = String.valueOf(bookNumber[bookNumber.length -1] + 1)+".jpg";

                        File destination = new File("C:\\Users\\great_000\\Documents\\"
                                + "NetBeansProjects\\LibrarySystem\\src\\Images\\" + 
                                newImageFilename);
                        //copy and Rename file
                        if(sourceFile.isFile()){
                            try {
                                copyFileUsingStream(sourceFile, destination);
                            } catch (IOException ex) {
                                Logger.getLogger(MouseHandler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        database.openConnection(addBookTitle, addBookQuantity, 
                                addBookDescription, bookNumber[bookNumber.length -1] + 1,
                                imagePreview, addBooks, imageChooser);
                     }else{
                         JOptionPane.showMessageDialog(addBooks, "Quantity field "
                                 + "shoud be have Numeric Value.","Invalid Input" 
                                 ,JOptionPane.WARNING_MESSAGE);  
                        }
                    }else{
                         JOptionPane.showMessageDialog(addBooks, "Please choose a book Image",
                                 "Invalid Input", JOptionPane.WARNING_MESSAGE);    
                         }
                }else{
                    JOptionPane.showMessageDialog(addBooks, "All fiedls should not be "
                            + "empty.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                 }
        }
        
        //account setting
        try {
            String allLrn[] = new String[100];
            for(int i=0; i < Database.getAllUsers().size() -1 ;i++){
                allLrn[i] = String.valueOf(Database.getAllUsers().get(i).getLrn());
                
            }     
        if(me.getSource() == changeGender){
            accountCases = 1;
        }else if(me.getSource() == changeLrn){
            accountCases = 2;   
        }else if(me.getSource() == changeName){
            accountCases = 3;   
        }else if(me.getSource() == changeGradeLevel){
            accountCases = 4;   
        }else if(me.getSource() == changeSection){
            accountCases = 5;   
        }else if(me.getSource() == changeNumber){
            accountCases = 6;   
        }else if(me.getSource() == changePass){
            accountCases = 7;   
        }else if(me.getSource() == changeAddress){
            accountCases = 8;  
        }
        
        if(me.getSource() == changeGender | me.getSource() == changeLrn |
                me.getSource() == changeName | me.getSource() == changeGradeLevel |
                me.getSource() == changeSection | me.getSource() == changeNumber |
                me.getSource() == changeAddress | me.getSource() == changePass ){
            database.openConnection(changeGender, TheFrame.currentUsersLrn,
                    accountCases,changeLrn, accountContainer,allLrn,
                    changeName, changeGradeLevel,changeSection,
                    changeNumber,changeAddress, myGender, myLrn,myName,myGradeLevel,
                    mySection, myAddress, myMobileNumber, changePass);
        }
        if(me.getSource() == changeImage){
            int choice = myImageChooser.showOpenDialog(accountContainer);
            if(choice == JFileChooser.APPROVE_OPTION){
                File destinationFile = new File("C:\\Users\\great_000\\Documents\\"
                                + "NetBeansProjects\\LibrarySystem\\src\\Images\\" + 
                                Database.getAllUsers(TheFrame.currentUsersLrn).get(0).getStudentNumber() + 
                                ".jpg");
                System.out.println("current student number: "  + Database.getAllUsers(TheFrame.currentUsersLrn).get(0).getStudentNumber());
                File newSourceFile = myImageChooser.getSelectedFile();
                 //copy and Rename file
                        if(newSourceFile.isFile()){
                            try {
                                copyFileUsingStream(newSourceFile, destinationFile);
                            } catch (IOException ex) {
                                Logger.getLogger(MouseHandler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                 database.openConnection(myLrn, myName, myGradeLevel, mySection, 
                    myAddress, myGender, myMobileNumber, 
                    TheFrame.currentUsersLrn.intValue(), myIcon,  
                    myImageContainer);        
                }
             
        }
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(MouseHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        for (int i = 0; i < bookCount ;i++) {
            if(me.getSource() == backDetail[i]){
                   scrollPane.setViewportView(bookPanel);
                   bookDetailBorrowDate[i].setSelectedIndex(0);
               }
            
                if(me.getSource() == borrowButton[i] && borrowButton[i].isEnabled()) {
                    
                    if(bookDetailBorrowDate[i].getSelectedIndex() > 0){
                        bookDetailBorrowDate[i].setBorder(new LineBorder(Color.GRAY, 1));
                    try {
                       
                        if(!"Quantity: 0".equals(bookDetailQuantity[i].getText())){
                            borrowedBookTitle = bookTitle[i].getText();
                            
                            System.out.println(startDate);
                            
                            //end date choices
                            switch(bookDetailBorrowDate[i].getSelectedIndex()){
                                case 1: endDate.setTime(oneWeek);
                                    break;
                                    
                                case 2: endDate.setTime(twoWeek);
                                    break;
                                
                                case 3: endDate.setTime(threeWeek);
                                    break;
                                
                                case 4: endDate.setTime(fourWeek);
                                    break;
                            }
                            
                            System.out.println(endDate);
                            database.openConnection(TheFrame.currentUsersLrn, 
                                    Integer.parseInt( Database.getAllCustomer().get(i).getBookNumber()),
                                    borrowedBookTitle, startDate, endDate,
                                    Database.getAllCustomer().get(i).getQuantity(),
                                    scrollPane, bookPanel, bookDetailBorrowDate, bookCount);
                         
                            System.out.println(TheFrame.currentUsersLrn);
                            bookDetailQuantity[i].setText("Quantity: " + String.valueOf(Database.getAllCustomer().get(i).getQuantity()));
                            if(Database.getAllCustomer().get(i).getQuantity() > 0){
                                bookDetailQuantity[i].setForeground(Color.BLUE);
                            }else   bookDetailQuantity[i].setForeground(Color.RED);
                            
                            if(Database.getAllCustomer().get(i).getQuantity() <= 0){
                                borrowButton[i].setEnabled(false);
                                bookDetailBorrowDate[i].setEnabled(false);
                            }else{
                                borrowButton[i].setEnabled(true);
                                 bookDetailBorrowDate[i].setEnabled(true);
                            }
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(MouseHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                        JOptionPane.showMessageDialog(null, "Select a borrow date duration", "Invalid", 1);
                        bookDetailBorrowDate[i].setBorder(new LineBorder(Color.RED,1));
                    }
             }       
        }
          for(int i=0 ; i < borrowedBookCount; i++){
                  if(me.getSource() == returnBookDate[i] ||
                     me.getSource() == returnBookImage[i] ||
                     me.getSource() == returnBookTitle[i] ||
                     me.getSource() ==returnBooks[i]){
                     
                     returnScroll.setViewportView(returnBookDetailPanel[i]);
             }
                  
             
             if(me.getSource() == returnBack[i]){
                 returnScroll.setViewportView(returnContainer);
             }     
            
            try {
                  
             if(me.getSource() == returnReturnBook[i]){

                 System.err.println(Database.getAllCustomer().get(i).getQuantity()); 
                database.openConnection(currentLrn,Database.getAllborrowedBooks(currentLrn).get(i).getBorrowCode(), 
                    Database.getAllborrowedBooks(currentLrn).get(i).getBookNumber(),
                    Database.getAllCustomer(Database.getAllborrowedBooks(currentLrn).get(i).getBookNumber()).get(0).getQuantity() ,
                    returnScroll, bookDetailQuantity, bottomButtons
                    , returnContainer);
             }      
             
             if(me.getSource() == returnRenewBook[i]){
                 
                     
                 database.openConnection(currentLrn,Database.getAllborrowedBooks(currentLrn).get(i).getBorrowCode(),
                         Database.getAllborrowedBooks(currentLrn).get(i).getBookNumber(),
                         returnScroll, Database.getAllborrowedBooks(currentLrn).get(i).getEndDate(),
                         returnContainer, bottomButtons, Database.getAllborrowedBooks(currentLrn).get(i).getBookTitle());
             
                 
                 
             }
            } catch (ClassNotFoundException | SQLException ex) {
                             Logger.getLogger(MouseHandler.class.getName()).log(Level.SEVERE, null, ex);
                         }
          }           
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
         if(me.getSource() == searchButton){
           database.openConnection(bookTitle, searchBar, books);          
           scrollPane.setViewportView(bookPanel);
        }  
        
        for (int i = 0; i < bookCount ;i++) {
               if(me.getSource() == books[i] || me.getSource() == bookTitle[i] 
                       || me.getSource() == imageContainerBook[i]){
                   scrollPane.setViewportView(bookDetail[i]);
                   books[i].setBorder(new LineBorder(new Color(90,49,138), 1));
            }
               
        }
        
        try{
        indexChecker(section);
        indexChecker(gradeLevel);
        indexChecker(gender);
        indexChecker(middleName);
        
        if(me.getSource() == lrn){
            lrn.setCaretPosition(0);
        }
        if(me.getSource() == Fname){
            Fname.setCaretPosition(0);
        }
        if(me.getSource() == Lname){
            Lname.setCaretPosition(0);
        }
        if(me.getSource() == address){
            address.setCaretPosition(0);
        }
        if(me.getSource() == Mnumber){
            Mnumber.setCaretPosition(3);
        }
        if(me.getSource() == pass1){
            pass1.setCaretPosition(0);
        }
        if(me.getSource() == pass2){
            pass2.setCaretPosition(0);
        }
        
        
        if(me.getSource() != lrn){
            lrn.setBorder(normalBorder);
            if("".equals(lrn.getText())){
                lrn.setText("LRN");
                lrn.setForeground(Color.DARK_GRAY);
            }
        }   
        if(me.getSource() != Fname){
            if("".equals(Fname.getText())){
                Fname.setText("First Name");
                Fname.setForeground(Color.DARK_GRAY);
            }
             Fname.setBorder(normalBorder);
        }
        if(me.getSource() != Lname){
              if("".equals(Lname.getText())){
                Lname.setText("Last Name");
                Lname.setForeground(Color.DARK_GRAY);
            }
             Lname.setBorder(normalBorder);
        }
        if(me.getSource() != address){
             if("".equals(address.getText())){
                address.setText("Address");
                address.setForeground(Color.DARK_GRAY);
            }
            address.setBorder(normalBorder);
        }     
        if(me.getSource() != Mnumber){
            if("".equals(Mnumber.getText()) || "+63".equals(Mnumber.getText())){
                Mnumber.setText("Mobile Number");
                Mnumber.setForeground(Color.DARK_GRAY);
            }
             Mnumber.setBorder(normalBorder);    
        }
        
        if(me.getSource() != pass1){
            if("".equals(pass1.getText())){
                pass1.setText("Password");
                pass1.setEchoChar((char)0);
                pass1.setForeground(Color.DARK_GRAY);
            }
            pass1.setBorder(normalBorder);
        }
        if(me.getSource() != pass2){
            if("".equals(pass2.getText())){
                pass2.setText("Confirm Password");
                pass2.setEchoChar((char)0);
                pass2.setForeground(Color.DARK_GRAY);
            }
            pass2.setBorder(normalBorder);
        }
        
        if(me.getSource() != gradeLevel){
            gradeLevel.setBorder(normalBorder);
        }
        if(me.getSource() != section){
            section.setBorder(normalBorder);
        }
        if(me.getSource() != middleName){
            middleName.setBorder(normalBorder);
        }
        if(me.getSource() != gender){
            gender.setBorder(normalBorder);
        }
        
        if(me.getSource() == signIn){
            lrn.setText("LRN");
            lrn.setForeground(Color.DARK_GRAY);
            gradeLevel.setSelectedIndex(0);
            gradeLevel.setForeground(Color.DARK_GRAY);
            section.setSelectedIndex(0);
            section.setForeground(Color.DARK_GRAY);
            Lname.setText("Last Name");
            Lname.setForeground(Color.DARK_GRAY);
            Fname.setText("First Name");
            Fname.setForeground(Color.DARK_GRAY);
            middleName.setSelectedIndex(0);
            middleName.setForeground(Color.DARK_GRAY);
            gender.setSelectedIndex(0);
            gender.setForeground(Color.DARK_GRAY);
            address.setText("Address");
            address.setForeground(Color.DARK_GRAY);
            Mnumber.setText("Mobile Number");
            Mnumber.setForeground(Color.DARK_GRAY);
            pass1.setText("Password");
            pass1.setForeground(Color.DARK_GRAY);
            pass2.setText("Confirm Password");
            pass2.setForeground(Color.DARK_GRAY);
            pass1.setEchoChar((char)0);
            pass2.setEchoChar((char)0);
            signUpImageContainer.setIcon(null);
            signUpImageContainer.setText("<html> <body> <center>"
                + "Choose an Image(.jpg)</center></body> </html>");
            signUpImageContainer.setBorder(normalBorder);
        }
        
        //log in panel
        if(me.getSource() != lrnLogin){
            lrnLogin.setBorder(new LineBorder(Color.BLACK, 1));
        }
        if(me.getSource() == signUp){
            
            try {
                if("LRN".equals(lrn.getText())){
                lrn.setBorder(errorBorder);
                }else {lrn.setBorder(normalBorder);}
                if(gradeLevel.getSelectedIndex() == 0){
                    gradeLevel.setBorder(errorBorder);
                }else{gradeLevel.setBorder(normalBorder);}
                if(section.getSelectedIndex() == 0){
                    section.setBorder(errorBorder);
                }else {section.setBorder(normalBorder);}
                if("First Name".equals(Fname.getText())){
                    Fname.setBorder(errorBorder);
                }else{Fname.setBorder(normalBorder);}
                if ("Last Name".equals(Lname.getText())) {
                    Lname.setBorder(errorBorder);
                } else {  Lname.setBorder(normalBorder);}
                if(middleName.getSelectedIndex() == 0){
                    middleName.setBorder(errorBorder);
                }else{middleName.setBorder(normalBorder);}
                if(gender.getSelectedIndex() == 0){
                    gender.setBorder(errorBorder);
                }else{gender.setBorder(normalBorder);}
                if("Address".equals(address.getText())){
                    address.setBorder(errorBorder);
                }else{address.setBorder(normalBorder);}
                if("Mobile Number".equals(Mnumber.getText())){
                    Mnumber.setBorder(errorBorder);
                }else{Mnumber.setBorder(normalBorder);}
                if("Password".equals(pass1.getText())){
                    pass1.setBorder(errorBorder);
                }else{pass1.setBorder(normalBorder);}
                if("Confirm Password".equals(pass2.getText())){
                    pass2.setBorder(errorBorder);
                }else{pass2.setBorder(normalBorder);}
                if(!pass1.getText().equals(pass2.getText())){
                    pass2.setBorder(errorBorder);
                }else pass2.setBorder(normalBorder);
               if(signUpImageContainer.getIcon() == null){
                   signUpImageContainer.setBorder(errorBorder);
               }else{
                   signUpImageContainer.setBorder(normalBorder);
               }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        }catch(Exception e){
            
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
   
    
            for (int i = 0; i < bookCount; i++) {
               if(me.getSource() == books[i] || me.getSource() == bookTitle[i] 
                       || me.getSource() == imageContainerBook[i]){
                   books[i].setBorder(new LineBorder(Color.RED, 2));
                }else  books[i].setBorder(new LineBorder(new Color(90,49,138), 1));
               if(me.getSource() == backDetail[i]){
                   backDetail[i].setBorder(new LineBorder(Color.RED, 1));
               }else  backDetail[i].setBorder(new LineBorder(Color.BLACK, 1));
               if(me.getSource() == borrowButton[i] ){
                   borrowButton[i].setBorder(new LineBorder(Color.RED, 1));
               }else  borrowButton[i].setBorder(new LineBorder(Color.BLACK, 1));
               if(!borrowButton[i].isEnabled()){
                   borrowButton[i].setBorder(new LineBorder(Color.GRAY));
               }
            }
       
         for(int i=0 ; i < borrowedBookCount; i++){
             
             
             try {
                  if(me.getSource() == returnBookDate[i] ||
                        me.getSource() == returnBookImage[i] ||
                        me.getSource() == returnBookTitle[i]){
                    returnBooks[i].setBorder(new LineBorder(Color.RED, 1));
                }else {
                    returnBooks[i].setBorder(new LineBorder(Color.BLACK, 1));
                }
                     
                if(me.getSource() == returnReturnBook[i]){
                    returnReturnBook[i].setBorder(new LineBorder(Color.RED, 1));
                }else{
                    returnReturnBook[i].setBorder(new LineBorder(Color.BLACK, 1));
                }
                
                if(me.getSource() == returnRenewBook[i]){
                    returnRenewBook[i].setBorder(new LineBorder(Color.RED, 1));
                }else{
                    returnRenewBook[i].setBorder(new LineBorder(Color.BLACK, 1));
                }
                
                if(me.getSource() == returnBack[i]){
                    returnBack[i].setBorder(new LineBorder(Color.RED, 1));
                }else {
                    returnBack[i].setBorder(new LineBorder(Color.BLACK, 1));
                }
             } catch (Exception e) {
             }
               
            
         }
         
    }

    @Override
    public void mouseExited(MouseEvent me) {
                
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        
    private void indexChecker(JComboBox cbox){
   
            if(cbox.getSelectedIndex() != 0){
            cbox.setForeground(Color.BLACK);
        }else cbox.setForeground(Color.DARK_GRAY);
    
    }
  
    private static void copyFileUsingStream(File source, File dest) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    }catch (IOException e){
        System.out.println(e.getMessage() + e.getCause());
    } 
  }
    
 }
