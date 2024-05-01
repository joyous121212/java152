package com.itwill.project.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.project.controller.RentalDao;
import com.itwill.project.model.Rental;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class RentalCheckDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblEmail;
	private JTextField textEmail;
	private JTextField textPW;
	private JLabel lblPassword;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonPane;
	
	private RentalDao dao = RentalDao.getInstance();

	/**
	 * Launch the application.
	 */
	public static void showRentalCheckDialog() {
		try {
			RentalCheckDialog dialog = new RentalCheckDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RentalCheckDialog() {
		setBounds(100, 100, 450, 218);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblEmail = new JLabel("이메일");
			lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 20));
			lblEmail.setBounds(31, 13, 74, 34);
			contentPanel.add(lblEmail);
		}
		{
			textEmail = new JTextField();
			textEmail.setBounds(133, 13, 256, 34);
			contentPanel.add(textEmail);
			textEmail.setColumns(10);
		}
		{
			lblPassword = new JLabel("비밀번호");
			lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 20));
			lblPassword.setBounds(31, 78, 100, 34);
			contentPanel.add(lblPassword);
		}
		{
			textPW = new JTextField();
			textPW.setColumns(10);
			textPW.setBounds(133, 78, 256, 34);
			contentPanel.add(textPW);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener((e) -> search());
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

	private void search() {
		// 이메일, 비밀번호 입력 후 아이디 같으면 다음화면 넘어감
		String email = textEmail.getText();
		String pw = textPW.getText();
		if (email.equals("")) {
			JOptionPane.showMessageDialog(contentPanel, "이메일을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
			textEmail.requestFocus();
			
			return;
		}
		if (pw.equals("")) {
			JOptionPane.showMessageDialog(contentPanel, "비밀번호를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
			textPW.requestFocus();
		}
		
		// DAO메서드를 호출해서 검색 결과를 가져옴.
		List<Rental> rental = dao.searchEmail(email);
		List<Rental> password = dao.searchPW(pw);
		// TODO: 둘다 없는 경우
		List<Rental> result = new ArrayList<>();
		
		if (result.equals(rental) && result.equals(password)) {
			JOptionPane.showMessageDialog(contentPanel, "다시한번 확인하세요");
		} else if (rental.equals(password)){
			RentalCheckFrame.showRentalCheckFrame();
			dispose();
		} else {
			JOptionPane.showMessageDialog(contentPanel, "다시한번 확인하세요");
		}	
	}

}
