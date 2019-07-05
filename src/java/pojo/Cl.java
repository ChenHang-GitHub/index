/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Time;

/**
 *
 * @author chenshihang
 */
public class Cl {

    public int getCid() {
        return Cid;
    }

    public void setCid(int Cid) {
        this.Cid = Cid;
    }

    public String getClassCode() {
        return ClassCode;
    }

    public void setClassCode(String ClassCode) {
        this.ClassCode = ClassCode;
    }

    public String getMTime() {
        return MTime;
    }

    public void setMTime(String MTime) {
        this.MTime = MTime;
    }

    public String getATime() {
        return ATime;
    }

    public void setATime(String ATime) {
        this.ATime = ATime;
    }

    public String getCdesc() {
        return Cdesc;
    }

    public void setCdesc(String Cdesc) {
        this.Cdesc = Cdesc;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    @Override
    public String toString() {
        return "Classes{" + "ClassName=" + ClassName + ", Cid=" + Cid + ", ClassCode=" + ClassCode + ", MTime=" + MTime + ", ATime=" + ATime + ", Cdesc=" + Cdesc + '}';
    }
     private  String  ClassName;
     private  int Cid ; 
     private  String    ClassCode;
     private  String MTime;
     private  String ATime;
     private  String Cdesc;
    


}
