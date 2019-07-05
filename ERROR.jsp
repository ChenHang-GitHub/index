<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery.js"></script>

        <script language="javascript">
            $(function () {
                $('.error').css({'position': 'absolute', 'left': ($(window).width() - 490) / 2});
                $(window).resize(function () {
                    $('.error').css({'position': 'absolute', 'left': ($(window).width() - 490) / 2});
                })
            });
        </script> 


    </head>


    <body style="background:#FFF8ED;">

        <div class="place">
            <span>位置：</span>
            <ul class="placeul">
                <li><a href="javascript:;">首页</a></li>
                <li><a href="javascript:;">错误提示</a></li>
            </ul>
        </div>

        <div class="error">

            <%
                String error = (String) request.getAttribute("error");
                if (error.equals("不同员工班次编码不能相同")) {
            %>
            <h2><%=error%>错误</h2>
            <div class="reindex"><a href="main/right.jsp" target="rightFrame">返回首页</a></div>

            <%     } else if (error.equals("不同员工员工编码不能相同")) {
            %>
            <h2><%=error%>错误</h2>
            <div class="reindex"><a href="main/right.jsp" target="rightFrame">返回首页</a></div>
            <%           } else if (error.equals("部分员工未添加员工班次信息")) {
            %>
            <h2><%=error%>&nbsp;&nbsp;错误</h2>
            <div class="reindex"><a href="main/right.jsp" target="rightFrame">返回首页</a></div>
            <% } else if (error.equals("添加不存在员工班次信息")) {
            %>
            <h2><%=error%>错误</h2>
            <div class="reindex"><a href="main/right.jsp" target="rightFrame">返回首页</a></div>
            <%
            } else if (error.equals("考勤表无该员工考勤信息")) {
            %>
            <h2><%=error%>错误</h2>
            <div class="reindex"><a href="main/right.jsp" target="rightFrame">返回首页</a></div>
            <%   } else if (error.equals("无该员工")) {
            %>
            <h2><%=error%>错误</h2>
            <div class="reindex"><a href="main/right.jsp" target="rightFrame">返回首页</a></div>

            <%}
            %>


        </div>


    </body>

</html>
