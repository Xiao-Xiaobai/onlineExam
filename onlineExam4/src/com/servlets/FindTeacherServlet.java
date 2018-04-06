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
 * Servlet implementation class FindTeacherServlet
 */
@WebServlet("/FindTeacherServlet")
public class FindTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTeacherServlet() {
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
		String tName = request.getParameter("tName");
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		if(tNo != "" && tNo != null){
			sql = "select * from Teacher where tNo = '" + tNo + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在该教师";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				Vector<String> oneTea;
				String[] teaMess = new String[rows.size()];
				for(int i = 0; i < rows.size(); i++){
					oneTea = rows.get(i);
					teaMess[i] = "{ \"tno\": \"" + oneTea.get(0).replaceAll(" ", "") + "\","
							+ "\"password\": \"" + oneTea.get(1).replaceAll(" ", "") + "\","
							+ "\"name\": \"" + oneTea.get(2).replaceAll(" ", "") + "\","
							+ "\"sex\": \"" + oneTea.get(3).replaceAll(" ", "") + "\""							
							+ "}"; 
				}
				String jsonStr = Arrays.toString(teaMess);
				response.getWriter().print(jsonStr);
			}
		}
		if(tName != "" && tName != null){
			sql = "select * from Teacher where tName = '" + tName + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在该教师";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				Vector<String> oneTea;
				String[] teaMess = new String[rows.size()];
				for(int i = 0; i < rows.size(); i++){
					oneTea = rows.get(i);
					teaMess[i] = "{ \"tno\": \"" + oneTea.get(0).replaceAll(" ", "") + "\","
							+ "\"password\": \"" + oneTea.get(1).replaceAll(" ", "") + "\","
							+ "\"name\": \"" + oneTea.get(2).replaceAll(" ", "") + "\","
							+ "\"sex\": \"" + oneTea.get(3).replaceAll(" ", "") + "\""							
							+ "}"; 
				}
				String jsonStr = Arrays.toString(teaMess);
				response.getWriter().print(jsonStr);
			}
		}
	}

}
