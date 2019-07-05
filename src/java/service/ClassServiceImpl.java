/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ClassDao;
import dao.ClassDaoImpl;
import java.util.List;
import pojo.Cl;
import servlet.Classes;
import util.DBConnection;

/**
 *
 * @author chenshihang
 */
public class ClassServiceImpl  implements  ClassService{
        
    private  ClassDao cd = null ;
    private  DBConnection dbconn = null;
    
    public  ClassServiceImpl () throws  Exception
    {
      this.dbconn = new DBConnection();
      this.cd =new  ClassDaoImpl(dbconn.getConnection());
    }
    

    @Override
    public List<Cl> showClassInfoService() throws Exception {
        
         return cd.showClassInfoDao();
        
    }

    @Override
    public boolean ClassUpdateService(Cl c) throws Exception {
        
        return cd.ClassUpdateDao(c);
    }

    @Override
    public boolean ClassAddService(Cl c,String TypeFromF) throws Exception {
           boolean flag = cd.checkEmp(TypeFromF); 
           if(flag==true)
           {
                       return  cd.ClassAddDao(c,TypeFromF);
           }
           else 
           {
                return false;
           }
        
    }

    @Override
    public boolean ClassDelService(int DelById) throws Exception {
         return cd.ClassDelDao(DelById);
    }

    @Override
    public List<Cl>  searchClassService(String search) throws Exception {
        
         return cd.searchClassDao(search);
    }
    
}
