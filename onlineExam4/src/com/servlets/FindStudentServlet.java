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
 * Servlet implementation class FindStudentServlet
 */
@WebServlet("/FindStudentServlet")
public class FindStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindStudentServlet() {
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
		String findSno = request.getParameter("findSno");
		String findSname = request.getParameter("findSname");
		String findMajor = request.getParameter("findMajor");
		String findYear = request.getParameter("findYear");
		String findClass = request.getParameter("findClass");
		String flag = request.getParameter("flag");
		
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		if(flag.equals("findBySno")){
			sql = "select * from Student where sNo = '" + findSno + "'";
		}else if(flag.equals("findBySname")){
			sql = "select * from Student where sName = '"+ findSname +"'";
		}else if(flag.equals("findByMajor")){
			sql = "select * from Student where major = '"+ findMajor +"'";
		}else if(flag.equals("findByYear")){
			if(findMajor.equals("暂无")){
				sql = "select * from Student where year = '"+ findYear +"'";
			}else{
				sql = "select * from Student where major = '"+ findMajor +"' and year = '"+ findYear +"'";
			}
		}else if(flag.equals("findByClass")){
			if(!findMajor.equals("暂无") && !findYear.equals("暂无")){
				sql = "select * from Student where major = '"+ findMajor +"' and year = '"+ findYear +"' and class = '"+ findClass +"'";
			}else if(!findMajor.equals("暂无")){
				sql = "select * from Student where major = '"+ findMajor +"' and class = '"+ findClass +"'";
			}else if(!findYear.equals("暂无")){
				sql = "select * from Student where year = '"+ findYear +"' and class = '"+ findClass +"'";
			}else{
				sql = "select * from Student where class = '"+ findClass +"'";
			}
		}else{
			sql = "select * from Student";
		}
		rows = dataProcess.getData(sql);
		if(rows.size() == 0){
			mess = "查询的学生不存在";
			String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
			response.getWriter().print(jsonStr);
			return;
		}else{
			Vector<String> oneStu;
			String[] stuMess = new String[rows.size()];
			for(int i = 0; i < rows.size(); i++){
				oneStu = rows.get(i);
				stuMess[i] = "{ \"sno\": \"" + oneStu.get(0).replaceAll(" ", "") + "\","
						+ "\"password\": \"" + oneStu.get(1).replaceAll(" ", "") + "\","
						+ "\"name\": \"" + oneStu.get(2).replaceAll(" ", "") + "\","
						+ "\"sex\": \"" + oneStu.get(3).replaceAll(" ", "") + "\","
						+ "\"major\": \"" + oneStu.get(4).replaceAll(" ", "") + "\","
						+ "\"year\": \"" + oneStu.get(5).replaceAll(" ", "") + "\","
						+ "\"clas\": \"" + oneStu.get(6).replaceAll(" ", "") + "\""
						+ "}"; 
			}
			String jsonStr = Arrays.toString(stuMess);
			response.getWriter().print(jsonStr);
		}
	}
		

}
