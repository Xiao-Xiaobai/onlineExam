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
 * Servlet implementation class FindExamMessServlet
 */
@WebServlet("/FindExamMessServlet")
public class FindExamMessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindExamMessServlet() {
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
		String eName = request.getParameter("eName");
		String eSub = request.getParameter("eSub");
		String flag = request.getParameter("flag");
		String tNo = request.getParameter("tNo");
		
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		if(flag == null){
			if(tNo == null || tNo.equals("")){
				sql = "select * from Exam";
				
			}else{
				sql = "select Exam.* from Exam,Tea_Sub where Tea_Sub.tNo = '"+ tNo +"' and Tea_Sub.subName = Exam.eSub";
			}
			rows = dataProcess.getData(sql);
			Vector<String> oneExam;
			String[] examMess = new String[rows.size()];
			for(int i = 0; i < rows.size(); i++){
				oneExam = rows.get(i);				
				examMess[i] = "{ \"id\": \"" + oneExam.get(0) + "\","
						+ "\"eName\": \"" + oneExam.get(1).replaceAll(" ", "") + "\","
						+ "\"eSub\": \"" + oneExam.get(2).replaceAll(" ", "") + "\","
						+ "\"totalTime\": \"" + oneExam.get(3) + "\","
						+ "\"totalScore\": \"" + oneExam.get(4) + "\","
						+ "\"creator\": \"" + oneExam.get(5).replaceAll(" ", "") + "\","
						+ "\"createTime\": \"" + oneExam.get(6).trim() + "\""
						+ "}"; 
			}
			String jsonStr = Arrays.toString(examMess);
			response.getWriter().print(jsonStr);
		}else if(flag.equals("findByEname")){			
			sql = "select * from Exam where eName like '%" + eName + "%'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在相关考试";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				Vector<String> oneExam;
				String[] examMess = new String[rows.size()];
				for(int i = 0; i < rows.size(); i++){
					oneExam = rows.get(i);				
					examMess[i] = "{ \"id\": \"" + oneExam.get(0) + "\","
							+ "\"eName\": \"" + oneExam.get(1).replaceAll(" ", "") + "\","
							+ "\"eSub\": \"" + oneExam.get(2).replaceAll(" ", "") + "\","
							+ "\"totalTime\": \"" + oneExam.get(3) + "\","
							+ "\"totalScore\": \"" + oneExam.get(4) + "\","
							+ "\"creator\": \"" + oneExam.get(5).replaceAll(" ", "") + "\","
							+ "\"createTime\": \"" + oneExam.get(6).trim() + "\""
							+ "}"; 
				}
				String jsonStr = Arrays.toString(examMess);
				response.getWriter().print(jsonStr);
			}			
		}else if(flag.equals("findBySubName")){			
			sql = "select * from Exam where eSub like '%" + eSub + "%'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在相关考试";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				Vector<String> oneExam;
				String[] examMess = new String[rows.size()];
				for(int i = 0; i < rows.size(); i++){
					oneExam = rows.get(i);				
					examMess[i] = "{ \"id\": \"" + oneExam.get(0) + "\","
							+ "\"eName\": \"" + oneExam.get(1).replaceAll(" ", "") + "\","
							+ "\"eSub\": \"" + oneExam.get(2).replaceAll(" ", "") + "\","
							+ "\"totalTime\": \"" + oneExam.get(3) + "\","
							+ "\"totalScore\": \"" + oneExam.get(4) + "\","
							+ "\"creator\": \"" + oneExam.get(5).replaceAll(" ", "") + "\","
							+ "\"createTime\": \"" + oneExam.get(6).trim() + "\""
							+ "}"; 
				}
				String jsonStr = Arrays.toString(examMess);
				response.getWriter().print(jsonStr);
			}
		}


	}

}
