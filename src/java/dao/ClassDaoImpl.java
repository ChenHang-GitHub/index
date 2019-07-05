/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Cl;
import servlet.Classes;

/**
 *
 * @author chenshihang
 */
public class ClassDaoImpl implements ClassDao {

    private Connection conn;
    private PreparedStatement pstmt;

    public ClassDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cl> showClassInfoDao() throws Exception {
        String sql = "select * from Classes";
        pstmt = conn.prepareStatement(sql);
        List<Cl> cl = new ArrayList<>();
        Cl c = null;
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            c = new Cl();
            c.setCid(rs.getInt(1));
            c.setClassCode(rs.getString(2));
            c.setClassName(rs.getString(3));
            c.setMTime(rs.getString(4));
            c.setATime(rs.getString(5));
            c.setCdesc(rs.getString(6));
            cl.add(c);
        }
        System.out.println("dao.ClassDaoImpl.showClassInfoDao()" + cl);
        pstmt.close();
        return cl;
    }

    // 捕获更新异常处理，确保班次编码不重复 返回servlet 进行处理
    @Override
    public boolean ClassUpdateDao(Cl c) {
        try {
            String sql = "update Classes set Cid=?,ClassCode=?,ClassName=?,MTime=?,ATime=?,Cdesc=? where Cid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, c.getCid());
            pstmt.setString(2, c.getClassCode());
            pstmt.setString(3, c.getClassName());
            pstmt.setString(4, c.getMTime());
            pstmt.setString(5, c.getATime());
            pstmt.setString(6, c.getCdesc());
            pstmt.setInt(7, c.getCid());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean ClassAddDao(Cl c, String TypeFromF) throws Exception {

        String sql = "insert into Classes(ClassCode,ClassName,MTime,ATime,Cdesc) values(?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, c.getClassCode());
        pstmt.setString(2, c.getClassName());
        pstmt.setString(3, c.getMTime());
        pstmt.setString(4, c.getATime());
        pstmt.setString(5, c.getCdesc());
        pstmt.execute();
        AddClassToEmpTable(TypeFromF, c.getClassCode());
        return true;

    }

    // 对添加班次进行校验搜索已有员工
    @Override
    public boolean checkEmp(String TypeFromF) throws Exception {
        boolean flag = false;
        String sql = "select Emp_Id from employees where  Eno = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, TypeFromF);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            flag = true;
            System.out.println("有该员工");
        }
        return flag;
    }
    
    private void AddClassToEmpTable(String TypeFromF, String classCode) throws Exception {

        String sql = "update employees set EClassCode =? where Eno=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, classCode);
        pstmt.setString(2, TypeFromF);
        pstmt.execute();
        System.out.println("dao.ClassDaoImpl.AddClassToEmpTable()成功更新员工ClasCOde");
    }

    @Override
    public boolean ClassDelDao(int DelById) throws Exception {
        String sql = "delete  from Classes where Cid= ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, DelById);
        pstmt.execute();
        return true;
    }

    @Override
    public List<Cl> searchClassDao(String search) throws Exception {
        List<Cl> cl = new ArrayList<Cl>();
        Cl c = null;
        String sql = "select * from Classes where ClassCode=? or ClassName=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, search);
        pstmt.setString(2, search);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            c = new Cl();
            c.setCid(rs.getInt(1));
            c.setClassCode(rs.getString(2));
            c.setClassName(rs.getString(3));
            c.setMTime(rs.getString(4));
            c.setATime(rs.getString(5));
            c.setCdesc(rs.getString(6));
            cl.add(c);
        }
        System.out.println("dao.ClassDaoImpl.searchClassDao()Cllllllllllll" + cl);
        return cl;
    }

}
