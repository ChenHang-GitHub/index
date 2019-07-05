<%@page import="pojo.User"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
            <script type="text/javascript" src="js/jquery.js"></script>
            <script type="text/javascript">
		function load(){
			window.setInterval('showRealTime(clock)',1000);
		}
	    </script>

    </head>


    <body onload="load()">

        <div class="place">
            <span>位置：</span>
            <ul class="placeul">
                <li>个人信息</li>
            </ul>
        </div>

        <div class="mainindex">


            <div class="welinfo">
                <span><img src="images/sun.png" alt="天气" /></span>
                <b>    <span><%=((User) session.getAttribute("user")).getUserName()%></span> 欢迎使用考勤管理系统</b>
            </div>

            <div class="welinfo">
                <span><img src="images/time.png" alt="时间" /></span> 
                
                
                <div id="clock">当前时间：</div>
		<script type="text/javascript">
			function showRealTime(clock){
				var d = new Date();
				var year = d.getFullYear();
				var month = d.getMonth() + 1;
				var date = d.getDate();
				var days = new Array("日","一","二","三","四","五","六");
				var day = d.getDay();
				var hour = (d.getHours() < 10) ? ("0" + d.getHours()) : d.getHours();
				var min = (d.getMinutes() < 10) ? ("0" + d.getMinutes()) : d.getMinutes();
				var sec = (d.getSeconds() < 10) ? ("0" + d.getSeconds()) : d.getSeconds();
				var now = year + "年" + month + "月" + date + "日星期" + days[day] +  hour + ":" + min + ":" + sec;
				clock.innerHTML = "现在的时间是：" + now;
			}
		</script>
    
                
                
                
                
            </div>


            </div>
     

 </body>

</html>
