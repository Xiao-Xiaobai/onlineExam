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
 * Servlet implementation class TeacherFindStuServlet
 */
@WebServlet("/TeacherFindStuServlet")
public class TeacherFindStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherFindStuServlet() {
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
		String tNo = request.getParameter("tNo");
		String flag = request.getParameter("flag");
		String findSub = request.getParameter("findSub");
		String findMajor = request.getParameter("findMajor");
		String findYear = request.getParameter("findYear");
		String findClass = request.getParameter("findClass");
		
		String mess = null;
		String sql;
		DataProcess dataProcess = new DataProcess();
		Vector<Vector<String>> rows;
		
		if(flag.equals("findAll")){
			sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
					tNo + "' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
		}else if(flag.equals("findBySub")){
			sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
					tNo + "' and Tea_Sub.subName = '"+ findSub +"' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
		}else if(flag.equals("findByMajor")){
			if(findSub.equals("")){
				sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
						tNo + "' and Student.major = '"+ findMajor +"' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
			}else{
				sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
						tNo + "' and Tea_Sub.subName = '"+ findSub +"' and Student.major = '"+ findMajor +"' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
			}
		}else if(flag.equals("findByYear")){
			if(findMajor.equals("ÔÝÎÞ")){
				if(findSub.equals("")){
					sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
							tNo + "' and Student.year = '"+ findYear +"' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
				}else{
					sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
							tNo + "' and Tea_Sub.subName = '"+ findSub +"' and Student.year = '"+ findYear +"' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
				}
			}else{
				if(findSub.equals("")){
					sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
							tNo + "' and Student.major = '"+ findMajor +"' and Student.year = '"+ findYear +"' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
				}else{
					sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
							tNo + "' and Tea_Sub.subName = '"+ findSub +"' and Student.major = '"+ findMajor +"' and Student.year = '"+ findYear +"' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
				}
			}
			
		}else if(flag.equals("findByClass")){			
			if(findSub.equals("")){
				sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
						tNo + "' and Student.major = '"+ findMajor +"' and Student.year = '"+ findYear +"' and Student.class = '"+ findClass +"' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
			}else{
				sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
						tNo + "' and Tea_Sub.subName = '"+ findSub +"' and Student.major = '"+ findMajor +"' and Student.year = '"+ findYear +"' and Student.class = '"+ findClass +"' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
			}			
		}else{
			sql = "select distinct Student.sNo,sName,sSex,major,year,class from Student, Tea_Sub, Stu_Sub where Tea_Sub.tNo = '"+
					tNo + "' and Tea_Sub.subName = Stu_Sub.subName and Stu_Sub.sNo = Student.sNo";
		}
		rows = dataProcess.getData(sql);
		Vector<String> oneStu;
		String[] stuMess = new String[rows.size()];
		for(int i = 0; i < rows.size(); i++){
			oneStu = rows.get(i);
			stuMess[i] = "{ \"sno\": \"" + oneStu.get(0).replaceAll(" ", "") + "\","
					+ "\"name\": \"" + oneStu.get(1).replaceAll(" ", "") + "\","
					+ "\"sex\": \"" + oneStu.get(2).replaceAll(" ", "") + "\","
					+ "\"major\": \"" + oneStu.get(3).replaceAll(" ", "") + "\","
					+ "\"year\": \"" + oneStu.get(4).replaceAll(" ", "") + "\","
					+ "\"clas\": \"" + oneStu.get(5).replaceAll(" ", "") + "\""
					+ "}"; 
		}
		String jsonStr = Arrays.toString(stuMess);
		//System.out.println(jsonStr);
		response.getWriter().print(jsonStr);
	}

}
