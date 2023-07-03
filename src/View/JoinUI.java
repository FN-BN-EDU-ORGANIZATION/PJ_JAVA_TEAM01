package src.View;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import src.Domain.Domain1.Dao.MemberDao;
import src.Domain.Domain1.Dto.MemberDto;

public class JoinUI extends JFrame implements ActionListener{

	JTable table;
	JTextField txt1; //아이디
	JPasswordField txt2; //비밀번호
	JPasswordField txt3; //비밀번호확인
	JTextField txt4; //이름
	JTextField txt5; //주소
	JTextField txt6; //핸드폰번호
	JButton btn1; //가입버튼
	JButton btn2; //뒤로가기버튼(로그인화면출력)
	JButton btn3; //id 중복확인
	Label L_txt1; 
	Label L_txt2;
	Label L_txt3;
	Label L_txt4;
	Label L_txt5;
	Label L_txt6;
	
	JFrame Frm_join;
	
	LoginUI loginUI;
	MainGUI mainGUI;
	
	
	// DB연결정보 저장용 변수
	String id = "root";
	String pw = "1234";
	String url = "jdbc:mysql://localhost:3306/musicdb";

		// JDBC참조변수
	Connection conn = null; // DB연결용 참조변수
	PreparedStatement pstmt = null; // SQL쿼리 전송용 참조변수
	ResultSet rs = null; // SQL쿼리 결과(SELECT결과) 수신용 참조변수
	
	
	JoinUI(){
		super("JOIN US!");
		setBounds(100, 100, 400, 380);
		
		// 패널
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		//
		txt1 = new JTextField();
		txt2 = new JPasswordField();
		txt3 = new JPasswordField();
		txt4 = new JTextField();
		txt5 = new JTextField();
		txt6 = new JTextField();
		btn1 = new JButton("돌아가기");
		btn2 = new JButton("회원가입");
		btn3 = new JButton("확인");
		L_txt1 = new Label("ID");
		L_txt2 = new Label("PW");
		L_txt3 = new Label("PW Check");
		L_txt4 = new Label("NAME");
		L_txt5 = new Label("ADDRESS");
		L_txt6 = new Label("PHONE");
		
		//
		txt1.setBounds(120,10,150,30);
		txt2.setBounds(120,50,230,30);
		txt3.setBounds(120,90,230,30);
		txt4.setBounds(120,130,230,30);
		txt5.setBounds(120,170,230,30);
		txt6.setBounds(120,210,230,30);
		btn1.setBounds(30,270,100,40);//뒤로
		btn2.setBounds(250,270,100,40);//가입
		btn3.setBounds(275,10,75,30);
		L_txt1.setBounds(50,10,60,30);
		L_txt2.setBounds(45,50,60,30);
		L_txt3.setBounds(30,90,60,30);
		L_txt4.setBounds(40,130,60,30);
		L_txt5.setBounds(30,170,60,30);
		L_txt6.setBounds(35,210,60,30);
		
		//EVENT
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		//컴포넌트 패널에 추가
		panel.add(txt1);
		panel.add(txt2);
		panel.add(txt3);
		panel.add(txt4);
		panel.add(txt5);
		panel.add(txt6);
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(L_txt1);
		panel.add(L_txt2);
		panel.add(L_txt3);
		panel.add(L_txt4);
		panel.add(L_txt5);
		panel.add(L_txt6);
		
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
	
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1) {
			System.out.println("BACK_BTN");
			this.loginUI = new LoginUI();
			this.loginUI.setVisible(true);
			loginUI.setVisible(true);
			this.setVisible(false);
		}else if(e.getSource()==btn2) {
			System.out.println("JOIN_BTN");
			// 입력 값 확인
		    String name = txt1.getText();
		    String id = txt2.getText();
		    String pw = txt4.getText();
		    String email = txt5.getText();
		    String phone = txt6.getText();
		    
		    // 빈 값 체크
		    if (name.isEmpty() || id.isEmpty() || pw.isEmpty() || email.isEmpty() || phone.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "모든 필드를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
		        return; // 회원 가입 중단
		    }
		    
			//DB에 INSERT
		    try {
		        conn = DriverManager.getConnection(url, this.id, this.pw);
		        pstmt = conn.prepareStatement("insert into tbl_member values(?,?,?,?,?,null)");
		        pstmt.setString(1, name);
		        pstmt.setString(2, id);
		        pstmt.setString(3, pw);
		        pstmt.setString(4, email);
		        pstmt.setString(5, phone);
		        int result = pstmt.executeUpdate();
		        if (result > 0) {
		            System.out.println("INSERT 성공");
		            JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!", "JOIN success!", JOptionPane.INFORMATION_MESSAGE);
		            this.Frm_join = new JFrame();
		            Frm_join.setVisible(false);
		            this.setVisible(false);
		            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            loginUI = new LoginUI();
		            loginUI.setVisible(true);
		        } else {
		            System.out.println("INSERT 실패");
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		}else if(e.getSource()==btn3) {
//			System.out.println("Duplicate");
			try {
				conn=DriverManager.getConnection(url,id,pw);
				pstmt = conn.prepareStatement("select * from tbl_member where id=?");
				pstmt.setString(1, txt1.getText());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					JOptionPane.showMessageDialog(null,"이미 존재하는 아이디입니다..", "Duplicate",JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"사용가능한 아이디입니다!", "Useful",JOptionPane.INFORMATION_MESSAGE);
				}
				pstmt.close();
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

//	public static void main(String[] args) {
//		new JoinUI();
//	}
	
}