package com.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class AddPaperServlet
 */
@WebServlet("/AddPaperServlet")
public class AddPaperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPaperServlet() {
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
		String eName = request.getParameter("eName");		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String qType = request.getParameter("qType");
		float qScore = Float.parseFloat(request.getParameter("qScore"));
		String qTitle = request.getParameter("qTitle").replaceAll("'", "''");
		String qSelection = request.getParameter("qSelection").replaceAll("'", "''");
		String qAnswer = request.getParameter("qAnswer");
				
		String mess = null; //要发送的信息
		String sql;
		DataProcess dataProcess = new DataProcess();
	    
		sql = "select id from Paper where eName = '"+ eName +"' and qNo = '"+ qNo +"'";
		if(dataProcess.getData(sql).size() > 0){
			mess = "该试卷已存在同样题号的题目";
		}else{
			sql = "insert into Paper(eName, qNo, qType, qScore, qTitle, qSelection, qAnswer) values('"+ eName +"', '"+ qNo +"', '"+ qType 
					+"', '"+ qScore +"', '"+ qTitle +"', '"+ qSelection +"', '"+ qAnswer +"')";
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
