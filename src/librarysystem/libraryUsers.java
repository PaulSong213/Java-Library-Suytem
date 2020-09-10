/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

/**
 *
 * @author great_000
 */
public class libraryUsers {
    
    private int lrn;
    private String studentNumber;
    public libraryUsers(int lrn, String studentNumber){
        this.lrn = lrn;
        this.studentNumber = studentNumber;
    }
    
    public void setStudentNumber(String studentNumber){
        this.studentNumber = studentNumber;
    }
    
    public String getStudentNumber(){
        return studentNumber;
    }
    
    public void setLrn(int lrn){
        this.lrn = lrn;
    }
    
    public int getLrn(){
        return lrn;
    }
}
