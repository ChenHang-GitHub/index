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
public class Employee {

    public int getEmp_Id() {
        return Emp_Id;
    }

    public void setEmp_Id(int Emp_Id) {
        this.Emp_Id = Emp_Id;
    }


    public String getEname() {
        return Ename;
    }

    public void setEname(String Ename) {
        this.Ename = Ename;
    }

    public String getEsex() {
        return Esex;
    }

    public void setEsex(String Esex) {
        this.Esex = Esex;
    }

    public int getEage() {
        return Eage;
    }

    public void setEage(int Eage) {
        this.Eage = Eage;
    }

    public String getEmin() {
        return Emin;
    }

    public void setEmin(String Emin) {
        this.Emin = Emin;
    }

    public String getECardId() {
        return ECardId;
    }

    public void setECardId(String ECardId) {
        this.ECardId = ECardId;
    }

    public String getEpay() {
        return Epay;
    }

    public void setEpay(String Epay) {
        this.Epay = Epay;
    }

    public String getEtel() {
        return Etel;
    }

    public void setEtel(String Etel) {
        this.Etel = Etel;
    }

    public String getEjintel() {
        return Ejintel;
    }

    public void setEjintel(String Ejintel) {
        this.Ejintel = Ejintel;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    private int Emp_Id;
    private String Eno ;
    private String Ename;
    private String Esex;
    private int Eage;
    private String Emin;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    private String ECardId ;

    public String getEno() {
        return Eno;
    }

    public void setEno(String Eno) {
        this.Eno = Eno;
    }
    private String Epay;
    private String Etel;

    @Override
    public String toString() {
        return "Employee{" + "Emp_Id=" + Emp_Id + ", Eno=" + Eno + ", Ename=" + Ename + ", Esex=" + Esex + ", Eage=" + Eage + ", Emin=" + Emin + ", ECardId=" + ECardId + ", Epay=" + Epay + ", Etel=" + Etel + ", Ejintel=" + Ejintel + ", postId=" + postId + ", desc=" + desc + ", post=" + post + '}';
    }

 
    private String Ejintel;
    private int  postId;
    private String desc;
    private String  post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
         
    
    
}
