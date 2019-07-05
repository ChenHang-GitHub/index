<%@page import="javax.websocket.Session"%>
<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.Template"%>
<%@page import="pojo.Department"%>
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
            <script src="https://cdn.staticfile.org/jquery/3.4.0/jquery.min.js"></script>
            <script src="https://cdn.staticfile.org/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>


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
                <li><a href="javascript:;">部门</a></li>
                <li><a href="javascript:;">清单</a></li>
            </ul>
        </div>
        
        
<!--   <div class="tools">
                            <ul class="toolbar">
                                            <li class="click"><span><img src="images/t01.png" /></span>添加</li>

                                <li><span><img src="images/t03.png" /></span>  <input type="submit" style="background: transparent"  value="批量删除"></input> </li>
                            </ul>
                        </div>
        -->
        
        
        
        

            <div class="rightinfo">
                <form action="Login" method="post">
                    <input type="hidden" name="oper" value="searchDep" />
                    <div class="tools">
                        <ul class="toolbar">

                            <li>
                                <span><img src="images/t01.png" /></span>
                                <input name = "searchDepNameOrCode" type="text"  style="height: 25px ; background: transparent" placeholder="请输入编码或者名称" ></input>
                                <input type="submit" style="background: transparent ;font-weight: bold ;color: firebrick" value="查询"></input>
                            </li> 

                        </ul>
                    </div>

                </form>

            <table class="tablelist">
                <form  action="Login" method="post">
                    <input type="hidden" name="oper" value="deleteSeletedDep" />
                    <thead>
                        <tr>
                            <th></th>
                            <th>ID<i class="sort"><img src="images/px.gif" /></i></th>
                            <th>部门编码</th>
                            <th>部门名称</th>
                            <th>部门负责人</th>
                            <th>部门职责</th>
                            <th>上级部门</th>   
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>

                    <%

                        List<Department> t = new ArrayList<Department>();
                        t = (List<Department>) request.getAttribute("dep");

                        for (Department Temp : t) {

                    %>
                        <tr>
                            <td><input name="chk" id="chk" type="checkbox" value="<%=Temp.getDid()%>" /></td>
                            <td><%=Temp.getDid()%></td>
                            <td><%=Temp.getDCode()%></td>
                            <td><%=Temp.getDName()%></td>
                            <td><%=Temp.getDInCharge()%></td>
                            <td><%=Temp.getDeDuty()%></td>
                            <td><%=Temp.getD_superior()%></td>
                            <td><a href="Login?oper=tranToDepAdd&Did=<%=Temp.getDid()%>&DCode=<%=Temp.getDCode()%>&DName=<%=Temp.getDName()%>&DInCharge=<%=Temp.getDInCharge()%>&DeDuty=<%=Temp.getDeDuty()%>&D_superior=<%=Temp.getD_superior()%>" class="tablelink" >修改</a>
                                <a href="Login?oper=deleteDep&flag=<%=Temp.getDid()%>" class="tablelink"> 删除</a></td>
                        </tr> 
                        <% }%>
                     
                       
            <div class="tools">
                <ul class="toolbar">
                    <li class="click"><span><img src="images/t01.png" /></span>添加</li>
                    <li><span><img src="images/t03.png" /></span>  <input type="submit"  style="background: transparent ; width: 79px" value="批量删除"></input> </li>
                </ul>
            </div>

                        
                       
                    </tbody>       
 </form>
            </table>
                        
            <div class="pagin">
                <div class="message">共<i class="blue"></i><%=t.size()%>条记录</div>
                <ul class="paginList">
                    <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>

                    <%
                        int size = (int) request.getAttribute("size");
                        System.out.println("className.methodName()" + size);
                        if (size <= 5 && size >= 1) {
                            size = 1;
                        }

                        if (size > 5 && size % 5 == 0) {
                            size /= size;
                        } else if (size > 5 && size % 5 != 0) {
                            size /= size;
                            size += 1;
                        }
                    %>
                    <% for (int i = 1; i <= size; i++) {%> 

                    <li class="paginItem "><a href="Login?oper=showb&pageNum=<%=i%>"><%=i%></a></li>

                    <% }%>
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
                    <a href="Login?oper=tranToD">     <input  type="button"  class="sure" value="确定" />&nbsp;</a>
                    <input name="" type="button"  class="cancel" value="取消" />
                </div>
            </div>
        </div>
        </div>
        <script type="text/javascript">
            $('.tablelist tbody tr:odd').addClass('odd');
        </script>

    </body>

</html>
