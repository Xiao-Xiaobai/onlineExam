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
 * Servlet implementation class FindQuestionMessServlet
 */
@WebServlet("/FindQuestionMessServlet")
public class FindQuestionMessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindQuestionMessServlet() {
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
		String flag = request.getParameter("flag");
		String findEname = request.getParameter("eName");
		String findQtitle = request.getParameter("qTitle");
		
		String questionsMess = ""; //返回的题目信息
		String[] questions = null; //保存题目信息的数组
		String qSelection = ""; //返回的选项信息
		String mess = ""; //返回的提示信息
		
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		
		if(flag == null){
			sql = "select * from Paper";
			rows = dataProcess.getData(sql);
		}else if(flag.equals("findByEname")){
			sql = "select * from Paper where eName like '%"+ findEname + "%'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在该考试的题目";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
				return;
			}
		}else if(flag.equals("findByQtitle")){
			sql = "select * from Paper where qTitle like '%"+ findQtitle + "%'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在该题目";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
				return;
			}
		}else{
			sql = "select * from Paper";
			rows = dataProcess.getData(sql);
		}
		questions = new String[rows.size()];
		for(int i = 0; i < rows.size(); i++){
			Vector<String> row = rows.get(i);
			String strSelection = row.get(6).trim();
			if(strSelection != null && !strSelection.equals("")){
				String[] splitStrSelection = strSelection.split(";");					
				String[] selects = new String[splitStrSelection.length];
				for(int j = 0; j < splitStrSelection.length; j++){
					String[] splitOneSelection = splitStrSelection[j].split("\\.");
					String selectNo = splitOneSelection[0];
					String selection = splitOneSelection[1];
					
					selects[j] = "{ \"selectNo\": \""+ selectNo +"\","
							+ "\"selection\": \""+ selection +"\""
							+"}";
				}
				qSelection = Arrays.toString(selects);
				questions[i] = "{ \"id\": \""+ row.get(0) +"\","						
						+ "\"eName\": \"" + row.get(1).trim() + "\","
						+ "\"qNo\": \"" + row.get(2) + "\","
						+ "\"qType\": \"" + row.get(3).trim() + "\","
						+ "\"qScore\": \"" + row.get(4) + "\","
						+ "\"qTitle\": \"" + row.get(5).trim() + "\","
						+ "\"qSelection\": " + qSelection + ","
						+ "\"qAnswer\": \"" + row.get(7).trim() + "\""
						+ "}";
			}else{
				questions[i] = "{ \"id\": \""+ row.get(0) +"\","						
						+ "\"eName\": \"" + row.get(1).trim() + "\","
						+ "\"qNo\": \"" + row.get(2) + "\","
						+ "\"qType\": \"" + row.get(3).trim() + "\","
						+ "\"qScore\": \"" + row.get(4) + "\","
						+ "\"qTitle\": \"" + row.get(5).trim() + "\","
						+ "\"qAnswer\": \"" + row.get(7).trim() + "\""
						+ "}";
			}								
		}
		questionsMess = Arrays.toString(questions);
		response.getWriter().print(questionsMess);
		
			
		
		
		
	}

}
