/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import dao.UserDaoImpl;
import java.util.List;
import pojo.Department;
import pojo.Employee;
import pojo.Post;
import pojo.User;
import util.DBConnection;

/**
 *
 * @author chenshihang
 */
public class UserServiceImpl implements UserService {

    private UserDao ud = null;
    private DBConnection dbconn = null;

    public UserServiceImpl() throws Exception {
        this.dbconn = new DBConnection();
        this.ud = new UserDaoImpl(dbconn.getConnection());
    }

    @Override
    public User checkUserLoginService(String uname, String pwd) throws Exception {
        return ud.checkUserLoginDao(uname, pwd);
    }

    @Override
    public List<Employee> ShowEmployeeService() throws Exception {
        return ud.ShowEmployeeDao();
    }

    @Override
    public List<Employee> showEmployeeByIdService(String parameter) throws Exception {

        return ud.showEmployeeByIdServiceDao(parameter);
    }

    @Override
    public List<Employee> ShowEmployeeService(int pageNum, String searchName) throws Exception {
        return ud.ShowEmployeeDao(pageNum, searchName);
    }

    @Override
    public List<Employee> ShowEmployeeService(int pageNum) throws Exception {
        return ud.ShowEmployeeDao(pageNum);

    }

    @Override
    public boolean updateInfoService(Employee emp) throws Exception {

//        System.out.println("service.UserServiceImpl.updateInfoService()"+ "Work!!!!!!!!!!!!!!!!");
        return ud.updateInfoDao(emp);

    }

    @Override
    public boolean addEmpService(Employee emp) throws Exception {
        return ud.addEmpDao(emp);
    }

    @Override
    public boolean deleteEmpService(String flag) throws Exception {

        return ud.deleteEmpDao(flag);

    }

    @Override
    public List<Department> ShowDepartmentService() throws Exception {

        return ud.ShowDepartmentDao();
    }

    @Override
    public List<Department> ShowDepartmentService(int pageNum) throws Exception {
        return ud.ShowDepartmentDao(pageNum);
    }

    @Override
    public List<String> getDInChargeService() throws Exception {

        return ud.getDInChargeDao();
    }

    @Override
    public boolean updateDepInfoService(Department d) throws Exception {
        System.out.println("service.UserServiceImpl.updateDepInfoService()HHHHHHHHHHHHHh");
        return ud.updateDepInfoDao(d);
    }

    @Override
    public boolean addDepService(Department d) throws Exception {
        return ud.addDepDao(d);
    }

    @Override
    public boolean deleteDepService(String flag) throws Exception {
        return ud.deleteDepDao(flag);
    }

    @Override
    public void deleteSelected(int EnoFromchk) throws Exception {
        ud.deleteSelected(EnoFromchk);
    }

    @Override
    public List<Department> ShowDepartmentService(String searchName) throws Exception {

        return ud.ShowDepartmentDao(searchName);
    }

    @Override
    public void deleteSelectedDep(int DidFromchk) throws Exception {
        ud.deleteSelectedDep(DidFromchk);
    }

    @Override
    public List<Post> ShowPostService() throws Exception {

        return ud.ShowPostDao();
    }

    @Override
    public List<String> getPostIdFromStService() throws Exception {
        return ud.getPostIdFromStDao();
    }

    @Override
    public List<String> getDNameFromDeService() throws Exception {
        return ud.getDNameFromDeService();

    }

    @Override
    public String getDeDutyFromDeService(String parameter) throws Exception {
        return ud.getDeDutyFromDeDao(parameter);
    }

    @Override
    public boolean updatePostInfo(Post p) throws Exception {
        return ud.updatePostInfoDao(p);
    }

    @Override
    public boolean deletePostService(String flag) throws Exception {

        return ud.deletePostDao(flag);
    }

    @Override
    public boolean addPostService(Post p) throws Exception {
        return ud.addPostDao(p);
    }

    @Override
    public List<Post> searchPostService(String temp) throws Exception {

        return ud.searchPostDao(temp);
    }

}
