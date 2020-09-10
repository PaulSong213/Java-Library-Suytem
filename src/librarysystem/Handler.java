/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author great_000
 */
public class Handler implements ActionListener{

    
   private JPanel adminPanel;
   private JButton logIn;
   private JButton signUp;
   private JPanel main;
   private JPanel logInPanel;
   private JPanel signUpPanel;
   private JPanel home;
   private JFrame frame;
   private JComboBox gradeLevel;
   private JComboBox section;
   private String[] gradeLevels;
   private DefaultComboBoxModel sections11;
   private DefaultComboBoxModel sections12;
   private DefaultComboBoxModel Blank;
   private JButton loginLOGIN;
   private JTextField lrnLogIn;
   private JTextField passLogIn;
   private JButton signupSIGNUP;
   private JTextField lrn;
   private JTextField Fname;
   private JTextField Lname;
   private JTextArea address;
   private JTextField Mnumber;
   private JPasswordField pass1;
   private JPasswordField pass2;
   private JComboBox middleName;
   private JComboBox gender;
   private static boolean  loggedIn = false;
   private Database database = new Database();
   private JLabel userName;
   private JPanel centerPanel;
   private JPanel returnBook;
   private JPanel borrowBook;
   private JPanel renewBook;
   private JPanel myAccount;
   private JButton returnBookButton;
   private JButton borrowBookButton;
   private JButton myAccountButton;
   private JLabel returnTopBanner;
   private LineBorder selectedBorder = new LineBorder(Color.RED, 1);
   private LineBorder notSelectedBorder = new LineBorder(new Color(18, 74, 61), 1);
   private String test;
   private JButton signUpAddImage;
   private JLabel signUpImageContainer;
   private JFileChooser imageChooser;
   private Image beforeImage;
   private File sourceFile = null;
   private ImageIcon signUpIcon; 
   private int modifiedStudentNum[] = new int[200]; 
    //logging in
    public Handler(JButton logIn, JButton signUp ,  JPanel logInPanel, JPanel signUpPanel,
                   JButton loginLOGIN, JTextField lrnLogIn, JTextField passwordLogIn,
                   JPanel home,JFrame frame, JPanel main, JLabel userName, 
                   JLabel returnTopBanner, JPanel adminPanel ) throws IOException{
        
        this.adminPanel = adminPanel;
        this.returnTopBanner = returnTopBanner;
        this.logIn = logIn;
        this.signUp = signUp;
        this.logInPanel = logInPanel;
        this.signUpPanel = signUpPanel;
        this.loginLOGIN = loginLOGIN;
        this.lrnLogIn = lrnLogIn;
        this.passLogIn = passwordLogIn;
        this.home = home;
        this.main  = main;
        this.frame = frame;
        this.userName = userName;
        loginLOGIN.addActionListener(this);
        logIn.addActionListener(this);
        signUp.addActionListener(this);
    
    }
    
    //sign up requirement to make accounts
    public Handler(JButton signupSIGNUP, JTextField lrnSignUp, JComboBox gradeLevel,
                   JComboBox section, JTextField firstName, JTextField lastName,
                   JComboBox middleName, JComboBox gender, JTextArea address,
                   JTextField mobileNumber, JPasswordField pass1, JPasswordField pass2,
                   DefaultComboBoxModel sections11, DefaultComboBoxModel sections12,
                   DefaultComboBoxModel Blank, JFrame frame, JButton signUpAddImage,
                   JLabel signUpImageContainer,JFileChooser imageChooser, 
                   ImageIcon signUpIcon){
        this.signUpIcon = signUpIcon;
        this.imageChooser = imageChooser;
        this.signUpAddImage = signUpAddImage;
        this.signUpImageContainer = signUpImageContainer;
        this.signupSIGNUP = signupSIGNUP;
        this.frame = frame;
        this.lrn = lrnSignUp;
        this.gradeLevel = gradeLevel;
        this.section = section;
        this.Fname = firstName;
        this.Lname = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.address = address;
        this.Mnumber = mobileNumber;
        this.pass1 = pass1;
        this.pass2 = pass2;
        this.sections11 = sections11;
        this.sections12 = sections12;
        this.Blank = Blank;
        signUpAddImage.addActionListener(this);
        gradeLevel.addActionListener(this);
        signupSIGNUP.addActionListener(this);
        section.addActionListener(this);
    }
    
    //home panel switch tabs
    public Handler( JPanel centerPanel, JButton borrowBookButton, 
            JButton returnBookBottun, 
            JButton myAccountButton ,JPanel borrowBook, JPanel returnBook,
             JPanel myAccount){
        
        this.centerPanel = centerPanel;
        this.borrowBookButton = borrowBookButton;
        this.returnBookButton = returnBookBottun;
        
        this.myAccountButton = myAccountButton;
        this.returnBook = returnBook;
        this.borrowBook = borrowBook;
        this.myAccount = myAccount;
        returnBookBottun.addActionListener(this);
        borrowBookButton.addActionListener(this);
        myAccountButton.addActionListener(this);
    }

    

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        if(ae.getSource() == borrowBookButton){
            borrowBook.setVisible(true);
            returnBook.setVisible(false);
            myAccount.setVisible(false);
            borrowBookButton.setBorder(selectedBorder);
            returnBookButton.setBorder(notSelectedBorder);
            myAccountButton.setBorder(notSelectedBorder);
        }
        if(ae.getSource() == returnBookButton){
            borrowBook.setVisible(false);
            returnBook.setVisible(true);
            myAccount.setVisible(false);
            borrowBookButton.setBorder(notSelectedBorder);
            returnBookButton.setBorder(selectedBorder);
            myAccountButton.setBorder(notSelectedBorder);
            
        }
       
        if(ae.getSource() == myAccountButton){
            borrowBook.setVisible(false);
            returnBook.setVisible(false);
            myAccount.setVisible(true);
            borrowBookButton.setBorder(notSelectedBorder);
            returnBookButton.setBorder(notSelectedBorder);
            myAccountButton.setBorder(selectedBorder);
        }
        
        if(ae.getSource() == logIn){
            lrnLogIn.setText("");
            passLogIn.setText("");
            signUpPanel.setVisible(true);
            logInPanel.setVisible(false);
        }else if(ae.getSource() == signUp){
            logInPanel.setVisible(true);
            signUpPanel.setVisible(false);
        }
        try {
            if(ae.getSource() == gradeLevel){
            section.setSelectedIndex(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            switch (gradeLevel.getSelectedIndex()) {
                case 0:
                    section.setModel(Blank);
                    break;
                case 1:
                    section.setModel(sections11);
                    break;
                case 2:
                    section.setModel(sections12);
                    break;
                case 3:
                    section.setModel(Blank);
                    break;
                default:
                   break;
            }
        }catch(Exception e){
            
        }
        //add Image
        if(ae.getSource() == signUpAddImage){
            int choice =imageChooser.showOpenDialog(signUpPanel);
            if(choice == JFileChooser.APPROVE_OPTION){
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
                
                signUpIcon = new ImageIcon(newImage);
                signUpImageContainer.setIcon(signUpIcon);
                signUpImageContainer.setText("");
                }else{
                    JOptionPane.showMessageDialog(signUpPanel, "Please select an Image "
                            + "file format `.jpg` ", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        //sign up requirement to log in
        if(ae.getSource() == signupSIGNUP){
            try {
                for(int i=0; i < Database.getAllUsers().size();i++){
                    System.out.println(Database.getAllUsers().get(i).getStudentNumber());
                    modifiedStudentNum[i] = Integer.parseInt(Database.getAllUsers().get(i).getStudentNumber().substring(1));
                    System.out.println(modifiedStudentNum[i]);
                }
                
                if(pass1.getText() == null ? pass2.getText() == null :
                        pass1.getText().equals(pass2.getText()) &&
                        !"LRN".equals(lrn.getText()) &&
                        gradeLevel.getSelectedIndex() != 0 &&
                        section.getSelectedIndex() != 0 &&
                        !"First Name".equals(Fname.getText()) &&
                        !"Last Name".equals(Lname.getText()) &&
                        !"Address".equals(address.getText()) &&
                        !"Mobile Number".equals(Mnumber.getText()) &&
                        !"Password".equals(pass1.getText()) &&
                        !"Confirm Password".equals(pass2.getText()) &&
                         signUpImageContainer.getIcon() != null){
                    System.out.println("Added to database");
                    database.openConnection(loggedIn, lrn, gradeLevel,section,Fname,
                            Lname,middleName,gender, address, Mnumber, pass1, pass2,
                            "s" + String.valueOf(maxValue(modifiedStudentNum) + 1));
                    
                    String newImageFilename = "s" + String.valueOf(maxValue(modifiedStudentNum) + 1) +".jpg";

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
                    JOptionPane.showMessageDialog(frame, "Account Created", "Registered", 1);
                    sourceFile = null;
                    signUpImageContainer.setIcon(null);
                    signUpImageContainer.setText("<html> <body> <center>"
                     + "Choose an Image(.jpg)</center></body> </html>");
                    
                    lrn.setText("LRN");
                    gradeLevel.setSelectedIndex(0);
                    section.setSelectedIndex(0);
                    Fname.setText("First Name");
                    Lname.setText("Last Name");
                    middleName.setSelectedIndex(0);
                    gender.setSelectedIndex(0);
                    address.setText("Address");
                    Mnumber.setText("Mobile Number");
                    pass1.setText("Password");
                    pass2.setText("Confirm Password");
                    pass1.setEchoChar((char)0);
                    pass2.setEchoChar((char)0);
                }else { System.out.println("Not added to database ");
                JOptionPane.showMessageDialog(frame,"Invalid Entry."
                        + " Check on the red boxes.","Invalid",0);
                }
            } catch (ClassNotFoundException ex) {  
                Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  
        if(ae.getSource() == loginLOGIN){
        
            try {
                database.openConnection(lrnLogIn, passLogIn, loggedIn, logInPanel
                    ,home, main,frame, userName, TheFrame.currentUsersLrn,
                    returnTopBanner, adminPanel);
                
            } catch (Exception e) {
            }
                
                
            }
        
        

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  public int maxValue(int array[]){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
          list.add(array[i]);
        }
       return Collections.max(list);
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

