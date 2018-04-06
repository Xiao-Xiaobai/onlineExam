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
 * Servlet implementation class UpdateExamServlet
 */
@WebServlet("/UpdateExamServlet")
public class UpdateExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateExamServlet() {
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
		String eName = request.getParameter("eName");		
		String eSub = request.getParameter("eSub");
		String totalTime = request.getParameter("totalTime");
		String totalScore = request.getParameter("totalScore");
				
		String mess; //要发送的信息
		String sql;
		DataProcess dataProcess = new DataProcess();
		
		if(eName == null || eName.equals("")){
			mess = "请输入考试名称";
		}else if(eSub == null || eSub.equals("")){
			mess = "请输入考试科目";
		}else if(totalTime == null || totalTime.equals("")){
			mess = "请输入考试时长";
		}else if(totalScore == null || totalScore.equals("")){
			mess = "请输入卷面总分";
		}else {
			sql = "update Exam set eName = '"+ eName +"', eSub = '"+ eSub +"', totalTime = '"+ totalTime 
					+ "', totalScore = '"+ totalScore + "' where id = '"+ id + "'";
			if(dataProcess.update(sql)){
				mess = "success";
			}else{
				mess = "error";
			}
		}
			
		//发送数据
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
