package com.itwill.swing04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppMain04 {

	private JFrame frame;
	private JTextField textField1;
	private JTextField textField2;
	private JTextArea textResult;
	private JButton btnAdd;
	private JButton btnSubtract;
	private JButton btnMultiply;
	private JButton btnDivision;
	private JLabel lblNum1;
	private JLabel lblNum2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain04 window = new AppMain04();
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
	public AppMain04() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 459, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNum1 = new JLabel("Number1");
		lblNum1.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblNum1.setBounds(12, 10, 114, 46);
		frame.getContentPane().add(lblNum1);
		
		lblNum2 = new JLabel("Number2");
		lblNum2.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblNum2.setBounds(12, 74, 114, 46);
		frame.getContentPane().add(lblNum2);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textField1.setBounds(138, 12, 287, 46);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textField2.setColumns(10);
		textField2.setBounds(138, 76, 287, 46);
		frame.getContentPane().add(textField2);
		
		btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleButtonClick(e);
				
//				System.out.println(e.getSource() == btnSubtract);
//				double i = 0;
//				double i2 = 0;
//				try {
//					i = Double.parseDouble(textField1.getText());
//					i2 = Double.parseDouble(textField2.getText());
//				} catch (NumberFormatException x) {
//					textResult.setText("Number1 또는 Number2에는 숫자를 입력하세요.");
//					return;
//				}
//				double result = i + i2;
//				String msg = String.format("%f + %f = %f", i, i2, result);
//
//				textResult.setText(msg);
				
			}
		});
		btnAdd.setFont(new Font("굴림", Font.PLAIN, 36));
		btnAdd.setBounds(12, 164, 97, 46);
		frame.getContentPane().add(btnAdd);
		
		btnSubtract = new JButton("-");
		btnSubtract.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleButtonClick(e);
			}
		});
		btnSubtract.setFont(new Font("굴림", Font.PLAIN, 36));
		btnSubtract.setBounds(117, 164, 97, 46);
		frame.getContentPane().add(btnSubtract);
		
		btnMultiply = new JButton("X");
		btnMultiply.addActionListener((e) -> handleButtonClick(e));
		btnMultiply.setFont(new Font("굴림", Font.PLAIN, 36));
		btnMultiply.setBounds(226, 164, 97, 46);
		frame.getContentPane().add(btnMultiply);
		
		btnDivision = new JButton("/");
		btnDivision.addActionListener(this::handleButtonClick);
		// AppMain04.this::handleButtonClick
		btnDivision.setFont(new Font("굴림", Font.PLAIN, 36));
		btnDivision.setBounds(335, 164, 97, 46);
		frame.getContentPane().add(btnDivision);
		
		textResult = new JTextArea();
		textResult.setFont(new Font("D2Coding", Font.PLAIN, 18));
		textResult.setBounds(12, 224, 413, 150);
		frame.getContentPane().add(textResult);
	}
	
	private void handleButtonClick(ActionEvent event) {
		// JTextField에 입력된 문자열을 숫자(double)로 변환
		double x = 0;
		double y = 0;
		try {
			x = Double.parseDouble(textField1.getText());
			y = Double.parseDouble(textField2.getText());
		} catch (NumberFormatException ex) {
			textResult.setText("Number1 또는 Number2에는 숫자로 입력...");
			return; // 메서드 종료
		}
		
		double result = 0; // 사칙연산 결과를 저장할 변수
		String operator = ""; // 사직연산 기호(+, -, x, /)를 저장할 변수
		
		Object source = event.getSource(); // 이벤트가 발생한 소스(UI 컴포넌트)
		if (source == btnAdd) {
			result = x + y;
			operator = "+";
		} else if (source == btnSubtract) {
			result = x - y;
			operator = "-";
		} else if (source == btnMultiply) {
			result = x * y;
			operator = "x";
		} else if (source == btnDivision) {
			result = x / y;
			operator = "/";
		}
		
		// 결과창에 보여줄 문자열
		String msg = String.format("%f %s %f = %f", x, operator, y, result);
		textResult.setText(msg);
		
	}
}
