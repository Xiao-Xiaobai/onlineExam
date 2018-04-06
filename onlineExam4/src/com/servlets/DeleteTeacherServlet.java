package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class DeleteTeacherServlet
 */
@WebServlet("/DeleteTeacherServlet")
public class DeleteTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTeacherServlet() {
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
		String tNo = request.getParameter("tno");
		String subName = request.getParameter("subName");
		String id = request.getParameter("id");
		
		String mess = null;
		String sql;
		DataProcess data = new DataProcess();
		
		if(subName == null){
			if(id == null){
				sql = "delete from Teacher where tNo = '" + tNo + "'";
				String sql2 = "delete from Tea_Sub where tNo = '" + tNo + "'";
				String sql3 = "delete from Teacher_Class where tNo = '" + tNo + "'";
				if(data.update(sql) && data.update(sql2) && data.update(sql3)){
					mess = "success";
				} else{
					mess = "error";
				}
			}else{
				sql = "delete from Teacher_Class where id = '" + id + "'";
				if(data.update(sql)){
					mess = "success";
				} else{
					mess = "error";
				}
			}
		}else{			
			sql = "delete from Tea_Sub where tNo = '" + tNo + "' and subName = '" + subName + "'";				
			if(data.update(sql)){
				mess = "success";
			}else{
				mess = "error";
			}
		}
		
		//·¢ËÍÊý¾Ý
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
