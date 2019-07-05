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
public class CheckTable {

    public int getKid() {
        return Kid;
    }

    public void setKid(int Kid) {
        this.Kid = Kid;
    }

    public String getKCode() {
        return KCode;
    }

    public void setKCode(String KCode) {
        this.KCode = KCode;
    }

    public String getKName() {
        return KName;
    }

    public void setKName(String KName) {
        this.KName = KName;
    }

    public Timestamp getKMTime() {
        return KMTime;
    }

    public void setKMTime(Timestamp KMTime) {
        this.KMTime = KMTime;
    }

    public Timestamp getKATime() {
        return KATime;
    }

    public void setKATime(Timestamp KATime) {
        this.KATime = KATime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CheckTable{" + "Kid=" + Kid + ", KCode=" + KCode + ", KName=" + KName + ", KMTime=" + KMTime + ", KATime=" + KATime + ", state=" + state + '}';
    }
    
    private  int Kid;
    private String KCode;
    private String KName;
    private Timestamp  KMTime;
    private Timestamp KATime;
    private String state ;
    
    
}
