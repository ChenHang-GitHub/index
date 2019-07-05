/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import pojo.Cl;
import servlet.Classes;

/**
 *
 * @author chenshihang
 */
public interface ClassService {

    public List<Cl> showClassInfoService() throws  Exception;

    public boolean ClassUpdateService(Cl c) throws  Exception;



    public boolean ClassDelService(int DelById) throws  Exception;

    public List<Cl>  searchClassService(String search) throws  Exception;

    public boolean ClassAddService(Cl c, String TypeFromF) throws  Exception;

  

    
}
