<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>header</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="shortcut icon" href="images/kao.jpg">
</head>
<body>
    <div class="header">
        <div class="inner_center">
            <div class="header_img">
                <img src="images/header_img.png" alt="在线考试系统">
            </div>
            <div class="time">
                <span class='fl'>
                <%  
                	String userName = null;
                	if(session.getAttribute("userName") != null){
                		userName = session.getAttribute("userName").toString();
                	}
                	if(userName == null){
                		userName = "";
                		out.print(userName);               
                	} else{
                		out.print(userName + ", ");
                	}
                %>
                </span><span class='fl'>你好！欢迎进入校园在线考试系统</span>
                <a href="ExitServlet" class="fl exit" id="ext">退出</a>
                <span id="date" class="fr"></span>
            </div>
        </div>

    </div>
<script>
	//var user = document.getElementById("user");
	var ext = document.getElementById("ext");
	var username = "<%=userName%>";
	if(username != ""){
		ext.style.display = "block";
	} else{
		ext.style.display = "none";
	}
	

	function date() {
	    var date = new Date().toLocaleString();
	    document.getElementById("date").innerHTML = "今天是 " + date;
	}
	date();
	setInterval(date,1000);
</script>
</body>
</html>