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
 * Servlet implementation class FindTeaSubServlet
 */
@WebServlet("/FindTeaSubServlet")
public class FindTeaSubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTeaSubServlet() {
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
		String tNo = request.getParameter("tNo");
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		
		if(tNo != "" && tNo != null){
			sql = "select * from Tea_Sub where tNo = '" + tNo + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "该教师未教授任何课程";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				String sql2 = "select tName from Teacher where tNo = '" + tNo + "'";
				String tName = dataProcess.getData(sql2).get(0).get(0).replaceAll(" ", "");
				Vector<String> oneRow;
				String[] teaMess = new String[rows.size()];
				for(int i = 0; i < rows.size(); i++){
					oneRow = rows.get(i);
					String subName = oneRow.get(2).replaceAll(" ", "");
					sql = "select subNo from Subject where subName = '"+ subName +"'";
					String subNo = dataProcess.getData(sql).get(0).get(0).replaceAll(" ", "");
					teaMess[i] = "{ \"id\": \"" + oneRow.get(0) + "\","
							+ "\"tno\": \"" + oneRow.get(1).replaceAll(" ", "") + "\","
							+ "\"tName\": \"" + tName + " \","
							+ "\"subNo\": \"" + subNo + " \","
							+ "\"subName\": \"" + subName + "\""																		
							+ "}"; 
				}
				String jsonStr = Arrays.toString(teaMess);
				response.getWriter().print(jsonStr);
			}
		}else{
			mess = "error";
		}
	}

}
