package com.itwill.project.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import com.itwill.project.controller.RentalDao;
import com.itwill.project.model.Rental;
import com.itwill.project.model.RentalInfo;
import com.itwill.project.view.RentalDetailFrame.CreateNotify;

public class RentalCalendar  {
	


	
	private static JLabel lblTime;
	private static com.itwill.project.view.Calendarmain calendarmain;

	

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void showSwingCalendar() {
		calendarmain = new Calendarmain();
		calendarmain.setBounds(100, 100, 900, 400);
		calendarmain.setTitle("날짜 및 시간 선택");
		calendarmain.getContentPane().setLayout(null);
		
		lblTime = new JLabel("예약 시간");
		lblTime.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblTime.setBounds(536, 10, 62, 43);
		calendarmain.getContentPane().add(lblTime);
		
	}



	



}

class Calendarmain extends JFrame implements ActionListener {

	private static final String[] SEARCH_TYPES = {
			"오전", "오후", "야간"
	};
	
	private static final String[] COLUMN_NAMES = {
			"날짜", "시간"
	};
	
	private RentalDao dao = RentalDao.getInstance();
	
	Container container = getContentPane();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	JTable table = new JTable();
	DefaultTableModel tableModel = new DefaultTableModel();
	JScrollPane scrollPane = new JScrollPane();
	
	
	JButton btnAdd = new JButton();
	JButton btnBefore = new JButton("Before");
	JButton btnAfter = new JButton("After");
	JButton btnConfirm = new JButton("완료");
	JButton btnCancel = new JButton("취소");
	
	JComboBox<String> comboBox = new JComboBox<String>();
	
	
	
	public JLabel label = new JLabel("00년 0월");
	
	public JToggleButton[] buttons = new JToggleButton[49];
	String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
	CalendarFunction cf = new CalendarFunction();
	
	public Calendarmain() {
		setTitle("달력");
		setSize(550, 400);
		setLocation(400, 400);
		init();
		start();
		setVisible(true);
	}
	
	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		container.setLayout(new BorderLayout());
		container.add("North", panel1);
		container.add("Center", panel2);
		
		panel1.setLayout(new FlowLayout());
		panel1.add(btnBefore);
		panel1.add(label);
		panel1.add(btnAfter);
		
		Font font = new Font("D2Coding", Font.BOLD, 18);
		btnAfter.setFont(font);
		btnBefore.setFont(font);
		label.setFont(font);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener((e) -> addNewDateTime());
		btnAdd.setBounds(780, 20, 97, 23);
		container.add(btnAdd);
		
		comboBox = new JComboBox<>();
		final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(SEARCH_TYPES);
		comboBox.setModel(comboBoxModel);
		comboBox.setBounds(610, 20, 145, 23);
		container.add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(543, 51, 323, 242);
		container.add(scrollPane);
		
		table = new JTable();
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		label.setText(cf.getCalText());
		
		btnConfirm = new JButton("완료");
		btnConfirm.addActionListener((e) -> createRentalInfo());
		btnConfirm.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnConfirm.setBounds(656, 303, 97, 43);
		container.add(btnConfirm);
		
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnCancel.setBounds(767, 303, 97, 43);
		container.add(btnCancel);
		
		panel2 = new JPanel();
		panel2.setBounds(0, 0, 504, 322);
		container.add(panel2);
		
		System.out.println(buttons.toString());
		
		panel3.setLayout(new GridLayout(7, 7, 5, 5));
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JToggleButton();
			panel3.add(buttons[i]);
			
			buttons[i].setFont(font);
			
			if(i<7) buttons[i].setText(dayNames[i]);
			
			if(i%7 == 0) buttons[i].setForeground(Color.RED);
			if(i%7 == 6) buttons[i].setForeground(Color.BLUE);
		}
		cf.setButtons(buttons);
		cf.calSet();
		panel2.add(panel3);
		ButtonGroup  buttonGroup = new ButtonGroup();
		for (int i = 0; i < buttons.length; i++) {
			buttonGroup.add(buttons[i]);
		}
				
	}
	
	private void createRentalInfo() {
		// TODO RentalInfo 데이터에 저장
		String date = null;
		String time = null;
		
		RentalInfo rentalInfo = new RentalInfo(date, time);
		System.out.println((String) table.getValueAt(0, 0));
		int result = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			date = (String) table.getValueAt(i, 0);
			time = (String) table.getValueAt(i, 1);
			rentalInfo = new RentalInfo(date, time);
			result = dao.createRentalInfo(rentalInfo);			
		}
		if (result == 1) {
			JOptionPane.showMessageDialog(container, "예약 성공!");

			dispose();
		} else {
			JOptionPane.showMessageDialog(container, "예약 실패");
		}		
	}

	private void initializeTable() {
		List<RentalInfo> rentalInfo = dao.readInfo();
		resetTable(rentalInfo);
	}
	
	private void resetTable(List<RentalInfo> rentalInfo) {
		
	}
	
	
	// 선택 된 날짜, 시간 SQL 테이블에 없으면 JTable, SQL테이블 에 입력, 
	// 있으면 알림 메세지
	private void addNewDateTime() {
		String date = null;
		
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].isSelected()) {
				date = buttons[i].getText();				
			}
		}
		
		String dd = label.getText() + date + "일";
		
		String time = null;
		int type = comboBox.getSelectedIndex();
		if (type == 0) {
			time ="오전";
		} else if (type == 1) {
			time ="오후";
		} else if (type == 2) {
			time ="야간";
		}
		
		
		List<RentalInfo> rentalInfo = dao.searchDate(dd);
		List<RentalInfo> tt = dao.searchTime(time);
		
		// 날짜, 시간이 같은 경우
		
		for (RentalInfo r : rentalInfo) {
			if (r.getDate().equals(dd)) {
				if (r.getTime().equals(time)) {
					JOptionPane.showMessageDialog(container, "이미 마감되었습니다.");
					return;
				}
			} 
			
		}
		Object[] row = { dd, time };
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			if (dd.equals(tableModel.getValueAt(i, 0)) && time.equals(tableModel.getValueAt(i, 1))) {
				JOptionPane.showMessageDialog(container, "이미 선택되었습니다.");
				return;
			}
		}
		tableModel.addRow(row);
		
		System.out.println(dd.equals(tableModel.getValueAt(0, 0)));
				
		System.out.println(label.getText() + date + "일");
		System.out.println(dd);
		System.out.println(dao.searchDate(dd));
		System.out.println(time);
		System.out.println(dao.searchTime(time));

	}

	private void start() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		btnAfter.addActionListener(this);
		btnBefore.addActionListener(this);
		btnCancel.addActionListener((e) -> dispose());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int gap = 0;
		if(e.getSource() == btnAfter) {
			gap = 1;
		} else if (e.getSource() == btnBefore) {
			gap = -1;
		} 
		cf.allInit(gap);
		label.setText(cf.getCalText());
		
	}
	

	
}




