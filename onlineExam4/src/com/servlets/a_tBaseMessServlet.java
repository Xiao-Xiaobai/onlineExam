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
 * Servlet implementation class a_tBaseMess
 */
@WebServlet("/a_tBaseMess")
public class a_tBaseMessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a_tBaseMessServlet() {
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
		String tNo = request.getParameter("tNo");
		String flag = request.getParameter("flag");
		
		String sql;
		DataProcess dataProcess = new DataProcess();
		if(flag == null){
			sql = "select * from Teacher order by tNo";	
		}else if(flag.equals("teacherQuery")){
			sql = "select * from Teacher where tNo = '"+ tNo +"'";
		}else{
			sql = "select * from Teacher";
		}

		Vector<Vector<String>> teas = dataProcess.getData(sql);
		Vector<String> oneTea;
		String[] teaMess = new String[teas.size()];
		for(int i = 0; i < teas.size(); i++){
			oneTea = teas.get(i);
			teaMess[i] = "{ \"tno\": \"" + oneTea.get(0).replaceAll(" ", "") + "\","
					+ "\"password\": \"" + oneTea.get(1).replaceAll(" ", "") + "\","
					+ "\"name\": \"" + oneTea.get(2).replaceAll(" ", "") + "\","
					+ "\"sex\": \"" + oneTea.get(3).replaceAll(" ", "") + "\" "					
					+ "}"; 
		}
		String jsonStr = Arrays.toString(teaMess);
		response.getWriter().print(jsonStr);
	}

}
