/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.sql.Date;

/**
 *
 * @author great_000
 */
public class borrowedBookName {
    int borrowersLrn;
    int bookNumber;
    String bookTitle;
    Date startDate;
    Date endDate;
    int borrowCode;
    public borrowedBookName(int borrowersLrn,int bookNumber,String bookTitle,
            Date startDate, Date endDate, int borrowCode){
        this.borrowersLrn = borrowersLrn;
        this.bookNumber = bookNumber;
        this.bookTitle = bookTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.borrowCode = borrowCode;
    }
    
    public void setBorrowCode(int borrowCode){
        this.borrowCode = borrowCode;
    }
    public void setLrn(int borrowersLrn){
        this.borrowersLrn = borrowersLrn;
    }
    public void setBookNumber(int bookNumber){
        this.bookNumber = bookNumber;
    }
    
    public void setBookTitle(String bookTitle){
        this.bookTitle = bookTitle;
    }
    
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    
    public int getBorrowCode(){
        return borrowCode;
    }
    
    public int getLrn(){
        return borrowersLrn;
    }
    
    public int getBookNumber(){
        return bookNumber;
    }
    
    public String getBookTitle(){
        return bookTitle;
    }
    
    public Date getStartDate(){
        return startDate;
    }
    
    public Date getEndDate(){
        return endDate;
    }
}
