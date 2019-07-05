<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*,pojo.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
	<head>
		<title>实时时间</title>
	</head>
	<script type="text/javascript">
		function load(){
			window.setInterval('showRealTime(clock)',1000);
		}
	</script>
	<body onload="load()">
		<div id="clock"></div>
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
				var now = year + "年" + month + "月" + date + "日<br>星期" + days[day] + "<br>" + hour + ":" + min + ":" + sec;
				clock.innerHTML = "现在的时间是：" + now;
			}
		</script>
	</body>
</html>
