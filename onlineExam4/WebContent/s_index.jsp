<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>校园在线考试系统-请登录</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="shortcut icon" href="images/kao.jpg">
    <script src="js/jquery/jquery-3.1.0.min.js"></script>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />      
    </div>
    
    <div class="content">
        <div class="inner_center">
            我是学生
        </div>
    </div>
    
    <div id="footer">
    	<jsp:include page="footer.jsp" />
    </div>
<script>
	/*
	$(function(){
		$("#sub").click(function(){
			var user = $("#user").val();
			var pwd = $("#pwd").val();
			var message = $("#message");
			var userId = $("input:radio:checked").val();
			$.ajax({
				type: 'post',
				url: "LoginServlet",
				//dataType: "json",
				data:{
					user: user,
					pwd: pwd,
					userId: userId
				},
				async: true,
				success: function(data){
					if(data != null || data != ""){
						message.text(data);
					} else{
						
					}
				}
			});
		});
	});*/
</script>
</body>
</html>