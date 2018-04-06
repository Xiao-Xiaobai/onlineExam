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
    	String sNo, sName;
    	if(session.getAttribute("user") == null){
    		response.sendRedirect("login.jsp");
        	return;
    	}else{
    		sNo = session.getAttribute("user").toString();
    		sName = session.getAttribute("userName").toString();
    	}
    	String eId = request.getParameter("eId");
    %>
    <div class="testing" ng-app="testingApp">
        <div class="inner_center" ng-controller="testingController">
        	<div class="testing_left fl">
				<ul>
					<li>考试信息</li>
					<li><span>学号：</span><span><%=sNo %></span></li>
					<li><span>姓名：</span><span><%=sName %></span></li>
					<li ng-show="started"><span>总时长：</span><span>{{totalTime}}分钟</span></li>
					<li ng-show="started && !submited"><span>离考试结束还剩：</span><span class="leftTime">{{leftTime}}</span></li>
					<li ng-show="submited"><span>用时：</span><span>{{usedTime}}分钟</span>
					<li ng-show="submited"><span>得分：</span><span>{{myScore}}分</span>
					<li ng-show="!submited"><span>注意：</span><span>确定开始后开始计时，计时结束后将停止作答，系统自动提交答案，考试期间不得退出！</span></li>
					<li ng-hide="started"><button ng-click="start(<%=sNo %>, <%=eId %>)">确定开始</button></li>
					<li ng-show="submited"><button ng-click="returnPage(<%=sNo %>)">返回考试列表</button></li>
				</ul>
        	</div>
        	<div class="testing_right fr" ng-if="started">
				<div class="paper">
					<div class="paperTitle">{{eName}}</div>
					<div class="paperMes"><div>科目：{{eSub}}</div> <span>考试时间：{{totalTime}}分钟</span> <span>总分：{{totalScore}}分</span> <span class="myScore" ng-if="submited">得分：{{myScore}}分</span></div>
					<div ng-show="mess" class="message">{{mess}}</div>
					<div class="question" ng-repeat="question in questions track by $index" ng-init="outerIndex=$index">			
						<div class="quesTitle"><span>{{$index+1}}</span><span>{{question.qType}}</span> <span>{{question.qTitle}}</span> <span>({{question.qScore}}分)</span></div>
						<div class="queSelection" ng-if="question.qType=='单选题'">
							<div ng-repeat="select in question.qSelection track by $index">
								<input type="radio"  value="{{select.selectNo}}" ng-model="myAnswer[outerIndex]" ng-disabled="submited"> <span>{{select.selectNo}}.</span> <span>{{select.selection}}</span>
							</div>
							<div class="quesAnswer" ng-if="submited"><span class="rightAnswer">正确答案：{{question.qAnswer}}</span><span class="myAnswer">我的答案：{{myAnswer[$index]}}</span>
								<span ng-if="right[$index]" class="judgeIcon right">√</span><span ng-if="wrong[$index]" class="judgeIcon">×</span>
							</div>
						</div>
						<div class="queSelection" ng-if="question.qType=='多选题'">
							<div ng-repeat="select in question.qSelection track by $index">
								<input type="checkbox" ng-model="myAnswer[outerIndex][$index]"  ng-disabled="submited"> <span>{{select.selectNo}}.</span> <span>{{select.selection}}</span>
							</div>
							<div class="quesAnswer" ng-if="submited"><span class="rightAnswer">正确答案：{{question.qAnswer}}</span><span class="myAnswer">我的答案：{{myAnswer[$index]}}</span>
								<span ng-if="right[$index]" class="judgeIcon right">√</span><span ng-if="wrong[$index]" class="judgeIcon">×</span>
							</div>
						</div>
						<div class="queJudge" ng-if="question.qType=='判断题'">
							<div><span>正确 </span><input type="radio" value="T" ng-model="myAnswer[outerIndex]"  ng-disabled="submited"></div>
							<div><span>错误</span> <input type="radio" value="F" ng-model="myAnswer[outerIndex]"  ng-disabled="submited"></div>
							<div class="quesAnswer" ng-if="submited"><span class="rightAnswer">正确答案：{{question.qAnswer}}</span><span class="myAnswer">我的答案：{{myAnswer[$index]}}</span>
								<span ng-if="right[$index]" class="judgeIcon right">√</span><span ng-if="wrong[$index]" class="judgeIcon">×</span>
							</div>
						</div>			
					</div>
					<div ng-hide="submited || mess"><button ng-click="submit()" class="submitBtn">提交</button></div>
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
<script src="testingApp.js"></script>

</body>
</html>