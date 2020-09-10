/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import com.mysql.cj.util.StringUtils;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author great_000
 */
public final class ManageAccountAdmin extends JPanel{
    Color background;
    JPanel topPanel;
    JPanel centerPanel;
    JTable accTable;
    JScrollPane scrollPane;
    DefaultTableModel accModel;
    Color topColor;
    Database database = new Database();
    JButton updateImage;
    ImageIcon icon;
    JLabel imageContainer;
    boolean imageSave = false;
    JFileChooser  fileChooser;
    String fileChosen;
    JPasswordField passField;
    JButton showPass;
    ImageIcon hideIcon;
    ImageIcon showIcon;
    JTextField lrnField;
    JTextField searchBar;
    JButton searchButton;
    ImageIcon searchLogo;
    public ManageAccountAdmin(){
        
        panelProperties();
        topPanel();
        centerPanel();
    }
    private void panelProperties(){
        background = new Color(185,209,217);
        setBackground(background);
        setVisible(false);
        setLayout(new BorderLayout());
    }
    
    private void centerPanel(){
        topColor = new Color(18, 74, 61);
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        accTable = new JTable(){
          @Override
            public boolean isCellEditable(int row, int column) {
               //cell 5 and up are only editable
              return column >= 7;
            }
   
        };
        
        scrollPane = new JScrollPane(accTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        accModel = new DefaultTableModel(new String[]{"LRN", "Password", 
            "Student No.","Full Name", "Gender", "Grade & Section","Address" ,
            "Contact No." , "Update Account", "Delete Account"}, 0);
        accTable.setRowHeight(20);
        accTable.setFont(new Font("Arial", Font.PLAIN, 14));
        accTable.setBorder(new LineBorder(Color.BLACK));
        accTable.setGridColor(Color.DARK_GRAY);
        accTable.setBackground(background);
        accTable.setForeground(Color.BLACK);
        accTable.setSelectionBackground(Color.WHITE);
        
        
        accTable.setModel(accModel);
        accTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
        accTable.getTableHeader().setBackground(topColor);
        accTable.getTableHeader().setForeground(Color.WHITE);
        
        accTable.getColumn("Update Account").setCellRenderer(new ManageAccountAdmin.ButtonRenderer());
        accTable.getColumn("Update Account").setCellEditor(new   ManageAccountAdmin.ButtonEditor(new JCheckBox()));
        accTable.getColumn("Delete Account").setCellRenderer(new ManageAccountAdmin.ButtonRenderer());
        accTable.getColumn("Delete Account").setCellEditor(new   ManageAccountAdmin.ButtonEditor(new JCheckBox()));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        DefaultTableCellRenderer passRenderer = new DefaultTableCellRenderer();
        passRenderer.setHorizontalAlignment( JLabel.CENTER );
        passRenderer.setForeground(background);
        passRenderer.setVisible(true);
        
        
        DefaultCellEditor c = new DefaultCellEditor(new JPasswordField());
        
       
        
        for(int i = 0; i < accTable.getColumnCount();i++){
            accTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
           
        }
        accTable.getColumn("Password").setCellRenderer(passRenderer);
        accTable.getColumn("Password").setCellEditor(c);
       
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private void topPanel(){
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100, 40));
        topPanel.setBackground(background);
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
        
        panel.setBackground(background);
        panel.setPreferredSize(new Dimension(350, 250));
        
       
        lrnField = new JTextField();
        showPass = new JButton();
        showPass.setPreferredSize(new Dimension(20, 18));
        showPass.addActionListener(passAction);
        passField = new JPasswordField();
        
        imageContainer = new JLabel();

        imageContainer.setPreferredSize(new Dimension(100, 100));
        imageContainer.setBorder(new LineBorder(Color.BLACK));
        lrnField.setPreferredSize(new Dimension(200, 20));
        passField.setPreferredSize(new Dimension(178, 20));   
         hideIcon = new ImageIcon("src\\Images\\hideIcon.jpg");
         showIcon = new ImageIcon("src\\Images\\showIcon.jpg");
        showPass.setIcon(hideIcon);
        
        
        JLabel labels[] = new JLabel[5];
        for(int i = 0 ; i < 2; i++){
            labels[i] = new JLabel();
            labels[i].setFont(new Font("Arial", Font.PLAIN, 14));
            labels[i].setForeground(Color.BLACK);
        }
        for(int i = 2; i < labels.length; i++){
            labels[i] = new JLabel();
            labels[i].setFont(new Font("Arial", Font.PLAIN, 12));
            labels[i].setForeground(Color.BLACK);
        }
        labels[0].setText("LRN: ");
        labels[1].setText("Password: ");        
        panel.setLayout(layout);
        
         addobjects(panel, imageContainer, layout, gbc,    1,0,1,3,0,0,20,-50 , GridBagConstraints.EAST);
        addobjects(panel, labels[2], layout, gbc,          2,0,1,1,0,55,0,0 , GridBagConstraints.WEST);
        addobjects(panel, labels[3], layout, gbc,          2,1,1,1,0,55,0,0 , GridBagConstraints.WEST);
        addobjects(panel, labels[4], layout, gbc,          2,2,1,1,-70,55,0,0 , GridBagConstraints.WEST);
        addobjects(panel, labels[0], layout, gbc,          1,3,1,1,0,0,10,0  , GridBagConstraints.EAST);
        addobjects(panel, labels[1], layout, gbc,          1,4,1,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(panel, lrnField, layout, gbc,           2,3,2,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(panel, passField, layout, gbc,          2,4,1,1,0,0,10,0 , GridBagConstraints.EAST);
        addobjects(panel, showPass, layout, gbc,          3,4,1,1,0,0,10,0 , GridBagConstraints.EAST);
       
        
        if (isPushed) {
            int switchcase = 0;
            
            //update book
            if(button.getText().startsWith("Click to update")){
                switchcase = 2;
            lrnField.setText(accTable.getValueAt(Integer.parseInt(button.getName()),
                    0).toString());
            passField.setText(accTable.getValueAt(Integer.parseInt(button.getName()),
                    1).toString());
            labels[2].setText(accTable.getValueAt(Integer.parseInt(button.getName()),
                    2).toString());
            labels[3].setText(accTable.getValueAt(Integer.parseInt(button.getName()),
                    4).toString());
            labels[4].setText(accTable.getValueAt(Integer.parseInt(button.getName()),
                    3).toString());
           icon = new ImageIcon("src\\images\\" +accTable.getValueAt(Integer.parseInt(button.getName()),
                    2) + ".jpg");
           Image edit = icon.getImage();
           Image edit2 = edit.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
           icon = new ImageIcon(edit2);
           imageContainer.setIcon(icon);
           
            int choice = JOptionPane.showConfirmDialog(scrollPane, panel, "Update " + 
                    accTable.getValueAt(Integer.parseInt(button.getName()) ,0), 
                    JOptionPane.OK_CANCEL_OPTION);  
                    if(choice == 0){
                       
                        if(!StringUtils.isEmptyOrWhitespaceOnly(lrnField.getText()) 
                                && !StringUtils.isEmptyOrWhitespaceOnly(passField.getText())){
                            if(StringUtils.isStrictlyNumeric(lrnField.getText()) &&
                                    lrnField.getText().length() == 5){
                               
                                database.openConnection(switchcase, lrnField, scrollPane, passField,
                                        accTable.getValueAt(Integer.parseInt(button.getName()), 0).toString());
                                JOptionPane.showMessageDialog(scrollPane, "Update Success");
                                database.openConnection(accModel, accTable, passField);
                                
                            }else{
                                JOptionPane.showMessageDialog(scrollPane, 
                                        "LRN field should have 5 numeric input.",
                                        "Invalid input" ,JOptionPane.WARNING_MESSAGE);
                            }
                      }else{
                            JOptionPane.showMessageDialog(scrollPane, "All field should not "
                                    + "be empty.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                        } 
                   }
            }
            
            //delete account
            if(button.getText().startsWith("Click to delete")){
                switchcase = 1;
                int del =JOptionPane.showConfirmDialog(scrollPane, "Are you sure "
                        + "you want to delete " + 
                        accTable.getValueAt(Integer.parseInt(button.getName()), 0) +
                        " from database?"
                        , "Delete Book", JOptionPane.YES_NO_OPTION);
                if(del == 0 ){
                    database.openConnection(switchcase, lrnField, scrollPane, passField,
                            accTable.getValueAt(Integer.parseInt(button.getName()), 0).toString());
                    JOptionPane.showMessageDialog(scrollPane, 
                        accTable.getValueAt(Integer.parseInt(button.getName()), 0) +
                        " was successfully deleted.", "Update Succes", JOptionPane.PLAIN_MESSAGE);
                   
                    database.openConnection(accModel, accTable, passField);
                    
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
    
 
     ActionListener passAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
             if(passField.getEchoChar() == (char)0){
                passField.setEchoChar('•');
                 showPass.setIcon(hideIcon);
             }else{
                 passField.setEchoChar((char)0);
                
                 showPass.setIcon(showIcon);
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
