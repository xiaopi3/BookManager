package com.xiaopi3.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.xiaopi3.dao.BookDao;
import com.xiaopi3.dao.BookTypeDao;
import com.xiaopi3.model.Book;
import com.xiaopi3.model.BookType;
import com.xiaopi3.util.DbUtil;
import com.xiaopi3.util.StringUtil;

public class BookAddInterFrm extends JInternalFrame {
	private JTextField bookNameTxt;
	private JTextField bookAuthorTxt;
	private JTextField bookPriceTxt;
	private JComboBox bookTypeCb;
	private JTextArea bookDescTxt;
	private JRadioButton manJrb;
	private JRadioButton femalJrb;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DbUtil dbUtil=new DbUtil();
	private BookDao bookDao=new BookDao();
	private BookTypeDao bookTypeDao=new BookTypeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddInterFrm frame = new BookAddInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookAddInterFrm() {
		setTitle("\u56FE\u4E66\u6DFB\u52A0");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 499, 570);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		
		bookAuthorTxt = new JTextField();
		bookAuthorTxt.setColumns(10);
		
		manJrb = new JRadioButton("\u7537");
		manJrb.setSelected(true);
		buttonGroup.add(manJrb);
		
		femalJrb = new JRadioButton("\u5973");
		buttonGroup.add(femalJrb);
		
		JLabel label_2 = new JLabel("\u4F5C\u8005\u6027\u522B\uFF1A");
		
		JLabel label_3 = new JLabel("\u56FE\u4E66\u4EF7\u683C\uFF1A");
		
		bookPriceTxt = new JTextField();
		bookPriceTxt.setColumns(10);
		
		bookTypeCb = new JComboBox();
		bookTypeCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillBookType();
			}
		});
		
		JLabel label_4 = new JLabel("\u56FE\u4E66\u7C7B\u522B\uFF1A");
		
		JLabel label_5 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		
		bookDescTxt = new JTextArea();
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185)));
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAddActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultValue();
			}
		});
		button_1.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(55)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button)
							.addGap(29)
							.addComponent(button_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(label_2)
								.addComponent(label_4))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(manJrb)
											.addGap(18)
											.addComponent(femalJrb)))
									.addGap(24)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_1)
											.addGap(18)
											.addComponent(bookAuthorTxt, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_3)
											.addGap(18)
											.addComponent(bookPriceTxt, 105, 105, 105))))
								.addComponent(bookTypeCb, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))))
					.addGap(65))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(bookAuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(manJrb)
						.addComponent(femalJrb)
						.addComponent(label_2)
						.addComponent(label_3)
						.addComponent(bookPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookTypeCb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(61))
		);
		getContentPane().setLayout(groupLayout);

		fillBookType();
	}
	private void bookAddActionPerformed(ActionEvent e) {
		String bookName=bookNameTxt.getText();
		String bookAuthor=bookAuthorTxt.getText();
		String bookPrice=bookPriceTxt.getText();
		String bookDesc=bookDescTxt.getText();
		
		if(StringUtil.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(this, "书名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(bookAuthor)) {
			JOptionPane.showMessageDialog(this, "作者不能为空！");
			return;
		}
		if(StringUtil.isEmpty(bookPrice)) {
			JOptionPane.showMessageDialog(this, "价格不能为空！");
			return;
		}
		float price;
		try {
			price=Float.parseFloat(bookPrice);
		}catch(Exception ee) {
			JOptionPane.showMessageDialog(this, "价格输入错误！");
			return;
		}
		
		String authorSex;
		if(manJrb.isSelected()) {
			authorSex="男";
		}else {
			authorSex="女";
		}
		
		BookType bookTypeItem=(BookType) bookTypeCb.getSelectedItem();
		int bookTypeId=bookTypeItem.getId();
		
		Book book=new Book(bookName,bookAuthor,authorSex,price,bookTypeId,bookDesc);
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			int result=bookDao.add(conn, book);
			if(result!=1) {
				JOptionPane.showMessageDialog(this, "添加失败！");
				return;
			}
			resultValue();
		} catch (Exception e2) {
			e2.printStackTrace();
		}finally {
			try {
				dbUtil.close(conn);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, "添加失败！");
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 重置表单数据
	 */
	private void resultValue() {
		bookNameTxt.setText("");
		bookAuthorTxt.setText("");
		femalJrb.setSelected(true);
		bookPriceTxt.setText("");
		bookDescTxt.setText("");
		if(bookTypeCb.getItemCount()>0)
			bookTypeCb.setSelectedIndex(0);
	}

	/**
	 * 初始化图书类别组合框
	 */
	private void fillBookType() {
		bookTypeCb.removeAllItems();
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			ResultSet rs=bookTypeDao.list(conn, new BookType());

			while(rs.next()) {
				BookType bookTypeItem=new BookType();
				bookTypeItem.setId(rs.getInt("id"));
				bookTypeItem.setBookTypeName(rs.getString("bookTypeName"));
				bookTypeCb.addItem(bookTypeItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.close(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
