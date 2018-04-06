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
 * Servlet implementation class FindPaperMessServlet
 */
@WebServlet("/FindPaperMessServlet")
public class FindPaperMessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPaperMessServlet() {
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
		String eId = request.getParameter("eId");
		String stuEid = request.getParameter("stuEid");
		String flag = request.getParameter("flag");
		
		String paperMess = ""; //要响应的数据
		String mess = ""; //没有查到试卷题目时的提示信息
		String paper = ""; //返回的题目信息
		String[] paperContent = null; //保存题目信息的数组
		String qSelection = ""; //返回的选项信息
		String grade="", stuAnswer="", usedTime="";
		String sql;
		DataProcess dataProcess = new DataProcess();
		
		if(stuEid != null){
			sql = "select eName,grade,stuAnswer,usedTime from Stu_Exam where id = '"+ stuEid +"'";
			String stuEname = dataProcess.getData(sql).get(0).get(0).trim();
			grade = dataProcess.getData(sql).get(0).get(1);
			stuAnswer = dataProcess.getData(sql).get(0).get(2).replaceAll(" ", "");
			usedTime = dataProcess.getData(sql).get(0).get(3);
			sql = "select eName,eSub,totalTime,totalScore from Exam where eName = '"+ stuEname +"'";
		}else{
			sql = "select eName,eSub,totalTime,totalScore from Exam where id = '"+ eId +"'";
		}
		Vector<String> row = dataProcess.getData(sql).get(0);
		String eName = row.get(0).trim();
		String eSub = row.get(1).trim();
		String totalTime = row.get(2);
		String totalScore = row.get(3);
		sql = "select * from Paper where eName = '"+ eName +"'";
		Vector<Vector<String>> rows = dataProcess.getData(sql);
		if(rows.size() == 0){
			mess = "该试卷还没有题目";			
		}else{
			paperContent = new String[rows.size()];
			for(int i = 0; i < rows.size(); i++){
				row = rows.get(i);
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
					paperContent[i] = "{ \"id\": \""+ row.get(0) +"\","						
							+ "\"qNo\": \"" + row.get(2) + "\","
							+ "\"qType\": \"" + row.get(3).trim() + "\","
							+ "\"qScore\": \"" + row.get(4) + "\","
							+ "\"qTitle\": \"" + row.get(5).trim() + "\","
							+ "\"qSelection\": " + qSelection + ","
							+ "\"qAnswer\": \"" + row.get(7).trim() + "\""
							+ "}";
				}else{
					paperContent[i] = "{ \"id\": \""+ row.get(0) +"\","						
							+ "\"qNo\": \"" + row.get(2) + "\","
							+ "\"qType\": \"" + row.get(3).trim() + "\","
							+ "\"qScore\": \"" + row.get(4) + "\","
							+ "\"qTitle\": \"" + row.get(5).trim() + "\","
							+ "\"qAnswer\": \"" + row.get(7).trim() + "\""
							+ "}";
				}								
			}
			paper = Arrays.toString(paperContent);
		}
		if(paper == ""){
			paperMess = "{ \"mess\" : \"" + mess + "\","
					+ "\"eName\": \"" + eName + "\","
					+ "\"eSub\": \"" + eSub + "\","
					+ "\"totalTime\": \"" + totalTime + "\","
					+ "\"totalScore\": \"" + totalScore + "\""
					+ "}";
		}else{
			if(flag == null){
				paperMess = "{ \"mess\" : \"" + mess + "\","
						+ "\"eName\": \"" + eName + "\","
						+ "\"eSub\": \"" + eSub + "\","
						+ "\"totalTime\": \"" + totalTime + "\","
						+ "\"totalScore\": \"" + totalScore + "\","
						+ "\"paperContent\": " + paper + ""
						+ "}";
			}else{ //flag="stuPaper"
				paperMess = "{ \"mess\" : \"" + mess + "\","
						+ "\"eName\": \"" + eName + "\","
						+ "\"eSub\": \"" + eSub + "\","
						+ "\"totalTime\": \"" + totalTime + "\","
						+ "\"totalScore\": \"" + totalScore + "\","
						+ "\"paperContent\": " + paper + ","
						+ "\"grade\": \"" + grade + "\","
						+ "\"stuAnswer\": \"" + stuAnswer + "\","
						+ "\"usedTime\": \"" + usedTime + "\""
						+ "}";
			}
		}

		response.getWriter().print(paperMess);
		
	}

}
