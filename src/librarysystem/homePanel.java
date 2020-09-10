/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author great_000
 */
public class homePanel extends JPanel {
    
    Color background;
    Color buttonColor;
    Color topColor;
    ImageIcon logo;
    JButton[] buttons = new JButton[4];
    JPanel buttonPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    GridBagLayout gbLayout;
    GridBagConstraints gbc;
    Database database = new Database();
    ReturnBook returnBook = new ReturnBook();
    BorrowBook borrowBook = new BorrowBook();
    MyAccount myAccount = new MyAccount();
    public homePanel() throws IOException{
        

        background = new Color(185,209,217);
        buttonColor = new Color (149,169,174);
        topColor = new Color(18, 74, 61);
        logo = new ImageIcon("src\\Images\\logo.jpg");
        panelProperties();
        setLayout(new BorderLayout());
        buttonsPanel(buttons, buttonPanel,this);
        topPanel(topPanel, this);
        centerPanel(centerPanel, this);
        //setVisible(false);
        
    }   
    private void panelProperties(){
        setMinimumSize(new Dimension(800,600));
        setBackground(background);
    }
    
    
    
    private void buttonsPanel(JButton[] b, JPanel component,JPanel container){
        component = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Graphics2D g2 = (Graphics2D)g;
                g2.setColor(topColor);
                g2.drawLine(0 ,0, 3000, 0);
            }
        };
        component.setBackground(background);
        component.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        component.setPreferredSize(new Dimension(700, 60));
        for (int j = 0; j < 4; j++) {
            b[j] = new JButton();
            b[j].setPreferredSize(new Dimension(150, 50));
            b[j].setBackground(buttonColor);
            b[j].setForeground(Color.BLACK);
            b[j].setBorder(new LineBorder(topColor, 1));
            b[0].setBorder(new LineBorder(Color.RED, 1));
            b[j].setFont(new Font("Arial", Font.PLAIN,14));
            component.add(b[j]);
        }
            b[0].setText("Borrow Book");
            b[1].setText("Return/Renew Book");
            b[2].setText("Manage Account");
            b[3].setText("Log Out");
            
        container.add(component, BorderLayout.SOUTH);
    }
    
    private void topPanel(JPanel component, JPanel container){
        component.setBackground(topColor);
        component.setPreferredSize(new Dimension(700, 60));
        JLabel logo = new JLabel(this.logo);
        component.add(logo);
        JLabel schoolName = new JLabel("Sample School University");
        schoolName.setForeground(Color.WHITE);
        schoolName.setFont(new Font("Agency FB", Font.BOLD, 34));
        component.add(schoolName);
        
        container.add(component, BorderLayout.NORTH);
    }
    
     private void centerPanel(JPanel component, JPanel container){
        component.setBackground(background);
        component.setLayout(new CardLayout());
       
        component.add(borrowBook);
        component.add(returnBook);
        component.add(myAccount);
        
        container.add(component);  
    }
   

}
