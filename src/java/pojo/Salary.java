/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author chenshihang
 */
public class Salary {

    public int getSId() {
        return SId;
    }

    public void setSId(int SId) {
        this.SId = SId;
    }

    public String getSCode() {
        return SCode;
    }

    public void setSCode(String SCode) {
        this.SCode = SCode;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public BigDecimal getSalary() {
        return Salary;
    }

    public void setSalary(BigDecimal Salary) {
        this.Salary = Salary;
    }

    public Date getSDate() {
        return SDate;
    }

    public void setSDate(Date SDate) {
        this.SDate = SDate;
    }

    public Date getEDate() {
        return EDate;
    }

    public void setEDate(Date EDate) {
        this.EDate = EDate;
    }

    @Override
    public String toString() {
        return "Salary{" + "SId=" + SId + ", SCode=" + SCode + ", SName=" + SName + ", Salary=" + Salary + ", SDate=" + SDate + ", EDate=" + EDate + '}';
    }
    private  int  SId;
    private String SCode;
    private String SName;
    private BigDecimal Salary;
    private Date SDate;
    private Date EDate;
    
}
