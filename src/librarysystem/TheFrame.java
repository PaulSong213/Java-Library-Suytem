/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author great_000
 */
public final class TheFrame extends JFrame implements ActionListener{
    
    final private String title = "Library System";
    public static int width = 700;
    final private int height = 600;
    private Image image;
    LogIn logIn = new LogIn();
    SignUp signUp = new SignUp();
    homePanel home = new homePanel();
    adminPanel admin = new adminPanel();
    BorrowBook borrow;
    Handler handler;
    MouseHandler mHandler;
    KeyHandler keyHandler;
    JPanel main;
    private float posX = -350;
    private float posY = 0;
    private float velX = 10;
    private int imgPosition = 0;
    private String imgText = "Welcome";
    private String imgState = "Sign in Now";
    private Color imgTextColor = Color.YELLOW;
    Timer timer;
    Database databse = new Database();
    public static  AtomicInteger currentUsersLrn = new AtomicInteger(0);
    
    public TheFrame() throws SQLException, ClassNotFoundException, IOException{
        this.borrow = new BorrowBook();
        frameProperties(title, width, height);

        logIn.signUp.addActionListener(this);
        signUp.logIn.addActionListener(this);
        logIn.logIn.addActionListener(this);
        home.buttons[0].addActionListener(this);
        home.buttons[1].addActionListener(this);
        home.buttons[2].addActionListener(this);
        home.buttons[3].addActionListener(this);
        admin.bottomButton[3].addActionListener(this);
        

        mHandler = new MouseHandler(admin.bottomButton[0],admin.bottomButton[1],
                admin.bottomButton[2], admin.manageBooks, admin.addBooks, 
                admin.manageAccount,  admin.centerPanel,
                admin.manageBooks.tableModel, admin.manageBooks.mainTable,
                admin.manageBooks.searchButton,admin.manageBooks.searchBar,
                admin.addBooks.titleField,admin.addBooks.quantityField,
                admin.addBooks.descriptionArea, admin.addBooks.selectFile,
                admin.addBooks.imageChooser, admin.addBooks.bookIcon,
                admin.addBooks.imagePreview, admin.addBooks.addBookButton, 
                admin.manageAccount.accTable, admin.manageAccount.accModel,
                admin.manageAccount.passField, admin.manageAccount.searchButton,
                admin.manageAccount.searchBar);
        
        mHandler = new MouseHandler(home.myAccount.changeLrn, 
                home.myAccount.changeName, home.myAccount.changeGradeLevel,
                home.myAccount.changeSection, home.myAccount.changeAddress,
                home.myAccount.changeGender, home.myAccount.changeNumber,
                home.myAccount.container, home.myAccount.myLrn,
                home.myAccount.myGender, home.myAccount.myName,
                home.myAccount.myGradeLevel,home.myAccount.mySection,
                home.myAccount.myAddress,home.myAccount.myMobileNumber,
                home.myAccount.changePass, home.myAccount.changeImage,
                home.myAccount.imageContainer, home.myAccount.imageChooser,
                home.myAccount.icon);
        

        handler = new Handler(signUp.SignUp, signUp.lrnField, signUp.gradeLevel,
                signUp.section, signUp.FirstnameField, signUp.LastnameField,
                signUp.middleInitial, signUp.gender,signUp.addressField,
                signUp.mobileNumberField, signUp.passwordField,signUp.passwordField2,
                signUp.g11, signUp.g12, signUp.Blank, this, signUp.addImage,
                signUp.imageContainer, signUp.imageChooser, signUp.icon);
        mHandler = new MouseHandler(signUp,signUp.lrnField,signUp.FirstnameField,signUp.LastnameField,signUp.addressField
                        ,signUp.mobileNumberField,signUp.passwordField, signUp.passwordField2,signUp.gradeLevel,
                        signUp.section,signUp.middleInitial, signUp.gender, signUp.logIn, signUp.SignUp,
                        logIn.lrnField, logIn.passwordField, signUp.imageContainer);
        keyHandler = new KeyHandler(signUp.lrnField, signUp.FirstnameField, signUp.LastnameField,
            signUp.addressField,signUp.mobileNumberField, signUp.passwordField,signUp.passwordField2);
       handler = new Handler( home.centerPanel, home.buttons[0],
               home.buttons[1],home.buttons[2],
               home.borrowBook,home.returnBook,
               home.myAccount);
       
        
        try {
            image =  ImageIO.read(new File("src\\images\\signUpBannerFinal.jpg"));
        } catch (Exception e) {
            System.out.println("Image not Fount");
        }
        
        main = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponents(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.drawImage(image, (int)posX, (int)posY, this);
                
                GradientPaint paint = new GradientPaint(500, 500, Color.BLUE, 100 , 100 , Color.GREEN, true);
                g2.setPaint(paint);
                g2.fillRoundRect((int)posX + 380, (int)posY +150, 250, 300, 50,50);
                
                g2.setColor(imgTextColor);
                g2.setFont(new Font("Rockwell Extra Bold",Font.BOLD,34));
                g2.drawString(imgText,(int)posX + 400, (int)posY + 250);
                g2.setFont(new Font("Rockwell Extra Bold",Font.BOLD,24));
                g2.drawString(imgState,(int)posX + 410, (int)posY + 290);
                
                
            }
        };
        MainPanel();
        add(main);
        add(home);
        add(admin);
        //setContentPane(admin);
        setContentPane(main);
        
        handler = new Handler(logIn.signUp, signUp.logIn, logIn,signUp,  
                        logIn.logIn, logIn.lrnField,logIn.passwordField, 
                        home,this, main, home.borrowBook.userName,
                        home.returnBook.topBanner, admin);
        timer = new Timer(1,this);
        timer.start();
    }
    
    public void frameProperties(String title, int width , int height){
        setTitle(title);
        setMinimumSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);
       
      
    }
    
    public void MainPanel(){
        main.setBackground(new Color(18, 74, 61));
        main.setBounds(100, 0, 600, 800);
        main.setLayout(null);
        main.add(signUp);
        main.add(logIn);
        main.setVisible(true);
    }
    
    
    //borrowed books - WRONG METHOD
    public void createBorrowedBooksReturnBooks(JPanel books[], JPanel container,
            int borrowedBookCount, JLabel bookTitle[], int lrn, JLabel bookImage[],
            JLabel bookDate[], Image image[], ImageIcon icon[],
            Color background, JPanel returnBookDetailPanel[],JScrollPane retunScroll,
             JButton returnBack[],JButton returnReturnBook[],JButton returnRenewBook[],
             JLabel returnDetailBookTitle[],JLabel returnDetailStartDate[],
             JLabel returnDetailEndDate[],JLabel returnDetailImage[],
              GridBagLayout layout, GridBagConstraints gbc) throws IOException{
        
        
         try {
             container.setPreferredSize(new Dimension(0, borrowedBookCount * 100));
             
                for(int i = 0; i < borrowedBookCount; i++){
                
                image[i] = ImageIO.read(new File("src\\images\\" + (i+1) + ".jpg"));
                icon[i] = new ImageIcon(image[i]); 
                
                returnDetailBookTitle[i] = new JLabel();
                returnDetailBookTitle[i].setText("Book Title: " +Database.getAllborrowedBooks(lrn).get(i).getBookTitle());
                returnDetailBookTitle[i].setFont(new Font ("Arial", Font.PLAIN, 16));
                returnDetailBookTitle[i].setForeground(Color.BLACK);
                returnDetailBookTitle[i].setHorizontalAlignment(SwingConstants.LEFT);
                
                returnDetailEndDate[i] = new JLabel();
                returnDetailEndDate[i].setText("Expiry Date: "+Database.getAllborrowedBooks(lrn).get(i).getEndDate().toString());
                returnDetailEndDate[i].setFont(new Font ("Arial", Font.PLAIN, 16));
                returnDetailEndDate[i].setForeground(Color.BLACK);
                returnDetailEndDate[i].setHorizontalAlignment(SwingConstants.LEFT);
                
                returnDetailStartDate[i] = new JLabel();
                returnDetailStartDate[i].setText("Borrow Date: " +Database.getAllborrowedBooks(lrn).get(i).getStartDate().toString());
                returnDetailStartDate[i].setFont(new Font ("Arial", Font.PLAIN, 16));
                returnDetailStartDate[i].setForeground(Color.BLACK);
                returnDetailStartDate[i].setHorizontalAlignment(SwingConstants.LEFT);
                
                returnDetailImage[i] = new JLabel();
                returnDetailImage[i].setIcon(icon[Database.getAllborrowedBooks(lrn).get(i).getBookNumber() - 1]);
                
                returnBack[i] = new JButton("Back");
                returnBack[i].setPreferredSize(new Dimension(130, 50));
                returnBack[i].setBorder(new LineBorder(Color.BLACK, 1));
                
                returnRenewBook[i] = new JButton("Renew Book");
                returnRenewBook[i].setPreferredSize(new Dimension(130, 50));
                returnRenewBook[i].setBorder(new LineBorder(Color.BLACK, 1));
                
                returnReturnBook[i] = new JButton("Return Book");
                returnReturnBook[i].setPreferredSize(new Dimension(120, 50));
                returnReturnBook[i].setBorder(new LineBorder(Color.BLACK, 1));
                
                returnBookDetailPanel[i] = new JPanel();
                returnBookDetailPanel[i].setBackground(background);
                
                
                returnBookDetailPanel[i].setLayout(layout);                                               //top -left- bottom - right  
                addobjects(returnDetailImage[i], returnBookDetailPanel[i], layout, gbc,        0, 0, 1, 3,    0, 0,  0, 0);
                addobjects(returnDetailBookTitle[i], returnBookDetailPanel[i], layout, gbc,    1, 1, 1, 1,    0, 0, 0, 0);
                addobjects(returnDetailStartDate[i], returnBookDetailPanel[i], layout, gbc,    1, 1, 1, 1,    40, 50, 0, 0);
                addobjects(returnDetailEndDate[i], returnBookDetailPanel[i], layout, gbc,      1, 1, 1, 1,    80, 45, 0, 0);
                addobjects(returnReturnBook[i], returnBookDetailPanel[i], layout, gbc,         0, 4, 1, 1,    30, 0, 1, 1);
                addobjects(returnRenewBook[i], returnBookDetailPanel[i], layout, gbc,          1, 4, 1, 1,    30, 1, 1, 1);
                addobjects(returnBack[i], returnBookDetailPanel[i],layout, gbc,                2, 4, 1, 1,    30, 1, 1, 1);
                
                books[i] = new JPanel();
                books[i].setBackground(background);
                books[i].setBorder(new LineBorder(Color.BLACK, 1));
                books[i].setPreferredSize(new Dimension(120,170));
                books[i].setLayout(new BorderLayout());
                
                bookTitle[i] = new JLabel();
                bookTitle[i].setText(Database.getAllborrowedBooks(lrn).get(i).getBookTitle());
                bookTitle[i].setFont(new Font ("Arial", Font.BOLD, 14));
                bookTitle[i].setHorizontalAlignment(SwingConstants.CENTER);
                bookTitle[i].setForeground(Color.BLACK);
                
                bookImage[i] = new JLabel();
                bookImage[i].setIcon(icon[Database.getAllborrowedBooks(lrn).get(i).getBookNumber() - 1]);
                
                
                bookDate[i] = new JLabel();
                bookDate[i].setText(Database.getAllborrowedBooks(lrn).get(i).getEndDate().toString());
                bookDate[i].setHorizontalAlignment(SwingConstants.CENTER);
                if(Database.getAllborrowedBooks(lrn).get(i).getStartDate().before(Database.getAllborrowedBooks(lrn).get(i).endDate)){
                    bookDate[i].setForeground(new Color(12,130,40));
                }else bookDate[i].setForeground(Color.RED);
                
                
                books[i].add(bookDate[i], BorderLayout.NORTH);
                books[i].add(bookImage[i], BorderLayout.CENTER);
                books[i].add(bookTitle[i], BorderLayout.SOUTH);
                container.add(books[i]);
                  
       }
       
        mHandler = new MouseHandler(books, bookTitle
                        ,bookImage, bookDate, borrowedBookCount, container, returnBookDetailPanel,
                        home.returnBook.scrollpane, returnBack,
                        home.returnBook.returnReturnBook, home.returnBook.returnRenewBook,
                        currentUsersLrn.intValue(), home.buttons, home.borrowBook.bookDetailQuantity,
                        home.borrowBook.bookCount);        
        container.setPreferredSize(new Dimension(0, borrowedBookCount * 50));
       } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(TheFrame.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == logIn.logIn){
            databse.openConnection(admin.manageBooks.tableModel, 
                    admin.manageBooks.mainTable);
            
            databse.openConnection(home.borrowBook.books, home.borrowBook.bookTitle,
                    home.borrowBook.scrollablePanel, home.borrowBook.imageContainerBook,
                    home.borrowBook.icon, home.borrowBook.bookImage,
                    home.borrowBook.editImage, home.borrowBook.background, 
                    home.borrowBook.titleFont, home.borrowBook.bookDetail,
                    home.borrowBook.bookDetailLayout, home.borrowBook.backDetail,
                    home.borrowBook.bookDetailBorrowDate, home.borrowBook.borrowDateChoices, 
                    home.borrowBook.bookDetailBorrow, home.borrowBook.bookDetailQuantity,
                    home.borrowBook.bookDetailDescription, home.borrowBook.bookDetailName, 
                    home.borrowBook.bookDetailImage, home.borrowBook.bookDetailDescriptionScroll, 
                    home.borrowBook.bookDetialCons);
            try {
                mHandler = new MouseHandler(home.borrowBook.bookTitle,
                        home.borrowBook.books, home.borrowBook.imageContainerBook,
                        home.borrowBook.bookCount, home.borrowBook.scrollpane,
                        home.borrowBook.bookDetail, home.borrowBook.backDetail,
                        home.borrowBook.scrollablePanel, home.borrowBook.bookDetailBorrow,
                        home.borrowBook.searchButton,home.borrowBook.searchBar,
                        BorrowBook.currentUsersLrn, home.borrowBook.borrowedBookNumber,
                        home.borrowBook.borrowedBookTitle, home.borrowBook.startDate,
                        home.borrowBook.endDate, home.borrowBook.bookDetailQuantity,
                        home.borrowBook.bookDetailBorrowDate);
                
                        for(int i=0; i < home.borrowBook.bookCount;i++){
                            home.borrowBook.bookDetailBorrow[i].addActionListener(this);
                            home.borrowBook.bookDetailBorrowDate[i].addActionListener(this);
                        }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(TheFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource() == home.buttons[0]){
             databse.openConnection(home.borrowBook.books, home.borrowBook.bookTitle,
                    home.borrowBook.scrollablePanel, home.borrowBook.imageContainerBook,
                    home.borrowBook.icon, home.borrowBook.bookImage,
                    home.borrowBook.editImage, home.borrowBook.background, 
                    home.borrowBook.titleFont, home.borrowBook.bookDetail,
                    home.borrowBook.bookDetailLayout, home.borrowBook.backDetail,
                    home.borrowBook.bookDetailBorrowDate, home.borrowBook.borrowDateChoices, 
                    home.borrowBook.bookDetailBorrow, home.borrowBook.bookDetailQuantity,
                    home.borrowBook.bookDetailDescription, home.borrowBook.bookDetailName, 
                    home.borrowBook.bookDetailImage, home.borrowBook.bookDetailDescriptionScroll, 
                    home.borrowBook.bookDetialCons);
             
              try {
                mHandler = new MouseHandler(home.borrowBook.bookTitle,
                        home.borrowBook.books, home.borrowBook.imageContainerBook,
                        home.borrowBook.bookCount, home.borrowBook.scrollpane,
                        home.borrowBook.bookDetail, home.borrowBook.backDetail,
                        home.borrowBook.scrollablePanel, home.borrowBook.bookDetailBorrow,
                        home.borrowBook.searchButton,home.borrowBook.searchBar,
                        BorrowBook.currentUsersLrn, home.borrowBook.borrowedBookNumber,
                        home.borrowBook.borrowedBookTitle, home.borrowBook.startDate,
                        home.borrowBook.endDate, home.borrowBook.bookDetailQuantity,
                        home.borrowBook.bookDetailBorrowDate);
                
                        for(int i=0; i < home.borrowBook.bookCount;i++){
                            home.borrowBook.bookDetailBorrow[i].addActionListener(this);
                            home.borrowBook.bookDetailBorrowDate[i].addActionListener(this);
                        }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(TheFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        
        if(ae.getSource() == logIn.logIn | ae.getSource() == home.buttons[2]){
            databse.openConnection(home.myAccount.myLrn, home.myAccount.myName, 
                    home.myAccount.myGradeLevel, home.myAccount.mySection, 
                    home.myAccount.myAddress, home.myAccount.myGender, 
                    home.myAccount.myMobileNumber, currentUsersLrn.intValue(),
                    home.myAccount.icon,  home.myAccount.imageContainer);
           
        }
        
        if(ae.getSource() == home.buttons[3] || 
                ae.getSource() == admin.bottomButton[3]){
            
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?",
                    "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if(confirm == 0){
                admin.setVisible(false);
                currentUsersLrn.set(0);
                logIn.setVisible(true);
                home.setVisible(false);
                main.setVisible(true);
                setSize(new Dimension(width, height));
                setResizable(false);
                setContentPane(main);
            }
        }
        
        
        if(ae.getSource() == logIn.logIn || ae.getSource() == home.buttons[1]){
            try {
                home.returnBook.bookPanel.removeAll();
                databse.openConnection(home.returnBook.scrollpane,
                        home.returnBook.books, home.returnBook.bookPanel,
                        currentUsersLrn, home.returnBook.bookTitle,
                        home.returnBook.bookImage, home.returnBook.endDate,
                        home.returnBook.bookIcon, home.returnBook.imageEdit,
                        home.returnBook.imageEdit2,home.returnBook.bookDetailPanel, home.returnBook.scrollpane,
                        home.returnBook.returnBack, home.returnBook.returnReturnBook,
                        home.returnBook.returnRenewBook,home.returnBook.returnDetailBookTitle,
                        home.returnBook.returnDetailStartDate, home.returnBook.returnDetailEndDate,
                        home.returnBook.returnDetailImage, home.returnBook.layout,
                        home.returnBook.gbc,home.buttons, home.borrowBook.bookDetailQuantity,
                        home.borrowBook.bookCount, home.returnBook.background);
                home.returnBook.bookPanel.addMouseListener(mHandler);
                
                mHandler = new MouseHandler(home.returnBook.books, 
                        home.returnBook.bookTitle, home.returnBook.bookImage,
                        home.returnBook.endDate, home.returnBook.bookCount, 
                        home.returnBook.bookPanel, home.returnBook.bookDetailPanel,
                        home.returnBook.scrollpane, home.returnBook.returnBack,
                        home.returnBook.returnReturnBook, home.returnBook.returnRenewBook,
                        currentUsersLrn.intValue(), home.buttons,
                        home.borrowBook.bookDetailQuantity, home.borrowBook.bookCount);
                
                
                System.out.println(currentUsersLrn);
                System.out.println(Database.getAllborrowedBooks(currentUsersLrn.intValue()).size());
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(TheFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource() == home.buttons[0] | ae.getSource() == home.buttons[2] ){
            home.returnBook.scrollpane.setViewportView(home.returnBook.bookPanel);    
            home.returnBook.bookPanel.removeAll();
        }
        if(ae.getSource() == home.buttons[1] | ae.getSource() == home.buttons[2]  ){
            for(int i = 0; i < home.borrowBook.bookCount; i++){
            home.borrowBook.bookDetailBorrowDate[i].setSelectedIndex(0);
            home.borrowBook.books[i].setVisible(true);
            }
            home.borrowBook.scrollpane.setViewportView(home.borrowBook.scrollablePanel);
        }
        
        if(ae.getSource() == logIn.signUp){
            imgPosition = 1;
            imgText = "Join Here";
            imgState = "Sign up Now";
            imgTextColor = Color.CYAN;
            repaint();
        }else if(ae.getSource() == signUp.logIn){
            imgPosition = 2;
            imgText = "Welcome";
            imgState = "Sign in Now";
            imgTextColor = Color.YELLOW;
            repaint();
        }
        if(imgPosition == 1){
            if(posX < 38){
            posX += velX;
             repaint();
            }
        }
        if(imgPosition == 2){
           if(posX > -350){
            posX -= velX;
            repaint();
           }
        }    
      
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

