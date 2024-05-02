package com.itwill.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RentalCheckFrame extends JFrame {
	private static final String[] COLUMN_NAMES = {
			"대관 장소", "날짜", "시간"
	};

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel searchPanel;
	private JPanel buttonPanel;
	private JLabel lblNewLabel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void showRentalCheckFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalCheckFrame frame = new RentalCheckFrame();
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
	public RentalCheckFrame() {
		initialize();
	}
	public void initialize() {
		setTitle("예약 내역 확인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		searchPanel = new JPanel();
		contentPane.add(searchPanel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("내역 확인");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 24));
		searchPanel.add(lblNewLabel);
		
		buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		btnConfirm = new JButton("확인");
		btnConfirm.addActionListener((e) -> dispose());
		btnConfirm.setFont(new Font("D2Coding", Font.PLAIN, 24));
		buttonPanel.add(btnConfirm);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener((e) -> dispose());
		btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 24));
		buttonPanel.add(btnCancel);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
	}

}
