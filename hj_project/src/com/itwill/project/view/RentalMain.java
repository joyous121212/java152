package com.itwill.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itwill.project.controller.RentalDao;
import com.itwill.project.model.Rental;
import com.itwill.project.view.RentalDetailFrame.CreateNotify;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class RentalMain implements CreateNotify {

	private JFrame frame;
	private JButton btnRegister;
	private JButton btnCheck;
	private JButton btnManager;
	
	private RentalDao dao = RentalDao.getInstance();
	private JLabel lblTitle;
	String theaterIcon = "image\\theater icon.png";
	private JPanel panelImage;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalMain window = new RentalMain();
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
	public RentalMain() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(theaterIcon));
		frame.setBounds(100, 100, 438, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnRegister = new JButton("대관 신청");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RentalDetailFrame.showRentalInformationFrame(frame, RentalMain.this);
			}
		});
		btnRegister.setFont(new Font("D2Coding", Font.PLAIN, 30));
		btnRegister.setBounds(12, 170, 398, 69);
		frame.getContentPane().add(btnRegister);
		btnCheck = new JButton("신청 내역 확인");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentalCheckDialog.showRentalCheckDialog();
			}
		});
		btnCheck.setFont(new Font("D2Coding", Font.PLAIN, 30));
		btnCheck.setBounds(12, 263, 398, 69);
		frame.getContentPane().add(btnCheck);
		
		lblTitle = new JLabel("연습실 대관 시스템");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 27));
		lblTitle.setBounds(12, 46, 398, 95);
		frame.getContentPane().add(lblTitle);
		
		btnManager = new JButton("관리자");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentalManagerDialog.showRentalManagerDialog();
			}
		});
		btnManager.setFont(new Font("D2Coding", Font.PLAIN, 24));
		btnManager.setBounds(251, 367, 159, 43);
		frame.getContentPane().add(btnManager);
		
		
		panelImage = new JPanel();
		panelImage.setBounds(0, 0, 422, 432);
		frame.getContentPane().add(panelImage);
		panelImage.setLayout(null);
	}

	@Override
	public void notifyCreateSuccess() {
		// 테이블에 update 성공했을 때 RentalDetailFrame이 호출하는 메서드
		JOptionPane.showMessageDialog(frame, "테이블에 저장 성공!");
	}
}
