<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>校园在线考试系统-学生</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="shortcut icon" href="images/kao.jpg">
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />      
    </div>
    <%
    	String sNo = null;
    	sNo = session.getAttribute("user").toString();
    	if(sNo == null){
    		response.sendRedirect("login.jsp");
        	return;
    	}
    %>
    <div class="s_content" ng-app="studentApp">
        <div class="inner_center">
            <div class="left_side fl">
	            <ul>
	                <li><a select-link href="#/s_subMess/<%=sNo %>" class="studentSub">我的课程</a></li>
	                <li><a select-link href="#/s_subMess/<%=sNo %>">查看课程</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/s_test/<%=sNo %>"  class="studentExam">我的考试</a></li>
	                <li><a select-link href="#/s_waitTest/<%=sNo %>">待参加考试</a></li>
	                <li><a select-link href="#/s_hisTest/<%=sNo %>">已参加考试</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/s_info/<%=sNo %>"  class="studentInfo">个人信息</a></li>
	                <li><a select-link href="#/s_info/<%=sNo %>">基本信息</a></li>
	                <li><a select-link href="#/s_updatePwd/<%=sNo %>">修改密码</a></li>
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
<script src="studentApp.js"></script>
<script src="main/index_default_ctrl.js" charset="utf-8"></script>
<script src="main/s_subMess_ctrl.js" charset="utf-8"></script>
<script src="main/s_info_ctrl.js" charset="utf-8"></script>
<script src="main/updatePwd_ctrl.js" charset="utf-8"></script>
<script src="main/s_testlist_ctrl.js" charset="utf-8"></script>
<script src="main/s_waitTest_ctrl.js" charset="utf-8"></script>
<script src="main/s_hisTest_ctrl.js" charset="utf-8"></script>
</body>
</html>