/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import com.mysql.cj.util.StringUtils;
import java.lang.Object;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TableUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;


/**
 *
 * @author great_000
 */
public final class ManageBooks extends JPanel{
    
    Color background;
    Color topColor;
    JScrollPane scroll;
    JTable bookTable;
    JPanel topPanel;
    JPanel centerPanel;
    JTable mainTable;
    JButton updateButton[];
    JButton deleteButton[];
    DefaultTableModel tableModel;
    int bookCount;
    JTextField searchBar;
    JButton searchButton;
    ImageIcon searchLogo;
    Database database = new Database();
    JButton updateImage;
    ImageIcon icon;
    JLabel imageContainer;
    boolean imageSave = false;
    JFileChooser  fileChooser;
    String fileChosen;
    public ManageBooks(){
        background = new Color(185,209,217);
        topColor = new Color(18, 74, 61);
        try {
            this.bookCount = Database.getAllCustomer().size();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManageBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
        panelProperties();
        topPanel();
        centerPanel();
    }
    
    public void panelProperties(){
        setBackground(background);
        setVisible(true);
        setLayout(new BorderLayout());
        
    }
    public void centerPanel(){
        centerPanel = new JPanel();
        centerPanel.setBackground(background);
        centerPanel.setLayout(new GridLayout());
        
        tableModel = new DefaultTableModel(new String[]{"Title", "Book Number",
        "Quantity", "Description", "Update Book", "Delete Book"},0);
       
        
        mainTable = new JTable(tableModel){
          @Override
            public boolean isCellEditable(int row, int column) {
               //cell 5 and up are only editable
               return column >= 4;
            }
            
        };
        mainTable.setRowHeight(20);
        mainTable.setFont(new Font("Arial", Font.PLAIN, 14));
        mainTable.setBorder(new LineBorder(Color.BLACK));
        mainTable.setGridColor(Color.DARK_GRAY);
        mainTable.setBackground(background);
        mainTable.setForeground(Color.BLACK);
        mainTable.setSelectionBackground(Color.WHITE);
        mainTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
        mainTable.getTableHeader().setBackground(topColor);
        mainTable.getTableHeader().setForeground(Color.WHITE);
        
        mainTable.getColumn("Update Book").setCellRenderer(new ButtonRenderer());
        mainTable.getColumn("Update Book").setCellEditor(new ButtonEditor(new JCheckBox()));
        mainTable.getColumn("Delete Book").setCellRenderer(new ButtonRenderer());
        mainTable.getColumn("Delete Book").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        for(int i = 0; i < mainTable.getColumnCount();i++){
            mainTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
           
        }
        scroll = new JScrollPane(mainTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        scroll.setBackground(background);
        scroll.setBorder(null);
        centerPanel.add(scroll);
        add(centerPanel, BorderLayout.CENTER);
    }
    public void topPanel(){
        topPanel = new JPanel();
        topPanel.setBackground(background);
        topPanel.setPreferredSize(new Dimension(100, 40));
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchLogo = new ImageIcon("src\\images\\searchLogo.png");
        searchBar = new JTextField(15);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 18));
        
        searchButton = new JButton();
        searchButton.setIcon(searchLogo);
        searchButton.setBackground(background);
        searchButton.setPreferredSize(new Dimension(30, 30));
        
        topPanel.add(searchBar);
        topPanel.add(searchButton);
        
        add(topPanel,BorderLayout.NORTH);
    }
    
    //table buttons
    class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setPreferredSize(new Dimension(100, 100));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        setName(String.valueOf(row));
        
        return this;
    }
    
}

    class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);

            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    fireEditingStopped();
                } catch (Exception ee) {
                }
                
            }
        });
    
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        button.setBackground(topColor);
        button.setForeground(Color.WHITE);
        button.setName(String.valueOf(row));
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panel = new JPanel();
        
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(350, 450));
        
       
        JTextField titleField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextArea descriptionArea = new JTextArea();
        imageContainer = new JLabel();
        updateImage = new JButton("Change Book Image");
        updateImage.setPreferredSize(new Dimension(120, 25));
        updateImage.setFont(new Font("Arial", Font.PLAIN, 9));
        updateImage.setForeground(Color.BLACK);
        
        JScrollPane descriptionAreaScroll = new JScrollPane(descriptionArea);
        
        imageContainer.setPreferredSize(new Dimension(120, 160));
        imageContainer.setBorder(new LineBorder(Color.BLACK));
        titleField.setPreferredSize(new Dimension(250, 20));
        quantityField.setPreferredSize(new Dimension(250, 20));
        descriptionAreaScroll.setPreferredSize(new Dimension(250, 140));
        descriptionArea.setRows(7);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setAutoscrolls(true);   
        
        JLabel labels[] = new JLabel[4];
        for(int i = 0 ; i < labels.length; i++){
            labels[i] = new JLabel();
            labels[i].setFont(new Font("Arial", Font.PLAIN, 14));
            labels[i].setForeground(Color.BLACK);
        }
        labels[0].setText("Title: ");
        labels[1].setText("Quantity: ");        
        labels[2].setText("Description: "); 
        labels[3].setText("Book Image: ");
        panel.setLayout(layout);
        
        addobjects(panel, labels[0], layout, gbc,       1,1,1,1,0,0,10,0  , GridBagConstraints.EAST);
        addobjects(panel, labels[1], layout, gbc,       1,2,1,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(panel, labels[2], layout, gbc,       1,3,1,1,-100,0,30,0 , GridBagConstraints.EAST);
        addobjects(panel, labels[3], layout, gbc,       1,4,1,2,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(panel, titleField, layout, gbc,      2,1,1,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(panel, quantityField, layout, gbc,   2,2,1,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(panel, descriptionAreaScroll, layout, gbc,     2,3,1,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(panel, imageContainer, layout, gbc,     2,4,1,1,0,0,0,0 , GridBagConstraints.CENTER);
         addobjects(panel, updateImage, layout, gbc,     2,5,1,1,0,0,0,0 , GridBagConstraints.CENTER);
        if (isPushed) {
            int switchcase = 0;
            
            //update book
            if(button.getText().startsWith("Click to update")){
                switchcase = 2;
            titleField.setText(mainTable.getValueAt(Integer.parseInt(button.getName()),
                    0).toString());
            quantityField.setText(mainTable.getValueAt(Integer.parseInt(button.getName()),
                    2).toString());
           descriptionArea.setText(mainTable.getValueAt(Integer.parseInt(button.getName()),
                    3).toString());
           icon = new ImageIcon("src\\images\\" +mainTable.getValueAt(Integer.parseInt(button.getName()),
                    1) + ".jpg");
           Image edit = icon.getImage();
           Image edit2 = edit.getScaledInstance(120, 160,  java.awt.Image.SCALE_SMOOTH);
           icon = new ImageIcon(edit2);
           imageContainer.setIcon(icon);
           updateImage.addActionListener(action);
           
            int choice = JOptionPane.showConfirmDialog(scroll, panel, "Update " + 
                    mainTable.getValueAt(Integer.parseInt(button.getName()) ,0), 
                    JOptionPane.OK_CANCEL_OPTION);  
                    if(choice == 0){
                       
                        if(!StringUtils.isEmptyOrWhitespaceOnly(titleField.getText()) 
                                && !StringUtils.isEmptyOrWhitespaceOnly(quantityField.getText())
                                && !StringUtils.isEmptyOrWhitespaceOnly(descriptionArea.getText())){
                            if(StringUtils.isStrictlyNumeric(quantityField.getText())){
                                database.openConnection(mainTable.getValueAt(Integer.parseInt(button.getName()), 1).toString()
                                        , switchcase, titleField, quantityField, descriptionArea);
                                imageSave = true;
                                updateImage.doClick();
                                database.openConnection(tableModel, mainTable);
                                searchBar.setText("");
                                JOptionPane.showMessageDialog(scroll, "Update Success");
                            }else{
                                JOptionPane.showMessageDialog(scroll, "Quantity fied "
                                        + "should be numeric input.","Invalid input" ,JOptionPane.WARNING_MESSAGE);
                            }
                      }else{
                            JOptionPane.showMessageDialog(scroll, "All field should not "
                                    + "be empty.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                        } 
                   }
            }
            
            //delete book
            if(button.getText().startsWith("Click to delete")){
                switchcase = 1;
                int del =JOptionPane.showConfirmDialog(scroll, "Are you sure "
                        + "you want to delete " + 
                        mainTable.getValueAt(Integer.parseInt(button.getName()), 0) +
                        " from database?"
                        , "Delete Book", JOptionPane.YES_NO_OPTION);
                if(del == 0 ){
                    JOptionPane.showMessageDialog(scroll, 
                        mainTable.getValueAt(Integer.parseInt(button.getName()), 0) +
                        " was successfully deleted.", "Update Succes", JOptionPane.PLAIN_MESSAGE);
                    
                    database.openConnection(mainTable.getValueAt(Integer.parseInt(button.getName()), 1).toString()
                    , switchcase, titleField, quantityField, descriptionArea);
                    database.openConnection(tableModel, mainTable);
                    searchBar.setText("");
                }
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
    
    
     ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            fileChooser = new JFileChooser("C:\\Users\\great_000\\Desktop\\");
             
            if(!imageSave){
            int choice = fileChooser.showOpenDialog(scroll);
            if(choice == 0){
                System.out.println(fileChooser.getSelectedFile());
                fileChosen = fileChooser.getSelectedFile().toString();
                String mimetype= new MimetypesFileTypeMap().getContentType(fileChooser.getSelectedFile());
                String type = mimetype.split("/")[0];
                if(type.equals("image")){
                    icon = new ImageIcon(fileChooser.getSelectedFile().toString());
                    Image edit = icon.getImage();
                    Image edit2 = edit.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(edit2);
                    imageContainer.setIcon(icon);
                }else{
                    JOptionPane.showMessageDialog(scroll, "Please select an Image "
                            + "file format `.jpg` ", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
                
            }
           }else if (imageSave){

                System.out.println("Saving New Image2");

                System.out.println(fileChosen);
                
                System.out.println(mainTable.getValueAt(Integer.parseInt(button.getName()),
                    1).toString());
                File sourceFile = new File(fileChosen);                
                File destination = new File("C:\\Users\\great_000\\Documents\\"
                                + "NetBeansProjects\\LibrarySystem\\src\\Images\\" + 
                                mainTable.getValueAt(Integer.parseInt(button.getName()),
                                1).toString()+ ".jpg" );
                
                if(sourceFile.isFile()){
                            try {
                                copyFileUsingStream(sourceFile, destination);
                                System.out.println("copied");
                            } catch (IOException ex) {
                                Logger.getLogger(MouseHandler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                }else{
                    System.err.println("not a file");
                }
            imageSave = false;
           } 
            
            
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
 }
    
    
    //grid Bag lay out properties
    private void addobjects( Container yourcontainer,Component componente, GridBagLayout layout, GridBagConstraints gbc, 
            int gridx, int gridy, int gridwidth, int gridheight, int top, int left, int bottom, int right,
            int anch){

        gbc.insets = new Insets(top, left, bottom, right);
        
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        
        gbc.anchor = anch;
        
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
       
        layout.setConstraints(componente, gbc);
        yourcontainer.add(componente);
    }
    
    private void copyFileUsingStream(File source, File dest) throws IOException {
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
