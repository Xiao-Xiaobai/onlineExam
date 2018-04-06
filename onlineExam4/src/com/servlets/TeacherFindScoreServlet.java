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
 * Servlet implementation class TeacherFindScoreServlet
 */
@WebServlet("/TeacherFindScoreServlet")
public class TeacherFindScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherFindScoreServlet() {
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
		//String flag = request.getParameter("flag");
		String eName = request.getParameter("eName");
		String major = request.getParameter("major");
		String year = request.getParameter("year");
		String clas = request.getParameter("clas");
		
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		
		sql = "select Student.sNo,sName,sSex,major,year,class,Stu_Exam.id,grade,eStatus from Student,Stu_Exam where major = '"+ major 
				+"' and year = '"+ year +"' and class = '"+ clas +"' and Stu_Exam.eName = '"+ eName +"' and Stu_Exam.sNo = Student.sNo";
		rows = dataProcess.getData(sql);
		if(rows.size() == 0){
			mess = "不存在该查询条件下的学生";
			String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
			response.getWriter().print(jsonStr);
			return;
		}else{
			String sql2 = "select count(*) from ("+ sql +") as totalNum";
			String totalNum = dataProcess.getData(sql2).get(0).get(0);
			String sql3 = "select count(*) from ("+ sql +") as testedNum where eStatus = '已考'";
			String testedNum = dataProcess.getData(sql3).get(0).get(0);
			String sql4 = "select totalScore from Exam where eName = '"+ eName +"'";
			float totalScore = Float.parseFloat(dataProcess.getData(sql4).get(0).get(0));
			String sql5 = "select count(*) from ("+ sql +") as passNum where grade >= "+ totalScore * 0.6;
			String passNum = dataProcess.getData(sql5).get(0).get(0);
			String sql6 = "select avg(grade) from ("+ sql +") as avgScore";
			float avgScore = Float.parseFloat(dataProcess.getData(sql6).get(0).get(0));
			
			Vector<String> oneStu;
			String[] stuMess = new String[rows.size()];
			for(int i = 0; i < rows.size(); i++){
				oneStu = rows.get(i);
				stuMess[i] = "{ \"sno\": \"" + oneStu.get(0).replaceAll(" ", "") + "\","
						+ "\"name\": \"" + oneStu.get(1).replaceAll(" ", "") + "\","
						+ "\"sex\": \"" + oneStu.get(2).replaceAll(" ", "") + "\","
						+ "\"major\": \"" + oneStu.get(3).replaceAll(" ", "") + "\","
						+ "\"year\": \"" + oneStu.get(4).replaceAll(" ", "") + "\","
						+ "\"clas\": \"" + oneStu.get(5).replaceAll(" ", "") + "\","
						+ "\"stuEid\": \"" + oneStu.get(6) + "\","
						+ "\"grade\": \"" + oneStu.get(7) + "\","
						+ "\"eStatus\": \"" + oneStu.get(8).replaceAll(" ", "") + "\""
						+ "}"; 
			}
			String jsonStr = "{ \"totalNum\": \"" + totalNum + "\","
					+ "\"testedNum\": \"" + testedNum +"\","
					+ "\"totalScore\": \"" + totalScore +"\","
					+ "\"passNum\": \"" + passNum +"\","
					+ "\"avgScore\": \"" + avgScore +"\","
					+ "\"stuMess\": " + Arrays.toString(stuMess)
					+ "}"; 
			//System.out.println(jsonStr);
			response.getWriter().print(jsonStr);
		}
	}

}
