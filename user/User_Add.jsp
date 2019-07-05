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
                <li><a href="javascript:;">员工</a></li>
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
                     
                <form action ="Login" method="post">
                    <input type="hidden" name="oper" value="add" />
                    <div id="tab1" class="tabson">
                        <ul class="forminfo">
                            
                   
                                <h1></h1>
                            <li><label><b>*</b>员工编码</label><input name="TypeA"   value=""required="true" type="text" class="dfinput" style="width:300px;"/></li>
                            <li><label><b>*</b>姓名</label><input name="TypeB"  value=""required="true" type="text" class="dfinput" style="width:300px;"/></li>
                            <li><label><b>*</b>年龄</label><input name="TypeC" value=""required="true" type="text" class="dfinput" style="width:300px;"/></li>
                            <li><label><b>*</b>民族</label><input name="TypeD" value="" required="true"type="text" class="dfinput" style="width:300px;"/></li>

                            <li><label><b>*</b>身份证</label><input name="TypeE" value=""required="true"type="text" class="dfinput" style="width:300px;"/></li>
                            <li><label><b>&nbsp;</b>薪水</label><input name="TypeF" value=""    type="text" class="dfinput" style="width:300px;"/></li>
                            <li><label><b>*</b>联系电话</label><input name="TypeG" required="true" value=""type="text" class="dfinput" style="width:300px;"/></li>
                            <li><label><b>*</b>紧急联系人</label><input name="TypeH" required="true" value="" type="text" class="dfinput" style="width:300px;"/></li>


                            <li><label>&nbsp;&nbsp;岗位<b></b></label>  
                                <div class="vocation">
                                    <select class="select1"  name="TypeI" >
                                        <option value="1">总经理</option>
                                        <option value="2">会计</option>
                                        <option value="3">工程师</option>
                                        <option value="4">项目经理</option>
                                    </select>
                                </div>
                            </li>
                            <li><label><b>*</b>个人描述</label><input name="TypeJ" value=""type="textarea" class="dfinput" style="width:300px; height: 130px"/></li>  
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