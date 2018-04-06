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
 * Servlet implementation class AddSubToClassServlet
 */
@WebServlet("/AddSubToClassServlet")
public class AddSubToClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubToClassServlet() {
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
		String subNo = request.getParameter("subNo");
		String subName = request.getParameter("subName");
		String major = request.getParameter("major");
		String year = request.getParameter("year");
		String clas = request.getParameter("clas");
		//String flag = request.getParameter("flag");
		
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
					sql2 = "select sNo from Stu_Sub where sNo = '"+ sNo +"' and subName = '"+ subName +"'";
					if(dataProcess.getData(sql2).size() == 0){
						sql3 = "insert into Stu_Sub(sNo, subName) values('"+ sNo +"', '"+ subName +"')";
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
			}else{
				mess = "该班级没有学生";
			}
			sql1 = "select tNo from Tea_sub where subName = '"+ subName +"'";
			if(dataProcess.getData(sql1).size() > 0){
				String tNo = dataProcess.getData(sql1).get(0).get(0);
				sql2 = "select tNo from Teacher_Class where tNo = '"+ tNo +"' and major = '"+ major +"' and year = '"+ year + "' and class = '"+ clas + "'";
				if(dataProcess.getData(sql2).size() == 0){
					sql3 = "insert into Teacher_Class(tNo, major, year, class) values('"+ tNo +"', '"+ major +"', '"+ year +"', '"+ clas +"')";
					if(dataProcess.update(sql3)){
						mess = "success";
					}else{
						mess = "error";
					}
				}
			}
			
		}
		//发送数据
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
