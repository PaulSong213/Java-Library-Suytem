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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author great_000
 */
public class MyAccount extends JPanel{
    
    Color background;
    JLabel myLrn;
    JLabel myName;
    JLabel myGradeLevel;
    JLabel mySection;
    JTextArea myAddress;
    JLabel myGender;
    JLabel myMobileNumber;
    JLabel topBanner;
    JLabel myPass;
    JScrollPane scrollAddress;
    JButton changePass;
    JButton changeLrn;
    JButton changeName;
    JButton changeGradeLevel;
    JButton changeSection;
    JButton changeAddress;
    JButton changeGender;
    JButton changeNumber;
    JScrollPane mainScroll;
    JPanel container;
    JPanel topPanel;
    GridBagLayout layout;
    GridBagConstraints gbc;
    JLabel imageContainer;
    ImageIcon icon;
    JButton changeImage;
    JFileChooser imageChooser;
    public MyAccount(){
        panelProperties();
        labelProperties();
       
    }
    private void panelProperties(){
        background = new Color(185,209,217);
        setBackground(background);
    }
    
    private void labelProperties(){
        
        
        setLayout(new BorderLayout());
        topBanner = new JLabel("Profile Settings");
        topBanner.setFont(new Font("Arial", Font.PLAIN, 24));
        topBanner.setForeground(Color.BLACK);
        
        topPanel = new JPanel();
        container = new JPanel();
        myLrn = new JLabel();
        myLrn.setFont(new Font("Arial", Font.PLAIN, 16));
        myLrn.setForeground(Color.BLACK);
        myName = new JLabel();
        myName.setFont(new Font("Arial", Font.PLAIN, 16));
        myName.setForeground(Color.BLACK);
        myGradeLevel = new JLabel();
        myGradeLevel.setFont(new Font("Arial", Font.PLAIN, 16));
        myGradeLevel.setForeground(Color.BLACK);
        mySection = new JLabel();
        mySection.setFont(new Font("Arial", Font.PLAIN, 16));
        mySection.setForeground(Color.BLACK);
        myPass = new JLabel("Password: •••••••••••••");
        myPass.setFont(new Font("Arial", Font.PLAIN, 16));
        myPass.setForeground(Color.BLACK);
        
        myAddress = new JTextArea();
        myAddress.setFont(new Font("Arial", Font.PLAIN, 16));
        myAddress.setForeground(Color.BLACK);
        myAddress.setBackground(Color.LIGHT_GRAY);
        myAddress.setLineWrap(true);
        myAddress.setWrapStyleWord(true);
        myAddress.setAutoscrolls(true);
        myAddress.setEditable(false);
        myAddress.setBackground(background);
        
        scrollAddress = new JScrollPane(myAddress, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
           , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollAddress.setBackground(background);
        scrollAddress.setBorder(null);
        scrollAddress.setPreferredSize(new Dimension(250, 80));
        myGender = new JLabel();
        myGender.setFont(new Font("Arial", Font.PLAIN, 16));
        myGender.setForeground(Color.BLACK);
        myMobileNumber = new JLabel();
        myMobileNumber.setFont(new Font("Arial", Font.PLAIN, 16));
        myMobileNumber.setForeground(Color.BLACK);
        changeLrn = new JButton("Change LRN");
        changeLrn.setPreferredSize(new Dimension(180, 30));
        changeName  = new JButton("Change Name");
        changeName.setPreferredSize(new Dimension(180, 30));
        changeGradeLevel  = new JButton("Change Grade Level");
        changeGradeLevel.setPreferredSize(new Dimension(180, 30));
        changeSection = new JButton("Change Section");
        changeSection.setPreferredSize(new Dimension(180, 30));
        changeAddress = new JButton("Change Address");
        changeAddress.setPreferredSize(new Dimension(180, 30));
        changeGender  = new JButton("Change Gender");
        changeGender.setPreferredSize(new Dimension(180, 30));
        changeNumber  = new JButton("Change Mobile Number");
        changeNumber.setPreferredSize(new Dimension(180, 30));
        changePass = new JButton("Change Password");
        changePass.setPreferredSize(new Dimension(180, 30));
        changeImage = new JButton("Change Profile Picture");
        changeImage.setPreferredSize(new Dimension(180, 30));
        imageContainer = new JLabel();
        imageContainer.setPreferredSize(new Dimension(100, 100));
        imageContainer.setBorder(new LineBorder(Color.BLACK));
        imageChooser = new JFileChooser("C:\\Users\\great_000\\Desktop");
        
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        
        mainScroll = new JScrollPane(container, 
           JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScroll.setBorder(null);
        
        container.setLayout(layout);
        container.setBackground(background);
        topPanel.setBackground(background);
        topPanel.add(topBanner);
        addobjects(imageContainer, container, layout, gbc,     0,0,1,1,10,0,0,0);
        addobjects(changeImage, container, layout, gbc,        1,0,1,1,10,160,-70,0);
        addobjects(myLrn, container, layout, gbc,              0,1,1,1,10,0,0,0);
        addobjects(changeLrn, container, layout, gbc,          1,1,1,1,10,160,0,0);
        addobjects(myName, container, layout, gbc,             0,2,1,1,10,0,0,0);
        addobjects(changeName, container, layout, gbc,         1,2,1,1,10,160,0,0);
        addobjects(myGradeLevel, container, layout, gbc,       0,3,1,1,10,0,0,0);
        addobjects(changeGradeLevel, container, layout, gbc,   1,3,1,1,10,160,0,0);
        addobjects(mySection, container, layout, gbc,          0,4,1,1,10,0,0,0);
        addobjects(changeSection, container, layout, gbc,      1,4,1,1,10,160,0,0);
        addobjects(myGender, container, layout, gbc,           0,5,1,1,10,0,0,0);
        addobjects(changeGender, container, layout, gbc,       1,5,1,1,10,160,0,0);
        addobjects(myMobileNumber, container, layout, gbc,     0,6,1,1,10,0,0,0);
        addobjects(changeNumber, container, layout, gbc,       1,6,1,1,10,160,0,0);
        addobjects(myPass, container, layout, gbc,             0,7,1,1,10,0,0,0);
        addobjects(changePass, container, layout, gbc,         1,7,1,1,10,160,0,0);
        addobjects(scrollAddress, container, layout, gbc,      0,8,1,1,10,0,0,0);
        addobjects(changeAddress, container, layout, gbc,      1,8,1,1,-40,160,0,0);
        
        
        this.add(mainScroll, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    }

    //grid Bag lay out properties
    private void addobjects(Component componente, Container yourcontainer, GridBagLayout layout, GridBagConstraints gbc, 
            int gridx, int gridy, int gridwidth, int gridheight, int top, int left, int bottom, int right){

        gbc.insets = new Insets(top, left, bottom, right);
        
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
       
        layout.setConstraints(componente, gbc);
        yourcontainer.add(componente);
    }
}
