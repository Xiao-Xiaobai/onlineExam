<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html charset=utf-8">
    <title>校园在线考试系统-管理员</title>
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
	                <li><a select-link href="#/a_sMess">查看学生</a></li>
	                <li><a select-link href="#/addStudent">新增学生</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/a_tMess"  class="tea">教师管理</a></li>
	                <li><a select-link href="#/a_tMess">查看教师</a></li>
	                <li><a select-link href="#/addTeacher">新增教师</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/a_subMess"  class="sub">课程管理</a></li>
	                <li><a select-link href="#/a_subMess">课程列表</a></li>
	                <li><a select-link href="#/addSubject">新增课程</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/a_eMess"  class="exam">考试管理</a></li>
	                <li><a select-link href="#/a_eMess">已有考试</a></li>
	                <li><a select-link href="#/addExam">新增考试</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/a_qMess"  class="paper">试题管理</a></li>
	                <li><a select-link href="#/a_qMess">试题列表</a></li>
	                <li><a select-link href="#/addPaper">新增试题</a></li>
	            </ul>
	            <ul>
	                <li><a select-link href="#/a_analysis"  class="analy">统计分析</a></li>
	                <li><a select-link href="#/a_analysis">考试分析</a></li>
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
<script src="main/a_subMess_ctrl.js" charset="utf-8"></script>
<script src="main/a_eMess_ctrl.js" charset="utf-8"></script>
<script src="main/hisTest_ctrl.js" charset="utf-8"></script>
<script src="main/waitTest_ctrl.js" charset="utf-8"></script>
<script src="main/addStu_ctrl.js" charset="utf-8"></script>
<script src="main/deleteStu_ctrl.js" charset="utf-8"></script>
<script src="main/updateStu_ctrl.js" charset="utf-8"></script>
<script src="main/updateTea_ctrl.js" charset="utf-8"></script>
<script src="main/stuSub_ctrl.js" charset="utf-8"></script>
<script src="main/teaSub_ctrl.js" charset="utf-8"></script>
<script src="main/teaClass_ctrl.js" charset="utf-8"></script>
<script src="main/addTea_ctrl.js" charset="utf-8"></script>
<script src="main/addSub_ctrl.js" charset="utf-8"></script>
<script src="main/addExam_ctrl.js" charset="utf-8"></script>
<script src="main/addPaper_ctrl.js" charset="utf-8"></script>
<script src="main/a_pMess_ctrl.js" charset="utf-8"></script>
<script src="main/a_qMess_ctrl.js" charset="utf-8"></script>
<script src="main/analysisExam_ctrl.js" charset="utf-8"></script>
</body>
</html>