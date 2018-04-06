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
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
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
		String subjects[] = request.getParameterValues("subjects");
		String flag = request.getParameter("flag");
		
		String mess = null; //Ҫ���͵���Ϣ
		String sql;
		DataProcess dataProcess = new DataProcess();
		
		if(flag == null){
			if(sNo == null || sNo.equals("")){
				mess = "������ѧ��";
			}else if(sPwd == null || sPwd.equals("")){
				mess = "����������";
			}else if(sName == null || sName.equals("")){
				mess = "����������";
			}else if(sSex == null || sSex.equals("")){
				mess = "��ѡ���Ա�";
			}else {
				
				sql = "select * from Student where sNo = '" + sNo + "'";
				Vector<Vector<String>>rows = dataProcess.getData(sql);
				if(rows.size() > 0){
					mess = "��ѧ���Ѵ���";
				}else{
					sql = "insert into Student values('"+ sNo +"', '"+ sPwd + "', '"+ sName + "', '"+ sSex + "', '"
							+ major + "', '"+ year + "', '"+ clas + "')";
					if(dataProcess.update(sql)){
						mess = "success";
					}
					if(subjects != null){
						for(int i = 0; i < subjects.length; i++){
							String sql2 = "select subName from Subject where subName = '" + subjects[i] + "'";
							if(dataProcess.getData(sql2).size() > 0){
								sql = "select subName from Stu_Sub where sNo = '"+ sNo +"' and subName = '"+ subjects[i] +"'";
								if(dataProcess.getData(sql).size() == 0){
									sql = "insert into Stu_Sub(sNo, subName) values('" + sNo + "', '" + subjects[i] + "')";
									if(dataProcess.update(sql)){
										mess = "success";
									}else{
										mess = "error";
										break;
									}
								}
							}else{
								mess = "�Բ��𣬲����ڡ�" + subjects[i] + "�����ſγ̣�";
								break;
							}
						}
					}				
				}
			}
		} else if(flag.equals("addStuSub")){
			if(subjects != null){
				for(int i = 0; i < subjects.length; i++){
					String sql2 = "select subName from Subject where subName = '" + subjects[i] + "'";
					if(dataProcess.getData(sql2).size() > 0){
						sql = "select subName from Stu_Sub where sNo = '"+ sNo + "' and subName = '"+ subjects[i] + "'";
						if(dataProcess.getData(sql).size() == 0){
							sql = "insert into Stu_Sub(sNo, subName) values('" + sNo + "', '" + subjects[i] + "')";
							if(dataProcess.update(sql)){
								mess = "success";
							}else{
								mess = "error";
								break;
							}
						}else{
							mess = "�Բ������Ѿ��С�" + subjects[i] + "�����ſγ�";
							break;
						}
					}else{
						mess = "�Բ��𣬲����ڡ�" + subjects[i] + "�����ſγ̣�";
						break;
					}
				}
			}else{
				mess = "������γ���";
			}
			
		}
		//��������
		String jsonStr = "{ \"mess\" : \"" + mess + "\"}";
		response.getWriter().print(jsonStr);
	}

}
