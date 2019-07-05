<%@page import="pojo.Post"%>
<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.Template"%>
<%@page import="pojo.Employee"%>
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
                <li><a href="#">首页</a></li>
                <li><a href="#">岗位</a></li>
                <li><a href="#">清单</a></li>
            </ul>
        </div>

        <div class="rightinfo">
            <form action="Classes" method="post">
                <input type="hidden" name="oper" value="searchClass" />
                <div class="tools">

                    <ul class="toolbar">
                        <li class="click"><span><img src="images/t01.png" /></span>添加</li>
                        <li>
                            <span><img src="images/t01.png" /></span>
                            <input name = "searchDepNameOrCode" type="text"  style="height: 25px ; background: transparent" placeholder="请输入编码或者名称" ></input>
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
                        <th>打卡人编码</th>
                        <th>打卡人姓名</th>
                        <th>打卡日期</th>
                        <th>操作</th>
                    </tr>

                </thead>
                <%
//                            List<Post> t = new ArrayList<Post>();
//                            t = (List<Post>) request.getAttribute("po");
//                            for (Post Temp : t) {
                       

                %>


                <tbody>
                    <tr>
                        <td><input name="chk" id="chk" type="checkbox" value="1" /></td>
                        <td>1</td>
                        <td>2</td>
                        <td>3</td>
                        <td>4</td>
                        <td><a href="" class="tablelink" >修改</a>
                            <a href="" class="tablelink">删除</a></td>
                    </tr> 

                </tbody>


            </table>




            <div class="pagin">
                <div class="message">共<i class="blue">1</i> 条记录</div>
                <ul class="paginList">
                    <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
                    <li class="paginItem"><a href="javascript:;">1</a></li>
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
