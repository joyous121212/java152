package com.itwill.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itwill.project.controller.RentalDao;
import com.itwill.project.model.Rental;
import com.itwill.project.model.RentalInfo;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;

public class RentalCheckFrame extends JFrame {
	private static final String[] COLUMN_NAMES = {
			"날짜", "시간"
	};

	private static final long serialVersionUID = 1L;
	
	private String frameImage = "image/background.jpg";
	
	private RentalDao dao = RentalDao.getInstance();
	private Component parent;
	
	private String email;
	private String pw;
	private int rentalId;
	
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnConfirm;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JTextField textApproval;
	private JLabel lblApproval;
	private JLabel lblContent;
	private JTextArea textAreaContent;
	private JScrollPane scrollPaneContent;
	private JLabel lblImage;

	/**
	 * Launch the application.
	 */
	public static void showRentalCheckFrame(Component parent, String email, String pw, int rentalId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalCheckFrame frame = new RentalCheckFrame(parent, email, pw, rentalId);
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
	public RentalCheckFrame(Component parent, String email, String pw, int rentalId) {
		this.parent = parent;
		this.email = email;
		this.pw = pw;
		this.rentalId = rentalId;
		
		
		initialize();
		initializeRentalInfo();
	}
	


	public void initialize() {
		setTitle("예약 내역 확인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x, y, 530, 570);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 44, 504, 250);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.getTableHeader().setFont(new Font("D2Coding", Font.PLAIN, 20));
		table.setFont(new Font("D2Coding", Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setBackground(SystemColor.menu);
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		textApproval = new JTextField();
		textApproval.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textApproval.setColumns(10);
		textApproval.setBounds(134, 436, 375, 35);
		contentPane.add(textApproval);
		
		lblApproval = new JLabel("승인 여부");
		lblApproval.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblApproval.setBounds(5, 436, 117, 33);
		contentPane.add(lblApproval);
		
		lblContent = new JLabel("내용");
		lblContent.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblContent.setBounds(5, 304, 117, 33);
		contentPane.add(lblContent);
		
		scrollPaneContent = new JScrollPane();
		scrollPaneContent.setBounds(134, 304, 375, 122);
		contentPane.add(scrollPaneContent);
		
		textAreaContent = new JTextArea();
		textAreaContent.setFont(new Font("D2Coding", Font.PLAIN, 20));
		scrollPaneContent.setViewportView(textAreaContent);
		
		lblNewLabel = new JLabel("내역 확인");
		lblNewLabel.setBounds(196, 10, 108, 29);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 24));
		
		btnConfirm = new JButton("확인");
		btnConfirm.setBounds(421, 481, 81, 37);
		contentPane.add(btnConfirm);
		btnConfirm.addActionListener((e) -> dispose());
		btnConfirm.setFont(new Font("D2Coding", Font.PLAIN, 20));
		
		lblImage = new JLabel(new ImageIcon(frameImage));
		lblImage.setBackground(SystemColor.menu);
		lblImage.setBounds(0, 0, 514, 531);
		contentPane.add(lblImage);
	}
	
	private void initializeRentalInfo() {
		List<RentalInfo> rentalInfo = new ArrayList<RentalInfo>();
		rentalInfo = dao.searchDateTime(email, pw);
		if (rentalInfo == null) return;
		
		for (RentalInfo r : rentalInfo) {
			Object[] row = {
					r.getDate(),
					r.getTime()
			};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
		
		Rental rental = new Rental();
		rental = dao.read(rentalId);
		System.out.println(rental);
		String approval = rental.getApproval();
		
		System.out.println(approval);
		System.out.println(approval == null);
		
			if (approval == null) {
				textApproval.setText("미확정");
			} else if (approval.equals("승인")) {
				textApproval.setText("승인되었습니다");
			} else if (approval.equals("승인")) {
				textApproval.setText("승인되었습니다.");
			}
		
		String content = rental.getContent();
		textAreaContent.setText(content);
	}
}
