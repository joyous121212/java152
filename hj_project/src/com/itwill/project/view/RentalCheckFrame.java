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

public class RentalCheckFrame extends JFrame {
	private static final String[] COLUMN_NAMES = {
			"날짜", "시간"
	};

	private static final long serialVersionUID = 1L;
	
	private RentalDao dao = RentalDao.getInstance();
	private int rentalId;
	
	private JPanel contentPane;
	private JPanel searchPanel;
	private JPanel buttonPanel;
	private JLabel lblNewLabel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JTextField textApproval;
	private JLabel lblApproval;

	/**
	 * Launch the application.
	 */
	public static void showRentalCheckFrame(int rentalId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalCheckFrame frame = new RentalCheckFrame(rentalId);
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
	public RentalCheckFrame(int rentalId) {
		this.rentalId = rentalId;
		
		initialize();
		initializeRentalInfo();
	}
	


	public void initialize() {
		setTitle("예약 내역 확인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 570);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchPanel = new JPanel();
		searchPanel.setBackground(SystemColor.info);
		searchPanel.setBounds(5, 5, 504, 39);
		contentPane.add(searchPanel);
		
		lblNewLabel = new JLabel("내역 확인");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 24));
		searchPanel.add(lblNewLabel);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(5, 479, 504, 47);
		contentPane.add(buttonPanel);
		
		btnConfirm = new JButton("확인");
		btnConfirm.addActionListener((e) -> dispose());
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnConfirm.setFont(new Font("D2Coding", Font.PLAIN, 24));
		buttonPanel.add(btnConfirm);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener((e) -> dispose());
		btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 24));
		buttonPanel.add(btnCancel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 44, 504, 382);
		contentPane.add(scrollPane);
		
		table = new JTable();
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
	}
	
	private void initializeRentalInfo() {
		List<RentalInfo> rentalInfo = new ArrayList<RentalInfo>();
		rentalInfo = dao.readInfo(rentalId);
		System.out.println(rentalInfo);
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
		String approval = rental.getApproval();
		if (approval == "승인") {
			textApproval.setText("승인되었습니다.");
		} else if (approval == "불가"){
			textApproval.setText("승인불가입니다.");
		} else {
			textApproval.setText("미확정");
		}
	}
}
