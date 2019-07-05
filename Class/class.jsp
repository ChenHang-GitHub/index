<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.Template"%>
<%@page import="pojo.Cl"%>
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
                $(document).ready(function () {
                    $(".click").click(function () {
                        $(".tip").fadeIn(200);
                    });

                    $(".tiptop a").click(function () {
                        $(".tip").fadeOut(200);
                    });

                    $(".sure").click(function () {
                        $(".tip").fadeOut(100);
                    });

                    $(".cancel").click(function () {
                        $(".tip").fadeOut(100);
                    });

                });
            </script>


    </head>


    <body>
        <div class="place">
            <span>位置：</span>
            <ul class="placeul">
                <li><a href="javascript:;">首页</a></li>
                <li><a href="javascript:;">班次</a></li>
                <li><a href="javascript:;">清单</a></li>
            </ul>
        </div>

        <div class="rightinfo">


            <form action="Classes" method="post">
                <input type="hidden" name="oper" value="searchClass" />
                <div class="tools">

                    <ul class="toolbar">

                        <li class="click"><span><img src="images/t01.png" /></span>添加</li>
                        <!--<li class="click"><span><img src="images/t02.png" /></span>修改</li>-->
                        <!--<li><span><img src="images/t03.png" /></span><a href="Login?oper=deleteSeleted">批量删除</a></li>-->

                        <li>
                            <span><img src="images/t01.png" /></span>
                            <input name = "searchClassBy" type="text" style="height: 25px ;background: transparent" placeholder="请输入名称或编码" ></input>
                            <input type="submit" style="background: transparent; font-weight: bold ;color: firebrick " value="查询"></input>
                        </li> 

                    </ul>
                </div>
            </form>

            <!--table-->      


            <table class="tablelist">
                <form  action="Login" method="post">
                    <input type="hidden" name="oper" value="deleteSeleted" />
                    <thead>

                        <tr>
                            <th></th>
                            <th>ID<i class="sort"><img src="images/px.gif" /></i></th>
                            <th>班次编码</th>
                            <th>班次名称</th>
                            <th>早上上班时间</th>
                            <th>下午上班时间</th>
                            <th>操作</th>
                        </tr>

                    </thead>

                    <tbody>
                        <%
                            List<Cl> t = new ArrayList<Cl>();
                            t = (List<Cl>) request.getAttribute("cl");
                            for (Cl Temp : t) {
                        %>
                        <tr>

                            <td><input name="chk" id="chk" type="checkbox" value="1" /></td>
                            <td><%=Temp.getCid()%></td>
                            <td><%=Temp.getClassCode()%></td>
                            <td><%=Temp.getClassName()%></td>
                            <td><%=Temp.getMTime()%></td>
                            <td><%=Temp.getATime()%></td>
                            <td><a href="Classes?oper=Class_Add&Cid=<%=Temp.getCid()%>&ClassCode=<%=Temp.getClassCode()%>&ClassName=<%=Temp.getClassName()%>&MTime=<%=Temp.getMTime()%>&ATime=<%=Temp.getATime()%>" class="tablelink" >修改</a>
                                <a href="Classes?oper=Class_Del&Cid=<%=Temp.getCid()%>" class="tablelink">删除</a></td>
                        </tr> 
                        <%}%>
<!--                        <div class="tools">
                            <ul class="toolbar">
                                <li><span><img src="images/t03.png" /></span>  <input type="submit"  style="background: transparent" value="批量删除"></input> </li></ul></div>
                            </ul>
                        </div>-->
                    </tbody>
                </form>       

            </table>

                        
                        
                        
                        

            <div class="pagin">

                <div class="message">共<i class="blue"><%=t.size()%></i> 条记录</div>

                <ul class="paginList">
                    <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
                    <li class="paginItem current "><a href="">1</a></li>
                    <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
                </ul>

            </div>


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
                    <a href="Class/Class_Add.jsp">     <input  type="button"  class="sure" value="确定" />&nbsp;</a>
                    <input name="" type="button"  class="cancel" value="取消" />
                </div>


            </div>

        </div>

        <script type="text/javascript">
            $('.tablelist tbody tr:odd').addClass('odd');
        </script>

    </body>

</html>
