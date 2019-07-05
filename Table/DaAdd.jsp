<%@page import="java.text.SimpleDateFormat"%>
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
                <li><a href="#">打卡单</a></li>
                <li><a href="#">编辑</a></li>
            </ul>
        </div>
        <div class="formbody">


            <div id="usual1" class="usual"> 

                <div class="itab">
                    <ul> 
                        <li><a href="#tab1" class="selected">打卡</a></li> 
                    </ul>
                </div> 
                
                <%
                     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         Date date = new Date(System.currentTimeMillis());
                         String time = df.format(date);
                         HttpSession hs = request.getSession();
                         User u = (User) hs.getAttribute("user");
                     

                 
               %> 
                <form action ="Table" method="post">
                    <input type="hidden" name="oper" value="AddDaTable" />
                    <div id="tab1" class="tabson">
                        <ul class="forminfo">
                            <h1></h1>
                            <li><label><b>*</b>打卡人</label><input name="TypeA"   value="<%=u.getUserName()%>"  readonly="true"  type="text" class="dfinput" style="width:300px;"/></li>
                            <li><label><b>*</b>打卡时间</label><input name="TypeB"  value="<%=time%>"  type="text" class="dfinput" style="width:300px;"/></li>
                            <li><label><b>*</b>备注</label><input name="TypeC" value=""type="textarea" class="dfinput" style="width:300px; height: 130px"/></li>  
                            <li><label>&nbsp;</label><input  type="submit" value ="保存" class="dfinput" style="width:50px;"/></li>
                            </ul>
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