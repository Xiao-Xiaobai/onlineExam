package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class DeleteExamServlet
 */
@WebServlet("/DeleteExamServlet")
public class DeleteExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExamServlet() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		String mess;
		String sql, sql2;
		DataProcess data = new DataProcess();
		sql = "select eName from Exam where id = '"+ id +"'";
		String eName = data.getData(sql).get(0).get(0);
		sql = "delete from Exam where id = '" + id + "'";
		sql2 = "delete from Paper where eName = '"+ eName + "'";
		if(data.update(sql) && data.update(sql2)){
			mess = "success";
		} else{
			mess = "error";
		}
		
			
		//��������
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
