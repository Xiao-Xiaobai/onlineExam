package com.dataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

public class DataProcess {
	private String url = "jdbc:sqlserver://localhost:1433;" +
            "databaseName = onlineExam;"; //���ݿ��������λ��
	private String user = "bj"; //��½���ݿ���������û���
	private String password = "123";//��½���ݿ������������
	private Connection conn = null;
	private Statement stm = null;
	private ResultSet rst = null;
	
	//��ȡ���ݣ�sql��select���,rows���ļ�¼
	public Vector<Vector<String>> getData(String sql){
		Vector<Vector<String>> rows = new Vector<Vector<String>>(); //��¼�ļ���
		
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //�������ݿ�����
			conn = DriverManager.getConnection(url, user, password);//��ȡ����
			stm = conn.createStatement();//����ִ��sql��������
			rst = stm.executeQuery(sql); //ִ��sql���
			Vector<String> row = null; //����һ����¼
			ResultSetMetaData rsmd = rst.getMetaData();
			int col = rsmd.getColumnCount();
			while(rst.next()){
				row=new Vector<String>(); //ÿ����¼��Ӧ����һ��Vector����row
				for(int i = 0; i < col; i++){//ѭ����
					row.add(rst.getString(i + 1));
				}
				rows.add(row);
			}
			
			stm.close();
			conn.close();
		}catch(Exception e){
			System.err.print(e);
		}
		
		return rows;
	}
	
	//���ɾ���������ݣ�sql��insert��delete��update���
	public boolean update(String sql){
		boolean b = false;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //�������ݿ�����
			conn = DriverManager.getConnection(url, user, password);//��ȡ����
			stm = conn.createStatement();//����ִ��sql��������
			stm.executeUpdate(sql); //ִ��sql���
			b = true;
			stm.close();
			conn.close();
		}catch(Exception e){
			System.err.print(e);
		}
		return b;
	}
}
