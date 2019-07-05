/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import pojo.User;
import service.UserService;
import service.UserServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.apache.coyote.http11.Constants.a;
import pojo.Department;
import pojo.Employee;
import pojo.Post;

/**
 *员工 岗位 部门的servlet
 * @author chenshihang
 */
public class Login extends HttpServlet {

    UserService us = null;

    protected void Service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, Exception {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //  根据oper参数判断调用的方法
        String oper = req.getParameter("oper");
        if ("login".equals(oper)) {
            checkUserLogin(req, resp);
        } else if ("out".equals(oper)) {
            Userout(req, resp);
        } else if ("showy".equals(oper)) {
            ShowEmployee(req, resp);
        } else if ("tranTo".equals(oper)) {
            tranToUpdateView(req, resp);
        } else if ("updateEmpInfo".equals(oper)) {
            updateInfo(req, resp);
        } else if ("add".equals(oper)) {
            addEmp(req, resp);
        } else if ("delete".equals(oper)) {
            deleteEmp(req, resp);
        } else if ("showb".equals(oper)) {
            ShowDepartment(req, resp);
        } else if ("showp".equals(oper)) {
            ShowPost(req, resp);
        } else if ("tranToDepAdd".equals(oper)) {
            tranToDepView(req, resp);
        } else if ("updateDepInfo".equals(oper)) {
            updateDepInfo(req, resp);
        } else if ("addDep".equals(oper)) {
            addDep(req, resp);
        } else if ("tranToD".equals(oper)) {
            tranToD(req, resp);
        } else if ("deleteDep".equals(oper)) {
            deleteDep(req, resp);
        } else if ("searchEmp".equals(oper)) {
            ShowEmployee(req, resp);

        } else if ("deleteSeleted".equals(oper)) {
            deleteSeleted(req, resp);
        } else if ("searchDep".equals(oper)) {
            ShowDepartment(req, resp);
        } else if ("deleteSeletedDep".equals(oper)) {
            deleteSeletedDep(req, resp);
        } else if ("tranToPostAdd".equals(oper)) {
            tranToPostAdd(req, resp);
        } else if ("updatePostInfo".equals(oper)) {
            updatePostInfo(req, resp);
        } else if ("Post_Add".equals(oper)) {
            Post_Add(req, resp);
        } else if ("toPost_addView".equals(oper)) {
            toPost_addView(req, resp);
        } else if ("deletePost".equals(oper)) {
            deletePost(req, resp);
        } else if ("searchPost".equals(oper)) {
            searchPost(req, resp);
        } else {
            System.out.println("error");
        }
    }
    //用户登入功能实现
    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, Exception {

        us = new UserServiceImpl();
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        System.out.println(uname + " " + pwd);
        User u = us.checkUserLoginService(uname, pwd);
        System.out.println("servlet.login.checkUserLogin()" + u);
        if (u != null) {
            System.out.println("servlet.login.Service()" + "loginSuccess");
            //创建session
            HttpSession hs = req.getSession();
            hs.setAttribute("user", u);
            req.getRequestDispatcher("main/main.jsp").forward(req, resp);
        } else {
           resp.sendRedirect("index.jsp");
        }
    }
    //主页退出功能实现
    private void Userout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession hs = req.getSession();
        hs.invalidate();
        //request.getSession(true)：若存在会话则返回该会话，否则新建一个会话。
        //request.getSession(false)：若存在会话则返回该会话，否则返回NULL
        if (req.getSession(false) == null) {
            System.out.println("Session has been invalidated!");
        } else {
            System.out.println("Session is active!");
        }
        resp.sendRedirect("index.jsp");
        System.out.println("OUT Success------------------------------");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Service(req, resp); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Service(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //显示员工表信息
    private void ShowEmployee(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        // 分页功能实现
        int pageNum = 0;
        if (req.getParameter("pageNum") == null) {
            pageNum = 1;
        } else {
            pageNum = Integer.parseInt(req.getParameter("pageNum"));
        }
        // 通过oper 判断 搜索显示还是全部显示  进行分页处理
        if (req.getParameter("oper").equals("searchEmp")) {

            String searchName = req.getParameter("searchEmpName");
            System.out.println("U searchName:" + searchName + " pageNum" + pageNum);

            List<Employee> emp = us.ShowEmployeeService(pageNum, searchName);
            int size = 0;
            System.out.println("SHow  Employee By SearchName ????????" + emp.toString() + emp + "Size+?" + size);
            req.setAttribute("size", size);
            req.setAttribute("emp", emp);

            req.getRequestDispatcher("user/userInfo.jsp").forward(req, resp);
        } else {
            List<Employee> empSize = us.ShowEmployeeService();
            int size = empSize.size();
            List<Employee> emp = us.ShowEmployeeService(pageNum);
            System.out.println("SHow  Employee" + emp.toString() + emp);
            req.setAttribute("size", size);
            req.setAttribute("emp", emp);
            req.getRequestDispatcher("user/userInfo.jsp").forward(req, resp);
        }

    }
    //显示部门信息
    private void ShowDepartment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        // Dep 的size 
        int pageNum = 0;
        if (req.getParameter("pageNum") == null) {
            pageNum = 1;
            System.out.println("servlet.Login.ShowEmployee().PageNum=?" + req.getParameter("pageNum"));
        } else {
            pageNum = Integer.parseInt(req.getParameter("pageNum"));
        }
        System.out.println("PageNum=?" + pageNum);

        if (req.getParameter("oper").equals("searchDep")) {
            int size = 0;
            String searchName = req.getParameter("searchDepNameOrCode");
            List<Department> dep = us.ShowDepartmentService(searchName);
            System.out.println("servlet.Login.ShowDepartment().searchDepNameOrCode" + searchName);
            req.setAttribute("size", size);
            req.setAttribute("dep", dep);
            req.getRequestDispatcher("user/DepartInfo.jsp").forward(req, resp);
        } else {
            List<Department> depSize = us.ShowDepartmentService();
            List<Department> dep = us.ShowDepartmentService(pageNum);
            System.out.println("servlet.Login.ShowDepartment() Department Size+" + dep.size() + dep.toString());
            int size = depSize.size();
            req.setAttribute("dep", dep);
            req.setAttribute("size", size);
            req.getRequestDispatcher("user/DepartInfo.jsp").forward(req, resp);
        }
    }
    // 员工修改页面的显示
    private void tranToUpdateView(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        List<Employee> emp = us.showEmployeeByIdService(req.getParameter("flag"));
        req.setAttribute("update", emp);
        req.getRequestDispatcher("user/EmpAdd_del.jsp").forward(req, resp);
    }
    //更新员工信息功能
    private void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        Employee emp = new Employee();
        String TypeFromA = req.getParameter("TypeA");
        String TypeFromB = req.getParameter("TypeB");
        String TypeFromC = req.getParameter("TypeC");
        String TypeFromD = req.getParameter("TypeD");
        String TypeFromE = req.getParameter("TypeE");
        String TypeFromF = req.getParameter("TypeF");
        String TypeFromG = req.getParameter("TypeG");
        String TypeFromH = req.getParameter("TypeH");
        String TypeFromI = req.getParameter("TypeI");
        String TypeFromJ = req.getParameter("TypeJ");

        emp.setDesc(TypeFromJ);
        emp.setECardId(TypeFromE);
        emp.setEage(Integer.parseInt(TypeFromC));
        emp.setEjintel(TypeFromH);
        emp.setEmin(TypeFromD);
        emp.setEname(TypeFromB);
        emp.setEno(TypeFromA);
        emp.setEpay(TypeFromF);
        emp.setEtel(TypeFromG);
        emp.setPostId(Integer.parseInt(TypeFromI));

        int Eid = Integer.parseInt(req.getParameter("Eid"));
        emp.setEmp_Id(Eid);

        boolean flag;
        flag = us.updateInfoService(emp);
        // 对员工编码重复做出处理
        if (flag == false) {
            String error = "不同员工员工编码不能相同";
            req.setAttribute("error", error);
            req.getRequestDispatcher("ERROR.jsp").forward(req, resp);
        } else {
            ShowEmployee(req, resp);
        }
//         req.getRequestDispatcher("user/userInfo.jsp").forward(req, resp);
    }
    //更新部门信息
    private void updateDepInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        Department d = new Department();
        String TypeFromA = req.getParameter("TypeA");
        String TypeFromB = req.getParameter("TypeB");
        String TypeFromC = req.getParameter("TypeC");
        String TypeFromD = req.getParameter("TypeD");
        String TypeFromE = req.getParameter("TypeE");
        String TypeFromF = req.getParameter("TypeF");
        d.setDCode(TypeFromA);
        d.setDName(TypeFromB);
        d.setDInCharge(TypeFromC);
        d.setDeDuty(TypeFromD);
        d.setD_superior(TypeFromE);
        System.out.println("Here");
        boolean flag = us.updateDepInfoService(d);
    }
    //更新岗位信息
    private void updatePostInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        Post p = new Post();
        String TypeFromA = req.getParameter("TypeA");
        String TypeFromB = req.getParameter("TypeB");
        String TypeFromC = req.getParameter("TypeC");
        String TypeFromD = req.getParameter("TypeD");
        String TypeFromE = req.getParameter("TypeE");
        String TypeFromF = req.getParameter("TypeF");
        p.setPostCode(TypeFromA);
        p.setPostName(TypeFromB);
        p.setPost_depart(TypeFromC);
        p.setPost_superior(TypeFromD);
        p.setPost_cate(TypeFromE);
        p.setPostId(Integer.parseInt(req.getParameter("TypeG")));
        // 设置职责
        p.setPost_desc(TypeFromF);
        System.out.println("updatePostInfo()" + p);
        boolean flag = us.updatePostInfo(p);
        ShowPost(req, resp);

    }
    //添加员工
    private void addEmp(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        Employee emp = new Employee();
        String TypeFromA = req.getParameter("TypeA");
        String TypeFromB = req.getParameter("TypeB");
        String TypeFromC = req.getParameter("TypeC");
        String TypeFromD = req.getParameter("TypeD");
        String TypeFromE = req.getParameter("TypeE");
        String TypeFromF = req.getParameter("TypeF");
        String TypeFromG = req.getParameter("TypeG");
        String TypeFromH = req.getParameter("TypeH");
        String TypeFromI = req.getParameter("TypeI");
        String TypeFromJ = req.getParameter("TypeJ");

        emp.setDesc(TypeFromJ);
        emp.setECardId(TypeFromE);
        emp.setEage(Integer.parseInt(TypeFromC));
        emp.setEjintel(TypeFromH);
        emp.setEmin(TypeFromD);
        emp.setEname(TypeFromB);
        emp.setEno(TypeFromA);
        emp.setEpay(TypeFromF);
        emp.setEtel(TypeFromG);
        emp.setPostId(Integer.parseInt(TypeFromI));
        //添加界面无显示 设置默认为男性
        emp.setEsex("1");
        boolean flag = us.addEmpService(emp);

        if (flag == false) {
            String error = "不同员工员工编码不能相同";
            req.setAttribute("error", error);
            req.getRequestDispatcher("ERROR.jsp").forward(req, resp);
        } else {
            ShowEmployee(req, resp);
        }
    }
    //添加部门功能实现
    private void addDep(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        Department d = new Department();
        String TypeFromA = req.getParameter("TypeA");
        String TypeFromB = req.getParameter("TypeB");
        String TypeFromC = req.getParameter("TypeC");
        String TypeFromD = req.getParameter("TypeD");
        String TypeFromE = req.getParameter("TypeE");
        d.setDCode(TypeFromA);
        d.setDName(TypeFromB);
        d.setDInCharge(TypeFromC);
        d.setDeDuty(TypeFromD);
        d.setD_superior(TypeFromE);
        boolean flag = us.addDepService(d);
        System.out.println("addDep d ?=" + d);
        ShowDepartment(req, resp);

    }
    //添加岗位功能实现
    private void Post_Add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        Post p = new Post();
        String TypeFromA = req.getParameter("TypeA");
        String TypeFromB = req.getParameter("TypeB");
        String TypeFromC = req.getParameter("TypeC");
        String TypeFromD = req.getParameter("TypeD");
        String TypeFromE = req.getParameter("TypeE");
        String TypeFromF = req.getParameter("TypeF");
        p.setPostCode(TypeFromA);
        p.setPostName(TypeFromB);
        p.setPost_depart(TypeFromC);
        p.setPost_superior(TypeFromD);
        p.setPost_cate(TypeFromE);
        p.setPost_desc(TypeFromF);
        boolean flag = us.addPostService(p);
        ShowPost(req, resp);

    }
    //
    private void tranToD(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<String> li = us.getDInChargeService();
        req.setAttribute("DeIn", li);
        req.getRequestDispatcher("user/Dep_Add.jsp").forward(req, resp);
    }
    //删除员工
    private void deleteEmp(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        String flag = req.getParameter("flag");
        boolean f = us.deleteEmpService(flag);
        ShowEmployee(req, resp);
    }
      //批量删除
    private void deleteSeleted(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        String[] chk = req.getParameterValues("chk");
        if (req.getParameter("chk") == null) {
            ShowEmployee(req, resp);
        } else {
            for (int i = 0; i < chk.length; i++) {
                int EnoFromchk = Integer.parseInt(chk[i]);
                us.deleteSelected(EnoFromchk);
                System.out.println("servlet.Login.deleteSeleted()" + EnoFromchk);
            }
            ShowEmployee(req, resp);
        }

    }
   //批量删除
    private void deleteSeletedDep(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        String[] chk = req.getParameterValues("chk");
        for (int i = 0; i < chk.length; i++) {
            int DidFromchk = Integer.parseInt(chk[i]);
            us.deleteSelectedDep(DidFromchk);
            System.out.println("servlet.Login.deleteSeleted()" + DidFromchk);
        }
        ShowDepartment(req, resp);
        System.out.println("servlet.Login.deleteSeletedDep()Here ? --");

    }

    private void deleteDep(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        String flag = req.getParameter("flag");
        boolean f = us.deleteDepService(flag);
        System.out.println("Here!" + flag);
        ShowDepartment(req, resp);
    }

    private void ShowPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        List<Post> po = us.ShowPostService();
        System.out.println("servlet.Login.ShowPost()" + po.toString());
        req.setAttribute("po", po);
        req.getRequestDispatcher("user/PostInfo.jsp").forward(req, resp);

    }
   //搜索岗位功能实现
    private void searchPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        String temp = req.getParameter("searchPostNameOrCode");
        List<Post> po = us.searchPostService(temp);

        req.setAttribute("po", po);
        req.getRequestDispatcher("user/PostInfo.jsp").forward(req, resp);

    }
    // 跳转到修改部门功能
    private void tranToDepView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
        us = new UserServiceImpl();
        Department d = new Department();
        d.setDid(Integer.parseInt(req.getParameter("Did")));
        d.setDCode(req.getParameter("DCode"));
        d.setDName(req.getParameter("DName"));
        d.setDInCharge(req.getParameter("DInCharge"));
        d.setDeDuty(req.getParameter("DeDuty"));
        d.setD_superior(req.getParameter("D_superior"));
        req.setAttribute("dep", d);
        List<String> li = us.getDInChargeService();
        req.setAttribute("DeIn", li);
        System.out.println("servlet.Login.tranToDepView()" + req.getAttribute("dep"));
        req.getRequestDispatcher("user/DepAdd_del.jsp").forward(req, resp);
    }
    //跳转到修改岗位功能
    private void tranToPostAdd(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        Post p = new Post();
        p.setPostId(Integer.parseInt(req.getParameter("PostId")));
        p.setPostCode(req.getParameter("PostCode"));
        p.setPostName(req.getParameter("PostName"));
        p.setPost_depart(req.getParameter("Post_depart"));
        p.setPost_superior(req.getParameter("Post_superior"));
        p.setPost_cate(req.getParameter("Post_cate"));
        p.setPost_desc(req.getParameter("Post_desc"));

        System.out.println("servlet.Login.tranToPostAdd()" + p);

        List<String> li = us.getPostIdFromStService();
        List<String> li2 = us.getDNameFromDeService();
        String li3 = us.getDeDutyFromDeService(req.getParameter("Post_depart"));
        req.setAttribute("DName", li2);
        req.setAttribute("poCode", li);
        req.setAttribute("poa", p);
        req.setAttribute("DeDuty", li3);
        req.getRequestDispatcher("user/PostAdd_del.jsp").forward(req, resp);

    }

    private void toPost_addView(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        List<String> li = us.getPostIdFromStService();
        List<String> li2 = us.getDNameFromDeService();
        req.setAttribute("DName", li2);
        req.setAttribute("poCode", li);
        req.getRequestDispatcher("user/Post_Add.jsp").forward(req, resp);
    }

    private void deletePost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        us = new UserServiceImpl();
        String flag = req.getParameter("flag");
        boolean f = us.deletePostService(flag);
        System.out.println("Here!" + flag);
        ShowPost(req, resp);

    }

}
