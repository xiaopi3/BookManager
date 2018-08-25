package com.xiaopi3.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.xiaopi3.dao.BookDao;
import com.xiaopi3.dao.BookTypeDao;
import com.xiaopi3.model.Book;
import com.xiaopi3.model.BookType;
import com.xiaopi3.util.DbUtil;
import com.xiaopi3.util.StringUtil;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookManageInterFrm extends JInternalFrame {
	private JTextField s_bookNameTxt;
	private JTextField s_authorTxt;
	private JTable table;
	private JTextField bookIdTxt;
	private JTextField bookNameTxt;
	private JTextField bookPriceTxt;
	private JTextField bookAuthorTxt;
	private JComboBox s_bookTypeNameCb;
	private JComboBox u_bookTypeNameCb;
	private JRadioButton bookAuthorSexManRb;
	private JRadioButton bookAuthorSexFemalRb;
	private JTextArea bookDescTxt;
	
	private DbUtil dbUtil=new DbUtil();
	private BookDao bookDao=new BookDao();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManageInterFrm frame = new BookManageInterFrm();
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
	public BookManageInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7BA1\u7406");
		setBounds(100, 100, 658, 566);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("搜索条件"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder("表单操作"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		
		JLabel label_1 = new JLabel("\u7F16\u53F7\uFF1A");
		
		bookIdTxt = new JTextField();
		bookIdTxt.setEditable(false);
		bookIdTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("\u540D\u79F0\uFF1A");
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("\u6027\u522B\uFF1A");
		
		JLabel label_4 = new JLabel("\u4EF7\u683C\uFF1A");
		
		bookPriceTxt = new JTextField();
		bookPriceTxt.setColumns(10);
		
		bookAuthorSexManRb = new JRadioButton("\u7537");
		buttonGroup.add(bookAuthorSexManRb);
		
		bookAuthorSexFemalRb = new JRadioButton("\u5973");
		buttonGroup.add(bookAuthorSexFemalRb);
		
		JLabel label_5 = new JLabel("\u4F5C\u8005\uFF1A");
		
		bookAuthorTxt = new JTextField();
		bookAuthorTxt.setColumns(10);
		
		JLabel label_6 = new JLabel("\u7C7B\u522B\uFF1A");
		
		u_bookTypeNameCb = new JComboBox();
		
		JLabel label_7 = new JLabel("\u63CF\u8FF0\uFF1A");
		
		bookDescTxt = new JTextArea();
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1));
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateValueActionPerformed(e);
				fillTable();
			}
		});
		button_1.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/edit.png")));
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteValueActionPerformed(e);
				fillTable();
			}
		});
		button_2.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_1)
											.addGap(18)
											.addComponent(bookIdTxt, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
											.addGap(34)
											.addComponent(label_2))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(54)
											.addComponent(bookAuthorTxt, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(label_3)))
									.addGap(18)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(bookAuthorSexManRb)
											.addGap(18)
											.addComponent(bookAuthorSexFemalRb)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(label_6))
										.addComponent(bookNameTxt, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(label_4)
											.addGap(18)
											.addComponent(bookPriceTxt, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
										.addComponent(u_bookTypeNameCb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_7)
									.addGap(18)
									.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(94)
							.addComponent(button_1)
							.addGap(75)
							.addComponent(button_2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_5)))
					.addGap(26))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(bookIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(label_4)
						.addComponent(bookPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_5)
								.addComponent(bookAuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3)
								.addComponent(bookAuthorSexManRb)
								.addComponent(bookAuthorSexFemalRb)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_6)
								.addComponent(u_bookTypeNameCb, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(button_2)
						.addComponent(button_1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fillValue();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u4E66\u540D", "\u4F5C\u8005", "\u6027\u522B", "\u4EF7\u683C", "\u63CF\u8FF0", "\u7C7B\u522B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(86);
		table.getColumnModel().getColumn(3).setPreferredWidth(48);
		table.getColumnModel().getColumn(4).setPreferredWidth(62);
		table.getColumnModel().getColumn(5).setPreferredWidth(155);
		table.getColumnModel().getColumn(6).setPreferredWidth(110);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		
		s_bookNameTxt = new JTextField();
		s_bookNameTxt.setColumns(10);
		
		JLabel label = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		
		s_authorTxt = new JTextField();
		s_authorTxt.setColumns(10);
		
		s_bookTypeNameCb = new JComboBox();
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookListActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/search.png")));
		
		JLabel label_8 = new JLabel("\u7C7B\u522B\uFF1A");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_authorTxt, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(16)
					.addComponent(label_8)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookTypeNameCb, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(button)
						.addComponent(s_bookTypeNameCb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		fillBookType("search");
		fillBookType("select");
		fillTable();
	}
	private void deleteValueActionPerformed(ActionEvent e) {
		int id=-1;
		try {
			id=Integer.parseInt(bookIdTxt.getText());	
		}catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "请选择一项删除！");
			return;
		}
		Book book=new Book();
		book.setId(id);
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			int result=bookDao.delete(conn, book);
			if(result!=1) {
				JOptionPane.showMessageDialog(this, "更新失败！");
				return;
			}
		}catch(Exception e3) {
			e3.printStackTrace();
			JOptionPane.showMessageDialog(this, "更新失败！");
		}finally {
			try {
				dbUtil.close(conn);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 修改图书事件
	 * @param e
	 */
	private void updateValueActionPerformed(ActionEvent e) {
		int id=-1;
		try {
			id=Integer.parseInt(bookIdTxt.getText());			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "请选择一项更改！");
			return;
		}
		String bookName=bookNameTxt.getText();
		
		float bookPrice=-1;
		try {
			bookPrice=Float.parseFloat(bookPriceTxt.getText());			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "价格不能为空或填写错误！");
			return;
		}
		String author=bookAuthorTxt.getText();
		String sex;
		if(bookAuthorSexManRb.isSelected())
			sex="男";
		else
			sex="女";
		BookType bookType;
		bookType=(BookType) u_bookTypeNameCb.getSelectedItem();
		Integer bookTypeId=bookType.getId();
		String bookDesc=bookDescTxt.getText();
		if(StringUtil.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(this, "书名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(author)) {
			JOptionPane.showMessageDialog(this, "作者不能为空！");
			return;
		}

		
		Book book=new Book(id,bookName,author,sex,bookPrice,bookTypeId,bookDesc);
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			int result=bookDao.update(conn, book);
			if(result!=1) {
				JOptionPane.showMessageDialog(this, "更新失败！");
				return;
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "更新失败！");
			e2.printStackTrace();
		}finally {
			try {
				dbUtil.close(conn);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		resetValue();
	}

	/**
	 *表格的填充
	 */
	private void bookListActionPerformed(ActionEvent e) {
		fillTable();
	}
	private void fillTable() {
		Connection conn=null;
		DefaultTableModel dftm=(DefaultTableModel) table.getModel();
		dftm.setRowCount(0);
		try {
			Book book=new Book();
			conn=dbUtil.getConn();
			BookType bookType=(BookType)s_bookTypeNameCb.getSelectedItem();
			int bookTypeId=bookType.getId();
			book.setBookTypeId(bookTypeId);
			String bookName=s_bookNameTxt.getText().trim();
			String author=s_authorTxt.getText().trim();
			if(StringUtil.isNotEmpty(bookName)) {
				book.setBookName(bookName);
			}
			if(StringUtil.isNotEmpty(author)) {
				book.setAuthor(author);
			}
			
			ResultSet rs=bookDao.list(conn,book );
			
			while(rs.next()) {
				Book bookItem=new Book();
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("sex"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeName"));
				dftm.addRow(v);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			try {
				dbUtil.close(conn);
			} catch (SQLException e2) {
				
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 下拉列表的填充
	 * @param type
	 */
	private void fillBookType(String type) {
		
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			ResultSet rs=bookTypeDao.list(conn, new BookType());
			BookType bookTypeItem=new BookType();
			if("search".equals(type)) {
				
				bookTypeItem.setBookTypeName("请选择...");
				bookTypeItem.setId(-1);
				s_bookTypeNameCb.addItem(bookTypeItem);//添加object后，对其的修改会同步到下拉列表中！！
				while(rs.next()) {
					bookTypeItem=new BookType();//必须创建新的对象，否则会更改原来添加的项目
					bookTypeItem.setBookTypeName(rs.getString("bookTypeName"));
					bookTypeItem.setId(rs.getInt("id"));
					s_bookTypeNameCb.addItem(bookTypeItem);
				}
			}
			if("select".equals(type)) {
				while(rs.next()) {
					bookTypeItem=new BookType();
					bookTypeItem.setBookTypeName(rs.getString("bookTypeName"));
					bookTypeItem.setId(rs.getInt("id"));
					u_bookTypeNameCb.addItem(bookTypeItem);
				}
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
	/**
	 * 操作选项填充
	 */
	private void fillValue() {
		bookIdTxt.setText((String)table.getValueAt(table.getSelectedRow(), 0));
		bookNameTxt.setText((String)table.getValueAt(table.getSelectedRow(), 1));
		bookAuthorTxt.setText((String)table.getValueAt(table.getSelectedRow(), 2));
		
		String sex=(String)table.getValueAt(table.getSelectedRow(), 3);
		
		if("男".equals(sex)) {
			bookAuthorSexManRb.setSelected(true);
		}else {
			bookAuthorSexFemalRb.setSelected(true);
		}
		
		bookPriceTxt.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
		bookDescTxt.setText((String)table.getValueAt(table.getSelectedRow(), 5));
		
		String bookTypeName=(String)table.getValueAt(table.getSelectedRow(), 6);
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			BookType bookType=new BookType();
			bookType.setBookTypeName(bookTypeName);
			ResultSet rs=bookTypeDao.list(conn, bookType);
			rs.next();
			int id=rs.getInt("id");
			bookType.setId(id);
			u_bookTypeNameCb.setSelectedItem(bookType);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.close(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	private void resetValue() {
		bookIdTxt.setText("");
		bookNameTxt.setText("");
		bookPriceTxt.setText("");
		bookAuthorTxt.setText("");
		bookAuthorSexFemalRb.setSelected(false);
		bookAuthorSexManRb.setSelected(false);
		bookDescTxt.setText("");
	}
}
