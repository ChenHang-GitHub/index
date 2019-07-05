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
                    <li><a href="javascript:;">首页</a></li>
                    <li><a href="javascript:;">岗位</a></li>
                    <li><a href="javascript:;">清单</a></li>
                </ul>
            </div>

        <form action="Login" method="post">
                 <input type="hidden" name="oper" value="searchPost" />
            <div class="rightinfo">

                <div class="tools">

                    <ul class="toolbar">
                        
                            <li>
                                <span><img src="images/t01.png" /></span>
                                <input name = "searchPostNameOrCode" type="text"  style="height: 25px ; background: transparent" placeholder="请输入编码或者名称" ></input>
                                <input type="submit" style="background: transparent ;font-weight: bold ;color: firebrick" value="查询"></input>
                            </li> 
                        <li class="click"><span><img src="images/t01.png" /></span>添加</li>
                        <li><span><img src="images/t03.png" /></span>批量删除</li>
                  
                    </ul>
     
                </div>
    </form>      

                <table class="tablelist">

                    <thead>
                        <tr>
                            <th></th>
                            <th>ID<i class="sort"><img src="images/px.gif" /></i></th>
                            <th>岗位编码</th>
                            <th>岗位名称</th>
                            <th>所在部门</th>
                            <th>直接上级</th>
                            <th>岗位类别</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <% 
                            List<Post> t = new ArrayList<Post>();
                            t = (List<Post>) request.getAttribute("po");
                            for (Post Temp : t) {

                    %>
                    
                    
                    <tbody>
                        <tr>
                            <td><input name="chk" id="chk" type="checkbox" value="" /></td>
                            <td><%=Temp.getPostId()%></td>
                            <td><%=Temp.getPostCode()%></td>
                            <td><%=Temp.getPostName()%></td>
                            <td><%=Temp.getPost_depart()%></td>
                            <td><%=Temp.getPost_superior()%></td>
                            <td><%=Temp.getPost_cate()%></td>
                            <td><a href="Login?oper=tranToPostAdd&PostId=<%=Temp.getPostId()%>&PostCode=<%=Temp.getPostCode()%>&PostName=<%=Temp.getPostName()%>&Post_depart=<%=Temp.getPost_depart()%>&Post_superior=<%=Temp.getPost_superior()%>&Post_cate=<%=Temp.getPost_cate()%>&Post_desc=<%=Temp.getPost_desc()%>" class="tablelink" >修改</a> <a href="Login?oper=deletePost&flag=<%=Temp.getPostId()%>" class="tablelink"> 删除</a></td>
                        </tr>

                    </tbody>
                         <% }%>

                </table>
                         
                         


                <div class="pagin">
                        <div class="message">共<i class="blue"> <%=((List<Post>) request.getAttribute("po")).size()%></i> 条记录</div>
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
