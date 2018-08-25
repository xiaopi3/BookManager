package com.xiaopi3.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.xiaopi3.dao.BookDao;
import com.xiaopi3.dao.BookTypeDao;
import com.xiaopi3.model.BookType;
import com.xiaopi3.util.DbUtil;
import com.xiaopi3.util.StringUtil;

public class BookTypeManageInterFrm extends JInternalFrame {
	private JTextField s_bookTypeNameTxt;
	private JTable bookTypeTable;
	
	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private BookDao bookDao=new BookDao();
	private JTextField bookTypeIdTxt;
	private JTextField bookTypeNameTxt;
	private JTextArea bookTypeDescTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageInterFrm frame = new BookTypeManageInterFrm();
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
	public BookTypeManageInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u7BA1\u7406");
		setBounds(100, 100, 415, 549);
		
		JLabel label = new JLabel("\u7C7B\u522B\u540D\u79F0\uFF1A");
		
		s_bookTypeNameTxt = new JTextField();
		s_bookTypeNameTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/search.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button)))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		
		JLabel label_1 = new JLabel("\u7F16\u53F7\uFF1A");
		
		bookTypeIdTxt = new JTextField();
		bookTypeIdTxt.setEditable(false);
		bookTypeIdTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7C7B\u522B\uFF1A");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("\u63CF\u8FF0\uFF1A");
		
		bookTypeDescTxt = new JTextArea();
		bookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185)));
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdateActionEvent(e);
			}
		});
		button_1.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/edit.png")));
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionEvent(e);
			}
		});
		button_2.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookTypeIdTxt, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookTypeNameTxt, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookTypeDescTxt, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(50)
							.addComponent(button_1)
							.addGap(18)
							.addComponent(button_2)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(bookTypeIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button_2))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bookTypeTableMouseTyped(e);
			}
		});
		bookTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "\u7C7B\u522B", "\u63CF\u8FF0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new BookType());
	}
	/**
	 * 图书类别删除事件处理
	 * @param e
	 */
	private void bookTypeDeleteActionEvent(ActionEvent evt) {
		int bookTypeId;
		try {
			bookTypeId=Integer.parseInt(bookTypeIdTxt.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "请选择要修改的记录！");
			return;
		}
		int result=JOptionPane.showConfirmDialog(this, "确定删除？", "警告", JOptionPane.YES_NO_OPTION);
		if(result==0) {
			Connection conn=null;
			try {
				conn=dbUtil.getConn();
				boolean flag=bookDao.existBookByBookTypeId(conn, bookTypeId);
				if(flag){
					JOptionPane.showMessageDialog(this, "当前类别下有图书，无法删除！");
					return;
				}
				int deleteResult=bookTypeDao.delete(conn, bookTypeId);
				if(deleteResult==1) {
					JOptionPane.showMessageDialog(this, "删除成功！");
					resetValue();
					fillTable(new BookType());
				}else
					JOptionPane.showMessageDialog(this, "删除失败！");
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "删除失败！");
			}finally {
				try {
					dbUtil.close(conn);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
			
		
	}
	/**
	 * 图书类别更新事件处理
	 * @param evt
	 */
	private void bookTypeUpdateActionEvent(ActionEvent evt) {
		int bookTypeId;
		try {
			bookTypeId=Integer.parseInt(bookTypeIdTxt.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "请选择要修改的记录！");
			return;
		}
		
		String bookTypeName=bookTypeNameTxt.getText();
		if(StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(this, "图书类别不能为空！");
			return;
		}
		String bookTypeDesc=bookTypeDescTxt.getText();
		
		BookType bookType=new BookType(bookTypeId,bookTypeName,bookTypeDesc);
		Connection conn=null;
		try {
			conn = dbUtil.getConn();
			int result=bookTypeDao.update(conn, bookType);
			if(result==1) {
				JOptionPane.showMessageDialog(this, "修改成功！");
				resetValue();
				fillTable(new BookType());
			}
			else {
				JOptionPane.showMessageDialog(this, "修改失败！");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "修改失败！");
			e.printStackTrace();
		}finally {
			try {
				dbUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	/**
	 * 表格行点击事件处理
	 * @param e
	 */
	private void bookTypeTableMouseTyped(MouseEvent e) {
		int row=bookTypeTable.getSelectedRow();
		bookTypeIdTxt.setText(((Integer)bookTypeTable.getValueAt(row, 0)).toString());
		bookTypeNameTxt.setText((String)bookTypeTable.getValueAt(row, 1));
		bookTypeDescTxt.setText((String)bookTypeTable.getValueAt(row, 2));
	}

	private void bookTypeSearchActionPerformed(ActionEvent evt) {
		String s=s_bookTypeNameTxt.getText();
		BookType bookType=new BookType();
		bookType.setBookTypeName(s);
		fillTable(bookType);
	}
	/**
	 * 条件查询表格
	 * @param bookType 查询条件
	 */
	public void fillTable(BookType bookType) {
		 DefaultTableModel dtm= (DefaultTableModel) bookTypeTable.getModel();//先获取表格模式
		//清空表格
		 dtm.setRowCount(0);
		 Connection conn=null;
		 try {
			conn=dbUtil.getConn();
			ResultSet rs=bookTypeDao.list(conn, bookType);
			//添加数据到表格，采用先resultset--vector--dtm
			while(rs.next()) {
				Vector v=new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			
		}finally {
			try {
				dbUtil.close(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	/**
	 * 重置表单数据
	 */
	public void resetValue() {
		bookTypeIdTxt.setText("");
		bookTypeNameTxt.setText("");
		bookTypeDescTxt.setText("");
	}
}
