package com.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class UpdateTeacherServlet
 */
@WebServlet("/UpdateTeacherServlet")
public class UpdateTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTeacherServlet() {
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
		String tNo = request.getParameter("tNo");
		String tPwd = request.getParameter("tPassword");
		String tName = request.getParameter("tName");
		String tSex = request.getParameter("tSex");
		String major = request.getParameter("major");
		String year = request.getParameter("year");
		String clas = request.getParameter("clas");
		
		String id = request.getParameter("id");
		
		String flag = request.getParameter("flag");
		
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		
		String mess = null; //Ҫ���͵���Ϣ
		String sql;
		DataProcess dataProcess = new DataProcess();
		
		if(flag == null){
			if(tNo == null || tNo.equals("")){
				mess = "������ѧ��";
			}else if(tPwd == null || tPwd.equals("")){
				mess = "����������";
			}else if(tName == null || tName.equals("")){
				mess = "����������";
			}else if(tSex == null || tSex.equals("")){
				mess = "��ѡ���Ա�";
			}else {
				
				sql = "update Teacher set tPassword = '"+tPwd+"' , tName='"+tName+"' , tSex='"+tSex+"'";
				if(dataProcess.update(sql)){
					mess = "success";
				}else{
					mess = "error";
				}
				
			}
		} else if(flag.equals("updateTeaClass")){
			sql = "update Teacher_Class set major = '" + major + "', year = '" + year + "', class = '" + clas + "'where id = '" + id + "'";
			if(dataProcess.update(sql)){
				mess = "success";
			}else{
				mess = "error";
			}
		}else if(flag.equals("updateTeaPwd")){
			sql = "select tPassword from Teacher where tNo = '"+ tNo +"'";
			String rightPwd = dataProcess.getData(sql).get(0).get(0).trim();
			if(oldPwd.equals(rightPwd)){
				sql = "update Teacher set tPassword = '"+ newPwd +"' where tNo = '"+ tNo +"'";
				if(dataProcess.update(sql)){
					mess = "success";
				}else{
					mess = "error";
				}
			}else{
				mess = "�������������";
			}
		}
		//��������
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
