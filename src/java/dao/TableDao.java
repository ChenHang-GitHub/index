/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import pojo.CheckTable;
import pojo.PunchCard;
import pojo.RepairCard;
import pojo.Salary;

/**
 *
 * @author chenshihang
 */
public interface TableDao {

    // 显示打卡单
    public List<PunchCard> showDaDao() throws Exception;
    //添加打卡单信息
    public boolean AddDaTableDao(PunchCard p) throws  Exception;
    // 显示补卡单
    public List<RepairCard> showReDao() throws  Exception;
    //添加补卡单信息
    public boolean AddReTableDao(RepairCard r) throws  Exception;
    //显示考勤表和根据上午下午的考勤信息添加出勤状况            
    public List<CheckTable> showChAndAddState(int month) throws  Exception;
    //考勤表的搜索功能实现
    public List<CheckTable> searchCheckAllDao(String parameter, String parameter0, String parameter1) throws  Exception;
    //显示派薪单
    public List<Salary> showSalaryDao() throws  Exception;
    //添加派薪信息
    public boolean addSalaryDao(Salary s) throws  Exception;
    //添加派薪信息时需要验证员工的信息即是否有该员工
    public boolean checkEmpByNameAndCode(Salary s) throws  Exception;
    //删除派薪信息方法
    public boolean deleteSalaryDao(String Sid) throws  Exception;

    
}
