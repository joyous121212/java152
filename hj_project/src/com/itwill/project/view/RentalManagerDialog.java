package com.itwill.project.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;

public class RentalManagerDialog extends JDialog {
	
	private String image = "image/background.jpg";

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonPane;
	private JLabel lblContentImage;
	private JLabel lblButtonPaneImage;
	
	private Component parent;

	/**
	 * Launch the application.
	 */
	public static void showRentalManagerDialog(Component parent) {
		try {
			RentalManagerDialog dialog = new RentalManagerDialog(parent);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RentalManagerDialog(Component parent) {
		this.parent = parent;
		initialize();
	}
	
	public void initialize() {
		setTitle("관리자 로그인");
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}		
		setBounds(x, y, 407, 224);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(114, 75, 256, 34);
		contentPanel.add(textField);

		lblPassword = new JLabel("비밀번호");
		lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblPassword.setBounds(12, 75, 100, 34);
		contentPanel.add(lblPassword);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(114, 10, 256, 34);
		contentPanel.add(textField_1);

		lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblEmail.setBounds(12, 10, 74, 34);
		contentPanel.add(lblEmail);
		
		lblContentImage = new JLabel(new ImageIcon(image));
		lblContentImage.setBounds(0, 0, 391, 144);
		contentPanel.add(lblContentImage);

		buttonPane = new JPanel();
		buttonPane.setBounds(0, 140, 391, 45);
		contentPanel.add(buttonPane);
		buttonPane.setBackground(SystemColor.menu);
		okButton = new JButton("OK");
		okButton.setBackground(new Color(226, 217, 200));
		okButton.setBounds(202, 5, 68, 31);
		okButton.addActionListener((e) -> checkManager());
		buttonPane.setLayout(null);
		okButton.setFont(new Font("D2Coding", Font.PLAIN, 18));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(226, 217, 200));
		cancelButton.setBounds(275, 5, 111, 31);
		cancelButton.setFont(new Font("D2Coding", Font.PLAIN, 18));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		lblButtonPaneImage = new JLabel(new ImageIcon(image));
		lblButtonPaneImage.setBounds(0, 0, 391, 45);
		buttonPane.add(lblButtonPaneImage);

	}

	private void checkManager() {
		if (textField.getText().equals("1111") && textField_1.getText().equals("1111")) {
			RentalManagerFrame.showRentalManagerFrame(RentalManagerDialog.this);
			dispose();
		} else {
			JOptionPane.showMessageDialog(contentPanel, "다시 한번 확인하세요.");
		}
	}
}
