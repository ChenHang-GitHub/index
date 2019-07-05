/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Cl;
import service.ClassService;
import service.ClassServiceImpl;

/**
 * 班次表的servlet
 *
 * @author chenshihang
 */
public class Classes extends HttpServlet {

    ClassService cs = null;

    protected void Service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, Exception {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //通过获取oper来调用班次表不同的方法
        String oper = req.getParameter("oper");
        if ("showc".equals(oper)) {
            showClassInfo(req, resp);
        } else if ("Class_Add".equals(oper)) {
            ClassAdd_del(req, resp);
        } else if ("ClassUpdate".equals(oper)) {
            ClassUpdate(req, resp);
        } else if ("ClassAdd".equals(oper)) {
            ClassAdd(req, resp);
        } else if ("Class_Del".equals(oper)) {
            ClassDel(req, resp);

        } else if ("searchClass".equals(oper)) {
            searchClass(req, resp);
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
            Logger.getLogger(Classes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Classes.class.getName()).log(Level.SEVERE, null, ex);
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

    //显示所有班次信息
    private void showClassInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        cs = new ClassServiceImpl();
        List<Cl> c = cs.showClassInfoService();
        req.setAttribute("cl", c);
        req.getRequestDispatcher("Class/class.jsp").forward(req, resp);
    }

    //修改已有班次信息的方法
    private void ClassAdd_del(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Cl c = new Cl();
        c.setCid(Integer.parseInt(req.getParameter("Cid")));
        c.setClassCode(req.getParameter("ClassCode"));
        c.setClassName(req.getParameter("ClassName"));
        c.setMTime(req.getParameter("MTime"));
        c.setATime(req.getParameter("ATime"));
        req.setAttribute("id", c.getCid());
        req.setAttribute("cl", c);
        System.out.println("servlet.Classes.ClassAdd_del()" + c);

        req.getRequestDispatcher("Class/ClassAdd_del.jsp").forward(req, resp);
        System.out.println("servlet.Classes.Class_Add()Adddddddddddddddd");
    }

    //更新班次信息的方法
    private void ClassUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        cs = new ClassServiceImpl();
        Cl c = new Cl();
        String TypeFromA = req.getParameter("TypeA");
        String TypeFromB = req.getParameter("TypeB");
        String TypeFromC = req.getParameter("TypeC");
        String TypeFromD = req.getParameter("TypeD");
        String TypeFromE = req.getParameter("TypeE");
        //
        c.setCid(Integer.parseInt(req.getParameter("TypeF")));
        c.setClassCode(TypeFromA);
        c.setClassName(TypeFromB);
        c.setMTime(TypeFromC);
        c.setATime(TypeFromD);
        c.setCdesc(TypeFromE);
        boolean flag = cs.ClassUpdateService(c);
        if (flag == true) {
            showClassInfo(req, resp);
            // 对错误的更新进行处理
        } else {
            String error = "不同员工班次编码不能相同";
            req.setAttribute("error", error);
            req.getRequestDispatcher("ERROR.jsp").forward(req, resp);
        }

    }

    //添加班次信息的方法
    private void ClassAdd(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        cs = new ClassServiceImpl();
        Cl c = new Cl();
        String TypeFromA = req.getParameter("TypeA");
        String TypeFromB = req.getParameter("TypeB");
        String TypeFromC = req.getParameter("TypeC");
        String TypeFromD = req.getParameter("TypeD");
        String TypeFromE = req.getParameter("TypeE");
        String TypeFromF = req.getParameter("TypeF");
        c.setClassCode(TypeFromA);
        c.setClassName(TypeFromB);
        c.setMTime(TypeFromC);
        c.setATime(TypeFromD);
        c.setCdesc(TypeFromE);

        boolean flag = cs.ClassAddService(c, TypeFromF);

        // 级联更新出错的处理
        if (flag == false) {
            String error = "添加不存在员工班次信息";
            req.setAttribute("error", error);
            req.getRequestDispatcher("ERROR.jsp").forward(req, resp);
        } else {
            showClassInfo(req, resp);
        }
        System.out.println("servlet.Classes.ClassAdd()Here  c =?" + c);

    }

    //删除班次信息的方法
    private void ClassDel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        cs = new ClassServiceImpl();
        int DelById = Integer.parseInt(req.getParameter("Cid"));
        boolean flag = cs.ClassDelService(DelById);
        showClassInfo(req, resp);
    }
    //班次的搜索功能实现

    private void searchClass(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        cs = new ClassServiceImpl();
        String search = req.getParameter("searchClassBy");
        List<Cl> cl = cs.searchClassService(search);
        req.setAttribute("cl", cl);
        req.getRequestDispatcher("Class/class.jsp").forward(req, resp);
    }

}
