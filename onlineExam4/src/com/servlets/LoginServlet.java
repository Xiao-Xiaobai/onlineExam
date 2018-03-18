package com.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//接收数据
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String userId = request.getParameter("userId");
		String mess;
		//验证数据 处理数据
		//System.out.println(user);
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		HttpSession session = request.getSession();
		if(user==null || user.equals("")){
			mess = "请输入用户名！";
			//response.getWriter().print(mess);
		} 
		else if(pwd==null || pwd.equals("")){
			mess = "请输入密码！";
			//response.getWriter().print(mess);
		}
		else if(userId==null || userId.equals("")){
			mess = "请选择身份!";
			//response.getWriter().print(mess);
		}	
		else{ 
			if(userId.equals("admin")){
		
				if(user.equals("admin") && pwd.equals("admin")){
					session.setAttribute("user", user);
					session.setAttribute("userId", userId);
					session.setAttribute("userName", "admin");
					mess = "success";
				} else{
					mess = "用户名或密码不正确！";
				}
			}
			else if(userId.equals("student")){
				sql = "select * from Student where sNo='"+user+"' and sPassword='"+pwd+"' ";
				rows = dataProcess.getData(sql);
				if(rows.size()>0){
					String userName = rows.get(0).get(2);
					session.setAttribute("user", user);
					session.setAttribute("userId", userId);
					session.setAttribute("userName", userName);
					mess = "success";
				} else{
					mess = "用户名或密码不正确！";
				}
			}
			else if(userId.equals("teacher")){
				sql = "select * from Teacher where tNo='"+user+"' and tPassword='"+pwd+"' ";
				rows = dataProcess.getData(sql);
				if(rows.size()>0){
					String userName = rows.get(0).get(2);
					//System.out.println(userName);
					session.setAttribute("user", user);
					session.setAttribute("userId", userId);
					session.setAttribute("userName", userName);
					mess = "success";
				} else{
					mess = "用户名或密码不正确！";
				}
			}
			else{
				mess = "登录失败";
			}	
		}
		//发送数据
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
