package com.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataBase.DataProcess;

/**
 * Servlet implementation class UpdateStudentServlet
 */
@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
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
		String sNo = request.getParameter("sNo");
		String sPwd = request.getParameter("sPassword");
		String sName = request.getParameter("sName");
		String sSex = request.getParameter("sSex");
		String major = request.getParameter("major");
		String year = request.getParameter("year");
		String clas = request.getParameter("clas");
		
		String eId = request.getParameter("eId");
		String status = request.getParameter("status");
		String grade = request.getParameter("grade");
		String flag = request.getParameter("flag");
		
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		
		String score = request.getParameter("score");
		String stuAnswer[] = request.getParameterValues("stuAnswer");
		String usedTime = request.getParameter("usedTime");
		String strAnswer = Arrays.toString(stuAnswer);		
		String mess = null; //要发送的信息
		String sql;
		DataProcess dataProcess = new DataProcess();
		
		if(flag.equals("updateBase")){
			if(sNo == null || sNo.equals("")){
				mess = "请输入学号";
			}else if(sPwd == null || sPwd.equals("")){
				mess = "请输入密码";
			}else if(sName == null || sName.equals("")){
				mess = "请输入姓名";
			}else if(sSex == null || sSex.equals("")){
				mess = "请选择性别";
			}else {
				
				sql = "update Student set sPassword = '"+sPwd+"' , sName='"+sName+"' , sSex='"+sSex+"' , major='"
						+major+"' , year='"+year+"' , class='"+clas+"' where sNo = '"+sNo+"' ";
				if(dataProcess.update(sql)){
					mess = "success";
				}else{
					mess = "error";
				}
				
			}
		} else if(flag.equals("updateStuSub")){
			sql = "update Stu_Exam set eStatus = '" + status + "', grade = '"
					+ grade + "' where id = '" + eId + "'";
			if(dataProcess.update(sql)){
				mess = "success";
			}else{
				mess = "error";
			}
		} else if(flag.equals("updateStuExam")){
			sql = "update Stu_Exam set eStatus = '" + status + "' where id = '" + eId + "'";
			if(dataProcess.update(sql)){
				mess = "success";
			}else{
				mess = "error";
			}
		} else if(flag.equals("updateStuPwd")){
			sql = "select sPassword from Student where sNo = '"+ sNo +"'";
			String rightPwd = dataProcess.getData(sql).get(0).get(0).trim();
			if(oldPwd.equals(rightPwd)){
				sql = "update Student set sPassword = '"+ newPwd +"' where sNo = '"+ sNo +"'";
				if(dataProcess.update(sql)){
					mess = "success";
				}else{
					mess = "error";
				}
			}else{
				mess = "旧密码输入错误";
			}
		}else if(flag.equals("stuExamSubmit")){
			sql = "select eStatus from Stu_Exam where id = '"+ eId +"'";
			String isWait = dataProcess.getData(sql).get(0).get(0).trim();
			if(isWait.equals("已考")){
				mess = "你已参加过该考试，请勿重复提交";
			}else{
				sql = "update Stu_Exam set eStatus = '已考', grade = '"+ score +"', stuAnswer = '"+ strAnswer +"', usedTime = '"+ usedTime +"' where id = '" + eId + "'";
				if(dataProcess.update(sql)){
					mess = "success";
				}else{
					mess = "error";
				}
			}
			
		}
		//发送数据
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
