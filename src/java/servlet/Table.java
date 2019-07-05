
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.CheckTable;
import pojo.PunchCard;
import pojo.RepairCard;
import pojo.Salary;
import pojo.User;
import service.TableService;
import service.TableServiceImpl;
import service.UserService;
import service.UserServiceImpl;

/**
 * 打卡单 补卡单 考勤表 派薪单的servlet
 *
 * @author chenshihang
 */
public class Table extends HttpServlet {

    TableService ts = null;

    protected void Service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, Exception {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //通过获取oper 来判断调用相应的处理方法
        String oper = req.getParameter("oper");
        if ("showda".equals(oper)) {
            showDa(req, resp);
        } else if ("AddDaTable".equals(oper)) {
            AddDaTable(req, resp);
        } else if ("showre".equals(oper)) {
            showRe(req, resp);
        } else if ("AddReTable".equals(oper)) {
            AddReTable(req, resp);
        } else if ("showch".equals(oper)) {
            showCh(req, resp);
        } else if ("searchCheck".equals(oper)) {
            searchCheck(req, resp);
        } else if ("showsa".equals(oper)) {
            showSalary(req, resp);
        } else if ("addSalary".equals(oper)) {
            addSalary(req, resp);
        } else if ("deleteSalary".equals(oper)) {
            deleteSalary(req, resp);
        } else {
            System.out.println("error");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Service(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Service(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // 显示打卡单信息
    private void showDa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ts = new TableServiceImpl();
        List<PunchCard> pl = ts.showDaService();
        req.setAttribute("pl", pl);
        req.getRequestDispatcher("Table/DaTable.jsp").forward(req, resp);
    }

    //添加打卡单信息
    private void AddDaTable(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ts = new TableServiceImpl();
        String TypeA = req.getParameter("TypeA");
        String TypeB = req.getParameter("TypeB");
        String TypeC = req.getParameter("TypeC");
        PunchCard p = new PunchCard();
        HttpSession hs = req.getSession();
        hs.getAttribute("user");
        User u = (User) hs.getAttribute("user");
        System.out.println("这里User:?" + u);
        p.setPno(u.getUno());
        p.setPdesc(TypeC);
        p.setPName(TypeA);
        Timestamp time = Timestamp.valueOf(TypeB);
        //String 转 DateStame
        p.setPDate(time);
        boolean flag = ts.AddDaTableService(p);
        showDa(req, resp);

        System.out.println("servlet.Table.showDa()" + p);
//        req.getRequestDispatcher("Table/DaAdd.jsp").forward(req, resp);
    }

    // 显示补卡单信息
    private void showRe(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ts = new TableServiceImpl();
        List<RepairCard> rl = ts.showReService();
        System.out.println("servlet.Table.showRe()Rlllllll" + rl);
        req.setAttribute("rl", rl);
        req.getRequestDispatcher("Table/ReTable.jsp").forward(req, resp);
    }
    //添加补卡单信息

    private void AddReTable(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ts = new TableServiceImpl();
        String TypeA = req.getParameter("TypeA");
        String TypeB = req.getParameter("TypeB");
        String TypeC = req.getParameter("TypeC");
        RepairCard r = new RepairCard();
        //通过获取session获取登录人的信息 
        HttpSession hs = req.getSession();
        hs.getAttribute("user");
        User u = (User) hs.getAttribute("user");
        r.setRno(u.getUno());
        r.setRdesc(TypeC);
        r.setRName(TypeA);
        Timestamp time = Timestamp.valueOf(TypeB);
        //String 转 DateStame
        r.setRDate(time);
        boolean flag = ts.AddReTableService(r);
//           showDa(req,resp);
        showRe(req, resp);
        System.out.println("servlet.Table.showDa()" + r);
    }

    //显示考勤表信息
    private void showCh(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ts = new TableServiceImpl();
        //获取当前月份
        Calendar c;
        c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        System.out.println("月份" + month);
        List<CheckTable> cl = ts.showChAndAddState(month);
        if (cl.size() == 0) {
                //没有员工班次信息 无法查看出勤情况 抛出异常页面
            String error = "部分员工未添加员工班次信息";
            req.setAttribute("error", error);
            req.getRequestDispatcher("ERROR.jsp").forward(req, resp);
        } else {
            // 用于向jsp 页面的 Echart传递出勤情况参数 
            System.out.println("输出cl数据：" + cl);
            req.setAttribute("cl", cl);
            int MState = 0;
            int AState = 0;
            int kuanggong = 0;
            int normol = 0;
            for (CheckTable Temp : cl) {
                if (Temp.getState().equals("下午早退")) {
                    AState++;
                } else if (Temp.getState().equals("旷工")) {
                    kuanggong++;
                } else if (Temp.getState().equals("早上迟到")) {
                    MState++;
                } else {
                    normol++;
                }
            }
            req.setAttribute("MState", MState);
            req.setAttribute("AState", AState);
            req.setAttribute("kuanggong", kuanggong);
            req.setAttribute("normol", normol);
            System.out.println("数组" + MState + AState + kuanggong + normol);
            req.getRequestDispatcher("Table/Check.jsp").forward(req, resp);
        }
    }
      /*
         出勤表的搜索功能实现方法和 EChart方法 
    */
    private void searchCheck(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ts = new TableServiceImpl();
        if (!req.getParameter("startDate").equals("") && !req.getParameter("endDate").equals("") && !req.getParameter("searchName").equals("")) {
            System.out.println("搜索条件：" + req.getParameter("startDate") + req.getParameter("endDate") + req.getParameter("searchName"));
            List<CheckTable> cl = ts.searchCheakAllService(req.getParameter("startDate"), req.getParameter("endDate"), req.getParameter("searchName"));
            req.setAttribute("cl", cl);
            int MState = 0;
            int AState = 0;
            int kuanggong = 0;
            int normol = 0;
            for (CheckTable Temp : cl) {
                if (Temp.getState().equals("下午早退")) {
                    AState++;
                } else if (Temp.getState().equals("旷工")) {
                    kuanggong++;
                } else if (Temp.getState().equals("早上迟到")) {
                    MState++;
                } else {
                    normol++;
                }
            }
            req.setAttribute("MState", MState);
            req.setAttribute("AState", AState);
            req.setAttribute("kuanggong", kuanggong);
            req.setAttribute("normol", normol);
            System.out.println("数组" + MState + AState + kuanggong + normol);
            req.getRequestDispatcher("Table/Check.jsp").forward(req, resp);
//               showCh(req, resp);
        } else {
            showCh(req, resp);
        }

    }
     // 显示派薪单
    private void showSalary(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ts = new TableServiceImpl();
        List<Salary> sl = ts.showSalaryService();
        System.out.println("servlet.Table.showSalary()" + sl);
        req.setAttribute("sl", sl);
        req.getRequestDispatcher("Salary/salary.jsp").forward(req, resp);
    }
      // 添加薪水信息
    private void addSalary(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ts = new TableServiceImpl();
        String TypeA = req.getParameter("TypeA");
        String TypeB = req.getParameter("TypeB");
        String TypeC = req.getParameter("TypeC");
        String TypeD = req.getParameter("TypeD");
        String TypeE = req.getParameter("TypeE");
        String TypeF = req.getParameter("TypeF");
        System.out.println("servlet.Table.addSalary()hhh");
        System.out.println("cd ;" + TypeC + TypeD);
//        java.util.Date curDate = sDate;
//        java.sql.Date sdate = new java.sql.Date(curDate.getTime());
        Salary s = new Salary();
        s.setSName(TypeA);
        s.setSCode(TypeF);
        // date 转换  
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date sDate = new Date();
            sDate = df.parse(TypeC);
            Date eDate = df.parse(TypeD);
            s.setSDate(sDate);
            s.setEDate(eDate);
        } catch (Exception e) {
            resp.sendRedirect("DateError.jsp");
        }
        
        BigDecimal bd = new BigDecimal(TypeE);
        s.setSalary(bd);
        boolean flag1 = ts.checkEmpByNameAndCodeService(s);
        if (flag1 == true) {
            boolean flag2 = ts.addSalaryService(s);
            if (flag2 == false) {
                // 考勤表无该员工考勤信息时 抛出错误页面 
                String error = "考勤表无该员工考勤信息";
                req.setAttribute("error", error);
                req.getRequestDispatcher("ERROR.jsp").forward(req, resp);
            }
        } else {
            // 添加非员工表员工时抛出错误页面
            String error = "无该员工";
            req.setAttribute("error", error);
            req.getRequestDispatcher("ERROR.jsp").forward(req, resp);
        }

        showSalary(req, resp);
        System.out.println("servlet.Table.addSalary()" + s.toString());
//           req.getRequestDispatcher("Salary/salary_Add.jsp").forward(req, resp);
    }
// 删除薪水单方法
    private void deleteSalary(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ts = new TableServiceImpl();
        String Sid = req.getParameter("SId");
        boolean flag = ts.deleteSalaryService(Sid);
        System.out.println("删除" + Sid + flag);
        showSalary(req, resp);
    }

}
