package com.itwill.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;

public class RentalManagerFrame extends JFrame {
	private static final String[] COLUMN_NAMES = {
			"이름", "내용", "예약 시간"
	};

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDetail;
	private JButton btnSave;
	private JButton btnCancel;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void showRentalManagerFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalManagerFrame frame = new RentalManagerFrame();
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
	public RentalManagerFrame() {
		initialize();
	}
	public void initialize() {
		setTitle("관리자 페이지");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnDetail = new JButton("상세보기");
		btnDetail.setFont(new Font("D2Coding", Font.PLAIN, 18));
		panel.add(btnDetail);
		
		btnSave = new JButton("확인");
		btnSave.setFont(new Font("D2Coding", Font.PLAIN, 18));
		panel.add(btnSave);
		
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 18));
		panel.add(btnCancel);
	}

}
