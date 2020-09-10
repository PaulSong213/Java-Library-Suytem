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


public  class LogIn extends JPanel implements ActionListener{
    
    private Color backgroundColor;
    private JLabel title;
    private JLabel signInLabel;
    private JLabel lrn;
    private JLabel password;
    public JTextField lrnField;
    public JPasswordField passwordField;
    public JButton logIn;
    public JButton signUp;
    private Font titleFont;
    private Font innerFont;
    private Font midFont;
    private GridBagLayout GBLayOut;
    private GridBagConstraints gbc;
    
    float posX =1;
    float posY =1;
    float velX = 20;
    Timer timer;
    boolean LogInSite = true;
    public LogIn(){
        
        innerFont = new Font("Agency FB", Font.PLAIN, 20);
        titleFont = new Font("Agency FB", Font.BOLD, 54);
        midFont = new Font("Agency FB", Font.BOLD, 35);
        backgroundColor = new Color(18, 74, 61);
        GBLayOut = new GridBagLayout();
        gbc = new GridBagConstraints();
       
        panelProperties(backgroundColor, GBLayOut);
        title = new JLabel();
        lrn = new JLabel();
        password = new JLabel();
        lrnField = new JTextField(15);
        passwordField = new JPasswordField(15);
        signUp = new JButton("Sign Up Instead");
        logIn = new JButton("Log in");
        signInLabel = new JLabel();
        
        LabelProperties(title, "Library System", titleFont, Color.WHITE);
        LabelProperties(signInLabel, "Sign In:", midFont, Color.WHITE);
        LabelProperties(lrn, "LRN:", innerFont, Color.WHITE);
        LabelProperties(password, "Password:", innerFont, Color.WHITE);
        signUp.addActionListener(this);
        buttonProperites(logIn, Color.CYAN, 100, 30, innerFont, new LineBorder(Color.BLACK, 1),Color.BLACK);
        buttonProperites(signUp, backgroundColor, 100, 15, new Font("Arial",Font.PLAIN,10), null,Color.YELLOW);
        textFieldProperteis(lrnField);
        textFieldProperteis(passwordField);
        
        lrnField.setDocument(new FieldListener(5,lrnField));
        addobjects(title,       this, GBLayOut, gbc,         0, 0, 2, 1, -180, 0  ,0 ,0);
        addobjects(lrn,       this, GBLayOut, gbc,           0, 1, 1, 1,  0, 50 ,0 ,0);
        addobjects(lrnField,       this, GBLayOut, gbc,      0, 2, 1, 1,  0 , 50  ,0 ,0);
        addobjects(password,       this, GBLayOut, gbc,      0, 3, 1, 1,  30 , 50 ,0 ,0);
        addobjects(passwordField,       this, GBLayOut, gbc, 0, 4, 1, 1,  0 , 50  ,0 ,0);
        addobjects(logIn,       this, GBLayOut, gbc,         0, 5, 2, 1,  30 , 0  ,0 ,0);
        addobjects(signUp,       this, GBLayOut, gbc,        0, 6, 2, 1,  5 , 0  ,0 ,0);
        intOnlyText(lrnField);
        timer = new Timer(1, this);
        timer.start();
        
    }
        private void intOnlyText(JTextField t){
           
          t.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!((c >= '0') && (c <= '9') ||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE))) {
                t.setBorder(new LineBorder(Color.RED, 1));
                getToolkit().beep();
                e.consume();
              }else{
                  t.setBorder(new LineBorder(Color.BLACK, 1));
              }
            }
          });
    }
   
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(156, 247, 185));
        g2.drawRoundRect(75, 150, 250, 290,50,50);
       
  
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == signUp){
           LogInSite = false;
        }
        if(!LogInSite){
            if(posX < 350){
            posX += velX;
            repaint();
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //panel properties
    final public void panelProperties(Color color, GridBagLayout layout){
        setBackground(color);
        setLayout(layout);
        setBounds(300, 0, 400, 600);
        setVisible(true);
       
    }
    
    private void textFieldProperteis(JTextField textField){
        textField.setPreferredSize(new Dimension(0, 25));
        textField.setBorder(new LineBorder(Color.BLACK, 1));
        textField.setBackground(new Color(156, 247, 185));
        textField.setFont(new Font("Arial", Font.BOLD, 15));
    }
    
      private void textFieldProperteis(JPasswordField textField){
        textField.setPreferredSize(new Dimension(0, 25));
        textField.setBorder(new LineBorder(Color.BLACK, 1));
        textField.setBackground(new Color(156, 247, 185));
        textField.setFont(new Font("Arial", Font.BOLD, 15));
    }
    
    private void buttonProperites(JButton logIn, Color color, int width, int height,Font font,Border border, Color colorFont){
        logIn.setBackground(color);
        logIn.setPreferredSize(new Dimension(width, height));
        logIn.setForeground(Color.BLACK);
        logIn.setFont(font);
        logIn.setBorder(border);
        logIn.setForeground(colorFont);
    }
    
    //grid Bag lay out properties
    final public void addobjects(Component componente, Container yourcontainer, GridBagLayout layout, GridBagConstraints gbc, 
            int gridx, int gridy, int gridwidth, int gridheight, int top, int left, int bottom, int right){

        gbc.insets = new Insets(top, left, bottom, right);
        
        gbc.ipadx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
       
        layout.setConstraints(componente, gbc);
        yourcontainer.add(componente);
    }
    
    //label properties
    final public void LabelProperties(JLabel label, String Title,Font font, Color color){
        label.setText(Title);
        label.setFont(font);
        label.setForeground(color);
    }
    
 

    
}
