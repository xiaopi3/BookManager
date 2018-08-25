package com.xiaopi3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table =null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u57FA\u672C\u6570\u636E\u7EF4\u62A4");
		mnNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/base.png")));
		menuBar.add(mnNewMenu);
		
		JMenu menu_1 = new JMenu("\u56FE\u4E66\u7C7B\u522B\u7BA1\u7406");
		menu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/bookTypeManager.png")));
		mnNewMenu.add(menu_1);
		
		JMenuItem mntmAdd = new JMenuItem("add");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeAddInterFrm btaif=new BookTypeAddInterFrm();
				btaif.setVisible(true);
				table.add(btaif);
			}
		});
		mntmAdd.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		menu_1.add(mntmAdd);
		
		JMenuItem mntmUpdate = new JMenuItem("update");
		mntmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeManageInterFrm btamif=new BookTypeManageInterFrm();
				btamif.setVisible(true);
				table.add(btamif);
			}
		});
		mntmUpdate.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		menu_1.add(mntmUpdate);
		
		JMenu menu_2 = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		menu_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/bookManager.png")));
		mnNewMenu.add(menu_2);
		
		JMenuItem mntmAdd_1 = new JMenuItem("add");
		mntmAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAddInterFrm baif=new BookAddInterFrm();
				baif.setVisible(true);
				table.add(baif);
			}
		});
		mntmAdd_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		menu_2.add(mntmAdd_1);
		
		JMenuItem mntmUpdate_1 = new JMenuItem("update");
		mntmUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookManageInterFrm bmif=new BookManageInterFrm();
				bmif.setVisible(true);
				table.add(bmif);
			}
		});
		mntmUpdate_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		menu_2.add(mntmUpdate_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "are you ok?","see you",JOptionPane.YES_NO_OPTION);
				if(result==0)
					dispose();
			}
		});
		mntmExit.setIcon(new ImageIcon(MainFrm.class.getResource("/images/exit.png")));
		mnNewMenu.add(mntmExit);
		
		JMenu menu = new JMenu("\u5173\u4E8E");
		menu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/about.png")));
		menuBar.add(menu);
		
		JMenuItem mntmXiaopi = new JMenuItem("xiaopi3");
		mntmXiaopi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xiaopi3InterFrm xif=new xiaopi3InterFrm();
				xif.setVisible(true);
				table.add(xif);
			}
		});
		mntmXiaopi.setIcon(new ImageIcon(MainFrm.class.getResource("/images/me.png")));
		menu.add(mntmXiaopi);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JDesktopPane();
		table.setBackground(new Color(255, 240, 245));
		contentPane.add(table, BorderLayout.CENTER);
		
		//设置当前窗体最大化扩展状态
		this.setExtendedState(MAXIMIZED_BOTH);
	}

}
