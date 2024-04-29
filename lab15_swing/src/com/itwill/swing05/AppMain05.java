package com.itwill.swing05;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;

public class AppMain05 {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldPhone;
	private JTextField textFieldEmail;
	private JLabel lblName;
	private JLabel lbPhone;
	private JLabel lblEmail;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTextArea textResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain05 window = new AppMain05();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppMain05() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 685, 529);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblName.setBounds(56, 57, 119, 37);
		frame.getContentPane().add(lblName);
		
		lbPhone = new JLabel("전화번호");
		lbPhone.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lbPhone.setBounds(56, 114, 119, 37);
		frame.getContentPane().add(lbPhone);
		
		lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblEmail.setBounds(56, 177, 119, 37);
		frame.getContentPane().add(lblEmail);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("D2Coding", Font.PLAIN, 18));
		textFieldName.setBounds(187, 57, 285, 37);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setFont(new Font("D2Coding", Font.PLAIN, 18));
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(187, 122, 285, 37);
		frame.getContentPane().add(textFieldPhone);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("D2Coding", Font.PLAIN, 18));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(187, 185, 285, 37);
		frame.getContentPane().add(textFieldEmail);
		
		btnNewButton = new JButton("입력");
		btnNewButton.setFont(new Font("D2Coding", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() { // 익명 내부 클래스
			@Override
			public void actionPerformed(ActionEvent e) {
				handleInputButtonClick();
			}
		});
		btnNewButton.setBounds(484, 185, 119, 37);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 266, 547, 129);
		frame.getContentPane().add(scrollPane);
		
		textResult = new JTextArea();
		textResult.setFont(new Font("D2Coding", Font.PLAIN, 18));
		scrollPane.setViewportView(textResult);
	}
	
	private void handleInputButtonClick() {
		// JTextField(이름, 전화번호, 이메일)에 입력된 문자열을 읽음.
		String name = textFieldName.getText();
		String phone = textFieldPhone.getText();
		String email = textFieldEmail.getText();
		
		String msg = String.format("이름:%s, 전화번호:%s, 이메일:%s\n", name, phone, email);
		
		// 이름, 전화번호, 이메일을 JTextArea에 씀.
//		textResult.setText(msg);
		textResult.append(msg); // 기존에 작성된 내용 끝에 추가.
		
		// 모든 JTextField의 입력된 내용을 지움.
		textFieldName.setText("");
		textFieldPhone.setText("");
		textFieldEmail.setText("");
		
	}
}
