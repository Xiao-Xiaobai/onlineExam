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
		String sNo = request.getParameter("sNo");
		String flag = request.getParameter("flag");
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		
		if(flag.equals("findBySno")){
			sql = "select * from Stu_Sub where sNo = '" + sNo + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "该学生未加入任何课程";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
				return;
			}else{
				String sql2 = "select sName from Student where sNo = '" + sNo + "'";
				String sName = dataProcess.getData(sql2).get(0).get(0).replaceAll(" ", "");
				Vector<String> oneRow;
				String[] stuMess = new String[rows.size()];
				for(int i = 0; i < rows.size(); i++){
					oneRow = rows.get(i);
					String subName = oneRow.get(2).replaceAll(" ", "");
					String sql3 = "select * from Stu_Exam where sNo = '"+ sNo +"' and eSub = '"+ subName +"'";
					String eName, status, grade, eId;
					if(dataProcess.getData(sql3).size() > 0){
						Vector<String> exam = dataProcess.getData(sql3).get(0);
						eId = exam.get(0);
						eName = exam.get(2).replaceAll(" ", "");
						status = exam.get(4).replaceAll(" ", "");
						grade = exam.get(5);
					}else{
						eName = "暂无";
						status = "无";
						grade = "0";
						eId = "";
					}
					stuMess[i] = "{ \"subId\": \"" + oneRow.get(0) + "\","
							+ "\"eId\": \"" + eId + " \","
							+ "\"sno\": \"" + oneRow.get(1).replaceAll(" ", "") + "\","
							+ "\"sName\": \"" + sName + " \","
							+ "\"subName\": \"" + oneRow.get(2).replaceAll(" ", "") + "\","
							+ "\"eName\": \"" + eName + "\","
							+ "\"status\": \"" + status + "\","
							+ "\"grade\": \"" + grade + "\""						
							+ "}"; 
				}
				String jsonStr = Arrays.toString(stuMess);
				response.getWriter().print(jsonStr);
			}
		}else if(flag.equals("studentQuery")){
			sql = "select * from Stu_Sub where sNo = '" + sNo + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "该学生未加入任何课程";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
				return;
			}else{
				sql = "select Stu_Sub.subName, tName from Stu_Sub, Tea_Sub, Teacher where Stu_Sub.sNo = '"+ sNo
						+"' and  Stu_Sub.subName = Tea_Sub.subName and Tea_Sub.tNo = Teacher.tNo";
				rows = dataProcess.getData(sql);
				if(rows.size() > 0){
					String[] subMess = new String[rows.size()];
					for(int i = 0; i < rows.size(); i++){
						Vector<String> oneRow = rows.get(i);
						subMess[i] = "{ \"subName\": \"" + oneRow.get(0).trim() + "\","
								+ "\"tName\": \"" + oneRow.get(1).trim() + " \""												
								+ "}"; 
					}
					String jsonStr = Arrays.toString(subMess);
					response.getWriter().print(jsonStr);
				}
			}
		}else{
			mess = "error";
		}
	}

}
