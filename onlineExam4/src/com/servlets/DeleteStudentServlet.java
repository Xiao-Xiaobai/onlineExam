package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class DeleteStudentServlet
 */
@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String sNo = request.getParameter("sno");
		String sub = request.getParameter("subName");
		String eId = request.getParameter("eId");
		String flag = request.getParameter("flag");
		
		String mess = null;
		String sql;
		DataProcess data = new DataProcess();
		if(flag.equals("deleteAll")){
			String sql1 = "delete from Student where sNo = '"+ sNo +"'";
			String sql2 = "delete from Stu_Sub where sNo = '"+ sNo +"'";
			String sql3 = "delete from Stu_Exam where sNo = '"+ sNo +"'";
			if(data.update(sql1) && data.update(sql2) && data.update(sql3)){
				mess = "success";				
			} else{
				mess = "error";
			}	
		} else if(flag.equals("deleteStuSub")){
			sql = "delete from Stu_Sub where sNo = '" + sNo + "' and subName = '" + sub + "'";
			String sql2 = "delete from Stu_Exam where sNo = '" + sNo + "' and eSub = '" + sub + "'";
			if(data.update(sql) && data.update(sql2)){
				mess = "success";
			} else{
				mess = "error";
			}
		} else if(flag.equals("deleteStuExam")){
			sql = "delete from Stu_Exam where id = '"+ eId +"'";
			if(data.update(sql)){
				mess = "success";
			} else{
				mess = "error";
			}
		}
			
		//·¢ËÍÊý¾Ý
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
