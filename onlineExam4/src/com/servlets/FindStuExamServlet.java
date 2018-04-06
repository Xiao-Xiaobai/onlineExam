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
 * Servlet implementation class FindStuExamServlet
 */
@WebServlet("/FindStuExamServlet")
public class FindStuExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindStuExamServlet() {
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
		String sNo = request.getParameter("sNo");
		String flag = request.getParameter("flag");
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		
		if(sNo != "" && sNo != null){
			if(flag.equals("waitTest")){
				sql = "select * from Stu_Exam where sNo = '" + sNo + "' and eStatus = '待考'";
				rows = dataProcess.getData(sql);
				if(rows.size() == 0){
					mess = "暂无待参加的考试";
					String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
					response.getWriter().print(jsonStr);
					return;
				}else{
					String sql2 = "select sName from Student where sNo = '" + sNo + "'";
					String sName = dataProcess.getData(sql2).get(0).get(0).replaceAll(" ", "");
					Vector<String> oneRow;
					String[] waitTests = new String[rows.size()];
					for(int i = 0; i < rows.size(); i++){
						oneRow = rows.get(i);					
						waitTests[i] = "{ \"id\": \"" + oneRow.get(0) + "\","
								+ "\"sNo\": \"" + oneRow.get(1).trim() + "\","
								+ "\"sName\": \"" + sName + " \","
								+ "\"eName\": \"" + oneRow.get(2).trim() + " \","
								+ "\"eSub\": \"" + oneRow.get(3).trim() + "\","
								+ "\"eStatus\": \"" + oneRow.get(4).trim() + "\","
								+ "\"grade\": \"" + oneRow.get(5) + "\""						
								+ "}"; 
					}
					String jsonStr = Arrays.toString(waitTests);
					response.getWriter().print(jsonStr);
				}
			}else if(flag.equals("hisTest")){
				sql = "select * from Stu_Exam where sNo = '" + sNo + "' and eStatus = '已考'";
				rows = dataProcess.getData(sql);
				if(rows.size() == 0){
					mess = "暂无已参加的考试";
					String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
					response.getWriter().print(jsonStr);
					return;
				}else{
					String sql2 = "select sName from Student where sNo = '" + sNo + "'";
					String sName = dataProcess.getData(sql2).get(0).get(0).replaceAll(" ", "");
					Vector<String> oneRow;
					String[] hisTests = new String[rows.size()];
					for(int i = 0; i < rows.size(); i++){
						oneRow = rows.get(i);					
						hisTests[i] = "{ \"id\": \"" + oneRow.get(0) + "\","
								+ "\"sNo\": \"" + oneRow.get(1).trim() + "\","
								+ "\"sName\": \"" + sName + " \","
								+ "\"eName\": \"" + oneRow.get(2).trim() + " \","
								+ "\"eSub\": \"" + oneRow.get(3).trim() + "\","
								+ "\"eStatus\": \"" + oneRow.get(4).trim() + "\","
								+ "\"grade\": \"" + oneRow.get(5) + "\""						
								+ "}"; 
					}
					String jsonStr = Arrays.toString(hisTests);
					response.getWriter().print(jsonStr);
				}
			}else if(flag.equals("studentQuery")){
				sql = "select * from Stu_Exam where sNo = '"+ sNo +"'";
				rows = dataProcess.getData(sql);
				if(rows.size() == 0){
					mess = "暂无考试记录";
					String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
					response.getWriter().print(jsonStr);
					return;
				}else{					
					Vector<String> oneRow;
					String[] tests = new String[rows.size()];
					for(int i = 0; i < rows.size(); i++){
						oneRow = rows.get(i);					
						tests[i] = "{ \"id\": \"" + oneRow.get(0) + "\","
								+ "\"eName\": \"" + oneRow.get(2).replaceAll(" ", "") + " \","
								+ "\"eSub\": \"" + oneRow.get(3).replaceAll(" ", "") + "\","
								+ "\"eStatus\": \"" + oneRow.get(4).replaceAll(" ", "") + "\","
								+ "\"grade\": \"" + oneRow.get(5) + "\""						
								+ "}"; 
					}
					String jsonStr = Arrays.toString(tests);
					response.getWriter().print(jsonStr);
				}
			}
		}else{
			mess = "error";
		}
	}

}
