<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>校园在线考试系统-教师</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="shortcut icon" href="images/kao.jpg">
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />      
    </div>
    
    <%
    	String tNo = null;
    	tNo = session.getAttribute("user").toString();
    	if(tNo == null){
    		response.sendRedirect("login.jsp");
        	return;
    	}
    %>
    <div class="t_content" ng-app="teacherApp">
        <div class="inner_center">
            <div class="left_side fl">
	            <ul>
	                <li><a select-link href="#/t_class/<%=tNo %>" class="teacherClass">我的班级</a></li>
	                <li><a select-link href="#/t_class/<%=tNo %>">查看班级</a></li>
	                <li><a select-link href="#/t_stu/<%=tNo %>">查看学生</a></li>
	                <li><a select-link href="#/addStu/<%=tNo %>">新增学生</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/t_sub/<%=tNo %>" class="teacherSub">我的课程</a></li>
	                <li><a select-link href="#/t_sub/<%=tNo %>">查看课程</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/t_exam/<%=tNo %>"  class="teacherExam">我的考试</a></li>
	                <li><a select-link href="#/t_exam/<%=tNo %>">已有考试</a></li>
	                <li><a select-link href="#/addExam/<%=tNo %>">新增考试</a></li>
	                <li><a select-link href="#/addPaper/<%=tNo %>">新增试题</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/t_stuScore/<%=tNo %>"  class="analysis">统计分析</a></li>
	                <li><a select-link href="#/t_stuScore/<%=tNo %>">学生试卷</a></li>
	                <li><a select-link href="#/analysisExam/<%=tNo %>">考试分析</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/t_info/<%=tNo %>"  class="teacherInfo">个人信息</a></li>
	                <li><a select-link href="#/t_info/<%=tNo %>">基本信息</a></li>
	                <li><a select-link href="#/t_updatePwd/<%=tNo %>">修改密码</a></li>
	            </ul>
	            	                        
        	</div>
        	<div class="main fr" ng-view>

        	</div>
        </div>
    </div>
    
    <div id="footer">
    	<jsp:include page="footer.jsp" />
    </div>
<script src="libs/jquery/jquery-3.1.0.min.js" ></script>
<script src="libs/angular/angular.min.js"></script>
<script src="libs/angular-route/angular-route.min.js"></script>
<script src="teacherApp.js"></script>
<script src="main/index_default_ctrl.js" charset="utf-8"></script>
<script src="main/t_info_ctrl.js" charset="utf-8"></script>
<script src="main/updatePwd_ctrl.js" charset="utf-8"></script>
<script src="main/t_class_ctrl.js" charset="utf-8"></script>
<script src="main/t_stu_ctrl.js" charset="utf-8"></script>
<script src="main/addStu_ctrl.js" charset="utf-8"></script>
<script src="main/t_sub_ctrl.js" charset="utf-8"></script>
<script src="main/addPaper_ctrl.js" charset="utf-8"></script>
<script src="main/addExam_ctrl.js" charset="utf-8"></script>
<script src="main/a_eMess_ctrl.js" charset="utf-8"></script>
<script src="main/a_pMess_ctrl.js" charset="utf-8"></script>
<script src="main/t_stuScore_ctrl.js" charset="utf-8"></script>
<script src="main/analysisExam_ctrl.js" charset="utf-8"></script>
</body>
</html>