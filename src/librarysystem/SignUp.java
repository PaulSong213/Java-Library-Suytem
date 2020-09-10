/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author great_000
 */
public final class SignUp extends JPanel{
    
    JOptionPane dialog;
    JTextField lrnField;
    JTextField LastnameField;
    JTextField FirstnameField;
    JComboBox middleInitial;
    JComboBox gradeLevel;
    JComboBox section;
    JComboBox gender;
    JTextArea addressField;
    JTextField mobileNumberField;
    JPasswordField passwordField;
    JPasswordField passwordField2;
    JButton logIn;
    JButton SignUp;
    Color labelColor;
    Color backgroundColor;
    Color fieldColor;
    Font labelFont;
    Border fieldBorder;
    String[] middleInitials;
    String[] gradeLevels;
    String[] sections11;
    String[] sections12;
    String[] genders;
    String[] blank;
    GridBagConstraints gbc;
    DefaultComboBoxModel g11;
    DefaultComboBoxModel g12;
    DefaultComboBoxModel Blank;
    FieldListener fieldListener;
    JPanel mainPanel;
    JScrollPane scroll;
    JFileChooser imageChooser;
    JLabel imageContainer;
    JButton addImage;
    JLabel imageInfo;
    ImageIcon icon;
    public SignUp(){
        gbc = new GridBagConstraints();
        labelColor = Color.WHITE;
        backgroundColor = new Color(18, 74, 61);
        fieldColor =  new Color(156, 247, 185);
        fieldBorder = new LineBorder(Color.BLACK, 1);
        labelFont = new Font("Agency FB",Font.PLAIN,15);
        
        this.setLayout(new BorderLayout());
        panelProperties();
        
        middleInitials = new String[28];
        gradeLevels = new String[4];
        sections11 = new String[4];
        sections12 = new String[4];
        blank = new String[2];
        genders = new String[3];
        
        arrayString(middleInitials,"Middle Initial" ,"A","B", "C","D","E","F", "G","H","I","J", "K","L","M","N", "O","P","Q","R", "S","T","U","V", "W","X","Y", "Z", "Ñ");
        arrayString(gradeLevels,"Grade Level" ,"Grade 11", "Grade 12" , "None");
        arrayString(sections11,"Section","Kamyas", "Bayabas", "Atis");
        arrayString(sections12,"Section", "Aso", "Pusa", "Daga");
        arrayString(genders, "Gender" ,"Male", "Female");
        arrayString(blank, "Section", "N/A");
        
        g11 = new DefaultComboBoxModel(sections11);
        g12 = new DefaultComboBoxModel(sections12);
        Blank = new DefaultComboBoxModel(blank);
        lrnField = new JTextField(18);
        LastnameField = new JTextField(12);
        FirstnameField = new JTextField(12);
        middleInitial = new JComboBox();
        gradeLevel = new JComboBox();
        section = new JComboBox();
        gender = new JComboBox();
        addressField = new JTextArea(2,25);
        mobileNumberField = new JTextField(15);
        passwordField = new JPasswordField(15);
        passwordField2 = new JPasswordField(15);
        SignUp = new JButton();
        logIn = new JButton();
        lrnField.setDocument(new FieldListener(5,lrnField));
        mobileNumberField.setDocument(new FieldListener(13, mobileNumberField));
        comboBoxProperties(middleInitial, Color.WHITE, new Font("Arial", Font.PLAIN,12), Color.BLACK, middleInitials);
        comboBoxProperties(gender, Color.WHITE, new Font("Arial", Font.PLAIN,12), Color.BLACK, genders);
        comboBoxProperties(gradeLevel, Color.WHITE, new Font("Arial", Font.PLAIN,12), Color.BLACK, gradeLevels);
        comboBoxProperties(section, Color.WHITE, new Font("Arial", Font.PLAIN,12), Color.BLACK, blank);
        textFieldProperties(lrnField, fieldColor, fieldBorder, 20, 25, "LRN");
        textFieldProperties(FirstnameField, fieldColor, fieldBorder, 20 , 25, "First Name");
        textFieldProperties(LastnameField, fieldColor, fieldBorder, 20 , 25, "Last Name");
        textFieldProperties(mobileNumberField, fieldColor, fieldBorder, 20 , 25, "Mobile Number");
        textFieldProperties(addressField, fieldColor, fieldBorder, "Address");
        textFieldProperties(passwordField, fieldColor, fieldBorder, 20, 25, "Password");
        textFieldProperties(passwordField2, fieldColor, fieldBorder, 20, 25, "Confirm Password");
        buttonProperties(SignUp, "Sign up", Color.CYAN, fieldBorder, Color.BLACK,new Font("Agency FB",Font.PLAIN,20) );
        buttonProperties(logIn, "Log in Instead", backgroundColor, null, Color.YELLOW, new Font("Arial",Font.PLAIN,10));
        intOnlyText(lrnField);
        intOnlyText(mobileNumberField);
        passwordHide(passwordField);
        passwordHide(passwordField2);
        addobjects(lrnField,         mainPanel, 60, 140, 220, 25);
        addobjects(gradeLevel,       mainPanel, 60, 170, 100, 20);
        addobjects(section,          mainPanel, 180, 170, 100, 20);
        addobjects(FirstnameField,   mainPanel, 30, 240, 130, 25);
        addobjects(LastnameField,    mainPanel, 180, 240, 130,25);
        addobjects(middleInitial,    mainPanel, 60, 270, 100, 20);
        addobjects(gender,           mainPanel, 180, 270, 100,20);
        addobjects(addressField,     mainPanel, 30, 295, 280, 50);
        addobjects(mobileNumberField,mainPanel, 70, 350, 200, 25);
        addobjects(passwordField,    mainPanel, 30, 425, 130, 25);
        addobjects(passwordField2,   mainPanel, 180, 425, 130, 25);
        addobjects(imageContainer,   mainPanel, 80, 480, 100, 105);
        addobjects(addImage,         mainPanel, 200, 480, 100, 25);
        addobjects(imageInfo,        mainPanel, 200, 510, 100, 60);
        addobjects(SignUp,           mainPanel, 120, 630, 100, 35);
        addobjects(logIn,            mainPanel, 120, 665, 100, 15);
    }
     public void panelProperties(){
        setBounds(0, 0, 400, 600);
        setBackground(backgroundColor);
        setVisible(false);
        
        mainPanel = new JPanel(){
          @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2= (Graphics2D)g;
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRoundRect(20, 20, 300, 80, 20, 20);
       
        
         //school info seperator
        g2.drawRoundRect(22, 125, 296, 80, 20, 20);
        //personal info seperartor
        g2.drawRoundRect(22, 220, 296, 175, 20, 20);
        //account info seperator
        g2.drawRoundRect(22, 410, 296, 60, 20, 20);
        
        g2.setColor(backgroundColor);
        g2.setFont(new Font("Agency FB" , Font.BOLD , 35));
        g2.drawString("Sign Up Form", 30, 60);
        g2.setFont(new Font("Arial" , Font.PLAIN , 13));
        g2.drawString("Fill Up to create one-time account", 30, 83);
  
        g2.fillRect(28, 120, 95, 15);
        g2.fillRect(28, 215, 105, 15);
        g2.fillRect(28, 405, 100, 15);
        
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial" , Font.BOLD , 10));
        g2.drawString("School Information", 30, 128);
        g2.drawString("Personal Information", 30, 223);
        g2.drawString("Account Information", 30, 413);
    }  
        };
        
        
        mainPanel.setLayout(null);
        mainPanel.setBackground(backgroundColor);
        mainPanel.setPreferredSize(new Dimension(400, 1000));
        scroll = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setBackground(backgroundColor);
        scroll.getHorizontalScrollBar().setBackground(backgroundColor);
        scroll.setPreferredSize(new Dimension(100, 1000));
        
        imageChooser = new JFileChooser("C:\\Users\\great_000\\Desktop");
        imageContainer = new JLabel("<html> <body> <center>"
                + "Choose an Image(.jpg)</center></body> </html>", SwingConstants.CENTER);
        imageContainer.setBorder(new LineBorder(Color.BLACK));
        imageContainer.setFont(new Font("Arial", Font.PLAIN, 12));
        imageContainer.setForeground(Color.WHITE);
        
        imageInfo = new JLabel();
        imageInfo.setText("<html><body>  This will serve as your profile picture.   </body> </html>");
        imageInfo.setForeground(Color.WHITE);
        icon = new ImageIcon();
        addImage = new JButton("Add Image");
        addImage.setPreferredSize(new Dimension(100, 30));
        
        
        add(scroll, BorderLayout.CENTER);
     }
     
     private void  textFieldProperties(JTextArea t, Color color, Border border, String string){
         t.setBackground(color);
         t.setBorder(border);
         //t.setPreferredSize(new Dimension(width, height));
         t.setText(string);
         t.setLineWrap(true);
         t.setWrapStyleWord(true);
         t.setAutoscrolls(true);   
     }
     
     public void textFieldProperties(JTextField t, Color color, Border border , int width , int height,String text){
         t.setBackground(color);
         t.setBorder(border);
         t.setPreferredSize(new Dimension(width,height));
         t.setText(text);
     }
     
     public void comboBoxProperties(JComboBox b, Color color, Font font, Color fColor, String[] items){
         b.setBackground(color);
         b.setFont(font);
         b.setModel(new DefaultComboBoxModel(items));
         b.setBorder(fieldBorder);
         ((JLabel)b.getRenderer()).setHorizontalAlignment(JLabel.CENTER); //set jcombox item to center
     }
     
      
     
     public void arrayString(String[] S, String s0, String s1, String s2, String s3, String s4, String s5, String s6
                , String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15
                , String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24
                , String s25, String s26, String s27){
         S[0] = s0;    S[3] = s3;     S[6] = s6;    S[9] = s9;      S[12] = s12;    S[15] = s15;       S[18] = s18;    S[21] = s21;   S[24] = s24;   S[27] = s27;
         S[1] = s1;    S[4] = s4;     S[7] = s7;    S[10] = s10;    S[13] = s13;    S[16] = s16;       S[19] = s19;    S[22] = s22;   S[25] = s25;                  
         S[2] = s2;    S[5] = s5;     S[8] = s8;    S[11] = s11;    S[14] = s14;    S[17] = s17;       S[20] = s20;    S[23] = s23;   S[26] = s26;  
    
     }
     public void arrayString(String[] S, String s0, String s1, String s2, String s3){
         S[0] = s0;
         S[1] = s1;
         S[2] = s2;
         S[3] = s3;
     }
     public void arrayString(String[] S, String s0, String s1){
         S[0] = s0;
         S[1] = s1;
     }
     public void arrayString(String[] S, String s0, String s1, String s2){
         S[0] = s0;
         S[1] = s1;
         S[2] = s2;
     }
     
     //positioning of component
    final public void addobjects(Component componente, Container yourcontainer, int x, int y, int w, int h){

        componente.setBounds(x, y, w, h);
        yourcontainer.add(componente);
    }
    
    
    public void buttonProperties(JButton b, String text, Color color, Border border, Color fcolor, Font f){
        b.setText(text);
        b.setBackground(color);
        b.setBorder(border);
        b.setForeground(fcolor);
        b.setFont(f);
    }
    
    private void passwordHide(JPasswordField pass){
        pass.setEchoChar((char)0);
    }
    
    private void intOnlyText(JTextField t){
           
          t.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!((c >= '0') && (c <= '9') ||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE))){
                t.setBorder(new LineBorder(Color.RED, 1));
                getToolkit().beep();
                e.consume();
              }else t.setBorder(fieldBorder);
            }
          });
    }

   
}
