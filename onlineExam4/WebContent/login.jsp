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
    <script src="libs/jquery/jquery-3.1.0.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />      
    
    <div class="content">
        <div class="inner_center">
            <div class="login">
                <p class="title">登录</p>
                
                    <span>用户名：</span><input type="text" class="txt"  id="user"><br>
                    <span>密码：</span><input type="password" class="txt"  id="pwd"><br>
                    <span>身份：</span>
                    <input type="radio" name="id" value="admin">管理员
                    <input type="radio" name="id" value="student">学生
                    <input type="radio" name="id" value="teacher">教师
                    <br>
                    <p class="mess" id="message"></p>
                    <input type="submit" value="登录" class="btn" id="sub">
                
            </div>
            <p> 测试账号：管理员:admin/admin 学生:201412201401041/123456 教师：20142222/111</p>
        </div>
    </div>
    
    <jsp:include page="footer.jsp" />
<script>
	$(function(){
		$("#sub").click(function(){
			var user = $("#user").val();
			var pwd = $("#pwd").val();
			var message = $("#message");
			var userId = $("input:radio:checked").val();
			$.ajax({
				type: 'post',
				url: "LoginServlet",
				dataType: "json",
				data:{
					user: user,
					pwd: pwd,
					userId: userId
				},
				async: true,
				success: function(data){
					if(data.mess == "success"){
						if(userId == "admin"){
							$(location).attr("href","a_index.jsp");
						} else if(userId == "student"){
							$(location).attr("href","s_index.jsp");
						} else{
							$(location).attr("href","t_index.jsp");
						}
						
					} else{
						message.text(data.mess);
					}
				}
			});
		});
	});
</script>
</body>
</html>