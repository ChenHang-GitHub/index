/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TableDao;
import dao.TableDaoImpl;
import java.util.List;
import pojo.CheckTable;
import pojo.PunchCard;
import pojo.RepairCard;
import pojo.Salary;
import util.DBConnection;

/**
 *
 * @author chenshihang
 */
public class TableServiceImpl  implements  TableService{
    private  TableDao td = null ;
    private  DBConnection dbconn = null;
    
    public  TableServiceImpl () throws  Exception
    {
      this.dbconn = new DBConnection();
      this.td =new  TableDaoImpl(dbconn.getConnection());
    }

    @Override
    public List<PunchCard> showDaService() throws Exception {
         return  td.showDaDao();
    }

    @Override
    public boolean AddDaTableService(PunchCard p) throws Exception {
        return  td.AddDaTableDao(p);
    }

    @Override
    public List<RepairCard> showReService() throws Exception {
        
        return td.showReDao();
    }

    @Override
    public boolean AddReTableService(RepairCard r) throws Exception {
        
        return  td.AddReTableDao(r);
    }

    @Override
    public List<CheckTable> showChAndAddState(int month) throws Exception {
       return  td.showChAndAddState(month);
    }

    @Override
    public List<CheckTable> searchCheakAllService(String parameter, String parameter0, String parameter1) throws Exception {
        
        return td.searchCheckAllDao(parameter,parameter0,parameter1);
    }

    @Override
    public List<Salary> showSalaryService() throws Exception {
        return  td.showSalaryDao();
        
    }

    @Override
    public boolean addSalaryService(Salary s) throws Exception {
        
        return  td.addSalaryDao(s);
    }

    @Override
    public boolean checkEmpByNameAndCodeService(Salary s) throws Exception {
        
        return  td.checkEmpByNameAndCode(s);
    }

    @Override
    public boolean deleteSalaryService(String Sid) throws Exception {
        if(!Sid.equals(""))
        {
            return  td.deleteSalaryDao(Sid);
        }
        return  false;
    }

}
