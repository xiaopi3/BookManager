package com.xiaopi3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaopi3.model.Book;
import com.xiaopi3.util.StringUtil;

/**
	 * ͼ��Dao��
	 * @author PP
	 *
	 */
public class BookDao {
	/**
	 * ���ͼ����Ϣ
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
	 * ������ѯͼ����Ϣ
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection conn,Book book) throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_book b,t_bookType bt where b.bookTypeId=bt.id");
		if(StringUtil.isNotEmpty(book.getBookName())) {
			sb.append(" and b.bookName like '%"+book.getBookName()+"%'");//ģ����ѯ
		}
		if(StringUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and b.author like '%"+book.getAuthor()+"%'");//ģ����ѯ
		}
		if(book.getBookTypeId()!=null&&book.getBookTypeId()!=-1) {
			sb.append(" and b.bookTypeId="+book.getBookTypeId());
		}
		PreparedStatement pstmt=conn.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	/**
	 * �޸�ͼ����Ϣ
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
	 * ɾ��ͼ����Ϣ
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
	 * �ж�ͼ��������Ƿ����ͼ��
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
