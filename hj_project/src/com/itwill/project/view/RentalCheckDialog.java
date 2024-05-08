package com.itwill.project.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.project.controller.RentalDao;
import com.itwill.project.model.Rental;
import com.itwill.project.model.RentalInfo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;

public class RentalCheckDialog extends JDialog {
	
	private String contentImage = "image/background.jpg";

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
	private JLabel lblContentImage;
	private JLabel lblButtonPaneImage;
	
	private Component parent;

	/**
	 * Launch the application.
	 */
	public static void showRentalCheckDialog(Component parent) {
		try {
			RentalCheckDialog dialog = new RentalCheckDialog(parent);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RentalCheckDialog(Component parent) {
		this.parent = parent;
		initialize();
	}
	public void initialize() {
		setTitle("이메일, 비밀번호 검색");
		getContentPane().setBackground(SystemColor.info);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x, y, 444, 219);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
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
			lblContentImage = new JLabel(new ImageIcon(contentImage));
			lblContentImage.setBackground(new Color(226, 217, 200));
			lblContentImage.setBounds(0, 0, 428, 139);
			contentPanel.add(lblContentImage);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setBounds(0, 134, 428, 46);
			contentPanel.add(buttonPane);
			buttonPane.setBackground(SystemColor.menu);
			{
				okButton = new JButton("OK");
				okButton.setBackground(new Color(226, 217, 200));
				okButton.setBounds(207, 5, 87, 31);
				okButton.addActionListener((e) -> search());
				buttonPane.setLayout(null);
				okButton.setFont(new Font("D2Coding", Font.PLAIN, 18));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(226, 217, 200));
				cancelButton.addActionListener((e) -> dispose());
				cancelButton.setBounds(306, 5, 117, 31);
				cancelButton.setFont(new Font("D2Coding", Font.PLAIN, 18));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			lblButtonPaneImage = new JLabel(new ImageIcon(contentImage));
			lblButtonPaneImage.setBounds(0, 0, 428, 46);
			buttonPane.add(lblButtonPaneImage);
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
		List<Rental> list = dao.searchEmailPw(email, pw);
		// TODO: 둘다 없는 경우
		List<Rental> result = new ArrayList<>();
		
		int id;
		if (list.isEmpty()) {
			id = 0;
		} else {
			id = list.getLast().getId();
		}

		System.out.println(list.isEmpty());
		System.out.println(rental);
		System.out.println(password);
		System.out.println(rental.equals(password));
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(result);
		
		if (rental.isEmpty() && password.isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "다시한번 확인하세요");
			return;
		}

		if (rental.equals(password)) {
			RentalCheckFrame.showRentalCheckFrame(RentalCheckDialog.this, email, pw, id);
			dispose();
		} else if(list.size() == 0){
			JOptionPane.showMessageDialog(contentPanel, "다시한번 확인하세요");
			return;
		}
	
	}
}
