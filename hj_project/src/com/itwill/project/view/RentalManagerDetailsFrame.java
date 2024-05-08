package com.itwill.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.itwill.project.controller.RentalDao;
import com.itwill.project.model.Rental;
import com.itwill.project.model.RentalInfo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;

public class RentalManagerDetailsFrame extends JFrame {

	private static final String[] COLUMN_NAMES = {
			"날짜", "시간"
	};

	private String frameImage = "image/background.jpg";
	
	private static final long serialVersionUID = 1L;
	
	private RentalDao dao = RentalDao.getInstance();
	private int rentalId;
	
	private JPanel contentPane;
	private JTextField textName;
	private JLabel lblName;
	private JTextField textGenre;
	private JLabel lblGenre;
	private JLabel lblContent;
	private JScrollPane scrollPaneContent;
	private JTextArea textAreaContent;
	private JScrollPane scrollPaneDateTime;
	private JLabel lblDateTime;
	private JTextField textCreatedTime;
	private JLabel lblCreatedTime;
	private JRadioButton rdbtnReject;
	private JRadioButton rdbtnApproval;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnConfirm;
	private JLabel lblConfirm;
	private JLabel lblId;
	private JTextField textId;
	private JTable tableDateTime;
	private DefaultTableModel tableModel;
	private JButton btnDelete;
	private JButton btnCancel;
	private JLabel lblImage;
	
	private Component parent;

	/**
	 * Launch the application.
	 */
	public static void showRentalManagerDetailsFrame(Component parent, int rentalId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalManagerDetailsFrame frame = new RentalManagerDetailsFrame(parent, rentalId);
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
	public RentalManagerDetailsFrame(Component parent, int rentalId) {
		this.parent = parent;
		this.rentalId = rentalId;
		
		initialize();
		initializeInfo();
	}


	public void initialize() {
		setTitle("상세보기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX(); // 부모 컴포넌트의 x 좌표
			y = parent.getY(); // 부모 컴포넌트의 y 좌표
		}
		setBounds(x, y, 447, 838);
		
		if (parent == null) {
			setLocationRelativeTo(null); // 화면 중앙에서 JFrame을 띄움.
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblName.setBounds(8, 58, 87, 40);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setBackground(new Color(211, 198, 173));
		textName.setEditable(false);
		textName.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textName.setBounds(107, 58, 312, 40);
		contentPane.add(textName);
		textName.setColumns(10);
		
		lblGenre = new JLabel("장르");
		lblGenre.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblGenre.setBounds(8, 108, 87, 40);
		contentPane.add(lblGenre);
		
		textGenre = new JTextField();
		textGenre.setBackground(new Color(211, 198, 173));
		textGenre.setEditable(false);
		textGenre.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textGenre.setColumns(10);
		textGenre.setBounds(107, 108, 312, 40);
		contentPane.add(textGenre);
		
		lblContent = new JLabel("내용");
		lblContent.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblContent.setBounds(8, 158, 87, 40);
		contentPane.add(lblContent);
		
		scrollPaneContent = new JScrollPane();
		scrollPaneContent.setBounds(8, 208, 411, 142);
		contentPane.add(scrollPaneContent);
		
		textAreaContent = new JTextArea();
		textAreaContent.setFont(new Font("D2Coding", Font.PLAIN, 24));
		scrollPaneContent.setViewportView(textAreaContent);
		
		lblDateTime = new JLabel("대관 날짜 및 시간");
		lblDateTime.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblDateTime.setBounds(8, 360, 320, 40);
		contentPane.add(lblDateTime);
		
		scrollPaneDateTime = new JScrollPane();
		scrollPaneDateTime.setBounds(8, 410, 411, 142);
		contentPane.add(scrollPaneDateTime);
		
		tableDateTime = new JTable();
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		tableDateTime.setModel(tableModel);
		scrollPaneDateTime.setViewportView(tableDateTime);
		
		lblCreatedTime = new JLabel("신청시간");
		lblCreatedTime.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblCreatedTime.setBounds(8, 562, 153, 40);
		contentPane.add(lblCreatedTime);
		
		textCreatedTime = new JTextField();
		textCreatedTime.setBackground(new Color(211, 198, 173));
		textCreatedTime.setEditable(false);
		textCreatedTime.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textCreatedTime.setColumns(10);
		textCreatedTime.setBounds(8, 604, 411, 40);
		contentPane.add(textCreatedTime);
		
		lblConfirm = new JLabel("승인여부");
		lblConfirm.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblConfirm.setBounds(8, 654, 153, 40);
		contentPane.add(lblConfirm);
		
		rdbtnApproval = new JRadioButton("승인");
		rdbtnApproval.setBackground(new Color(226, 217, 200));
		buttonGroup.add(rdbtnApproval);
		rdbtnApproval.setFont(new Font("D2Coding", Font.PLAIN, 18));
		rdbtnApproval.setBounds(8, 700, 79, 23);
		contentPane.add(rdbtnApproval);
		
		rdbtnReject = new JRadioButton("불가");
		rdbtnReject.setBackground(new Color(226, 217, 200));
		buttonGroup.add(rdbtnReject);
		rdbtnReject.setFont(new Font("D2Coding", Font.PLAIN, 18));
		rdbtnReject.setBounds(95, 700, 79, 23);
		contentPane.add(rdbtnReject);
		
		btnConfirm = new JButton("업데이트");
		btnConfirm.setBackground(new Color(226, 217, 200));
		btnConfirm.addActionListener((e) -> updateRental());
		btnConfirm.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnConfirm.setBounds(167, 737, 132, 52);
		contentPane.add(btnConfirm);
		
		lblId = new JLabel("번호");
		lblId.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblId.setBounds(8, 10, 87, 40);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setBackground(new Color(211, 198, 173));
		textId.setEditable(false);
		textId.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textId.setColumns(10);
		textId.setBounds(107, 10, 312, 40);
		contentPane.add(textId);
		
		btnDelete = new JButton("삭제");
		btnDelete.setBackground(new Color(226, 217, 200));
		btnDelete.addActionListener((e) -> deleteDateTime());
		btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 24));
		btnDelete.setBounds(44, 737, 108, 52);
		contentPane.add(btnDelete);
		
		btnCancel = new JButton("취소");
		btnCancel.setBackground(new Color(226, 217, 200));
		btnCancel.addActionListener((e) -> dispose());
		btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 24));
		btnCancel.setBounds(311, 737, 108, 52);
		contentPane.add(btnCancel);
		
		lblImage = new JLabel(new ImageIcon(frameImage));
		lblImage.setBounds(0, 0, 431, 799);
		contentPane.add(lblImage);
	}
	
	private void deleteDateTime() {
		int index = tableDateTime.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(contentPane, "삭제할 행을 먼저 선택하세요.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(contentPane, "정말 삭제할까요?", "삭제 확인", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			String date = (String) tableModel.getValueAt(index, 0);
			String time = (String) tableModel.getValueAt(index, 1);
			
			// DAO 메서드 호출.
			int result = dao.deleteInfo(date, time);
			if (result == 1) {
				initializeInfo();
				JOptionPane.showMessageDialog(contentPane, "삭제 성공!");
			} else {
				JOptionPane.showMessageDialog(contentPane, "삭제 실패!");
			}
		}
	}
	
	private void resetTable(List<RentalInfo> rentalInfo) {
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		for(RentalInfo r : rentalInfo) {
			Object[] row = {
					r.getDate(),
					r.getTime()
			};
			tableModel.addRow(row);
		}
		tableDateTime.setModel(tableModel);
	}

	private void initializeInfo() {
		Rental rental = dao.read(rentalId);
		List<RentalInfo> rentalInfo = new ArrayList<RentalInfo>();
		rentalInfo = dao.readInfo(rentalId);
		
		
		if(rental == null) return;
		
		textId.setText(rentalId + "");
		textName.setText(rental.getName());
		textGenre.setText(rental.getGenre());
		textAreaContent.setText(rental.getContent());
		textCreatedTime.setText(rental.getCreatedTime().toString());
		
		resetTable(rentalInfo);
		
	}
	
	
	
	private void updateRental() {
		String content = textAreaContent.getText();
		String approval = null;
		
		if(rdbtnApproval.isSelected()) {
			approval = rdbtnApproval.getText();
		} else if(rdbtnReject.isSelected()) {
			approval = rdbtnReject.getText();
		} else {
			return;
		}
		System.out.println(approval);
		
		Rental rental = new Rental(rentalId, null, null, null, content, null, null, null, approval);
		
		System.out.println(rental);
		int result = dao.update(rental);
		System.out.println(result);
		if (result == 1) {
			JOptionPane.showMessageDialog(contentPane, "업데이트 성공!");
			dispose();
		} else {
			JOptionPane.showMessageDialog(contentPane, "업데이트 실패!");
		}
	}
}
