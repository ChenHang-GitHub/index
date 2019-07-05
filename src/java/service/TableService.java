/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import pojo.CheckTable;
import pojo.PunchCard;
import pojo.RepairCard;
import pojo.Salary;

/**
 *
 * @author chenshihang
 */
public interface TableService {

    public List<PunchCard> showDaService() throws  Exception;

    public boolean AddDaTableService(PunchCard p) throws  Exception;

    public List<RepairCard> showReService() throws  Exception;

    public boolean AddReTableService(RepairCard r) throws  Exception;

    public List<CheckTable> showChAndAddState(int month) throws  Exception;

    public List<CheckTable> searchCheakAllService(String parameter, String parameter0, String parameter1) throws  Exception;

    public List<Salary> showSalaryService() throws  Exception;

    public boolean addSalaryService(Salary s) throws  Exception;

    public boolean checkEmpByNameAndCodeService(Salary s) throws  Exception;

    public boolean deleteSalaryService(String Sid) throws  Exception;

    
}
