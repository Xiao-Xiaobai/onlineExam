package com.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class FindStuSubServlet
 */
@WebServlet("/FindStuSubServlet")
public class FindStuSubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindStuSubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
		String sNo = request.getParameter("sNo").replaceAll(" ", "");
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		
		if(sNo != "" && sNo != null){
			sql = "select * from Stu_Sub where sNo = '" + sNo + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "该学生未加入任何课程";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				String sql2 = "select sName from Student where sNo = '" + sNo + "'";
				String sName = dataProcess.getData(sql2).get(0).get(0);
				Vector<String> oneRow;
				String[] stuMess = new String[rows.size()];
				for(int i = 0; i < rows.size(); i++){
					oneRow = rows.get(i);
					stuMess[i] = "{ \"sno\": \" " + oneRow.get(0) + " \","
							+ "\"sName\": \" " + sName + " \","
							+ "\"subName\": \" " + oneRow.get(1) + " \","
							+ "\"status\": \" " + oneRow.get(2) + " \","
							+ "\"grade\": \" " + oneRow.get(3) + " \""						
							+ "}"; 
				}
				String jsonStr = Arrays.toString(stuMess);
				response.getWriter().print(jsonStr);
			}
		}
	}

}
