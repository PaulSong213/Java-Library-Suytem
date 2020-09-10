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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author great_000
 */
public final class AddBooks extends JPanel{
    
    
    Color background;
    JPanel topPanel;
    JPanel centerPanel;
    JLabel addBookBanner;
    JTextField titleField;
    JTextField quantityField;
    JTextArea descriptionArea;
    JFileChooser imageChooser;
    JButton selectFile;
    JLabel titleLabel;
    JLabel quantityLabel;
    JLabel descriptionLabel;
    JLabel imageLabel;
    JButton addBookButton;
    GridBagLayout layout;
    GridBagConstraints gbc;
    JLabel imagePreview;
    ImageIcon bookIcon;
    JScrollPane scroll;
    Font fieldFont;
    public AddBooks(){
        background = new Color(185,209,217);
        panelProperties();
        topPanel();
        centerPanel();
    }
    public void panelProperties(){
        setBackground(background);
        setVisible(false);
        setLayout(new BorderLayout());
    }
    
    public void centerPanel(){
        fieldFont = new Font("Arial", Font.PLAIN, 16);
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        
        centerPanel = new JPanel();
        centerPanel.setBackground(background);
        centerPanel.setLayout(layout);
        titleField = new JTextField(25);
        titleField.setFont(fieldFont);
        
        quantityField = new JTextField(25);
        quantityField.setFont(fieldFont);
        
        descriptionArea = new JTextArea(7, 25); 
        descriptionArea.setFont(fieldFont);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setAutoscrolls(true);  
        descriptionArea.setBorder(new LineBorder(Color.GRAY));
        
        titleLabel = new JLabel("Title: ");
        titleLabel.setFont(fieldFont);
        titleLabel.setForeground(Color.BLACK);
        
        quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setFont(fieldFont);
        quantityLabel.setForeground(Color.BLACK);
        
        descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setFont(fieldFont);
        descriptionLabel.setForeground(Color.BLACK);
        
        
        imageLabel = new JLabel("Book image: ");
        imageLabel.setFont(fieldFont);
        imageLabel.setForeground(Color.BLACK);
        imageChooser = new JFileChooser("C:\\Users\\great_000\\Desktop\\");
        addBookButton = new JButton("Add Book");
        addBookButton.setPreferredSize(new Dimension(180, 45));
        addBookButton.setFont(fieldFont);
        
        selectFile = new JButton("Select File");
        selectFile.setPreferredSize(new Dimension(120, 25));
        imagePreview = new JLabel();
        imagePreview.setMaximumSize(new Dimension(120, 180));
        imagePreview.setPreferredSize(new Dimension(120, 180));
        imagePreview.setBorder(new LineBorder(Color.BLACK));
        imagePreview.setText("Choose an Image");
        imagePreview.setHorizontalAlignment(SwingConstants.CENTER);
      
        addobjects(centerPanel, titleLabel, layout, gbc,       0,0,1,1,50,0,10,0 , GridBagConstraints.EAST);
        addobjects(centerPanel, titleField, layout, gbc,       1,0,1,1,50,0,10,0 , GridBagConstraints.EAST);
        addobjects(centerPanel, quantityLabel, layout, gbc,    0,1,1,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(centerPanel, quantityField, layout, gbc,    1,1,1,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(centerPanel, descriptionLabel, layout, gbc, 0,2,1,1,-110,0,10,0 , GridBagConstraints.EAST);
        addobjects(centerPanel, descriptionArea, layout, gbc,  1,2,1,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(centerPanel, imageLabel, layout, gbc,       0,3,1,2,-200,0,10,0 , GridBagConstraints.EAST);
        addobjects(centerPanel, selectFile, layout, gbc,       1,4,1,1,0,0,20,0 , GridBagConstraints.WEST);
        addobjects(centerPanel, imagePreview, layout, gbc,     1,3,1,1,0,0,0,0 , GridBagConstraints.WEST);
        addobjects(centerPanel, addBookButton, layout, gbc,    0,6,3,1,0,0,30,0 , GridBagConstraints.CENTER);
        
        scroll = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }
    
    public void topPanel(){
        topPanel = new JPanel();
        topPanel.setBackground(background);
        topPanel.setPreferredSize(new Dimension(100, 40));
        
        addBookBanner = new JLabel("Add a Book");
        addBookBanner.setFont(new Font("Agency FB", Font.PLAIN, 26));
        addBookBanner.setForeground(Color.BLACK);
        
        topPanel.add(addBookBanner);
        add(topPanel,BorderLayout.NORTH );
    }
    
     //grid Bag lay out properties
    private void addobjects( Container yourcontainer,Component componente, GridBagLayout layout, GridBagConstraints gbc, 
            int gridx, int gridy, int gridwidth, int gridheight, int top, int left, int bottom, int right, int anch){

        gbc.insets = new Insets(top, left, bottom, right);
        
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        
        gbc.anchor = anch;
        
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
       
        layout.setConstraints(componente, gbc);
        yourcontainer.add(componente);
    }
}
