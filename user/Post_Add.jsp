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
            <link href="css/select.css" rel="stylesheet" type="text/css" />
            <script type="text/javascript" src="js/jquery.js"></script>
            <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
            <script type="text/javascript" src="js/select-ui.min.js"></script>
            <script type="text/javascript" src="editor/kindeditor.js"></script>

            <script type="text/javascript">
                KE.show({
                    id: 'content7',
                    cssPath: './index.css'
                });
            </script>

            <script type="text/javascript">
                $(document).ready(function (e) {
                    $(".select1").uedSelect({
                        width: 345
                    });
                    $(".select2").uedSelect({
                        width: 167
                    });
                    $(".select3").uedSelect({
                        width: 100
                    });
                });
            </script>
    </head>

    <body>

        <div class="place">
            <span>位置：</span>
            <ul class="placeul">
                <li><a href="javascript:;">岗位</a></li>
                <li><a href="javascript:;">编辑</a></li>
            </ul>
        </div>



<!--    <form action="Login" method="post">
    
    	<ul> 
    	<li></li>
	    <li><input name="uname" type="text" placeholder="用户名" class="loginuser"  /></li>
	    <li><input name="pwd" type="password" placeholder="密码" class="loginpwd"  /></li>
	    <li><input  type="submit" class="loginbtn" value="登录" /></li>  
	   </ul>
    </form>
    -->


        <div class="formbody">


            <div id="usual1" class="usual"> 

                <div class="itab">
                    <ul> 
                        <li><a href="#tab1" class="selected">修改个人信息</a></li> 
                    </ul>
                </div> 
                         <%
//                              Post p =  (Post)request.getAttribute("poa");
                                %>
                <form action ="Login" method="post">
                    <input type="hidden" name="oper" value="Post_Add" />
                    <div id="tab1" class="tabson">
                        <ul class="forminfo">
                                <h1></h1>
                                <li><label><b>*</b>岗位编码</label><input name="TypeA"  required="true"   value="" type="text"  class="dfinput" style="width:300px;"/></li>
                            <li><label><b>*</b>岗位名称</label><input name="TypeB" required="true"  value="" type="text" class="dfinput" style="width:300px;"/></li>
                           
                            <li><label>&nbsp;&nbsp;所在部门<b></b></label>  
                                <div class="vocation"  >
                                    <select class="select1"  name="TypeC">
                                       <% 
                                       List<String>  li2  =  ( List<String> )request.getAttribute("DName");
                                       for(String Temp :li2)
                                       { 
                                        %>      
                                        <option><%=Temp%></option>
                                        <% }%>
                                    </select>
                                </div>
                            </li>
                            
                                   <li><label>&nbsp;&nbsp;直接上级<b></b></label>  
                                <div class="vocation"  >
                                    <select class="select1"  name="TypeD">
                                        <% 
                                    
                                       List<String>  li  =  ( List<String> )request.getAttribute("poCode");
                                       for(String Temp :li)
                                       { 
                                        %>      
                                        <option><%=Temp%></option>
                                        <% }%>
                                    </select>
                                </div>
                            </li>
                                    
                                   <li><label>&nbsp;&nbsp;岗位类别<b></b></label>  
                                <div class="vocation"  >
                                    <select class="select1"  name="TypeE">
                                               <option>管理类</option>
                                               <option>技术类</option> 
                                               <option>文职类</option>
                                    </select>
                                </div>
                            </li>
                                  <%
//                                   String duty =   (String) request.getAttribute("DeDuty");
                                  
                                  %>
                       
                           
                                  <li><label><b>&nbsp;</b>岗位职责</label><input name="TypeF" value="" type="textarea" class="dfinput" style="width:300px;height: 80px"/></li>
                            <li><label>&nbsp;</label><input  type="submit" value ="提交" class="dfinput" style="width:50px;"/></li>
                    </div> 
                </form>
            </div> 

        </div> 




        <script type="text/javascript">
            $("#usual1 ul").idTabs();
        </script>

        <script type="text/javascript">
            $('.tablelist tbody tr:odd').addClass('odd');
        </script>


    </body>

</html>