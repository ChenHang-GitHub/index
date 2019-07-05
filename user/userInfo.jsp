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
                
            var idTmr;
        function  getExplorer() {
            var explorer = window.navigator.userAgent ;
            //ie
            if (explorer.indexOf("MSIE") >= 0) {
                return 'ie';
            }
            //firefox
            else if (explorer.indexOf("Firefox") >= 0) {
                return 'Firefox';
            }
            //Chrome
            else if(explorer.indexOf("Chrome") >= 0){
                return 'Chrome';
            }
            //Opera
            else if(explorer.indexOf("Opera") >= 0){
                return 'Opera';
            }
            //Safari
            else if(explorer.indexOf("Safari") >= 0){
                return 'Safari';
            }
        }    
            function method5(tableid) {
    
            if(getExplorer()=='ie')
            {
                var curTbl = document.getElementById(tableid);
                var oXL = new ActiveXObject("Excel.Application");
                var oWB = oXL.Workbooks.Add();
                var xlsheet = oWB.Worksheets(1);
                var sel = document.body.createTextRange();
                sel.moveToElementText(curTbl);
                sel.select();
                sel.execCommand("Copy");
                xlsheet.Paste();
                oXL.Visible = true;

                try {
                    var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
                } catch (e) {
                    print("Nested catch caught " + e);
                } finally {
                    oWB.SaveAs(fname);
                    oWB.Close(savechanges = false);
                    oXL.Quit();
                    oXL = null;
                    idTmr = window.setInterval("Cleanup();", 1);
                }

            }
            else
            {
                tableToExcel(tableid)
            }
        }
         function Cleanup() {
            window.clearInterval(idTmr);
            CollectGarbage();
        }
        var tableToExcel = (function() {
            var uri = 'data:application/vnd.ms-excel;base64,',
                    template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',
                    base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
                    format = function(s, c) {
                        return s.replace(/{(\w+)}/g,
                                function(m, p) { return c[p]; }) }
            return function(table, name) {
                if (!table.nodeType) table = document.getElementById(table)
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
                window.location.href = uri + base64(format(template, ctx))
            }
        })()
        
        
            </script>


    </head>


    <body>
        <div class="place">
            <span>位置：</span>
            <ul class="placeul">
                <li><a href="javascript:;">首页</a></li>
                <li><a href="javascript:;">员工</a></li>
                <li><a href="javascript:;">清单</a></li>
            </ul>
        </div>

        <div class="rightinfo">
<!--            <div class="tools">
                <ul class="toolbar">
                 
             
                </ul>-->
       

            <form action="Login" method="post">
                <input type="hidden" name="oper" value="searchEmp" />
                <div class="tools">

                    <ul class="toolbar">


                        <!--<li class="click"><span><img src="images/t02.png" /></span>修改</li>-->
                        <!--<li><span><img src="images/t03.png" /></span><a href="Login?oper=deleteSeleted">批量删除</a></li>-->

                        <li>
                            <span><img src="images/t01.png" /></span>
                            <input name = "searchEmpName" type="text" style="height: 25px ; background: transparent" placeholder="请输入用户名查询" ></input>
                            <input type="submit"  style="background: transparent ;font-weight: bold ;color: firebrick"value="查询"></input>
                        </li> 

                    </ul>
                </div>
            </form>
  
            
            
            
            <!--table-->      


            <table class="tablelist" id="tableExcel">
                <form  action="Login" method="post">
                    <input type="hidden" name="oper" value="deleteSeleted" />
                    <thead>

                        <tr>
                            <th></th>
                            <th>ID<i class="sort"><img src="images/px.gif" /></i></th>
                            <th>员工编码</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>民族</th>
                            <th>岗位</th>
                            <th>操作</th>
                        </tr>

                    </thead>

                    <tbody>
                        <%
                            List<Employee> t = new ArrayList<Employee>();
                            t = (List<Employee>) request.getAttribute("emp");
                            for (Employee Temp : t) {
                        %>
                        <tr>

                            <td><input name="chk" id="chk" type="checkbox" value="<%=Temp.getEmp_Id()%>" /></td>
                            <td><%= Temp.getEmp_Id()%></td>
                            <td><%= Temp.getEno()%></td>
                            <td><%= Temp.getEname()%></td>
                            <%
                            if ((Temp.getEsex()).equals("1")) { %>
                            <td>男</td>
                            <%
                            } else {
                            %>
                            <td>女</td>
                            <% }%>

                            <td><%=Temp.getEage()%></td>
                            <td><%=Temp.getEmin()%></td>
                            <td><%=Temp.getPost()%></td>
                            <td><a href="Login?oper=tranTo&flag=<%= Temp.getEmp_Id()%>" class="tablelink" >修改</a> <a href="Login?oper=delete&flag=<%= Temp.getEmp_Id()%>&pageNum=1" class="tablelink">删除</a></td>

                        </tr> 
                        <%      }
                        %>
                    </tbody>
                                   
                        <div class="tools">

                            <ul class="toolbar">
                                   <li class="click" style="width: 91px" ><span><img src="images/t01.png" /></span>添加</li>
                                <li><span><img src="images/t03.png" /></span>  <input type="submit"  style="background: transparent ; width:" value="批量删除"></input> </li>   
                                      <li>
                         <button type="button"  style ="background: transparent;color: firebrick ; font-weight: bold"onclick="method5('tableExcel')">一键导出Excel</button>
                         </li>
                            </ul>
                      
                        </div>

                </form>       

            </table>

   </div>
                        
                        
                        
                        
            <div class="pagin">

                <div class="message">共<i class="blue"> <%=((List<Employee>) request.getAttribute("emp")).size()%></i> 条记录</div>

                <ul class="paginList">
                    <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
                    <!--size = t.size/5;-->
                    <!--if(i<=5)-->

                    <%
                        int size = (int) request.getAttribute("size");
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

                    <li class="paginItem "><a href="Login?oper=showy&pageNum=<%=i%>"><%=i%></a></li>

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
                    <a href="user/User_Add.jsp">     <input  type="button"  class="sure" value="确定" />&nbsp;</a>
                    <input name="" type="button"  class="cancel" value="取消" />
                </div>


            </div>

        </div>

        <script type="text/javascript">
            $('.tablelist tbody tr:odd').addClass('odd');
        </script>

    </body>

</html>
