/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import pojo.Cl;
import servlet.Classes;

/**
 *
 * @author chenshihang
 */
public interface ClassDao  {
    //显示班次功能
    public List<Cl> showClassInfoDao() throws  Exception;
    //更新已有班次功能
    public boolean ClassUpdateDao(Cl c) throws  Exception ;
    //班次删除功能
    public boolean ClassDelDao(int DelById) throws  Exception;
    //搜索班次功能
    public List<Cl>  searchClassDao(String search) throws  Exception;
    //添加班次功能
    public boolean ClassAddDao(Cl c, String TypeFromF) throws  Exception;
    //添加时对员工表进行搜索校验员工
    public boolean checkEmp(String TypeFromF) throws  Exception;
    
}
