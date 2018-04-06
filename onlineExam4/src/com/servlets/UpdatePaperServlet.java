package com.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class UpdatePaperServlet
 */
@WebServlet("/UpdatePaperServlet")
public class UpdatePaperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePaperServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request,response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String qType = request.getParameter("qType");
		float qScore = Float.parseFloat(request.getParameter("qScore"));
		String qTitle = request.getParameter("qTitle");
		String qSelection = request.getParameter("qSelection");
		String qAnswer = request.getParameter("qAnswer");
				
		String mess = ""; //要发送的信息
		String sql;
		DataProcess dataProcess = new DataProcess();
		
		sql = "update Paper set qNo = '"+ qNo +"', qType = '"+ qType +"', qScore = '"+ qScore +"', qTitle = '"+ qTitle 
				+"', qSelection = '"+ qSelection +"', qAnswer = '"+ qAnswer +"' where id = '"+ id +"'";
		if(dataProcess.update(sql)){
			mess = "success";
		}else{
			mess = "error";
		}
			
		//发送数据
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
