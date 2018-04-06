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
 * Servlet implementation class AddSubjectServlet
 */
@WebServlet("/AddSubjectServlet")
public class AddSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubjectServlet() {
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
		String tNo = request.getParameter("tNo");
		//String flag = request.getParameter("flag");
		
		String mess = null; //要发送的信息
		String sql;
		DataProcess dataProcess = new DataProcess();
		
		
			if(subNo == null || subNo.equals("")){
				mess = "请输入课程号";
			}else if(subName == null || subName.equals("")){
				mess = "请输入课程名";
			}else {				
				sql = "select * from Subject where subNo = '" + subNo + "'";
				Vector<Vector<String>>rows = dataProcess.getData(sql);
				if(rows.size() > 0){
					mess = "该课程已存在";
				}else{
					if((tNo == null || tNo.equals(""))){
						sql = "insert into Subject values('"+ subNo +"', '"+ subName + "', '')";
					}else{
						sql = "insert into Subject values('"+ subNo +"', '"+ subName + "', '"+ tNo + "')";
					}
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
