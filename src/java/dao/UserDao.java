/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import pojo.Department;
import pojo.Employee;
import pojo.Post;
import pojo.User;

/**
 *
 * @author chenshihang
 */
public interface UserDao {

    //用户登入校验
    public User checkUserLoginDao(String uname, String pwd) throws Exception;

    //显示员工功能
    public List<Employee> ShowEmployeeDao() throws Exception;

    //通过员工id显示员工
    public List<Employee> showEmployeeByIdServiceDao(String parameter) throws Exception;

    // 更新员工信息
    public boolean updateInfoDao(Employee emp) throws Exception;

    //添加员工信息
    public boolean addEmpDao(Employee emp) throws Exception;

    //删除员工信息
    public boolean deleteEmpDao(String flag) throws Exception;

    //显示部门信息
    public List<Department> ShowDepartmentDao() throws Exception;

    //查询所有部门职责
    public List<String> getDInChargeDao() throws Exception;

    //更新部门信息
    public boolean updateDepInfoDao(Department d) throws Exception;

    //添加部门信息
    public boolean addDepDao(Department d) throws Exception;

    //删除
    public boolean deleteDepDao(String flag) throws Exception;

    //显示员工
    public List<Employee> ShowEmployeeDao(int pageNum) throws Exception;

    //分页显示员工
    public List<Employee> ShowEmployeeDao(int pageNum, String searchName) throws Exception;

    //批量删除实现
    public void deleteSelected(int EnoFromchk) throws Exception;

    //分页显示部门
    public List<Department> ShowDepartmentDao(int pageNum) throws Exception;

    // 根据Id查询 
    public List<Department> ShowDepartmentDao(String searchName) throws Exception;

    //批量删除
    public void deleteSelectedDep(int DidFromchk) throws Exception;

    //显示岗位
    public List<Post> ShowPostDao() throws Exception;

    //获取postCode
    public List<String> getPostIdFromStDao() throws Exception;

    //获取departName
    public List<String> getDNameFromDeService() throws Exception;

    //获取部门职责
    public String getDeDutyFromDeDao(String parameter) throws Exception;

    //更新岗位信息
    public boolean updatePostInfoDao(Post p) throws Exception;

    //删除岗位信息
    public boolean deletePostDao(String flag) throws Exception;

    //添加岗位信息
    public boolean addPostDao(Post p) throws Exception;

    //搜索岗位信息
    public List<Post> searchPostDao(String temp) throws Exception;
}
