package src.View;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginUI extends JFrame implements ActionListener{

	JTextField id_txt;
	JTextField pw_txt;
	JButton login_btn;
	JButton join_btn;
	Label L_id;
	Label L_pw;
	
	
	//
	MainGUI maingui;
	JoinUI joinUI;
//	MemberUI membergui;
//	UserUI usergui;
	
	// DB연결정보 저장용 변수
		String id = "root";
		String pw = "1234";
		String url = "jdbc:mysql://localhost:3306/musicdb";

		// JDBC참조변수
		Connection conn = null; // DB연결용 참조변수
		PreparedStatement pstmt = null; // SQL쿼리 전송용 참조변수
		ResultSet rs = null; // SQL쿼리 결과(SELECT결과) 수신용 참조변수
	
	public LoginUI(){
		super("LOGIN");
		setBounds(400, 100, 300, 170);

		// 패널
		JPanel panel = new JPanel();
		panel.setLayout(null);

		
		//
		id_txt = new JTextField();
		pw_txt = new JTextField();
		login_btn = new JButton("로그인");
		join_btn = new JButton("회원가입");
		L_id = new Label(" ID");
		L_pw = new Label("PW");
		
		//
		id_txt.setBounds(40,15,140,30);
		pw_txt.setBounds(40,50,140,30);
		login_btn.setBounds(185,15,80,65);
		join_btn.setBounds(40,90,225,30);
		L_id.setBounds(10, 15, 30, 30);
		L_pw.setBounds(10, 50, 30, 30);
		
		//EVENT
		login_btn.addActionListener(this);
		join_btn.addActionListener(this);
		
		
		// 컴포넌트를 패널에 추가
		panel.add(id_txt);
		panel.add(pw_txt);
		panel.add(login_btn);
		panel.add(join_btn);
		panel.add(L_id);
		panel.add(L_pw);
		 
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		//joinUI
		joinUI = new JoinUI();
		joinUI.setVisible(false);
//		joinUI.setMainGUI(this);
		
		
//		membergui = new MemberUI();
//		usergui = new UserUI();
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이브 적재
			System.out.println("Driver Loading Success..");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected..");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	maingui = new MainGUI();
            	maingui.setVisible(true);
            	
            	
            }
        });
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login_btn) {
			System.out.println("LOGIN_BTN");
			//로그인 체크
			
			
			//회원사서인지 판단해서 창띄우기
			if(id_txt.getText().equals("1"))
			{
				this.setVisible(false);
//				membergui.setVisible(true);
			}
			else if(id_txt.getText().equals("2"))
			{
				this.setVisible(false);
//				usergui.setVisible(true);
			}
			 
			
		}
		
		if(e.getSource()==join_btn) {
			System.out.println("JOIN_BTN");
			joinUI.setVisible(true);
			this.setVisible(false);
		}
		
	}
	

	public void setMainGUI(MainGUI maingui) {
		this.maingui = maingui;
//		membergui.setMainUI(maingui);
//		usergui.setMainUI(maingui);
		
		
	}
	
}
