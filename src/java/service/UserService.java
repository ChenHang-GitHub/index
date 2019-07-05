/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import pojo.Department;
import pojo.Employee;
import pojo.Post;
import pojo.User;

/**
 *
 * @author chenshihang
 */
public interface UserService {
    User checkUserLoginService(String uname,String pwd) throws  Exception;

    public List<Employee> ShowEmployeeService()  throws  Exception ;

    public List<Employee> showEmployeeByIdService(String parameter) throws  Exception;


    public boolean updateInfoService(Employee emp) throws Exception;

    public boolean addEmpService(Employee emp) throws  Exception;

    public boolean deleteEmpService(String flag) throws  Exception;

    public List<Department> ShowDepartmentService() throws  Exception;

    public List<String> getDInChargeService() throws  Exception;

    public boolean updateDepInfoService(Department d) throws  Exception;

    public boolean addDepService(Department d)throws Exception;

    public boolean deleteDepService(String flag)throws Exception;

    public List<Employee> ShowEmployeeService(int pageNum) throws  Exception;

    public List<Employee> ShowEmployeeService(int pageNum, String searchName) throws  Exception
            ;

    public void deleteSelected(int EnoFromchk) throws  Exception;

    public List<Department> ShowDepartmentService(int pageNum) throws  Exception ;

    public List<Department> ShowDepartmentService(String searchName) throws  Exception;

    public void deleteSelectedDep(int DidFromchk) throws  Exception;

    public List<Post> ShowPostService() throws  Exception;

    public List<String> getPostIdFromStService() throws  Exception;

    public List<String> getDNameFromDeService() throws  Exception;


    public String getDeDutyFromDeService(String parameter) throws  Exception;

    public boolean updatePostInfo(Post p) throws  Exception;

    public boolean deletePostService(String flag) throws  Exception; 

    public boolean addPostService(Post p) throws  Exception;

    public List<Post> searchPostService(String temp) throws  Exception;

    
}
