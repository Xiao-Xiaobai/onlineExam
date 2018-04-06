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
 * Servlet implementation class UpdateSubjectServlet
 */
@WebServlet("/UpdateSubjectServlet")
public class UpdateSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSubjectServlet() {
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
		String subNo = request.getParameter("subNo");		
		String subName = request.getParameter("subName");
		String tNo = request.getParameter("tNo");
		
		
		String mess; //要发送的信息
		String sql;
		DataProcess dataProcess = new DataProcess();
		
		if(subName == null || subName.equals("")){
			mess = "请输入课程名";
		}else{
			String upTno;
			if(tNo != null && !tNo.equals("")){
				upTno = tNo;
			}else{
				upTno = "";
			}
			sql = "update Subject set subName = '" + subName + "', teacherNo = '" + upTno + "' where subNo = '"+ subNo +"'";
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
