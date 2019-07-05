<%@ page language="java" import="java.util.*,pojo.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <base href="<%=basePath%>">
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>无标题文档</title>
            <link href="css/style.css" rel="stylesheet" type="text/css" />
            <script language="JavaScript" src="js/jquery.js"></script>
            <script type="text/javascript">

                function logout() {
                    if (confirm("您确定要退出控制面板吗？"))
                        window.top.location.href = "Login?oper=out";
                    return false;
                }
            </script>


    </head>

    <body style="background:url(images/topbg.gif) repeat-x;">

        <div class="topleft">
            <a href="main.html" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
        </div>
        
        <div  style="padding-top:20px ">
            <div class="topright"> 

                <div class="user" >
                    <span><a style="line-height:30px; font-size:14px;color:#fff" href="main/right.jsp" target="rightFrame"> <%=((User) session.getAttribute("user")).getUserName()%></a></span>
                    <span><a onclick="logout()" href="javascript:void(0)" style="line-height:30px; font-size:14px;color:#fff">退出系统</a></span>
                </div>
            </div>
        </div>
    </body>
</html>
