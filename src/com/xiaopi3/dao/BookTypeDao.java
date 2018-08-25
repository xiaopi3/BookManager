package com.xiaopi3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaopi3.model.BookType;
import com.xiaopi3.util.StringUtil;

public class BookTypeDao {
	/**
	 * 添加图书类别和描述
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection conn,BookType bookType) throws Exception {
		String sql="insert into t_bookType values(null,?,?);";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		
		return pstmt.executeUpdate();
	}
	/**
	 * 条件查询
	 * @param conn
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection conn,BookType bookType) throws Exception {
		StringBuffer sb=new StringBuffer("select * from t_bookType");
		if(StringUtil.isNotEmpty(bookType.getBookTypeName())) {
			sb.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");//模糊查询
		}
		
		PreparedStatement pstmt=conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	/**
	 * 删除图书类别
	 * @param conn
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection conn,int id) throws Exception{
		String sql="delete from t_bookType where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,id );
		return pstmt.executeUpdate();
	}
	/**
	 * 更新图书类别
	 * @param conn
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public int update(Connection conn,BookType bookType) throws Exception{
		String sql="update t_bookType set bookTypeName=?,bookTypeDesc=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		pstmt.setInt(3, bookType.getId());
		return pstmt.executeUpdate();
	}
}
