package com.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class AddExamToClassServlet
 */
@WebServlet("/AddExamToClassServlet")
public class AddExamToClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExamToClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String eName = request.getParameter("eName");
		String eSub = request.getParameter("eSub");
		String major = request.getParameter("major");
		String year = request.getParameter("year");
		String clas = request.getParameter("clas");
		
		String mess = null; //要发送的信息
		String sql1, sql2, sql3;
		DataProcess dataProcess = new DataProcess();
	
		if(major == null || major.equals("暂无")){
			mess = "请选择专业";
		}else if(year == null || year.equals("暂无")){
			mess = "请选择年级";
		}else if(clas == null || clas.equals("暂无")){
			mess = "请选择班级";
		}else{
			sql1 = "select sNo from Student where major = '"+ major +"' and year = '"+ year + "' and class = '"+ clas + "'";
			Vector<Vector<String>> sNos = dataProcess.getData(sql1);
			String sNo;
			if(sNos.size() > 0){				
				for(int i = 0; i < sNos.size(); i++){
					sNo = sNos.get(i).get(0);
					sql2 = "select sNo from Stu_Exam where sNo = '"+ sNo +"' and eName = '"+ eName + "'";
					if(dataProcess.getData(sql2).size() == 0){
						sql3 = "insert into Stu_Exam(sNo, eName, eSub, eStatus, grade, stuAnswer, usedTime) values('"+ sNo +"', '"+ eName +"', '"+ eSub +"', '待考', '0', '', '')";
						if(dataProcess.update(sql3)){
							mess = "success";
						}else{
							mess = "error";
							break;
						}
					}else{
						mess = "success";
					}
				}
			}
		}
		//发送数据
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
