package com.servlets;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class a_sBaseMess
 */
@WebServlet("/a_sBaseMess")
public class a_sBaseMessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a_sBaseMessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
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
		
		String sql;
		DataProcess dataProcess = new DataProcess();
		if(flag == null){
			sql = "select * from Student order by sNo";	
		}else if(flag.equals("studentQuery")){
			sql = "select * from Student where sNo = '"+ sNo +"'";
		}else{
			sql = "select * from Student";
		}
		Vector<Vector<String>> stus = dataProcess.getData(sql);
		Vector<String> oneStu;
		String[] stuMess = new String[stus.size()];
		for(int i = 0; i < stus.size(); i++){
			oneStu = stus.get(i);
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
		//System.out.println(jsonStr);
		response.getWriter().print(jsonStr);
	}

}
