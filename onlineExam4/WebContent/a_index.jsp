<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html charset=utf-8">
    <title>校园在线考试系统-请登录</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="shortcut icon" href="images/kao.jpg">
    
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />      
    </div>
    
    <div class="a_content" ng-app="app">
        <div class="inner_center">
            <div class="left_side fl">
	            <ul>
	                <li><a select-link href="#/a_sMess" class="stu">学生管理</a></li>
	                <li><a href="#/a_sMess">查看学生</a></li>
	                <li><a href="#/addStudent">新增学生</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/a_tMess"  class="tea">教师管理</a></li>
	                <li><a href="#/a_tMess">查看教师</a></li>
	                <li><a href="#">新增教师</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/a_qMess"  class="paper">试卷管理</a></li>
	                <li><a href="#">新增试卷</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/a_aMess"  class="analy">统计分析</a></li>
	                <li><a href="#">试卷分析</a></li>
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
<script src="app.js" charset="utf-8"></script>
<script src="main/index_default_ctrl.js" charset="utf-8"></script>
<script src="main/a_sMess_ctrl.js" charset="utf-8"></script>
<script src="main/a_tMess_ctrl.js" charset="utf-8"></script>
<script src="main/hisTest_ctrl.js" charset="utf-8"></script>
<script src="main/waitTest_ctrl.js" charset="utf-8"></script>
<script src="main/addStu_ctrl.js" charset="utf-8"></script>
<script src="main/deleteStu_ctrl.js" charset="utf-8"></script>
<script src="main/updateStu_ctrl.js" charset="utf-8"></script>
<script src="main/stuSub_ctrl.js" charset="utf-8"></script>
</body>
</html>