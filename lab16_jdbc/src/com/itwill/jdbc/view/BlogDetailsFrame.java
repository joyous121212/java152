package com.itwill.jdbc.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.jdbc.controller.BlogDao;
import com.itwill.jdbc.model.Blog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class BlogDetailsFrame extends JFrame {
	
	public interface UpdateNotify {
		public void notifyUpdateSuccess();
	}

	private static final long serialVersionUID = 1L;
	
	private BlogDao dao = BlogDao.getInstance();
	private Component parent;
	private int blogId;
	private UpdateNotify app;
	
	private JPanel contentPane;
	private JLabel lblId;
	private JTextField textId;
	private JTextField textTitle;
	private JScrollPane scrollPane;
	private JLabel lblTitle_1;
	private JLabel lblTitle;
	private JTextArea textContent;
	private JTextField textWriter;
	private JLabel lblWriter;
	private JTextField textCreated;
	private JTextField textModified;
	private JLabel lblModified;
	private JLabel lblCreated;
	private JButton btnDelete;
	private JButton btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void showBlogDetailsFrame(Component parent, int blogId, UpdateNotify app) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlogDetailsFrame frame = new BlogDetailsFrame(parent, blogId, app);
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
	
	public BlogDetailsFrame(Component parent, int blogId, UpdateNotify app) {
		  this.parent = parent;
	        this.blogId = blogId;
	        this.app = app;
	        
	        initialize();
	        initializeBlog();
	}
	public void initialize() {
		setTitle("블로그 상세보기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
        int y = 0;
        if (parent != null) {
            x = parent.getX();
            y = parent.getY();
        }
        setBounds(x, y, 450, 682);
        
        if (parent == null) {
            setLocationRelativeTo(null);
        }
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("번호");
		lblId.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblId.setBounds(12, 10, 131, 42);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textId.setBounds(155, 10, 269, 42);
		contentPane.add(textId);
		textId.setColumns(10);
		
		lblTitle = new JLabel("제목");
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblTitle.setBounds(12, 62, 131, 42);
		contentPane.add(lblTitle);
		
		textTitle = new JTextField();
		textTitle.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textTitle.setColumns(10);
		textTitle.setBounds(155, 62, 269, 42);
		contentPane.add(textTitle);
		
		lblTitle_1 = new JLabel("내용");
		lblTitle_1.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblTitle_1.setBounds(12, 114, 131, 42);
		contentPane.add(lblTitle_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 166, 412, 187);
		contentPane.add(scrollPane);
		
		textContent = new JTextArea();
		textContent.setFont(new Font("D2Coding", Font.PLAIN, 24));
		scrollPane.setViewportView(textContent);
		
		lblWriter = new JLabel("작성자");
		lblWriter.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblWriter.setBounds(12, 363, 131, 42);
		contentPane.add(lblWriter);
		
		textWriter = new JTextField();
		textWriter.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textWriter.setColumns(10);
		textWriter.setBounds(155, 363, 269, 42);
		contentPane.add(textWriter);
		
		lblCreated = new JLabel("작성시간");
		lblCreated.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblCreated.setBounds(12, 415, 131, 42);
		contentPane.add(lblCreated);
		
		textCreated = new JTextField();
		textCreated.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textCreated.setEditable(false);
		textCreated.setColumns(10);
		textCreated.setBounds(155, 415, 269, 42);
		contentPane.add(textCreated);
		
		lblModified = new JLabel("수정시간");
		lblModified.setFont(new Font("D2Coding", Font.PLAIN, 24));
		lblModified.setBounds(12, 467, 131, 42);
		contentPane.add(lblModified);
		
		textModified = new JTextField();
		textModified.setFont(new Font("D2Coding", Font.PLAIN, 24));
		textModified.setEditable(false);
		textModified.setColumns(10);
		textModified.setBounds(155, 467, 269, 42);
		contentPane.add(textModified);
		
		btnUpdate = new JButton("업데이트");
		btnUpdate.addActionListener((e) -> updateBlog());
		btnUpdate.setFont(new Font("D2Coding", Font.PLAIN, 24));
		btnUpdate.setBounds(94, 541, 159, 50);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("취소");
		btnDelete.addActionListener((e) -> dispose());
		btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 24));
		btnDelete.setBounds(265, 541, 159, 50);
		contentPane.add(btnDelete);
	}
	
	 private void initializeBlog() {
	        Blog blog = dao.read(blogId);
	        if (blog == null) return;
	        
	        textId.setText(blogId + "");
	        textTitle.setText(blog.getTitle());
	        textContent.setText(blog.getContent());
	        textWriter.setText(blog.getWriter());
	        textCreated.setText(blog.getCreatedTime().toString());
	        textModified.setText(blog.getModifiedTime().toString());
	    }
	 
	 private void updateBlog() {
		 String title = textTitle.getText();
		 String content = textContent.getText();
	        if (title.equals("") || title.equals("")) {
	            JOptionPane.showMessageDialog(this, 
	                    "제목과 내용은 반드시 입력해야 합니다.", 
	                    "경고", JOptionPane.WARNING_MESSAGE);
	            return;
	 }
	        
	        Blog blog = new Blog(blogId, title, content, null, null, null);
	        int result = dao.update(blog);
	        if (result == 1) {
	            app.notifyUpdateSuccess();
	            dispose();
	        } else {
	            JOptionPane.showMessageDialog(this, "업데이트 실패");
	        }
	    }
}
