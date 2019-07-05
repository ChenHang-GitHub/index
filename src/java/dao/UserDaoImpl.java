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
import pojo.Department;
import pojo.Employee;
import pojo.Post;
import pojo.User;

/**
 *
 * @author chenshihang
 */
public class UserDaoImpl implements UserDao {

    private Connection conn;
    private PreparedStatement pstmt;

    public UserDaoImpl(Connection connection) {
        this.conn = connection;
    }

    @Override
    public User checkUserLoginDao(String uname, String pwd) throws Exception {
        User user = null;
        String sql = "select *from tLogin where UserName=? and PassWord=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, uname);
        pstmt.setString(2, pwd);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            user = new User();
            user.setId(rs.getInt(1));
            user.setUno(rs.getString(2));
            user.setUserName(rs.getString(3));
            user.setPassWord(rs.getString(4));
        }
        pstmt.close();
        return user;
    }

    @Override
    public List<Employee> ShowEmployeeDao() throws Exception {
        Employee e = null;
        String sql = "select * from Employees";
        List<Employee> el = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            e = new Employee();
            e.setEmp_Id(rs.getInt(1));
            e.setEno(rs.getString(2));
            e.setEname(rs.getString(3));
            e.setEsex(rs.getString(4));
            e.setEage(rs.getInt(5));
            e.setEmin(rs.getString(6));
            e.setECardId(rs.getString(7));
            e.setEpay(rs.getString(8));
            e.setEtel(rs.getString(9));
            e.setEjintel(rs.getString(10));
            e.setPostId(rs.getInt(11));
            e.setDesc(rs.getString(12));
            e.setPost(getPostByid(e.getPostId()));
            //.....
            el.add(e);
        }
        pstmt.close();
        return el;
    }

    @Override
    public List<Post> ShowPostDao() throws Exception {

        Post p = null;
        List<Post> pl = new ArrayList<>();
        String sql = "select * from station";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            p = new Post();
            p.setPostId(rs.getInt(1));
            p.setPostCode(rs.getString(2));
            p.setPostName(rs.getString(3));
            p.setPost_depart(rs.getString(4));
            p.setPost_superior(rs.getString(5));
            p.setPost_cate(rs.getString(6));
            p.setPost_desc(rs.getString(7));
            pl.add(p);
        }
        pstmt.close();
        return pl;
    }

    @Override
    public List<Employee> ShowEmployeeDao(int pageNum) throws Exception {
        Employee e = null;
        int pageStart = 5 * (pageNum - 1); // 分页实现 默认一页显示5个内容   
        System.out.println("dao.UserDaoImpl.ShowEmployeeDao()" + pageStart);
        String sql = "select * from Employees limit ?,5";
        List<Employee> el = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, pageStart);

        ResultSet rs = pstmt.executeQuery();
        System.out.println("Here?");
        while (rs.next()) {
            e = new Employee();
            e.setEmp_Id(rs.getInt(1));
            e.setEno(rs.getString(2));
            e.setEname(rs.getString(3));
            e.setEsex(rs.getString(4));
            e.setEage(rs.getInt(5));
            e.setEmin(rs.getString(6));
            e.setECardId(rs.getString(7));
            e.setEpay(rs.getString(8));
            e.setEtel(rs.getString(9));
            e.setEjintel(rs.getString(10));
            e.setPostId(rs.getInt(11));
            e.setDesc(rs.getString(12));
            e.setPost(getPostByid(e.getPostId()));
            //.....
            el.add(e);
        }
        System.out.println("dao.UserDaoImpl.ShowEmployeeDao()" + el);
        pstmt.close();
        return el;
    }

    @Override
    public List<Department> ShowDepartmentDao(int pageNum) throws Exception {
        Department dep = null;
        int pageStart = 5 * (pageNum - 1); // 分页实现 默认一页显示5个内容 
        System.out.println("dao.UserDaoImpl.ShowDepartmentDao()" + pageStart);
        String sql = "select * from department limit ?,5";
        List<Department> li = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, pageStart);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            dep = new Department();
            dep.setDid(rs.getInt(1));
            dep.setDCode(rs.getString(2));
            dep.setDName(rs.getString(3));
            dep.setDInCharge(rs.getString(4));
            dep.setDeDuty(rs.getString(5));
            dep.setD_superior(rs.getString(6));
            li.add(dep);
        }
        pstmt.close();
        return li;

    }

    @Override
    public List<Department> ShowDepartmentDao() throws Exception {
        Department dep = null;
        String sql = "select * from Department";
        List<Department> li = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            dep = new Department();
            dep.setDid(rs.getInt(1));
            dep.setDCode(rs.getString(2));
            dep.setDName(rs.getString(3));
            dep.setDInCharge(rs.getString(4));
            dep.setDeDuty(rs.getString(5));
            dep.setD_superior(rs.getString(6));
            li.add(dep);
        }
        pstmt.close();
        return li;
    }

    @Override
    public List<Department> ShowDepartmentDao(String searchName) throws Exception {
        Department dep = null;
        String sql = "select * from Department where DCode=? or DName=?";
        List<Department> li = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, searchName);
        pstmt.setString(2, searchName);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            dep = new Department();
            dep.setDid(rs.getInt(1));
            dep.setDCode(rs.getString(2));
            dep.setDName(rs.getString(3));
            dep.setDInCharge(rs.getString(4));
            dep.setDeDuty(rs.getString(5));
            dep.setD_superior(rs.getString(6));
            li.add(dep);
        }
        System.out.println("dao.UserDaoImpl.ShowDepartmentDao()" + li);
        pstmt.close();
        return li;
    }

    @Override
    public List<Employee> ShowEmployeeDao(int pageNum, String searchName) throws Exception {
        Employee e = null;
        int pageStart = 5 * (pageNum - 1);
        System.out.println("dao.UserDaoImpl.ShowEmployeeDao()" + searchName);
        String sql = "select * from Employees where Ename=? limit ?,100";
        List<Employee> el = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, searchName);
        pstmt.setInt(2, pageStart);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            e = new Employee();
            e.setEmp_Id(rs.getInt(1));
            e.setEno(rs.getString(2));
            e.setEname(rs.getString(3));
            e.setEsex(rs.getString(4));
            e.setEage(rs.getInt(5));
            e.setEmin(rs.getString(6));
            e.setECardId(rs.getString(7));
            e.setEpay(rs.getString(8));
            e.setEtel(rs.getString(9));
            e.setEjintel(rs.getString(10));
            e.setPostId(rs.getInt(11));
            e.setDesc(rs.getString(12));
            e.setPost(getPostByid(e.getPostId()));
            //.....
            el.add(e);
        }
        System.out.println("dao.UserDaoImpl.ShowEmployeeDao()" + el);
        pstmt.close();
        return el;

    }

    private String getPostByid(int post) throws Exception {
        String temp = null;
        String sql = "select postName from station where postId = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, post);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            temp = rs.getString(1);
        }
        return temp;
    }

    @Override
    public List<Employee> showEmployeeByIdServiceDao(String parameter) throws Exception {
        Employee e = null;
        String sql = "select * from Employees where Emp_Id=?";
        List<Employee> el = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        System.out.println("parseInt=?" + Integer.parseInt(parameter));
        pstmt.setInt(1, Integer.parseInt(parameter));
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            e = new Employee();
            e.setEmp_Id(rs.getInt(1));
            e.setEno(rs.getString(2));
            e.setEname(rs.getString(3));
            e.setEsex(rs.getString(4));
            e.setEage(rs.getInt(5));
            e.setEmin(rs.getString(6));
            e.setECardId(rs.getString(7));
            e.setEpay(rs.getString(8));
            e.setEtel(rs.getString(9));
            e.setEjintel(rs.getString(10));
            e.setPostId(rs.getInt(11));
            e.setDesc(rs.getString(12));

            e.setPost(getPostByid(e.getPostId()));
            System.out.println("dao.UserDaoImpl.showEmployeeByIdServiceDao()?????" + e.toString());
            el.add(e);
        }
        pstmt.close();
        return el;
    }

    @Override
    public boolean updateInfoDao(Employee emp) {
        try {
            boolean flag = true;
            String sql = "update employees set Eno=?,Ename=?,Eage=?,Emin=?,ECardId=?,Epay=?,Etel=?,Ejintel=?,descri=?,postId=? where Emp_Id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getEno());
            pstmt.setString(2, emp.getEname());
            pstmt.setInt(3, emp.getEage());
            pstmt.setString(4, emp.getEmin());
            pstmt.setString(5, emp.getECardId());
            pstmt.setString(6, emp.getEpay());
            pstmt.setString(7, emp.getEtel());
            pstmt.setString(8, emp.getEjintel());
            pstmt.setString(9, emp.getDesc());
            pstmt.setInt(10, emp.getPostId());
            pstmt.setInt(11, emp.getEmp_Id());
            pstmt.execute();
            pstmt.close();
            return flag;
        } catch (SQLException ex) {
            //更新员工信息异常处理
            return false;
        }
    }

    @Override
    public boolean updateDepInfoDao(Department d) throws Exception {
        boolean flag = true;
        String sql = "update Department set DCode=?,DName=?,DInCharge=?,DeDuty=?,D_Superior=? where DCode=? ";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, d.getDCode());
        pstmt.setString(2, d.getDName());
        pstmt.setString(3, d.getDInCharge());
        pstmt.setString(4, d.getDeDuty());
        pstmt.setString(5, d.getD_superior());
        pstmt.setString(6, d.getDCode());
        pstmt.execute();
        System.out.println("dao.UserDaoImpl.updateDepInfoDao()" + d);

        return flag;
    }

    @Override
    public boolean updatePostInfoDao(Post p) throws Exception {
        boolean flag = true;
        String sql = "update  station set postCode=?,postName=?,post_depart=?,post_superior=?,post_cate=?,post_desc=? where postId=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, p.getPostCode());
        pstmt.setString(2, p.getPostName());
        pstmt.setString(3, p.getPost_depart());
        pstmt.setString(4, p.getPost_superior());
        pstmt.setString(5, p.getPost_cate());
        pstmt.setString(6, p.getPost_desc());
        pstmt.setInt(7, p.getPostId());
        pstmt.execute();
        return flag;
    }

    //将员工信息添加到Tlogin表 默认密码123456
    private void addTotLogin(Employee emp) throws Exception {
        String sql = "insert into tLogin(UserName,PassWord,Uno) values(?,'123456',?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, emp.getEname());
        pstmt.setString(2, emp.getEno());
        pstmt.execute();
    }

    @Override
    public boolean addEmpDao(Employee emp) throws Exception {
        boolean flag = true;
        // 级联增加 
        try {
            String sql = "insert into employees(Eno,Ename,Eage,Emin,ECardId,Epay,Etel,Ejintel,descri,Esex,postId) values(?,?,?,?,?,?,?,?,?,?,?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getEno());
            pstmt.setString(2, emp.getEname());
            pstmt.setInt(3, emp.getEage());
            pstmt.setString(4, emp.getEmin());
            pstmt.setString(5, emp.getECardId());
            pstmt.setString(6, emp.getEpay());
            pstmt.setString(7, emp.getEtel());
            pstmt.setString(8, emp.getEjintel());
            pstmt.setString(9, emp.getDesc());
            pstmt.setString(10, emp.getEsex());
            pstmt.setInt(11, emp.getPostId());
            pstmt.execute();
            addTotLogin(emp);
            System.out.println("dao.UserDaoImpl.updateDepInfoDao()HHHHHHHHHHHH");
        } catch (Exception e) {
            return false;
        } finally {
            pstmt.close();
        }
        return flag;
    }

    @Override
    public boolean addDepDao(Department d) throws Exception {
        boolean flag = true;
        String sql = "insert into department(DCode,DName,DInCharge,DeDuty,D_Superior) values(?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, d.getDCode());
        pstmt.setString(2, d.getDName());
        pstmt.setString(3, d.getDInCharge());
        pstmt.setString(4, d.getDeDuty());
        pstmt.setString(5, d.getD_superior());
        pstmt.execute();
        pstmt.close();
        return flag;
    }

    private boolean deleteDepByEmp(String flag) throws SQLException {
        String sql1 = "select Ename from employees where Emp_Id=?";
        pstmt = conn.prepareStatement(sql1);
        pstmt.setInt(1, Integer.valueOf(flag));
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println("dao.UserDaoImpl.deleteDepByEmp()" + rs.getString("Ename"));
            String sql2 = "delete from department where DInCharge=? ";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, rs.getString("Ename"));
            pstmt.execute();
            System.out.println("dao.UserDaoImpl.deleteDepByEmp() Here+++++++++++++++++++++++++");
        }

        return true;
    }

    @Override
    public boolean deleteEmpDao(String flag) throws Exception {
        boolean f = true;
        //级联删除 
        deleteDepByEmp(flag);
        String sql = "delete from employees where Emp_Id=?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.valueOf(flag));
        pstmt.execute();
        System.out.println("Success Here ================");

        pstmt.close();

        return f;
    }

    @Override
    public void deleteSelected(int EnoFromchk) throws Exception {

        String sql = "delete from employees where Emp_Id=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, EnoFromchk);
        pstmt.execute();
    }

    @Override
    public void deleteSelectedDep(int DidFromchk) throws Exception {
        String sql = "delete from department where Did=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, DidFromchk);
        pstmt.execute();
    }

    @Override
    public boolean deleteDepDao(String flag) throws Exception {
        String sql = "delete from department where Did=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.valueOf(flag));

        pstmt.execute();
        pstmt.close();
        return true;
    }

    @Override
    public List<String> getDInChargeDao() throws Exception {
        List<String> list = null;
        String sql = "select Ename from employees";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        list = new ArrayList<String>();
        while (rs.next()) {

            list.add(rs.getString("Ename"));

        }
        System.out.println("dao.UserDaoImpl.getDInChargeServiceDao()" + list.toString());

        return list;
    }

    @Override
    public List<String> getPostIdFromStDao() throws Exception {
        List<String> li = new ArrayList<>();
        String sql = "select postCode from station";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            li.add(rs.getString("postCode"));
        }
        System.out.println("dao.UserDaoImpl.getPostIdFromStDao()" + li);
        return li;
    }

    @Override
    public List<String> getDNameFromDeService() throws Exception {
        List<String> li = new ArrayList<>();
        String sql = "select DName from department";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            li.add(rs.getString("DName"));
        }
        System.out.println("dao.UserDaoImpl.DName()" + li);
        return li;
    }

    @Override
    public String getDeDutyFromDeDao(String parameter) throws Exception {
        String li = new String();
        String sql = "select DeDuty from department where DName=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, parameter);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            li = rs.getString("DeDuty");
        }
        System.out.println("dao.UserDaoImpl.Deduty()" + li);
        return li;

    }

    @Override
    public boolean deletePostDao(String flag) throws Exception {
        boolean f = true;
        //级联删除 
        String sql = "delete from  station where postId=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.valueOf(flag));
        pstmt.execute();
        pstmt.close();
        return f;
    }

    @Override
    public boolean addPostDao(Post p) throws Exception {
        boolean flag = true;
        String sql = "insert into station(postCode,postName,post_depart,post_superior,post_cate,post_desc) values(?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, p.getPostCode());
        pstmt.setString(2, p.getPostName());
        pstmt.setString(3, p.getPost_depart());
        pstmt.setString(4, p.getPost_superior());
        pstmt.setString(5, p.getPost_cate());
        pstmt.setString(6, p.getPost_desc());
        pstmt.execute();
        pstmt.close();
        return flag;
    }

    @Override
    public List<Post> searchPostDao(String temp) throws Exception {
        List<Post> pl = new ArrayList<>();
        Post p = null;
        String sql = "select * from station where postId=? or postCode=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, temp);
        pstmt.setString(2, temp);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            p = new Post();
            p.setPostId(rs.getInt(1));
            p.setPostCode(rs.getString(2));
            p.setPostName(rs.getString(3));
            p.setPost_depart(rs.getString(4));
            p.setPost_superior(rs.getString(5));
            p.setPost_cate(rs.getString(6));
            p.setPost_desc(rs.getString(7));
            pl.add(p);
        }
        return pl;
    }

}
