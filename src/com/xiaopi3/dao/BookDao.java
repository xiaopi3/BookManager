package com.xiaopi3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaopi3.model.Book;
import com.xiaopi3.util.StringUtil;

/**
	 * 图书Dao类
	 * @author PP
	 *
	 */
public class BookDao {
	/**
	 * 添加图书信息
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection conn,Book book) throws Exception{
		String sql="insert t_book values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setInt(5, book.getBookTypeId());
		pstmt.setString(6, book.getBookDesc());
		return pstmt.executeUpdate();
	}
	/**
	 * 条件查询图书信息
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection conn,Book book) throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_book b,t_bookType bt where b.bookTypeId=bt.id");
		if(StringUtil.isNotEmpty(book.getBookName())) {
			sb.append(" and b.bookName like '%"+book.getBookName()+"%'");//模糊查询
		}
		if(StringUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and b.author like '%"+book.getAuthor()+"%'");//模糊查询
		}
		if(book.getBookTypeId()!=null&&book.getBookTypeId()!=-1) {
			sb.append(" and b.bookTypeId="+book.getBookTypeId());
		}
		PreparedStatement pstmt=conn.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	/**
	 * 修改图书信息
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int update(Connection conn,Book book) throws Exception{
		String sql="update t_book set bookName=?,author=?,sex=?,price=?,bookTypeId=?,bookDesc=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setInt(5, book.getBookTypeId());
		pstmt.setString(6, book.getBookDesc());
		pstmt.setInt(7, book.getId());
		return pstmt.executeUpdate();
	}
	/**
	 * 删除图书信息
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection conn,Book book) throws Exception{
		String sql="delete from t_book where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, book.getId());
		return pstmt.executeUpdate();
	}
	/**
	 * 判断图书类别下是否存在图书
	 * @param conn
	 * @param bookTypeId
	 * @return
	 * @throws Exception
	 */
	public boolean existBookByBookTypeId(Connection conn,Integer bookTypeId)throws Exception{
		String sql="select * from t_book where bookTypeId=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, bookTypeId);
		ResultSet rs=pstmt.executeQuery();
		return rs.next();
	}
}
