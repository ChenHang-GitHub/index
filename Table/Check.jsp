<%-- 
    Document   : Check
    Created on : 2019-6-25, 22:01:25
    Author     : chenshihang
--%>

<%@page import="java.util.List"%>
<%@page import="pojo.CheckTable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
    </head><%@page import="pojo.Post"%>
    <%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.Template"%>
    <%@page import="pojo.Employee"%>
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
                <!-- 引入 echarts.js -->
                <script src="js/echarts.min.js"></script>
                <script type="text/javascript">

                </script>
        </head>


        <body>
            <div class="place">
                <span>位置：</span>
                <ul class="placeul">
                    <li><a href="javascript:;">首页</a></li>
                    <li><a href="javascript:;">考勤表</a></li>
                    <li><a href="javascript:;">清单</a></li>
                </ul>
            </div>

            <div class="rightinfo">
                <form action="Table" method="post">
                    <input type="hidden" name="oper" value="searchCheck" />
                    <div class="tools">

                        <ul class="toolbar">
                            <!--<li class="click"><span><img src="images/t01.png" /></span>添加</li>-->
                            <li>
                                <span><img src="images/t01.png" /></span>
                                开始日：<input name="startDate" type="date" style="background: transparent">
                                    截至日：<input name="endDate" type="date" style="background: transparent">
                                        <input name = "searchName" type="text"  style="height: 25px ; background: transparent" placeholder="请输入编码或者名称" ></input>
                                        <input type="submit" style="background: transparent ;font-weight: bold ;color: firebrick" value="查询"></input>
                                        </li> 

                                        </ul>
                                        </div>
                                        </form>

                                        <table class="tablelist">

                                            <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>ID<i class="sort"><img src="images/px.gif" /></i></th>
                                                    <th>员工编码</th>
                                                    <th>员工姓名</th>
                                                    <th>早上上班时间</th>
                                                    <th>下午上班时间</th>
                                                    <th>出勤情况</th>
                                                </tr>

                                            </thead>
                                            <%
                                                List<CheckTable> cl = (List<CheckTable>) request.getAttribute("cl");
                                                for (CheckTable Temp : cl) {
                                            %>


                                            <tbody>
                                                <tr>
                                                    <td><input name="chk" id="chk" type="checkbox" value="1" /></td>
                                                    <td><%=Temp.getKid()%></td>
                                                    <td><%=Temp.getKCode()%></td>
                                                    <td><%=Temp.getKName()%></td>
                                                    <%
                                                        if (Temp.getKMTime() == null) {
                                                    %>
                                                    <td>早上未打卡</td>
                                                    <%}%><%else {
                                                        String subString1 = String.valueOf(Temp.getKMTime()).substring(0,19);
                                                    %>
                                                    <td><%=subString1%></td>
                                                    <%}%>
                                                    <%
                                                        if (Temp.getKATime() == null) {
                                                    %>
                                                    <td>下午未打卡</td>
                                                    <%}%><%else {
                                                         String subString2 = String.valueOf(Temp.getKATime()).substring(0,19);                                                    
                                                    %>
                                                    <td><%=subString2%></td>
                                                    <%}%>

                                                    <td><%=Temp.getState()%></td>
                                                </tr> 
                                                <%}%>
                                            </tbody>


                                        </table>




                                        <div class="pagin">
                                            <div class="message">共<i class="blue"><%=cl.size()%></i> 条记录</div>
                                            <ul class="paginList">
                                                <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
                                                <li class="paginItem"><a href="javascript:;">1</a></li>
                                                <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
                                            </ul>
                                        </div>
                                        <div id="main" style="width: 400px;height:300px; padding-top: 20px"></div>
                                        <script type="text/javascript">
                                            // 基于准备好的dom，初始化echarts实例
                                            var myChart = echarts.init(document.getElementById('main'));
                                            var num = new Array();
                                            num = [<%=request.getAttribute("MState")%>,<%=request.getAttribute("AState")%>,
                                            <%=request.getAttribute("normol")%>,<%=request.getAttribute("kuanggong")%>]

                                            // 指定图表的配置项和数据
                                            var option = {

                                                title: {
                                                    text: '员工出勤情况'
                                                },
                                                tooltip: {},
                                                legend: {
                                                    data: ['销量']
                                                },
                                                xAxis: {
                                                    data: ["早上迟到", "下午迟到", "正常出勤", "旷工"]
                                                },
                                                yAxis: {},
                                                series: [{
                                                        name: '情况',
                                                        type: 'bar',
                                                        data: num
                                                    }]

                                            };

                                            // 使用刚指定的配置项和数据显示图表。
                                            myChart.setOption(option);
                                        </script>

                                        <div class="tip">
                                            <div class="tiptop"><span>提示信息</span><a></a></div>

                                            <div class="tipinfo">
                                                <span><img src="images/ticon.png" /></span>
                                                <div class="tipright">
                                                    <p>是否确认对信息的修改 ？</p>
                                                    <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
                                                </div>
                                            </div>


                                            <div class="tipbtn">
                                                <a href="Login?oper=toPost_addView">     <input  type="button"  class="sure" value="确定" />&nbsp;</a>
                                                <input name="" type="button"  class="cancel" value="取消" />
                                            </div>


                                        </div>

                                        </div>

                                        <script type="text/javascript">
                                            $('.tablelist tbody tr:odd').addClass('odd');
                                        </script>

                                        </body>

                                        </html>