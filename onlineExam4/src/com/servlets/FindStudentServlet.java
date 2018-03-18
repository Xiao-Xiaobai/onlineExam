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
		String sNo = request.getParameter("sNo").replaceAll(" ", "");
		String sName = request.getParameter("sName");
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		if(sNo == "" && sName == ""){
			mess = "请输入查询关键字";
			String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
			response.getWriter().print(jsonStr);
		}
		if(sNo != "" && sNo != null){
			sql = "select * from Student where sNo = '" + sNo + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在该学生";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				Vector<String> oneStu;
				String[] stuMess = new String[rows.size()];
				for(int i = 0; i < rows.size(); i++){
					oneStu = rows.get(i);
					stuMess[i] = "{ \"sno\": \" " + oneStu.get(0) + " \","
							+ "\"password\": \" " + oneStu.get(1) + " \","
							+ "\"name\": \" " + oneStu.get(2) + " \","
							+ "\"sex\": \" " + oneStu.get(3) + " \","
							+ "\"major\": \" " + oneStu.get(4) + " \","
							+ "\"year\": \" " + oneStu.get(5) + " \","
							+ "\"clas\": \" " + oneStu.get(6) + " \""
							+ "}"; 
				}
				String jsonStr = Arrays.toString(stuMess);
				response.getWriter().print(jsonStr);
			}
		}
		if(sName != "" && sName != null){
			sql = "select * from Student where sName = '" + sName + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在该学生";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				Vector<String> oneStu;
				String[] stuMess = new String[rows.size()];
				for(int i = 0; i < rows.size(); i++){
					oneStu = rows.get(i);
					stuMess[i] = "{ \"sno\": \" " + oneStu.get(0) + " \","
							+ "\"password\": \" " + oneStu.get(1) + " \","
							+ "\"name\": \" " + oneStu.get(2) + " \","
							+ "\"sex\": \" " + oneStu.get(3) + " \","
							+ "\"major\": \" " + oneStu.get(4) + " \","
							+ "\"year\": \" " + oneStu.get(5) + " \","
							+ "\"clas\": \" " + oneStu.get(6) + " \""
							+ "}"; 
				}
				String jsonStr = Arrays.toString(stuMess);
				response.getWriter().print(jsonStr);
			}
		}
	}

}
