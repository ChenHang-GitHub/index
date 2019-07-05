<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson .header").click(function(){
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		
		$parent.addClass("active");
		if(!!$(this).next('.sub-menus').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.sub-menus').hide();
			}else{
				$parent.addClass("open").find('.sub-menus').show();	
			}
			
			
		}
	});
	
	// 三级菜单点击
	$('.sub-menus li').click(function(e) {
        $(".sub-menus li.active").removeClass("active")
		$(this).addClass("active");
    });
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#fff3e1;">
	<div class="lefttop"><span></span>考勤系统v1.0</div>
    
    <dl class="leftmenu">
        
    <dd>
        
    <div class="title">
    <!--<span><img src="images/leftico01.png" /></span>管理信息-->
    </div>
        
        <ul class="menuson">
            <li>
                <div class="header">
                    <cite></cite>
                    <!--<a href="index.html" target="rightFrame">公司架构</a>-->
                    公司架构
                </div>
      

                <ul class="sub-menus">
                    <li><a href="Login?oper=showy&pageNum=1" target="rightFrame">员工</a></li>
                    <li><a href="Login?oper=showp" target="rightFrame">岗位</a></li>
                    <li><a href="Login?oper=showb" target="rightFrame">部门</a></li>
 
                </ul>

            </li>       
        </ul>  

        
        
    	<ul class="menuson">
           <li>
            <div class="header">
            <cite></cite>
            <!--<a href="index.html" target="rightFrame">公司架构</a>-->
             考勤数据
            </div>
               
            <ul class="sub-menus">
                <li><a href="Classes?oper=showc" target="rightFrame">班次</a></li>
      
      
            </ul>
        </li>       
        </ul>  
             
    	<ul class="menuson">
           <li>
            <div class="header">
            <cite></cite>
            <!--<a href="index.html" target="rightFrame">公司架构</a>-->
      考勤报表
            </div>
               
            <ul class="sub-menus">
         
                 <li><a href="Table?oper=showda" target="rightFrame">打卡单</a></li>
            <li><a href="Table?oper=showre"target="rightFrame">补卡单</a></li>
               <li><a href="Table?oper=showch"target="rightFrame">考勤表</a></li>
                    <li><a href="Table?oper=showsa"target="rightFrame">派薪单 </a></li>
            </ul>
        </li>  
            
        </ul>  
           
    	<ul class="menuson">
           <li>
            <div class="header">
            <cite></cite>
            <!--<a href="index.html" target="rightFrame">公司架构</a>-->
      考勤设置
            </div>
               
            <ul class="sub-menus">
            <li><a href="javascript:;">员工</a></li>
            <li><a href="javascript:;">岗位</a></li>
            <li><a href="javascript:;">部门</a></li>
            
            </ul>
        </li>   
            
        </ul>  
            	<ul class="menuson">
           <li>
            <div class="header">
            <cite></cite>
            <!--<a href="index.html" target="rightFrame">公司架构</a>-->
            财务管理
      
            </div>
               
            <ul class="sub-menus">
            <li><a href="javascript:;">员工</a></li>
            <li><a href="javascript:;">岗位</a></li>
            <li><a href="javascript:;">部门</a></li>
            
            </ul>
        </li>       
        </ul>  
    </dd>
    
    </dl>
    
</body>
</html>
