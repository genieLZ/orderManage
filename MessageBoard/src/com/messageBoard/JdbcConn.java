package com.messageBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JdbcConn {
	private static final String driver = "com.mysql.jdbc.Driver";;
	private static final String url = "jdbc:mysql://119.29.102.149:3306/messageboard?useUnicode=true&characterEncoding=utf-8&useSSL=false";;
	private static final String user = "root";
	private static final String password = "Cl520@lz";
	private Connection conn =null;
	private PreparedStatement psmt;
	private ResultSet rs;
	private String message;

	public JdbcConn() throws Exception {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {  
        return this.conn;  
    } 

	public void close() throws Exception {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public List<HashMap<String,Object>> selectMessage() throws Exception {
		String query = "select * from Message ORDER BY time desc;";
		psmt = conn.prepareStatement(query);
		rs = psmt.executeQuery();
		List<HashMap<String,Object>> result=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> map=new HashMap<String,Object>();
		if(rs==null)
			return null;
		ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
		int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数
		while(rs.next()) {
			map=new HashMap<String,Object>(columnCount);
			for(int i = 1; i <= columnCount; i++){
			map.put(md.getColumnName(i), rs.getObject(i));
			}
			result.add(map);
		}
		rs.close();
		conn.close();
		return result;
	}
	
	public int insertMessage(String text,String time) throws Exception {
		message = text.toString();
		String query = "insert into Message(message,time) value('"+message+"','"+time+"');";
		psmt = conn.prepareStatement(query);
		int r = psmt.executeUpdate();
		conn.close();
		return r;
	}
	
//	public void delectMessage(String text,Date t) throws Exception {
//		message = text.toString();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		time = sdf.format(t);;
//		String query = "delect Message where message = '"+message+"';";
//		psmt = conn.prepareStatement(query);
//	}
//	
//	public void updateMessage(String text,Date t) throws Exception {
//		message = text.toString();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		time = sdf.format(t);;
//		String query = "update Message set message = '"+message+"','"+time+"')";
//		psmt = conn.prepareStatement("");
//	}
}
