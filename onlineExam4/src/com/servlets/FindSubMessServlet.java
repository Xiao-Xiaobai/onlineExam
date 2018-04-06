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
 * Servlet implementation class FindSubMessServlet
 */
@WebServlet("/FindSubMessServlet")
public class FindSubMessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindSubMessServlet() {
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
		String subNo = request.getParameter("subNo");
		String subName = request.getParameter("subName");
		String flag = request.getParameter("flag");
		
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		if(flag == null){
			sql = "select * from Subject";
			rows = dataProcess.getData(sql);
			Vector<String> oneSub;
			String[] subMess = new String[rows.size()];
			for(int i = 0; i < rows.size(); i++){
				oneSub = rows.get(i);
				String tNo;
				String tName = "";
				String sql2;
				tNo = oneSub.get(2).replaceAll(" ", "");
				if(tNo != null && !tNo.equals("")){
					sql2 = "select tName from Teacher where tNo = '" + tNo + "'";	
					if(dataProcess.getData(sql2).size() > 0){
						tName = dataProcess.getData(sql2).get(0).get(0);
					}else{
						tName = "";
					}
				}else{
					String sql3 = "select tNo from Tea_sub where subName = '"+ oneSub.get(1).replaceAll(" ", "")+"'";
					if(dataProcess.getData(sql3).size() > 0){
						tNo = dataProcess.getData(sql3).get(0).get(0);
						sql2 = "select tName from Teacher where tNo = '" + tNo + "'";
						if(dataProcess.getData(sql2).size() > 0){
							tName = dataProcess.getData(sql2).get(0).get(0);
						}else{
							tName = "";
						}
					}else{
						tNo = "";
						tName = "";
					}
				}
				
				subMess[i] = "{ \"subNo\": \"" + oneSub.get(0).replaceAll(" ", "") + "\","
						+ "\"subName\": \"" + oneSub.get(1).replaceAll(" ", "") + "\","
						+ "\"tNo\": \"" + tNo + "\","
						+ "\"tName\": \"" + tName + "\""
						+ "}"; 
			}
			String jsonStr = Arrays.toString(subMess);
			response.getWriter().print(jsonStr);
		}else if(flag.equals("findBySubNo")){
			sql = "select * from Subject where subNo = '" + subNo + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在该课程";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				Vector<String> oneSub;
				String[] subMess = new String[rows.size()];
				String tNo,tName;
				for(int i = 0; i < rows.size(); i++){
					oneSub = rows.get(i);
					tNo = oneSub.get(2).replaceAll(" ", "");
					if(tNo != null && !tNo.equals("")){
						String sql2 = "select tName from Teacher where tNo = '" + tNo + "'";
						if(dataProcess.getData(sql2).size() > 0){
							tName = dataProcess.getData(sql2).get(0).get(0);
						}else{
							tName = "";
						}
					}else{
						tNo = "";
						tName = "";
					}
					subMess[i] = "{ \"subNo\": \"" + oneSub.get(0).replaceAll(" ", "") + "\","
							+ "\"subName\": \"" + oneSub.get(1).replaceAll(" ", "") + "\","
							+ "\"tNo\": \"" + tNo + "\","
							+ "\"tName\": \"" + tName + "\""
							+ "}"; 
				}
				String jsonStr = Arrays.toString(subMess);
				response.getWriter().print(jsonStr);
			}
		}else if(flag.equals("findBySubName")){
			sql = "select * from Subject where subName = '" + subName + "'";
			rows = dataProcess.getData(sql);
			if(rows.size() == 0){
				mess = "不存在该课程";
				String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
				response.getWriter().print(jsonStr);
			}else{
				Vector<String> oneSub;
				String[] subMess = new String[rows.size()];
				String tNo,tName;
				for(int i = 0; i < rows.size(); i++){
					oneSub = rows.get(i);
					tNo = oneSub.get(2).replaceAll(" ", "");
					if(tNo != null && !tNo.equals("")){
						String sql2 = "select tName from Teacher where tNo = '" + tNo + "'";
						if(dataProcess.getData(sql2).size() > 0){
							tName = dataProcess.getData(sql2).get(0).get(0);
						}else{
							tName = "";
						}
					}else{
						tNo = "";
						tName = "";
					}
					subMess[i] = "{ \"subNo\": \"" + oneSub.get(0).replaceAll(" ", "") + "\","
							+ "\"subName\": \"" + oneSub.get(1).replaceAll(" ", "") + "\","
							+ "\"tNo\": \"" + tNo + "\","
							+ "\"tName\": \"" + tName + "\""
							+ "}"; 
				}
				String jsonStr = Arrays.toString(subMess);
				response.getWriter().print(jsonStr);
			}
		}

	
	}

}
