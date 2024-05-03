package com.itwill.project.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.project.controller.RentalDao;
import com.itwill.project.model.Rental;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class RentalDetailFrame extends JFrame {
	
	public interface CreateNotify {
		void notifyCreateSuccess();
	}

	private static final long serialVersionUID = 1L;
	
	private RentalDao dao = RentalDao.getInstance();
	private CreateNotify app;
	
	private Component parent;
	private JPanel contentPane;
	private JLabel lblName;
	private JTextField textName;
	private JTextField textEmail;
	private JTextField textPassword;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblContent;
	private JTextArea textContent;
	private JRadioButton rbPlay;
	private JRadioButton rbMusic;
	private JRadioButton rbDance;
	private JRadioButton rbTradition;
	private JRadioButton rbEtc;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnBefore;
	private JButton btnConfirm;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void showRentalInformationFrame(Component parent, CreateNotify app) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalDetailFrame frame = new RentalDetailFrame(parent, app);
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
	public RentalDetailFrame(Component parent, CreateNotify app) {
		this.parent = parent;
		this.app = app; 
		
		initialize();
	}
	
	public void initialize() {
		setTitle("정보 입력");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x, y, 450, 527);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblName = new JLabel("이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblName.setBounds(12, 10, 118, 48);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textName.setBounds(142, 11, 280, 48);
		contentPane.add(textName);
		textName.setColumns(10);
		
		lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblEmail.setBounds(12, 68, 118, 48);
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textEmail.setColumns(10);
		textEmail.setBounds(142, 69, 280, 48);
		contentPane.add(textEmail);
		
		lblPassword = new JLabel("비밀번호");
		lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblPassword.setBounds(12, 126, 118, 48);
		contentPane.add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textPassword.setColumns(10);
		textPassword.setBounds(142, 127, 280, 48);
		contentPane.add(textPassword);
		
		lblContent = new JLabel("대관 내용(상세히 적어주세요.)");
		lblContent.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblContent.setBounds(12, 184, 410, 48);
		contentPane.add(lblContent);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 230, 410, 129);
		contentPane.add(scrollPane);
		
		textContent = new JTextArea();
		textContent.setFont(new Font("D2Coding", Font.PLAIN, 18));
		scrollPane.setViewportView(textContent);
		
		rbPlay = new JRadioButton("연극");
		rbPlay.addActionListener((e) -> handleRadioButtonClick(e));
		buttonGroup.add(rbPlay);
		rbPlay.setBounds(12, 365, 76, 48);
		contentPane.add(rbPlay);
		
		rbDance = new JRadioButton("무용");
		rbDance.addActionListener((e) -> handleRadioButtonClick(e));
		buttonGroup.add(rbDance);
		rbDance.setBounds(92, 365, 76, 48);
		contentPane.add(rbDance);
		
		rbMusic = new JRadioButton("음악");
		rbMusic.addActionListener((e) -> handleRadioButtonClick(e));
		buttonGroup.add(rbMusic);
		rbMusic.setBounds(172, 365, 76, 48);
		contentPane.add(rbMusic);
		
		rbTradition = new JRadioButton("전통예술");
		rbTradition.addActionListener((e) -> handleRadioButtonClick(e));
		buttonGroup.add(rbTradition);
		rbTradition.setBounds(252, 365, 76, 48);
		contentPane.add(rbTradition);
		
		rbEtc = new JRadioButton("기타");
		rbEtc.addActionListener((e) -> handleRadioButtonClick(e));
		buttonGroup.add(rbEtc);
		rbEtc.setBounds(346, 365, 76, 48);
		contentPane.add(rbEtc);
		
		btnBefore = new JButton("이전");
		btnBefore.addActionListener((e) -> dispose());
		btnBefore.setFont(new Font("D2Coding", Font.PLAIN, 24));
		btnBefore.setBounds(150, 419, 130, 46);
		contentPane.add(btnBefore);
		
		btnConfirm = new JButton("완료");
		btnConfirm.addActionListener((e) -> createNewRental());
		btnConfirm.setFont(new Font("D2Coding", Font.PLAIN, 24));
		btnConfirm.setBounds(292, 419, 130, 46);
		contentPane.add(btnConfirm);
	}
	
	private void handleRadioButtonClick(ActionEvent event) {
		JRadioButton rb = (JRadioButton) event.getSource();
		String text = rb.getText();
		boolean selected = rb.isSelected();
	}

	private void createNewRental() {
		// 제목, 내용 작성자에 입력된 내용을 Rental 객체로 만들어서
		// DAO 메서드를 사용해서 DB 테이블에 insert
		String name = textName.getText();
		String email = textEmail.getText();
		String password = textPassword.getText();
		String content = textContent.getText();
		
		if(name.equals("") || email.equals("") || password.equals("") || content.equals("")) {
			JOptionPane.showMessageDialog(RentalDetailFrame.this, "빈칸을 모두 채우세요.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String genre = null;
		
		if(rbPlay.isSelected()) {
			genre = rbPlay.getText();
		} else if (rbDance.isSelected()) {
			genre = rbDance.getText();
		} else if(rbMusic.isSelected()) {
			genre = rbMusic.getText();
		} else if(rbTradition.isSelected()) {
			genre = rbTradition.getText();
		} else if(rbEtc.isSelected()){
			genre = rbEtc.getText();
		} else {
			JOptionPane.showMessageDialog(contentPane, "장르를 선택하세요.");
			return;
		}
		
		Rental rental = new Rental(0, name, email, password, content, genre, null, null);
		int result = dao.create(rental);
		if (result == 1) {
			app.notifyCreateSuccess();
			
			dispose();
		} else {
			JOptionPane.showMessageDialog(RentalDetailFrame.this, "INSERT 실패");
		}
		
		SwingCalendar.showSwingCalendar();		
		
	}





}
