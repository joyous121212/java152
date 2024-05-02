package com.itwill.project.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RentalManagerDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonPane;

	/**
	 * Launch the application.
	 */
	public static void showRentalManagerDialog() {
		try {
			RentalManagerDialog dialog = new RentalManagerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RentalManagerDialog() {
		initialize();
	}
	
	public void initialize() {
		setTitle("관리자 로그인");
		setBounds(100, 100, 407, 224);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(114, 75, 256, 34);
			contentPanel.add(textField);
		}
		{
			lblPassword = new JLabel("비밀번호");
			lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 20));
			lblPassword.setBounds(12, 75, 100, 34);
			contentPanel.add(lblPassword);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(114, 10, 256, 34);
			contentPanel.add(textField_1);
		}
		{
			lblEmail = new JLabel("이메일");
			lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 20));
			lblEmail.setBounds(12, 10, 74, 34);
			contentPanel.add(lblEmail);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RentalManagerFrame.showRentalManagerFrame();
						dispose();
					}
				});
				okButton.setFont(new Font("D2Coding", Font.PLAIN, 18));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("D2Coding", Font.PLAIN, 18));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
