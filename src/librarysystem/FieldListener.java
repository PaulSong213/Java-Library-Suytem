/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.*;


/**
 *
 * @author great_000
 */
class FieldListener extends PlainDocument {
    
    private int limit;
    private JTextField field;
    FieldListener(int limit, JTextField field) {
      super();
      this.limit = limit;
      this.field = field;
    }

    FieldListener(int limit, boolean upper) {
      super();
      this.limit = limit;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
      if (str == null)
        return;
      if ((getLength() + str.length()) <= limit) {
        super.insertString(offset, str, attr);
      }else{
      field.setBorder(new LineBorder(Color.RED, 1));
      }
    }
}
