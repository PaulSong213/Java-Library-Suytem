/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author great_000
 */
public class ReturnBook extends JPanel{
    
    Color background;
    JPanel topPanel;
    JPanel bookPanel;
    JPanel books[];
    JLabel bookTitle[];
    JLabel bookImage[];
    JLabel endDate[];
    JPanel bookDetailPanel[];
    Database database = new Database();
    String test;
    int bookCount = 100;
    JScrollPane scrollpane;
    
    JButton returnBack[];
    JButton returnReturnBook[];
    JButton returnRenewBook[];
    JLabel returnDetailBookTitle[];
    JLabel returnDetailStartDate[];
    JLabel returnDetailEndDate[];
    JLabel returnDetailImage[];
    GridBagLayout layout; 
    GridBagConstraints gbc; 
    JLabel topBanner;
    ImageIcon bookIcon[];
    Image imageEdit[];
    Image imageEdit2[];
    public ReturnBook(){
        this.topBanner = new JLabel();
        this.layout = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.returnDetailImage = new JLabel[100];
        this.returnDetailBookTitle = new JLabel[100];
        this.returnDetailEndDate = new JLabel[100];
        this.returnDetailStartDate = new JLabel[100];
        this.bookDetailPanel = new JPanel[100];
        this.bookTitle = new JLabel[100];
        this.bookImage = new JLabel[100];
        this.endDate = new JLabel[100];
        this.returnBack = new JButton[100];
        this.returnRenewBook = new JButton[100];
        this.returnReturnBook = new JButton[100];
        this.scrollpane = new JScrollPane();
        this.books = new JPanel[100];
        this.bookPanel = new JPanel();
        this.topPanel = new JPanel();
        this.bookIcon = new ImageIcon[100];
        this.imageEdit = new Image[100];
        this.imageEdit2 = new Image[100];
        panelProperties();
        bookPanel();
        topPanel();

    } 
    private void panelProperties(){
        this.background = new Color(185,209,217);
        setBackground(Color.BLUE);
        setVisible(false);
        setLayout(new BorderLayout());
        
      
    }
    private void bookPanel(){
       bookPanel.setBackground(background);
       bookPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

       scrollpane.setBorder(null);
       scrollpane.setViewportView(bookPanel);
       scrollpane = new JScrollPane();
       this.add(scrollpane, BorderLayout.CENTER);
    }
    private void topPanel(){
       topPanel.setBackground(background);
       topPanel.setPreferredSize(new Dimension(0, 40));
       topBanner.setForeground(Color.BLACK);
       topBanner.setFont(new Font("Arial", Font.PLAIN, 18));
       topPanel.add(topBanner);
       this.add(topPanel, BorderLayout.NORTH);
    }
}
