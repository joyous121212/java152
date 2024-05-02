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
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;

public class RentalManagerFrame extends JFrame {
	private static final String[] COLUMN_NAMES = {
			"번호", "이름", "내용", "예약 시간"
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
	
	private RentalDao dao = RentalDao.getInstance();
	private JButton btnDelete;

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
		initializeTable();
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
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener((e) -> deleteRental());
		btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 18));
		panel.add(btnDelete);
		
		btnSave = new JButton("확인");
		btnSave.setFont(new Font("D2Coding", Font.PLAIN, 18));
		panel.add(btnSave);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener((e) -> dispose());
		btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 18));
		panel.add(btnCancel);
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
					r.getContent(),
					r.getCreatedTime(),
			};
			tableModel.addRow(row); // 테이블 모델에 행 데이터를 추가.
		}
		table.setModel(tableModel);
	}

}
