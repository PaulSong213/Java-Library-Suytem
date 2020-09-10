/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author great_000
 */
public final class adminPanel extends JPanel{
    
    
    JPanel bottomPanel;
    JPanel centerPanel;
    JPanel topPanel;
    JLabel logoContainer;
    JLabel schoolLabel;
    ImageIcon logo;       
    Color background;
    Color topColor;
    Color buttonColor;
    JButton[] bottomButton;
    ManageBooks manageBooks = new ManageBooks();
    AddBooks addBooks = new AddBooks();
    ManageAccountAdmin manageAccount = new ManageAccountAdmin();
    public adminPanel(){
        panelProperties();
        background = new Color(185,209,217);
        topColor = new Color(18, 74, 61);
        topPanel();
        centerPanel();
        bottomPanel();
    }
    
    public void panelProperties(){
        setBackground(Color.yellow);
        setLayout(new BorderLayout());
        //setVisible(false);
    }
    public void bottomPanel(){
        bottomPanel = new JPanel(){
             @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Graphics2D g2 = (Graphics2D)g;
                g2.setColor(topColor);
                g2.drawLine(0 ,0, 3000, 0);
            }
        };
        bottomPanel.setBackground(background);
        bottomPanel.setPreferredSize(new Dimension(700, 60));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        
        buttonColor = new Color (149,169,174);
        
        bottomButton = new JButton[4];
        for(int i = 0; i < 4; i++){
            bottomButton[i] = new JButton();
            bottomButton[i].setPreferredSize(new Dimension(150, 50));
            bottomButton[i].setBackground(buttonColor);
            bottomButton[i].setForeground(Color.BLACK);
            bottomButton[i].setBorder(new LineBorder(topColor, 1));
            bottomButton[i].setFont(new Font("Arial", Font.PLAIN,14));
            bottomPanel.add(bottomButton[i]);
        }
       bottomButton[0].setBorder(new LineBorder(Color.RED, 1));
       bottomButton[0].setText("Manage Books");
       bottomButton[1].setText("Add Books");
       bottomButton[2].setText("Manage Account");
       bottomButton[3].setText("Log Out");
       add(bottomPanel, BorderLayout.SOUTH);
    }
    
    
    public void centerPanel(){
        
        
        centerPanel = new JPanel();
        centerPanel.setBackground(background);
        centerPanel.setLayout(new CardLayout());
        
        centerPanel.add(manageBooks);
        centerPanel.add(addBooks);
        centerPanel.add(manageAccount);
        
        add(centerPanel, BorderLayout.CENTER);
    }
    
    
    public void topPanel(){
        topPanel = new JPanel();
        topPanel.setBackground(topColor);
        topPanel.setPreferredSize(new Dimension(700, 60));
        
        logo =new ImageIcon("src\\Images\\logo.jpg");
        logoContainer = new JLabel(logo);
        schoolLabel = new JLabel("Admin - Sample School University");
        schoolLabel.setForeground(Color.YELLOW);
        schoolLabel.setFont(new Font("Agency FB", Font.BOLD, 34));
        
        topPanel.add(logoContainer);
        topPanel.add(schoolLabel);
        add(topPanel, BorderLayout.NORTH);
    }
}
