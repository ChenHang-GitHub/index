/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author chenshihang
 */
public class PunchCard {

    public int getPid() {
        return Pid;
    }

    public void setPid(int Pid) {
        this.Pid = Pid;
    }

    public String getPno() {
        return Pno;
    }

    public void setPno(String Pno) {
        this.Pno = Pno;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }



    public String getPdesc() {
        return Pdesc;
    }

    public void setPdesc(String Pdesc) {
        this.Pdesc = Pdesc;
    }

    @Override
    public String toString() {
        return "PunchCard{" + "Pid=" + Pid + ", Pno=" + Pno + ", PName=" + PName + ", PDate=" + PDate + ", Pdesc=" + Pdesc + '}';
    }

    private int Pid;
    private String Pno;
    private String PName;
    private Timestamp PDate;

    public Timestamp getPDate() {
        return PDate;
    }

    public void setPDate(Timestamp PDate) {
        this.PDate = PDate;
    }
    private String Pdesc;
}
