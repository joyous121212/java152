package com.itwill.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itwill.project.controller.RentalDao;
import com.itwill.project.model.Rental;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

public class RentalManagerFrame extends JFrame {
	private static final String[] COLUMN_NAMES = {
			"번호", "이름", "장르", "내용", "예약 시간"
	};
	
	private String frameImage = "image/background.jpg";

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDetail;
	private JButton btnSave;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel tableModel;
	
	private RentalDao dao = RentalDao.getInstance();
	private JButton btnDelete;
	private JLabel lblImage;
	
	private Component parent;

	/**
	 * Launch the application.
	 */
	public static void showRentalManagerFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalManagerFrame frame = new RentalManagerFrame(parent);
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
	public RentalManagerFrame(Component parent) {
		this.parent = parent;
		
		initialize();
		initializeTable();
	}
	public void initialize() {
		setTitle("관리자 페이지");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x, y, 477, 547);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 452, 428);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.getTableHeader().setFont(new Font("D2Coding", Font.PLAIN, 20));
		table.setFont(new Font("D2Coding", Font.PLAIN, 20));
		table.setRowHeight(25);
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		btnDetail = new JButton("상세보기");
		btnDetail.setBackground(new Color(226, 217, 200));
		btnDetail.setBounds(94, 443, 105, 46);
		contentPane.add(btnDetail);
		btnDetail.addActionListener((e) -> showDetailsFrame());
		btnDetail.setFont(new Font("D2Coding", Font.PLAIN, 15));
		
		btnDelete = new JButton("삭제");
		btnDelete.setBackground(new Color(226, 217, 200));
		btnDelete.setBounds(211, 443, 69, 46);
		contentPane.add(btnDelete);
		btnDelete.addActionListener((e) -> deleteRental());
		btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 15));
		
		btnSave = new JButton("확인");
		btnSave.addActionListener((e) -> dispose());
		btnSave.setBackground(new Color(226, 217, 200));
		btnSave.setBounds(292, 443, 69, 46);
		contentPane.add(btnSave);
		btnSave.setFont(new Font("D2Coding", Font.PLAIN, 15));
		
		
		lblImage = new JLabel(new ImageIcon(frameImage));
		lblImage.setBounds(0, 0, 470, 508);
		contentPane.add(lblImage);
	}
	
	private void showDetailsFrame() {
		int index = table.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(contentPane, "상세보기할 행을 먼저 선택하세요.", "경고", JOptionPane.WARNING_MESSAGE);
			
			return;
		}
		Integer id = (Integer) tableModel.getValueAt(index, 0);
		RentalManagerDetailsFrame.showRentalManagerDetailsFrame(RentalManagerFrame.this, id);
	}

	private void deleteRental() {
		int index = table.getSelectedRow(); // 테이블에서 선택된 행의 인덱스
		if(index == -1) {
			JOptionPane.showMessageDialog(contentPane, "삭제할 행을 먼저 선택하세요.", "경고", JOptionPane.WARNING_MESSAGE);;
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(contentPane, "정말 삭제할까요?", "삭제 확인", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			// 선택된 행에서 블로그 번호(id)를 읽음.
			Integer id = (Integer) tableModel.getValueAt(index, 0);
			// TODO: DAO의 delete 메서드 호출.
			int result = dao.delete(id);
			if (result == 1) {
				initializeTable();
				JOptionPane.showMessageDialog(contentPane, "삭제 성공!");
			} else {
				JOptionPane.showMessageDialog(contentPane, "삭제 실패!");
			}
		}
		
	}

	private void initializeTable() {
		// DAO를 사용해서 DB테이블에서 검색.
		List<Rental> rentals = dao.read();
		resetTable(rentals);
	}

	private void resetTable(List<Rental> rentals) {
		
		// 검색한 내용을 JTable에 보여줌 - JTable의 테이블 모델을 재설정.
		tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 테이블 모델 리셋
		for(Rental r : rentals) {
			// DB 테이블에서 검색한 레코드를 JTable에서 사용할 행 데이터로 변환.
			Object[] row = {
					r.getId(),
					r.getName(),
					r.getGenre(),
					r.getContent(),
					r.getCreatedTime(),
			};
			tableModel.addRow(row); // 테이블 모델에 행 데이터를 추가.
		}
		table.setModel(tableModel);
	}

}
