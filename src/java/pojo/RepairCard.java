/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Timestamp;

/**
 *
 * @author chenshihang
 */
public class RepairCard {

    public int getRId() {
        return RId;
    }

    public void setRId(int RId) {
        this.RId = RId;
    }

    public String getRno() {
        return Rno;
    }

    public void setRno(String Rno) {
        this.Rno = Rno;
    }

    public String getRName() {
        return RName;
    }

    public void setRName(String RName) {
        this.RName = RName;
    }


    public String getRdesc() {
        return Rdesc;
    }

    public void setRdesc(String Rdesc) {
        this.Rdesc = Rdesc;
    }

    @Override
    public String toString() {
        return "RepairCard{" + "RId=" + RId + ", Rno=" + Rno + ", RName=" + RName + ", RDate=" + RDate + ", Rdesc=" + Rdesc + '}';
    }
    
    private  int RId;
    private  String Rno;
    private  String RName;
    private  Timestamp RDate;

    public Timestamp getRDate() {
        return RDate;
    }

    public void setRDate(Timestamp RDate) {
        this.RDate = RDate;
    }
    private  String Rdesc;
    
    
}
