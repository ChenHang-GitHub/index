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
public class Department {
    
     private  int  Did ;
     private  String DCode;
     private  String DName;
     private  String DInCharge;
     private  String DeDuty;
     private  String D_superior;

    @Override
    public String toString() {
        return "Department{" + "Did=" + Did + ", DCode=" + DCode + ", DName=" + DName + ", DInCharge=" + DInCharge + ", DeDuty=" + DeDuty + ", D_superior=" + D_superior + '}';
    }

    public int getDid() {
        return Did;
    }

    public void setDid(int Did) {
        this.Did = Did;
    }

    public String getDCode() {
        return DCode;
    }

    public void setDCode(String DCode) {
        this.DCode = DCode;
    }

    public String getDName() {
        return DName;
    }

    public void setDName(String DName) {
        this.DName = DName;
    }

    public String getDInCharge() {
        return DInCharge;
    }

    public void setDInCharge(String DInCharge) {
        this.DInCharge = DInCharge;
    }

    public String getDeDuty() {
        return DeDuty;
    }

    public void setDeDuty(String DeDuty) {
        this.DeDuty = DeDuty;
    }

    public String getD_superior() {
        return D_superior;
    }

    public void setD_superior(String D_superior) {
        this.D_superior = D_superior;
    }
    
}
