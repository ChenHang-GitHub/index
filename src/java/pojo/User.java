/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;



/**
 *
 * @author chenshihang
 */
public class User {

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

  
//
//    public User(int Id, String UserName, String PassWord) {
//        this.Id = Id;
//        this.UserName = UserName;
//        this.PassWord = PassWord;
//    }


    private  int  Id;

    @Override
    public String toString() {
        return "User{" + "Id=" + Id + ", Uno=" + Uno + ", UserName=" + UserName + ", PassWord=" + PassWord + '}';
    }

    public String getUno() {
        return Uno;
    }

    public void setUno(String Uno) {
        this.Uno = Uno;
    }
    private  String Uno;
    private  String UserName;
    private String PassWord;
}
