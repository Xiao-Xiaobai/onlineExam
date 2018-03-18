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
		response.setContentType("text/html;charset=utf-8");
		String sql = "select * from Student order by sNo";
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> stus = dataProcess.getData(sql);
		Vector<String> oneStu;
		String[] stuMess = new String[stus.size()];
		for(int i = 0; i < stus.size(); i++){
			oneStu = stus.get(i);
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
