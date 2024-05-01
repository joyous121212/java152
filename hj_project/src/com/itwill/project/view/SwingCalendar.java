package com.itwill.project.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;

public class SwingCalendar {
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void showSwingCalendar() {
		com.itwill.project.view.Calendarmain calendarmain = new Calendarmain();
	}
}

class Calendarmain extends JFrame implements ActionListener {

	Container container = getContentPane();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	JButton btnBefore = new JButton("Before");
	JButton btnAfter = new JButton("After");
	
	JLabel label = new JLabel("00년 0월");
	
	JButton[] buttons = new JButton[49];
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
		container.setLayout(new BorderLayout());
		container.add("North", panel1);
		container.add("Center", panel2);
		
		panel1.setLayout(new FlowLayout());
		panel1.add(btnBefore);
		panel1.add(label);
		panel1.add(btnAfter);
		
		Font font = new Font("D2Coding", Font.BOLD, 24);
		btnAfter.setFont(font);
		btnBefore.setFont(font);
		label.setFont(font);
		
		label.setText(cf.getCalText());
		
		panel2.setLayout(new GridLayout(7, 7, 5, 5));
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			panel2.add(buttons[i]);
			
			buttons[i].setFont(font);
			
			if(i < 7) buttons[i].setText(dayNames[i]);
			
			if(i%7 == 0) buttons[i].setForeground(Color.RED);
			if(i%7 == 6) buttons[i].setForeground(Color.BLUE);
			
		}
		cf.setButtons(buttons);
		cf.calSet();
	}
	
	private void start() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		btnAfter.addActionListener(this);
		btnBefore.addActionListener(this);
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




