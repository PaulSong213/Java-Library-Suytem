/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.util.ArrayList;

/**
 *
 * @author great_000
 */
public class bookName {
    private String title;
    private String bookNumber;
    private int Quantity;
    private String Description;
    
    public bookName(String title, String bookNumber, int Quantity,
            String Description){
        this.Quantity = Quantity;
        this.Description = Description;
        this.title = title;
        this.bookNumber = bookNumber;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    public void setQuantity(int Quantity){
        this.Quantity = Quantity;
    }
    public void setDescription(String Description){
        this.Description = Description;
    }
    
    public void setBookNumber(String bookNumber){
        this.bookNumber = bookNumber;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getBookNumber(){
        return bookNumber;
    }
    
    public int getQuantity(){
        return  Quantity;
    }
    
    public String getDescription(){
        return  Description;
    }
    
    
}
