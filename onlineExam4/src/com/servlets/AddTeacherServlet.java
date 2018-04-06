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
 * Servlet implementation class AddTeacherServlet
 */
@WebServlet("/AddTeacherServlet")
public class AddTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeacherServlet() {
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
		String majors[] = request.getParameterValues("major");
		String years[] = request.getParameterValues("year");
		String clases[] = request.getParameterValues("clas");
		String subjects[] = request.getParameterValues("subjects");
		String flag = request.getParameter("flag");
		
		String mess = null; //要发送的信息
		String sql;
		DataProcess dataProcess = new DataProcess();
		
		if(flag == null){
			if(tNo == null || tNo.equals("")){
				mess = "请输入工号";
			}else if(tPwd == null || tPwd.equals("")){
				mess = "请输入密码";
			}else if(tName == null || tName.equals("")){
				mess = "请输入姓名";
			}else if(tSex == null || tSex.equals("")){
				mess = "请选择性别";
			}else {				
				sql = "select * from Teacher where tNo = '" + tNo + "'";
				Vector<Vector<String>>rows = dataProcess.getData(sql);
				if(rows.size() > 0){
					mess = "该教师已存在";
				}else{
					sql = "insert into Teacher values('"+ tNo +"', '"+ tPwd + "', '"+ tName + "', '"+ tSex + "')";
					if(dataProcess.update(sql)){
						mess = "success";
					}
					for(int i = 0; i < majors.length; i++){
						if(!majors[i].equals("暂无")){
							sql = "insert into Teacher_Class(tNo, major, year, class) values('" + tNo
									+ "', '"+ majors[i] + "', '"+ years[i] + "', '"+ clases[i] + "')"; 
							if(dataProcess.update(sql)){
								mess = "success";
							}
						}
					}

					if(subjects != null){
						for(int i = 0; i < subjects.length; i++){
							String sql2 = "select subName from Subject where subName = '" + subjects[i] + "'";
							if(dataProcess.getData(sql2).size() > 0){
								sql = "insert into Tea_Sub(tNo, subName, examStatus) values('" + tNo + "', '" + subjects[i] + "', '无')";
								if(dataProcess.update(sql)){
									mess = "success";
								}else{
									mess = "error";
									break;
								}
							}else{
								mess = "对不起，不存在《" + subjects[i] + "》这门课程！";
								break;
							}
						}
					}
					
				}
			}
		} else if(flag.equals("addTeaSub")){
			if(subjects == null){
				mess = "请输入课程名";
			}else{
				for(int i = 0; i < subjects.length; i++){
					String sql2 = "select subName from Subject where subName = '" + subjects[i] + "'";
					if(dataProcess.getData(sql2).size() > 0){
						sql = "select subName from Tea_Sub where tNo = '"+ tNo + "' and subName = '"+ subjects[i] + "'";
						if(dataProcess.getData(sql).size() == 0){
							sql = "insert into Tea_Sub(tNo, subName, examStatus) values('" + tNo + "', '" + subjects[i] + "', '无')";
							if(dataProcess.update(sql)){
								mess = "success";
							}else{
								mess = "error";
								break;
							}
						}else{
							mess = "对不起，你已经有《" + subjects[i] + "》这门课程";
							break;
						}
					}else{
						mess = "对不起，不存在《" + subjects[i] + "》这门课程！";
						break;
					}
				}
			}
		} else if(flag.equals("addTeaClass")){
			if(majors[0].equals("暂无")){
				mess = "请选择专业";
			}else{
				for(int i = 0; i < majors.length; i++){
					if(!majors[i].equals("暂无")){
						sql = "insert into Teacher_Class(tNo, major, year, class) values('" + tNo
								+ "', '"+ majors[i] + "', '"+ years[i] + "', '"+ clases[i] + "')"; 
						dataProcess.update(sql);
					}
				}
				mess = "success";
			}
		}
		//发送数据
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
