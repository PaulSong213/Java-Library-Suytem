/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;


import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author great_000
 */
public class KeyHandler implements KeyListener{

    JTextField lrn;
    JTextField fName;
    JTextField lName;
    JTextArea address;
    JTextField mNumber;
    JPasswordField pass1;
    JPasswordField pass2;
    public KeyHandler(JTextField lrn, JTextField fName,JTextField lName,
        JTextArea address,JTextField mNumber,JPasswordField pass1,JPasswordField pass2 ) {
        this.lrn = lrn;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.mNumber = mNumber;
        this.pass1 = pass1;
        this.pass2 = pass2;
        lrn.addKeyListener(this);
        fName.addKeyListener(this);
        lName.addKeyListener(this);
        address.addKeyListener(this);
        mNumber.addKeyListener(this);
        pass1.addKeyListener(this);
        pass2.addKeyListener(this);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getSource() == lrn){
        if("LRN".equals(lrn.getText())){
            clearText(lrn);
         }
        }
        
        if(ke.getSource() == fName){
        if("First Name".equals(fName.getText())){
            clearText(fName);
         }
        }
        
        if(ke.getSource() == lName){
        if("Last Name".equals(lName.getText())){
            clearText(lName);
         }
        }
        
        if(ke.getSource() == address){
        if("Address".equals(address.getText())){
            address.setText("");
            address.setForeground(Color.BLACK);
        }
        }
        
        if(ke.getSource() == mNumber){
        if("Mobile Number".equals(mNumber.getText())){
              mNumber.setText("+63");
              mNumber.setForeground(Color.BLACK);
              mNumber.setCaretPosition(3);
          }
        }
        
        if(ke.getSource() == pass1){
        if("Password".equals(pass1.getText())){
            clearText(pass1);
            pass1.setEchoChar('•');
        }
        }
        
        if(ke.getSource() == pass2){
        if("Confirm Password".equals(pass2.getText())){
            clearText(pass2);
            pass2.setEchoChar('•');
        }
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void clearText(JTextField text){
        text.setText("");
        text.setForeground(Color.BLACK);
        text.setCaretPosition(0);
    }
}
