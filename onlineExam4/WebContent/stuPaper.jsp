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
    	String sNo, sName, tNo;
    	if(request.getParameter("tNo") != null){
    		request.setCharacterEncoding("utf-8");
    		tNo = request.getParameter("tNo");
    		sNo = request.getParameter("sNo");
    		sName = "";
    	}else{
    		if(session.getAttribute("user") == null){
    			response.sendRedirect("login.jsp");
        		return;
    		}else{
    			sNo = session.getAttribute("user").toString();
    			sName = session.getAttribute("userName").toString();
    		}
    		tNo = "";
    	}
    	String eId = request.getParameter("eId");
    %>
    <div class="testing" ng-app="stuPaperApp">
        <div class="inner_center" ng-controller="stuPaperController">
        	<nav ng-show="tNo!=''">
    			<a href="t_index.jsp#/teacher">首页</a>
    			<span> > </span>
    			<a href="t_index.jsp#/t_stuScore/{{tNo}}">学生成绩</a>
    			<span> > </span>
    			<span>学生试卷</span>
			</nav>
			<nav ng-show="tNo==''">
    			<a href="s_index.jsp#/student">首页</a>
    			<span> > </span>
    			<a href="s_index.jsp#/s_test/{{sNo}}">我的考试</a>
    			<span> > </span>
    			<a href="s_index.jsp#/s_hisTest/{{sNo}}">已参加的考试</a>
    			<span> > </span>
    			<span>我的试卷</span>
			</nav>
        	<div class="testing_left fl">
				<ul>
					<li>考试信息</li>
					<li><span>学号：</span><span><%=sNo %></span></li>
					<li><span>姓名：</span><span ng-show="tNo==''"><%=sName %></span><span ng-show="tNo!=''">{{sName}}</span></li>
					<input type="hidden" value="<%=eId %>" ng-init="eId='<%=eId %>'" >
					<input type="hidden" value="<%=tNo %>" ng-init="tNo='<%=tNo %>'" >
					<input type="hidden" value="<%=sNo %>" ng-init="sNo='<%=sNo %>'" >
					<li><span>总时长：</span><span>{{totalTime}}分钟</span></li>		
					<li><span>用时：</span><span>{{usedTime}}分钟</span>
					<li><span>得分：</span><span>{{myScore}}分</span>
					
					<li><button ng-click="returnPage(<%=sNo %>)" ng-show="tNo==''">返回考试列表</button></li>
				</ul>
        	</div>
        	<div class="testing_right fr" ng-show="eId">
				<div class="paper">
					<div class="paperTitle">{{eName}}</div>
					<div class="paperMes"><div>科目：{{eSub}}</div> <span>考试时间：{{totalTime}}分钟</span> <span>总分：{{totalScore}}分</span> <span class="myScore">得分：{{myScore}}分</span></div>
					<div ng-show="mess" class="message">{{mess}}</div>
					<div class="question" ng-repeat="question in questions track by $index" ng-init="outerIndex=$index">			
						<div class="quesTitle"><span>{{$index+1}}</span><span>{{question.qType}}</span> <span>{{question.qTitle}}</span> <span>({{question.qScore}}分)</span></div>
						<div class="queSelection" ng-if="question.qType=='单选题'">
							<div ng-repeat="select in question.qSelection track by $index">
								<input type="radio"  value="{{select.selectNo}}" id="{{select.selectNo}}" ng-model="myAnswer[outerIndex]" ng-disabled="submited"> <span>{{select.selectNo}}.</span> <label for="{{select.selectNo}}">{{select.selection}}</label>
							</div>
							<div class="quesAnswer"><span class="rightAnswer">正确答案：{{question.qAnswer}}</span><span class="myAnswer">我的答案：{{myAnswer[$index]}}</span>
								<span ng-if="right[$index]" class="judgeIcon right">√</span><span ng-if="wrong[$index]" class="judgeIcon">×</span>
							</div>
						</div>
						<div class="queSelection" ng-if="question.qType=='多选题'">
							<div ng-repeat="select in question.qSelection track by $index">
								<input type="checkbox" id="{{select.selectNo}}." ng-model="myAnswer[outerIndex][$index]"  ng-disabled="submited"> <span>{{select.selectNo}}.</span> <label for="{{select.selectNo}}.">{{select.selection}}</label>
							</div>
							<div class="quesAnswer"><span class="rightAnswer">正确答案：{{question.qAnswer}}</span><span class="myAnswer">我的答案：{{myAnswer[$index]}}</span>
								<span ng-if="right[$index]" class="judgeIcon right">√</span><span ng-if="wrong[$index]" class="judgeIcon">×</span>
							</div>
						</div>
						<div class="queJudge" ng-if="question.qType=='判断题'">
							<div><label for="T">正确 </label><input type="radio" value="T" id="T" ng-model="myAnswer[outerIndex]"  ng-disabled="submited"></div>
							<div><label for="F">错误</label> <input type="radio" value="F" id="F" ng-model="myAnswer[outerIndex]"  ng-disabled="submited"></div>
							<div class="quesAnswer"><span class="rightAnswer">正确答案：{{question.qAnswer}}</span><span class="myAnswer">我的答案：{{myAnswer[$index]}}</span>
								<span ng-if="right[$index]" class="judgeIcon right">√</span><span ng-if="wrong[$index]" class="judgeIcon">×</span>
							</div>
						</div>			
					</div>
				</div>
			</div>
        
        </div>
    </div>
    
    <div id="footer">
    	<jsp:include page="footer.jsp" />
    </div>
<script src="libs/jquery/jquery-3.1.0.min.js" ></script>
<script src="libs/angular/angular.min.js"></script>
<script src="libs/angular-route/angular-route.min.js"></script>
<script src="stuPaperApp.js"></script>

</body>
</html>