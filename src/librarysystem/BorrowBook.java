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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author great_000
 */
public class BorrowBook extends JPanel{
    
    Database database = new Database();
    Color background;
    JLabel bookTitle[]; 
    JPanel books[];
    JLabel bookDetailName[];
    JLabel bookDetailImage[];
    JLabel bookDetailQuantity[];
    JTextArea bookDetailDescription[];
    JPanel bookDetail[];
    JLabel imageContainerBook[];
    Image bookImage[];
    Image editImage[];
    ImageIcon icon[];
    ImageIcon searchLogo;
    JButton searchButton= new JButton();
    JButton backDetail[];
    JButton bookDetailBorrow[];
    JScrollPane bookDetailDescriptionScroll[];
    JTextField searchBar;
    int bookCount =100;
    GridBagLayout bookDetailLayout;
    GridBagConstraints bookDetialCons;
    JLabel userName;
    String borrowDateChoices[];
    static int currentUsersLrn;
    int borrowedBookNumber;
    String borrowedBookTitle;
    Date startDate;
    Date endDate;
    JComboBox bookDetailBorrowDate[];
    JPanel bookPanel;
    JPanel topPanel;
    JPanel scrollablePanel;
    JScrollPane scrollpane;
    long currentTime = System.currentTimeMillis();
    Calendar now = Calendar.getInstance();
    Font titleFont;
    public BorrowBook() throws IOException{
        
        background = new Color(185,209,217);
        titleFont = new Font ("Arial", Font.BOLD, 14);
        setBackground(background);
        setLayout(new BorderLayout());
     
        //database.openConnection();
        System.err.println(currentUsersLrn);    
        this.bookDetailLayout = new GridBagLayout();
        this.bookDetialCons = new GridBagConstraints();
        this.userName = new JLabel();
        this.borrowDateChoices = new String[5];
        this.startDate = new Date(currentTime);
        this.endDate =  new Date(currentTime);
        try {
            this.bookDetailBorrowDate = new JComboBox[bookCount];
            this.bookDetailDescriptionScroll = new JScrollPane[bookCount];
            this.bookDetailBorrow = new JButton[bookCount];
            this.bookDetailQuantity = new JLabel[bookCount];
            this.bookDetailDescription = new JTextArea[bookCount];
            this.bookDetailImage = new JLabel[bookCount];
            this.bookDetailName = new JLabel[bookCount];
            this.backDetail = new JButton[bookCount];
            this.bookDetail = new JPanel[bookCount];
            this.bookImage = new Image[bookCount];
            this.editImage = new Image[bookCount];
            this.icon = new ImageIcon[bookCount];
            this.books = new JPanel[bookCount];
            this.imageContainerBook =  new JLabel[bookCount];
            this.bookTitle = new JLabel[bookCount];
            bookCount = Database.getAllCustomer().size();
            System.out.println();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BorrowBook.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        bookImage[0] = ImageIO.read(new File("src\\images\\searchLogo.png"));
        searchLogo = new ImageIcon(bookImage[0]);
        
        topPanel = new JPanel();
        
        
        //createBooks(this, scrollablePanel, bookPanel,scrollpane);
        createBooksNew();
        createTopPanel(this, topPanel, searchLogo);
    }
    private void createBooksNew(){
        bookPanel = new JPanel();
        bookPanel.setBackground(background);
        bookPanel.setLayout(new BorderLayout());
        scrollablePanel = new JPanel();
        scrollablePanel.setBackground(background);
        scrollablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        scrollablePanel.setPreferredSize(new Dimension(0, bookCount * 55));
        scrollpane = new JScrollPane();
        scrollpane = new JScrollPane(scrollablePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        bookPanel.add(scrollpane,BorderLayout.CENTER);
        add(bookPanel, BorderLayout.CENTER);
    }
    
    
    private void createBooks(JPanel container, JPanel component, JPanel scroll,
            JScrollPane scrollpane){
        component.setBackground(background);
        component.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        component.setPreferredSize(new Dimension(0, bookCount * 55));
        for (int i = 0; i < bookCount; i++) {
            bookDetail[i] = new JPanel();
            bookDetail[i].setBackground(background);
            bookDetail[i].setLayout(bookDetailLayout);
            
            backDetail[i] = new JButton("Back");

            books[i] = new JPanel();
            books[i].setBorder(new LineBorder(Color.BLACK, 1));
            books[i].setBackground(background);
            books[i].setPreferredSize(new Dimension(120,160));
            books[i].setLayout(new BorderLayout(0, 0));
            
            arrayString(borrowDateChoices, "Borrow Duration","1 Week","2 Weeks","3 Weeks","4 Weeks");
            bookDetailBorrowDate[i] = new JComboBox(borrowDateChoices);
                      
            
            try {
                bookDetailBorrow[i] = new JButton("Borrow Book");
                bookDetailQuantity[i] = new JLabel("Quantity: " + String.valueOf(Database.getAllCustomer().get(i).getQuantity()));
                bookDetailDescription[i] = new JTextArea(Database.getAllCustomer().get(i).getDescription(), 5,1);
                bookDetailName[i] = new JLabel(Database.getAllCustomer().get(i).getTitle());
                bookTitle[i] = new JLabel(Database.getAllCustomer().get(i).getTitle());
                bookTitle[i].setFont(new Font ("Arial", Font.BOLD, 14));
                bookTitle[i].setHorizontalAlignment(SwingConstants.CENTER);
                bookTitle[i].setForeground(Color.BLACK);
                bookImage[i] = ImageIO.read(new File("src\\images\\" + (i+1) + ".jpg"));
                
                editImage[i] = bookImage[i].getScaledInstance(120, 180, Image.SCALE_DEFAULT);
                icon[i] = new ImageIcon(editImage[i]);
                imageContainerBook[i] = new JLabel(icon[i]);
                imageContainerBook[i].setPreferredSize(new Dimension(120, 160));
                bookDetailImage[i] = new JLabel(icon[i]);
                bookDetailBorrow[i].setBorder(new LineBorder(Color.BLACK, 1));
                bookDetailBorrow[i].setPreferredSize(new Dimension(120, 40));
                if(Database.getAllCustomer().get(i).getQuantity() > 0){
                    bookDetailQuantity[i].setForeground(Color.BLUE);  
                }else   bookDetailQuantity[i].setForeground(Color.RED);  
                
                if(Database.getAllCustomer().get(i).getQuantity() == 0){
                    bookDetailBorrowDate[i].setEnabled(false);
                    bookDetailBorrow[i].setEnabled(false);
                }else{
                    bookDetailBorrowDate[i].setEnabled(true);
                    bookDetailBorrow[i].setEnabled(true);
                }
                
            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(BorrowBook.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(component, "ERROR");
                Logger.getLogger(BorrowBook.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            
            addobjects(bookDetailName[i], bookDetail[i], bookDetailLayout, bookDetialCons,                 0,0,2,1, 0 , 0 ,  30,    0);
            addobjects(bookDetailImage[i], bookDetail[i], bookDetailLayout, bookDetialCons,                0,1,1,1, 0 , 0 ,   0,   20);
            addobjects(bookDetailQuantity[i], bookDetail[i], bookDetailLayout, bookDetialCons,             0,2,1,1, 0 , 0 ,   0,   30);
            addobjects(bookDetailBorrowDate[i], bookDetail[i], bookDetailLayout, bookDetialCons,           0,3,1,1, 0 , 0 ,   0,   30);
            addobjects(bookDetailDescriptionScroll[i], bookDetail[i], bookDetailLayout, bookDetialCons,    1,1,1,3, 0 , 0 ,   0,    0);
            addobjects(bookDetailBorrow[i], bookDetail[i], bookDetailLayout, bookDetialCons,               0,4,1,1, 20, 0 ,   0, -200);
            addobjects(backDetail[i], bookDetail[i], bookDetailLayout, bookDetialCons,                     1,4,1,1, 20, 0 ,   0,   50);
            

            books[i].add(imageContainerBook[i], BorderLayout.CENTER);
            books[i].add(bookTitle[i], BorderLayout.SOUTH);
            component.add(books[i]);
  
        }
        scrollpane.setLayout(new ScrollPaneLayout());
        scrollpane.setViewportView(component);
        scrollpane.setBorder(null);
        
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBackground(Color.YELLOW);
        scroll.setLayout(new BorderLayout());
        scroll.add(scrollpane,BorderLayout.CENTER);
        container.add(scroll, BorderLayout.CENTER);
    }
    
    private void createTopPanel(JPanel container, JPanel component
            , ImageIcon logo){
            searchBar = new JTextField(15);
            
        component.setLayout(bookDetailLayout);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton = new JButton();
        searchButton.setIcon(logo);
        searchButton.setBackground(background);
        searchButton.setPreferredSize(new Dimension(30, 30));
        userName.setFont(new Font("Arial", Font.PLAIN, 16));
        userName.setForeground(Color.BLUE);
        //userName.setPreferredSize(new Dimension(300,100));

        addobjects(userName, component, bookDetailLayout, bookDetialCons,     0,0,1,1,0,-500,0,0);
        addobjects(searchBar, component, bookDetailLayout, bookDetialCons,    1,0,1,1,0,0,0,-400);
        addobjects(searchButton, component, bookDetailLayout, bookDetialCons, 2,0,1,1,0,0,0,-650);
    
        component.setBackground(background);
        component.setPreferredSize(new Dimension(100, 40));
        container.add(component, BorderLayout.NORTH);
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
    private void arrayString(String[] S, String s0, String s1, String s2, String s3,
            String s4){
         S[0] = s0;
         S[1] = s1;
         S[2] = s2;
         S[3] = s3;
         S[4] = s4;
     }
}
